package CH4.part4;

import java.io.*;
import java.security.*;

public class DoAttack {
    public static void main(String args[]) throws Exception {
        String md;
        BufferedReader in = new BufferedReader(
                new FileReader("dict.txt"));
        while ((md = in.readLine()) != null) {
            if (md.indexOf(args[0]) == 0) {
                System.out.println(md);
                break;
            }
        }
        in.close();
    }
}
