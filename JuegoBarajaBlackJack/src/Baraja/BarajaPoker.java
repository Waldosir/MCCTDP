package Baraja;

import java.util.ArrayList;
import java.util.Collections;

public class BarajaPoker {
	private ArrayList<CartasPoker> baraja = new ArrayList<CartasPoker>();
	private int numeroDecks;
	
	public BarajaPoker(int numeroDecks) { //Entra un numero de Decks a crear
		this.numeroDecks = numeroDecks;
		empezarMazo();
		//Para luego clonar la cantidad de cartas del Deck
		
	}
	
	
	public void empezarMazo() {
		baraja.clear();//Limpia Deck
		//Hace un deck
		for(int i=0;i<this.numeroDecks;i++) {
			crearMazo(); //Crea "numeroDecks" en total para un solo Deck
		}
		System.out.println("\nSe ha creado un nuevo mazo\n");
		
	}
	
	private void crearMazo() {//Creacion del mazo con sus 52 cartas iniciales
		for(int i=0;i<ValorCarta.values().length;i++) {
			ValorCarta valor = ValorCarta.values()[i];//Valor en numero de la carta
			
			
			for(int j=0;j<Simbolo.values().length;j++) { //Se añade Constructor con valor de la carta y su simbolo
				CartasPoker carta = new CartasPoker(valor,Simbolo.values()[j]);
				this.baraja.add(carta);//Se añade al ArrayList
			}
		}
		
	}
	
	public String toString() {//Te da las cartas que restan de la baraja
		String dato = "Cartas en la baraja:\n";
		for(int i=0;i<baraja.size();i++) {
			dato += (i+1)+"-"+this.baraja.get(i).toString();
		}
		
		return dato+"\n";
		
	}
	
	public void barajar() {//Mueve al azar de lugar las cartas
		Collections.shuffle(this.baraja);
		System.out.println("Se ha barajeado el deck\n");
	}
	
	public void cortar() {//Corta a la mitad las cartas y pone la mitad superior de la baraja abajo de la mitad inferior
			int Corte = (int)(this.baraja.size()/2);
			for(int i=0;i<Corte;i++) { //Va poniendo carta debajo una por una. Proceso lento
				this.baraja.add(baraja.get(0));
				this.baraja.remove(0);
			}
			System.out.println("Se ha cortado las cartas a la mitad");
		
	
	}
	
	public CartasPoker sacarCarta() {//Dar una carta del Deck - Sacar una carta
		CartasPoker cartaActual;
		try {
			cartaActual = this.baraja.get(0); //Toma la carta
			this.baraja.remove(0);//Remueve la carta de la baraja
		}
		catch(Exception NullPointerException) { //Si se acaban las cartas
			System.out.println("Se acabaron las cartas del mazo");
			crearMazo();//Inicia mazo 
			barajar();//Barajea mazo
			cartaActual = this.baraja.get(0); //Toma la carta
			this.baraja.remove(0);//Remueve la carta de la baraja
		}
		
		return cartaActual; //Regesa la carta tomada
	}
	
	public String mostrarCartaTope() {
		CartasPoker cartaActual;
		try {
			cartaActual = this.baraja.get(0); //Toma la carta
			this.baraja.remove(0);//Remueve la carta de la baraja
		}
		catch(Exception NullPointerException) { //Si se acaban las cartas
			System.out.println("Se acabaron las cartas del mazo");
			crearMazo();//Inicia mazo 
			barajar();//Barajea mazo
			cartaActual = this.baraja.get(0); //Toma la carta
			this.baraja.remove(0);//Remueve la carta de la baraja
		}
		
		return "La carta del tope es "+cartaActual.toString(); //Regesa la carta tomada
	}
	
	
	
	
	
}
