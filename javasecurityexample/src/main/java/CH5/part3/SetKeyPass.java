package CH5.part3;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

public class SetKeyPass {
    public static void main(String args[]) throws Exception {
        String name = "mykeystore";
        String alias = args[0];
        char[] storepass = "wshr.ut".toCharArray();
        char[] oldkeypass = args[1].toCharArray();
        char[] newkeypass = args[2].toCharArray();

        FileInputStream in = new FileInputStream(name);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(in, storepass);
        Certificate[] cchain = ks.getCertificateChain(alias);
        PrivateKey pk = (PrivateKey) ks.getKey(alias, oldkeypass);
        ks.setKeyEntry(alias, pk, newkeypass, cchain);
        in.close();
        FileOutputStream output = new FileOutputStream("333");
        ks.store(output, storepass);
        output.close();

    }


}  



