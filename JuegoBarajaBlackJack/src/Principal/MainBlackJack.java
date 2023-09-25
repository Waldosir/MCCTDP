package Principal;

import java.util.ArrayList;
import java.util.Scanner;

import JuegoMesa.BarajaPoker;
import Personas.Jugador;

public class MainBlackJack {
	static Scanner sc = new Scanner(System.in);//Leer datos
	
	public static void turnoJugador(Jugador j, BarajaPoker b) {
		boolean turnoJugador = true;
		do {
			System.out.println("\nJugador "+j.getNombre()+" con fichas "+j.getNumeroFichas()+"\nOpciones:");
			System.out.println("1- Pedir carta");
			System.out.println("2- Subir apuesta");
			System.out.println("3- Quedarte");
			System.out.println("4- Retirarte");
			String Opcion = sc.next();
			try{
				switch(Integer.parseInt(Opcion)) {
				case 1:
					j.setCartas(b.DarCarta());
					turnoJugador = false;
					break;
				case 2:
					System.out.println("No hace nada aun XD");
					break;
				case 3:
					turnoJugador = false;
					break;
				case 4:
					j.setEstadoEnJuego(false);
					turnoJugador = false;
					break;
				default:
					System.out.println("No es una opcion. Escoger una opcion valida.\n");
				}
			}
			catch(Exception e) {
				System.out.println("no hay cartas en el mazo para tomar");
			}
			
			
		}
		while(turnoJugador);
	}
	
	
	public static void main(String[] args) {
		BarajaPoker p = new BarajaPoker();
		ArrayList<Jugador> j = new ArrayList();
		Jugador j1 = new Jugador("Aldair",25);
		Jugador j2 = new Jugador("Felipe",30);
		Jugador j3 = new Jugador("Joel",45);
		j.add(j1); j.add(j2); j.add(j3);
		
		
		for(int i = 0;i<j.size();i++) {
			j.get(i).setCartas(p.DarCarta());
			j.get(i).setCartas(p.DarCarta());
		}
		
		for(int i=0;i<j.size();i++) {
			turnoJugador(j.get(i),p);
		}
		
		System.out.println("Valores finales: ");
		
		int sumar=0;
		for(int i=0;i<j.size();i++) {
			if(j.get(i).getEstadoEnJuego()) {
				System.out.println(j.get(i).toString());
			}
			
		}
		
		System.out.println("Valor sumado:"+sumar);
		/*
		p.Barajar();
		System.out.println("--------Empezado de nuevo--------");
		for(int i = 0;i<52;i++) {
			p.DarCarta();
		}
		
		p.Cortar();
		p.Barajar();
*/
	}

}
