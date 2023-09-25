package Baraja;

public enum ValorCarta {
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5),
	SEIS(6),
	SIETE(7),
	OCHO(8),
	NUEVE(9),
	DIEZ(10),
	JOTAS(11),
	REINA(12),
	REY(13),
	AS(14);
	
	private int valorCarta;
	
	private ValorCarta(int valor) {
		this.valorCarta = valor;
	}
	
	public int getValorCarta() {
		return valorCarta;
	}
}
