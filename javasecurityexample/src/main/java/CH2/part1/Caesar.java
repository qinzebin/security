package CH2.part1;

public class Caesar{
   public static void main(String args[]) throws Exception{
        String s=args[0];
        int key=Integer.parseInt(args[1]);
        String es="";
        for(int i=0;i<s.length( );i++){
           char c=s.charAt(i);
           if(c>='a' && c<='z'){
              c+=key%26;
              if(c<'a') c+=26;
              if(c>'z') c-=26;
	   }
           else if(c>='A' && c<='Z'){
              c+=key%26;
              if(c<'A') c+=26;
              if(c>'Z') c-=26;
           }
           es+=c;
       }
       System.out.println(es);
      
   }
}

