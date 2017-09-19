package CH5.part2;

import java.io.*;
import java.security.*;

import java.security.cert.*;
import java.util.*;
import java.math.*;

// import sun.security.x509.*;
public class ShowCertInfo {

    public static void main(String args[]) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream("qinzebin2.cer");
        java.security.cert.Certificate c = cf.generateCertificate(in);
        in.close();

        X509Certificate t = (X509Certificate) c;
        System.out.println("版本号 " + t.getVersion());
        System.out.println("序列号 " + t.getSerialNumber().toString(16));
        System.out.println("全名 " + t.getSubjectDN());
        System.out.println("签发者全名\n" + t.getIssuerDN());
        System.out.println("有效期起始日 " + t.getNotBefore());
        System.out.println("有效期截至日 " + t.getNotAfter());
        System.out.println("签名算法 " + t.getSigAlgName());
        byte[] sig = t.getSignature();
        System.out.println("签名\n" + new BigInteger(sig).toString(16));
        PublicKey pk = t.getPublicKey();
        byte[] pkenc = pk.getEncoded();
        System.out.println("公钥");
        for (int i = 0; i < pkenc.length; i++) {
            System.out.print(pkenc[i] + ",");
        }
    }
}





