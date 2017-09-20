package CH7.part2;

import java.net.*;
import java.math.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.cert.*;

public class MySSLServerSession {
    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "mykeystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");

        SSLServerSocketFactory ssf =
                (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(5432);
        System.out.println("Waiting for connection...");
        while (true) {
            Socket s = ss.accept();
            SSLSession session = ((SSLSocket) s).getSession();
            Certificate[] cchain2 = session.getLocalCertificates();
            System.out.println("The Certificates used in local");
            for (int i = 0; i < cchain2.length; i++) {
                System.out.println(((X509Certificate) cchain2[i]).
                        getSubjectDN());
            }


            System.out.println("Peer host is " + session.getPeerHost());
            System.out.println("Cipher is " + session.getCipherSuite());
            System.out.println("Protocol is " + session.getProtocol());
            System.out.println("ID is " + new BigInteger(session.getId()));
            System.out.println("Session created in " + session.getCreationTime());
            System.out.println("Session accessed in " + session.getLastAccessedTime());

            PrintStream out = new PrintStream(s.getOutputStream());
            out.println("Hi");
            out.close();
            s.close();
        }

    }
}
