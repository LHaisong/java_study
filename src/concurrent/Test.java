package concurrent;

public class Test {
    public static int i=0;
    public static int getI(){
        return i;
    }
    public static void increase(){
        i++;
    }
    public static void main(String[]args){
       int ThreadCount=5;
       try{
           Thread[]threads=new Thread[ThreadCount];
           for(int i=0;i<ThreadCount;i++){
               threads[i]=new Thread(new Runnable() {
                   @Override
                   public void run() {
                      increase();
                   }
               });
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       System.out.println(i);
    }
}
