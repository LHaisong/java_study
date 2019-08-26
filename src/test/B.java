package test;

public class B extends A{
    public static void main(String[]args){
        B b=new B();
        b.incre();
        int a=b.getState();
        System.out.println(a);
    }

}
