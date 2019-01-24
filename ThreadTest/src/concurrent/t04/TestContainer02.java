package concurrent.t04;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestContainer02<T> {

	private final LinkedList<T> list = new LinkedList<T>();
	private ReentrantLock lock = new ReentrantLock(true);

	public void put(T t) {
		lock.lock();
		while (list.size() >= 10) {
			try {
				lock.unlock();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.add(t);
		System.out.println(Thread.currentThread().getName() + "生产一条数据，生产之后，数据信息为：" + list.size());
		lock.unlock();
	}

	public void get() {
		lock.lock();
		while (list.size() == 0) {
			try {
				lock.unlock();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.removeFirst();
		System.out.println(Thread.currentThread().getName() + "消费一条数据，消费之后，数据信息为：" + list.size());
		lock.unlock();
	}

	public static void main(String[] args) {
		final TestContainer02<String> tc = new TestContainer02<>();
		//定义生产者		
		for (int i = 0; i < 10; ++i) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						tc.put(new Date().getTime() + "");
					}
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//定义消费者
		for (int i = 0; i < 10; ++i) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						tc.get();
					}
				}
			}).start();
		}
	}
}
