package concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 获取正在运行的线程的信息
 */
public class MultiThread {
	public static void main(String[]args){
		ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
		ThreadInfo[]threadInfos=threadMXBean.dumpAllThreads(false,false);
		for(ThreadInfo t:threadInfos){
			System.out.println("["+t.getThreadId()+"]"+t.getThreadName());
		}
	}
}
