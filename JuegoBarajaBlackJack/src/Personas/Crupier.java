package Personas;

import java.util.ArrayList;

public class Crupier extends Jugador {
	
	private static String nombreCrupier="Crupier";
	private static final int numeroFichas = 9000;

	
	
	public Crupier( ) {
		super(nombreCrupier, numeroFichas);
	}

	public int getNumeroFichas() {//Saber fichas del jugador
		return numeroFichas;
	}

	public String getNombre() {
		return nombreCrupier;
	}
	
	public String toString() {
		String datos="\n";
		datos+= manoJugador();
		return datos;
	}
	
	

}
