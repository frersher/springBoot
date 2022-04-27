package com.shine.pgp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SignatureException;
import java.util.Iterator;

import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;

public class Decrypter {

	public static DecryptionResult decryptFile(PGPSecretKeyRingCollection secretKeyRing, char[] passwd,
			PGPPublicKeyRingCollection pgpPublicKeyRingCollection, InputStream in, OutputStream out)
			throws IOException, Exception {
		DecryptionResult decryptionRes = new DecryptionResult();
		String outFileName = "";
		PGPPublicKeyEncryptedData pbe = null;

		in = PGPUtil.getDecoderStream(in);

		PGPObjectFactory pgpF = new PGPObjectFactory(in);

		Object o = pgpF.nextObject();

		if (o == null)
			throw new RuntimeException("Cannot recognize input data format");
		PGPEncryptedDataList enc;
		// PGPEncryptedDataList enc;
		if ((o instanceof PGPEncryptedDataList))
			enc = (PGPEncryptedDataList) o;
		else {
			enc = (PGPEncryptedDataList) pgpF.nextObject();
		}

		@SuppressWarnings("rawtypes")
		Iterator encObjects = enc.getEncryptedDataObjects();
		if (!encObjects.hasNext()) {
			throw new RuntimeException("No encrypted data");
		}
		PGPPrivateKey sKey = null;
		PGPSecretKey secretKey = null;

		while (encObjects.hasNext()) {
			Object obj = encObjects.next();
			if ((obj instanceof PGPPublicKeyEncryptedData)) {
				PGPPublicKeyEncryptedData encData = (PGPPublicKeyEncryptedData) obj;

				secretKey = secretKeyRing.getSecretKey(encData.getKeyID());

				if (secretKey != null) {

					sKey = secretKey.extractPrivateKey(passwd, "BC");
					// Let the exception be thrown.

					if (sKey != null) {
						pbe = encData;
						decryptionRes.setSecretKey(new PrintablePGPSecretKey(secretKey));
						break;
					}
				}
			}
		}

		if (sKey == null) {
			throw new RuntimeException("Cannot find private key for the encrypted data.");
		}

		InputStream clear = pbe.getDataStream(sKey, "BC");

		PGPObjectFactory plainFact = new PGPObjectFactory(clear);

		Object message = plainFact.nextObject();
		Object sigLiteralData = null;
		PGPObjectFactory pgpFact = null;

		if ((message instanceof PGPCompressedData)) {
			PGPCompressedData cData = (PGPCompressedData) message;
			pgpFact = new PGPObjectFactory(cData.getDataStream());
			message = pgpFact.nextObject();
			if ((message instanceof PGPOnePassSignatureList)) {
				sigLiteralData = pgpFact.nextObject();
			}
		}
		if ((message instanceof PGPLiteralData)) {
			outFileName = processLiteralData((PGPLiteralData) message, out, null);
		} else if ((message instanceof PGPOnePassSignatureList)) {
			decryptionRes.setIsSigned(true);

			PGPSignatureWrapper sigWrap = new PGPSignatureWrapper(((PGPOnePassSignatureList) message).get(0));

			PGPPublicKey pubKey = pgpPublicKeyRingCollection.getPublicKey(sigWrap.getKeyID());
			if (pubKey == null) {
				throw new Exception("Cannot find the public key [0x"
						+ Integer.toHexString((int) sigWrap.getKeyID()).toUpperCase() + "] in the pubring");

			}

			decryptionRes.setSignee(new PrintablePGPPublicKey(pubKey));
			sigWrap.initVerify(pubKey, "BC");
			outFileName = processLiteralData((PGPLiteralData) sigLiteralData, out, sigWrap);
			PGPSignatureList sigList = (PGPSignatureList) pgpFact.nextObject();
			decryptionRes.setIsSignatureValid(sigWrap.verify(sigList.get(0)));

		} else if ((message instanceof PGPSignatureList)) {
			decryptionRes.setIsSigned(true);

			PGPSignatureWrapper sigWrap = new PGPSignatureWrapper(((PGPSignatureList) message).get(0));

			PGPPublicKey pubKey = pgpPublicKeyRingCollection.getPublicKey(sigWrap.getKeyID());
			if (pubKey == null) {
				throw new Exception("Cannot find the public key [0x"
						+ Integer.toHexString((int) sigWrap.getKeyID()).toUpperCase() + "] in the pubring");

			}
			decryptionRes.setSignee(new PrintablePGPPublicKey(pubKey));
			sigWrap.initVerify(pubKey, "BC");
			if (pgpFact == null) {
				throw new RuntimeException("pgpFact is null.");
			}
			sigLiteralData = pgpFact.nextObject();
			outFileName = processLiteralData((PGPLiteralData) sigLiteralData, out, sigWrap);
			decryptionRes.setIsSignatureValid(sigWrap.verify(null));

		} else {
			throw new PGPException(
					"message is not a simple encrypted file - type unknown.\n(" + message.getClass() + ")");
		}

		if ((pbe.isIntegrityProtected()) && (!pbe.verify())) {
			throw new Exception("Message failed integrity check");
		}
		decryptionRes.setDecryptFileName(outFileName);

		return decryptionRes;
	}

	private static String processLiteralData(PGPLiteralData ld, OutputStream out, PGPSignatureWrapper sig)
			throws IOException, SignatureException {
		String outFileName = ld.getFileName();
		InputStream unc = ld.getInputStream();

		if (sig == null) {
			int ch;
			while ((ch = unc.read()) >= 0)
				out.write(ch);
		} else {
			int ch;
			while ((ch = unc.read()) >= 0) {
				out.write(ch);
				sig.update((byte) ch);
			}
		}
		return outFileName;
	}

}
