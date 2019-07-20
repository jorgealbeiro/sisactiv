package rfid;

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
		SerialComm.getInstance().initialize();
		t.jdjdjdjd();

	}

}
