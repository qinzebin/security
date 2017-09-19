package CH4.part1;
import java.security.*;

public class DigestPass{
    public static void main(String args[ ]) throws Exception{
        String x=args[0];
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(x.getBytes("UTF8"));
        byte s[ ]=m.digest( );

        String result="";
        for (int i=0; i<s.length; i++){
            result+=Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }
        System.out.println(result);
    }
}

