package datos;

public class Tabla {
	//Valor de fila, columna y datos de tabla
	private int fila,columna;
	private boolean[][] datos;
	
	public Tabla(int fila, int columna, boolean datos[][]) { //Constructor para introducir datos
		this.fila = fila;
		this.columna = columna;
		this.datos = new boolean [fila][columna];
		
		for(int i=0;i<fila;i++) {//Ingreso de datos
			for(int j=0;j<columna;j++) {
				this.datos[i][j] = datos[i][j];
			}
		}
	}
	
	public Tabla(int fila, int columna) {//Constructor sin tabla de datos
		this.fila = fila;
		this.columna = columna;
	}
	
	//Ingresar nueva tabla
	public void setDatos(boolean[][] datos) {
		this.datos = datos;
	}
	
	//Obtener datos de la tabla
	public boolean[][] getDatos() {
		return datos;
	}
	
	//Tabla generada con los datos de la tabla
	public String toString(){		
		String FormaTabla = "   ";//Espacio entre los 0 del inicio de la tabla
		for(int i=0;i<this.columna;i++) {//Hacer indicadores de columnas
			if(i<10) {
				FormaTabla +=i+"  ";//Caso unidades
			}else {
				FormaTabla +=i+" ";//Caso decenas
			}
			
		}
		FormaTabla +="\n";//Salto de linea
		
		for(int i=0;i<this.fila;i++) { //For de filas
			if(i<10) {//La columna inicial
				FormaTabla  += i+"  ";//Unidades
			}else {
				FormaTabla +=i+" ";//Decenas
			}
			
			for(int j=0;j<this.columna;j++)//For de columnas
			{
				if(this.datos[i][j]) { //Caso donde organismos esten vivos
					FormaTabla +="X  ";
				}
				else {//Caso donde organismos esten muertos
					FormaTabla+="-  ";
				}
			}
			FormaTabla+="\n";//Salto de linea
		}
		
		return FormaTabla;
		
	}
	
	public boolean[][] TablaNueva() {//Algoritmo para verificar organismos vivos o muertas
		boolean[][] Newdatos  = new boolean[this.fila][this.columna];//Colocar tabla de datos
		
		for(int i =0;i<this.fila;i++) {//For Fila
			for(int j=0;j<this.columna;j++) {//For Columna
				Newdatos[i][j] = VerificaSiVive(i,j);//Hace el acomodo por condiciones		
			}
		}
		return Newdatos;//Regresa la nueva tabla de datos
	}
	
	private boolean VerificaSiVive(int PosFila, int PosColumna) {
		boolean estaVivo = false;
		int NumVivos = 0;
		boolean dato = this.datos[PosFila][PosColumna];
		for(int i = PosFila-1;i<=PosFila+1;i++) {
			for(int j = PosColumna-1;j<=PosColumna+1;j++) {
				/*Condiciones del if. Se deben de cumplir todas las condiciones:
				 *1)El valor actual no puede entrar en la comparacion, dado que no es un vecino
				 */
				
				try {
					if(i!=PosFila && j !=PosColumna)  {
						if(this.datos[i][j]) {//Si encuentra un vecino vivo
							NumVivos++;
						}
					}
					
				} catch(Exception e)
		        {}  
				
			}
		}
		
		/*if para caso que este vivo. Se deben de cumplir las 3 condiciones:
		 * 1)Si esta vivo
		 * 2)El numero de vecinos vivos es mayor o igual a 3
		 * 3)El numero de vivos es menor o igual a 3 
		 */
		if(dato && NumVivos>=2 && NumVivos<=3) {
			estaVivo = true;
		}else if(!dato && NumVivos == 3) {//si no esta vivo y los vecinos vivos son exactamente 3
			estaVivo = true;
		}
		//Si no se cumplen los casos anteriores
		return estaVivo;
	
	}
	
	//Verifica si las tablas son iguales
	private boolean TablasIguales(boolean datosT2[][]) {
		
		for(int i = 0; i<this.fila;i++) {
			for(int j = 0; j<this.columna;j++) {
				if(this.datos[i][j]!=datosT2[i][j]) {//Si el valor llegara a ser distinto
					return false;//Es falso
				}
			}
		}
		
		return true;
	}
	
	//Verifica si hay un organismo vivo por toda la tabla
	private boolean HayOrganismoVivo() {
		for(int i =0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(this.datos[i][j]) {//Encuentra un solo ser vivo
					return true;//Es verdadero
						}
					}
				}
		return false;//No encuentra a nadie vivo
		
	}
	
	//Detiene el programa bajo las condiciones adecuadas
	public boolean DetenerPrograma(boolean datosT2[][]) {
		boolean detenerPrograma = false;
		if(TablasIguales(datosT2)) {//Tablas iguales
			System.out.print("La generacion anterior es igual a la actual.");
			detenerPrograma= true;
		}else if(!HayOrganismoVivo()) {//No encontro un ser vivo
			System.out.print("Ya no hay seres vivos en la generacion actual.");
			detenerPrograma = true;
		}
		
		return detenerPrograma;
	}
}
