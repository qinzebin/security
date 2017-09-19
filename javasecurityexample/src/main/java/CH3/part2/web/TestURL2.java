package CH3.part2.web;

public class TestURL2{
  static public void main( String args[] ) throws Exception {
          System.out.println("From Main of TestURL2");
          TestURL t=new TestURL( );
          t.tt();
  }

  public void tt(String s, int i){
       System.out.println("Fron tt with args");
       System.out.println(s);
       System.out.println(i);
  }


}
