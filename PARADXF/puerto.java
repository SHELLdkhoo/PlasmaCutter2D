import javax.comm.*;
import java.io.*;

/** Clase que controla el puerto serie de la computadora */
public class puerto{

	//Definición de los parámetros de control del puerto

	static CommPortIdentifier portID;//identificador del puerto en el hardware de la PC
	static SerialPort serial;//puerto serie

	static InputStream input;//datos de entrada
	static OutputStream output;//datos de salida

	public static int entrada;
	public static int salida;

	static InputStreamReader isr = new InputStreamReader(System.in) ;
	static BufferedReader br = new BufferedReader(isr);

	//metodo para abrir el puerto y establecer parámetros
	public static void openPort(){
		try{
			serial=(SerialPort)portID.open("Puerto", 1000);
			serial.setSerialPortParams(19200,
									   SerialPort.DATABITS_8,
									   SerialPort.STOPBITS_1,
									   SerialPort.PARITY_NONE);
			serial.setInputBufferSize(1);
			input=serial.getInputStream();
			output=serial.getOutputStream();

		}catch (Exception e){System.out.print("Error0: "+e);}
	}

	//metodo para liberar el puerto
	public static void closePort(){
		try{
			serial.close();
		}catch (Exception e){System.out.print("Error0: "+e);}
	}

	//metodo para apropiarse del puerto de la PC
	public static void getPort(){
		try{
			portID=CommPortIdentifier.getPortIdentifier("COM1");
		}catch (Exception e){System.out.print("Error1: "+e);}
	}

	//metodo que entra en espera hasta que se reciba en el puerto un valor determinado
	public static void get(int n){
		entrada=0;
		do{
			try{
				if (input.available()>0){
					entrada=input.read();
					System.out.println("Dato Recibido ("+entrada+")"+"\n");
				}
			}catch (Exception e){System.out.print("Error2: "+e);}
		}while(entrada!=n);
	}

	//metodo que devuelve el valor del dato recibido en el puerto
	public static int get(){
		entrada=0;
		try{
			if (input.available()>0){
				entrada=input.read();
				System.out.println("Dato Recibido ("+entrada+")"+"\n");
			}
		}catch (Exception e){System.out.print("Error2: "+e);}

		return(entrada);
	}

	//metodo que envia un dato por el puerto
	public static void send(int n){
		try{
			output.write(n);
			System.out.println("Dato Enviado ("+n+")"+"\n");
		}catch (Exception e){System.out.print("Error3: "+e);}
	}
}
