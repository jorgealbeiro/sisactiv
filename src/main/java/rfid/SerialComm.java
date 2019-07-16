package rfid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Component;
import java.util.Enumeration;
import javax.swing.JOptionPane;

public class SerialComm implements SerialPortEventListener {

	private static SerialComm sc =null; 
	
	public static SerialComm getInstance() {
		if(sc==null) {
			SerialComm.sc=new SerialComm();
		}
		return sc;
	}
	
	private SerialComm() {
		
	}
	
	SerialPort serialPort;

	public  String manilla = "";
	
	private static final String PORT = "COM3";

	private BufferedReader input;

	private OutputStream output;

	private static final int TIME_OUT = 2000;

	private static final int DATA_RATE = 9600;

	public void initialize() {
		System.setProperty("gnu.io.rxtx.SerialPorts", PORT);
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			if (currPortId.getName().equals(PORT)) {
				portId = currPortId;
				break;
			}
		}
		System.out.println("holahola");
		if (portId == null) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "No se encontró el puerto");

			// System.out.println("No se encontró el puerto");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		System.out.println("holahola22");

	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println(inputLine);

				System.out.println("nooooooooooooooooooooooooooo");
				if (inputLine.startsWith("Card UID")) {
					System.out.println("hollllllllllll..     " + inputLine.substring(10).trim());
//						gui.cuid( inputLine.substring(10).trim() );
					System.out.println(inputLine.substring(10).trim());
					manilla = inputLine.substring(10).trim();
				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}

}
