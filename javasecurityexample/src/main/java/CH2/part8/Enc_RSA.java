package CH2.part8;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;
import java.security.interfaces.*;
import java.math.*;
import java.io.*;

public class Enc_RSA {
    public static void main(String args[]) throws Exception {
        String s = "Hello World!";
        FileInputStream f = new FileInputStream("Skey_RSA_pub.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        System.out.println("e= " + e);
        System.out.println("n= " + n);

        byte ptext[] = s.getBytes("UTF8");
        BigInteger m = new BigInteger(ptext);
        BigInteger c = m.modPow(e, n);
        System.out.println("c= " + c);

        String cs = c.toString();
        BufferedWriter out =
                new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("Enc_RSA.dat")));
        out.write(cs, 0, cs.length());
        out.close();

    }
}