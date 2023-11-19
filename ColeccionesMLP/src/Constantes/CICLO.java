package Constantes;

public enum CICLO {
	MAYOR(">"), MAYORIGUAL(">="), MENOR("<"), MENORIGUAL("<="),
	DISTINTO("<>"),IGUALCOMPARA("==");

	
	private String condicion;
		
	private CICLO(String condicion) {
		this.condicion = condicion;
	}
	public String toString() {
		return this.condicion;
	}
	static public boolean isCiclo(String linea) {
		for(int i=0; i<LITVAR.values().length;i++) {
			if(linea.contains(CICLO.values()[i].toString())) {
				return true;
			}
		}
		return false;
				
		}

}
