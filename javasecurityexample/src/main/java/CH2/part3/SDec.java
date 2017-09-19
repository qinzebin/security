package CH2.part3;

import java.io.*;
import java.security.Key;
import javax.crypto.*;
import javax.crypto.spec.*;


public class SDec {
    public static void main(String args[]) throws Exception {

/*
byte [ ] keykb ={11,-105,-119,50,4,-105,16,38,-14,-111,21,-95,
70,-15,76,-74,67,-88,59,-71,55,-125,104,42};
byte ctext[ ]={-57,119,0,-45,-9,23,37,-56,-60,-34,-99,105,99,113,-17,76};
*/

        FileInputStream f = new FileInputStream("SEnc.dat");
        int num = f.available();
        byte[] ctext = new byte[num];
        f.read(ctext);

        //方式1：字节方式
        /*FileInputStream f2 = new FileInputStream("keykb1.dat");
        int num2 = f2.available();
        byte[] keykb = new byte[num2];
        f2.read(keykb);
        SecretKeySpec k = new SecretKeySpec(keykb, "DESede");*/

        //方式2：对象方式
        FileInputStream f2 = new FileInputStream("key1.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(f2);
        Key k = (Key)objectInputStream.readObject();

        Cipher cp = Cipher.getInstance("DESede");
        cp.init(Cipher.DECRYPT_MODE, k);
        byte[] ptext = cp.doFinal(ctext);
        String p = new String(ptext, "UTF8");
        System.out.println(p);


    }
}