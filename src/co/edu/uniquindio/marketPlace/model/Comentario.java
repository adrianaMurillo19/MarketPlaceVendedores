package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;



public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String texto;
	private LocalDate fecha;
	private LocalTime Hora;
	Vendedor vendedorComentarista;
	Producto productoComentado;


	//Constructor
	public Comentario() {

	}

	public Comentario(String texto, LocalDate fecha, LocalTime hora, Vendedor vendedorComentarista,
			Producto productoComentado) {
		super();
		this.texto = texto;
		this.fecha = fecha;
		Hora = hora;
		this.vendedorComentarista = vendedorComentarista;
		this.productoComentado = productoComentado;
	}




	/**
	 * Metodo accesor
	 * @return
	 */
	public Producto getProductoComentado() {
		return productoComentado;
	}



	public void setProductoComentado(Producto productoComentado) {
		this.productoComentado = productoComentado;
	}



	/**
	 * Metodo accesor
	 * 
	 * @return vendedorComentarista
	 */
	public Vendedor getVendedorComentarista() {
		return  vendedorComentarista;
	}




	/**
	 * Metodo modificador 
	 * @param vendedorComentarista
	 */

	/*
	public void setVendedorComentarista(Vendedor  vendedorComentarista) {
		this. vendedorComentarista =  vendedorComentarista;
		this.nombre = vendedorComentarista.getNombre();
		this.apellido = vendedorComentarista.getApellido();

	}*/


	public void setVendedorComentarista(Vendedor vendedorComentarista) {
		this.vendedorComentarista = vendedorComentarista;
	}


	/**
	 * Metodo accesor
	 * 
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return Hora;
	}

	public void setHora(LocalTime hora) {
		Hora = hora;
	}

	@Override
	public String toString() {
		return "Comentario [texto=" + texto + ", fecha=" + fecha + ", Hora=" + Hora + ", vendedorComentarista="
				+ vendedorComentarista + ", productoComentado=" + productoComentado + "]";
	}


      

	






}
