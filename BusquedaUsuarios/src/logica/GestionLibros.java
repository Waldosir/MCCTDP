package logica;



import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import Datos.Libros;
import Datos.Usuarios;

public class GestionLibros {
	String Null = "";
	String ruta = "DatosBiblioteca\\Libros.txt";//Ruta a donde van los datos de libros.
	int MaximoLibrosPrestamo = 3;
	HashMap<String,Libros> libro;
	
	public GestionLibros() {
		libro = new HashMap<>();
	}
	
	public int MayorID() {//Para OrdenarTxT y saber cuantas veces va a reordenar
		Libros[] todos = recuperarLibros();//Funcion con todos los libros
		int x = 0;
		if(todos.length>0) {
			for(Libros p:todos) {
				if(x<p.getID()) {
					x = p.getID();
				}
			}
		}
		
		return x;//Regresa el ID más alto
	}
	
	
	public void OrdenarTxt() {//Ordena el txt de menor a mayor
		Libros li = new Libros();
		for(int i = 0;i<=MayorID();i++) {
			Libros l = li.buscarIDLibro(i);//Busca el libro
			if(l!=null) {//Si hay un dato
				l.eliminar(l.getNombre());//Elimina el dato y luego lo agrega hasta abajo
				l.agregar(l.getID(), l.getNombre(), l.getDescripcion(), l.getHojas(), l.getColaespera(), l.getNumerolibros());
			}
			
		}
	}

	//Agrega los datos de nombre, descripcion y hojas en el txt
	public boolean agregar(String nombre, String descripcion, int hojas) { 
		if(buscarLibros(nombre)==null) {//Si el usuario no está repetido se anade
			Libros l = new Libros(AsignarID(),nombre,descripcion,hojas,0,1);//Objeto con los datos
			try(FileOutputStream fos = new FileOutputStream(ruta,true);//Mira la ruta
					PrintStream salida = new PrintStream(fos)){//Imprime en una linea los datos con separacion
			salida.println(l.getID()+"|"+l.getNombre()+"|"+l.getDescripcion()+
					"|"+l.getHojas()+"|"+l.getColaespera()+"|"+l.getNumerolibros());	
				
			}catch(IOException ex) {//Si hay errores los da y cuales
				ex.printStackTrace();
			}
			return true;//Se agrego con exito
		}else {
			return false;//Ya existe el valor y no se agregó
		}
	}
	
	//Lo mismo que el anterior, pero funciona para los casos de borrar y luego introducir el dato nuevamente
	public boolean agregar(int ID, String nombre, String descripcion, int hojas, 
			int colaUsuarios, int numlibros) {
		if(buscarLibros(nombre)==null) {
			Libros l = new Libros(ID,nombre,descripcion,hojas,colaUsuarios,numlibros);
			try(FileOutputStream fos = new FileOutputStream(ruta,true);
					PrintStream salida = new PrintStream(fos)){
				salida.println(l.getID()+"|"+l.getNombre()+"|"+l.getDescripcion()+
						"|"+l.getHojas()+"|"+l.getColaespera()+"|"+l.getNumerolibros());	
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	//Te da los datos completos de un libro con saber su nombre
	public Libros buscarLibros(String nombreLibro) {
		Libros l = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){//busca el archivo
			String s;
			while((s = bf.readLine())!=null) {//Hasta que termine la hoja completa
				String[] datos = s.split("[|]");//Separa la linea de datos en un Array
				if(datos[1].equals(nombreLibro)) {//Si el nombre del libro es igual, se crea el objeto con todos los datos
					l = new Libros(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),Integer.parseInt(datos[5]));
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return l;//Regresa el objeto o regresa null si no encuentra nada
	}
	
	//Te da los datos completos de un libro con saber su ID
	public Libros buscarIDLibro(int ID) {
		Libros l = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");
				if(datos[0].equals(ID+"")) {
					l = new Libros(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),Integer.parseInt(datos[5]));
					break;
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return l;
	}
	
	//Asigna un ID automáticamente, para que no se repitan ID
	private int AsignarID() {
		int x = 0;
		while(true) {
			x++;
			if(buscarIDLibro(x) == null) {//Si no encuentra el ID, lo asigna
				break;
			}
			
			
		}
		return x;
	}
	
	//Elimina los datos de un txt
	public boolean eliminar(String nombreLibro) {
		boolean res = false;
		Libros[] todos = recuperarLibros();//Todos los libros
		try(PrintStream out = new PrintStream(ruta);){//Checa el archivo y su ruta
			for(Libros p:todos) {
				//Pone todos los nombres de los demás libros, exceptuando el que quieres retirar
				if(!nombreLibro.equals(p.getNombre())) {
					out.println( p.getID()  +"|"+p.getNombre()+"|"+p.getDescripcion()+"|"+p.getHojas()+"|"+p.getColaespera()+"|"+p.getNumerolibros());
				}
				else {
					res = true;//Se logra eliminar
				}
			}
			
	
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return res;//No se logra eliminar
	}
	
	public Libros[] recuperarLibros() {//Lista de todos los libros
		ArrayList<Libros> existentes = new ArrayList<>(); //Lista para colocar todos los libros
		Libros l = null;
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			String s;
			while((s = bf.readLine())!=null) {
				String[] datos = s.split("[|]");//Saca todos los datos
				l = new Libros(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),Integer.parseInt(datos[5]));
				existentes.add(l);//Se anaden los datos/objetos a la lista
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return existentes.toArray(new Libros[0]);}//Forma de retornar todos los objetos
	
	public Libros AnadirLibro(String NomLibro, int CantidadAnadir) {//Anade libros a un libro existente
		Libros l = buscarLibros(NomLibro);
		if(l == null) {//No existe el libro
			System.out.println("No se encuentra el libro");
		}else {//Toma los datos iguales, borra el anterior y anade la cantidad de libros 
			int ID = l.getID();
			String descripcion = l.getDescripcion();
			int hojas = l.getHojas();
			int cola = l.getColaespera();
			int numlibros = l.getNumerolibros()+CantidadAnadir;
			eliminar(NomLibro);//Borra
			//Anade con cantidad nueva
			agregar(ID, NomLibro,descripcion,hojas,cola,numlibros);
		}
		
		return l;
	}
	
	public void DesapilarColaLibro(String NomLibro) {//Quita uno de la cola
		Libros l =  buscarLibros(NomLibro);
		if(l == null) {
			System.out.println("No se encuentra el libro");
		}else {
			int ID = l.getID();
			String descripcion = l.getDescripcion();
			int hojas = l.getHojas();
			int cola = l.getColaespera()-1;//Se quita uno de la cola de espera
			int numlibros = l.getNumerolibros();
			eliminar(NomLibro);
			agregar(ID, NomLibro,descripcion,hojas,cola,numlibros);
		}
	}
	
	//Condiciones para poder pedir un libro
	public boolean PoderPedirLibro(String nombrelibro, int IDUsuario) {
		Libros n = new Libros(); n = n.buscarLibros(nombrelibro);
		Usuarios u = new Usuarios(); u= u.buscarIDUsuario(IDUsuario);
		GestionPrestamo gn = new GestionPrestamo();
		
		if(n.getNumerolibros()<=0) {//Si no hay libros en stock
			System.out.println("No hay libros en stock");
			return false;
		}
		if(u.getNumeroLibrosPrestado()>=MaximoLibrosPrestamo) {//Si tiene mas de 3 libros pedidos (maximo de libros)
			System.out.println("Numero de libros excedidos por el usuario");
			return false;
		}
		if(gn.buscarLibroPrestado(nombrelibro, IDUsuario)) {//Tiene el libro, por lo que no
			//puede pedir el libro dos veces porque ya tiene uno en prestamo
			System.out.println("Ya tiene libro similar en su posesion");
			return false;
		}
		//Si es el primero en la cola Y ADEMÁS hay al menos un libro disponible
		if((gn.SiguienteNumero(nombrelibro) == IDUsuario)&&n.getNumerolibros()>0 ) {
			return true;
		}else if(gn.TamanoCola(nombrelibro)>0) {//Hay fila de apartado 
			return false;
		}
		
		
		
		return true;//Si no hay problema de nada, se lo lleva si o si.
	}
	

}
