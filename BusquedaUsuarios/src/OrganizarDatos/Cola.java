package OrganizarDatos;


public class Cola <T>{
	
	private Nodo<T> inicio, fondo;
	
	public Cola() {
		this.inicio = null;
		this.fondo = null;
	}
	
	public boolean esVacia() {
		return this.inicio == null;
	}
	
	public void agregar(T valor) {
		Nodo<T> nuevo = new Nodo<T>(valor);
		
		if(esVacia()) {
			this.inicio = nuevo;
			this.fondo = nuevo;
		} else {
			fondo.setEnlace(nuevo);
			fondo = nuevo;
		}
	}
	
	public void imprimir() {
		imprimeRecur(inicio);
	}
	
	private void imprimeRecur(Nodo<T> actual) {
		if (actual == null)
			return;
		System.out.println(actual.getDato());
		imprimeRecur(actual.getEnlace());
	}
	
	public void retirar() {
		if (!esVacia()) {
			if(inicio == fondo) {
				inicio = null;
				fondo = null;
			}else
				inicio = inicio.getEnlace();
		}
	}
	
	public T primero() {
		if(!esVacia())
			return inicio.getDato();
		System.out.println("La cola est� vacia");
		return null;
	}
	
	public boolean buscar(T valor) {
		if (esVacia())
			return false;
		return buscarRecur(inicio, valor);
	}
	
	private boolean buscarRecur(Nodo<T> actual, T valor) {
		if (actual == null)
			return false;
		if (actual.getDato().equals(valor))
			return true;
		return buscarRecur(actual.getEnlace(), valor);
	}
	
	//Buscar el ultimo elmento en la cola.
	public T fondo() {
		T dato = fondo.getDato();
		return dato;
	}
	
	//Eliminar un elemento en la cola por su valor.
	public void remover(T valor) {
		if(buscar(valor)) {
			Nodo<T> n1= inicio;
			while(true) {
				if(n1.getDato().equals(valor)) {
					inicio = n1.getEnlace();
					return;
				}
				else if(n1.getEnlace().getDato().equals(valor)) {
					break;
				}
				n1 = n1.getEnlace();	
			}
			n1.setEnlace(n1.getEnlace().getEnlace());
		}
		
	}
	
	//Buscar un elemento en la cola y si lo encuentra modifica su valor
	public void editar(T valor, T nuevo) {
		if(buscar(valor)) {
			Nodo<T> n1= inicio;
			while(true) {
				if(n1.getDato().equals(valor)) {
					break;
				}
				n1 = n1.getEnlace();
				
			}
			n1.setElemento(nuevo);
		}else {
			//System.out.println("No se encuentra el numero en la pila");
		}
		
	}
}
