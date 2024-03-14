import java.awt.*;
import javax.swing.*;
/** Clase que permite manipular y trazar dibujos en pantalla */
public class nCan2 extends JPanel{
 private int shape,lado;


 public final static int CIRCLE = 1, SQUARE = 2, CUADRICULA = 3;
	//Variables para trazar una linea entre dos puntos
	public int x1,y1,x2,y2;
	String color="amarillo";


   public nCan2()
   {
   	this.setBackground(Color.black);
	this.setForeground(Color.blue);
	this.requestFocus();

	}




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

	//Limpia la pantalla
	public void clean(){
	this.update(this.getGraphics());
	Apanelar(this.getGraphics());

	}



   public void draw( int s, int l )
      {
         shape = s;
   	     lado = l;
   	     repaint();
      }

	//Metodo de trazado
	public void paint(Graphics g){
		if(color=="amarillo"){g.setColor(Color.yellow);}//define el color
		else if(color=="verde"){g.setColor(Color.green);}
		else if(color=="rojo"){g.setColor(Color.red);}
		else{g.setColor(Color.white);}
		g.drawLine(x1,y1,x2,y2);//dibuja un linea
        Apanelar(g);
	}




public void Apanelar(Graphics g){
		 if ( shape == CIRCLE )
			         acircular(g);
			      else if ( shape == SQUARE )
			         atunalar(g);
				  else if( shape == CUADRICULA)
		      cuadricular(g);


	}



	public void cuadricular(Graphics g)    //una cuadricula simple
	   {

	  int abajo = 0;   int izq = 0;
	  		   for (int i = lado; i <= this.getWidth(); i++)
	  		   {
	  		   g.setColor(Color.blue);
	  		   g.drawLine(0,abajo,this.getWidth(),abajo);
	  		   g.drawLine(izq,0,izq,this.getHeight());
	  		   abajo += lado;
	  		   izq += lado;
	             }
	      }



public void acircular(Graphics g)
   {
   int centerx,centery;    centerx =  this.getWidth()/2;
   centery = this.getHeight()/2;   int vol= lado;
   g.setColor(Color.blue);
   g.drawLine(0,centery,this.getWidth() ,centery);    //mira
   g.drawLine(centerx, 0 , centerx, this.getHeight());
   centerx-=lado/2;   centery -=lado/2;

   for(int i = centerx; i >=0; i--)
      {
	  g.setColor(Color.blue);
   	  g.drawOval(centerx,centery,vol,vol);
	  vol+=lado;
	  centerx-=lado/2;
	  centery-=lado/2;
       }
	  }





   public void atunalar(Graphics g)
   {
   int centerx,centery;    centerx =  this.getWidth()/2;
   centery = this.getHeight()/2;   int vol= lado;
   g.setColor(Color.blue);
   g.drawLine(0,centery,this.getWidth() ,centery);    //mira
   g.drawLine(centerx, 0 , centerx, this.getHeight());

   centerx-=lado/2;     centery -=lado/2;
   for(int i = centerx; i >=0; i--)
      {
	  g.setColor(Color.blue);
   	  g.drawRect(centerx,centery,vol,vol);
	  vol+=lado;
	  centerx-=lado/2;
	  centery-=lado/2;
       }
	  }









}