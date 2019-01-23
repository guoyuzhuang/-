package concurrent.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Test02 {

	public static void main(String[] args) {
		CountDownLatch down = new CountDownLatch(5);
		Test02_Container tc = new Test02_Container();
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (tc.size() != 5) {
					try {
						down.await();//等待门栓开放
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("size is 5");

			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; ++i) {
					if (down.getCount() > 0)
						down.countDown();
					tc.add();
					System.out.println(Thread.currentThread().getName() + "---" + tc.size());
				}

			}
		}).start();
	}
}

class Test02_Container {
	private List<String> list = new ArrayList<>();

	public void add() {
		list.add(new String(""));
	}

	public int size() {
		return list.size();
	}
}
