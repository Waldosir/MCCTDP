package datosUsuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;


public class GestionUsuarios {
	String Null = "";
	final String NombreArchivo = "puntajes.txt";
	final String NombreCarpeta = "carpetaPuntajes";
	final String Ruta = NombreCarpeta+ "\\"+NombreArchivo;

	//Agrega un usuario
	public boolean agregar(String usuario, int puntaje) {
		if(buscarUsuario(usuario)==null) {//Si no encuentra a un usuario igual
			Usuario u = new Usuario(usuario,puntaje);//Crea un usuario
			/*
			 * FileOutPutStream permite la salida de datos. Como FileWriter, solo que permite 
			 * flujo de Bytes sin formato
			 * 
			 * El constructor escribe en un String, con valor booleano true
			 */
			try(FileOutputStream fos = new FileOutputStream(Ruta,true); 
					PrintStream salida = new PrintStream(fos)){
			//Imprime Nombre|Puntaje
				salida.println(u.getNombre()+"|"+u.getPuntaje());	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
		
	//Busca el usuario y devuelve todos los datos del usuario
	public Usuario buscarUsuario(String nombreUsuario) {
		Usuario u = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(Ruta))){//Encuentra el archivo
			String s;
			while((s = bf.readLine())!=null) {//Lee una linea entera y hasta que la línea ya no tenga nada
				String[] datos = s.split("[|]");//Array de todos los datos seccionados
				if(datos[1].equals(nombreUsuario)) {//Si encuentra el nombre se crea el objeto con sus datos
					u = new Usuario(datos[0],Integer.parseInt(datos[1]));
					break;
				}
			}
		}catch(FileNotFoundException ex) { //Excepction no encuentra el archivo
			crearArchivo(); //Crea el archivo
		}
		catch(IOException ex) { //Otra clase de error
			System.out.println("Error");
			ex.printStackTrace();
		}
		
		
		
		return u;//Si no encuentra nada, regresa null
	}
	
	private void crearArchivo() {
		try {
		      File myObj = new File(Ruta); //Archivo en objeto
		      if (myObj.createNewFile()) { //Si se crea el archivo
		        //System.out.println("Archivo creado: " + myObj.getName());
		      } else {//Si ya existe el archivo
		        //System.out.println("Ya existe el archivo.");
		      }
		    }
		catch (IOException e) {//En dado caso no se pueda crear el archivo
		      System.out.println("Un error ha ocurrido.");
		      e.printStackTrace();
		    }
		  }
		
	public boolean eliminar(String usuario) {//Elimina un usuario
		boolean res = false;
		Usuario[] todos = recuperarUsuarios();//Saca todos los datos
		try(PrintStream out = new PrintStream(Ruta);){//Crea un nuevo archivo con esos datos
			for(Usuario p:todos) {
				if(!usuario.equals(p.getNombre())) {//Imprime todos los datos excepto el del usuario a eliminar
					out.println( p.getNombre()  +"|"+p.getPuntaje());
				}
				else {
					res = true;//Se logró eliminar
				}
			}
			
	
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;//Si no se logra eliminar
	}
	
	public Usuario[] recuperarUsuarios() {//Lista de todos los usuarios y sus datos
		ArrayList<Usuario> existentes = new ArrayList<Usuario>();
		Usuario u = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(Ruta))){//Lee los datos
			String s;
			while((s = bf.readLine())!=null) {//Hasta que no haya linea para leer
				String[] datos = s.split("[|]");//Reagrupa los datos con su respectivo separador
				//Crea un usuario con esos datos
				u = new Usuario(datos[0],Integer.parseInt(datos[1]));
				existentes.add(u);//Se anade el usuario a la lista
				}
			}catch(FileNotFoundException ex) { //Excepction no encuentra el archivo
				crearArchivo(); //Crea el archivo
			} 
		catch(IOException ex) {
			ex.printStackTrace();
		}
		//Se regresa el ArrayList en Array
		return existentes.toArray(new Usuario[0]);
		
	}
	
	public void borraTodo(){
		try(PrintStream out = new PrintStream(Ruta);){//Crea un nuevo archivo con esos datos
			FileOutputStream writer = new FileOutputStream(Ruta);
			writer.write(("").getBytes()); //Escribe un archivo sin caracteres
			writer.close();
			
		}catch(FileNotFoundException ex) { //Excepction no encuentra el archivo
			crearArchivo(); //Crea el archivo
		} 
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void actualizarLista(ArrayList<Usuario> listaUsuarios) {
		borraTodo(); //Archivo limpio
		try {
			for(int i=0; i<10;i++) { //Agrega solo a los 10 usuarios
				agregar(listaUsuarios.get(i).getNombre(),listaUsuarios.get(i).getPuntaje());
			}
		}catch(Exception NullPointerException) { //Si hay menos de 10 usuarios
			
		}
		
	}
		
	
	
}
