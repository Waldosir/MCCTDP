package juegoBuscaminas;

public class Casillas {
	private boolean hayMina;
	private boolean casillaTapada;
	private int numero;
	
	
	public Casillas(){
		this.numero = 0;
		this.hayMina = false;
		this.casillaTapada = true;
		
	}
	
	public Casillas(boolean hayMina) {
		this.hayMina = hayMina;
		this.numero = 0;
		this.casillaTapada = true;
	}
	
	public boolean getHayMina() {
		return this.hayMina;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public boolean getCasillaTapada() {
		return this.casillaTapada;
	}
	
	public void setHayMina(boolean hayMina) {
		this.hayMina = hayMina;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setCasillaTapada(boolean casillaTapada) {
		this.casillaTapada = casillaTapada;
	}
	
	

}
