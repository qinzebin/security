package CH2.part6;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class SDecCBC {
    public static void main(String args[]) throws Exception {


        FileInputStream f = new FileInputStream("SEncCBC.dat");
        byte[] rand = new byte[8];
        f.read(rand);
        IvParameterSpec iv = new IvParameterSpec(rand);

        int num = f.available();
        byte[] ctext = new byte[num];
        f.read(ctext);

        FileInputStream f2 = new FileInputStream("key1.dat");
        ObjectInputStream b = new ObjectInputStream(f2);
        Key k = (Key) b.readObject();

        Cipher cp = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cp.init(Cipher.DECRYPT_MODE, k, iv);
        byte[] ptext = cp.doFinal(ctext);
        String p = new String(ptext, "UTF8");
        System.out.println(p);
    }
}