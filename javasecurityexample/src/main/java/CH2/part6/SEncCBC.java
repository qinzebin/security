package CH2.part6;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class SEncCBC {
    public static void main(String args[]) throws Exception {
        String s = "Hello123Hello123Hello123Hello123";

        FileInputStream f1 = new FileInputStream("key1.dat");
        ObjectInputStream b = new ObjectInputStream(f1);
        Key k = (Key) b.readObject();

        byte[] rand = new byte[8];
        Random r = new Random();
        r.nextBytes(rand);
        IvParameterSpec iv = new IvParameterSpec(rand);

        Cipher cp = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cp.init(Cipher.ENCRYPT_MODE, k, iv);
        byte ptext[] = s.getBytes("UTF8");

        byte ctext[] = cp.doFinal(ptext);
        for (int i = 0; i < ctext.length; i++) {
            System.out.print(ctext[i] + ",");
        }
        FileOutputStream f2 = new FileOutputStream("SEncCBC.dat");
        f2.write(rand);
        f2.write(ctext);
    }
}
