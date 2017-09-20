package CH7.part3;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;

public class MyHTTPSServer {
    public static void main(String args[]) {
        System.setProperty("javax.net.ssl.keyStore", "mykeystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");


        int i = 0;
        try {
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket ss = ssf.createServerSocket(443);
            System.out.println("Web Server OK ");

            while (true) {
                Socket s = ss.accept();  //等待请求
                PrintStream out = new PrintStream(s.getOutputStream());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                String info = null;
                while ((info = in.readLine()) != null) {
                    System.out.println("now got " + info);
                    if (info.equals("")) break;
                }

                System.out.println("now go");
                out.println("HTTP/1.0 200 OK");
                out.println("MIME_version:1.0");
                out.println("Content_Type:text/html");
                i++;
                String c = "<html> <head></head><body> <h1> Hi,  this is "
                        + i + "</h1></Body></html>";
                out.println("Content_Length:" + c.length());
                out.println("");
                out.println(c);
                out.close();
                s.close();
                in.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
