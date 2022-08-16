package co.edu.uniquindio.marketPlace.controller;

import java.util.ArrayList;

import co.edu.uniquindio.marketPlace.exception.VendedorNoExisteException;
import co.edu.uniquindio.marketPlace.model.*;
import javafx.scene.image.Image;

public class CrudProductoVendedorController {

	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;
	
	
	//Constructor
	public CrudProductoVendedorController(ModelFactoryController modelFactoryController) {
		
		this.modelFactoryController = modelFactoryController;
	    marketPlace = modelFactoryController.getMarketPlace();
	}

	
	/**
	 * Metodo accesor
	 * @return
	 */
	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	/**
	 * Metodo modificados
	 * @param marketPlace
	 */
	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}
	
	/**
	 * En esta Lista se va almacenando la INFORMACIÓN de los PRODUCTOS
	 * @return
	 */
	public ArrayList<Producto>obtenerProductos(){
		
		return modelFactoryController.obtenerProductos();
	}

	//REVISAR
	public Producto CrearProducto(String nombre, String codigo, Image imagen, String categoria, double precio,
			Estado estado) throws VendedorNoExisteException {
		
		return  modelFactoryController.crearProducto(nombre, codigo, imagen, categoria, precio, estado);
	}

	//FALTA
	public void actualizarProducto(String nombreNuevo, String codigoNuevo, Image imagenNuevo, String categoriaNuevo,
			double precioNuevo, Estado estadoNuevo) {
		// TODO Auto-generated method stub
		
	}

	//Falta
	public boolean eliminarProducto() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
}
