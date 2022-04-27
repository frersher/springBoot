package com.shine.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;

/**
 * pgp 加解密
 */
public class PgpUtils {


    // TODO 这个方法OK
    public static void main(String[] args) throws Exception {
        PgpUtils pgpUtils = PgpUtils.getInstance();
        // gpg签名加密：入参第一个为加密的文件 第二个为密钥
        String result = pgpUtils.decryptFile(new FileInputStream(new File("/Users/cb/Desktop/log/TOPPAY_camt052_202204250800.xml")),
            new FileInputStream(new File("/Users/cb/Downloads/test/topPaySecret.asc")));

        System.out.println(result);

        // Map<String, Object> map = XmlMapUtils.xmlStr2map(result, false);
        // //        System.out.println(map.size());
        //
        // //        for (Map.Entry<String, Object> e : map.entrySet()) {
        // //            System.out.println(e.getKey());
        // //            System.out.println(e.getValue().getClass());
        // //        }
        //
        // List<Map<String, String>> list = (List<Map<String, String>>) map.get("Row");
        // for (Map m : list) {
        //     System.out.println(m);
        // }
    }

    private static PgpUtils INSTANCE = null;

    public static PgpUtils getInstance() {

        if (INSTANCE == null) INSTANCE = new PgpUtils();
        return INSTANCE;
    }

    private PgpUtils() {
    }


    public PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in);

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //
        PGPPublicKey key = null;

        //
        // iterate through the key rings.
        //
        Iterator<PGPPublicKeyRing> rIt = pgpPub.getKeyRings();

        while (key == null && rIt.hasNext()) {
            PGPPublicKeyRing kRing = rIt.next();
            Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
            while (key == null && kIt.hasNext()) {
                PGPPublicKey k = kIt.next();

                if (k.isEncryptionKey()) {
                    key = k;
                }
            }
        }

        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }

        return key;
    }

    /**
     * Load a secret key ring collection from keyIn and find the secret key corresponding to
     * keyID if it exists.
     *
     * @param keyIn input stream representing a key ring collection.
     * @param keyID keyID we want.
     * @param pass  passphrase to decrypt secret key with.
     * @return
     * @throws IOException
     * @throws PGPException
     * @throws NoSuchProviderException
     */
    private static PGPPrivateKey findSecretKey(InputStream keyIn, long keyID, char[] pass) throws IOException, PGPException, NoSuchProviderException {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));

        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);

        if (pgpSecKey == null) {
            return null;
        }

        PBESecretKeyDecryptor a = new JcePBESecretKeyDecryptorBuilder(new JcaPGPDigestCalculatorProviderBuilder().setProvider("BC").build()).setProvider("BC").build(pass);

        return pgpSecKey.extractPrivateKey(a);
    }

    /**
     * decrypt the passed in message stream
     */
    public static String decryptFile(InputStream in, InputStream keyIn) throws Exception {
        return decryptFile(in, keyIn, "123456".toCharArray());
    }

    public  static String decryptFile(InputStream in, InputStream keyIn, char[] passwd) throws Exception {
        String result = null;
        Security.addProvider(new BouncyCastleProvider());
        in = PGPUtil.getDecoderStream(in);
        PGPObjectFactory pgpF = new PGPObjectFactory(in);
        PGPEncryptedDataList enc;
        Object o = pgpF.nextObject();
        //
        // the first object might be a PGP marker packet.
        //
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }

        //
        // find the secret key
        //
        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;

        while (sKey == null && it.hasNext()) {
            pbe = it.next();
            sKey = findSecretKey(keyIn, pbe.getKeyID(), passwd);
        }

        if (sKey == null) {
            throw new IllegalArgumentException("Secret key for message not found.");
        }

        PublicKeyDataDecryptorFactory b = new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").setContentProvider("BC").build(sKey);

        InputStream clear = pbe.getDataStream(b);

        PGPObjectFactory plainFact = new PGPObjectFactory(clear);

        Object message = plainFact.nextObject();

        // if (message instanceof PGPCompressedData) {
        PGPCompressedData cData = (PGPCompressedData) message;
        PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
        message = pgpFact.nextObject();
        // }

        // 这里是没带签名的逻辑
        if (message instanceof PGPLiteralData) {
            PGPLiteralData ld = (PGPLiteralData) message;
            InputStream unc = ld.getInputStream();

            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(unc));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } else if (message instanceof PGPOnePassSignatureList) { //签名逻辑再解密
            // check signature and extract message
            PGPOnePassSignatureList p1 = (PGPOnePassSignatureList) message;
            PGPOnePassSignature ops = p1.get(0);
            PGPLiteralData p2 = (PGPLiteralData) pgpFact.nextObject();
            InputStream dIn = p2.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(dIn));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } else {

            throw new PGPException("Message is not a simple encrypted file - type unknown.");
        }

        if (pbe.isIntegrityProtected()) {
            if (!pbe.verify()) {
                throw new PGPException("Message failed integrity check");
            }
        }
        return result;
    }


    public void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey, boolean armor,
                            boolean withIntegrityCheck) throws IOException, NoSuchProviderException, PGPException {
        Security.addProvider(new BouncyCastleProvider());

        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);

        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));

        comData.close();

        JcePGPDataEncryptorBuilder c = new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC");

        PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(c);

        JcePublicKeyKeyEncryptionMethodGenerator d = new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider(new BouncyCastleProvider()).setSecureRandom(new SecureRandom());

        cPk.addMethod(d);

        byte[] bytes = bOut.toByteArray();

        OutputStream cOut = cPk.open(out, bytes.length);

        cOut.write(bytes);

        cOut.close();

        out.close();
    }


    private byte[] inputStreamToByteArray(InputStream is) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[1024];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }


    /**
     * verify the signature in in against the file fileName.
     */
    private boolean verifySignature(String fileName, byte[] b, InputStream keyIn) throws
            GeneralSecurityException, IOException, PGPException {
        //in = PGPUtil.getDecoderStream(in);

        PGPObjectFactory pgpFact = new PGPObjectFactory(b);
        PGPSignatureList p3 = null;

        Object o = pgpFact.nextObject();
        if (o instanceof PGPCompressedData) {
            PGPCompressedData c1 = (PGPCompressedData) o;

            pgpFact = new PGPObjectFactory(c1.getDataStream());

            p3 = (PGPSignatureList) pgpFact.nextObject();
        } else {
            p3 = (PGPSignatureList) o;
        }

        PGPPublicKeyRingCollection pgpPubRingCollection = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(keyIn));


        InputStream dIn = new BufferedInputStream(new FileInputStream(fileName));

        PGPSignature sig = p3.get(0);
        PGPPublicKey key = pgpPubRingCollection.getPublicKey(sig.getKeyID());


        sig.init(new JcaPGPContentVerifierBuilderProvider().setProvider(new BouncyCastleProvider()), key);

        int ch;
        while ((ch = dIn.read()) >= 0) {
            sig.update((byte) ch);
        }

        dIn.close();

        if (sig.verify()) {
            return true;
        } else {
            return false;
        }
    }

    private PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(input));

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //

        Iterator keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing) keyRingIter.next();

            Iterator keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext()) {
                PGPSecretKey key = (PGPSecretKey) keyIter.next();

                if (key.isSigningKey()) {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find signing key in key ring.");
    }

    private byte[] createSignature(String fileName, InputStream keyIn, char[] pass, boolean armor) throws
            GeneralSecurityException, IOException, PGPException {


        PGPSecretKey pgpSecKey = readSecretKey(keyIn);
        PGPPrivateKey pgpPrivKey = pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider(new BouncyCastleProvider()).build(pass));
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pgpSecKey.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1).setProvider(new BouncyCastleProvider()));


        sGen.init(PGPSignature.BINARY_DOCUMENT, pgpPrivKey);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ArmoredOutputStream aOut = new ArmoredOutputStream(byteOut);


        BCPGOutputStream bOut = new BCPGOutputStream(byteOut);

        InputStream fIn = new BufferedInputStream(new FileInputStream(fileName));

        int ch;
        while ((ch = fIn.read()) >= 0) {
            sGen.update((byte) ch);

        }

        aOut.endClearText();

        fIn.close();

        sGen.generate().encode(bOut);

        if (armor) {
            aOut.close();
        }

        return byteOut.toByteArray();
    }


    /**
     * 生成签名文件
     *
     * @param filePath       签名文件路径
     * @param privateKeyPath 私钥路径
     * @param outFilePath    输出证书路径
     *                       证书名称必须与签名文件名称一样 多后缀: .asc
     *                       比如: 签名文件为:di.ova   那么生成的证书必须为: di.ova.asc
     * @param passWord       证书密码
     * @return 证书字节数组
     */
    public static byte[] signatureCreate(String filePath, String privateKeyPath, String outFilePath, String
            passWord) {

        try {
            FileInputStream privKeyIn = new FileInputStream(privateKeyPath);
            FileOutputStream signatureOut = new FileOutputStream(outFilePath);
            byte[] sig = PgpUtils.getInstance().createSignature(filePath, privKeyIn, passWord.toCharArray(), true);
            signatureOut.write(sig);
            signatureOut.flush();
            signatureOut.close();
            privKeyIn.close();
            return sig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签名验证
     *
     * @param filePath      被签名的文件路径
     * @param publicKeyPath 公钥路径
     * @param signFilePath  签名文件路径
     * @return 是否通过
     */
    public static boolean verifySignature(String filePath, String publicKeyPath, String signFilePath) {
        try {
            FileInputStream pubKeyIs = new FileInputStream(publicKeyPath);
            FileInputStream signFile = new FileInputStream(signFilePath);
            byte[] signFileBytes = new byte[signFile.available()];
            signFile.read(signFileBytes);
            final boolean verifyResult = PgpUtils.getInstance().verifySignature(filePath, signFileBytes, pubKeyIs);
            signFile.close();
            pubKeyIs.close();
            return verifyResult;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
