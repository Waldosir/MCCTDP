package enums;

import java.util.Arrays;

public enum dificultad {
	principiante(8,"principiante"),facil(15,"facil"),normal(30,"normal"),dificil(50,"dificil");
	
	private int tamano;
	private String nivel;
	
	private dificultad(int tamano,String nivel) {
		this.tamano = tamano;
		this.nivel = nivel;
	}
	
	public String toString() {
		return this.nivel;
	}
	
	public boolean[][] getTabla() {
		boolean[][] tabla = new boolean[this.tamano][this.tamano];
		Arrays.fill(tabla, false);
		return tabla;
	}
	
	public int getLargoTabla() {
		return this.tamano;
	}
	
}
