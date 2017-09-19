package CH4.part4;

import java.io.*;
import java.security.*;

public class SetPass {
    public static void main(String args[]) throws Exception {
        String name = args[0];
        String passwd = args[1];

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(passwd.getBytes("UTF8"));
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }

        PrintWriter out = new PrintWriter(
                new FileOutputStream("passwd.txt"));
        out.println(name);
        out.println(result);
        out.close();
    }
}
