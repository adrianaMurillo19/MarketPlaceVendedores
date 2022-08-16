package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;
import java.util.ArrayList;
import co.edu.uniquindio.marketPlace.exception.ProductoExisteException;
import co.edu.uniquindio.marketPlace.exception.ProductoNoExisteException;
import javafx.scene.AccessibleRole;
import javafx.scene.image.Image;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version 1
 *
 */


public class Vendedor extends Persona implements Serializable{


	private static final long serialVersionUID = 1L;
	ArrayList<Producto>listaProductos = new ArrayList<>();
	ArrayList<Mensaje>listaMensajesEnviados = new ArrayList<>();
	ArrayList<Mensaje>listaMensajesRecibidos = new ArrayList<>();
	ArrayList<MeGusta>listaMeGustasV = new ArrayList<>();
	ArrayList<Comentario>listaComentarios = new ArrayList<>();
	ArrayList<Vendedor>listaAliados = new ArrayList<>();
	ArrayList<Vendedor>listaVendedoresAliadosAmigo = new ArrayList<>();

	//Constructores

	public Vendedor(ArrayList<Producto> listaProductos, ArrayList<Mensaje> listaMensajesEnviados,
			ArrayList<Mensaje> listaMensajesRecibidos, ArrayList<MeGusta> listaMeGustasV,
			ArrayList<Comentario> listaComentarios, ArrayList<Vendedor> listaAliados,
			ArrayList<Vendedor> listaVendedoresAliadosAmigo) {
		
		this.listaProductos = listaProductos;
		this.listaMensajesEnviados = listaMensajesEnviados;
		this.listaMensajesRecibidos = listaMensajesRecibidos;
		this.listaMeGustasV = listaMeGustasV;
		this.listaComentarios = listaComentarios;
		this.listaAliados = listaAliados;
		this.listaVendedoresAliadosAmigo = listaVendedoresAliadosAmigo;
	}

	public Vendedor(){

	}


	// ------------------------------------------------------------------------------------------------------
	// ---------------------------------------- CRUD PRODUCTO-----------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------





	/**
	 * Permite verificar si ya este el producto
	 * @param codigo
	 * @return boolean
	 */
	public boolean verificarProductoExistente(String codigo) {

		boolean codigoExiste =  false;

		for (Producto producto : listaProductos) {

			if(producto.getCodigo().equalsIgnoreCase(codigo)){
				codigoExiste = true;
				break;
			}
		}

		return codigoExiste;
	}

	/**
	 * Permite devolver el producto si lo encontró
	 * @param codigo
	 * @return Producto
	 */
	/*public Producto obtenerProducto(String codigo){

		ArrayList<Producto>listaProductos = new ArrayList<>();
		Producto encontrado = null;
		int i;
		for(i=0;i<listaProductos.size() && listaProductos!= null;i++){
			if(listaProductos.get(i).getCodigo().equalsIgnoreCase(codigo)){
				encontrado = listaProductos.get(i);
				break;
			}
		}
		return encontrado;
	} */

	
	public void buscarVendedor(){

	}

	public void sugerirContacto(){

	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaProductos
	 */
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaProductos
	 */
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaMensajesEnviados
	 */
	public ArrayList<Mensaje> getListaMensajesEnviados() {
		return listaMensajesEnviados;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaMensajesEnviados
	 */
	public void setListaMensajesEnviados(ArrayList<Mensaje> listaMensajesEnviados) {
		this.listaMensajesEnviados = listaMensajesEnviados;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaMensajesRecibidos
	 */
	public ArrayList<Mensaje> getListaMensajesRecibidos() {
		return listaMensajesRecibidos;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaMensajesRecibidos
	 */
	public void setListaMensajesRecibidos(ArrayList<Mensaje> listaMensajesRecibidos) {
		this.listaMensajesRecibidos = listaMensajesRecibidos;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaMeGustasV
	 */
	public ArrayList<MeGusta> getListaMeGustasV() {
		return listaMeGustasV;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaMeGustasV
	 */
	public void setListaMeGustasV(ArrayList<MeGusta> listaMeGustasV) {
		this.listaMeGustasV = listaMeGustasV;
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
	 * @param listaComentarios
	 */
	public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaAliados
	 */
	public ArrayList<Vendedor> getListaAliados() {
		return listaAliados;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param listaAliados
	 */
	public void setListaAliados(ArrayList<Vendedor> listaAliados) {
		this.listaAliados = listaAliados;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return listaVendedoresAliadosAmigo
	 */
	public ArrayList<Vendedor> getListaVendedoresAliadosAmigo() {
		return listaVendedoresAliadosAmigo;
	}


	/**
	 * Metodo modificador
	 * 
	 * @param listaVendedoresAliadosAmigo
	 */
	public void setListaVendedoresAliadosAmigo(ArrayList<Vendedor> listaVendedoresAliadosAmigo) {
		this.listaVendedoresAliadosAmigo = listaVendedoresAliadosAmigo;
		
	}




}
