package CH2.part5;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class StreamOut {
    public static void main(String args[]) throws Exception {

        FileInputStream f = new FileInputStream("key1.dat");
        ObjectInputStream ob = new ObjectInputStream(f);
        Key k = (Key) ob.readObject();
        Cipher cp = Cipher.getInstance("DESede");
        if (args[0].equals("dec"))
            cp.init(Cipher.DECRYPT_MODE, k);
        else
            cp.init(Cipher.ENCRYPT_MODE, k);
        FileInputStream in = new FileInputStream(args[1]);
        FileOutputStream out = new FileOutputStream(args[2]);
        CipherOutputStream cout = new CipherOutputStream(out, cp);
        int b = 0;
        while ((b = in.read()) != -1) {
            cout.write(b);
        }

        cout.close();
        out.close();
        in.close();
    }
}
