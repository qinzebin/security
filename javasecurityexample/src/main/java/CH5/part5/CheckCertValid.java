package CH5.part5;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
public class CheckCertValid{

    public static void main(String args[ ]) throws Exception{
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in=new FileInputStream(args[0]);
        java.security.cert.Certificate c=cf.generateCertificate(in);
        in.close();
        X509Certificate t=(X509Certificate) c;

        Calendar cld=Calendar.getInstance();
        int year=Integer.parseInt(args[1]);
        int month=Integer.parseInt(args[2])-1;  // as 0 is Jan, 11 
        int day=Integer.parseInt(args[3]);
        cld.set(year,month,day);
        Date d=cld.getTime();
        System.out.println(d);
        try{
            t.checkValidity(d);
            System.out.println("OK");
        }catch(CertificateExpiredException e){   //过期
            System.out.println("Expired");
            System.out.println(e.getMessage());
        }
        catch(CertificateNotYetValidException e){   //尚未生效
            System.out.println("Too early");
            System.out.println(e.getMessage());
        }
    }
}



