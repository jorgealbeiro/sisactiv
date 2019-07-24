package rfid;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 */
public class SerialComm {

	private static final String PORT = "COM3";
	private static final int DATA_RATE = 9600;
	private PanamaHitek_Arduino panaArdu;

	public String manilla = "no detecto manilla";

	private static SerialComm sc = null;

	public static SerialComm getInstance() {
		if (sc == null) {
			SerialComm.sc = new SerialComm();
		}
		return sc;
	}

	private SerialComm() {
		this.panaArdu = new PanamaHitek_Arduino();
		try {
			this.panaArdu.arduinoRX(PORT, DATA_RATE, listener);
		} catch (ArduinoException | SerialPortException ex) {
			Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	SerialPortEventListener listener = new SerialPortEventListener() {

		@Override
		public void serialEvent(SerialPortEvent spe) {
			try {
				String inputLine = panaArdu.printMessage();
				if (inputLine.startsWith("Card UID: ")) {
//					System.out.println("input: " + inputLine.substring(10, 21));
					manilla = inputLine.substring(10, 21);
					System.out.println("manilla: " + manilla);
				}
			} catch (SerialPortException ex) {
				Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ArduinoException ex) {
				Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	};

	public static void initialize() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
//					System.out.println(SerialComm.getInstance().manilla);
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

		SerialComm.getInstance();
		SerialComm.initialize();

	}
}