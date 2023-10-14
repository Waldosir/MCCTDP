package principal;

import enums.dificultad;
import juegoBuscaminas.Casillas;
import juegoBuscaminas.TablaBuscaminas;

public class mainBuscaminas {

	public static void main(String[] args) {
		TablaBuscaminas t1 = new TablaBuscaminas(dificultad.principiante.getLargoTabla(),dificultad.principiante.toString());
		Casillas c = t1.getCasilla(0, 0);
		System.out.println(c.getNumero());
		
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
