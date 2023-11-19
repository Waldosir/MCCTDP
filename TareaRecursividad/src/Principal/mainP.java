package Principal;

import java.util.ArrayList;

public class mainP {
	static int contar = 0;
	public static void main(String[] args) {
		/*
		•
		Sumar números en un arreglo (usar [ ])
		•
		Sumar elementos de una lista (usar ArrayList)
		•
		Impresión de datos de un arreglo (respetar orden)
		•
		Elevar a una potencia un numero
		•
		Convertir decimal a binario MCD
		•
		Función de Ackerman
		•
		Números de Catalán
		•
		Búsqueda binaria
		•
		Juegos
		*/
		
		//Sumar números en un arreglo (usar [ ])
		int [] muchosnumeros = {3,6,8};
		System.out.println("La suma de estos numeros es de "+SumaArrayNumeros(muchosnumeros,0));//5to ejercicio
		
		//Sumar elementos de una lista (usar ArrayList)
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i=0;i<6;i++) {
			numeros.add(i);
			}
		System.out.println("La suma es de "+reSumaArrayList(numeros, numeros.size()));
		
		//Impresión de datos de un arreglo (respetar orden)
		String [] muchosStrings = {"Hola", "Hoy","Sale", "Si"};
		System.out.println("Imprimir Strings:"+ImprimeArrayStrings(muchosStrings,muchosStrings.length-1));//6to ejercicio
		
		//Elevar a una potencia un numero
		System.out.println("La potencia de 3 a la 4 es de "+rePotencia(3,1));
		
		//Convertir decimal a binario MCD
		System.out.println(decBin(12));
		
		//Función de Ackerman
		System.out.println(ackerman(2,1));	
		
		
		Hanoi h = new Hanoi(3, 1, 3, 2);
		System.out.println("Total de entradas a mover: "+h.getContar());
		//Números de Catalán
		
		
		
		/*
		ImprimeDeNhasta0(10);//1er ejercicio
		System.out.println(" ");
		Fib(5);//2do ejercicio
		System.out.println(" ");
		System.out.println("La suma de 4 para abajo es de "+SumaDeNumerosInversa(4));//3er ejercicio
		System.out.println(" ");
		ImprimeImpares(8);//4to ejercicio
		System.out.println(" ");
		
		System.out.println(" ");
		String [] muchosStrings = {"Hola", "Hey","Simon", "Canon"};
		System.out.println("Imprimir Strings:"+ImprimeArrayStrings(muchosStrings,muchosStrings.length-1));//6to ejercicio
		System.out.println(" ");
		String PruebaPal = "anita lava la tina";
		String PruebaPal2 = "anita lava la tinaa";
		Palindromo(PruebaPal);//7vo ejercicio
		Palindromo(PruebaPal2);//7vo ejercicio
		System.out.println(" ");
		String nombre = "hola";
		RecursoTodaCadena(nombre,0,1);//8vo ejercicio
		System.out.println(" ");
		
		*/
		
		
	}

	
	  public static int ackerman(int m, int n){
	        int acker = 0;
	        if(m==0){
	            acker += n+1;
	        }else{
	            if(n == 0){
	                acker += ackerman(m-1,1);
	            }else if(m > 0 && n > 0){
	                acker += ackerman(m-1,ackerman(m,n-1));
	            }
	        }
	        return acker;
	    }
	
	public static String decBin(int n) {
		String valor="";
		 if (n < 2) {
	            return n+"";
	        } else {
	            return  decBin(n/2)+ (n%2);
	        }
	}
	
	public static int rePotencia(int numero, int potencia) {
		if(potencia==0) {
			return 1;
		}
		else if(potencia<=1) {
			return numero;
		}
		
		return numero*rePotencia(numero,potencia-1);
	}
	
	public static int reSumaArrayList(ArrayList<Integer> cadena, int numero) {
		if(numero<=0) {
			return cadena.get(numero);
		}
		
		return cadena.get(numero-1) + reSumaArrayList(cadena, numero-1);
		
	}
	

	public static void RecursoTodaCadena(String Cadena,int i,int j) {
		if(i>=Cadena.length()) {
			return;
		}
		
		if(j>Cadena.length()) {
			i++;
			j = 1;
		}
		
		if(i<Cadena.length()&&j<=Cadena.length()&&j>i) {
			String n = Cadena.substring(i,j);
			System.out.println(n);
		}
		j +=1;
		
		
		RecursoTodaCadena(Cadena,i,j);
	}
	

	
	public static void ImprimeDeNhasta0(int n) {
		System.out.println("Numero actual: "+n);
		if(n==0) {
			return;
		}
		ImprimeDeNhasta0(n-1);
	}
	

	public static void Fib(int n) {
		int Datos[] = new int[n];
		System.out.println("\nDatos de Fibonacci numero "+n+":");
		for(int i=0;i<n;i++ ) {
			System.out.print(MetFib(i)+",");
		}
		System.out.println("");
		
		
	}
	public static int MetFib(int n) {
		if(n<2) {
			return n;
		}
		return MetFib(n-1) + MetFib(n-2);
	}
	
	
	
	public static int SumaDeNumerosInversa(int n) {
		if(n==0) {
			return n;
		}
		
		return SumaDeNumerosInversa(n-1)+n;
	}
	
	public static void ImprimeImpares(int n) {
		if(n ==0) {
			return;
		}
		if(n%2 !=0) {
			System.out.println("Numero impar:"+n);
		}
		ImprimeImpares(n-1);
	}
	
	public static int SumaArrayNumeros(int [] d,int  numero) {
		if(numero ==d.length-1) {
			return d[numero];
			
		}
		
		return SumaArrayNumeros(d, numero+1 )+ d[numero];
	}
	
	public static String ImprimeArrayStrings(String [] d,int  numero) {
		if(numero ==0) {
			return d[numero];
			
		}
		
		return ImprimeArrayStrings(d, numero-1 )+"\n"+ d[numero];
	}
	public static String QuitarEspacios(String Cadena) {
		String CadNew = "";
		char EspacioBlanco = ' ';
		for(int i=0;i<Cadena.length();i++) {
		if(Cadena.charAt(i)!=EspacioBlanco) {
			CadNew += Cadena.charAt(i);
		}
		}
		return CadNew;
	}
	
	public static void Palindromo(String Cadena){
		String CadenaNueva = QuitarEspacios(Cadena);
		System.out.println("La palabra "+Cadena+" "+RecurPalindromo(CadenaNueva,CadenaNueva.length()-1));
	}
	public static String RecurPalindromo(String Cadena, int n) {
		String Volteado="";
		if(n ==0) {
			String a = "";
			a+= Cadena.charAt(0);
			return a;
		}
			Volteado += Cadena.charAt(n)+RecurPalindromo(Cadena,n-1);
		if(Volteado.equals(Cadena)) {
			return "Es Palindromo";
		}
		else if(!Volteado.equals(Cadena) && Volteado.length()==Cadena.length()) {
			return "No es palindromo";
		}
		
		
		return Volteado;
	}
	
	
	
	

}
