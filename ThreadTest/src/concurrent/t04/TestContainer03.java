package concurrent.t04;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestContainer03<T> {

	private final LinkedList<T> list = new LinkedList<T>();
	//	private ReentrantLock lock = new ReentrantLock(true);
	private Lock lock = new ReentrantLock(false);
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		lock.lock();
		try {
			while (list.size() >= 10) {
				try {
					producer.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(t);
			System.out.println(Thread.currentThread().getName() + "生产一条数据，生产之后，数据信息为：" + list.size());
			consumer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

	public void get() {
		try {
			lock.lock();
			while (list.size() == 0) {
				try {
					consumer.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.removeFirst();
			System.out.println(Thread.currentThread().getName() + "消费一条数据，消费之后，数据信息为：" + list.size());
			producer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final TestContainer03<String> tc = new TestContainer03<>();
		//定义生产者		
		for (int i = 0; i < 1; ++i) {
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
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//定义消费者
		for (int i = 0; i < 1; ++i) {
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
