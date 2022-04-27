package com.shine.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Date;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import sun.misc.BASE64Encoder;

public class PgpUtil {
    

	
	/**
	 * 私有方法，用于生成指定位宽的PGP RSA密钥对
	 *
	 * @param rsaWidth_ RSA密钥位宽
	 * @return 未经私钥加密的PGP密钥对
	 * @throws Exception IO错误，数值错误等
	 */
	private static PGPKeyPair generateKeyPair(int rsaWidth_) throws Exception {
    
	    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");//获取密钥对生成器实例
	    kpg.initialize(rsaWidth_);//设定RSA位宽
	    KeyPair kp = kpg.generateKeyPair();//生成RSA密钥对
	    return new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, kp, new Date());//返回根据日期，密钥对生成的PGP密钥对
	}
	 
	/**
	 * 获取PGP密钥<br>
	 * 密钥是将密钥对的私钥部分用对称的加密方法CAST-128算法加密，再加上公钥部分
	 *
	 * @param identity_   密钥ID也就是key值，可以用来标记密钥属于谁
	 * @param passPhrase_ 密钥的密码，用来解出私钥
	 * @param rsaWidth_   RSA位宽
	 * @return PGP密钥
	 * @throws Exception IO错误和数值错误等
	 */
	public static PGPSecretKey getSecretKey(String identity_, String passPhrase_, int rsaWidth_) throws Exception {
    
	    char[] passPhrase = passPhrase_.toCharArray(); //将passPharse转换成字符数组
	    PGPKeyPair keyPair = PgpUtil.generateKeyPair(rsaWidth_); //生成RSA密钥对
	    PGPDigestCalculator sha1Calc = new JcaPGPDigestCalculatorProviderBuilder().build().get(
					HashAlgorithmTags.SHA1); //使用SHA1作为证书的散列算法
	    /**
	     * 用证书等级生成的认证，将公私钥对和PGP ID密码绑定构造PGP密钥（SecretKey）
	     *
	     * @param certificationLevel PGP密钥的证书等级
	     * @param keyPair 需要绑定的公私钥对
	     * @param id 需要绑定的ID
	     * @param checksumCalculator 散列值计算器，用于计算私钥密码散列
	     * @param hashedPcks the hashed packets to be added to the certification.（先不管）
	     * @param unhashedPcks the unhashed packets to be added to the certification.（也先不管）
	     * @param certificationSignerBuilder PGP证书的生成器
	     * @param keyEncryptor 如果需要加密私钥，需要在这里传入私钥加密器
	     * @throws PGPException 一些PGP错误
	     */
	    return new PGPSecretKey(
	            PGPSignature.DEFAULT_CERTIFICATION,
	            keyPair,
	            identity_,
	            sha1Calc,
	            null,
	            null,
	            new JcaPGPContentSignerBuilder(keyPair.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1),
	            //密钥的加密方式
	            new JcePBESecretKeyEncryptorBuilder(PGPEncryptedData.CAST5, sha1Calc).setProvider("BC").build(passPhrase)
	    );
	}
	
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
    
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String passPhrase_ = "123456789";
	    char[] passPhrase = passPhrase_.toCharArray(); //将passPharse转换成字符数组

		PGPSecretKey secretKey = PgpUtil.getSecretKey("wathdata", passPhrase_, 2048);

		// 这里打印私钥-------------重要
		String privateKeyString = new BASE64Encoder().encode(secretKey.getEncoded());
		System.out.println(privateKeyString);
	
	
		PGPPublicKey publicKey = secretKey.getPublicKey();
		//FileOutputStream fileOutputStream = new FileOutputStream("c://1.txt");
		byte[] encoded = publicKey.getEncoded();
		// 这里打印公钥----------------重要
		String publicKeyString = new BASE64Encoder().encode(encoded);  
		System.out.println(publicKeyString);

	}
	

}