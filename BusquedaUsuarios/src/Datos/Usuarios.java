package Datos;

import logica.GestionUsuarios;

public class Usuarios extends GestionUsuarios {
	private int ID,NumeroLibrosPrestado;
	private String Usuario, Contrasena;
	
	public Usuarios(int ID2,String usuario2, String contrasena2) {
		this.ID = ID2;
		this.Usuario = usuario2;
		this.Contrasena = contrasena2;
		this.NumeroLibrosPrestado =0;
	}
	
	public Usuarios(int ID, String usuario, String contrasena, int NumPrestado) {
		this.ID = ID;
		this.Usuario = usuario;
		this.Contrasena = contrasena;
		this.NumeroLibrosPrestado = NumPrestado;
		
	}
	public Usuarios(String usuario, String contrasena) {
		this.Usuario = usuario;
		this.Contrasena = contrasena;
		
	}
	
	public Usuarios() {
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getNumeroLibrosPrestado() {
		return NumeroLibrosPrestado;
	}

	public void setNumeroLibrosPrestado(int numeroLibrosPrestado) {
		NumeroLibrosPrestado = numeroLibrosPrestado;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	
	
	
	

}
