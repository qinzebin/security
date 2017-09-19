package CH4.part1;

import java.security.*;
import java.io.*;

public class DigestInput {
    public static void main(String args[]) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        FileInputStream fin = new FileInputStream(args[0]);
//	BufferedInputStream bin=new BufferedInputStream(fin);
        DigestInputStream din = new DigestInputStream(fin, m);
        while (din.read() != -1) ;

        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }
        System.out.println(result);//ed076287532e86365e841e92bfc50d8c
    }
}

