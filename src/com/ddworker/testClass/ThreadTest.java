package com.ddworker.testClass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程总结
 * 多线程启动方式:1.继承thread 类,实现run 方法;
 * 				 2.实现Runable 接口,实现run 方法
 * 				 3.匿名内部类方式实现 new Thread(new Runnbale(){ //Todo run funciton});
 * 				 4.匿名内部类实现 new Thread(){ //Todo run funciton }
 * volatile 修饰变量, 实现可见性,一定程度上保证有序性,不支持原子性
 * Synchronized  修饰方法 类, 实现锁, 达到原子性,有序性,可见性
 * lock  锁线程,lock.lockInterruptibly 支持请求锁的线程中断
 *		  lock 最终需要释放锁,lock.unlock, 线程中断后不需要释放锁,线程中断后会抛出异常IllegalMonitorStateException
 * @author Cit
 *
 */
public class ThreadTest {
	
	private static int a = 0;
	private static int b = 3;
	private static Lock lock = new ReentrantLock();
	
	
	public static void main(String[] args) {
		
		//1. 调用其他继承Thread 的类创建
		th thins = new th();
		thins.start();
		
		
		//2.调用其他实现Runnable 的类创建
		Thread thrins = new Thread(new thr());
		
		//3.通过内部类的方式实现
		Thread thrins2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Anonymous Inner Class and Implements Runnable to Create Thread!");

			}
			
		});
		thrins2.start();
//		thrins2.run(); 直接调用不会启动多线程
		
		
		//4.内部类继承Thread
		Thread th4 = new Thread() {
			
			//实现Thread 接口
			@Override
			public void run() {
				System.out.println("Anonymous Inner Class and Extends Thread to Create Thread!");
			}
		};
		th4.start();
		
		//synchronized 修饰符同步方法
//		int i = 0;
//		while(i++ <5) {
//			new Thread() {
//				@Override
//				public synchronized void run() {
//					a++;
//					System.out.println("Thread:" +Thread.currentThread().getName()+ " " + a);
//				};
//				
//			}.start();
//		}
		
//		//lock 锁操作: lock 和 trylock
//		for(int j = 0;j<2;j++) {
//			new Thread() {
//				@Override
//				public void run() {
//					
//					Thread tcur = Thread.currentThread();
////					lock.lock();	
////					if(lock.tryLock()) {
//						try {
//							System.out.println(tcur.getName()+":获取锁成功");
////								System.out.println(Thread.currentThread().getName()+ " " + b++);
//						} catch (Exception e) {
////								e.printStackTrace();
//						} finally {
//							System.out.println(tcur.getName()+":释放锁成功");
//							lock.unlock();
//							
//						}
////					}else{
////							System.out.println(tcur.getName()+":获取锁失败");
////					}
//
//				};
//				
//			}.start();
//		}
		
		//验证lcok.lockInterruptibly() 支持请求锁的线程中断操作
		Thread th1 = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
//					lock.tryLock(); //尝试获取锁
					whileFunc();
				} catch (Exception e) {
					System.out.println(Thread.currentThread().getName() + " 线程中断");
				} finally {
					lock.unlock();
				}
				
			}
		};
		Thread th2 = new Thread() {
			@Override
			public void run() {
				try {
//					lock.lock(); //lock 不支持请求中的线程中断
					lock.lockInterruptibly(); //支持请求线程中断
					whileFunc(); //在此请求锁住方法,因此中断,无法继续执行
					System.out.println("继续执行");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " 线程中断");
				}
			}
		};
		th1.start(); //th1 加锁
		th2.start(); //th2 申请锁
		th2.interrupt(); //中断th2线程等待申请锁的休眠状态
		
		
		System.out.println("Thread:" +Thread.currentThread().getName()+" Nomal method print.");
		
	}
	
	/**
	 * 线程公用方法,共享以达到 同步方法,进程占用资源的目的
	 * 确定是否加锁,是否中断
	 */
	private static void whileFunc() {
		int xx = 0;
		long start = System.currentTimeMillis();
		while((System.currentTimeMillis() - start) <300) {
			xx++;
		}
		System.out.println(300);
	}
}


class th extends Thread {
	
	@Override
	public void run() {
		System.out.println("Extends Thread to Create Thread!");
	}
	
	
}


class thr implements Runnable {

	@Override
	public synchronized  void run() {
		System.out.println("Implements Runable to Create Thread!");
	}
}

