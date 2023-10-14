package principal;

import java.util.Scanner;

import enums.dificultad;
import juegoBuscaminas.Casillas;
import juegoBuscaminas.TablaBuscaminas;

public class mainBuscaminas {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int filaU, columnaU;
		TablaBuscaminas t1 = new TablaBuscaminas(dificultad.principiante.getLargoTabla(),dificultad.principiante.toString());
		Casillas c = t1.getCasilla(0, 0);
		System.out.println(c.getNumero());
		boolean sigueVivo = true;
		do {
			System.out.println(t1);
			System.out.print("Fila: ");
			filaU = sc.nextInt();
			System.out.print("columna: ");
			columnaU = sc.nextInt();
			sigueVivo = t1.seleccionarCasilla(filaU, columnaU);
		}while(sigueVivo);
		
		System.out.println(t1);
	}
	
	static void recursivo(int x) {
		if(x<0) {
			return;
		}
		
		System.out.println("va "+x);
		recursivo(x-1);
	}
	
	static void numeroRandom() {
		 int numeroAleatorio = (int) (Math.random()*25+1);
		 
		 System.out.println("Numero random: "+numeroAleatorio);
	}
	
	

}
