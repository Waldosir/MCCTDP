package Constantes;

public enum LITVAR {
ENTERO("entero"), REAL("real"), TEXTO("texto"), IGUAL("=");

		
	private String declaracion;
		
	private LITVAR(String declaracion) {
		this.declaracion = declaracion;
	}
	public String toString() {
		return this.declaracion;
	}
	static public boolean isDeclaracion(String linea) {
		if(CICLO.isCiclo(linea)) {
			return false;
		}
		for(int i=0; i<LITVAR.values().length;i++) {
			if(linea.contains(LITVAR.values()[i].toString())) {
				return true;
			}
		}
		return false;
				
		}

}
