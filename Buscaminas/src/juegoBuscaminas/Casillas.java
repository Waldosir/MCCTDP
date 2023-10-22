package juegoBuscaminas;

public class Casillas {
	private boolean hayMina;//La mina como tal
	private boolean casillaTapada;//Si esta destapada o tapada la casilla
	private int numero; //Numero de minas alrededor
	private boolean bloquear; //Bloquear casilla ("?")
	private boolean marcaBomba; //Marca jugador si piensa que es una bomba
	
	public Casillas(boolean hayMina) { //Constructor que solo cambia si es una mina o no
		this.numero = 0;
		this.hayMina = hayMina;
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
		if(this.casillaTapada) {//Si esta tapada
			if(!(this.bloquear || this.marcaBomba)) { //Si no cumple que (esta bloqueada o tiene marca de bomba)
				return true;
			}
		}
		return false;
	}
	
	

}
