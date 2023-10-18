package datosUsuario;

import enums.dificultad;
import juegoBuscaminas.TablaBuscaminas;

public class Usuario {
	private String nombre;//Nombre del usuario
	private int puntaje;//Puntaje
	private dificultad dificultadU; //Dificultad de la persona
	private boolean condicionJuego;
	private TablaBuscaminas tabla;
	private int niveles;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.dificultadU = null;
		this.condicionJuego = true;
		this.niveles = 0;
	}
	
	public int getNivel() {
		return this.niveles;
	}
	
	public void setNivel(int nivel) {
		this.niveles = nivel;
	}
	
	public void sumarNivel() {
		this.niveles += 1;
	}
	
	public TablaBuscaminas getTabla() {
		return this.tabla;
	}
	
	public void setTablaBuscaminas(TablaBuscaminas tabla) {
		this.tabla = tabla;
	}
	
	public boolean getCondicionJuego() {
		return this.condicionJuego;
	}
	
	public void setCondicionJuego(boolean condicionJuego) {
		this.condicionJuego = condicionJuego;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void sumarPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}

	public dificultad getDificultadU() {
		return dificultadU;
	}

	public void setDificultadU(dificultad dificultadU) {
		this.dificultadU = dificultadU;
	}
	
	public String toString() {
		return this.nombre+" --> nivel "+this.niveles;
	}
	
	
	
	

}
