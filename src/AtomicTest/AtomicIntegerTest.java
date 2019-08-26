package AtomicTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[]args){
        AtomicInteger aI=new AtomicInteger(5);
//        System.out.println("No1 "+aI.get());
//        aI.set(6);
//        System.out.println("No2 "+aI.get());
//        aI.getAndSet(7);
//        System.out.println("No3 "+aI.get());
//        aI.compareAndSet(6,8);
//        System.out.println("No4 "+aI.get());
//        aI.getAndIncrement();
//        System.out.println("No5 "+aI.get());
        aI.getAndAdd(5);
        System.out.println("No6 "+aI);
    }
}
