package principal;

import java.util.ArrayList;
import java.util.Scanner;

import Datos.Libros;
import Datos.Usuarios;
import logica.GestionPrestamo;

public class main {

	public static void main(String[] args) {
		
		OrdenarFicheros();//Ordena los ficheros
		MenuPrincipal();//Menu principal
		OrdenarFicheros();//Ordena los ficheros nuevamente

		
	}
	public static void OrdenarFicheros() {//Ordena ambos ficheros
		Libros u = new Libros();
		Usuarios v = new Usuarios();
		u.OrdenarTxt();
		v.OrdenarTxt();
	}
	
	public static int Opcion() {//Evitar errores de ingreso cuando no son numeros
		int op = 0;
		Scanner sc = new Scanner(System.in);//Ingresar datos
		do {
			try {
				System.out.print("Opcion:");
				op = sc.nextInt();
			}catch(Exception e) {
			    System.out.println("ERROR: Caracter no valido");
			}
			break;
		}while(true);
		return op;
	}
	
	public static void MenuPrincipal() {//Menu principal
		
		int op;
		do {
			Scanner sc = new Scanner(System.in);//Ingresar datos
			System.out.println("\nMenu principal");
			System.out.println("1-Ingresar usuario y contrasena");
			System.out.println("2-Nuevo usuario");
			System.out.println("3-Biblioteca Main");
			System.out.println("4-salir");
			op = Opcion();
			
			
				
			
			
			switch(op) {
			case 1://Ingreso de usuario con contrasena
				IngresarSesion();
				break;
			case 2://Nuevo usuarios
				NuevoUsuario();
				break;
			case 3://Main de la biblioteca
				MainBiblio();
				break;
			case 4://Salir
				break;
			default:
				System.out.println("Debes de escoger una opcion valida");
				
			}
			
		}while(op != 4);
		
	}
	
	public static String NombreOpcionesUsuario() {//Opciones de usuarios desplegados en lista
		boolean OpcionValida = false;
		
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Usuarios u = new Usuarios();
		Usuarios[] todos = u.recuperarUsuarios();
		int OpcionE = 0;
		while(!OpcionValida && todos.length>0) {//Si hay usuarios en la lista y hasta que no haya una opcion valida
			System.out.println("\nOpciones de usuarios:");
			System.out.println("0-Salir");
			
			for(Usuarios p:todos) {//Nombre e ID
				System.out.println(p.getID()+"-"+p.getUsuario());
			}
			OpcionE = Opcion();
			for(Usuarios p:todos) {
				if(p.getID() == OpcionE) {//Si está entre las opciones a escoger se rompe el ciclo
					OpcionValida = true;
				}
			}
			if(OpcionE == 0) {//Opcion de salir
				return null;
			}
			if(!OpcionValida) {//Caso de que no se rompa el ciclo
				System.out.println("No es una opcion valida. Escoge una valida");
			}
		}
		if(todos.length<=0) {//Si no hay usuarios a escoger
			return null;
		}

		
		return u.buscarIDUsuario(OpcionE).getUsuario();//Regresa el nombre del usuario
	}
	
	public static String NombreOpcionesLibro() {//Opciones de libros desplegados en lista
		boolean OpcionValida = false;
		
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Libros l = new Libros();
		Libros[] todos = l.recuperarLibros();
		int OpcionE = 0;
		while(!OpcionValida && todos.length>0) {
			System.out.println("\nOpciones de libro:");
			System.out.println("0-Salir");
			for(Libros p:todos) {
				System.out.println(p.getID()+"-"+p.getNombre());
			}
			OpcionE = Opcion();
			for(Libros p:todos) {
				if(p.getID() == OpcionE) {
					OpcionValida = true;
				}
			}
			if(OpcionE == 0) {
				return null;
			}
			if(!OpcionValida) {
				System.out.println("No es una opcion valida. Escoge una valida");
			}
		}
		if(todos.length<=0) {
			return null;
		}
		
		
		return l.buscarIDLibro(OpcionE).getNombre();
	}
	
	public static void MainBiblioLibros() {//El main de la biblioteca para opciones de libros
		Scanner sc = new Scanner(System.in);//Ingresar datos
		String nombre,Descripcion,QuitaBarras;
		int NumHojas = 0, ID,numcola,numlibros;
		Libros g = new Libros();
		Libros[] todos = g.recuperarLibros();
		GestionPrestamo pre = new GestionPrestamo();
		System.out.println("\nOpciones de libro en Main:");
		System.out.println("0-Salir");
		System.out.println("1-Anadir nuevo libro");
		System.out.println("2-Modificar datos de un libro");
		System.out.println("3-Anadir libros adicionales a uno ya existente");
		System.out.println("4-Quitar libro existente");
		System.out.println("5-Ver todos los libros y sus datos");

		
		int op = Opcion();
		QuitaBarras = sc.nextLine();
		switch(op) {
		case 0:
			break;
		case 1://Agregar un libro nuevo
			System.out.println("Nombre del libro:");
			nombre = sc.nextLine();
			System.out.println("Descripcion:");
			Descripcion = sc.nextLine();
			System.out.println("Numero de hojas:");
			NumHojas = Integer.parseInt(sc.next());
			if(g.agregar(nombre, Descripcion, NumHojas)) {
				pre.CreacionArchivo(nombre);
				System.out.println("Libro "+nombre+" agregado con exito");
			}
			else {
				System.out.println("El libro "+nombre+" ya existe o no se pudo agregar");
			}
			break;
		case 2://Modificar datos de un libro existente
			nombre = NombreOpcionesLibro();
			if(nombre != null) {
				g = g.buscarLibros(nombre);
				System.out.println("Descripcion:");
				Descripcion = sc.nextLine();
				System.out.println("Numero de hojas:");
				NumHojas = Integer.parseInt(sc.next());
				g.eliminar(nombre);
				g.agregar(g.getID(), nombre, Descripcion, NumHojas, g.getColaespera(), g.getNumerolibros());
			}
			break;
		case 3://Anadir libros adicionales a uno existente
			 OrdenarFicheros();//Ordena todo
				if(todos.length>0) {//Hay libros
					nombre = NombreOpcionesLibro();
					if(nombre !=null) {
						System.out.println("Numero de libros a anadir:");
						numlibros = sc.nextInt();
						g.AnadirLibro(nombre, numlibros);
					}
					
				}
				else {
					System.out.println("No hay libros en sistema");
				}
				
				break;
		case 4://Quitar libros existentes
			if(todos.length<=0) {
				System.out.println("No hay libros en sistema");
			}
			else {
				nombre = NombreOpcionesLibro();
				 if(nombre != null && pre.TamanoNumPersonasPrestadoLibro(nombre)<=0) {
					pre.BorrarArchivo(nombre);
					g.eliminar(nombre);
				}else {
					System.out.println("Hay libros pendientes por devolver. No se puede borrar");
				}
				
			}
				
			break;
		case 5://Ver la lista de los libros y sus datos
			 OrdenarFicheros();
				todos = g.recuperarLibros();
				if(todos.length<=0) {
					System.out.println("No hay libros");
				}else {
					for(Libros li:todos) {
						System.out.println("ID:"+li.getID());
						System.out.println("Nombre:"+li.getNombre());
						System.out.println("Descripcion:"+li.getDescripcion());
						System.out.println("Numero de hojas:"+li.getHojas());
						System.out.println("Cola de espera:"+li.getColaespera());
						System.out.println("Numeros disponibles en biblioteca:"+li.getNumerolibros());
						System.out.println("Cantidad prestada: "+pre.TamanoNumPersonasPrestadoLibro(li.getNombre()));
						System.out.println("");
					}
				}
				
				break;
		default:
			System.out.println("Opcion no valida. Regresando al menu");
		}
		
		
	}

	public static void MainBiblioUsuarios() {//El main de la biblioteca para opciones de Usuarios
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Libros g = new Libros();
		Usuarios u = new Usuarios();
		GestionPrestamo pre = new GestionPrestamo();
		Libros[] todos = g.recuperarLibros();
		Usuarios[] todosU = u.recuperarUsuarios();
		int op,ID,librospedidos;
		String nombre, nombreactual,contrasena;
		
		System.out.println("0-Salir");
		System.out.println("1-Modificar un usuario");
		System.out.println("2-Borrar un usuario");
		System.out.println("3-ver todos los datos de un usuario");
		System.out.print("Opcion:");
		op = sc.nextInt();
		switch(op) {
		case 0://Salir
			break;
		case 1://Modificar un usuario
			if(todosU.length>0) {
				String nombreAntiguo = NombreOpcionesUsuario();
				if(nombreAntiguo!= null) {
					u = u.buscarUsuario(nombreAntiguo);
					System.out.print("Nombre nuevo:");
					nombreactual = sc.nextLine();
					System.out.println("Contrasena nueva:");
					String contrasenanueva = sc.nextLine();
					//Se agrega el usuario con el mismo ID pero con nombre y contra nuevos
					if(u.agregar(u.getID(), nombreactual, contrasenanueva, u.getNumeroLibrosPrestado())) {
						//Se borra el anterior si se agregó conrrectamente
						if(u.eliminar(nombreAntiguo)) {
							System.out.println("Se han modificado los datos");
						}
					}else {
						System.out.println("Usuario ya existente, no se puede modificar");
					}
				}
			}else {
				System.out.println("No se encuentran usuarios");
			}
			
			break;
		case 2://Borrar un usuario
			if(todos.length>0) {
				OrdenarFicheros();
				String nombreU = NombreOpcionesUsuario();
				if(nombreU!=null) {
					u = u.buscarUsuario(nombreU);
					if(u.getNumeroLibrosPrestado()>0) {//Si debe libros no se puede borrar
						System.out.println("Todavia debe libros. No se puede borrar");
					}else {
						for(Libros p:todos) {//Elimina de todas las colas el nombre
							pre.eliminarPersonaCola(p.getNombre(), u.getID());
						}
						u.eliminar(nombreU);//Elimina el usuario definitamente
						System.out.println("Borrado con exito");
					}
				}
			}else {
				System.out.println("No existen usuarios para borrar");
			}
			
			break;
		case 3://Todos los datos de un usuario
			if(todos.length>0) {
				OrdenarFicheros();
				nombre = NombreOpcionesUsuario();
				if(nombre!=null) {
					Usuarios Datos = u.buscarUsuario(nombre);
					System.out.println("ID:"+Datos.getID());
					System.out.println("Nombre:"+Datos.getUsuario());
					System.out.println("Contrasena:"+Datos.getContrasena());
					System.out.println("Numero de libros pendientes:"+Datos.getNumeroLibrosPrestado());
					System.out.println("Libros prestados:");
					for(Libros p:todos) {
						if(pre.buscarLibroPrestado(p.getNombre(), Datos.getID())) {
							System.out.println("->"+p.getNombre());
						}
					}
					System.out.println("");
				}
				
			}else {
				System.out.println("No existen usuarios");
			}
			 
				break;
		default:
			System.out.println("Opcion no valida\n");
			
		}
	}
	
	public static void MainBiblioCola() {//El main de la biblioteca para opciones de cola
		int op=0;
		Libros l = new Libros();
		Usuarios u = new Usuarios();
		GestionPrestamo pre = new GestionPrestamo();
		String nombrelibro;
		do {
			System.out.println("\nMenu de cola en el main");
			System.out.println("0-Salir");
			System.out.println("1-Anadir a alguien de la cola");
			System.out.println("2-Quitar a alguien de la cola");
			System.out.println("3-Datos de la cola de un libro");
			System.out.print("Opcion:");
			op =  Opcion();
			switch(op) {
			case 0:
				break;
			case 1://Anadir a alguien a la cola
				String PersonaAgregarCola = NombreOpcionesUsuario();
				if(PersonaAgregarCola!=null) {
					Usuarios UAgregarCola = u.buscarUsuario(PersonaAgregarCola);
					nombrelibro = NombreOpcionesLibro();
					if(nombrelibro!=null) {
						if(pre.agregarcola(nombrelibro, UAgregarCola.getID())) {
							System.out.println(UAgregarCola.getUsuario()+" anadido a la cola del libro");
						}else {
							System.out.println("Persona ya en la cola");
						}
						
					}
				}
				break;
			case 2://Quitas del inicio de la cola a la persona que está en el inicio de la cola
				nombrelibro = NombreOpcionesLibro();
				if(nombrelibro!=null) {
					int IDColaInicio = pre.SiguienteNumero(nombrelibro);
					System.out.println("Seguro que quiere quitar a "+IDColaInicio+"-"+u.buscarIDUsuario(IDColaInicio).getUsuario()+" de la fila?");
					System.out.println("1-Si");
					int OpcionCaso2 = Opcion();
					switch(OpcionCaso2) {
					case 1:
						pre.eliminarPersonaCola(nombrelibro, IDColaInicio);
						break;
					default:
						System.out.println("Cancelando");
					}
				}
				
				break;
			case 3://Datos de la cola de un libro
				nombrelibro = NombreOpcionesLibro();
				if(nombrelibro!=null) {
					Libros lcola = l.buscarLibros(nombrelibro);
					System.out.println("Cola del libro: "+lcola.getColaespera());
					ArrayList<Integer> ColaLibro = pre.recuperarIDCola(nombrelibro);
					for(int i =0;i<ColaLibro.size();i++) {
						System.out.println(ColaLibro.get(i)+"-"+u.buscarIDUsuario(ColaLibro.get(i)).getUsuario());
					}
						
				}
				break;
			default:
				System.out.println("Opcion no valida");
			}
			
		}while(op!=0);
		
	}
	
	public static void MainBiblio() {//Main de la biblioteca
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Libros g = new Libros();
		Usuarios u = new Usuarios();
		Libros[] todos = g.recuperarLibros();
		GestionPrestamo pre = new GestionPrestamo();
		int op,NumHojas,numLibros,numcola,ID;
		String nombre,Descripcion;
		String QuitaBarras;
		
		do {
		System.out.println("\nBienvenido al main de biblioteca");
		System.out.println("0-salir");
		System.out.println("1-Opciones libro");
		System.out.println("2-Opciones Usuario");
		System.out.println("3-Opciones cola");
		op = sc.nextInt();
		QuitaBarras = sc.nextLine();
		
		switch(op){
		case 0://Salir
			break;
		case 1://Opciones de libros
			MainBiblioLibros();
			break;
		case 2://Opciones de usuarios
			MainBiblioUsuarios();
			break;
		case 3://Opciones de cola
			MainBiblioCola();
			break;
		default:
			System.out.println("Opcion no valida");
		}
			
		}while(op!=0);
	}
	
	public static void NuevoUsuario() {//Creacion de un nuevo usuario
		Scanner sc = new Scanner(System.in);//Ingresar datos
		System.out.println("Nombre del usuario: ");
		String Nombre = sc.nextLine();
		System.out.println("Contrasena para el usuario: ");
		String Contrasena = sc.nextLine();
		Usuarios nuevo = new Usuarios();
		if(nuevo.agregar(Nombre, Contrasena)) {
			System.out.println("Usuario agregado con exito");
		}
		else {
			System.out.println("Usuario ya existente. Ingrese con normalidad");
		}
		
		
	}
	
	public static void IngresarSesion() {//Iniciar sesion 
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Usuarios u = new Usuarios();
		System.out.print("Nombre del usuario:");
		String Nombre = sc.nextLine();
		System.out.print("Contrasena para el usuario: ");
		String Contrasena = sc.nextLine();
		u = u.buscarUsuario(Nombre);
		if(u == null) {
			System.out.println("Usuario no encontrado");
		}
		else if(u.getContrasena().equals(Contrasena)) {
			System.out.println("\nUsuario y contrasena validos");
			MenuUsuario(Nombre);
		}
		else {
			System.out.println("Usuario y/o contrasena incorrecta");
		}
		
		
	}
	
	public static void MenuUsuario(String Nombre) {//Menu para el usuario
		Scanner sc = new Scanner(System.in);//Ingresar datos
		int op ;
		Libros l = new Libros();
		Libros[] todos = l.recuperarLibros();
		GestionPrestamo gp = new GestionPrestamo();
		Usuarios UA = new Usuarios();
		do {
			
			
			System.out.println("\nBienvenido "+Nombre);
			System.out.println("0-Salir");
			System.out.println("1-Prestamo de libro");
			System.out.println("2-Devolucion de libro");
			System.out.println("3-Libros disponibles");
			System.out.println("4-Ver libros en posesion");
			System.out.println("5-Cambiar contrasena");
			System.out.println("6-Entra en la cola de un libro");
			
			op = sc.nextInt();
			switch(op) {
			case 0:
				break;
			case 1://Prestamo de libros
				OrdenarFicheros();
				PrestamoLibros(Nombre);
				break;
			case 2://Devolucion libros
				OrdenarFicheros();
				 DevolucionLibros(Nombre);
				 break;
			case 3://Libros disponibles
				OrdenarFicheros();
				System.out.println("Libros disponibles:");
				if(todos.length>0) {
					for(Libros p:todos) {
						if(p.getNumerolibros()>0) {
							System.out.println(p.getID()+"-"+p.getNombre());
						}
					}
				}else {
					System.out.println("No hay libros");
				}
				
				break;
			case 4://Libros en posesion
				OrdenarFicheros();
				System.out.println("Libros en posesion:");
				if(todos.length>0) {
					for(Libros p:todos) {
						if(gp.buscarLibroPrestado(p.getNombre(), UA.buscarUsuario(Nombre).getID())) {
							System.out.println(p.getNombre());
						}
					}
				}else {
					System.out.println("No hay libros en sistema");
				}
				
				
				break;
			case 5://Cambiar contrasena
				System.out.println("Contrasena nueva:");
				String ContraNueva = sc.next();
				UA.buscarUsuario(Nombre).ModificarContrasena(Nombre, ContraNueva);
				break;
			case 6:	//Entrar en una cola de libro
				if(todos.length>0) {
					System.out.println("0-Salir");
					for(Libros p:todos) {
						if(p.getNumerolibros()<=0) {
							System.out.println(p.getID()+"-"+p.getNombre());
						}
					}
					int opcionlibro = sc.nextInt();
					boolean OpcionValida = false;
					for(Libros p:todos) {
						if(p.getNumerolibros()<=0) {
							if(p.getID()==opcionlibro || opcionlibro == 0) {
								OpcionValida = true;
							}
						}
					}
					if(OpcionValida) {
					if(opcionlibro == 0){
						
					}else if(gp.agregarcola(l.buscarIDLibro(opcionlibro).getNombre(), UA.buscarUsuario(Nombre).getID())) {
							Libros LibroEditar = l.buscarIDLibro(opcionlibro);
							LibroEditar.eliminar(LibroEditar.getNombre());
							LibroEditar.agregar(LibroEditar.getID(), LibroEditar.getNombre(), LibroEditar.getDescripcion(), LibroEditar.getHojas(), LibroEditar.getColaespera()+1, LibroEditar.getNumerolibros());
							System.out.println("Usuario "+Nombre+" agregado a la cola con exito");
						}
						else {
							System.out.println("No se pudo anadir el usuario a la cola. Ya existe o no se pudo anadir");
						}
					}else {
						System.out.println("No existe esa opcion. Error");
					}
					
				}else {
					System.out.println("No hay libros en sistema");
				}
				
				break;
			default:
				System.out.println("Opcion no valida");
			}
			
		}while(op!=0);
	}
	
	public static void DevolucionLibros(String Nombre) {//Devolver un libro
		Scanner sc = new Scanner(System.in);//Ingresar datos
		Usuarios UA = new Usuarios(); UA = UA.buscarUsuario(Nombre);
		Libros l = new Libros();
		Libros[] todos = l.recuperarLibros();
		System.out.println("Retorno de libros:");
		if(todos.length>0) {
			System.out.println("0-Salir");
			for(Libros p:todos) {
				System.out.println(p.getID()+"-"+p.getNombre());
			}
			int OpcionE = sc.nextInt();
			boolean OpcionValida = false;
			for(Libros p:todos) {
				if(p.getID() == OpcionE||OpcionE == 0) {
					OpcionValida = true;
				}
			}if(OpcionE == 0) {
				
			}else if(OpcionValida && OpcionE!=0) {
				GestionPrestamo gp = new GestionPrestamo();
				Libros libroactual = l.buscarIDLibro(OpcionE);
				if(gp.eliminarPersonaPrestamo(libroactual.getNombre(),UA.getID())) {
					libroactual.AnadirLibro(libroactual.getNombre(), 1);
					UA.ModificarLibrosPrestados(Nombre,-1);
					System.out.println("Se ha devuelto el libro "+libroactual.getNombre()+" sin problemas");
				}else {
					System.out.println("No se encuentra la persona en la lista");
				}
			}else {
				System.out.println("Error, opcion no valida");
			}
		}else {
			System.out.println("No hay libros en sistema");
		}
		
	}
	
	public static void PrestamoLibros(String Nombre) {//Prestamo de libro
		Scanner sc = new Scanner(System.in);//Ingresar datos
		GestionPrestamo gp = new GestionPrestamo();//Comandos para prestamo de libros
		Usuarios UA = new Usuarios();UA = UA.buscarUsuario(Nombre);
		Libros l = new Libros();
		Libros[] todos = l.recuperarLibros();
		int i = 0;
		System.out.println("Prestamo de libros:");
		if(todos.length>0) {
			System.out.println("0-Salir");
			for(Libros p:todos) {
				
				System.out.println(p.getID()+"-"+p.getNombre());
			}
			int OpcionE = sc.nextInt();
			boolean OpcionValida = false;
			for(Libros p:todos) {
				if(p.getID() == OpcionE) {
					OpcionValida = true;
				}
			}
			if(OpcionE == 0) {
				
			}
			else if(OpcionValida&&OpcionE !=0) {//No es opcion 0 y es valido
				Libros libroactual = l.buscarIDLibro(OpcionE);//Objeto libro
				if(libroactual.PoderPedirLibro(libroactual.getNombre(), UA.getID()) ) {//Condicion para pedir un libro
					libroactual.AnadirLibro(libroactual.getNombre(), -1);//Se retira un libro disponible
					UA.ModificarLibrosPrestados(UA.getUsuario(),1);//Se anade un libro que ya tiene en posesion el usuario
					gp.agregarPrestamoLibro(libroactual.getNombre(), UA.getID());//Se agrega en la lista de gente a la que se le prestó el libro
					if(gp.SiguienteNumero(libroactual.getNombre())== UA.getID()) {//Si la persona estaba en la cola
						gp.eliminarPersonaCola(libroactual.getNombre(), UA.getID());//Se elimina de la cola
						libroactual.DesapilarColaLibro(libroactual.getNombre());//Se retira de la cola la persona que está arriba
					}
					System.out.println("El prestamo se ha hecho sin problemas");
				}else {
					System.out.println("No se puede prestar. La cola de espera es de: "+libroactual.getColaespera());
				}
			}else {
				System.out.println("Error, opcion no valida");
			}
		}else {
			System.out.println("No hay libros en sistema");
		}
		
			
		
	}

}
