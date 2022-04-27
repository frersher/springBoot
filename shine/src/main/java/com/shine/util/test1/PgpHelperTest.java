package com.shine.util.test1;

import com.shine.pgp.PgpHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PgpHelperTest {
	// xtransfer 公钥,存放在银行,用于加密
	static final String PUBLIC_KEY_STREAM = "-----BEGIN PGP PUBLIC KEY BLOCK-----\n"
			+ "\n"
			+ "mQINBGJC0JYBEADHKqP5mAMv1S0YTS61z7p8jEiDgcjJJS8x4QyW9Gra+GYXw8+R\n"
			+ "WJdxy6AoPO//sZBouVhuvK7ypgAIRdId8f/t3CHHzNFdhPQ3tUGeTrJD6Vnz8XwM\n"
			+ "SEh+Kw0j+NX5fQYyoeDHQ0JHDyI5lPrh+I+dL5OgsHSHCPdcn21YDa5rxDek+D8+\n"
			+ "XRq162BNJwrY1608rOzp3T5nz6w3U+27FS28CDeSII11KcMTX8/Sfs/kcuJlVxS9\n"
			+ "sFbHjMSr15c1rNTEQ/d1wM1xnK+zQ0udRfokrwjOdqFCjfpcZF9yGrWAfKRr7aLk\n"
			+ "QKzmZ8l+cTrQnod1+9ZupkFey8r5g6PyCEO4lzMC89NqTb0h1PBdFaau3EaTjorj\n"
			+ "gf/wXpJbHI8OIKwrHNRStL64yjtISX+nBQ301bKAUHiDOFE8Z+G3sfYlQ9gtLOyM\n"
			+ "J5S4lo3rwvxfFlPjzLTGEwhacLxyOEUNMgzteknZd5RJGTsEgtIeGCPqDDp0lvIk\n"
			+ "35IssmCocJ5s0jnUmdQoRH+l7kzlx+jF4Nzjopq1UemTVh1MZSUPV9vf9VxDhR1r\n"
			+ "HisbjZLPWmaGOGQEn4sH7K6yPU0rVsWvD/iuiGRuvQ0h0/m200TWSGJIuwOvSc1W\n"
			+ "N6XjFid14VIE/1OuhZKhLTznTkMsUBOkYieedcb3380W0eRujYn5J4OvCQARAQAB\n"
			+ "tBxUb3BQYXkgPGFsc290cmFuc0Bmb3N1bi5jb20+iQJUBBMBCAA+FiEE4DTLV4M1\n"
			+ "9or8LNLY0Qf/u+MxZ+0FAmJC0JYCGwMFCRaSEioFCwkIBwIGFQoJCAsCBBYCAwEC\n"
			+ "HgECF4AACgkQ0Qf/u+MxZ+21WQ//S1HMSxwlkGPZcEJkDGWKrNU/2xAh3TudqZ46\n"
			+ "FuOs90TIqKTZ0ojNCFjo5v7kxE1VqT8R8Iw1RxuFsMbX7465VURheYVhLopHJxkL\n"
			+ "AG5IDjzjvU2OU4Eq9BAsojr3EWK7j3JiSHwAHM2yaUQ5PVLFhUFCddzTl/Nd42c9\n"
			+ "RbUS/A2x5XPUKKEiK448xZEID8y+1EYEMOzqyS8bFFyhrIota4X5RbyZ88LARHTx\n"
			+ "Pql1bQkZk5giCf8qe4ziaS89hoFmjSePNuMgvTbtvgBroMnrzY3VE0RSNrya+Aov\n"
			+ "HMX+u83X89s72lIkhgxvcNkisN+hsDYoEpvBb7Gv0kFz3OIzDBzRxRgK05ivzvVW\n"
			+ "MQBhy+qeYvRWlixp3PETbPnILypkXtgYxeFybJROoc28eberc4ByfOKwRn3QX8cq\n"
			+ "mkiulNPvi8RG9olZn7XczdD/b4ToTo8md8J5u//QkbHu5chbQLz9YK7hmURLYCdf\n"
			+ "fTKemq4fzpk5K+kytvloSD5ynJDMHPHWM8d/sQySIqg0iBIP3pcy7k2k7G61g6qi\n"
			+ "JrHo795bIB49Rn1ZEnGvxGHHvux1iaQTRe2kCKwt4EHzYwITYoEpPToNbMCMPyjs\n"
			+ "IbFzj5XtumfNUU3z75D3AG33IH/2A4f1LlhRSQsmMhx/6vb122RPRXfjaHUHQ7X1\n"
			+ "8LoBJ9q5AY0EYkLQlgEMAM8oo1NwX0bpF6lVh/ZwSt9rsUzUmP8OX+wPzRQrzTUr\n"
			+ "ej1QmQ4GP4Kx24Y49iX91Z2q5tfZWXYcSaSj/LAeh90QH37SURfOpB+CmzrfyL7a\n"
			+ "8jT6q8ZntVrLGaFVPn9R8mdzdzxuL5uleoKxkAot+zJn/lXerBt7ILiBBSXXLey+\n"
			+ "ncPf2hsoBfDpJRiVIkC0I6uW8fjwYBxg5aBpmJh0yxIiMf1W2pT47kwiv4hY/p9f\n"
			+ "h5jv2Eexs3aolwoYga9jyCx87PGtdlCf6q0klzvYqI1uCf0kghBwAmR9hLbd8pFl\n"
			+ "RI0/l147iiX3SL0euP7pjTJ+YfJcBxcDSOs9d2HsJaJxzhzwIv0FIVwNv5oKj2ua\n"
			+ "XM0D5I+H7MzT2VsA26nRuOvUCtxh2IKP39lCp2222M65qgr8N5Hy221+q5lcovVE\n"
			+ "2dZXL2gjKdbYrGJdtKwLBnMU+jwm7MxxPLz1CFiS2Lid4/RRGhecSe1KCoir0fJs\n"
			+ "q1xAoViJbMPOMZuac7dSTQARAQABiQI8BBgBCAAmFiEE4DTLV4M19or8LNLY0Qf/\n"
			+ "u+MxZ+0FAmJC0JYCGwwFCRaSEioACgkQ0Qf/u+MxZ+2dPBAApomyExLgaNrlK8NW\n"
			+ "5GWnMbATKi0I3cJlQuV/iXGfRVfOJLIgIjgyYaBH/IjWejLfv3+eY0zRo4pRAgsg\n"
			+ "oZqoGARTn67jhvVOy7Az1Jzt5Nd49FafI3kHPEOmzeent85kaqflfKAIjV2u8tdM\n"
			+ "UIw9ev4HX59CtJeRZU5neCem7pLfcZa6AYC5QG9PMziI183w4K2YrLS2DF4s80s9\n"
			+ "ymkjn+0s4rWqjBByPMI0od1py5cTlhSxAtW1MV7QNDUAGOdWv/76nTl63VWcdpXk\n"
			+ "a9vkLw4ECrCFNNq82odBNdchf5+iq+AhWlk9t453BpV+OiZtzHApIS0u/t9JKaW4\n"
			+ "yLFP7I+gvpJQmSuIVW0+6GEo2ECC0MogW+gAr39ck23sLoEy2TtiAeYjaCVQv8AB\n"
			+ "79lO2+LqIYLy7HQm3d3I3mg91nfAlmporx0mr7gcUzikBgEH2Z7rewH7NHGJXr9H\n"
			+ "EVzyj0h87nM2pJ9B4HTM5KppC4KuzpaU7yypPDFH1HvHEJRvPierVzymjaraoYiF\n"
			+ "xMPvjwumU59xqNZCNUtDXxUYyJ4ABfV66zWqnqdL8WitVEbW3uO3lGmAsAaQTk9k\n"
			+ "kP1RkqOx1iLBcVwqHgHo1W3NoLclm5IF2rOudYTgpb2e9nxOfQ/DSYuHo5B9fk1M\n"
			+ "ehNDyVKrVSv4S/iYI0auCqVblYo=\n"
			+ "=/4Nn\n"
			+ "-----END PGP PUBLIC KEY BLOCK-----\n";

	// 银行 私钥,存放在银行,用于签名
	static final String PRIVATE_KEY_STREAM = "-----BEGIN PGP PRIVATE KEY BLOCK-----\n"
			+ "\n"
			+ "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\n"
			+ "o5B9fk1MehNDyVKrVSv4S/iYI0auCqVblYo=\n"
			+ "=Hows\n"
			+ "-----END PGP PRIVATE KEY BLOCK-----\n";
	// 私钥密码
	static final String PASSWORD = "123456";
//	static final String PUBLIC_KEY_PATH = "E:\\PGP\\pub.asc";
//	static final String PRIVATE_KEY_PATH = "E:\\PGP\\prv.asc";

	// 源文本
	static final String SOURCE_PATH = "/Users/cb/Desktop/log/TOPPAY_camt052.xml";
	// 加密后的文本
	static final String ENCRYPTED_PATH = "/Users/cb/Desktop/log/encryted52.xml";
	// 解密后的文本
	static final String DECRYPTED_PATH = "/Users/cb/Desktop/log/dencryted52.xml";

	public static void main(String[] args) throws Exception {
		// 加密 HashAlgorithmTags.MD5
		PgpHelper.doEncrypt(PUBLIC_KEY_STREAM, PASSWORD, PRIVATE_KEY_STREAM, new File(SOURCE_PATH),
				new File(ENCRYPTED_PATH), "MD5");

		// 解密
		FileInputStream pgpIn = new FileInputStream(ENCRYPTED_PATH);
		FileOutputStream plainOut = new FileOutputStream(DECRYPTED_PATH);
		PgpHelper.doDecrypt(PUBLIC_KEY_STREAM, PASSWORD, PRIVATE_KEY_STREAM, pgpIn, plainOut);
	}
}
