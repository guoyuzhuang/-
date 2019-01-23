package concurrent.t01;

import java.util.concurrent.TimeUnit;

public class TestVolatile implements Runnable {

	private /*volatile*/ int i = 0;

	@Override
	public void run() {
		while (i < 50) {
			try {
				TimeUnit.MICROSECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				if (i < 50)
					System.out.println(Thread.currentThread().getName() + "增加数量：" + i++);
			}
		}
	}

	public static void main(String[] args) {
		TestVolatile tv = new TestVolatile();
		for (int i = 0; i < 5; ++i) {
			Thread t = new Thread(tv);
			t.start();
		}
	}

}
