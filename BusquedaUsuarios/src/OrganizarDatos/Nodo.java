package OrganizarDatos;

public class Nodo <T>{
	T elemento;
	Nodo<T> enlace;
	
	public Nodo(T elemento) {
		this.elemento = elemento;
		this.enlace = null;
	}
	
	public Nodo(T elemento, Nodo<T> enlace) {
		this.elemento = elemento;
		this.enlace = enlace;
	}
	
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	public T getDato() {
		return this.elemento;
	}
	
	
	public Nodo<T> getEnlace(){
		return this.enlace;
	}
	
	public void setEnlace(Nodo<T> enlace) {
		this.enlace = enlace;
	}

	
	
	
	
}
