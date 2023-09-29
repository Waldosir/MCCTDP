package Baraja;

public enum ValorCarta { //Enumeracion del simbolo con su valor en numero
	AS(1),
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
	REY(13);
	
	
	private int valorCarta; //Valor en numeros enteros
	
	private ValorCarta(int valor) { //Constructor ENUM
		this.valorCarta = valor;
	}
	
	public int getNumeroEnteroCarta() {//Regresa el valor en enteros
		return valorCarta;
	}
}
