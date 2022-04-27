package com.shine.pgp;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;

public class Encrypter {

	public static void encryptFile(OutputStream out, InputStream in, String fileName, Collection<PGPPublicKey> encKeys,
			boolean armor, boolean withIntegrityCheck, PGPSecretKey signWithKey, char[] signKeyPass,
			String signHashAlgo) throws Exception {
		PGPSignatureGenerator signatureGenerator = null;
		if (armor) {
			out = new ArmoredOutputStream(out);
		}

		PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(PGPEncryptedData.CAST5,
				withIntegrityCheck, new SecureRandom(), "BC");

		for (PGPPublicKey p : encKeys) {
			encryptedDataGenerator.addMethod(p);
		}

		OutputStream encryptedOut = encryptedDataGenerator.open(out, new byte[65536]);

		PGPCompressedDataGenerator compressedDataGenerator = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
		OutputStream compressedOut = compressedDataGenerator.open(encryptedOut);

		if (signWithKey != null) {
			PGPPublicKey pubSigKey = signWithKey.getPublicKey();
			PGPPrivateKey secretKey = signWithKey.extractPrivateKey(signKeyPass, "BC");
			signatureGenerator = new PGPSignatureGenerator(pubSigKey.getAlgorithm(), resolveHashAlgo(signHashAlgo),
					"BC");
			signatureGenerator.initSign(0, secretKey);
			@SuppressWarnings("rawtypes")
			Iterator it = pubSigKey.getUserIDs();
			if (it.hasNext()) {
				PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
				spGen.setSignerUserID(false, (String) it.next());
				signatureGenerator.setHashedSubpackets(spGen.generate());
			}
			signatureGenerator.generateOnePassVersion(false).encode(compressedOut);
		}

		PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
		OutputStream literalOut = literalDataGenerator.open(compressedOut, 'b', fileName, new Date(), new byte[65536]);
		// FileInputStream inputFileStream = new FileInputStream(fileName);
		byte[] buf = new byte[65536];
		int len;
		while ((len = in.read(buf)) > 0) {
			literalOut.write(buf, 0, len);
			if (signatureGenerator != null) {
				signatureGenerator.update(buf, 0, len);
			}
		}
		literalOut.close();
		literalDataGenerator.close();
		if (signatureGenerator != null) {
			signatureGenerator.generate().encode(compressedOut);
		}
		compressedOut.close();
		compressedDataGenerator.close();
		encryptedOut.close();
		encryptedDataGenerator.close();
		// in.close();

		if (armor) {
			out.close();
		}

	}

	private static int resolveHashAlgo(String signHashAlgo) throws Exception {
		try {
			Field f = HashAlgorithmTags.class.getField(signHashAlgo);
			return f.getInt(null);
		} catch (Exception e) {
			throw new Exception("Not valid signHashAlgo : " + signHashAlgo, e);
		}
	}

}
