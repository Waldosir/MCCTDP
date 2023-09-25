/*
 * GONZALEZ ROBLES ALDAIR - M17171128
 * 
 * Programa "El juego de la vida"
 * en el cual es un autómata celular
 * donde evolucionan celulas mediante
 * reglas muy simples como que celulas
 * vivas siguen vivas si tiene vecinos
 * cercanos igual a 2 o 3, y que nace
 * un nuevo ser vivo de un espacio muerto
 * si exactamente son 3 vecinos los que tiene
*/


package principal;

import java.util.Scanner;

import datos.Tabla;

public class MainPrincipal {
	

	static int MinimoFilasTablero = 2, MaximoFilasTablero = 20; //Rango fila tablero
	static int MinimoColumnasTablero = 2, MaximoColumnasTablero = 20; //Rango columna tablero
	static int MinimoGeneraciones = 1, MaximoGeneraciones = 50; //Rango generaciones

	static Scanner sc = new Scanner(System.in);//Leer datos
	//Ingreso de datos del usuario en su respectivo rango con su pregunta
	static int IngresoEnteroRango(String TextoPregunta, int minimo, int maximo) {
				
				int numero = 0;
				while(true) {
					
					System.out.print(TextoPregunta); //Hace la peticion del dato
					String ValorUsuario = sc.nextLine();
					try{
						numero = Integer.parseInt(ValorUsuario);
						
						if(numero>=minimo && numero<=maximo) { //Verifica que este en el rango
							return numero; //Regresa el numero y sale del ciclo
						}else {// Si no esta en el rango
							System.out.print("Valor fuera del rango. Ingrese un valor dentro del rango\n");
						}
						
					}
					catch(Exception e) {
						System.out.println("Error. Valor no valido. Ingrese el valor nuevamente");
						
					}
					
					
				}
				
				
				
		}
	
	static boolean[][] TablaDatos (int NumFilas, int NumColumnas, int NumeroOrganismos, 
			int MinimoArreglo, int MaximoArregloF, int MaximoArregloC){
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
				return datos;
		
	}
	
	
	
	public static void main(String[] args) {
		/*
		//Ingreso de fila, columna y generaciones por parte del usuario
		int numFilas = IngresoEnteroRango("Ingrese numero de filas del tablero: ",
				MinimoFilasTablero, MaximoFilasTablero);
		
		int numColumnas = IngresoEnteroRango("Ingrese numero de columnas del tablero: ",
				MinimoColumnasTablero, MaximoColumnasTablero);
		int numGeneraciones = IngresoEnteroRango("Ingrese el numero de generaciones del juego: ",
				MinimoGeneraciones,MaximoGeneraciones);
		
		//Minimo y maximo del organismo
		int MinimoOrganismos = 0,MaximoOrganismos = (int) (numFilas*numColumnas*.5);
		//Valores Minimo y Maximo del arreglo para introducir datos
		int MinimoArreglo = 0, MaximoArregloF = numFilas-1, MaximoArregloC = numColumnas-1;
		//Ingreso de numero de organismos
		int numeroOrganismos = IngresoEnteroRango("Ingrese numero de organismos iniciales: ",
				MinimoOrganismos,MaximoOrganismos);
		*/
		//Tabla con los datos ingresados por el usuario
		/*boolean datos[][] = TablaDatos(numFilas,numColumnas,numeroOrganismos, 
				MinimoArreglo, MaximoArregloF, MaximoArregloC);
		*/
		
		int numFilas = 8;
		int numColumnas = 10;
		int numGeneraciones = 10;
		int numeroOrganismos = 10;
		int MinimoArreglo = 0, MaximoArregloF = numFilas -1, MaximoArregloC = numColumnas-1;
		
		boolean datos[][] = new boolean[numFilas][numColumnas];
		datos[1][3] = true;
		datos[2][3] = true;
		datos[3][2] = true;
		datos[3][3] = true;
		datos[3][4] = true;
		datos[4][3] = true;
		datos[5][7] = true;
		datos[5][8] = true;
		datos[6][7] = true;
		datos[6][8] = true;
		
		//Tablas: Una actual del programa, y otra para comparar
		Tabla tActual = new Tabla(numFilas, numColumnas,datos);
		
		//Función generación de tabla.
		tActual.GeneracionTabla(numGeneraciones);
		sc.close();
		System.out.print("Fin del programa.");

	}
	

	
	
	

}
