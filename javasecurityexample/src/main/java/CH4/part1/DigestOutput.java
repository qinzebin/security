package CH4.part1;

import java.security.*;
import java.io.*;

public class DigestOutput {
    public static void main(String args[]) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        FileOutputStream fout = new FileOutputStream(args[0]);
        DigestOutputStream dout = new DigestOutputStream(fout, m);
        int b;
        while ((b = System.in.read()) != -1) {
            dout.write(b);
        }
        dout.close();

        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }
        System.out.println(result);
    }
}

