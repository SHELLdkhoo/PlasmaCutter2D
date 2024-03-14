/** Una entidad es un nodo para formar una lista con las entidades del dibujo */
public class Entidad{

	//Cada nodo puede tomar la forma de las siguientes entidades
	ARC arco;
	CIRCLE circulo;
	ELLIPSE elipse;
	LINE linea;
	LWPOLYLINE polilineaLW;
	POINT punto;
	RAY rayo;
	XLINE lineaX;
	Entidad next;

	Entidad(){}

	//Metodo para saber el tipo de nodo que es
	String getType(){
		if(arco!=null)return("ARC");
		else if(circulo!=null)return("CIRCLE");
		else if(elipse!=null)return("ELLIPSE");
		else if(linea!=null)return("LINE");
		else if(polilineaLW!=null)return("LWPOLYLINE");
		else if(punto!=null)return("POINT");
		else if(rayo!=null)return("RAY");
		else if(lineaX!=null)return("XLINE");
		else return(null);
	}
}