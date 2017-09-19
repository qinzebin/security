package CH2.part5;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class StreamIn {
    public static void main(String args[]) throws Exception {

        FileInputStream f = new FileInputStream("key1.dat");
        ObjectInputStream ob = new ObjectInputStream(f);
        Key k = (Key) ob.readObject();

        Cipher cp = Cipher.getInstance("DESede");
        //cp.init(Cipher.DECRYPT_MODE, k);
        cp.init(Cipher.ENCRYPT_MODE, k);

        FileInputStream in = new FileInputStream(args[0]);
        CipherInputStream cin = new CipherInputStream(in, cp);

        int b = 0;
        while ((b = cin.read()) != -1) {
            System.out.print((byte) b + ",");
//              System.out.print((char)b);
        }


    }
}



