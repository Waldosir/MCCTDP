package juegoBuscaminas;

import datosUsuario.Usuario;
import enums.dificultad;

public class TablaBuscaminas {
	//Valor de fila, columna y datos de tabla
	private int fila,columna;
	private Casillas[][] tablaBuscaminas;//Tabla con casillas
	private int numeroMinas;//Numero de minas
	private int numeroMarcas;
	
	public TablaBuscaminas(int fila,int columna, String nivelDificultad) { //Constructor para introducir datos
		this.fila = fila;
		this.columna = columna;
		this.tablaBuscaminas = new Casillas[fila][columna]; //Tabla buscaminas
		numeroMarcas = 0;
		generarTabla(nivelDificultad);//Genera una tabla
	}
	
	public int getNumeroMinas() {
		return this.numeroMinas;
	}
	
	public void anadirCantidadMarcas(int numeroMarcas) {
		this.numeroMarcas += numeroMarcas;
	}
	
	public int getNumeroMarcas() {
		return this.numeroMarcas;
	}
	
	private void setNumeroMarcas(int numeroMarcas) {
		this.numeroMarcas = numeroMarcas;
	}
	
	public Casillas getCasilla(int posX, int posY) {
		return this.tablaBuscaminas[posX][posY];
	}
	
	private void generarTabla(String nivelDificultad) {//Genera una tabla dependiendo de la dificultad
		double porcentaje = 0; int minimo=0, maximo=0, rango=0;
		System.out.println("Dificultad: "+nivelDificultad);
		if(nivelDificultad.equals(dificultad.principiante.toString())) {
			//10% del tablero
			minimo = 10; maximo = 10; rango = maximo-minimo;
		}else if(nivelDificultad.equals(dificultad.facil.toString())) {
			//15%-20% tamaño del tablero
			minimo = 15; maximo = 20; rango = maximo-minimo;
		}else if(nivelDificultad.equals(dificultad.normal.toString())) {
			//20%-25% tamaño del tablero
			minimo = 20; maximo = 25; rango = maximo-minimo;
		}else if(nivelDificultad.equals(dificultad.dificil.toString())){
			//25%-40 tamaño del tablero
			minimo = 25; maximo = 40;
		}
		rango = maximo-minimo;
		//(int) (Math.random()*(Max-Min)+Min);
		porcentaje = ((int) (Math.random()*rango+minimo));
		porcentaje /=100;
		//Numero de minas
		this.numeroMinas = (int)(porcentaje*getTamanoTabla());
		generarMinas(this.numeroMinas);//Genera las minas en la tabla
		generarCasillasVacias();//Genera las casillas vacias
		generarNumeros();//Genera numeros en base a minas
	}
	
	private void generarMinas(int cantidad) {
		if(cantidad<=0) {//Condicion de terminar
			return;
		}
		//Posiciones de la mina
		int posMinaFila = (int) (Math.random()*(this.fila-1)+0);
		int posMinaColumna = (int) (Math.random()*(this.columna-1)+0);
		
		try {
			if(this.tablaBuscaminas[posMinaFila][posMinaColumna].getHayMina()) {//Detecta que hay mina
				generarMinas(cantidad);//Otra vez a buscar otra casilla sin mina
			}
			
		}catch(Exception NullPointerException) {//Detecta que no hay mina (No hay valor/No existe)
			this.tablaBuscaminas[posMinaFila][posMinaColumna] = new Casillas(true);//Mina
			generarMinas(cantidad-1);//Por otra mina
		}
		
		
	}
	
	private void generarCasillasVacias() {//Genera casillas vacias
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(this.tablaBuscaminas[i][j] == null) {//Si es null
					this.tablaBuscaminas[i][j] = new Casillas();//Casilla nueva vacia
				}
				
			}
		}
		
	}
	
	private void generarNumeros() {
		for(int i=0;i<this.fila;i++) {
		for(int j=0;j<this.columna;j++) {
			if(!this.tablaBuscaminas[i][j].getHayMina()) {//Si no es mina
				this.tablaBuscaminas[i][j].setNumero(cantidadMinasVecinas(i,j));//Numero de minas alrededor
			}
		}
		}
	}
	
	private int cantidadMinasVecinas(int posX, int posY) {//Checa las minas circundantes
		int cantidadMinas = 0;
		for(int i=posX-1;i<=posX+1;i++) {
			for(int j=posY-1;j<=posY+1;j++) {
				try {
					if(!(i==posX && j==posY)) {//Mientras no sea la misma posicion
						if(this.tablaBuscaminas[i][j].getHayMina()) {//Encuentra una mina vecina
							cantidadMinas++;//Contador de mina
						}
					}
				}
				catch(Exception NullPointerException) {
				}
			}
		}
		return cantidadMinas;
	}
	
	public int getTamanoTabla() {
		return this.fila*this.columna;
	}
	
	private void destaparCasillas(int x, int y) {//Destapar casilla
			//Si hay un numero o la casilla está destapada
		//Esta es condicion de inicio recursivo
			if((this.tablaBuscaminas[x][y].getNumero()>0) || !(this.tablaBuscaminas[x][y].getCasillaTapada()) ) {
				this.tablaBuscaminas[x][y].setCasillaTapada(false);//Destapa la casilla
				return;//Termina recursivo
			}else {
				//Destapa casilla
				this.tablaBuscaminas[x][y].setCasillaTapada(false);
				
				//Recorre las casillas a su alrededor (No hay condicion para no contarse a sí mismo)
				for(int i=x-1;i<=x+1;i++) {
					for(int j=y-1;j<=y+1;j++) {
						try {//Evita un Null
							destaparCasillas(i,j);
						}
							catch(Exception NullPointerException) {
					}
					}
					
			
		}
		}
		
	}
	
	public boolean seleccionarCasilla(int x, int y, Usuario u) {
		boolean sigueJugando = false;
		try {
			//Si hay una mina Y no está bloqueado
			if(this.tablaBuscaminas[x][y].getHayMina() && !this.tablaBuscaminas[x][y].getBloquear()) {
			System.out.println("Perdiste");
			u.setCondicionJuego(false);//Condicion falsa de ganador
			destaparTodo();//Destapa todo para mostrar como estaba el tablero
			sigueJugando =  false;//Retorna falso para que acabe el juego
			
		}else if(this.tablaBuscaminas[x][y].getBloquear()) {//Si esta bloqueado
			System.out.println("Casilla bloqueada. No se puede seleccionar");
			sigueJugando =  true;//Sigue en el juego
			
		}else{
			destaparCasillas(x, y);//Destapa casilla
			if(ganar()){//Condicion ganar
				System.out.println("Ganaste");
				u.setCondicionJuego(true);//Condicion ganada
				sigueJugando = false; //Se acaba la partida porque gano
			}else {
				sigueJugando = true;//Sigue en el juego
			}
			
		}
			
		}catch(Exception NullPointerException) {
			System.out.println("No existe esa casilla");
			sigueJugando = true;//Sigue en el juego
		}
		
		return sigueJugando;
	}

	private void destaparTodo() {//Destapa todas las casillas
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
					this.tablaBuscaminas[i][j].setCasillaTapada(false);
				}
			}
	}
	
	private boolean ganar() {//Gana si todas las casillas sin minas estan destapadas
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {//Encuentra una casilla destapada que no hay mina
				Casillas c = this.tablaBuscaminas[i][j];
				//Si no hay mina y la casilla esta tapada.
					if(!(c.getHayMina())&&(c.getCasillaTapada())){
						return false;//Aun no ganas
					}
				}
			}
		return true; //Casillas destapadas sin minas
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
				if(!this.tablaBuscaminas[i][j].getCasillaTapada()) {//Si la casilla esta destapada
				if(this.tablaBuscaminas[i][j].getHayMina()) {//Si es una mina
					FormaTabla +="B  ";//Explosion
				}
				else if(this.tablaBuscaminas[i][j].getNumero()>0) { //Casilla con numero
					FormaTabla +=this.tablaBuscaminas[i][j].getNumero()+"  ";
				}
				else {//Casilla sin numero
					FormaTabla+="-  ";
				}
			}else {//Casillas tapadas
				if(this.tablaBuscaminas[i][j].getMarcaBomba()) {//Marca de bomba
					FormaTabla+="M  ";//Mina
				}else if(this.tablaBuscaminas[i][j].getBloquear()) {//Marca bloqueo
					FormaTabla+="?  ";//Bloqueo
				}else {//Sin marca
					FormaTabla+="X  ";
				}
				
				
			}
				
			}
			FormaTabla+="\n";//Salto de linea
		}
		FormaTabla+="\nNumero de minas: "+this.numeroMinas+"";
		setNumeroMarcas(checarNumeroMarcas());
		FormaTabla+="\nNumero de marcas: "+this.numeroMarcas+"";
		return FormaTabla;
		
	}
	


	private int checarNumeroMarcas() {
		int contador = 0;
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(this.tablaBuscaminas[i][j].getBloquear() || this.tablaBuscaminas[i][j].getMarcaBomba()) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	
}
