package enums;

public enum dificultad {
	//base, altura, dificultad
	principiante(8,8,"principiante"),facil(15,15,"facil"),normal(30,30,"normal"),dificil(50,50,"dificil");
	
	private int base;
	private int altura;
	private String nivel;
	
	private dificultad(int base,int altura,String nivel) {
		this.base = base;
		this.altura = altura;
		this.nivel = nivel;
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
	
}
