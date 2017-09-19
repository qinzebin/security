package CH3.part2.web;

import java.io.*;

public class TestURL{
  static public void main( String args[] ) throws Exception {
          System.out.println("From Main");
          System.out.println(args[0]);
          System.out.println(args[1]);
  }

  public void tt( ) throws Exception{
       System.out.println("From tt without args");
       byte b[]="How are you!".getBytes("UTF8");
       FileOutputStream f=new FileOutputStream("ssss.txt");
       f.write(b);
  }


  public void tt(String s, int i){
       System.out.println("Fron tt with args");
       System.out.println(s);
       System.out.println(i);
  }


}
