package com.shine.pgp;

import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;

public class PgpHelper {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static void doDecrypt(String secretKey, String privateKeyPassphrase, String publicKey, InputStream pgp,
			OutputStream plain) throws Exception {

		InputStream secKeyIn = null;
		InputStream pubKeyIn = null;
		try {
			secKeyIn = new ByteArrayInputStream(secretKey.getBytes());
			pubKeyIn = new ByteArrayInputStream(publicKey.getBytes());
			doDecrypt(secKeyIn, privateKeyPassphrase, pubKeyIn, pgp, plain);
		} finally {
			IOUtils.closeQuietly(secKeyIn);
			IOUtils.closeQuietly(pubKeyIn);
		}

	}

	public static void doDecrypt(InputStream secretKey, String privateKeyPassphrase, InputStream publicKey,
			InputStream pgp, OutputStream plain) throws Exception {
		if (privateKeyPassphrase == null) {
			throw new IllegalArgumentException("privateKeyPassphrase is not specified.");
		}

		if (publicKey == null) {
			throw new IllegalArgumentException("publicKey is not specified.");
		}
		if (secretKey == null) {
			throw new IllegalArgumentException("privateKey is not specified.");
		}

		char[] passwd = privateKeyPassphrase.toCharArray();

		PGPSecretKeyRingCollection pgpSecretKeyRingCollection = new PGPSecretKeyRingCollection(
				PGPUtil.getDecoderStream(secretKey));

		PGPPublicKeyRingCollection pgpPublicKeyRingCollection = new PGPPublicKeyRingCollection(
				PGPUtil.getDecoderStream(publicKey));

		Decrypter.decryptFile(pgpSecretKeyRingCollection, passwd, pgpPublicKeyRingCollection, pgp, plain);

	}

	/**
	 * @param secretKey
	 * @param privateKeyPassphrase
	 * @param publicKey
	 * @param src
	 * @param fileName
	 * @param dest
	 * @throws Exception
	 */
	public static void doEncrypt(InputStream secretKey, String privateKeyPassphrase, InputStream publicKey,
			InputStream src, String fileName, OutputStream dest, String signHashAlgo) throws Exception {

		if (privateKeyPassphrase == null) {
			throw new IllegalArgumentException("privateKeyPassphrase is not specified.");
		}

		char[] passwd = privateKeyPassphrase.toCharArray();

		PGPSecretKeyRingCollection pgpSecretKeyRingCollection = new PGPSecretKeyRingCollection(
				PGPUtil.getDecoderStream(secretKey));

		PGPPublicKeyRingCollection pgpPublicKeyRingCollection = new PGPPublicKeyRingCollection(
				PGPUtil.getDecoderStream(publicKey));

		PGPSecretKey signWithKey = null;
		Iterator<?> secretKeyRings = pgpSecretKeyRingCollection.getKeyRings();
		while (secretKeyRings.hasNext()) {
			PGPSecretKeyRing ring = (PGPSecretKeyRing) secretKeyRings.next();
			Iterator<?> secKeys = ring.getSecretKeys();
			while (secKeys.hasNext()) {
				PGPSecretKey secKey = (PGPSecretKey) secKeys.next();

				if (secKey.isSigningKey()) {
					signWithKey = secKey;
					break;
				}

			}

		}

		if (signWithKey == null) {
			throw new Exception("Cannot find proper sign key ");
		}

		Iterator<?> pubKeyRings = pgpPublicKeyRingCollection.getKeyRings();
		Collection<PGPPublicKey> encKeys = new ArrayList<PGPPublicKey>();

		while (pubKeyRings.hasNext()) {
			PGPPublicKeyRing pubKeyRing = (PGPPublicKeyRing) pubKeyRings.next();

			Iterator<?> pubKeys = pubKeyRing.getPublicKeys();
			while (pubKeys.hasNext()) {

				PGPPublicKey pubKey = (PGPPublicKey) pubKeys.next();

				if (pubKey.isEncryptionKey()) {
					encKeys.add(pubKey);
				}
			}

		}

		Encrypter.encryptFile(dest, src, fileName, encKeys, true, true, signWithKey, passwd, signHashAlgo);

	}

	public static void doEncrypt(String secretKey, String privateKeyPassphrase, String publicKey, File plainFile,
			File pgpFile, String signHashAlgo) throws Exception {
		InputStream secKeyIn = null;
		InputStream pubKeyIn = null;
		InputStream fileIn = null;
		OutputStream pgpOut = null;
		try {
			secKeyIn = new ByteArrayInputStream(secretKey.getBytes());
			pubKeyIn = new ByteArrayInputStream(publicKey.getBytes());
			fileIn = new FileInputStream(plainFile);
			pgpOut = new FileOutputStream(pgpFile);

			doEncrypt(secKeyIn, privateKeyPassphrase, pubKeyIn, fileIn, plainFile.getName(), pgpOut, signHashAlgo);
		} finally {
			IOUtils.closeQuietly(secKeyIn);
			IOUtils.closeQuietly(pubKeyIn);
			IOUtils.closeQuietly(fileIn);
			IOUtils.closeQuietly(pgpOut);
		}
	}

}
