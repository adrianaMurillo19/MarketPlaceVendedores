package co.edu.uniquindio.marketPlace.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.AccessibleRole;
import javafx.scene.image.Image;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version 1
 *
 */

public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;  


	private String nombre;
	private String codigo;
	private Image imagen;
	private String categoria;
	private double precio;
	private Estado estado;
	LocalDate fecha;
	LocalTime hora;

	//Vendedor vendedorPro;
	ArrayList<MeGusta>listaMeGustas = new ArrayList<>();
	ArrayList<Comentario>listaComentarios = new ArrayList<>();//cada producto debe tener un listado de comentarios

	//Constructores

	public Producto(){

	}

	/**
	 * Metodo accesor
	 * 
	 * @return ListaMeGustas
	 */
	public ArrayList<MeGusta> getListaMeGustas() {
		return listaMeGustas;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaMeGustas
	 */
	public void setListaMeGustas(ArrayList<MeGusta> listaMeGustas) {
		this.listaMeGustas = listaMeGustas;
	}



	/**
	 * Metodo accesor
	 * 
	 * @return listaComentarios
	 */
	public ArrayList<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaComentario
	 */
	public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Metodo accesor
	 * 
	 * @return coidgo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return imagen
	 */
	public Image getImagen() {
		return imagen;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param imagen
	 */
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	/**
	 * Metodo accesor
	 * 
	 * @return precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return Estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaMensajesEnviados
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
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







	/**
	 * REVISAR
	 */
	public void obtenerEstadoTransaccion(){

	}




	@Override
	public String toString() {
		return nombre + " " + precio +" " +estado + " ";
	}



	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((listaComentarios == null) ? 0 : listaComentarios.hashCode());
		result = prime * result + ((listaMeGustas == null) ? 0 : listaMeGustas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (estado != other.estado)
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (listaComentarios == null) {
			if (other.listaComentarios != null)
				return false;
		} else if (!listaComentarios.equals(other.listaComentarios))
			return false;
		if (listaMeGustas == null) {
			if (other.listaMeGustas != null)
				return false;
		} else if (!listaMeGustas.equals(other.listaMeGustas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}












}
