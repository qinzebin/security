package CH5.part3;

import java.io.*;
import java.security.*;

public class SetStorePass {
    public static void main(String args[]) throws Exception {
        char[] oldpass = args[0].toCharArray();
        char[] newpass = args[1].toCharArray();
        String name = "mykeystore";
        FileInputStream in = new FileInputStream(name);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(in, oldpass);
        in.close();
        FileOutputStream output = new FileOutputStream(name);
        ks.store(output, newpass);
        output.close();
    }
}  



