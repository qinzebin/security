package CH5.part2;

import java.io.*;
import java.security.*;
/* Certificate
PrintCert2.java:15: reference to Certificate is ambiguous, both class java.security.cert.Certificate in java.security.cert and class java.security.Certificate in java.security match
So import exactly
*/
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class PrintCert2 {

    public static void main(String args[]) throws Exception {
        String pass = "123456";
        String alias = "qinzebin2";
        String name = "mykeystore";

        FileInputStream in = new FileInputStream(name);
        //注意,K, S均大写
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(in, pass.toCharArray());

        Certificate c = ks.getCertificate(alias);
        in.close();
        System.out.println(c);

    }

}


/*        若使用默认的keystore则可如下获取路径名称
    String userhome=System.getProperty("user.home");
          String name=userhome+File.separator+".keystore";
*/

