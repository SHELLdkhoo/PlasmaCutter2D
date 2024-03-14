/** Esta clase invoca a la ventana estándar de abrir archivo de Windows */
import java.awt.*;

public class abrir2 extends FileDialog{

	public abrir2(Frame f){

		super(f,"Cargar un archivo .NC o .GC");
		setFile("*.DXF" );
		setSize(400,250);
		show();
	}
}