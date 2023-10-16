package juegoBuscaminas;

public class Casillas {
	private boolean hayMina;
	private boolean casillaTapada;
	private int numero;
	private boolean bloquear;
	private boolean marcaBomba;
	
	
	public Casillas(){
		this.numero = 0;
		this.hayMina = false;
		this.casillaTapada = true;
		this.bloquear = false;
		this.marcaBomba = false;
	}
	
	public Casillas(boolean hayMina) {
		this.hayMina = hayMina;
		this.numero = 0;
		this.casillaTapada = true;
		this.bloquear = false;
		this.marcaBomba = false;
	}
	
	public boolean getMarcaBomba() {
		return this.marcaBomba;
	}
	
	
	public void setMarcaBomba(boolean marca) {
		this.bloquear = marca;
		this.marcaBomba = marca;
	}
	

	
	public boolean getBloquear() {
		return this.bloquear;
	}
	
	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
		if(bloquear = false) {
			this.marcaBomba = false;
		}
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
		if(this.casillaTapada==false) {
			this.bloquear = false;
			this.marcaBomba= false;
		}
	}
	
	

}
