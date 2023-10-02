package JuegoMesa;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Baraja.BarajaPoker;
import Baraja.CartasPoker;
import Personas.Jugador;
import Personas.Crupier;

public class BlackJack {
	static private final String gana = "gana", empate = "empate", pierde = "pierde", apostadoBJ = "apostadoBJ";
	static Scanner sc = new Scanner(System.in);//Leer datos
	
	private ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
	private BarajaPoker barajaPoker;
	private Crupier crupier;
	
	public BlackJack(ArrayList<Jugador> listaJugadores, BarajaPoker barajaPoker, Crupier crupier) {//Constructor
		this.listaJugadores = listaJugadores;
		this.barajaPoker = barajaPoker;
		this.crupier = crupier;
		
	}
	
	private void anadirJugadorNuevo() { //Crea un jugador
		String nombre, cantidad;
		int cantidadFichas;
		do {
			System.out.print("\nNombre del jugador nuevo:");
			nombre = sc.next();
			if(!(nombreRepetido(nombre))) {
				System.out.print("Numero de fichas:");
				cantidad = sc.next();
				try {
					cantidadFichas = Integer.parseInt(cantidad);
					break;
				}
				catch(Exception e) {
					System.out.println("Escribe un valor valido");
				}
			}else {
				System.out.println("Nombre repetido. Tomar otro nombre");
			}
			
		}while(true);
		this.listaJugadores.add(new Jugador(nombre,cantidadFichas));
	}
	
	private void borrarJugador() {//Borra un jugador
		String opcion;
		int opcionInt;
		do {
			System.out.println("0- Salir");
			verJugadores();
			System.out.print("Opcion:");
			opcion = sc.next();
			try {
				opcionInt = Integer.parseInt(opcion);
				if(opcionInt != 0) {
					opcionInt-=1;
					String nombreJ = this.listaJugadores.get(opcionInt).getNombre();
					this.listaJugadores.remove(opcionInt);
					System.out.println("Se ha removido el jugador |"+nombreJ+ "| con exito");
				}
				break;
				
			} catch(Exception e) {
				System.out.println("Opcion no valida. Repetir opcion\n");
			}
		}while(true);
		
		
		
	}
	
	private void verJugadores() {//Ve informacion de los jugadores disponibles
		System.out.println("Lista de jugadores:");
		for(int i=0;i<listaJugadores.size();i++) {
			System.out.println((i+1)+"- "+listaJugadores.get(i));
		}
	}
	
	private boolean nombreRepetido(String nombre) {
		boolean condicion = false;
		for(int i=0;i<this.listaJugadores.size();i++) {
			if(this.listaJugadores.get(i).getNombre().equals(nombre)) {
				condicion = true;
				break;
			}
		}
		return condicion;
	}
	
	private void retirarJugadoresSinDinero() {
		Jugador j;
		for(int i=0;i<listaJugadores.size();i++) {
			j = this.listaJugadores.get(i);
			if(j.getNumeroFichas()<=0) {
				this.listaJugadores.remove(i);
				System.out.println(j+" fue eliminado de la partida");
			}
		}
	}
	
	private void anadirDineroJugador() {
		String opcion;
		int opcionInt, fichas;
		do {
			System.out.println("0- Salir");
			verJugadores();
			System.out.print("Opcion:");
			opcion = sc.next();
			try {
				opcionInt = Integer.parseInt(opcion);
				if(opcionInt != 0) {
					opcionInt-=1;
					Jugador j = this.listaJugadores.get(opcionInt);
					System.out.print("Dinero a anadir:");
					fichas= Integer.parseInt(sc.next());
					j.darNumeroFichas(-fichas);
					System.out.println("El jugador "+j.getNombre()+ "se le han anadido las fichas "+fichas);
				}
				break;
				
			} catch(Exception e) {
				System.out.println("Opcion no valida. Repetir opcion\n");
			}
		}while(true);
		
		
	}
	
	public void menuJugarBJ() {
		boolean ciclo = true;
		do {
			System.out.println("\nMenu BlackJack");
			System.out.println("0-Salir");
			System.out.println("1-Jugar la mesa actual");
			System.out.println("2-Anadir jugador");
			System.out.println("3-Retirar jugador");
			System.out.println("4-Ver informacion de jugadores");
			System.out.println("5- Anadir ficha a jugador");
			String opcion = sc.next();
			switch(opcion){
			case "0":
				ciclo = false;
				break;
			case "1":
				if(listaJugadores.size()>0) {
					rondaMesa();
				}else {
					System.out.println("No hay jugadores");
				}
				
				break;
			case "2":
				if(listaJugadores.size()<7) {
					anadirJugadorNuevo();
				}else {
					System.out.println("No se pueden anadir mas jugadores");
				}
				
				break;
			case "3":
				if(listaJugadores.size()>0) {
					borrarJugador();
				}else {
					System.out.println("No hay jugadores");
				}
				
				break;
			case "4":
				verJugadores();
				break;
			case "5":
				if(listaJugadores.size()>0) {
					anadirDineroJugador();
				}else {
					System.out.println("No hay jugadores");
				}
				break;
			default:
				System.out.println("No es una opcion valida. Volver a intentar");
				
			}
			
			
		}while(ciclo);
		
	}
	
	private void rondaMesa() {//Todo lo que se hace en una ronda
		//Todos los pasos que hay en una ronda
		faseApuestasIniciales();//Apostar antes de iniciar
		entregarCartasJugadores();//Entrega 2 cartas a cada jugador
		
		if(crupierPuedeTenerBlackJack()) { //Si el crupier PUEDE tener Blackjack
			asegurarApuestaJugadorACrupierBJ();//Asegurar apuesta de BJ
			if(!condicionBlackJack(this.crupier)) {//Si no tiene BJ el Crupier
				System.out.println("Crupier no tiene BJ. Sigue el juego con normalidad\n");
				espera();
				//Turno normal
				turnoJugadores();
				turnoCupier();
			}else {//Si crupier SI tiene BJ
				turnoCupier();
			}
			
		}else {//Turno normal
			turnoJugadores();
			turnoCupier();
		}
		
		verGanadores();//Checa ganadores
		limpiarJugadores();//Limpia jugadores
		retirarJugadoresSinDinero();
	}
	
	private void asegurarApuestaJugadorACrupierBJ() {//Apostar para asegurar que Crupier tiene BJ
		Jugador j; String Opcion; boolean ciclo = true;
		for(int i=0;i<this.listaJugadores.size();i++) {
			j = this.listaJugadores.get(i);//Toma un jugador
			do {
				System.out.println("Carta que muestra el crupier: "+this.crupier.cartaCrupier());
				System.out.println("Jugador "+j.getNombre()+" desea apostar si el Crupier tiene BJ?");
				System.out.println("0- No ");
				System.out.println("1- Si");
				System.out.print("Opcion:");
				Opcion = sc.next();
				switch(Opcion) {
				case "0":
					j.setEstadoFinalRonda(pierde);
					System.out.println("No se ha apostado contra el crupier");
					ciclo = false;
					break;
				case "1":
					j.setEstadoFinalRonda(apostadoBJ);
					int fichasApuestaSegura = (int)(j.getMontoAApostar()/2);
					j.setMontoAApostar(fichasApuestaSegura);
					System.out.println("Se ha apostado de forma segura contra el crupier");
					ciclo = false;
					default:
						System.out.println("Escoge una opcion valida");
				}
				
			}while(ciclo);
			
			
			
		}
	}
	
	private void faseApuestasIniciales() {//Apuestas iniciales
		Jugador j;
		for(int i=0;i<this.listaJugadores.size();i++) {
			j = this.listaJugadores.get(i);
			if(j.getEstadoEnJuego()) {
				cantidadInicialApostada(j);//Inicializar la apuesta	
			}
			
		}
		
	}
	
	private void entregarCartasJugadores() {//Entrega cartas a jugadores
		for(int j=0;j<2;j++) {//2 cartas
			for(int i=0;i<this.listaJugadores.size();i++) {//Todos los jugadores
				this.listaJugadores.get(i).tomarCarta(this.barajaPoker.sacarCarta());
			}
			this.crupier.tomarCarta(this.barajaPoker.sacarCarta());//Entrega a crupier
		}
		System.out.println("Se reparten 2 cartas a cada jugador y al Crupier");
	}
	
	private void limpiarJugadores() {//Limpia cartas de sus jugadores
		this.crupier.retirarCartas();
		for(int i=0;i<this.listaJugadores.size();i++) {
			this.listaJugadores.get(i).retirarCartas();
		}
		System.out.println("Se retiran manos.\n");
		
	}
	
	private void turnoJugadores() {//Turno de cada jugador
		for(int i =0;i<listaJugadores.size();i++) {//Recorre todos los jugadores
			if(this.listaJugadores.get(i).getEstadoEnJuego()) {
				turnoDeUnJugador(this.listaJugadores.get(i),i);//Turno de cada jugador	
			}
			
		}
	}
	
	private void cantidadInicialApostada(Jugador j) {//Lo que apuesta cada jugador
		int valorApuesta = 0;//Valor de la apuesta
		do {
			System.out.println(j.toString()); //Datos de la persona
			System.out.print("Cantidad a apostar:");
			String valorApostar = sc.next();
			
			try{
				valorApuesta = Integer.parseInt(valorApostar); //Transforma a entero
				if((valorApuesta<=j.getNumeroFichas())&&valorApuesta>0) {//Si la apuesta es menor o igual a las fichas que tiene
					j.setMontoAApostar(valorApuesta);//Atributo apuesta
					j.darNumeroFichas(valorApuesta);//Se retiran esas fichas
					System.out.println("Apuesta realizada\n");
					return;
				}
				System.out.println("No se puede cumplir ese monto. Reintentar\n");
				
			}catch(NumberFormatException e) {
				System.out.println("Error. Opcion no admisible.\n");
			}
			
		}while(true);
		
	}
	
	private String interfazJugador(Jugador j) {//Interfaz del jugador con datos de la mesa
		String dato = "";
		dato+= j.manoJugador();//Cartas del jugador
		dato+="\nCarta visible del cupier:"+crupier.getCartas(0);//Carta del crupier
		//Valor de sus cartas en numeros - Fichas del jugador - Valor de su apuesta.
		dato+="Valor actual: "+j.valorDeSusCartasEnBJ()+" --- Fichas:"+j.getNumeroFichas()+" --- valor apuesta: "+j.getMontoAApostar()+"\n";
		return dato;
	}
	
	private int opcionesDeJugador(Jugador j) {//Las opciones del jugador en su turno
		int Opcion = 0;
		do {
			System.out.println(interfazJugador(j)+"Opciones del jugador "+j.getNombre()+":");
			System.out.println("1- Pedir carta");
			System.out.println("2- Doblar apuesta - Solo en juego inicial");
			System.out.println("3- Quedarte");
			System.out.println("5- Ver resto de jugadores");
			System.out.print("Opcion:");
			String OpcionS = sc.next();
			try {
				Opcion = Integer.parseInt(OpcionS);
				System.out.println();
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("Valor no valido. Reintroducir valor\n");
			}
		}while(true);

		return Opcion;
	}
	
	private void espera() {//Espera un segundo.
		try {
			TimeUnit.SECONDS.sleep(1);//Espera un segundo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean sigueTurnoJugador(Jugador j) {//Verifica si el jugador puede seguir jugando
		boolean turnoJugador = true;
		if(j.valorDeSusCartasEnBJ()>21) {//Si se pasa de la cantidad
			j.setEstadoEnJuego(false);
			turnoJugador = false;
			j.setEstadoFinalRonda("pierde");
			System.out.println("Jugador "+j.getNombre()+" fuera de la partida");
		}
		return turnoJugador;
	}

	private void tomarUnaCarta(Jugador j) {//Jugador toma una carta
		CartasPoker carta = this.barajaPoker.sacarCarta();//Saca la carta
		j.tomarCarta(carta);//Jugador toma la carta
		//Valores de la carta (Nombre, numero) y el total sumado del jugador
		System.out.print("La carta sacada es "+carta.toString());
		System.out.println("Total sumado: "+j.valorDeSusCartasEnBJ());
	}
	
	private void turnoDeUnJugador(Jugador j, int posicion) {//Turno de un solo jugador
		boolean turnoJugador = true;//Para que el jugador tome decisiones hasta que se quede o pierda
		while(turnoJugador) {
			switch(opcionesDeJugador(j)) {//Escoge una opcion
				case 1://Tomar una carta
					tomarUnaCarta(j);
					turnoJugador = sigueTurnoJugador(j);
					break;
				case 2://Apuesta doble
					//Si tienes las fichas para pagar otra apuesta igual y tiene las 2 cartas iniciales
					if(j.getNumeroFichas()>=((j.getMontoAApostar()))&&(j.getNumeroDeCartasEnMano()==2)) {
						j.darNumeroFichas(j.getMontoAApostar());//Retiras las fichas
						j.setMontoAApostar(2*(j.getMontoAApostar()));//Das las fichas (Doblar apuesta)
						turnoJugador = false;//Ya no tiene otro turno
						System.out.println("Se ha doblado la apuesta");
						System.out.println("¿Quieres otra carta?");
						System.out.println("0-No (Puede ser tambien otra tecla)");
						System.out.println("1-Si");
						String OpcionApuesta = sc.next();
						if(OpcionApuesta.equals("1")) {//Si quiere otra carta
							tomarUnaCarta(j);//Toma otra carta
							sigueTurnoJugador(j);//Solo da el aviso sin guardar la variable en un lugar
						}
					}else {//No se puede apostar
						System.out.println("No se ha doblado la apuesta\n");
						espera();
					}
					break;
				case 3://Se queda
					turnoJugador = false;
					break;
				case 5://Ve las cartas de los demas jugadores
					System.out.println(manoDemasJugadores(posicion));
					break;
				default:
					System.out.println("No es una opcion. Escoger una opcion valida.\n");
					espera();
				}
			
			
			}
			System.out.println("Siguiente jugador...\n");
			espera();
		}
	
	private boolean crupierPuedeTenerBlackJack() {//Si Crupier tiene posibilidad 
		CartasPoker Carta = (CartasPoker) this.crupier.getCartas(0);
		int numeroCartaInicialC = Carta.getValorCarta().getNumeroEnteroCarta();
		if(numeroCartaInicialC>=10 || numeroCartaInicialC==1) {
			return true;
		}
		return false;
	}
	
	private String manoDemasJugadores(int posicionI) {
		String dato="";
		for(int i=0;i<listaJugadores.size();i++) {
			if(i!=posicionI) {
				dato+=this.listaJugadores.get(i).manoJugador()+"\n";
			}
		}
		return dato;
	}
	
	private void turnoCupier() {//Todo lo del turno del crupier
		int numeroCrupier = this.crupier.valorDeSusCartasEnBJ();//Checa mano del crupier
		while(numeroCrupier<=16) {//Crupier debe de ser mayor de 16
			this.crupier.tomarCarta(this.barajaPoker.sacarCarta());
			numeroCrupier = this.crupier.valorDeSusCartasEnBJ();
		}
		if(numeroCrupier>21) {
			this.crupier.setEstadoEnJuego(false);
		}
		System.out.println(this.crupier.toString());
		System.out.println("Termina el turno del crupier");
		espera();
		}
	
	private boolean condicionBlackJack(Jugador j) {
		if(j.valorDeSusCartasEnBJ()==21 && j.getNumeroDeCartasEnMano()==2) {
			return true;
		}
		return false;
	}
	
	//Condicion jugador Victoria_Derrota_Empate
	private void condicionJugadorV_D_E(Jugador jActual) {
		//Condiciones para ver si gana, empata o pierde
		String gana = "gana", pierde = "pierde";
		
		int numeroCrupier = this.crupier.valorDeSusCartasEnBJ(); //Valor de la carta del crupier
		int numeroJugador = jActual.valorDeSusCartasEnBJ();//Valor de la carta del jugador
		String condicionJugador;
		
		if(numeroJugador>numeroCrupier) {//Jugador tiene mano mayor que el crupier
			condicionJugador = gana;
		}else if(numeroJugador<numeroCrupier) {//Jugador tiene mano menor que el crupier
			condicionJugador = pierde;
		}
		else{//Si empatan, se hacen condiciones de empate
			condicionJugador = condicionesDeEmpate(jActual);
		}
		
		jActual.setEstadoFinalRonda(condicionJugador);
	}
	
	private String condicionesDeEmpate(Jugador jActual) {
		//Si tiene blackjack jugador, crupier o ambos
		boolean BlackJackJ = condicionBlackJack(jActual);
		boolean BlackJackC = condicionBlackJack(this.crupier);
		String condicion;
		if(BlackJackJ && !BlackJackC) {//Si jugador tiene blackjack y Crupier no
			condicion = gana;
		}else if(!BlackJackJ && BlackJackC) {//Si Crupier tiene blackjack y Jugador no
			condicion = pierde;
		}else {//Otro caso
			condicion = empate;
		}
		
		return condicion;
	}
	
	private void entregaFichasJugadorV_D_E( Jugador jActual) {
		int numeroFichas;
		if(jActual.getMontoAApostar()>0) {
			switch(jActual.getEstadoFinalRonda()) {
			case "gana":
				if(condicionBlackJack(jActual)) {
					numeroFichas = (int) (-2.5*jActual.getMontoAApostar());
					jActual.setEstadoFinalRonda("gana");
				}else {
					numeroFichas = -2*jActual.getMontoAApostar();
				}
				jActual.darNumeroFichas(numeroFichas);
				break;
			case "empate":
				jActual.darNumeroFichas(-jActual.getMontoAApostar());
				break;
			case "pierde":
				break;
			case "apostadoBJ":
				numeroFichas = -jActual.getMontoAApostar();
				jActual.darNumeroFichas(numeroFichas);
				break;
			default:
				System.out.println("Solo existen 3 casos. En caso de ver esto, contactar al programador");
				break;
			}
			
		}
		
	}
	
	private void verGanadores() {
		//Condiciones para ver si gana, empata o pierde
		
		int numeroCrupier = this.crupier.valorDeSusCartasEnBJ();
		Jugador jActual;
		
		//Si perdio el crupier
		boolean crupierPerdio = !(this.crupier.getEstadoEnJuego());//Si crupier pierde
		for(int i=0;i<this.listaJugadores.size();i++) {//Recorre todos los jugadores
			jActual = this.listaJugadores.get(i);//Jugador actual
			if(jActual.getEstadoEnJuego()) {//Verifica si el jugador actual sigue en el juego
				if(crupierPerdio) {//Si sigue vivo y el Crupier pierde, ganas
					jActual.setEstadoFinalRonda(gana);
				} else if(!condicionBlackJack(this.crupier)) {
					condicionJugadorV_D_E(jActual);
				}
				entregaFichasJugadorV_D_E(jActual);
			}
			
			}
			
			System.out.println(tablaFinal(numeroCrupier));

		
		}
	
	private String tablaFinal(int numeroCrupier) {
		Jugador jActual; int numeroJugador; String muestraInfo="\nTabla final:";
		String muestraBJ= " Blackjack - ";
		for(int i=0;i<this.listaJugadores.size();i++) {//Recorre todos los jugadores
			jActual = this.listaJugadores.get(i);//Jugador actual
			if(jActual.getMontoAApostar()>0) {
				numeroJugador = jActual.valorDeSusCartasEnBJ();
				
				muestraInfo +=jActual.getNombre()+" (num:"+numeroJugador+" ) - ";
				
				if(condicionBlackJack(jActual)) {
					muestraInfo+= muestraBJ;
				}
				muestraInfo+= this.crupier.getNombre()+" (num: "+numeroCrupier+" ) ";
				muestraInfo += "->"+jActual.getEstadoFinalRonda()+"\n";
		
			}	
			}
				
			
		return muestraInfo;
		
	}
	
	public void cerrarScanner() {
		sc.close();
	}
		

	
	
	
	
	
	
}