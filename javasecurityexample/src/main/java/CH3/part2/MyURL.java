package CH3.part2;

import java.net.*;
import java.lang.reflect.*;

public class MyURL {
    static public void main(String args[]) throws Exception {
        URL myurl[] = {
        /*new URL("file:///C:/CH3/ClassLoader/web/"),
        new URL ("http://www.shu.edu.cn/~xyx/test/jvenc/")*/
                new URL("file:///D:/projects/IdeaProjectsNew/security/javasecurityexample/target/classes/")
        };
        URLClassLoader x = new URLClassLoader(myurl);

        Class c = x.loadClass("TestURL");

        Class getArg1[] = {(new String[1]).getClass()};
        Method m = c.getMethod("main", getArg1);
        String[] my1 = {"arg1 passed", "arg2 passed"};
        Object myarg1[] = {my1};
        m.invoke(null, myarg1);

        Object ob = c.newInstance();
        Class arg2[] = {};
        Method m2 = c.getMethod("tt", arg2);
        m2.invoke(ob, null);

        Class arg3[] = {(new String()).getClass(), int.class};
        Method m3 = c.getMethod("tt", arg3);
        Object myarg2[] = {"Arg1", new Integer(100)};
        m3.invoke(ob, myarg2);
    }
}
