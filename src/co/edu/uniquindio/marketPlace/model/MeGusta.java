package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MeGusta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate fecha;
	private LocalTime hora;
    private Producto productoMeGusta;
    char like;
    
    public char getLike() {
		return like;
	}



	public void setLike(char like) {
		this.like = like;
	}

	ArrayList<Vendedor>listaVendedorMegusta = new ArrayList<>();
    
    
    
	public Producto getProductoMeGusta() {
		return productoMeGusta;
	}



	public void setProductoMeGusta(Producto productoMeGusta) {
		this.productoMeGusta = productoMeGusta;
	}

	

	//Constructores

	public MeGusta(){

	}



	public ArrayList<Vendedor> getListaVendedorMegusta() {
		return listaVendedorMegusta;
	}

	public void setListaVendedorMegusta(ArrayList<Vendedor> listaVendedorMegusta) {
		this.listaVendedorMegusta = listaVendedorMegusta;
	}
	/**
	 * Metodo accesor
	 * 
	 * @return fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return hora
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param hora
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


}
