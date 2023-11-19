package Principal;

import java.util.ArrayList;

import Comandos.Acciones;
import Comandos.OpMat;
import Constantes.CICLO;
import Constantes.CM;
import Constantes.LITVAR;
import Constantes.OPERADOR;
import LecturaDatos.Lectortxt;

public class mainP {

	public static void main(String[] args) {
		ArrayList<String> comandos = new ArrayList<String>();
		Acciones a = new Acciones();
		OpMat op = new OpMat();
		
		Lectortxt l = new Lectortxt();
		 l.iniciar();
		 asignacionCorrecta(a);
		 operacionMatematica(a);
		 

		
	}
	
	static void operacionMatematica(Acciones a) {
		a.accion("entero base");
		a.accion("entero altura");
		a.accion("real area");
		
		a.accion("leer base");
		a.accion("leer altura");
		a.accion("area = base * altura / 2");
		
		a.accion("imprime ~La base es: ~");
		a.accion("imprime base");
		a.accion("imprime ~salto~ ");
		a.accion("imprime ~La altura es: ~");
		a.accion("imprime altura");
		a.accion("imprime ~salto~ ");
		a.accion("imprime ~El area es: ~");
		a.accion("imprime area");
		a.accion("imprime ~salto~ ");
		
	}
	
	static void asignacionCorrecta(Acciones a) {
		a.accion("entero base");
		a.accion("entero altura");
		a.accion("real area");
		
		a.accion("leer base");
		a.accion("leer altura");
		a.accion("leer area");
		
		a.accion("imprime base");
		a.accion("imprime ~salto~ ");
		a.accion("imprime altura");
		a.accion("imprime ~salto~ ");
		a.accion("imprime area");
		a.accion("imprime ~salto~ ");
		
	}
	
	static void checandoFuncionOperadorMat(OpMat op) {
		String entero = LITVAR.ENTERO.toString(),real = LITVAR.REAL.toString(); 
		op.inicializarLiterales(entero, "Base");
		op.inicializarLiterales(entero, "Altura");
		op.inicializarLiterales(real, "Area");
		
		op.asignarValorLiterales("Base", 5);
		op.asignarValorLiterales("Altura", 3);
		
		op.colocarDatoEnPila("Base");
		op.colocarDatoEnPila("*");
		op.colocarDatoEnPila("Altura");
		op.colocarDatoEnPila("/");
		op.colocarDatoEnPila(2);
		op.asignarValorLiterales("Area", op.terminarOperacion());
		System.out.println();
	}

}
