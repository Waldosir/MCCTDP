package Personas;

import java.util.ArrayList;

import Baraja.CartasPoker;

public class Jugador {
	
	private String nombre;//Nombre jugador
	private int numeroFichas;//Numero de fichas
	private boolean estadoEnJuego;//Si puede seguir dentro del juego (Si tiene 0 fichas no puede volver al juego)
	private ArrayList<CartasPoker> cartas = new ArrayList<CartasPoker>(); //Guardar las cartas del jugador
	private int montoAApostar; //El monto que va a apostar el jugador
	private String estadoFinalRonda; //Ver si gana, pierde o empata
	
	public Jugador(String nombre, int numeroFichas) {//Constructor de jugador
		this.nombre = nombre;
		this.numeroFichas = numeroFichas;
		this.estadoEnJuego = true;
		this.montoAApostar=0;
		this.estadoFinalRonda  = "";
	}
	
	public String getEstadoFinalRonda() {//Ver si gana-pierde-empata
		return this.estadoFinalRonda;
	}
	
	public void setEstadoFinalRonda(String estadoFinalRonda) {//Colocar su estado actual
		this.estadoFinalRonda  = estadoFinalRonda;
	}
	
	public int getMontoAApostar() {//Ver si perdió o sigue jugando
		return this.montoAApostar;
	}
	
	public void setMontoAApostar(int montoAApostar) {//Ver si perdió o sigue jugando
		this.montoAApostar = montoAApostar ;
	}
	
	public int getNumeroDeCartasEnMano() { //Ver cantidad de cartas en su mano
		return cartas.size();
	}
	
	public boolean getEstadoEnJuego() {//Ver si perdió o sigue jugando
		return estadoEnJuego;
	}
	
	public void setEstadoEnJuego(boolean estadoEnJuego) {//Cambia su estado en el juego
		this.estadoEnJuego =estadoEnJuego;
	}
	
	public void retirarCartas() {//Retira sus cartas
		this.cartas.clear();
		if(this.numeroFichas>0) {//Si tiene como apostar puede volver a jugar
			this.estadoEnJuego = true;
		}
		this.montoAApostar=0;
		this.estadoFinalRonda  = "";
	}

	public int getNumeroFichas() {//Saber fichas del jugador
		return numeroFichas;
	}

	public void darNumeroFichas(int numeroFichas) {//Dar fichas al jugador
		this.numeroFichas -= numeroFichas;
	}

	public CartasPoker getCartas(int pos) {//Saber las cartas de poker que tiene
		return cartas.get(pos);
	}
	
	public void setCartas(ArrayList<CartasPoker> cartas) {//Saber las cartas de poker que tiene
		this.cartas = cartas;
	}

	public void tomarCarta(CartasPoker carta) {
		cartas.add(carta);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		String datos="Jugador |"+this.nombre+"| con fichas: "+this.numeroFichas;;

		return datos;
	}
	
	public String manoJugador() {
		String dato="Cartas del jugador "+this.nombre+":\n";
		for(int i=0;i<cartas.size();i++) {
			dato+= "->"+cartas.get(i).toString();
		}
		return dato;
	}
	
	public int valorDeSusCartasEnBJ() {
		//Los ases, su valor numerado y el valorCarta de la suma
		int As = 0; int valorCarta = 0, sumaNumeracionCartas = 0;
		CartasPoker carta;//Objeto carta
		
		for(int i=0;i<this.getNumeroDeCartasEnMano();i++) {//Checa toda su mano
			carta =  this.getCartas(i);//Saca una carta
			valorCarta = carta.getValorCarta().getNumeroEnteroCarta();//Obtiene el valor de la carta
			if(valorCarta==1) {//Si es un As
				As ++;
			}
			else if(valorCarta<10) {//Si vale menos de 10
				sumaNumeracionCartas += valorCarta;
			}else  {//Si es J, Q o K
				sumaNumeracionCartas += 10;
			}
			
			
		}
		
		while(As>0) {//Suma los Ases al final
			if(sumaNumeracionCartas<=10 && As==1) {//Ultimo As si es menor de 10 suma 11
				sumaNumeracionCartas +=11;
			}else {//Si hay mas de dos As
				sumaNumeracionCartas +=1;
			}
			As--;//Cuando termina de sumar cada As
		}
		return sumaNumeracionCartas;
	}

	

}
