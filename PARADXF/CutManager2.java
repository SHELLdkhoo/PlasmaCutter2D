/** Clase que permite manipular y trazar dibujos en la mesa con comunicaci�n via puerto serie */
public class CutManager2{

	//Variables para trazar una linea entre dos puntos
	public int x1,y1,x2,y2;

	//Puerto para la comunicaci�n
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

	//Metodo para llevar la cabeza de corte a la posici�n inicial
	public void Inicial(){
		setVars(0,0,0,0);
		//SerialPort.send(35);//c�digo de posici�n inicial
		//SerialPort.get(24);//espera el c�digo de listo
	}

	//Metodo para iniciar una transmisi�n
	public void Empezar(){
		SerialPort.send(99);//env�a el codigo que desactiva el control manual de la mesa
	}

	//Metodo para finalizar una transmisi�n
	public void Terminar(){
		SerialPort.send(66);//env�a el codigo que activa el control manual de la mesa
	}

	//Metodo para llevar a cabo un movimiento
	//Recibe la direcci�n, la cantidad de pasos a dar y la indicaci�n de encender o no en soplete
	public void cut(String direccion, boolean cortar,int pasos){

		//chequeo de "stop"
		if(seguir!=1){

			int n=0;

			//conversion de valor de direcci�n a un numero
			if(direccion.equals("NORTE")){n=1;}
			else if(direccion.equals("SUR")){n=2;}
			else if(direccion.equals("ESTE")){n=3;}
			else if(direccion.equals("OESTE")){n=4;}

			//si se debe encender el soplete
			if(cortar){
				if(n==1){
					for(int i=0;i<pasos;i++){
						SerialPort.send(53);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==2){
					for(int i=0;i<pasos;i++){
						SerialPort.send(58);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==3){
					for(int i=0;i<pasos;i++){
						SerialPort.send(57);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==4){
					for(int i=0;i<pasos;i++){
						SerialPort.send(54);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
			}

			//si no se debe encender el soplete
			else{
				if(n==1){
					for(int i=0;i<pasos;i++){
						SerialPort.send(21);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==2){
					for(int i=0;i<pasos;i++){
						SerialPort.send(26);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==3){
					for(int i=0;i<pasos;i++){
						SerialPort.send(25);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
				else if(n==4){
					for(int i=0;i<pasos;i++){
						SerialPort.send(22);//env�o del codigo de movimiento respectivo
						do{seguir=SerialPort.get();}
						while((seguir!=1)&&(seguir!=77));//espera la se�al de listo o de detener
						if(seguir==1)
						{break;}
					}
				}
			}
		}
	}
}