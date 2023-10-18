package principal;

import java.util.ArrayList;
import java.util.Scanner;

import datosUsuario.Usuario;
import enums.dificultad;
import juegoBuscaminas.Casillas;
import juegoBuscaminas.TablaBuscaminas;

//nivel principiante X puntos siempre que te tardes Y minutos

public class mainBuscaminas {
	static Scanner sc = new Scanner(System.in);
	
	static void espera() {
		  try {

	             Thread.sleep(2000);

	       } catch (InterruptedException e) {

	       }
	}
	
	
	static dificultad escogerDificultad(Usuario u) {
		boolean opcionNoValida = true;
		int opcion=0;
		dificultad dificultadEscogida = null;
		do {
			System.out.println("\nJugador "+u.getNombre());
			System.out.println("1- Dificultad Principiante");
			System.out.println("2- Dificultad facil");
			System.out.println("3- Dificultad normal");
			System.out.println("4- Dificultad experto");
			System.out.print("Opcion:");
			try {
				opcion = Integer.parseInt(sc.next());
			}catch(NumberFormatException e) {
			}
				
			switch(opcion) {
			case 1://Principiante
				dificultadEscogida = dificultad.principiante;
				opcionNoValida = false;
				break;//facil
			case 2:
				dificultadEscogida = dificultad.facil;
				opcionNoValida = false;
				break;
			case 3://Normal
				dificultadEscogida = dificultad.normal;
				opcionNoValida = false;
				break;
			case 4://Dificil
				dificultadEscogida = dificultad.dificil;
				opcionNoValida = false;
				break;
			default:
				System.out.println("Opcion no valida. Ingrese un valor apropiado.");
			}
			
		}while(opcionNoValida);
		return dificultadEscogida;
	}
	
	static void turnoIndividual(TablaBuscaminas t, Usuario u, int nivel) {
		int opcion = 0; 
		int filaU, columnaU;
		boolean sigueVivo = true;
		do {
			System.out.println("Turno jugador "+u.getNombre());
			System.out.println("Nivel "+nivel);
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
			try {
				switch(opcion) {
				case 1://Abrir una casilla
					System.out.println("Opcion escogida: Abrir");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					sigueVivo = t.seleccionarCasilla(filaU, columnaU, u);
					break;
				case 2://bloquear casilla
					System.out.println("Opcion escogida: Bloquear");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					//Solo se puede bloquear si la casilla esta tapada
					if(t.getCasilla(filaU, columnaU).condicionColocarMarca()) {
						t.getCasilla(filaU, columnaU).setBloquear(true);
					}else {
						System.out.println("No se puede bloquear una casilla destapada");
					}
					break;
				case 3://Desbloquear casilla
					System.out.println("Opcion escogida: Desbloquear");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					if(t.getCasilla(filaU, columnaU).getBloquear()) {
						t.getCasilla(filaU, columnaU).setMarcaBomba(false);
					}
					break;
				case 4://Marca bomba
					System.out.println("Opcion escogida: Marca mina");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					//Solo se puede marcar si la casilla esta tapada
					if(t.getCasilla(filaU, columnaU).condicionColocarMarca()) {
						t.getCasilla(filaU, columnaU).setMarcaBomba(true);
					}else {
						System.out.println("No se puede marcar una casilla destapada");
					}
					
					break;
				case 5:
					System.out.println("Opcion escogida: Desmarcar mina");
					filaU = escoger("Fila");
					columnaU = escoger("columna");
					if(t.getCasilla(filaU, columnaU).getMarcaBomba()) {
						t.getCasilla(filaU, columnaU).setMarcaBomba(false);
					}
					
					break;
				default:
					System.out.println("Opcion no valida");
				}
				
			}catch(Exception NullPointerException) {
				System.out.println("Opcion no valida");
				
			}
			
			
		}while(sigueVivo);
		espera();
		System.out.println(t.toString());//Imprime la tabla final.
		espera();

	}
	
	static Usuario crearUsuario(int numeroJugador) {//Crea un usuario
		System.out.print("Nombre del jugador "+(numeroJugador+1)+":");

		return new Usuario(sc.next());
	}
	
	static ArrayList<Usuario> listaUsuarios(){//Crea una lista de usuarios
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		int cantidad;
		do {
			cantidad = escoger("Numero de jugadores");
			if(cantidad>0) {//Se cumple la condicion se sale del ciclo
				break;
			}
			System.out.println("Numero de jugadores debe de ser mayor a 0");
		}while(true);
		
		for(int i=0; i<cantidad;i++) {//Añades usuarios
			lista.add(crearUsuario(i));
		}
		
		return lista;
	}
	
	public static void main(String[] args) {

		ArrayList<Usuario> listaJugadores = listaUsuarios(); //Crea lista de jugadores
		dificultadJugador(listaJugadores);//Asigna dificultad a cada jugador
		tablaBuscaminasJugadores(listaJugadores);//Asigna tabla a cada jugador
		turnoJugadores(listaJugadores);//Turno de cada jugador
		verTablaFinal(listaJugadores); //Tabla final de puntaje

		sc.close();
	}
	
	static void dificultadJugador(ArrayList<Usuario> lista) {//Cada jugador escoge la dificultad para empezar
		for(int i=0;i<lista.size();i++) {
			Usuario uActual = lista.get(i);
			uActual.setDificultadU(escogerDificultad(uActual));
		}
	}
	
	static void turnoJugadores(ArrayList<Usuario> lista) {//Turno de todos los jugadores
		for(int i=0;i<lista.size();i++) {
			int turnos = 4;//4 turnos maximo
			Usuario uActual = lista.get(i);
			do {
				uActual.sumarNivel();//Suma un nivel (Empieza en 0 y termina en el Max de turnos por jugador)
				turnoIndividual(uActual.getTabla(),uActual, uActual.getNivel()); //Turno del jugador
				if(uActual.getCondicionJuego()) {//Si gana
					System.out.print("Desea cambiar la dificultad? (s/n) :");
					String opcion = sc.next();
					if(opcion.equals("s")) {//Cambia la dificultad
						uActual.setDificultadU(escogerDificultad(uActual));
					}
					dificultad d = uActual.getDificultadU();//Dificultad y nueva tabla
					uActual.setTablaBuscaminas(new TablaBuscaminas(d.getBaseTabla(),d.getAlturaTabla(),d.toString()));
					turnos--;//Un turno menos del maximo
				}
				//Tiene turnos y el jugador sigue en pie.
			}while(turnos>0 && uActual.getCondicionJuego());
			
		}
	}
	
	static void tablaBuscaminasJugadores(ArrayList<Usuario> lista) {//Coloca una tabla para cada jugador
		for(int i=0;i<lista.size();i++) {
			Usuario u = lista.get(i);
			dificultad d = u.getDificultadU();
			u.setTablaBuscaminas(new TablaBuscaminas(d.getBaseTabla(),d.getAlturaTabla(),d.toString()));	
		}
	}
	
	static void verTablaFinal(ArrayList<Usuario> lista) {//Tabla final de jugadores y sus puntajes
		System.out.println("Tabla de puntuacion:");
		for(int i=0;i<lista.size();i++) {
			System.out.println(lista.get(i));
		}
		
	}
	
	static int escoger(String tag) { //Opcion de escoger un numero entero
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
	


}
