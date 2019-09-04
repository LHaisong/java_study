package concurrent;

/**
 * 死锁
 */
public class DeadLock {
    public static void method1(){
        synchronized (String.class) {
            System.out.println("lock String object1");
            synchronized (Integer.class) {
                System.out.println("lock Integer object1");
            }
        }
    }
    public static void method2(){
        synchronized (Integer.class) {
            System.out.println("lock Integer object2");
            synchronized (String.class) {
                System.out.println("lock String object2");
            }
        }
    }
    public static void main(String[]args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        }).start();
    }
}
