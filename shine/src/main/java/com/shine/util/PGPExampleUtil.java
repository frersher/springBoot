//package com.shine.util;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.NoSuchProviderException;
//import java.util.Iterator;
//import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
//import org.bouncycastle.openpgp.PGPException;
//import org.bouncycastle.openpgp.PGPPrivateKey;
//import org.bouncycastle.openpgp.PGPPublicKey;
//import org.bouncycastle.openpgp.PGPPublicKeyRing;
//import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
//import org.bouncycastle.openpgp.PGPSecretKey;
//import org.bouncycastle.openpgp.PGPSecretKeyRing;
//import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
//import org.bouncycastle.openpgp.PGPUtil;
//import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
//import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
//
//class PGPExampleUtil {
//  PGPExampleUtil() {
//  }
//
//  static byte[] compressFile(String var0, int var1) throws IOException {
//    ByteArrayOutputStream var2 = new ByteArrayOutputStream();
//    PGPCompressedDataGenerator var3 = new PGPCompressedDataGenerator(var1);
//    PGPUtil.writeFileToLiteralData(var3.open(var2), 'b', new File(var0));
//    var3.close();
//    return var2.toByteArray();
//  }
//
//  static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection var0, long var1, char[] var3) throws PGPException, NoSuchProviderException {
//    PGPSecretKey var4 = var0.getSecretKey(var1);
//    return var4 == null ? null : var4.extractPrivateKey((new JcePBESecretKeyDecryptorBuilder()).setProvider("BC").build(var3));
//  }
//
//  static PGPPublicKey readPublicKey(String var0) throws IOException, PGPException {
//    BufferedInputStream var1 = new BufferedInputStream(new FileInputStream(var0));
//    BufferedInputStream bufferedInputStream = new BufferedInputStream()
//    PGPPublicKey var2 = readPublicKey((InputStream)var1);
//    var1.close();
//    return var2;
//  }
//
//  static PGPPublicKey readPublicKey(InputStream var0) throws IOException, PGPException {
//    PGPPublicKeyRingCollection var1 = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(var0), new JcaKeyFingerprintCalculator());
//    Iterator var2 = var1.getKeyRings();
//
//    while(var2.hasNext()) {
//      PGPPublicKeyRing var3 = (PGPPublicKeyRing)var2.next();
//      Iterator var4 = var3.getPublicKeys();
//
//      while(var4.hasNext()) {
//        PGPPublicKey var5 = (PGPPublicKey)var4.next();
//        if (var5.isEncryptionKey()) {
//          return var5;
//        }
//      }
//    }
//
//    throw new IllegalArgumentException("Can't find encryption key in key ring.");
//  }
//
//  static PGPSecretKey readSecretKey(String var0) throws IOException, PGPException {
//    BufferedInputStream var1 = new BufferedInputStream(new FileInputStream(var0));
//    PGPSecretKey var2 = readSecretKey((InputStream)var1);
//    var1.close();
//    return var2;
//  }
//
//  static PGPSecretKey readSecretKey(InputStream var0) throws IOException, PGPException {
//    PGPSecretKeyRingCollection var1 = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(var0), new JcaKeyFingerprintCalculator());
//    Iterator var2 = var1.getKeyRings();
//
//    while(var2.hasNext()) {
//      PGPSecretKeyRing var3 = (PGPSecretKeyRing)var2.next();
//      Iterator var4 = var3.getSecretKeys();
//
//      while(var4.hasNext()) {
//        PGPSecretKey var5 = (PGPSecretKey)var4.next();
//        if (var5.isSigningKey()) {
//          return var5;
//        }
//      }
//    }
//
//    throw new IllegalArgumentException("Can't find signing key in key ring.");
//  }
//}
