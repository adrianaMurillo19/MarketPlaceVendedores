package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version 1
 *
 */

public abstract class Persona implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String cedula;
	private String direccion;
	private String usuario;
	private String password;
	

	//Contructor
	public Persona() {

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
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param cedula
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return nombre +" " + apellido +" ";
	}

	
	
	
}
