package Baraja;

public class CartasPoker {
	private Simbolo simbolo;
	private ValorCarta valorCarta;
	
	public CartasPoker (ValorCarta valorCarta, Simbolo simbolo) {
		this.valorCarta = valorCarta;
		this.simbolo = simbolo;
	}
	
	public Simbolo getSimbolo() {
		return simbolo;
	}
	
	public ValorCarta getValorCarta() {
		return valorCarta;
	}
	
	public String toString() {
		return this.valorCarta + " de "+ this.simbolo;
	}
	

	
	 
	
}

