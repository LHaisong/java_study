package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition和ReentrantLock完成阻塞队列ArrayBlockingQueue
 */
public class ConditionTest {
	Lock lock=new ReentrantLock();
	Condition notFull=lock.newCondition();
	Condition notEmpty=lock.newCondition();
	private int addIndex,removeIndex,count;
	private int []objects;

	public ConditionTest(int size){
		objects=new int[size];
	}

	public void add(int val)throws InterruptedException{
		lock.lock();
		try{
			while (count==objects.length){
				notFull.await();
			}
			objects[addIndex]=val;
			if(++addIndex==objects.length){
				addIndex=0;
			}
			++count;
			notEmpty.signal();
		}finally {
			lock.unlock();
		}
	}

	public int remove()throws InterruptedException{
		lock.lock();
		try{
			while(count==0){
				notEmpty.await();
			}
			int x=objects[removeIndex];
			if(++removeIndex==objects.length){
				removeIndex=0;
			}
			--count;
			notFull.signal();
			return x;
		}finally {
			lock.unlock();
		}
	}
	public static void main(String[]args) {

	}
}
