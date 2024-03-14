import java.awt.*;
import javax.swing.*;

public class CustomPanel extends JPanel {
   public final static int CIRCLE = 1, SQUARE = 2, CUADRICULA = 3;
   private int shape,lado;
   private String nombre;

    
	public CustomPanel(String name)
	{
	this.setBackground(Color.black);
	this.setForeground(Color.green);
	nombre = name;
	this.add(new JLabel(nombre));

	}

   public CustomPanel()
   {
   	this.setBackground(Color.red);
	setDefaultLayout();
   }



public void setDefaultLayout()
{
	this.setLayout(new BorderLayout());
	this.setSize(10,10);
}


public String nombre()
{
return nombre;

}




   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );

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
   g.drawLine(0,centery,this.getWidth() ,centery);    //mira 
   g.drawLine(centerx, 0 , centerx, this.getHeight());	  
   centerx-=lado/2;   centery -=lado/2;
   
   for(int i = centerx; i >=0; i--)
      {
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
   
   g.drawLine(0,centery,this.getWidth() ,centery);    //mira 
   g.drawLine(centerx, 0 , centerx, this.getHeight());	  

   centerx-=lado/2;     centery -=lado/2;
   for(int i = centerx; i >=0; i--)
      {
   	  g.drawRect(centerx,centery,vol,vol);  
	  vol+=lado;
	  centerx-=lado/2;
	  centery-=lado/2;
       }
	  }
   
   
   
   
   
   // g.drawOval(0,0,vol,vol);   //esto es una textura muy buena
   //	 vol += lado; 
   
   
   
   
   
   
   
   
   
   
   
   
   public void draw( int s, int l )
   {
      shape = s;
	  lado = l;
      repaint();
   }
}
