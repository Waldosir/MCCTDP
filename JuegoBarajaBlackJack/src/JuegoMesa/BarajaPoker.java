package JuegoMesa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Baraja.CartasPoker;
import Baraja.Simbolo;
import Baraja.ValorCarta;

public class BarajaPoker {
	private ArrayList baraja = new ArrayList();
	
	public BarajaPoker() {
		EmpezarMazo();
	}
	
	public void EmpezarMazo() {
		baraja.clear();
		for(int i=0;i<13;i++) {
			ValorCarta valor = ValorCarta.values()[i];
			
			for(int j=0;j<4;j++) {
				CartasPoker carta = new CartasPoker(valor,Simbolo.values()[j]);
				this.baraja.add(carta);
			}
		}
	}
	
	public String toString() {
		/*
		try {
			CartasPoker cartaActual = baraja.get(0);
			this.baraja.remove(0);
			return cartaActual.getValorCarta() + " de "+ cartaActual.getSimbolo();
		}
		catch(Exception e) {
			return "no hay cartas en el mazo para tomar";
		}
		*/
		
		
		return "HOLAAAAAAA";
		
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
	
	public Object DarCarta() {
		CartasPoker cartaActual = (CartasPoker) this.baraja.get(0);
		this.baraja.remove(0);
		return cartaActual;
	}
	
	
	
	
	
}
