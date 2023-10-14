package juegoBuscaminas;

import enums.dificultad;

public class Buscaminas {
	/*
	private String nivelDificultad;
	private Tabla tabla;
	public Buscaminas(String nivelDificultad) {
		this.nivelDificultad= nivelDificultad;
	}
	
	private void numeroRandom() {
		 int numeroAleatorio = (int) (Math.random()*25+1);
		 System.out.println("Numero random: "+numeroAleatorio);
	}
	
	private void generarTabla() {
		double porcentaje;
		int numeroMinas;
		if(this.nivelDificultad.equals(dificultad.principiante)){
			porcentaje = 0.1;
			this.tabla.setDatos(dificultad.principiante.getTabla());;
			numeroMinas = (int)(porcentaje*dificultad.principiante.getTamanoTabla());
			
		}
		else if(this.nivelDificultad.equals(dificultad.facil)) {
			porcentaje = ((int) (Math.random()*20+15))/10;
		}else if(this.nivelDificultad.equals(dificultad.normal)) {
			porcentaje = ((int) (Math.random()*25+20))/10;
		}else {
			porcentaje = ((int) (Math.random()*40+25))/10;
		}
	}
	
	private void generarMinas(int cantidad) {
		if(cantidad<=0) {
			return;
		}
		int posMinaFila = (int) (Math.random()*(this.tabla.fila-1)+0);
		int posMinaColumna = (int) (Math.random()*(this.tabla.columna-1)+0);
		if(this.tabla.getDatos()[posMinaFila][posMinaColumna]) {
			this.tabla.setDatoTrue(posMinaFila, posMinaColumna);
			generarMinas(cantidad-1);
		}
		generarMinas(cantidad);
		
	}
	*/
}
