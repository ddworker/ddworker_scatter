package com.ddworker.testClass;

/**
 * 测试 对象
 * @author Cit
 *
 */
class testObj{
	static int nObj = 0;
}

/**
 * 测试多线程中的几种状态 NEW, RUNNABLE,WAITING,TIMED_WAITING,BLOCKED,TERMINATED
 * 测试Wait，Notify使用方式： 需要Synchronized获得锁
 * 测试Interrupt方法在不同状态下中断线程的情况：
 * 		正常情况不影响，中断Synchronized SLEEP,WAIT会跳过Synchronized后续代码继续执行，
 * 		而且会抛出InterruptedException
 * @author Cit
 *
 */
public class ThreadTest1 {

	public static void main(String[] args) throws InterruptedException {
		
        
		SubThread bt = new SubThread();
		//sleep中断测试
//		SubThread2 bt2 = new SubThread2();
//		bt2.start();
		
//        创建NEW
        System.out.println(bt.getState());
        bt.start();
        //就绪状态
        System.out.println(bt.getState());
        
        //等待
        Thread.sleep(2000);
//        bt2.interrupt();
        System.out.println(bt.getState());
        
        synchronized (testObj.class) {
        	testObj.nObj++;
        	testObj.class.notify(); //唤醒bt线程
        	System.out.println(testObj.nObj);
//        	//阻塞
        	//此处锁定testObj对象，bt线程blacked在对象等待池中
        	System.out.println(bt.getState());
		}
        //终止
        Thread.sleep(1000);
        System.out.println(bt.getState());
  
			
	}
	

}


/**
 * 子线程
 * @author Cit
 *
 */
class SubThread extends Thread{
	
	SubThread(){
		super();
	}
	public SubThread(ThreadGroup threadGroup, String string) {
		super(threadGroup, string);
	}
	
	@Override
	public void run() {
		//synchronized + wait +notify
		synchronized(testObj.class) {
			try {
				System.out.println("try to waiting ...");
				testObj.class.wait();
				++testObj.nObj;
				System.out.println("wake up!");
			} catch (InterruptedException e) {
				System.out.println("木好意思，线程被中断");
			}

		}
		System.out.println("线程0："+testObj.nObj);
		
	}
}


/**
 * 子线程
 * @author Cit
 *
 */
class SubThread2 extends Thread{
	
	SubThread2(){
		super();
	}
	public SubThread2(ThreadGroup threadGroup, String string) {
		super(threadGroup, string);
	}
	
	@Override
	public void run() {
		//synchronized + wait +notify
		synchronized(testObj.class) {
			try {
				System.out.println("sleep 4 secend");
				Thread.sleep(4000);
				testObj.nObj++;
				System.out.println(testObj.nObj);
			} catch (InterruptedException e) {
				System.out.println("线程被中断");
			}

		}
		System.out.println("线程2：");
		
	}
}


/**
 * 后台线程
 * @author Cit
 *
 */
class BackgroundThread extends Thread{
	
	BackgroundThread(){
		super();
	}
	public BackgroundThread(ThreadGroup threadGroup, String string) {
		super(threadGroup, string);
	}

	@Override
	public void run() {
		while(true) {
			//当检测到中断状态，则结束run方法
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Checked Interrupt status");
				return;
			}
		}
	}
}





