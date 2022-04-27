package com.shine.pgp;

public class DecryptionResult {
	private String decryptFileName = "";
	private boolean isSigned = false;
	private PrintablePGPPublicKey signee = null;
	private boolean isSignatureValid = false;
	private Exception signatureException = null;
	private String decryptedText;

	private PrintablePGPSecretKey secretKey;

	public String getDecryptFileName() {
		return this.decryptFileName;
	}

	public void setDecryptFileName(String decryptFileName) {
		this.decryptFileName = decryptFileName;
	}

	public boolean isIsSigned() {
		return this.isSigned;
	}

	public void setIsSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}

	public PrintablePGPPublicKey getSignee() {
		return this.signee;
	}

	public void setSignee(PrintablePGPPublicKey signee) {
		this.signee = signee;
	}

	public boolean isIsSignatureValid() {
		return this.isSignatureValid;
	}

	public void setIsSignatureValid(boolean isSignatureValid) {
		this.isSignatureValid = isSignatureValid;
	}

	public Exception getSignatureException() {
		return this.signatureException;
	}

	public void setSignatureException(Exception signatureException) {
		this.signatureException = signatureException;
	}

	public String getDecryptedText() {
		return this.decryptedText;
	}

	public void setDecryptedText(String decryptedText) {
		this.decryptedText = decryptedText;
	}

	public PrintablePGPSecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(PrintablePGPSecretKey secretKey) {
		this.secretKey = secretKey;
	}

}
