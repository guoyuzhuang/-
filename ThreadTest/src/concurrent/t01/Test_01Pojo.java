package concurrent.t01;

public class Test_01Pojo extends Thread{
	
	private Test_01 t;

	public Test_01Pojo(Test_01 t) {
		super();
		this.t = t;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; ++i) {
			t.setCount(t.getCount() + 1);
			System.out.println(Thread.currentThread().getName() + "执行结果为：" + t.getCount());
		}
	}

}
