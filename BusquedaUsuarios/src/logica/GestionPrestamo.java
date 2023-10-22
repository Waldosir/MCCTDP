package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import OrganizarDatos.Cola;
import Datos.Libros;
import Datos.Usuarios;





public class GestionPrestamo {
	String Null = "";
	final String rutaG = "DatosBiblioteca\\LibrosPrestados\\";
	final String rutaCola = rutaG + "\\Cola\\";
	

	
	public GestionPrestamo() {

	}
	
	public void CreacionArchivo(String nombrelibro) {//Crea los archivos de prestamo y de cola del libro
		String ruta = rutaG +nombrelibro+".txt";
		String ruta2 = rutaCola +nombrelibro+".txt";
		try {
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");//Crea el txt de prestamo
            PrintWriter writer2 = new PrintWriter(ruta2, "UTF-8");//Crea el txt de cola
            writer.close();//Cierra
            writer2.close();//Cierra
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	public void BorrarArchivo(String nombrelibro) {//Borra los archivos de un libro
		String ruta = rutaG +nombrelibro+".txt";
		String ruta2 = rutaCola +nombrelibro+".txt";
		File fichero = new File(ruta);//Busca los ficheros
		File fichero2 = new File(ruta2);
		if (fichero.delete() && fichero2.delete()) //Si se borran ambos
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no pudo ser borrado");
	}
	
	//Busca si encuentra el ID de una persona y ver si tiene ese libro en su poder
	public boolean buscarLibroPrestado(String nombrelibro,int IDUsuario) {
		String ruta = rutaG + nombrelibro+".txt";
		boolean res = false;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				if(datos[0].equals(IDUsuario+"")) {
					res = true;
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	//Agrega una persona al prestamo
	public void agregarPersonaPrestadoLibro(String nombrelibro, int IDUsuario) {
		Usuarios u = new Usuarios(); u = u.buscarIDUsuario(IDUsuario);
		if(agregarPrestamoLibro(nombrelibro,IDUsuario)) {
			System.out.println("El usuario "+u.getUsuario()+" se le ha prestado el libro "+
		nombrelibro+" con exito");
		}else {
			System.out.println("No se ha podido agregar el libro con exito");
		}
		
	}
	
	//Funcion que modifica el fichero para agregar a esa persona como prestamista del libro
	public boolean agregarPrestamoLibro(String nombrelibro,int IDUsuario) {
		String ruta = rutaG + nombrelibro+".txt";
		if(!buscarLibroPrestado(nombrelibro,IDUsuario)) {
			try(FileOutputStream fos = new FileOutputStream(ruta,true);
					PrintStream salida = new PrintStream(fos)){
			salida.println(IDUsuario+"|");	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	//Busca si la persona está en la cola del libro
	public boolean buscarLibroCola(String nombrelibro,int IDUsuario) {
		String ruta = rutaCola + nombrelibro+".txt";
		boolean res = false;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				if(datos[0].equals(IDUsuario+"")) {
					res = true;
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	//Agrega a la persona (Con su ID) a la cola del libro mencionado
	public boolean agregarcola(String nombrelibro,int IDUsuario) {
		String ruta = rutaCola + nombrelibro+".txt";
		Libros l = new Libros();
		if(!buscarLibroCola(nombrelibro,IDUsuario)&&!buscarLibroPrestado(nombrelibro,IDUsuario)) {
			try(FileOutputStream fos = new FileOutputStream(ruta,true);
					PrintStream salida = new PrintStream(fos)){
			salida.println(IDUsuario+"|");	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	//Elimina a la persona de la cola, sin importar su posicion
	public boolean eliminarPersonaCola(String nombrelibro,int ID) {
		String ruta = rutaCola + nombrelibro+".txt";
		boolean res = false;
		ArrayList<Integer> todos = recuperarIDCola(nombrelibro);
		try(PrintStream out = new PrintStream(ruta);){
			for(int i=0;i<todos.size();i++) {
				if(todos.get(i) != ID) {
					out.println( todos.get(i)  +"|");
				}
				else {
					res = true;
				}
			}
			
	
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
		
	//Tamano de la cola
	public int TamanoCola(String nombrelibro) {
		ArrayList<Integer> todos = recuperarIDCola(nombrelibro);
		if(todos.size()==0) {
			return 0;
		}
		return todos.size();
	}
	
	//Tamano de personas que pidieron prestado el libro
	public int TamanoNumPersonasPrestadoLibro(String nombrelibro) {
		ArrayList<Integer> todos = recuperarIDPrestamos(nombrelibro);
		if(todos.size()==0) {
			return 0;
		}
		return todos.size();
	}
	
	//La persona en el inicio de la cola
	public int SiguienteNumero(String nombrelibro) {
		ArrayList<Integer> todos = recuperarIDCola(nombrelibro);
		if(todos.size()==0) {
			return 0;
		}
		return todos.get(0);
	}
	
	
	//Elimina a una persona que ya devolvió el libro prestado
	public boolean eliminarPersonaPrestamo(String nombrelibro,int ID) {//Listo
		String ruta = rutaG + nombrelibro+".txt";
		boolean res = false;
		ArrayList<Integer> todos = recuperarIDPrestamos(nombrelibro);
		try(PrintStream out = new PrintStream(ruta);){
			for(int i=0;i<todos.size();i++) {
				if(todos.get(i) != ID) {
					out.println( todos.get(i)  +"|");
				}
				else {
					res = true;
				}
			}
			
	
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	//Recupera la lista de los ID de la gente que pidió prestado un libro
	public ArrayList<Integer> recuperarIDPrestamos(String NombreLibro) {
		String ruta = rutaG + NombreLibro+".txt";
		ArrayList<Integer> existentes = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				existentes.add(Integer.parseInt(datos[0]));
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return existentes;
		}
	
	//Recupera la lista de las personas que estan en la cola de un libro
	public ArrayList<Integer> recuperarIDCola(String NombreLibro) {
		String ruta = rutaCola + NombreLibro+".txt";
		ArrayList<Integer> existentes = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				existentes.add(Integer.parseInt(datos[0]));
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return existentes;
		}
	


}
