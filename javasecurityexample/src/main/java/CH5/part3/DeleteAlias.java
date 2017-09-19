package CH5.part3;

import java.io.*;
import java.security.*;

public class DeleteAlias {
    public static void main(String args[]) throws Exception {
        String pass = "wshr.ut";
        String name = "mykeystore";
        FileInputStream in = new FileInputStream(name);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(in, pass.toCharArray());
        if (ks.containsAlias(args[0])) {
            ks.deleteEntry(args[0]);
            FileOutputStream output = new FileOutputStream(name);
            ks.store(output, pass.toCharArray());
            System.out.println("Alias " + args[0] + " deleted");

        } else {
            System.out.println("Alias not exist");
        }
    }
}  
