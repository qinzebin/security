package CH2.part2;

import java.io.*;
import java.security.*;

public class Skey_kb {
    public static void main(String args[]) throws Exception {
        FileInputStream f = new FileInputStream("key1.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        Key k = (Key) b.readObject();
        byte[] kb = k.getEncoded();
        FileOutputStream f2 = new FileOutputStream("keykb1.dat");
        f2.write(kb);
        // 打印密钥编码中的内容
        for (int i = 0; i < kb.length; i++) {
            System.out.print(kb[i] + ",");
        }
    }
}
