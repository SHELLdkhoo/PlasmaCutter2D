
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.*;
import java.awt.event.*;
import java.util.*;
import javax.comm.*;
import java.io.*;
import JSuperButton;

/** Clase principal, que manipula las variables de la decodificación del archivo DXF */
public class UNIDADDXF extends JFrame implements ActionListener{

	//Componentes para la interfaz con el usuario
	static Container p;
	static JPanel p0 = new JPanel();
	static JPanel p1 = new JPanel();
	static JPanel p11= new JPanel();
	static JPanel p2 = new JPanel();
	JLabel ejex = new JLabel(new ImageIcon("./images/ejex.gif"),JLabel.CENTER);
	JLabel ejey = new JLabel(new ImageIcon("./images/ejey.gif"),JLabel.CENTER);
	JLabel bandaV = new JLabel(new ImageIcon("./images/bandaV.gif"));
	JLabel bandaH = new JLabel(new ImageIcon("./images/bandaH.gif"));
	JLabel title = new JLabel("Vista Previa",JLabel.CENTER);
	static JSuperButton c = new JSuperButton("Detener");
	static JSuperButton d = new JSuperButton("Dibujar");
	static JSuperButton clean = new JSuperButton("Limpiar");
	static JSuperButton t = new JSuperButton("Cortar");
	static JSuperButton pi = new JSuperButton("Posición Inicial");

	//clase para trazarel grafico en pantalla y sobre la UNIDADDXF
	static nCan2 can = new nCan2();
	static CutManager2 cutter = new CutManager2();
	static boolean stopdraw;

	//variables para el trazo de un línea recta
	int pendiente,ajuste,dX,dY,delta,acc,counter,nx,ny;
	boolean isDeltaDY;

	//para saber donde está la cabeza de la cortadora sobre la UNIDADDXF
	int AquiX,AquiY;

	//herramientas para leer y decodificar el archivo dxf
	StringTokenizer st;
	String file,input,elemento;

	//ventana de abrir2 archivo
	abrir2 a;

	//elementos de la lista de entidades en el dibujo
	Entidades ent;
	Entidad prov,y;





	//constructor de la interfaz
	public UNIDADDXF(){


		super("SISTEMA DE CORTE DE PIEZAS CON PLASMA VER2.0");


try{

	/*javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme( new javax.swing.plaf.metal.DefaultMetalTheme());
	UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	}*/
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		        }
catch(Exception exception){System.err.println("Error loading L&F: " + exception);}






        p = getContentPane();
		p.setLayout(new BorderLayout());
	    p0.setLayout(new BorderLayout(5,5));
		p0.setBackground(Color.black);

		p0.add("Center",can);
		p0.add("West",ejey);
		p0.add("North",bandaH);
		p0.add("South",ejex);
		p0.add("East",bandaV);

		p1.add("Center",c);
		p1.add("Center",d);
		p1.add("Center",clean);
		p1.add("Center",t);
		p1.add("Center",pi);

		p2.add("Center",title);

		p.add("North",title);
		p.add("Center",p0);
		p.add("South",p1);
		setContentPane(p);
		p.validate();




		        JMenuBar BarraMenus = new JMenuBar();
		        BarraMenus.setBorderPainted(true);
		        setJMenuBar(BarraMenus);

		        ImageIcon imageicon = new ImageIcon("images/iconos/icon1.gif");
		        ImageIcon imageicon1 = new ImageIcon("images/iconos/icon2.gif");
		        ImageIcon imageicon2 = new ImageIcon("images/iconos/icon3.gif");
		        ImageIcon imageicon3 = new ImageIcon("images/iconos/icon4.gif");
		        JMenu jmenu = new JMenu("Archivo");
		        JMenuItem jmenuitem0 = new JMenuItem("Abrir", imageicon);
		        JMenuItem jmenuitem1 = new JMenuItem("Guardar", imageicon1);
		        JMenuItem jmenuitem2 = new JMenuItem("Imprimir", imageicon2);
		        JMenuItem jmenuitem3 = new JMenuItem("Cerrar", imageicon3);
		        jmenu.add(jmenuitem0);
		        jmenu.add(jmenuitem1);
		        jmenu.addSeparator();
		        jmenu.add(jmenuitem2);
		        jmenu.add(jmenuitem3);
		        BarraMenus.add(jmenu);

		        ImageIcon imageicon4 = new ImageIcon("images/iconos/icon5.gif");
		        ImageIcon imageicon5 = new ImageIcon("images/iconos/icon6.gif");
		        ImageIcon imageicon6 = new ImageIcon("images/iconos/icon7.gif");
		        ImageIcon imageicon7 = new ImageIcon("images/iconos/icon8.gif");
		        ImageIcon imageicon8 = new ImageIcon("images/iconos/icon9.gif");
		        ImageIcon imageicon9 = new ImageIcon("images/iconos/icon10.gif");
		        ImageIcon imageicon10 = new ImageIcon("images/iconos/icon11.gif");
		        JMenu jmenu1 = new JMenu("Controles");
		        JMenuItem jmenuitem4  = new JMenuItem("Control de Temperatura", imageicon4);
		        JMenuItem jmenuitem5  = new JMenuItem("Control de Actuadores", imageicon5);
		        JMenuItem jmenuitem6  = new JMenuItem("Control de Alertas", imageicon6);
		        JMenuItem jmenuitem7  = new JMenuItem("Control de Velocidad", imageicon7);
		        JMenuItem jmenuitem8  = new JMenuItem("Control de Coordenadas", imageicon8);
		        JMenuItem jmenuitem9  = new JMenuItem("Control de Unidades", imageicon9);
		        JMenuItem jmenuitem10 = new JMenuItem("Control de Luces", imageicon10);
		        jmenu1.add(jmenuitem4);
		        jmenu1.add(jmenuitem5);
		        jmenu1.addSeparator();
		        jmenu1.add(jmenuitem6);
		        jmenu1.add(jmenuitem7);
		        jmenu1.add(jmenuitem9);
		        jmenu1.add(jmenuitem8);
		        jmenu1.add(jmenuitem10);
		        BarraMenus.add(jmenu1);

		        ImageIcon imageicon11 = new ImageIcon("images/iconos/icon12.gif");
		        ImageIcon imageicon12 = new ImageIcon("images/iconos/icon13.gif");
		        ImageIcon imageicon13 = new ImageIcon("images/iconos/icon14.gif");
		        ImageIcon imageicon14 = new ImageIcon("images/iconos/icon15.gif");
		        ImageIcon imageicon15 = new ImageIcon("images/iconos/icon16.gif");
		        ImageIcon imageicon16 = new ImageIcon("images/iconos/icon17.gif");
		        JMenu jmenu2 = new JMenu("Herramientas");
		        JMenuItem jmenuitem11 = new JMenuItem("Copiar", imageicon11);
		        JMenuItem jmenuitem12 = new JMenuItem("Pegar", imageicon12);
		        JMenuItem jmenuitem13 = new JMenuItem("Zoom para acercar", imageicon13);
		        JMenuItem jmenuitem14 = new JMenuItem("Zoom para alejar", imageicon14);
		        JMenuItem jmenuitem15 = new JMenuItem("Cambiar contrase\361a y(o) usuario", imageicon15);
		        JMenuItem jmenuitem16 = new JMenuItem("Checkeo de sistema", imageicon16);
		        jmenu2.add(jmenuitem11);
		        jmenu2.add(jmenuitem12);
		        jmenu2.addSeparator();
		        jmenu2.add(jmenuitem13);
		        jmenu2.add(jmenuitem14);
		        jmenu2.add(jmenuitem15);
		        jmenu2.add(jmenuitem16);
		        BarraMenus.add(jmenu2);

			    JMenu jmenu3 = new JMenu("Fuentes");
		        jmenu3.setMnemonic('r');
		        BarraMenus.add(jmenu3);
		        JMenu jmenu4 = new JMenu("INFO");
		        jmenu4.setMnemonic('i');
		        JMenuItem jmenuitem17 = new JMenuItem("Acerca de ...");
		        jmenu4.add(jmenuitem17);
                BarraMenus.add(jmenu4);


                c.addActionListener(this);
                jmenuitem0.addActionListener(this);
				d.addActionListener(this);
				t.addActionListener(this);
				pi.addActionListener(this);
				clean.addActionListener(this);
                stopdraw = true;



  jmenuitem0.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent actionevent)
          {limpiar();//limpia la pantalla
		   load();//carga un archivo nuevo
		 if(file!=null){check();   //si el archivo existe, lo analiza
						TrazarloEnPanel();}}});



    jmenuitem17.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
				String m = "Este programa pertenece al Instituto tecnologico de Costa Rica,\n  su distribucion y uso queda sujeto a las leyes de Derechos Reservados Internacionales,  \n  cualquier sugerencia o consulta a ...darksurfer77@hotmail.com";
             Icon logo = new ImageIcon("images/itcr.gif");
             JOptionPane.showMessageDialog(UNIDADDXF.this,m, "Acerca de...", -1,logo);
             repaint();

             }

        });


    jmenuitem9.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent actionevent)
	            {

	             TextField este[] = {new TextField(),new TextField()};
	             String init[] = {"ejex","ejey"};
	             Icon logo = new ImageIcon("images/itcr.gif");
	             JOptionPane.showInputDialog(UNIDADDXF.this, "Digite las Diviciones de la cuadricula en milimetros","Cambio de Multiplo", -1,logo,este,init);

	             }

	        });







jmenuitem3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent actionevent){System.exit(0);}});




}


    public void TrazarloEnPanel(){

		//toma la primera entidad de la lista
			y=ent.first;

		//mientras las entidades no se terminen
		//escoge el tipo de entidad que es y la dibuja en la pantalla de vista previa
						while(y!=null){

							if(y.getType()=="LINE"){
								can.color="amarillo";
								DibujaLinea(y);
							}
							if(y.getType()=="CIRCLE"){
								can.color="rojo";
								DibujaCirculo(y);
							}
							if(y.getType()=="RAY"){
								can.color="rojo";
								DibujaRayo(y);
							}
							if(y.getType()=="POINT"){
								can.color="amarillo";
								DibujaPunto(y);
							}
							if(y.getType()=="ARC"){
								can.color="verde";
								DibujaArco(y);
							}
							y=y.next;//avanza a la siguiente entidad
						}

		}




	//metodo de ejecución
	public static void main(String[] args){
		//construye una interfaz
		UNIDADDXF o = new UNIDADDXF();
		WindowListener l = new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		//establece las caracteristicas principales
		o.addWindowListener(l);
		o.pack();
		o.setSize(800,700);
		can.setSize(200,200);
		can.setBackground(Color.black);
		can.draw(3,100);
		can.clean();
		o.setVisible(true);
		o.setLocation(100,100);
		o.setResizable(false);

	}

	//controlador de acciones para los botones de la ventana

	public void actionPerformed(ActionEvent e){

		//botón de dibujar

        if(e.getSource()==d){
			limpiar();
            TrazarloEnPanel();
            }


        if(e.getSource()==c){

			stopdraw = false;
			return;

            }



		//botón de trazar
		if (e.getSource()==t){

			//si el archivo ya está cargado
			if(file!=null){

				//si se detuvo previamente, limpia los indicadores de posición
				if(cutter.seguir==1){
					cutter.seguir=0;
					AquiX=0;
					AquiY=0;
				}

				//toma la primera entidad
				y=ent.first;

				//se apropia del puerto y lo abre
				cutter.SerialPort.getPort();
				cutter.SerialPort.openPort();

				//envía codigos de ir a la posición inicial e iniciar la transmisión
				Inicial();
				Empezar();

				//mientras las entidades no se terminen
				//escoge el tipo de entidad que es y la traza en la UNIDADDXF
				while(y!=null){

					if(y.getType()=="LINE"){
						TrazaLinea(y);
					}
					if(y.getType()=="CIRCLE"){
						TrazaCirculo(y);
					}
					if(y.getType()=="RAY"){
						TrazaRayo(y);
					}
					if(y.getType()=="POINT"){
						TrazaPunto(y);
					}
					if(y.getType()=="ARC"){
						TrazaArco(y);
					}
					y=y.next;

					if(cutter.seguir==1){y=null;}
				}

				//envía codigos de terminar la transmisión e ir a la posición inicial
				Terminar();
				Inicial();

				//se libera el puerto
				cutter.SerialPort.closePort();
			}
		}

		//botón de limpiar
		if (e.getSource()==clean){
			limpiar();//limpia la pantalla
		}

		//botón de posición inicial
		if(e.getSource()==pi){
			//se apropia del puerto y lo abre
			cutter.SerialPort.getPort();
			cutter.SerialPort.openPort();
			//envía el codigo de posición inicial
			Inicial();
			//establece los parámetros de ubicación
			AquiX=0;
			AquiY=0;
			//libera el puerto
			cutter.SerialPort.closePort();
		}
	}

/***********************************************************************************
 ***********************************************************************************
					Metodos de dibujo o trazado de formas
 ***********************************************************************************
 ***********************************************************************************/



		//metodo para limpiar la pantalla
		public void limpiar(){
			can.clean();
		}



		//metodo que asigna a "file" la dirección de un archivo de texto (*.DXF)
			public void load(){
				a = new abrir2(this);
				file = a.getFile();
				if(file!=null)lee();
			}

			//guarda en el string "input" el contenido del archivo elegido
			public void lee(){
				try{
					File di=new File(a.getDirectory(),(a.getFile()));
					char entrada[]=new char[new Long(di.length()).intValue()];
					FileReader archivo = new FileReader(di);
					archivo.read(entrada,0,new Long(di.length()).intValue()-1);
					input = new String(entrada);
					archivo.close();
				}
				catch(IOException e){System.out.println("Error "+e.toString());}
			}

			//separa el código del archivo de texto en palabras individuales
			public void check(){

				//elementos que no se consideran palabras
				st=new StringTokenizer(input," \t\n\r\f!¡¿?{}[](),:;@#$%^&*+/",false);

				//inicializa la lista de entidades
				ent=new Entidades();

				//mientras no se gasten las palabras
				while (st.hasMoreTokens()){

					elemento = st.nextToken();

					//revisa cada palabra para ver si corresponde a una entidad
					//si lo es, inicializa una nueva entidad en la lista
					//y dependiendo del tipo de entidad, guarda los parámetros
					//respectivos según el método correspondiente

					if(elemento.equals("POINT")){
						prov=new Entidad();
						prov.punto=new POINT();
						punto();
						ent.add(prov);
						prov=null;
					}

					if(elemento.equals("ARC")){
						prov=new Entidad();
						prov.arco=new ARC();
						arco();
						ent.add(prov);
						prov=null;
					}

					if(elemento.equals("CIRCLE")){
						prov=new Entidad();
						prov.circulo=new CIRCLE();
						circulo();
						ent.add(prov);
						prov=null;
					}

					if(elemento.equals("LINE")){
						prov=new Entidad();
						prov.linea=new LINE();
						linea();
						ent.add(prov);
						prov=null;
					}

					if(elemento.equals("XLINE")){
						prov=new Entidad();
						prov.lineaX=new XLINE();
						lineaX();
						ent.add(prov);
						prov=null;
					}

					if(elemento.equals("LWPOLYLINE")){
						polilineaLW();
					}

					if(elemento.equals("RAY")){
						prov=new Entidad();
						prov.rayo=new RAY();
						rayo();
						ent.add(prov);
						prov=null;
					}
				}
			}




	//desplazar la cabeza de la cortadora hasta el inicio del corte
	public void GoThere(int x,int y){
		if((x!=AquiX)||(y!=AquiY)){
			cortalinea(AquiX,AquiY,x,y,false);
		}
	}


//Para dibujar o trazar una linea recta entre dos puntos (x,y)
	public void TrazaLinea(Entidad a){
		Double xi=new Double(a.linea.Xi);
		Double yi=new Double(a.linea.Yi);
		Double xf=new Double(a.linea.Xf);
		Double yf=new Double(a.linea.Yf);

		GoThere(xi.intValue(),yi.intValue());

		cortalinea(xi.intValue(),
			  	   yi.intValue(),
			  	   xf.intValue(),
			  	   yf.intValue(),
			  	   true);
	}

	public void DibujaLinea(Entidad a){
		Double xi=new Double(a.linea.Xi);
		Double yi=new Double(a.linea.Yi);
		Double xf=new Double(a.linea.Xf);
		Double yf=new Double(a.linea.Yf);

		linea(xi.intValue()/2,
			  yi.intValue()/2,
			  xf.intValue()/2,
			  yf.intValue()/2);
	}



//Para dibujar o trazar una linea recta entre un punto (x,y) y el fin de la pantalla/UNIDADDXF
	public void TrazaRayo(Entidad a){
		double xi=a.rayo.Xi;
		double yi=a.rayo.Yi;
		double xf=a.rayo.Xf;
		double yf=a.rayo.Yf;

		double xxf,yyf;

		xxf=xi;
		yyf=yi;

		while((xxf>=0)&&(xxf<=1000)&&(yyf>=0)&&(yyf<=1000)){
			xxf=xxf+xf;
			yyf=yyf+yf;
		}

		Double Xxi=new Double(xi);
		Double Yyi=new Double(yi);
		Double Xxf=new Double(xxf);
		Double Yyf=new Double(yyf);

		GoThere(Xxi.intValue(),Yyi.intValue());

		cortalinea(Xxi.intValue(),
			       Yyi.intValue(),
				   Xxf.intValue(),
			  	   Yyf.intValue(),
			  	   true);
	}

	public void DibujaRayo(Entidad a){
		double xi=a.rayo.Xi;
		double yi=a.rayo.Yi;
		double xf=a.rayo.Xf;
		double yf=a.rayo.Yf;

		double xxf,yyf;

		xxf=xi;
		yyf=yi;

		while((xxf>=0)&&(xxf<=1000)&&(yyf>=0)&&(yyf<=1000)){
			xxf=xxf+xf;
			yyf=yyf+yf;
		}

		Double Xxi=new Double(xi);
		Double Yyi=new Double(yi);
		Double Xxf=new Double(xxf);
		Double Yyf=new Double(yyf);

		linea(Xxi.intValue()/2,
			  Yyi.intValue()/2,
			  Xxf.intValue()/2,
			  Yyf.intValue()/2);
	}

//Para dibujar o trazar unpunto (x,y)
	public void TrazaPunto(Entidad a){
		Double x=new Double(a.punto.X);
		Double y=new Double(a.punto.Y);

		GoThere(x.intValue(),y.intValue());

		cortalinea(x.intValue(),
			 	   y.intValue(),
				   x.intValue(),
				   y.intValue(),
				   true);
	}

	public void DibujaPunto(Entidad a){
		Double x=new Double(a.punto.X);
		Double y=new Double(a.punto.Y);

		linea(x.intValue()/2,
			  y.intValue()/2,
			  x.intValue()/2,
			  y.intValue()/2);
	}

//Para dibujar o trazar un circulo como un poligono de 360 lados
	public void TrazaCirculo(Entidad a){
		Double x=new Double(a.circulo.X);
		Double y=new Double(a.circulo.Y);
		Double r=new Double(a.circulo.Radio);

		cortacirculo(r.intValue(),
				     x.intValue(),
				     y.intValue(),
				     0,360,
				     true);
	}

	public void DibujaCirculo(Entidad a){

		Double x=new Double(a.circulo.X);
		Double y=new Double(a.circulo.Y);
		Double r=new Double(a.circulo.Radio);

		circulo(r.intValue()/2,
			  x.intValue()/2,
			  y.intValue()/2,
			  0,360);
	}

//Para dibujar o trazar un arco como un poligono de n lados, con 0<n<360
	public void TrazaArco(Entidad a){

		Double x=new Double(a.arco.X);
		Double y=new Double(a.arco.Y);
		Double r=new Double(a.arco.Radio);
		Double sa=new Double(a.arco.SAngle);
		Double ea=new Double(a.arco.EAngle);

		if(sa.intValue()<ea.intValue())
			cortacirculo(r.intValue(),
						 x.intValue(),
						 y.intValue(),
						 sa.intValue(),
						 (ea.intValue()-sa.intValue()),
						 true);
		else
			cortacirculo(r.intValue(),
						 x.intValue(),
						 y.intValue(),
						 sa.intValue(),
						 (360-sa.intValue()+ea.intValue()),
						 true);
	}

	public void DibujaArco(Entidad a){

		Double x=new Double(a.arco.X);
		Double y=new Double(a.arco.Y);
		Double r=new Double(a.arco.Radio);
		Double sa=new Double(a.arco.SAngle);
		Double ea=new Double(a.arco.EAngle);

		if(sa.intValue()<ea.intValue())
			circulo(r.intValue()/2,
				  x.intValue()/2,
				  y.intValue()/2,
				  sa.intValue(),
				  (ea.intValue()-sa.intValue()));
		else
			circulo(r.intValue()/2,
				  x.intValue()/2,
				  y.intValue()/2,
				  sa.intValue(),
				  (360-sa.intValue()+ea.intValue()));
	}


/***********************************************************************************
 ***********************************************************************************
					Metodos de decodificación del archivo
 ***********************************************************************************
 ***********************************************************************************/

	//para formar un arco como un polígono de n lados/grados, con 0<n<360
	//y dibujar la secuencia de lineas de los lados
	public void circulo(int r,int x,int y,int i,int l){

		int rad=r;
		int xi,xf,yi,yf;
		Double Xi,Xf,Yi,Yf;
		int cx=x;  //posicion de inicio x
		int cy=y;  //posicion de inicio y
		int n=0;

		for(int j=i;j<=(i+l);j++){

			n=j;

			Xi=new Double(rad*(Math.cos((n)*Math.PI/180)));
			xi=cx+Xi.intValue();
			Yi=new Double(rad*(Math.sin((n)*Math.PI/180)));
			yi=cy+Yi.intValue();
			Xf=new Double(rad*(Math.cos((n+1)*Math.PI/180)));
			xf=cx+Xf.intValue();
			Yf=new Double(rad*(Math.sin((n+1)*Math.PI/180)));
			yf=cy+Yf.intValue();

			linea(xi,yi,xf,yf);
		}
	}

	//para formar un arco como un polígono de n lados/grados, con 0<n<360
	//y trazar la secuencia de lineas de los lados
	public void cortacirculo(int r,int x,int y,int i,int l,boolean cortar){

		int rad=r;
		int xi,xf,yi,yf;
		Double Xi,Xf,Yi,Yf;
		int cx=x;
		int cy=y;
		int n=0;
		boolean Go=false;

		for(int j=i;j<=(i+l);j++){

			n=j;

			Xi=new Double(rad*(Math.cos((n)*Math.PI/180)));
			xi=cx+Xi.intValue();
			Yi=new Double(rad*(Math.sin((n)*Math.PI/180)));
			yi=cy+Yi.intValue();
			Xf=new Double(rad*(Math.cos((n+1)*Math.PI/180)));
			xf=cx+Xf.intValue();
			Yf=new Double(rad*(Math.sin((n+1)*Math.PI/180)));
			yf=cy+Yf.intValue();

			//busca el inicio del primer lado del polígono
			if(!Go){
				GoThere(xi,yi);
				Go=true;
			}

			cortalinea(xi,yi,xf,yf,cortar);

			//chequea si se ordeno detener
			if(cutter.seguir==1){
				j=(i+l+1);
			}
		}
	}

	//metodo para dibujar una linea entre dos puntos (x,y)
	public void linea(int ix,int iy,int fx,int fy){
		linealizar(ix,(500-iy),fx,(500-fy));
		dibujar();
	}

	//metodo para trazar una linea entre dos puntos (x,y)
	public void cortalinea(int ix,int iy,int fx,int fy,boolean cortar){
		linealizarCut(ix,iy,fx,fy);
		cortar(cortar);
		AquiX=fx;
		AquiY=fy;

	}

	//metodo para llevar la cabeza de corte a la posición (0,0)
	public void Inicial(){
		cutter.Inicial();
	}

	//metodo para enviar el código de inicio de transmisión de archivo
	public void Empezar(){
		cutter.Empezar();
	}

	//metodo para enviar el código de final de transmisión de archivo
	public void Terminar(){
		cutter.Terminar();
	}

	//método que dibuja una linea recta de acuerdo a los valores
	//establecidos en el metodo linealizar
	public void dibujar(){

		//limpia el contador y el acumulador
		counter=0;
		acc=0;

		//si la diferencia entre los puntos inicial y final
		//es menor en la distancia vectorial "y"
		if(isDeltaDY){

			//mientras no se haya desplazado la distancia en "y"
			for(int i=0;i<delta;i++){

				can.x2+=(pendiente*nx);//establece "n" puntos en "x"
				can.paint(can.getGraphics());//dibuja
				can.avanzar();//avanza para dibujar la siguiente porción de linea

				can.y2+=ny;//establece 1 punto en "y"
				can.paint(can.getGraphics());//dibuja
				can.avanzar();//avanza para dibujar la siguiente porción de linea

				acc+=ajuste;//suma al acumulador el ajuste de inclinación

				//cuando el acumulador es mayor que el ajuste de inclinación
				if(acc>delta){
					counter++;//incrementa en 1 el contador
					acc-=delta;//al acumulador le resta el desplazamiento
					can.x2+=nx;//establece 1 punto en x
					can.paint(can.getGraphics());//dibuja
				}
			}

			//si no se ajustaron todos los puntos necesarios
			if(counter<ajuste){
				can.x2+=nx;//establece 1 punto en x
				can.paint(can.getGraphics());//dibuja
			}
		}

		//si la diferencia entre los puntos inicial y final
		//es menor en la distancia vectorial "x"
		//hace lo mismo que en el caso anterior pero
		//invirtiendo las órdenes en "y" y "x"
		else{

			for(int i=0;i<delta;i++){

				can.x2+=nx;
				can.paint(can.getGraphics());
				can.avanzar();

				can.y2+=(pendiente*ny);
				can.paint(can.getGraphics());
				can.avanzar();

				acc+=ajuste;

				if(acc>delta){
					counter++;
					acc-=delta;
					can.y2+=ny;
					can.paint(can.getGraphics());
				}
			}

			if(counter<ajuste){
				can.y2+=ny;
				can.paint(can.getGraphics());
			}
		}
	}

	//método que traza una linea recta de acuerdo a los valores
	//establecidos en el metodo linealizarCut
	//es el equivalente al método Dibujar, pero para una acción de corte
	//efectua el mismo procedimiento utilizando una instancia de
	//"CutManager2" en vez de una "nCan2"
	public void cortar(boolean cortar){

		counter=0;
		acc=0;
		String Horizontal="ESTE";
		String Vertical="NORTE";

		if(isDeltaDY){

			if(nx<0){Horizontal="OESTE";}
			if(ny<0){Vertical="SUR";}

			for(int i=0;i<delta;i++){

				can.x2+=(pendiente*nx);
				cutter.cut(Horizontal,cortar,pendiente);
				can.avanzar();

				can.y2+=ny;
				cutter.cut(Vertical,cortar,1);
				can.avanzar();

				acc+=ajuste;

				if(acc>delta){
					counter++;
					acc-=delta;
					can.x2+=nx;
					cutter.cut(Horizontal,cortar,1);
				}

				if(cutter.seguir==1){
					i=delta;
					counter=ajuste;
				}
			}

			if(counter<ajuste){
				can.x2+=nx;
				cutter.cut(Horizontal,cortar,1);
			}
		}

		else{

			if(nx<0){Horizontal="OESTE";}
			if(ny<0){Vertical="SUR";}

			for(int i=0;i<delta;i++){

				can.x2+=nx;
				cutter.cut(Horizontal,cortar,1);
				can.avanzar();

				can.y2+=(pendiente*ny);
				cutter.cut(Vertical,cortar,pendiente);
				can.avanzar();

				acc+=ajuste;

				if(acc>delta){
					counter++;
					acc-=delta;
					can.y2+=ny;
					cutter.cut(Vertical,cortar,1);
				}

				if(cutter.seguir==1){
					i=delta;
					counter=ajuste;
				}
			}

			if(counter<ajuste){
				can.y2+=ny;
				cutter.cut(Vertical,cortar,1);
			}
		}
	}

	//método que establece los parámetros para "Dibujar"
	public void linealizar(int a,int b,int c,int d){

		//pone los punteros de línea en el punto inicial
		can.setVars(a,b,a,b);

		ajuste=0;//limpia el valor de ajuste

		//obtiene el desplazamiento en "x" entre los puntos
		//así como la dirección
		dX=Math.abs(c-a);
		if(c<a){nx=-1;}
		else{nx=1;}

		//obtiene el desplazamiento en "y" entre los puntos
		//así como la dirección
		dY=Math.abs(d-b);
		if(d<b){ny=-1;}
		else{ny=1;}

		//fija los parámetros
		if(dX==0){
			pendiente=0;
			delta=dY;
			isDeltaDY=true;
		}

		//fija los parámetros
		else if(dY==0){
			pendiente=0;
			delta=dX;
			isDeltaDY=false;
		}

		//fija los parámetros
		else{
			if(dX>dY){
				pendiente=dX/dY;
				ajuste=dX%dY;
				delta=dY;
				isDeltaDY=true;
			}
			else{
				pendiente=dY/dX;
				ajuste=dY%dX;
				delta=dX;
				isDeltaDY=false;
			}
		}
	}

	//Es el equivalente de "Linealizar" para una acción de corte
	public void linealizarCut(int a,int b,int c,int d){

		cutter.setVars(a,b,a,b);

		ajuste=0;

		dX=Math.abs(c-a);
		if(c<a){nx=-1;}
		else{nx=1;}

		dY=Math.abs(d-b);
		if(d<b){ny=-1;}
		else{ny=1;}

		if(dX==0){
			pendiente=0;
			delta=dY;
			isDeltaDY=true;
		}

		else if(dY==0){
			pendiente=0;
			delta=dX;
			isDeltaDY=false;
		}

		else{
			if(dX>dY){
				pendiente=dX/dY;
				ajuste=dX%dY;
				delta=dY;
				isDeltaDY=true;
			}
			else{
				pendiente=dY/dX;
				ajuste=dY%dX;
				delta=dX;
				isDeltaDY=false;
			}
		}
	}


	//método para almacenar los parámetros de una entidad tipo POINT
	public void punto(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.punto.X=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.punto.Y=Double.parseDouble(elemento);
				done = true;
			}
		}
	}

	//método para almacenar los parámetros de una entidad tipo ARC
	public void arco(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.arco.X=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.arco.Y=Double.parseDouble(elemento);
			}

			else if(elemento.equals("40")){
				elemento = st.nextToken();
				prov.arco.Radio=Double.parseDouble(elemento);
			}

			else if(elemento.equals("50")){
				elemento = st.nextToken();
				prov.arco.SAngle=Double.parseDouble(elemento);
			}

			else if(elemento.equals("51")){
				elemento = st.nextToken();
				prov.arco.EAngle=Double.parseDouble(elemento);
				done = true;
			}
		}
	}

	//método para almacenar los parámetros de una entidad tipo CIRCLE
	public void circulo(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.circulo.X=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.circulo.Y=Double.parseDouble(elemento);
			}

			else if(elemento.equals("40")){
				elemento = st.nextToken();
				prov.circulo.Radio=Double.parseDouble(elemento);
				done = true;
			}
		}
	}

	//método para almacenar los parámetros de una entidad tipo RAY
	public void rayo(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.rayo.Xi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.rayo.Yi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("11")){
				elemento = st.nextToken();
				prov.rayo.Xf=Double.parseDouble(elemento);
			}

			else if(elemento.equals("21")){
				elemento = st.nextToken();
				prov.rayo.Yf=Double.parseDouble(elemento);
				done = true;
			}
		}
	}

	//método para almacenar los parámetros de una entidad tipo LWPOLYLINE
	//para este caso especial, gurda un arreglo de líneas como
	//si fueran entidades diferentes
	public void polilineaLW(){

		boolean done = false;


		while(!elemento.equals("90"))
			elemento = st.nextToken();
		elemento = st.nextToken();
		int n=Integer.parseInt(elemento);

		while(!elemento.equals("70"))
			elemento = st.nextToken();
		elemento = st.nextToken();
		int closed=Integer.parseInt(elemento);

		int m=n;

		if(closed>0)
			m++;

		double puntos[][]=new double[m][5];

		for (int i=0;i<n;i++){

			while(!elemento.equals("10"))
				elemento = st.nextToken();
			elemento = st.nextToken();

			puntos[i][0]=Double.parseDouble(elemento);

			while(!elemento.equals("20"))
				elemento = st.nextToken();
			elemento = st.nextToken();

			puntos[i][1]=Double.parseDouble(elemento);

			elemento = st.nextToken();

			if(elemento.equals("42")){
				elemento = st.nextToken();
				puntos[i][4]=Double.parseDouble(elemento);
			}
			else
				puntos[i][4]=0;
		}

		if(m>n){
			puntos[m-1][0]=puntos[0][0];
			puntos[m-1][1]=puntos[0][1];
		}

		for (int i=0;i<(m-1);i++){
			puntos[i][2]=puntos[i+1][0];
			puntos[i][3]=puntos[i+1][1];
		}

		for(int i=0;i<(m-1);i++){
			prov=new Entidad();
			if(puntos[i][4]==0){
				prov.linea=new LINE();
				prov.linea.Xi=puntos[i][0];
				prov.linea.Yi=puntos[i][1];
				prov.linea.Xf=puntos[i][2];
				prov.linea.Yf=puntos[i][3];
				ent.add(prov);
			}
			else{
				double dx2=Math.pow(puntos[i][2]-puntos[i][0],2);
				double dy2=Math.pow(puntos[i][3]-puntos[i][1],2);
				double d=Math.sqrt(dx2+dy2);

				double h=(puntos[i][4]*d)/2;

				double rad=(Math.pow(h,2)+Math.pow(d/2,2))/(2*h);

				System.out.print(h);
				System.out.print(d);
				System.out.print(rad);



			}
			prov=null;
		}
	}

	//método para almacenar los parámetros de una entidad tipo XLINE
	public void lineaX(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.lineaX.Xi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.lineaX.Yi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("11")){
				elemento = st.nextToken();
				prov.lineaX.Xf=Double.parseDouble(elemento);
			}

			else if(elemento.equals("21")){
				elemento = st.nextToken();
				prov.lineaX.Yf=Double.parseDouble(elemento);
				done = true;
			}
		}
	}

	//método para almacenar los parámetros de una entidad tipo LINE
	public void linea(){

		boolean done = false;

		while(!done){
			elemento = st.nextToken();

			if(elemento.equals("10")){
				elemento = st.nextToken();
				prov.linea.Xi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("20")){
				elemento = st.nextToken();
				prov.linea.Yi=Double.parseDouble(elemento);
			}

			else if(elemento.equals("11")){
				elemento = st.nextToken();
				prov.linea.Xf=Double.parseDouble(elemento);
			}

			else if(elemento.equals("21")){
				elemento = st.nextToken();
				prov.linea.Yf=Double.parseDouble(elemento);
				done = true;
			}
		}
	}
}


