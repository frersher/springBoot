package com.shine.pgp;

import java.util.Iterator;

import org.bouncycastle.openpgp.PGPPublicKey;

public class PrintablePGPPublicKey {

	PGPPublicKey base;

	public PrintablePGPPublicKey(PGPPublicKey iBase) {
		this.base = iBase;
	}

	public PGPPublicKey getPublicKey() {
		return this.base;
	}

	@Override
	public String toString() {
		StringBuilder outStr = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Iterator iter = this.base.getUserIDs();

		outStr.append("[0x");
		outStr.append(Integer.toHexString((int) this.base.getKeyID()).toUpperCase());
		outStr.append("] ");

		while (iter.hasNext()) {
			outStr.append(iter.next().toString());
			outStr.append("; ");
		}

		return outStr.toString();
	}
}
