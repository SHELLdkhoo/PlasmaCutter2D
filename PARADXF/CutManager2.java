/** Clase que permite manipular y trazar dibujos en la mesa con comunicación via puerto serie */
public class CutManager2{

	//Variables para trazar una linea entre dos puntos
	public int x1,y1,x2,y2;

	//Puerto para la comunicación
	puerto SerialPort=new puerto();
	int seguir=0;

	//Metodo para definir el valor de las variables
	public void setVars(int a,int b,int c,int d){
		x1=a;
		y1=b;
		x2=c;
		y2=d;
	}

	//Metodo para preparar el trazo de una linea a partir del punto final de la anterior
	public void avanzar(){
		x1=x2;
		y1=y2;
	}

	//Metodo para llevar la cabeza de corte a la posición inicial
	public void Inicial(){
		setVars(0,0,0,0);
		//SerialPort.send(35);//código de posición inicial
		//SerialPort.get(24);//espera el código de listo
	}

	//Metodo para iniciar una transmisión
	public void Empezar(){
		SerialPort.send(99);//envía el codigo que desactiva el control manual de la mesa
	}

	//Metodo para finalizar una transmisión
	public void Terminar(){
		SerialPort.send(66);//envía el codigo que activa el control manual de la mesa
	}

	//Metodo para llevar a cabo un movimiento
	//Recibe la dirección, la cantidad de pasos a dar y la indicación de encender o no en soplete
	public void cut(String direccion, boolean cortar,int pasos){

		//chequeo de "stop"
		if(seguir!=1){

			int n=0;

			//conversion de valor de dirección a un numero
			if(direccion.equals("NORTE")){n=1;}
			else if(direccion.equals("SUR")){n=2;}
			else if(direccion.equals("ESTE")){n=3;}
			else if(direccion.equals("OESTE")){n=4;}

			//si se debe encender el soplete
			if(cortar){
				if(n==1){
					for(int i=0;i<pasos;i++){
						SerialPort.send(53);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==2){
					for(int i=0;i<pasos;i++){
						SerialPort.send(58);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==3){
					for(int i=0;i<pasos;i++){
						SerialPort.send(57);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==4){
					for(int i=0;i<pasos;i++){
						SerialPort.send(54);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
			}

			//si no se debe encender el soplete
			else{
				if(n==1){
					for(int i=0;i<pasos;i++){
						SerialPort.send(21);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==2){
					for(int i=0;i<pasos;i++){
						SerialPort.send(26);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==3){
					for(int i=0;i<pasos;i++){
						SerialPort.send(25);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==4){
					for(int i=0;i<pasos;i++){
						SerialPort.send(22);//envío del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la señal de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
			}
		}
	}
}