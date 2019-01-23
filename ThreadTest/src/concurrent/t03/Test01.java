package concurrent.t03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p><b>Title: </b>Test01.java
 * <p><b>Description: </b>锁的重入
 * @author guoyuzhuang
 * @version V1.0
 * <p>
 * 2019年1月23日 guoyuzhuang 创建类  <br>
 *
 */
public class Test01 {
	Lock lock = new ReentrantLock();
	
	public void m1() {
		lock.lock();
		for(int i = 0; i < 5; ++i) {
			System.out.println(Thread.currentThread().getName() + " m1 " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	
	public void m2() {
		lock.lock();
		System.out.println("m2");
		lock.unlock();
	}
	
	public static void main(String[] args) {
		Test01 t = new Test01();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.m1();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.m2();
			}
		}).start();
	}
}
