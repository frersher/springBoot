package com.shine.pgp;

import java.util.Iterator;

import org.bouncycastle.openpgp.PGPSecretKey;

public class PrintablePGPSecretKey {

	PGPSecretKey base;

	public PrintablePGPSecretKey(PGPSecretKey iBase) {
		this.base = iBase;
	}

	public PGPSecretKey getSecretKey() {
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
