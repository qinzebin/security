package CH4.part4;

import java.io.*;
import java.security.*;

public class AttackPass {
    public static void main(String args[]) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        PrintWriter out = new PrintWriter(
                new FileOutputStream("dict.txt"));

        for (int i1 = 'a'; i1 < 'z'; i1++) {
            System.out.println("Now Processing" + (char) i1);
            for (int i2 = 'a'; i2 < 'z'; i2++)
                for (int i3 = 'a'; i3 < 'z'; i3++)
                    for (int i4 = 'a'; i4 < 'z'; i4++) {
                        char[] ch = {(char) i1, (char) i2, (char) i3, (char) i4};
                        String passwd = new String(ch);
                        m.update(passwd.getBytes("UTF8"));
                        byte s[] = m.digest();
                        String result = "";
                        for (int i = 0; i < s.length; i++) {
                            result += Integer.toHexString((0x000000ff & s[i]) |
                                    0xffffff00).substring(6);
                        }
                        out.print(passwd + "    ");
                        out.println(result);
                    }
        }
        out.close();
    }
}
