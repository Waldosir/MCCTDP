package JuegoMesa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Baraja.CartasPoker;
import Baraja.Simbolo;
import Baraja.ValorCarta;

public class BarajaPoker {
	private ArrayList<CartasPoker> baraja = new ArrayList();
	private ArrayList<CartasPoker> barajaInicial = new ArrayList();
	private int numeroDecks;
	
	public BarajaPoker(int numeroDecks) {
		this.numeroDecks = numeroDecks;
		for(int i=0;i<numeroDecks;i++) {
			CrearMazo();
		}
		this.barajaInicial = (ArrayList<CartasPoker>) this.baraja.clone();
		
	}
	
	public void EmpezarMazo() {
		baraja.clear();
		this.baraja =  (ArrayList<CartasPoker>) barajaInicial.clone();
		System.out.println("\nSe ha creado un nuevo mazo\n");
		
	}
	
	private void CrearMazo() {//Creacion del mazo con sus 52 cartas iniciales
		for(int i=0;i<ValorCarta.values().length;i++) {
			ValorCarta valor = ValorCarta.values()[i];
			
			
			for(int j=0;j<Simbolo.values().length;j++) {
				CartasPoker carta = new CartasPoker(valor,Simbolo.values()[j]);
				this.baraja.add(carta);
			}
		}
		
	}
	
	public String toString() {
		String dato = "Cartas en la baraja:\n";
		for(int i=0;i<baraja.size();i++) {
			dato += (i+1)+"-"+this.baraja.get(i).toString();
		}
		
		return dato+"\n";
		
	}
	
	public void Barajar() {
		Collections.shuffle(this.baraja);
		System.out.println("Se ha barajeado el deck");
	}
	
	public void Cortar() {
			int Corte = this.baraja.size()/2;
			for(int i=0;i<Corte;i++) {
				this.baraja.add(baraja.get(0));
				this.baraja.remove(0);
			}
			System.out.println("Se ha cortado las cartas a la mitad");
		
	
	}
	
	public CartasPoker SacarCarta() {
		CartasPoker cartaActual;
		try {
			cartaActual = this.baraja.get(0);
			this.baraja.remove(0);
		}
		catch(Exception NullPointerException) {
			System.out.println("Se acabaron las cartas del mazo");
			EmpezarMazo();
			Barajar();
			cartaActual = this.baraja.get(0);
			this.baraja.remove(0);
		}
		
		return cartaActual;
	}
	
	
	
	
	
}
