package Comandos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Ejecutor {
	private HashMap<String,Integer> tablaGInt = new HashMap<>();
	private HashMap<String,Double> tablaGDouble = new HashMap<>();
	
	public Ejecutor(String nombreDelPrograma,HashMap<String, Stack<String> > funciones,ArrayList<String> variables ) {
		
	}
	
	public void initLitGlobal(String texto) {
		String[] dato = texto.split(" ");
			if(isNumero(dato[2])) {

		}
		
	}
	
	private boolean isNumero(String texto) {
		for(int i=0;i<10;i++) {
			if((texto.charAt(0)+"").equals(i+"")) {
				return true;
			}
			
		}
		return false;
	}
	
}
