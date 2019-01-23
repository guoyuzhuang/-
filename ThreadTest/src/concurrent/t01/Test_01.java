package concurrent.t01;

import java.util.ArrayList;
import java.util.List;

public class Test_01 {
	
	private int count = 0;

	public static void main(String[] args) throws Exception {
		Test_01 t = new Test_01();
		/*for(int j = 0; j < 10; ++j) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < 10; ++i) {
						System.out.println(Thread.currentThread().getName() + "减库存，处理之后的结果为：" + (t.count++));
					}
				}
			}).start();
		}
		TimeUnit.SECONDS.sleep(2);
		System.out.println(t.count);*/
		List<Test_01Pojo> list = new ArrayList<>();
		for (int i = 0; i <  10; ++i) {
			Test_01Pojo ts = new Test_01Pojo(t);
			list.add(ts);
			ts.start();
		}
		/*for (int j = 0; j < list.size(); ++j) {
			Test_01Pojo ts = list.get(j);
			ts.join();
		}*/
		System.out.println("最终执行结果：" + t.getCount());
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
