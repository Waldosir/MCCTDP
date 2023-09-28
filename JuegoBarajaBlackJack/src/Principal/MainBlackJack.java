package Principal;

import java.util.ArrayList;
import java.util.Scanner;

import Baraja.CartasPoker;
import JuegoMesa.BarajaPoker;
import JuegoMesa.BlackJack;
import Personas.Crupier;
import Personas.Jugador;

public class MainBlackJack {
	static Scanner sc = new Scanner(System.in);//Leer datos
	
	public static Jugador jugadorNuevo(int numero, BarajaPoker b) {
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
	
	public static ArrayList<Jugador> listaJugadores(BarajaPoker b){
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
			jugadores.add(jugadorNuevo((i+1),b));
		}
		
		return jugadores;
	}

	
	public static void main(String[] args) {
		
		
		BlackJack mesaP = mesaPruebas();
		
		for(int i=1;i<=12;i++) {
			System.out.println("\nRonda "+i+":");
			mesaP.RondaMesa();
		}
		
		System.out.println("Se termino las rondas");
		
	}
	
	
	public static BlackJack mesaPruebas() {
		BarajaPoker baraja = new BarajaPoker(1);
		baraja.Barajar();
		
		ArrayList<Jugador> jugadores = new ArrayList();
		jugadores.add(new Jugador("Aldair",100));	
		jugadores.add(new Jugador("Jaime",100));
		jugadores.add(new Jugador("Joel",100));
		Crupier c = new Crupier();
		BlackJack mesaPrueba = new BlackJack(jugadores,baraja,c);
		
		return mesaPrueba;
	}

}
