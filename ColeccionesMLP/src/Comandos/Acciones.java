package Comandos;

import java.util.Scanner;

import Constantes.CM;
import Constantes.LITVAR;
import Constantes.OPERADOR;

public class Acciones {
	Scanner sc = new Scanner(System.in);
	OpMat opMat = new OpMat();
	
	public Acciones() {}
	
	private String[] separador(String linea) {
		String lineaS[] = linea.split("[ ]") ;
		return lineaS;
		}
	

	public void accion(String linea) {
		String comando = "";
		String restante = "";
		String variable = "";
		try {
			if(CM.isComando(linea)) {
				comando += separador(linea)[0];
				variable += ""+separador(linea)[1];
				if(comando.equals(CM.IMPRIME.toString())) {
					
					if(this.isTexto(linea)) {
						for(int i=comando.length();i<(linea.length());i++) {
							restante += linea.charAt(i);
						}
						String impresion = "";
						boolean sumar = false;
						for (char caracter : restante.toCharArray()) {
							if(caracter != '~' ) {
								if(sumar) {
									impresion+=caracter;
								}
							}else {
								if(!sumar) {
									sumar = true;
								}else {
									break;
								}
								
							}
							}
						imprimir(impresion);
					}else {
						imprimir(opMat.buscarLiteral(variable));
					}
				}
				else if(comando.equals(CM.LEER.toString())){
					System.out.print("Asignar numero a "+variable+":"); 
					double opcion = Double.parseDouble(sc.next());
					opMat.asignarValorLiterales(variable, opcion);
					System.out.println();
					}else {
						System.out.println("\nComando no existe. Reintentar\n");
					}
					
			}else if(LITVAR.isDeclaracion(linea)) {
				if(linea.contains(LITVAR.IGUAL.toString())) {
					variable += separador(linea)[0];
					for(int i=2;i<(separador(linea).length);i++) {
						restante += separador(linea)[i];
					}
					boolean sumar = false;
					String valor="";
					for (char caracter : restante.toCharArray()) {
						if(OPERADOR.isOperador(caracter+"")) {
							sumar = false;
						}else {
							sumar = true;
						}
						
						if(sumar) {
							valor+=caracter;
						}else {
							if(!valor.equals("")) {
								if(isNumero(valor+"")) {
									opMat.colocarDatoEnPila(Double.parseDouble(valor));
								}else {
									opMat.colocarDatoEnPila(valor);
								}
								valor = "";
							}
							
							opMat.colocarDatoEnPila(caracter+"");
						}

					}
					
					if(!valor.equals("")) {
						if(isNumero(valor+"")) {
							opMat.colocarDatoEnPila(Double.parseDouble(valor));
						}else {
							opMat.colocarDatoEnPila(valor);
						}
					}
					
					opMat.asignarValorLiterales(variable, opMat.terminarOperacion());
					
				}else if(linea.contains(LITVAR.ENTERO.toString())|| linea.contains(LITVAR.REAL.toString())) {
					comando = separador(linea)[0];
					variable = separador(linea)[1];
					opMat.inicializarLiterales(comando, variable);
				}
			}
			
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Comando no valido");
		}
		
		}

	private boolean isTexto(String texto) {
		for (char caracter : texto.toCharArray()) {
		if(caracter == '~') {
		return true;	
		}
		}
		return false;
	}
	
	
	//Impresion
	public void imprimir(String valor) {
		if(valor.equals("salto")) {
			System.out.println();
		}else {
			System.out.print(valor);
		}
		
	}
	
	public void imprimir(int valor) {
		System.out.print(valor);
	}
	
	public void imprimir(double valor) {
		System.out.print(valor);
	}

	
	
	public boolean isNumero(String caracter) {
		for(int i=0;i<10;i++) {
			if((caracter.charAt(0)+"").equals(i+"")) {
				return true;
			}
		}
		return false;
	}

}
