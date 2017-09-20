package CH7.part1;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;

public class MySSLServer {
    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "mykeystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        SSLServerSocketFactory ssf =
                (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(5432);
        System.out.println("Waiting for connection...");
        while (true) {
            Socket s = ss.accept();
            PrintStream out = new PrintStream(s.getOutputStream());
            out.println("Hi");
            out.close();
            s.close();
        }

    }

    public static class MySSLClient {
        public static void main(String args[]) throws Exception {
            /*System.setProperty("javax.net.ssl.trustStore",
                    "clienttrust");*/
            System.setProperty("javax.net.ssl.trustStore",
                    "mykeystore");

            SSLSocketFactory ssf =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket s = ssf.createSocket("127.0.0.1", 5432);
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String x = in.readLine();
            System.out.println(x);
            in.close();

        }
    }
}
