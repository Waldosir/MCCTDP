package enums;

public enum dificultad {
	//base, altura, dificultad
	principiante(8,8,"principiante",50,8,8+10),
	facil(15,15,"facil",450,20,20+10),
	normal(30,30,"normal",1350,45,45+10),
	dificil(50,50,"dificil",3350,100,100+10);
	
	/*Por nivel = 100
	 * Principiante: 50 puntos+ PorNivel = 150 final (8 minutos)
	 * Facil:150*3 + 100 = 450 + 100 puntos = 550 final (20 minutos) - Bono de 175 puntos
	 * Normal: 550 * 2 + bonus = 1100 +150puntos + 100 = 1250 + 100 = 1450 (45 minutos) - Bono de 212 puntos
	 * Dificil: 1350*2 + bonus = 2700 puntos +300 + 200 = 3450 (100 minutos) - Bono de 227 puntos
	*/
	private int base;
	private int altura;
	private String nivel;
	private int puntajeNivel;
	private int tiempoMinimo, tiempoMaximo;
	
	private dificultad(int base,int altura,String nivel, int puntajeNivel, int tiempoMinimo, int tiempoMaximo) {
		this.base = base;
		this.altura = altura;
		this.nivel = nivel;
		this.puntajeNivel = puntajeNivel;
		this.tiempoMinimo = tiempoMinimo;
		this.tiempoMaximo =  tiempoMaximo;
	}
	
	
	
	public int getTiempoMinimo() {
		return tiempoMinimo;
	}

	public int getTiempoMaximo() {
		return tiempoMaximo;
	}

	public String toString() {
		return this.nivel;
	}
	

	public int getBaseTabla() {
		return this.base;
	}
	
	public int getAlturaTabla() {
		return this.altura;
	}
	
	public int getPuntajeNivel() {
		return this.puntajeNivel;
	}
	
}
