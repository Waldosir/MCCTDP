package juegoBuscaminas;

import java.util.ArrayList;

import enums.dificultad;

public class TablaBuscaminas {
	//Valor de fila, columna y datos de tabla
	private int fila,columna;
	private Casillas[][] tablaBuscaminas;
	private int numeroMinas;
	
	public TablaBuscaminas(int numero, String nivelDificultad) { //Constructor para introducir datos
		this.fila = numero;
		this.columna = numero;
		this.tablaBuscaminas = new Casillas[fila][columna];
		generarTabla(nivelDificultad);
	}
	public int getNumeroMinas() {
		return this.numeroMinas;
	}
	
	public Casillas getCasilla(int posX, int posY) {
		return this.tablaBuscaminas[posX][posY];
	}
	
	private void generarTabla(String nivelDificultad) {
		double porcentaje = 0; int minimo, maximo, rango;
		System.out.println("Dificulltad: "+nivelDificultad);
		if(nivelDificultad.equals(dificultad.principiante.toString())) {
			porcentaje = 0.1;
		}else if(nivelDificultad.equals(dificultad.facil.toString())) {
			minimo = 15; maximo = 20; rango = maximo-minimo;
			porcentaje = ((int) (Math.random()*rango)+minimo);
			porcentaje /=100;
		}else if(nivelDificultad.equals(dificultad.normal.toString())) {
			minimo = 20; maximo = 25; rango = maximo-minimo;
			porcentaje = ((int) (Math.random()*rango)+minimo);
			porcentaje /=100;
		}else if(nivelDificultad.equals(dificultad.dificil.toString())){
			minimo = 25; maximo = 40; rango = maximo-minimo;
			porcentaje = ((int) (Math.random()*rango)+minimo);
			porcentaje /=100;
		}else {
			System.out.println("Error, supongo");
		}
		this.numeroMinas = (int)(porcentaje*getTamanoTabla());
		System.out.println("Minas hechas:"+this.numeroMinas);
		generarMinas(this.numeroMinas);
		generarCasillasVacias();
		generarNumeros();
	}
	
	private void generarMinas(int cantidad) {
		if(cantidad<=0) {
			return;
		}
		int posMinaFila = (int) (Math.random()*(this.fila-1)+0);
		int posMinaColumna = (int) (Math.random()*(this.columna-1)+0);
		try {
			if(this.tablaBuscaminas[posMinaFila][posMinaColumna].getHayMina()) {
				generarMinas(cantidad);
			}
			
		}catch(Exception NullPointerException) {
			this.tablaBuscaminas[posMinaFila][posMinaColumna] = new Casillas(true);
			generarMinas(cantidad-1);
		}
		
		
	}
	
	private void generarCasillasVacias() {
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(this.tablaBuscaminas[i][j] == null) {
					this.tablaBuscaminas[i][j] = new Casillas();
				}
				
			}
		}
		
	}
	private void generarNumeros() {
		for(int i=0;i<this.fila;i++) {
		for(int j=0;j<this.columna;j++) {
			if(!this.tablaBuscaminas[i][j].getHayMina()) {
				this.tablaBuscaminas[i][j].setNumero(cantidadMinasVecinas(i,j));
			}
		}
		}
	}
	private int cantidadMinasVecinas(int posX, int posY) {
		int cantidadMinas = 0;
		System.out.println("Empieza posicion "+posX+" - "+posY);
		for(int i=posX-1;i<=posX+1;i++) {
			for(int j=posY-1;j<=posY+1;j++) {
				try {
					if(!(i==posX && j==posY)) {
						System.out.println("Posicion "+i+" - "+j);
						if(this.tablaBuscaminas[i][j].getHayMina()) {
							cantidadMinas++;
							System.out.println("Hay mina");
						}
					}
				}
				catch(Exception NullPointerException) {
					System.out.println("Hubo error");
				}
			}
		}
		System.out.println("Es todo \n");
		return cantidadMinas;
	}
	
	public int getTamanoTabla() {
		return this.fila*this.columna;
	}
	
	public void destaparCasillas(int x, int y) {
		
			if((this.tablaBuscaminas[x][y].getNumero()>0) || !(this.tablaBuscaminas[x][y].getCasillaTapada()) ) {
				this.tablaBuscaminas[x][y].setCasillaTapada(false);
				return;
			}else {
				this.tablaBuscaminas[x][y].setCasillaTapada(false);
				
				for(int i=x-1;i<=x+1;i++) {
					for(int j=y-1;j<=y+1;j++) {
						try {
							destaparCasillas(i,j);
						}
							catch(Exception NullPointerException) {
					}
					}
					
			
		}
		}
		
	}
	
	public boolean seleccionarCasilla(int x, int y) {
		try {
			if(this.tablaBuscaminas[x][y].getHayMina()) {
			System.out.println("Perdiste");
			destaparTodo();
			return false;
		}else {
			destaparCasillas(x, y);
			if(ganar()){
				System.out.println("Ganaste");
				return false;
			}
			
			return true;
		}
			
		}catch(Exception NullPointerException) {
			System.out.println("No existe esa casilla");
			return true;
		}
		
		
	}

	public void destaparTodo() {
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
					this.tablaBuscaminas[i][j].setCasillaTapada(false);
				}
			}
	}
	
	private boolean ganar() {
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
					if(!(this.tablaBuscaminas[i][j].getHayMina())&&(this.tablaBuscaminas[i][j].getCasillaTapada())){
						return false;
					}
				}
			}
		return true;
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
			{ if(!this.tablaBuscaminas[i][j].getCasillaTapada()) {
				if(this.tablaBuscaminas[i][j].getHayMina()) {
					FormaTabla +="B  ";
				}
				else if(this.tablaBuscaminas[i][j].getNumero()>0) { //Caso donde organismos esten vivos
					FormaTabla +=this.tablaBuscaminas[i][j].getNumero()+"  ";
				}
				else {//Caso donde organismos esten muertos
					FormaTabla+="-  ";
				}
			}else {//Caso donde organismos esten muertos
				FormaTabla+="X  ";
			}
				
			}
			FormaTabla+="\n";//Salto de linea
		}
		
		return FormaTabla;
		
	}
	
	
	
}
