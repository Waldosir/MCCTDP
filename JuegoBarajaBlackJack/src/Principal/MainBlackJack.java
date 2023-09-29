package Principal;

import java.util.ArrayList;
import java.util.Scanner;

import Baraja.BarajaPoker;
import JuegoMesa.BlackJack;
import Personas.Crupier;
import Personas.Jugador;

public class MainBlackJack {
	static Scanner sc = new Scanner(System.in);//Leer datos
	
	public static BarajaPoker barajaNueva() {//Crea una baraja nueva
		int numeroCartas = 0;
		do {
			System.out.print("Numero de decks en la baraja:");
			String opcion = sc.next();
			try {
				numeroCartas = Integer.parseInt(opcion);
				if(numeroCartas>0) {
					break;
				}
				System.out.println("Introducir un valor mayor de 0");	
			}catch(Exception e ) {
				System.out.println("Introducir un valor valido");
			}
		}while(true);
		
		return new BarajaPoker(numeroCartas);
	}
	
	public static Crupier crupierNuevo() {//Crea un crupier nuevo
		String nombre, cantidad;
		int cantidadFichas;
		do {
			
			System.out.print("Nombre del crupier:");
			nombre = sc.next();
			System.out.print("Numero de fichas:");
			cantidad = sc.next();
			try {
				cantidadFichas = Integer.parseInt(cantidad);
				break;
			}
			catch(Exception e) {
				System.out.println("Escribe un valor valido");
			}
		}while(true);
		String nombreC = "Crupier "+nombre;
		Crupier c = new Crupier(nombreC,cantidadFichas);
		return c;
	}
	
	public static Jugador jugadorNuevo(int numero) { //Crea un jugador
		String nombre, cantidad;
		int cantidadFichas;
		do {
			System.out.print("Nombre del jugador"+numero+":");
			nombre = sc.next();
			System.out.print("Numero de fichas:");
			cantidad = sc.next();
			try {
				cantidadFichas = Integer.parseInt(cantidad);
				break;
			}
			catch(Exception e) {
				System.out.println("Escribe un valor valido");
			}
		}while(true);
		Jugador jNuevo = new Jugador(nombre,cantidadFichas);
		System.out.println("Jugador "+nombre+" anadido al juego\n");
		return jNuevo;
	}
	
	public static ArrayList<Jugador> listaJugadores(){//Crea una lista de jugadores
		ArrayList<Jugador> jugadores = new ArrayList();
		int numeroJugadores;
		do {
			System.out.print("Numero de jugadores:");
			String valorNumeroJugadores = sc.next();
			try {
				numeroJugadores = Integer.parseInt(valorNumeroJugadores);
				if(numeroJugadores>=1) {
					System.out.println();
					break;
				}
				System.out.println("Ingrese al menos un jugador");
			}
			catch(Exception e) {
				System.out.println("Error. Introduce un valor correcto");
			}
		}while(true);
		
		for(int i=0;i<numeroJugadores;i++) {
			jugadores.add(jugadorNuevo((i+1)));
		}
		
		return jugadores;
	}
	
	public static BlackJack mesaNuevaBJ() {//Crea una nueva mesa de BlackJack
		System.out.println("Bienvenido al juego de mesa BlackJack");
		BarajaPoker baraja =  barajaNueva();
		baraja.barajar();
		ArrayList<Jugador> jugadores = listaJugadores();
		Crupier c = crupierNuevo();
		
		return new BlackJack(jugadores,baraja,c);
		
	}

	
	public static void main(String[] args) {
		
		BlackJack mesa1 =  mesaNuevaBJ()
				;
		int numeroRondas = 12;
		
		for(int i=1;i<=numeroRondas;i++) {
			System.out.println("\nRonda "+i+":");
			mesa1.rondaMesa();
		}
		
		System.out.println("Se termino las rondas");
		
	}
	
	//Mesa para pruebas rapidas
	public static BlackJack mesaPruebas() {
		BarajaPoker baraja = new BarajaPoker(1);
		baraja.barajar();
		
		ArrayList<Jugador> jugadores = new ArrayList();
		jugadores.add(new Jugador("Aldair",100));	
		jugadores.add(new Jugador("Jaime",100));
		jugadores.add(new Jugador("Joel",100));
		Crupier c = new Crupier("Crupier",1000);
		BlackJack mesaPrueba = new BlackJack(jugadores,baraja,c);
		
		return mesaPrueba;
	}

}
