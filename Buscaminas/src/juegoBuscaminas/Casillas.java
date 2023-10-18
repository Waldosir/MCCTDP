package juegoBuscaminas;

public class Casillas {
	private boolean hayMina;//La mina como tal
	private boolean casillaTapada;//Si esta destapada o tapada la casilla
	private int numero; //Numero de minas alrededor
	private boolean bloquear; //Bloquear casilla ("?")
	private boolean marcaBomba; //Marca jugador si piensa que es una bomba
	
	
	public Casillas(){ //Constructor casilla vacia
		this.numero = 0;
		this.hayMina = false;
		this.casillaTapada = true;
		this.bloquear = false;
		this.marcaBomba = false;
	}
	
	public Casillas(boolean hayMina) { //Constructor mina
		this.hayMina = hayMina;
		this.numero = 0;
		this.casillaTapada = true;
		this.bloquear = false;
		this.marcaBomba = false;
	}
	
	public boolean getMarcaBomba() {
		return this.marcaBomba;
	}
	
	//Marcar tambien bloquea
	public void setMarcaBomba(boolean marca) {
		this.bloquear = marca;
		this.marcaBomba = marca;
	}
	

	
	public boolean getBloquear() {
		return this.bloquear;
	}
	
	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
		if(bloquear = false) { //Si el usuario desbloquea una marca de bomba
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
		if(this.casillaTapada==false) { //Si destapa de casualidad una casilla marcada
			this.bloquear = false;
			this.marcaBomba= false;
		}
	}
	
	public boolean condicionColocarMarca() {
		if(this.casillaTapada) {
			if(!(this.bloquear || this.marcaBomba)) {
				return true;
			}
		}
		return false;
	}
	
	

}
