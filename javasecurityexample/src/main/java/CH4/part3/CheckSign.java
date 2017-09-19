package CH4.part3;

import java.io.*;
import java.security.*;
import java.security.interfaces.*;

public class CheckSign {


    // 从签名者那儿得到msg.dat和sign.dat和公钥Skey_RSA_pub.dat
    // 修改msg.dat则数据无法通过验证，因此不怕网络传递过程中被修改，或signer不承认，因为只有signer才能针对msg.dat得到sign.dat，
    //    如果checker将内容改为$300，则signer可要求其用签名验证，

    // 1.验证完整性  checker担心是否网络传递时将内容修改过了，或者是否确实是signer发来的，不是别人乱传的，经检查签名，正确，于是放心，

    // 2.不可否认性。 signer不承认这个文件是自己发的，或说checker改过内容了，checker展示其签名sign.dat，只有signer有私钥，能根据msg.dat生成这样的内容，别人都不能。
    public static void main(String args[]) throws Exception {
        //获取数据，放在data数组
        FileInputStream f = new FileInputStream("msg.dat");
        int num = f.available();
        byte[] data = new byte[num];
        f.read(data);

        //读签名
        FileInputStream f2 = new FileInputStream("Sign.dat");
        int num2 = f2.available();
        byte[] signeddata = new byte[num2];
        f2.read(signeddata);

        //读公钥
        FileInputStream f3 = new FileInputStream("Skey_RSA_pub.dat");
        ObjectInputStream b = new ObjectInputStream(f3);
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();

        //获取对象
        Signature s = Signature.getInstance("MD5WithRSA");
        //初始化
        s.initVerify(pbk);
        //传入原始数据
        s.update(data);

        boolean ok = false;
        try {
            //用签名验证原始数据
            ok = s.verify(signeddata);
            System.out.println(ok);
        } catch (SignatureException e) {
            System.out.println(e);
        }

        System.out.println("Check Over");
    }

}