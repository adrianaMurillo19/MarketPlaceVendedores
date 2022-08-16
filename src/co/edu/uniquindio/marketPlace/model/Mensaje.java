package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Mensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate fecha;
	private LocalTime hora;
	private Vendedor vendedorMensajes;

	//Constructor
	public Mensaje(LocalDate fecha, LocalTime hora, Vendedor vendedorMensajes) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.vendedorMensajes = vendedorMensajes;
	}


	public Mensaje(){

	}




	public LocalDate getFecha() {
		return fecha;
	}






	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}






	public LocalTime getHora() {
		return hora;
	}






	public void setHora(LocalTime hora) {
		this.hora = hora;
	}






	public Vendedor getVendedorMensajes() {
		return vendedorMensajes;
	}



	public void setVendedorMensajes(Vendedor vendedorMensajes) {
		this.vendedorMensajes = vendedorMensajes;
	}




}
