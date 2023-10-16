package datosUsuario;

import enums.dificultad;

public class Usuario {
	private String nombre;
	private int puntaje;
	private dificultad dificultadU;
	
	public Usuario(String nombre, int puntaje, dificultad dificultadU) {
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.dificultadU = dificultadU;
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

	public dificultad getDificultadU() {
		return dificultadU;
	}

	public void setDificultadU(dificultad dificultadU) {
		this.dificultadU = dificultadU;
	}
	
	
	
	

}
