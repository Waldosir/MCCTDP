package Principal;

public class Hanoi {
	private int contar = 0;
	public Hanoi(int disco, int a, int b, int c) {
		mover(disco, a, b, c);
	}
	private void mover(int disco, int a, int b, int c) {
		this.contar++;
		if(disco ==1) {
			//System.out.println("Mueve el disco "+disco +" del poste: "+a + " al poste: "+b);
		}else {
			mover(disco-1,a,c,b);
			//System.out.println("Mueve el disco "+disco +" del poste: "+a + " al poste: "+b);
			mover(disco-1, c,b,a);
		}
		

	}
	
	public int getContar() {
		return this.contar;
	}
	
	public void setHanoi(int disco, int a, int b, int c) {
		this.contar = 0;
		mover(disco, a, b, c);
	}
	

}
