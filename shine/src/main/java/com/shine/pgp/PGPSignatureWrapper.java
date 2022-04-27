package com.shine.pgp;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;

public class PGPSignatureWrapper {
	PGPOnePassSignature sigOnePass;
	PGPSignature sigOldStyle;
	boolean isOnePass;

	public PGPSignatureWrapper(PGPOnePassSignature sigOnePass) {
		this.sigOnePass = sigOnePass;
		this.isOnePass = true;
	}

	public PGPSignatureWrapper(PGPSignature sigOldStyle) {
		this.sigOldStyle = sigOldStyle;
		this.isOnePass = false;
	}

	public void encode(OutputStream outStream) throws IOException {
		if (this.isOnePass)
			this.sigOnePass.encode(outStream);
		else
			this.sigOldStyle.encode(outStream);
	}

	public byte[] getEncoded() throws IOException {
		if (this.isOnePass) {
			return this.sigOnePass.getEncoded();
		}
		return this.sigOldStyle.getEncoded();
	}

	public int getKeyAlgorithm() {
		if (this.isOnePass) {
			return this.sigOnePass.getKeyAlgorithm();
		}
		return this.sigOldStyle.getKeyAlgorithm();
	}

	public int getHashAlgorithm() {
		if (this.isOnePass) {
			return this.sigOnePass.getHashAlgorithm();
		}
		return this.sigOldStyle.getHashAlgorithm();
	}

	public long getKeyID() {
		if (this.isOnePass) {
			return this.sigOnePass.getKeyID();
		}
		return this.sigOldStyle.getKeyID();
	}

	public long getSignatureType() {
		if (this.isOnePass) {
			return this.sigOnePass.getKeyID();
		}
		return this.sigOldStyle.getKeyID();
	}

	public void initVerify(PGPPublicKey pubKey, String provider) throws NoSuchProviderException, PGPException {
		if (this.isOnePass)
			this.sigOnePass.initVerify(pubKey, provider);
		else
			this.sigOldStyle.initVerify(pubKey, provider);
	}

	public void update(byte b) throws SignatureException {
		if (this.isOnePass)
			this.sigOnePass.update(b);
		else
			this.sigOldStyle.update(b);
	}

	public void update(byte[] bytes) throws SignatureException {
		if (this.isOnePass)
			this.sigOnePass.update(bytes);
		else
			this.sigOldStyle.update(bytes);
	}

	public void update(byte[] bytes, int off, int len) throws SignatureException {
		if (this.isOnePass)
			this.sigOnePass.update(bytes, off, len);
		else
			this.sigOldStyle.update(bytes, off, len);
	}

	public boolean verify(PGPSignature pgpSig) throws PGPException, SignatureException {
		if (this.isOnePass) {
			return this.sigOnePass.verify(pgpSig);
		}
		return this.sigOldStyle.verify();
	}
}
