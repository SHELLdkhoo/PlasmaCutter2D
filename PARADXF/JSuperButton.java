import javax.swing.*;
import java.awt.*;


public class JSuperButton extends JButton{
JButton button;


public JSuperButton(String name,String fija, String animada){
     super(name);
	ImageIcon fondo= new ImageIcon(fija);
	ImageIcon frente =new ImageIcon (animada);
		this.setPressedIcon(fondo);
		this.setRolloverIcon(frente);
		this.setRolloverEnabled(true);
		this.setToolTipText("Este es un boton Rollover del L&F marcel");
		return;

	}



public JSuperButton(String name){

        super(name);
	    this.setBackground(new Color(155, 200, 255));
	    this.setToolTipText("este es un boton simple del L&F marcel");
        return;

}



}