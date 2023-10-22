package logica;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import Datos.Usuarios;


public class GestionUsuarios {
	String Null = "";
	String ruta = "DatosBiblioteca\\Usuarios.txt";
	
	HashMap<String,Usuarios> usuario;
	
	public GestionUsuarios() {
		usuario = new HashMap<>();
	}
	
	public int MayorID() {//Para saber el maximo de veces para ordenartxt
		Usuarios[] todos = recuperarUsuarios();
		int x = 0;
		for(Usuarios p:todos) {
			if(x<p.getID()) {
				x = p.getID();
			}
		}
		return x;
	}
	
	public void OrdenarTxt() {//Ordena de menor a mayor los usuarios dependiendo su ID
		Usuarios[] todos = recuperarUsuarios();
		Usuarios U = new Usuarios();
		if(todos.length>0) {
			for(int i = 0;i<=MayorID();i++) {
				Usuarios u = U.buscarIDUsuario(i);
				if(u!=null) {
					u.eliminar(u.getUsuario());//Lo elimina y luego lo vuelve a agregar con todos los datos iguales
					u.agregar(u.getID(), u.getUsuario(), u.getContrasena(), u.getNumeroLibrosPrestado());
				}
			}
		}
		
	}

	public boolean agregar(String usuario, String contrasena) {//Agregar usuarios
		if(buscarUsuario(usuario)==null) {//Si no encuentra el usuario
			Usuarios u = new Usuarios(AsignarID(),usuario,contrasena);//Usuario basico
			try(FileOutputStream fos = new FileOutputStream(ruta,true);
					PrintStream salida = new PrintStream(fos)){//Crea en una nueva linea los datos del usuario
			salida.println(u.getID()+"|"+u.getUsuario()+"|"+u.getContrasena()+"|"+u.getNumeroLibrosPrestado());	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;//Lo creo
		}else {
			return false;//Ya existe el usuario
		}
	}
	
	//Lo mismo que el anterior agregar, solo que es usado para modificar usuarios
	public boolean agregar(int ID, String usuario, String contrasena, int LibrosPrestamo) {
		if(buscarUsuario(usuario)==null) {
			Usuarios u = new Usuarios(ID,usuario,contrasena,LibrosPrestamo);
			try(FileOutputStream fos = new FileOutputStream(ruta,true);
					PrintStream salida = new PrintStream(fos)){
			salida.println(u.getID()+"|"+u.getUsuario()+"|"+u.getContrasena()+"|"+u.getNumeroLibrosPrestado());	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
		
	//Busca el usuario y devuelve todos los datos del usuario
	public Usuarios buscarUsuario(String nombreUsuario) {
		Usuarios u = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){//Encuentra el archivo
			String s;
			while((s = bf.readLine())!=null) {//Lee una linea entera y hasta que la línea ya no tenga nada
				String[] datos = s.split("[|]");//Array de todos los datos seccionados
				if(datos[1].equals(nombreUsuario)) {//Si encuentra el nombre se crea el objeto con sus datos
					u = new Usuarios(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]));
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return u;//Si no encuentra nada, regresa null
	}
	
	public Usuarios buscarIDUsuario(int ID) {//Buscar como el anterior, pero ahora con el ID
		Usuarios u = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				if(datos[0].equals(ID+"")) {//Dado que ID es un entero, ID se transforma en String y compara
					u = new Usuarios(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]));
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return u;
	}
	
	private int AsignarID() {//Asignación de ID sin inconvenientes del usuario
		int x = 0;
		while(true) {
			x++;
			if(buscarIDUsuario(x) == null) {//Si ese ID no existe, se asigna
				break;
			}		
		}
		return x;
	}
	
	public boolean eliminar(String usuario) {//Elimina un usuario
		boolean res = false;
		Usuarios[] todos = recuperarUsuarios();//Saca todos los datos
		try(PrintStream out = new PrintStream(ruta);){//Crea un nuevo archivo con esos datos
			for(Usuarios p:todos) {
				if(!usuario.equals(p.getUsuario())) {//Imprime todos los datos excepto el del usuario a eliminar
					out.println( p.getID()  +"|"+p.getUsuario()+"|"+p.getContrasena()+"|"+p.getNumeroLibrosPrestado());
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
	
	public Usuarios[] recuperarUsuarios() {//Lista de todos los usuarios y sus datos
		ArrayList<Usuarios> existentes = new ArrayList<>();
		Usuarios u = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){//Lee los datos
			String s;
			while((s = bf.readLine())!=null) {//Hasta que no haya linea para leer
				String[] datos = s.split("[|]");//Reagrupa los datos con su respectivo separador
				//Crea un usuario con esos datos
				u = new Usuarios(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]));
				existentes.add(u);//Se anade el usuario a la lista
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		//Se regresa el ArrayList en Array
		return existentes.toArray(new Usuarios[0]);}
		
	//Modifica la contrasena del usuario
	public Usuarios ModificarContrasena(String Usuario, String ContraNueva) {
		
		Usuarios u = buscarUsuario(Usuario);
		if(u == null) {
			System.out.println("No se encuentra el usuario a cambiar la contrasena");
		}else {
			eliminar(Usuario);//Elimina el usuario
			//Vuelve a agregarlo pero con la contra nueva
			agregar(u.getID(), Usuario,ContraNueva,u.getNumeroLibrosPrestado());
		}
		
		return u;
	}
	
	//Modificar los datos de un libro prestado
	public void ModificarLibrosPrestados(String Usuario, int Devolucion) {
		Usuarios u = buscarUsuario(Usuario);//Datos del usuario actual
		if(u == null) {
			System.out.println("No se encuentra el usuario para el devolver o prestar libro");
		}else {
			eliminar(Usuario);//Elimina usuario
			//y crea nuevamente el usuario con los datos nuevos (libro prestado)
			agregar(u.getID(), u.getUsuario(),u.getContrasena(),u.getNumeroLibrosPrestado()+Devolucion);
		}
	}
	

}
