package LecturaDatos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Constantes.CM;

import java.util.Map.Entry;



public class Lectortxt {
	String Null = "";
	private final String NombreArchivo = "codigo2.txt";
	private final String NombreCarpeta = "codigos";
	private final String Ruta = NombreCarpeta+ "\\"+NombreArchivo;
	
	private HashMap<String, Stack<String> > funciones = new HashMap<>();
	private ArrayList<String> literalesGlobales = new ArrayList<String>();
	private String nombrePrograma="";
	
	private Stack<String> llavesFuncion = new Stack<String>();
	private Stack<String> llavesDemas = new Stack<String>();
	
	public ArrayList<String> leerTexto() {//Lista de todos los usuarios y sus datos
		ArrayList<String> informacion = new ArrayList<String>();
		try(BufferedReader bf = new BufferedReader(new FileReader(Ruta))){//Lee los datos
			String s;
			while((s = bf.readLine())!=null) {//Hasta que no haya linea para leer
				informacion.add(s);//Se anade el usuario a la lista
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		//Se regresa el ArrayList en Array
		return informacion;
		
	}
	
	public void iniciar() {
		/*
		for(String datos:leerTexto()) {
			System.out.println(datos);
		}
		//*/
		organizarPrograma(leerTexto());
		getDatosTotales();
	}
	
	public void getDatosTotales() {
		System.out.println("Nombre del programa:"+this.nombrePrograma);
		System.out.println("Datos del programa (funciones):");
		Stack<String> primero =  funciones.get("inicio");
		

		for(Entry<String, Stack<String>> entry: funciones.entrySet()) {
				System.out.println("Funcion "+entry.getKey()+":");
				primero =  funciones.get(entry.getKey());
				while(true) {
					if(primero.isEmpty()) {
						break;
					}
					System.out.println("-->"+primero.pop());
				}
				System.out.println("\n");
		}
		System.out.println("Datos globales:");
		for(String datosGlobales:literalesGlobales) {
			System.out.println(datosGlobales);
		}
	}
	
	public void organizarPrograma(ArrayList<String> programa) {
		boolean anadirComandos= false;
		String nombreFuncion="";
		Stack<String> datos = new Stack<String>();
		for(String comandos:programa) {
			if(isPrograma(comandos)) {
				this.nombrePrograma = comandos.replace("Programa", "");
				this.nombrePrograma = this.nombrePrograma.replace("{", "");
				this.nombrePrograma = this.nombrePrograma.replace(" ", "");
			}
			else if(isFuncion(comandos)) {
				for (char caracter : comandos.toCharArray()) {
					if(!((caracter == '{') || (caracter == ' ') )) {
						nombreFuncion+=caracter;
					}
				}
				anadirComandos = true;
		}else if(terminaFuncion(comandos)) {
			funciones.put(nombreFuncion, datos);
			nombreFuncion = "";
			datos = new Stack<String>();
			anadirComandos = false;
		}else if(anadirComandos) {
			datos.push(comandos);
		}else if(terminaPrograma(comandos)) {
			break;
		}else {
			literalesGlobales.add(comandos);
		}
		
	}
	}
	

	


	public boolean isPrograma(String linea) {
		return linea.contains("Programa");
	}
	
	public boolean terminaPrograma(String linea) {
		return (linea.contains("}")) &&(llavesDemas.isEmpty() && llavesFuncion.isEmpty());
	}
	

	public boolean haveKey(String linea) {
		for (char caracter : linea.toCharArray()) {
			if(caracter == '{') {
				return true;
			}
				
		}
		return false;
	}
	
	public boolean terminaFuncion(String linea) {
		for (char caracter : linea.toCharArray()) {
			if(caracter == '}')
				if(llavesDemas.isEmpty()) {
					if(!terminaPrograma(linea)) {
						llavesFuncion.pop();
						return true;
					}
					
				}else {
					llavesDemas.pop();
				}
				
		}
		return false;
		}
	
	

	
	/*
	public Stack<String> stackFinal(Stack<String> pila){
		Stack<String> pilaF = new Stack<String>();
		while(true) {
			if(pila.isEmpty()) {
				return pilaF;
			}
			pilaF.push(pila.pop());
		}
		
	}
	//*/
	public boolean isKeyNotFunction(String texto) {
		for(int i=0; i<CM.values().length;i++) {
			if(texto.contains(CM.values()[i].toString())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFuncion(String texto) {
		if(!(isKeyNotFunction(texto)) && haveKey(texto)) {
			llavesFuncion.push("{");
			return true;
		}else if(haveKey(texto)) {
			llavesDemas.push("{");
		}
		return false;
	}
	

	
	
	
	
	

}
