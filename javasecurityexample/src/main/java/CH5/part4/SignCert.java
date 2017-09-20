package CH5.part4;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;

import sun.security.x509.*;

public class SignCert {
    public static void main(String args[]) throws Exception {
        char[] storepass = "123456".toCharArray();
        char[] cakeypass = "123456".toCharArray();
        String alias = "qinzebin1";//CA证书
        String name = "mykeystore";
        // Cert of CA-----c1
        FileInputStream in = new FileInputStream(name);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(in, storepass);

        //从密钥库读取CA的私钥
        java.security.cert.Certificate c1 = ks.getCertificate(alias);
        PrivateKey caprk = (PrivateKey) ks.getKey(alias, cakeypass);
        in.close();

        //得到签发者
        byte[] encod1 = c1.getEncoded();
        X509CertImpl cimp1 = new X509CertImpl(encod1);
        X509CertInfo cinfo1 = (X509CertInfo) cimp1.get(X509CertImpl.NAME +
                "." + X509CertImpl.INFO);
        X500Name issuer = (X500Name) cinfo1.get(X509CertInfo.SUBJECT +
                "." + CertificateIssuerName.DN_NAME);

        // Cert of lf-----c2
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        //FileInputStream in2 = new FileInputStream(args[0]);
        FileInputStream in2 = new FileInputStream("qinzebin2.cer");
        java.security.cert.Certificate c2 = cf.generateCertificate(in2);
        in2.close();

        byte[] encod2 = c2.getEncoded();
        X509CertImpl cimp2 = new X509CertImpl(encod2);
        X509CertInfo cinfo2 = (X509CertInfo) cimp2.get(
                X509CertImpl.NAME + "." + X509CertImpl.INFO);
        //设置新证书有效期
        Date begindate = new Date();
        //60 day
        Date enddate = new Date(begindate.getTime() + 3000 * 24 * 60 * 60 * 1000L);
        CertificateValidity cv = new CertificateValidity(begindate, enddate);
        cinfo2.set(X509CertInfo.VALIDITY, cv);
        //设置新证书序列号
        int sn = (int) (begindate.getTime() / 1000);
        CertificateSerialNumber csn = new CertificateSerialNumber(sn);
        cinfo2.set(X509CertInfo.SERIAL_NUMBER, csn);
        //设置新证书签发者
        cinfo2.set(X509CertInfo.ISSUER + "." +
                CertificateIssuerName.DN_NAME, issuer);
        //设置新证书算法
        AlgorithmId algorithm =
                new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
        cinfo2.set(CertificateAlgorithmId.NAME +
                "." + CertificateAlgorithmId.ALGORITHM, algorithm);
        // 创建证书
        X509CertImpl newcert = new X509CertImpl(cinfo2);
        // 签名
        newcert.sign(caprk, "MD5WithRSA");
        System.out.println(newcert);

        // 存入密钥库
        ks.setCertificateEntry("qinzebin3_signed", newcert);//作为一个新证书存在
        /*
            PrivateKey prk=(PrivateKey)ks.getKey("lf",
        "wshr.ut".toCharArray( ));
                     java.security.cert.Certificate[] cchain={newcert};
        ks.setKeyEntry("lf_signed",prk,
        "newpass".toCharArray(),cchain);
            */
        FileOutputStream out = new FileOutputStream("newstore");
        ks.store(out, "123456".toCharArray());
        out.close();
    }
}

