package concurrent.t03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p><b>Title: </b>Test04.java
 * <p><b>Description: </b>��ƽ��
 * @author guoyuzhuang
 * @version V1.0
 * <p>
 * 2019��1��23�� guoyuzhuang ������  <br>
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

	ReentrantLock lock = new ReentrantLock(true);//�������Ĭ����false���ǹ�ƽ�ģ�Ҫ�빫ƽ�����������Ҫ����Ϊ��true

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "��ȡ����������" + i);
			lock.unlock();
		}
	}
}

class TS1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + "��ȡ����������" + i);
			}
		}
	}

}
