package CH2.part7;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class Skey_RSA {
    public static void main(String args[]) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        PublicKey pbkey = kp.getPublic();
        PrivateKey prkey = kp.getPrivate();

        FileOutputStream f1 = new FileOutputStream("Skey_RSA_pub.dat");
        ObjectOutputStream b1 = new ObjectOutputStream(f1);
        b1.writeObject(pbkey);

        FileOutputStream f2 = new FileOutputStream("Skey_RSA_priv.dat");
        ObjectOutputStream b2 = new ObjectOutputStream(f2);
        b2.writeObject(prkey);


    }
}