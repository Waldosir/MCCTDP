package Baraja;

public class CartasPoker {
	private Simbolo simbolo; //El palo de la carta
	private ValorCarta valorCarta; //Cantidad en numero de la carta
	
	public CartasPoker (ValorCarta valorCarta, Simbolo simbolo) { //Constructor
		this.valorCarta = valorCarta;
		this.simbolo = simbolo;
	}
	
	public Simbolo getSimbolo() { //Obtiene el simbolo
		return simbolo;
	}
	
	public ValorCarta getValorCarta() { //Obtiene el valor textual de la carta
		return valorCarta;
	}
	
	public String toString() { //Da la carta -JOTA DE CORAZON-
		return this.valorCarta + " de "+ this.simbolo+"\n";

	}
	

	
	 
	
}

