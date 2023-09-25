package Personas;

import java.util.ArrayList;

import Baraja.CartasPoker;

public class Jugador {
	
	private String nombre;
	private int numeroFichas;
	private boolean estadoEnJuego;
	private ArrayList<CartasPoker> cartas = new ArrayList();
	
	public Jugador(String nombre, int numeroFichas) {
		this.nombre = nombre;
		this.numeroFichas = numeroFichas;
		estadoEnJuego = true;
	}
	
	public boolean getEstadoEnJuego() {
		return estadoEnJuego;
	}
	
	public void setEstadoEnJuego(boolean estadoEnJuego) {
		this.estadoEnJuego =estadoEnJuego;
	}
	
	public void retirarCartas() {
		this.cartas.clear();
	}

	public int getNumeroFichas() {
		return numeroFichas;
	}

	public void setNumeroFichas(int numeroFichas) {
		this.numeroFichas = numeroFichas;
	}

	public CartasPoker getCartas(int pos) {
		return cartas.get(pos);
	}

	public void setCartas(Object object) {
		cartas.add((CartasPoker) object);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		String datos="";
		datos+="Cartas del jugador "+this.nombre+":\n";
		for(int i=0;i<cartas.size();i++) {
			datos+= cartas.get(i).toString()+"\n";
		}
		datos+="\nFichas actuales: "+this.numeroFichas+"\n";
		return datos;
	}

}
