package juegoBuscaminas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import datosUsuario.GestionUsuarios;
import datosUsuario.Usuario;
import enums.dificultad;

public class TablaBuscaminas extends GestionUsuarios {
	//Valor de fila, columna y datos de tabla
	private int fila,columna; //valores de fila y columna
	private Casillas[][] tablaBuscaminas;//Tabla con casillas
	private int numeroMinas;//Numero de minas
	private int numeroMarcas;//Numero de marcas
	private dificultad nivelDificultad; //Dificultad de la tabla
	private Usuario usuario; //Usuario
	private ArrayList<Usuario> ganadores = new ArrayList<Usuario>(); //Lista de ganadores
	private Scanner sc = new Scanner(System.in); //Scanner Sc
	
	final int PuntajePorNivel = 100; //Constante puntos por pasar un nivel
	
	public TablaBuscaminas(Usuario usuario) { //Constructor principal
		this.usuario = usuario;
		this.nivelDificultad = usuario.getDificultadU();
		this.fila = this.nivelDificultad.getBaseTabla();
		this.columna = this.nivelDificultad.getAlturaTabla();
		this.tablaBuscaminas = new Casillas[fila][columna]; //Tabla buscaminas
		numeroMarcas = 0;
		sacarTop10();
	}
	
	public TablaBuscaminas() { //Solo necesario para mostrar puntaje del juego
		sacarTop10();
	}
	

	private void sacarTop10() {//Saca al top 10 de jugadores
		GestionUsuarios gU = new GestionUsuarios();
		Usuario[] lista = gU.recuperarUsuarios();
		for(Usuario todos:lista) {
			this.ganadores.add(todos);
		}
	}
	
	private void generarNuevoJuego() {
		this.tablaBuscaminas = new Casillas[this.fila][this.columna]; //Destruye la tabla
		generarTabla();//Crea una nueva tabla
	}
	
	private void setNumeroMarcas(int numeroMarcas) { //Administrar numero de marcas (Bombas o bloqueos)
		this.numeroMarcas = numeroMarcas;
	}
	
	
	private Casillas getCasilla(int posX, int posY) { //Toma la casilla correspondiente
		return this.tablaBuscaminas[posX][posY];
	}
	
	private void generarTabla() {//Genera una tabla dependiendo de la dificultad
		double porcentaje = 0; int minimo=0, maximo=0, rango=0;
		if(this.nivelDificultad.toString().equals(dificultad.principiante.toString())) {
			//10% del tablero
			minimo = 10; maximo = 10; 
		}else if(this.nivelDificultad.toString().equals(dificultad.facil.toString())) {
			//15%-20% tamaño del tablero
			minimo = 15; maximo = 20;
		}else if(this.nivelDificultad.toString().equals(dificultad.normal.toString())) {
			//20%-25% tamaño del tablero
			minimo = 20; maximo = 25;
		}else {//Dificil
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
	
	private void generarMinas(int cantidad) { //Recursivo genera las minas
		if(cantidad<=0) {//Condicion de terminar
			return;
		}
		//Posiciones de la mina
		int posMinaFila = (int) (Math.random()*(this.fila-1)+0);
		int posMinaColumna = (int) (Math.random()*(this.columna-1)+0);
		
		try { //Va a dar error cada que verifique que no puede sacar valor de una casilla Null
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
					this.tablaBuscaminas[i][j] = new Casillas(false);//Casilla nueva vacia
				}
				
			}
		}
		
	}
	
	private void generarNumeros() { //Genera numeros alrededor de las minas
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
				try {//Cuenta hasta su misma casilla, dado que esta jamas tendra una mina
						if(this.tablaBuscaminas[i][j].getHayMina()) {//Encuentra una mina vecina
							cantidadMinas++;//Contador de mina
					}
				}
				catch(Exception NullPointerException) {
				}
			}
		}
		return cantidadMinas;
	}
	
	private int getTamanoTabla() { //Tamaño de la tabla
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
	
	private boolean seleccionarCasilla(int x, int y) {
		boolean sigueJugando = false;
		try {
			//Si hay una mina Y no está bloqueado
			if(this.tablaBuscaminas[x][y].getHayMina() && !this.tablaBuscaminas[x][y].getBloquear()) {
			System.out.println("Perdiste");
			this.usuario.setCondicionJuego(false);//Condicion falsa de ganador
			destaparTodo();//Destapa todo para mostrar como estaba el tablero
			sigueJugando =  false;//Retorna falso para que acabe el juego
		
			
		}else if(this.tablaBuscaminas[x][y].getBloquear()) {//Si esta bloqueado
			System.out.println("Casilla bloqueada. No se puede seleccionar");
			sigueJugando =  true;//Sigue en el juego
			
		}else{
			destaparCasillas(x, y);//Destapa casilla
			if(ganar()){//Condicion ganar
				System.out.println("Ganaste");
				this.usuario.setCondicionJuego(true);//Condicion ganada
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
		if(this.usuario.getCondicionJuego()) { //Si esta jugando se añaden esto:
			FormaTabla+="\nNumero de minas: "+this.numeroMinas+""; //Numero de Minas
			setNumeroMarcas(checarNumeroMarcas());
			FormaTabla+="\nNumero de marcas: "+this.numeroMarcas+""; //Marcas hechas por el jugador
		}
			
		FormaTabla += "\n";
		return FormaTabla;
		
	}
	
	private int checarNumeroMarcas() {//Checa las marcas que tiene actualmente la tabla (Con el metodo recursivo se retiran las marcas)
		int contador = 0;
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				//Si (Casilla bloqueada o casilla con marca de bomba)
				if(this.tablaBuscaminas[i][j].getBloquear() || this.tablaBuscaminas[i][j].getMarcaBomba()) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	private void turnoJugador() {
		int opcion = 0; 
		int filaU, columnaU;
		boolean enJuego = true;
		generarTabla();//Genera una tabla
		this.usuario.sumarNivel(1); //Empieza en nivel 0. Le suma un nivel
		do {
			System.out.println(this.usuario);
			System.out.println(toString());
			if(this.usuario.getHacerTrampa()) {
				trampa(); //Marca la primera casilla que encuentre tapada que no tenga bomba
				verPrimeraMina();//Marca la primera casilla que encuentre tapada con una bomba
			}
			System.out.println("1- Abrir");
			System.out.println("2- Bloquear");
			System.out.println("3- Desbloquear");
			System.out.println("4- Colocar marca Mina");
			System.out.println("5- Quitar marca Mina");
			
			System.out.print("Opcion:");
			try { //Evita opciones no deseables
				opcion = Integer.parseInt(sc.next());
			}catch(NumberFormatException e) { //Será una opcion no valida (0 por ejemplo)
				opcion = 0;
			}
			try { //Se escoje una casilla que no existe
				switch(opcion) {
				case 1://Abrir una casilla
					System.out.println("Opcion escogida: Abrir");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					enJuego = seleccionarCasilla(filaU, columnaU);
					break;
				case 2://bloquear casilla
					System.out.println("Opcion escogida: Bloquear");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					//Solo se puede bloquear si la casilla esta tapada
					if(getCasilla(filaU, columnaU).condicionColocarMarca()) {
						getCasilla(filaU, columnaU).setBloquear(true);
					}else {
						System.out.println("No se puede bloquear una casilla destapada");
					}
					break;
				case 3://Desbloquear casilla
					System.out.println("Opcion escogida: Desbloquear");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					if(getCasilla(filaU, columnaU).getBloquear()) {
						getCasilla(filaU, columnaU).setMarcaBomba(false);
					}
					break;
				case 4://Marca bomba
					System.out.println("Opcion escogida: Marca mina");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					//Solo se puede marcar si la casilla esta tapada
					if(getCasilla(filaU, columnaU).condicionColocarMarca()) {
						getCasilla(filaU, columnaU).setMarcaBomba(true);
					}else {
						System.out.println("No se puede marcar una casilla destapada");
					}
					
					break;
				case 5: //Desmarcar minas
					System.out.println("Opcion escogida: Desmarcar mina");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					if(getCasilla(filaU, columnaU).getMarcaBomba()) {
						getCasilla(filaU, columnaU).setMarcaBomba(false);
					}
					
					break;
				default:
					System.out.println("Opcion no valida");
				}
				
			}catch(Exception NullPointerException) {
				System.out.println("Casilla no valida.");
				
			}
			
			
		}while(enJuego);
		espera();//Muesta mensaje anterior durante 2 segundos
		System.out.println(toString());//Imprime la tabla final.
		espera();//Espera 2 segundos mostrando la tabla final

	}
	
	private int escoger(String tag) { //Opcion de escoger un numero entero
		int opcion=0;
		do {
			try {
				System.out.print(tag+": ");
				opcion = sc.nextInt();
				return opcion;
			}
			catch(NumberFormatException e) {
			System.out.println("Opcion no valida.");	
			}
		} while(true);
	}
	
	private void espera() {//Espera 2 segundos
		  try {

	             Thread.sleep(2000); //2000 ms. = 2 seg.

	       } catch (InterruptedException e) {

	       }
	}
	
	public void partidaBuscaminas() {
		long tiempoInicial, tiempoFinal;
		float minutosEsperados;
		int puntaje, puntajeBonus;
		
		do {
			puntaje = 0; //Puntaje en 0
			tiempoInicial = System.currentTimeMillis(); //Toma el tiempo actual 
			turnoJugador();//Turno del jugador
			tiempoFinal = System.currentTimeMillis(); //Toma el tiempo actual
			if(this.usuario.getCondicionJuego()) { //Si gana
				minutosEsperados = (tiempoFinal - tiempoInicial)/(60*1000F); //Minutos hechos en el nivel
				puntajeBonus = bonoTiempoPuntaje(minutosEsperados);
				puntaje = this.PuntajePorNivel + puntajeBonus; //Puntaje final
				System.out.println("\nPuntaje por nivel: "+this.PuntajePorNivel);
				System.out.println("Puntaje bonus: "+puntajeBonus);
				System.out.println("Puntaje de esta partida: "+puntaje);
				System.out.println("Puntaje total del jugador: "+(this.usuario.getPuntaje()+puntaje)+"\n");
				espera();
				generarNuevoJuego();//Genera una nueva tabla
			}else {
				System.out.println("Puntaje de esta partida: 0");
				if(this.usuario.getvida()>1) {//3 intentos.
					this.usuario.setCondicionJuego(true); //Lo regresa al juego
					this.usuario.restarVida(); //Resta una vida
					this.usuario.sumarNivel(-1); //Resta un nivel y al inicio se vuelve a sumar
					generarNuevoJuego(); //Genera un juego nuevo
				}else {
					this.usuario.setCondicionJuego(false); //Termina las rondas.
					}
			}
			this.usuario.sumarPuntaje(puntaje); //Suma el puntaje
		}while(this.usuario.getCondicionJuego());
		
		System.out.println("Puntaje final del juego.\nJugador: "+this.usuario.getNombre()+" -> "+this.usuario.getPuntaje());
		colocarEnTablaPuntaje();
	}
	
	private int bonoTiempoPuntaje(float minutos) { //Bono de tiempo por nivel
		int puntaje = this.nivelDificultad.getPuntajeNivel(); //Puntaje definido por nivel
		
			if(minutos>this.nivelDificultad.getTiempoMinimo()) {
				//Puntaje = Puntaje - (minutos pasados * puntajeFinal/tiempoMaximoEspera)
				puntaje = puntaje - (int)(minutos*puntaje/this.nivelDificultad.getTiempoMaximo());
			}
		if(puntaje<0) {//Para que el puntaje por nivel no se elimine
			puntaje = 0;
		}
		
		return puntaje;
	}
	
	private void colocarEnTablaPuntaje() {
		boolean condicionBorrar = false;
		Usuario borrar = null;
		for(Usuario lista: this.ganadores) {
			if(lista.getNombre().equals(this.usuario.getNombre())) {
				if(this.usuario.getPuntaje()>=lista.getPuntaje()) {
					borrar = lista;
					condicionBorrar = true;
					break;
				}
			}
		}
		
		if(condicionBorrar) {
			this.ganadores.remove(borrar);
		}
		this.ganadores.add(this.usuario);
		//Ordena los usuarios mediante puntajes
		Collections.sort(this.ganadores,new Comparator<Usuario>() {
			@over
			public int compare(Usuario o1, Usuario o2) {
				return Integer.valueOf(o2.getPuntaje()).compareTo(o1.getPuntaje());
			}
		}
				);
		
		this.actualizarLista(ganadores);
		System.out.println(tablaPuntajes());
	}
	
	
	public String tablaPuntajes() {
		Collections.sort(this.ganadores,new Comparator<Usuario>() {
			@over
			public int compare(Usuario o1, Usuario o2) {
				return Integer.valueOf(o2.getPuntaje()).compareTo(o1.getPuntaje());
			}
		}
				
				);
		
		String datos="";
		final String JugadorActual = "->Jugador actual";
		datos+= "Tabla de puntajes: \n";
		try {
			for(int i=0;i<10;i++) {
				datos+= (i+1)+"-"+this.ganadores.get(i).toString();
				if(this.ganadores.get(i).equals(this.usuario)) {
					datos+= JugadorActual;
				}
				datos +="\n";
			}
		
		}catch(Exception NullPointerException) {
			
		}
		return datos;
	}
	
	
	//Formas de avanzar o perder más rápido.
	private void trampa() {
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(!this.tablaBuscaminas[i][j].getHayMina() &&this.tablaBuscaminas[i][j].getCasillaTapada() ) {
					System.out.println("No hay mina en "+i+" - "+j);
					return;
				}
			}
		}
	}
	private void verPrimeraMina() {
		for(int i=0;i<this.fila;i++) {
			for(int j=0;j<this.columna;j++) {
				if(this.tablaBuscaminas[i][j].getHayMina() &&this.tablaBuscaminas[i][j].getCasillaTapada() ) {
					System.out.println("Hay mina en "+i+" - "+j);
					return;
				}
			}
		}
	}
	
	
	
}
