package Constantes;

public enum OPERADOR {
PRIMERO(new String[]{"^"},3),
SEGUNDO(new String[]{"*","/"},2),
TERCERO(new String[]{"+","-"},1);
	
	public final String[] valor;
	public final int posicion;
	
	private OPERADOR(String[] valor, int jerarquia) {
		this.valor = valor;
		this.posicion = jerarquia;
	}
	
	public static int getPosicion(String caracter) {
		int pos = 0;
		for(int i=0;i<OPERADOR.values().length;i++) {
			pos = OPERADOR.values()[i].valor.length;
			for(int j=0;j<pos;j++) {
				if(OPERADOR.values()[i].valor[j].equals(caracter)) {
					return OPERADOR.values()[i].posicion;
				}
			}
		}
		System.out.println("No se encontro valor. Retornara cero");
		return 0;
	}
	
	public static boolean isOperador(String caracter) {
		int pos = 0;
		for(int i=0;i<OPERADOR.values().length;i++) {
			pos = OPERADOR.values()[i].valor.length;
			for(int j=0;j<pos;j++) {
				if(OPERADOR.values()[i].valor[j].equals(caracter)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static boolean isMayor(String caracter1, String caracter2) {
		return getPosicion(caracter1)>getPosicion(caracter2);
	}
	
	
	/*
	public String toString(int valor) {
		if(valor == 0) {
			return valor1;
		}
		if(valor==1) {
			return valor2;
		}
		
		return null;
	}
	*/
	
	
}
