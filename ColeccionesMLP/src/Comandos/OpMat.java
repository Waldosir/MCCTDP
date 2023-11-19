package Comandos;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import Constantes.LITVAR;
import Constantes.OPERADOR;

public class OpMat {
	private HashMap<String,Integer> tablaInt = new HashMap<>();
	private HashMap<String,Double> tablaDouble = new HashMap<>();
	
	private Stack<Double> pilaNumero = new Stack<Double>();
	private Stack<String> pilaOperador = new Stack<String>();
	
	//Inicializa literales
	public void inicializarLiterales(String tipo, String nombre) {
		if(!nombre.equals("salto")) {
			if(tipo.equals(LITVAR.ENTERO.toString())) {
				tablaInt.put(nombre, 0);
			}else if(tipo.equals(LITVAR.REAL.toString())) {
				tablaDouble.put(nombre, 0.0);
			}
		}
		
	}
	
	public void asignarValorLiterales(String Literales, double valor) {
		System.out.println();
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			if(entry.getKey().equals(Literales)) {
				tablaInt.put(Literales, (int) valor);
			}
		}
		
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			if(entry.getKey().equals(Literales)) {
				tablaDouble.put(Literales, valor);
			}
		}
	}
	
	
	public double buscarLiteral(String nombre) {
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		System.out.print(nombre+" no existe.");
		return 0;
	}
	
	public boolean isDatoExist(String nombre) {
		for(Entry<String, Integer> entry: this.tablaInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		
		for(Entry<String, Double> entry: this.tablaDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		System.out.println("El dato "+nombre+ " no existe");
		return false;
	}
	
	public double realizaroperacion(double y, double x, String operador) {
		double valor = 0;
		switch(operador) {
		case "+":
			valor = x+y;
			break;
		case "-":
			valor = x-y;
			break;
		case "*":
			valor = x*y;
			break;
		case "/":
			valor = x/y;
			break;
		case "^":
			valor = Math.pow(x, y);
			break;		
		}		
		return valor;

	}
	

	public void colocarOperadorEnPila(String operador) {
		if(pilaOperador.isEmpty()) {
			pilaOperador.push(operador);
		} else if(OPERADOR.isMayor(operador, pilaOperador.peek())) {
			pilaOperador.push(operador);
		}
		else {
			double numero = realizaroperacion(
					pilaNumero.pop(),pilaNumero.pop(), pilaOperador.pop());
			pilaNumero.push(numero);
			pilaOperador.push(operador);
		}
	}
	
	public void colocarDatoEnPila(String valor) {
		
		if(OPERADOR.isOperador(valor)) {
			colocarOperadorEnPila(valor);
		}
		else if(isDatoExist(valor)) {
			pilaNumero.push(buscarLiteral(valor));
		}else {
			System.out.println("No se anadio a la pila. Compruebe el nombre de "+valor);
		}
		

		
	}
	
	public void colocarDatoEnPila(double valor) {
		pilaNumero.push(valor);
	}
	
	public void imprimeValorTope() {
		if(pilaOperador.isEmpty()) {
			System.out.println("Valor tope en operador es nulo");
		}else {
			System.out.println("Valor tope en operador: "+pilaOperador.peek());
		}
		if(pilaNumero.isEmpty()) {
			System.out.println("Valor tope en numero es");
		}else {
			System.out.println("Valor tope en numero es: "+pilaNumero.peek());
		}
		
	}
	
	public void RecorreDatosTablas() {
		System.out.println("Tabla entera de valores:");
		System.out.println("Enteros:");
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
		System.out.println("Reales:");
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
	}

	public double terminarOperacion() {
		
		while(true) {
			if(pilaOperador.isEmpty()) {
				return pilaNumero.pop();
			}else {
				pilaNumero.push(realizaroperacion(
						pilaNumero.pop(),pilaNumero.pop(),pilaOperador.pop()));
			}
		}
		
	}
	
}
