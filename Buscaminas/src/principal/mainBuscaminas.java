package principal;

import java.util.Scanner;

import datosUsuario.Usuario;
import enums.dificultad;
import juegoBuscaminas.TablaBuscaminas;

/* Proyecto 3. Buscaminas
 * GONZALEZ ROBLES ALDAIR - M17171128
 * Tecnologías de la programación. Agosto - Diciembre 2023
 * Proyecto individual (20 de Octubre del 2023)
 */

public class mainBuscaminas {
	static Scanner sc = new Scanner(System.in);
	
	static dificultad escogerDificultad() {//Escoge dificultad
		boolean opcionNoValida = true;
		int opcion=0;
		dificultad dificultadEscogida = null;
		do {
			System.out.println("Nivel de dificultad:");
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
	
	
	static Usuario crearUsuario() {//Crea un usuario
		String nombre;
		System.out.print("Nombre del jugador:");
		nombre = sc.next();
		dificultad d = escogerDificultad();
		
		return new Usuario(nombre,d);
	}
	

	public static void main(String[] args) {
		String opcion = "";
		
		do {
		System.out.println("Buscaminas: ");
		System.out.println("1-Iniciar juego");
		System.out.println("2- Tabla de puntajes");
		System.out.println("3- Salir");
		System.out.print("Opcion:");
		opcion = sc.next();
		System.out.println();
		if(opcion.equals("1")) {//Iniciar juego
			Usuario uActual = crearUsuario();
			TablaBuscaminas t = new TablaBuscaminas(uActual);
			t.partidaBuscaminas();
			t = null;
		}else if(opcion.equals("2")) { //Tabla de puntajes
			TablaBuscaminas t = new TablaBuscaminas();
			System.out.println(t.tablaPuntajes());
			t = null;
		}else if(opcion.equals("3")) { //Salir del juego
			break;
		}else {
			System.out.println("Escoja una opcino valida\n");
		}
		
		//System.gc(); //Inicia recolector de basura
		}while(true);
		
		sc.close();
	}
	



}
