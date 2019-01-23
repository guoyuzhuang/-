package concurrent.t03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p><b>Title: </b>Test04.java
 * <p><b>Description: </b>公平锁
 * @author guoyuzhuang
 * @version V1.0
 * <p>
 * 2019年1月23日 guoyuzhuang 创建类  <br>
 *
 */
public class Test04 {

	public static void main(String[] args) {
//		TS1 t = new TS1();
		TS2 t = new TS2();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
	}
}

class TS2 extends Thread {

	ReentrantLock lock = new ReentrantLock(true);//构造参数默认是false，非公平的，要想公平，构造参数需要设置为：true

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获取到了锁对象" + i);
			lock.unlock();
		}
	}
}

class TS1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + "获取到了锁对象" + i);
			}
		}
	}

}
