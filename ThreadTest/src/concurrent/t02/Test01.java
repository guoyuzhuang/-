package concurrent.t02;

import java.util.ArrayList;
import java.util.List;

public class Test01 {

	public static void main(String[] args) {
		Object lock = new Object();
		Test01_Container tc = new Test01_Container();
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					if (tc.size() != 5) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("size is 5");
					lock.notifyAll();
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					for(int i = 0; i < 10;++i) {
						if (tc.size() == 5) {
							lock.notifyAll();
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tc.add();
						System.out.println(Thread.currentThread().getName() + "---" + tc.size());
					}
				}
				
			}
		}).start();
	}
}

class Test01_Container {
	private List<String> list = new ArrayList<>();

	public void add() {
		list.add(new String(""));
	}

	public int size() {
		return list.size();
	}
}
