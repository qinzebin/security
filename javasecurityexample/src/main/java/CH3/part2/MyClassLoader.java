package CH3.part2;

import java.io.*;
import java.lang.reflect.*;
import java.security.*;
import javax.crypto.*;

public class MyClassLoader extends ClassLoader
{
  public Class findClass( String name){
       byte[ ] classData=null;
       try{
           FileInputStream f=
                new FileInputStream("C:\\CH3\\ClassLoader\\web\\"+
			name+".class");
           int num=f.available();
           classData=new byte[num];
           f.read(classData);
       } catch(IOException e){System.out.println(e); }
       Class x=defineClass(name, classData, 0, classData.length);
       return x;
  }
}

class MyURL2{

  static public void main( String args[] ) throws Exception {
      MyClassLoader x=new MyClassLoader();
      Class c = x.loadClass(args[0]);
      Class getArg1[] = { (new String[1]).getClass() };
      Method m = c.getMethod( "main", getArg1 );
      String[] my1={"arg1 passed","arg2 passed"};
      Object myarg1[] = {my1};
      m.invoke( null, myarg1 );

  }

}
