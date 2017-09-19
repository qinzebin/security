package CH4.part3;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;


import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;


//msg.dat存放不可修改的信息
//Skey_RSA_priv.dat 为以前的私钥，自己秘密保存，用自己的私钥签名
// java sign， 则生成签名文件sign.dat
// 将msg.dat和sign.dat一起给另一个人，同时将公钥给他

//运行结果：

public class Sign {


    public static void main(String args[]) throws Exception {
        //获取要签名的数据，放在data数组
        FileInputStream f = new FileInputStream("msg.dat");
        int num = f.available();
        byte[] data = new byte[num];
        f.read(data);

        //获取私钥
        FileInputStream f2 = new FileInputStream("Skey_RSA_priv.dat");
        ObjectInputStream b = new ObjectInputStream(f2);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();

        //获取Signature对象
        Signature s = Signature.getInstance("MD5WithRSA");
        //初始化
        s.initSign(prk);
        //传入要签名的数据
        s.update(data);
        System.out.println("");
        //签名
        byte[] signeddata = s.sign();
        for (int i = 0; i < data.length; i++) {
            System.out.print(signeddata[i] + ",");
        }
        //保存签名
        FileOutputStream f3 = new FileOutputStream("Sign.dat");
        f3.write(signeddata);


    }

}  