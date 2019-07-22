package rfid;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public void jdjdjdjd() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println(SerialComm.getInstance().manilla);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
//		SerialComm.getInstance().initialize();
//		t.jdjdjdjd();
		int i = 0;
		Date d1 = new Date(System.currentTimeMillis());
		d1.setMonth(d1.getMonth()-i);
		d1.setDate(1);
		Date d2 = new Date(System.currentTimeMillis());
		d2.setMonth(d2.getMonth()-i+1);
		d2.setDate(1);		
		System.out.println(d1+"ffffffffffffffffffffffffffff");
		System.out.println(d2 + "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
		
		List<Integer> m = new ArrayList<>();
		m.add(1);
		m.add(12);
		System.out.println(m.size());

		
	}

}
