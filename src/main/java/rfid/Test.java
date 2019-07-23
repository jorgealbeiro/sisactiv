package rfid;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Test {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialComm.getInstance();
		SerialComm.initialize();
		System.out.println(SerialComm.getInstance().manilla);
//		Test t = new Test();
//		SerialComm.getInstance();
//		t.jdjdjdjd();
//		int i = 0;
//		Date d1 = new Date(System.currentTimeMillis());
//		d1.setMonth(d1.getMonth()-i);
//		d1.setDate(1);
//		Date d2 = new Date(System.currentTimeMillis());
//		d2.setMonth(d2.getMonth()-i+1);
//		d2.setDate(1);		
//		System.out.println(d1+"ffffffffffffffffffffffffffff");
//		System.out.println(d2 + "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
//		
//		List<Integer> m = new ArrayList<>();
//		m.add(1);
//		m.add(12);
//		System.out.println(m.size());

		
	}

}
