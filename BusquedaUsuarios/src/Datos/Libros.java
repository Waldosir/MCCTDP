package Datos;

import logica.GestionLibros;

public class Libros extends GestionLibros{
	private int ID, hojas,numerolibros,colaespera;
	private String nombre,descripcion;
	
	public Libros(int iD, String nombre,String descripcion,int hojas,int colaespera, int numlibros) {
		this.ID = iD;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hojas = hojas;
		this.colaespera = colaespera;
		this.numerolibros =numlibros;
		
	
	}
	
	public Libros(int iD, String nombre,String descripcion,int hojas) {
		this.ID = iD;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hojas = hojas;
		this.colaespera = 0;
		this.numerolibros = 1;
		
	}
	
	public Libros(String nombre,String descripcion,int hojas) {
		this.hojas = hojas;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Libros(String nombre,String descripcion,int hojas, int numlibros) {
		this.hojas = hojas;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numerolibros = numlibros;
	}
	public Libros() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getHojas() {
		return hojas;
	}

	public void setHojas(int hojas) {
		this.hojas = hojas;
	}

	public int getColaespera() {
		return colaespera;
	}

	public void setColaespera(int colaespera) {
		this.colaespera = colaespera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumerolibros() {
		return numerolibros;
	}

	public void setNumerolibros(int numerolibros) {
		this.numerolibros = numerolibros;
	}
	
	
	
	
	
}
