package Constantes;

public enum CM {
LEER("leer"),  IMPRIME("imprime"),SI("si"), SINO("sino"),MIENTRAS("mientras");
	
	private String comando;
	
	private CM(String comando) {
		this.comando = comando;
	}
	public String toString() {
		return this.comando;
	}
	
	static public boolean isComando(String linea) {
		for(int i=0; i<CM.values().length;i++) {
			if(linea.contains(CM.values()[i].toString())) {
				return true;
			}
		}
		return false;
			
	}

	
	
	
}
