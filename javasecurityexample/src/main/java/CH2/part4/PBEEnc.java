package CH2.part4;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * PBE算法（Password Based Encryption，基于口令加密）是一种基于口令的加密算法，其特点是使用口令代替了密钥，而口令由用户自己掌管，
 * 采用随机数杂凑多重加密等方法保证数据的安全性。
 *
 * 原理：
 * PBE算法在加密过程中并不是直接使用口令来加密，而是加密的密钥由口令生成，这个功能由PBE算法中的KDF函数完成。
 * KDF函数的实现过程为：将用户输入的口令首先通过“盐”（salt）的扰乱产生准密钥，再将准密钥经过散列函数多次迭代后生成最终加密密钥，
 * 密钥生成后，PBE算法再选用对称加密算法对数据进行加密，可以选择DES、3DES、RC5等对称加密算法
 */
public class PBEEnc {
    public static void main(String args[]) throws Exception {
        String s = "你好!";
        char[] passwd = args[0].toCharArray();
        PBEKeySpec pbks = new PBEKeySpec(passwd);

        SecretKeyFactory kf =
                SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey k = kf.generateSecret(pbks);

        byte[] salt = new byte[8];
        Random r = new Random();
        r.nextBytes(salt);

        Cipher cp = Cipher.getInstance("PBEWithMD5AndDES");
        PBEParameterSpec ps = new PBEParameterSpec(salt, 1000);
        cp.init(Cipher.ENCRYPT_MODE, k, ps);

        byte ptext[] = s.getBytes("UTF8");
        byte ctext[] = cp.doFinal(ptext);

        //  将盐和加密结果合并在一起保存为密文
        FileOutputStream f = new FileOutputStream("PBEEnc.dat");
        f.write(salt);
        f.write(ctext);
        // 打印盐的值
        for (int i = 0; i < salt.length; i++) {
            System.out.print(salt[i] + ",");
        }
        System.out.println("");
        // 打印加密结果
        for (int i = 0; i < ctext.length; i++) {
            System.out.print(ctext[i] + ",");
        }
    }
}
