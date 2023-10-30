package recursividad;

public class main {

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
		
		
		ImprimeDeNhasta0(10);//1er ejercicio
		System.out.println(" ");
		Fib(5);//2do ejercicio
		System.out.println(" ");
		System.out.println("La suma de 4 para abajo es de "+SumaDeNumerosInversa(4));//3er ejercicio
		System.out.println(" ");
		ImprimeImpares(8);//4to ejercicio
		System.out.println(" ");
		int [] muchosnumeros = {3,6,8};
		System.out.println("La suma de estos numeros es de "+SumaArrayNumeros(muchosnumeros,0));//5to ejercicio
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
