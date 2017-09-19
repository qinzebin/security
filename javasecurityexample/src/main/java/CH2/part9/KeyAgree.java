package CH2.part9;

import java.io.*;
import java.math.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

public class KeyAgree {
    public static void main(String args[]) throws Exception {

        // 读取对方的DH公钥
        FileInputStream f1 = new FileInputStream(args[0]);
        ObjectInputStream b1 = new ObjectInputStream(f1);
        PublicKey pbk = (PublicKey) b1.readObject();

        //读取自己的DH私钥
        FileInputStream f2 = new FileInputStream(args[1]);
        ObjectInputStream b2 = new ObjectInputStream(f2);
        PrivateKey prk = (PrivateKey) b2.readObject();

        // 执行密钥协定
        KeyAgreement ka = KeyAgreement.getInstance("DH");
        ka.init(prk);
        ka.doPhase(pbk, true);

        //生成共享信息
        byte[] sb = ka.generateSecret();
        for (int i = 0; i < sb.length; i++) {
            System.out.print(sb[i] + ",");
        }
        SecretKeySpec k = new SecretKeySpec(sb, "DESede");
    }
}  