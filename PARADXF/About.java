import  java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class About extends JPanel{

JPanel panel;
static JWindow acerca;

public About(){

ImageIcon logo= new ImageIcon("images/itcr.gif");
JLabel mesage = new JLabel("Este programa es propiedad del ITCR");
JLabel mesage2 = new JLabel("Double click AQUI para ocultar");

panel = new JPanel();
panel.setLayout(new BorderLayout());
setBackground(Color.green);
setLayout(new FlowLayout());
setVisible(true);
this.setLayout(new FlowLayout());
JLabel imagen = new JLabel(logo);
panel.add("North" ,mesage);
panel.add("Center",imagen);
panel.add("South",mesage2);
this.add(panel);
}




public void paintComponent( Graphics g )
   {
      super.paintComponent( g );

   }



public static void main(String s[]){
	 acerca = new JWindow(new JFrame());
	 acerca.getAccessibleContext();
     acerca.getContentPane().add(new About());
	 acerca.addMouseListener(
	         new MouseAdapter() {
	            public void mouseClicked(MouseEvent e)
	            {
                ocultar();

	            }});

     acerca.show();
     acerca.setSize(300,250);
     acerca.setVisible(true);
     acerca.setLocation(500,500);


}


public static void ocultar(){
	acerca.setVisible(false);
}






}