package MD5;

public class Test {
    public static void main(String[]args){
        MD5Util md5Util=new MD5Util();
        String ret=md5Util.MD5Encode("lhslhs","utf-8");
        System.out.println(ret);
        /*e10adc3949ba59abbe56e057f20f883e*/
    }
}
