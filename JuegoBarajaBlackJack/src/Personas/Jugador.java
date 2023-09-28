package Personas;

import java.util.ArrayList;

import Baraja.CartasPoker;

public class Jugador {
	
	private String nombre;
	private int numeroFichas;
	private boolean estadoEnJuego;
	private ArrayList cartas = new ArrayList();
	private int montoAApostar;
	
	public Jugador(String nombre, int numeroFichas) {//Constructor de jugador
		this.nombre = nombre;
		this.numeroFichas = numeroFichas;
		this.estadoEnJuego = true;
		this.montoAApostar=0;
	}
	public int getMontoAApostar() {//Ver si perdió o sigue jugando
		return this.montoAApostar;
	}
	
	public void setMontoAApostar(int montoAApostar) {//Ver si perdió o sigue jugando
		this.montoAApostar = montoAApostar ;
	}
	
	
	public int getNumeroDeCartasEnMano() { //Ver cantidad de cartas en su mano
		return cartas.size();
	}
	
	public boolean getEstadoEnJuego() {//Ver si perdió o sigue jugando
		return estadoEnJuego;
	}
	
	public void setEstadoEnJuego(boolean estadoEnJuego) {//Cambia su estado en el juego
		this.estadoEnJuego =estadoEnJuego;
	}
	
	public void retirarCartas() {//Retira sus cartas
		this.cartas.clear();
		if(this.numeroFichas>0) {//Si tiene como apostar puede volver a jugar
			this.estadoEnJuego = true;
		}
		this.montoAApostar=0;
		
	}

	public int getNumeroFichas() {//Saber fichas del jugador
		return numeroFichas;
	}

	public void darNumeroFichas(int numeroFichas) {//Dar fichas al jugador
		this.numeroFichas -= numeroFichas;
	}

	public Object getCartas(int pos) {//Saber las cartas de poker que tiene
		return cartas.get(pos);
	}

	public void tomarCarta(Object object) {
		cartas.add(object);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		String datos="Jugador |"+this.nombre+"| con fichas: "+this.numeroFichas;;

		return datos;
	}
	
	public String manoJugador() {
		String dato="Cartas del jugador "+this.nombre+":\n";
		for(int i=0;i<cartas.size();i++) {
			dato+= "->"+cartas.get(i).toString();
		}
		return dato;
	}

}
