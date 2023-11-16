package datosUsuario;

import enums.dificultad;

public class Usuario {
	private String nombre;//Nombre del usuario
	private int puntaje;//Puntaje
	private dificultad dificultadU; //Dificultad de la persona
	private boolean condicionJuego; //Si sigue en juego o no
	private int niveles; //Nivel
	private int vidas; //Vidas
	private boolean hacerTrampa;
	
	public Usuario(String nombre, dificultad dificultadU) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.dificultadU = dificultadU;
		this.condicionJuego = true;
		this.niveles = 0; //Nivel 0.
		this.vidas = 1; //3 Vidas
		this.hacerTrampa = true;
	}
	
	public Usuario(String nombre, int puntaje) {
		this.nombre = nombre;
		this.puntaje =puntaje;
		this.dificultadU = dificultad.facil;//Dificultad estándar
	}
	
	
	
	public boolean getHacerTrampa() {
		return hacerTrampa;
	}

	public int getvida() {
		return this.vidas;
	}
	
	public void restarVida() {
		this.vidas --;
	}
	
	
	public int getNivel() {
		return this.niveles;
	}
	
	public void sumarNivel(int nivel) {
		this.niveles += nivel;
	}

	public boolean getCondicionJuego() {
		return this.condicionJuego;
	}
	
	public void setCondicionJuego(boolean condicionJuego) {
		this.condicionJuego = condicionJuego;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return this.puntaje;
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
		if(this.condicionJuego) {//Cuando esta vivo
			return "Turno del jugador "+this.nombre+" --> vidas: "+this.vidas+
					"\nNivel: "+this.niveles +" - dificultad: "+this.dificultadU+"\n";
		}else {//Cuando no esta vivo
			return this.nombre + " - puntaje--> "+this.puntaje;
		}
		
	}
	
	
	
	

}
