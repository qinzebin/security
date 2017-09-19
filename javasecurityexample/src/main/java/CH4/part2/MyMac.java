package CH4.part2;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class MyMac {
    public static void main(String args[]) throws Exception {
        //获取密钥
        /*      FileInputStream f=new FileInputStream("key1.dat");
              ObjectInputStream b=new ObjectInputStream(f);
              Key k=(Key)b.readObject( );
              byte [] kb={11,-105,-119,50,4,-105,16,38,-14,-111,21,-95,70,-15,76,-74,67,-88,59,-71,55,-125,104,42};
              SecretKeySpec k=new SecretKeySpec(kb,"HMACSHA1");

                KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
                SecretKey k = kg.generateKey();

        */

        byte[] kb = {11, -105, -119, 50, 4, -105, 16, 38, -14, -111, 21, -95, 70, -15, 76, -74, 67, -88, 59, -71, 55, -125, 104, 42};
        SecretKeySpec k = new SecretKeySpec(kb, "HMACSHA1");


        //获取Mac对象
        Mac m = Mac.getInstance("HmacMD5");
        m.init(k);
        String x = args[0];
        m.update(x.getBytes("UTF8"));
        byte s[] = m.doFinal();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        System.out.println(result);
    }
}
