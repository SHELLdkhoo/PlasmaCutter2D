/** La clase entidades es la raíz de la lista de nodos con entidades del dibujo */
public class Entidades{

	//Puntero a la primera entidad de la lista
	Entidad first,x;

	Entidades(){}

	//Metodo para agregar un nuevo elemento a la lista
	public void add(Entidad a){
		x=null;
		if(first==null)
			first=a;
		else{
			x=first;
			while(x.next!=null)
				x=x.next;
			x.next=a;
		}
	}
}