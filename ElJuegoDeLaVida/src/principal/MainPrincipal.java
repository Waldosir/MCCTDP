package principal;

import java.util.Scanner;

import datos.Tabla;

public class MainPrincipal {
	

	static int MinimoFilasTablero = 2, MaximoFilasTablero = 20; //Rango fila tablero
	static int MinimoColumnasTablero = 2, MaximoColumnasTablero = 20; //Rango columna tablero
	static int MinimoGeneraciones = 1, MaximoGeneraciones = 50; //Rango generaciones

	static Scanner sc = new Scanner(System.in);
	//Ingreso de datos del usuario en su respectivo rango con su pregunta
	static int IngresoEnteroRango(String TextoPregunta, int minimo, int maximo) {
				
				int numero = 0;
				while(true) {
					
					System.out.print(TextoPregunta); //Hace la peticion del dato
					String ValorUsuario = sc.nextLine();
					try{
						numero = Integer.parseInt(ValorUsuario);
						
						if(numero>=minimo && numero<=maximo) { //Verifica que este en el rango
							break;
						}else {// Si no esta en el rango
							System.out.print("Valor fuera del rango. Ingrese un valor dentro del rango\n");
						}
						
					}
					catch(Exception e) {
						System.out.println("Error. Valor no valido. Ingrese el valor nuevamente");
						
					}
					
					
				}
				
				return numero; //Regresa el numero y sale del ciclo
				
		}

	
	
	
	static void Pausar() {
		 System.out.println("Press Enter key to continue...");
	        try
	        {
	        	Scanner sc = new Scanner(System.in);
	        	sc.nextLine();
				sc.close();
	            
	        }  
	        catch(Exception e)
	        {}  
	}


	public static void main(String[] args) {
		
		
		//Ingreso de fila, columna y generaciones por parte del usuario
		int NumFilas = IngresoEnteroRango("Ingrese numero de filas del tablero: ",
				MinimoFilasTablero, MaximoFilasTablero);
		
		int NumColumnas = IngresoEnteroRango("Ingrese numero de columnas del tablero: ",
				MinimoColumnasTablero, MaximoColumnasTablero);
		int NumGeneraciones = IngresoEnteroRango("Ingrese el numero de generaciones del juego: ",
				MinimoGeneraciones,MaximoGeneraciones);
		
		//Minimo y maximo del organismo
		int MinimoOrganismos = 0,MaximoOrganismos = (int) (NumFilas*NumColumnas*.5);
		//Valores Minimo y Maximo del arreglo para introducir datos
		int MinimoArreglo = 0, MaximoArregloF = NumFilas-1, MaximoArregloC = NumColumnas-1;
		//Ingreso de numero de organismos
		int NumeroOrganismos = IngresoEnteroRango("Ingrese numero de organismos iniciales: ",
				MinimoOrganismos,MaximoOrganismos);
		
		//Tabla de ingreso de datos por parte del usuario
		boolean[][] datos  = new boolean[NumFilas][NumColumnas];
		for(int i=0; i<NumeroOrganismos;i++) {//Bucle para ingresar los datos
			while(true) {//Bucle para evitar que se introduzcan datos en una posicion que ya este viva la casilla
				int f = IngresoEnteroRango("Fila del organismo "+(i+1)+": ",MinimoArreglo,MaximoArregloF);
				int c = IngresoEnteroRango("Columna del organismo "+(i+1)+": ",MinimoArreglo,MaximoArregloC);
				System.out.println();
				if(datos[f][c]!=true) {//Si en esa casilla no hay ser vivo
					datos[f][c] = true; //Los datos de esa fila-columna se vuelven true
					break;//Sale del While hacia el siguiente caso
				}else {//Caso donde ya fue introducido en esa posicion -Entra en bucle
					System.out.println("Ya hay un ser vivo en esta coordenada."
							+ "Introducir en otra coordenada");
				}
				
			}
			
		}
		
		//Tablas: Una actual del programa, y otra para comparar
		Tabla TVieja  = new Tabla(NumFilas,NumColumnas);
		Tabla TNueva = new Tabla(NumFilas, NumColumnas,datos);
		
		for(int i=0; i<NumGeneraciones;i++) {
			System.out.println("Generacion "+(i+1));
			System.out.println(TNueva.toString()); //Tabla con los datos
			
			//Si la tabla vieja tiene datos, y si la condicion de detener la tabla se hace
				if(TVieja.getDatos()!=null && TNueva.DetenerPrograma(TVieja.getDatos())) {
					break;//Termina el ciclo de generaciones
				}
			TVieja.setDatos(TNueva.getDatos()); //Coloca los datos de la tabla actual para ser datos antiguos
			TNueva.setDatos(TVieja.TablaNueva());//Actualiza los datos de la tabla actual con los datos antiguos
			Pausar();      
		}
		
		System.out.print("Fin del programa.");

	}
}
