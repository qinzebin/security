package CH2.part8;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;
import java.security.interfaces.*;
import java.math.*;
import java.io.*;

public class Dec_RSA {
    public static void main(String args[]) throws Exception {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(new FileInputStream("Enc_RSA.dat")));
        String ctext = in.readLine();
        BigInteger c = new BigInteger(ctext);

        FileInputStream f = new FileInputStream("Skey_RSA_priv.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        BigInteger n = prk.getModulus();
        System.out.println("d= " + d);
        System.out.println("n= " + n);

        BigInteger m = c.modPow(d, n);
        System.out.println("m= " + m);
        byte[] mt = m.toByteArray();
        System.out.println("PlainText is ");
        for (int i = 0; i < mt.length; i++) {
            System.out.print((char) mt[i]);
        }


    }
}