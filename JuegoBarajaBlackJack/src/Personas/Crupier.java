package Personas;

public class Crupier extends Jugador {
	
	public Crupier(String nombre, int numeroFichas) {
		super(nombre, numeroFichas);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String datos="\n";
		datos+= manoJugador();
		return datos;
	}
	
	public String cartaCrupier() {
		return this.getCartas(0).toString();
	}
	
	

}
