package principal;

import java.util.Scanner;

import enums.dificultad;
import juegoBuscaminas.TablaBuscaminas;

public class mainBuscaminas {
	static Scanner sc = new Scanner(System.in);
	
	static dificultad escogerDificultad() {
		boolean opcionNoValida = true;
		int opcion=0;
		dificultad dificultadEscogida = null;
		do {
			System.out.println("\n1- Dificultad Principiante");
			System.out.println("2- Dificultad facil");
			System.out.println("3- Dificultad normal");
			System.out.println("4- Dificultad experto");
			System.out.print("Opcion:");
			try {
				opcion = Integer.parseInt(sc.next());
			}catch(NumberFormatException e) {
			}
				
			switch(opcion) {
			case 1:
				dificultadEscogida = dificultad.principiante;
				opcionNoValida = false;
				break;
			case 2:
				dificultadEscogida = dificultad.facil;
				opcionNoValida = false;
				break;
			case 3:
				dificultadEscogida = dificultad.normal;
				opcionNoValida = false;
				break;
			case 4:
				dificultadEscogida = dificultad.dificil;
				opcionNoValida = false;
				break;
			default:
				System.out.println("Opcion no valida. Ingrese un valor apropiado.");
			}
			
		}while(opcionNoValida);
		return dificultadEscogida;
	}
	
	static void opcionJuego(TablaBuscaminas t) {
		int opcion = 0; 
		int filaU, columnaU;
		boolean sigueVivo = true;
		do {
			System.out.println(t.toString());
			System.out.println("1- Abrir");
			System.out.println("2- Bloquear");
			System.out.println("3- Desbloquear");
			System.out.println("4- Colocar marca Mina");
			System.out.println("5- Quitar marca Mina");
			System.out.print("Opcion:");
			try {
				opcion = Integer.parseInt(sc.next());
			}catch(NumberFormatException e) {
				opcion = 0;
			}
			
			switch(opcion) {
			case 1://Abrir una casilla
				System.out.println("Opcion escogida: Abrir");
				filaU = escoger("Fila");
				columnaU = escoger("columna");
				sigueVivo = t.seleccionarCasilla(filaU, columnaU);
				break;
			case 2://bloquear casilla
				System.out.println("Opcion escogida: Bloquear");
				filaU = escoger("Fila");
				columnaU = escoger("columna");
				if(t.getCasilla(filaU, columnaU).getCasillaTapada()) {
					t.getCasilla(filaU, columnaU).setBloquear(true);
				}else {
					System.out.println("No se puede bloquear una casilla destapada");
				}
				
				break;
			case 3://Desbloquear casilla
				System.out.println("Opcion escogida: Desbloquear");
				filaU = escoger("Fila");
				columnaU = escoger("columna");
				t.getCasilla(filaU, columnaU).setBloquear(false);
				break;
			case 4://Marca bomba
				System.out.println("Opcion escogida: Marca mina");
				filaU = escoger("Fila");
				columnaU = escoger("columna");
				t.getCasilla(filaU, columnaU).setMarcaBomba(true);
				break;
			case 5:
				System.out.println("Opcion escogida: Desmarcar mina");
				filaU = escoger("Fila");
				columnaU = escoger("columna");
				t.getCasilla(filaU, columnaU).setMarcaBomba(false);
				break;
			default:
				System.out.println("Opcion no valida");
			}
			
		}while(sigueVivo);
		t.destaparTodo();
		System.out.println(t.toString());

	}
	
	static void juego(TablaBuscaminas t) {
		boolean sigueVivo = true;
		int filaU, columnaU;
		do {
			System.out.println(t);
			System.out.print("Fila: ");
			filaU = sc.nextInt();
			System.out.print("columna: ");
			columnaU = sc.nextInt();
			sigueVivo = t.seleccionarCasilla(filaU, columnaU);
		}while(sigueVivo);
	}
	
	public static void main(String[] args) {
		
		dificultad dUsuario = escogerDificultad();
		TablaBuscaminas t1 = new TablaBuscaminas(dUsuario.getLargoTabla(),dUsuario.toString());
		opcionJuego(t1);
		sc.close();

	}
	
	static int escoger(String tag) {
		boolean noValida = true;
		int opcion=0;
		do {
			try {
				System.out.print(tag+": ");
				opcion = sc.nextInt();
				noValida = false;
			}catch(NumberFormatException e) {
			System.out.println("Opcion no valida.");	
			}
		} while(noValida);
		
		
		return opcion;
	}
	


}
