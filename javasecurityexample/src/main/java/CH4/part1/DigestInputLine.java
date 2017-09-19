package CH4.part1;

import java.security.*;
import java.io.*;

public class DigestInputLine {
    public static void main(String args[]) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        FileInputStream fin = new FileInputStream(args[0]);
        DigestInputStream din = new DigestInputStream(fin, m);
        din.on(false);
        int b;
        while ((b = din.read()) != -1) {
            if (b == '$') {
                din.on(true);
            }
        }
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }
        System.out.println(result);


    }
}

