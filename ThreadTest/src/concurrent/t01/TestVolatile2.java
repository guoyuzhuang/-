package concurrent.t01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestVolatile2 {

	public static void main(String[] args) {
		TestVolatile2_Container tc = new TestVolatile2_Container();
		for (int i = 0; i < 5; ++i) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						tc.add();
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			t.start();
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("当前值为：" + tc.size());
				}
			}
		}).start();
	}

}

class TestVolatile2_Container {
	List<String> list = new ArrayList<>();

	public void add() {
		list.add(new String(""));
	}

	public int size() {
		return list.size();
	}
}
