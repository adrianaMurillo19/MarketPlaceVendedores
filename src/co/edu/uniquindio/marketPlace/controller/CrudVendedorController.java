package co.edu.uniquindio.marketPlace.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import co.edu.uniquindio.marketPlace.exception.*;
import co.edu.uniquindio.marketPlace.model.*;

public class CrudVendedorController {

	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;


	//Contructor
	public CrudVendedorController(ModelFactoryController modelFactoryController) {

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
     * Metodo modificador
     * @param marketPlace
     */
	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}
    
	/**
	 * list de vendedores
	 * @return
	 */
	public ArrayList<Vendedor>obtenerVendedores(){
		return modelFactoryController.obtenerVendedores();
	}


	public  Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion,String usuario, String clave) throws VendedorExisteException {
			
		
		return modelFactoryController.crearVendedor(nombre,  apellido, cedula, direccion,usuario, clave);
	}


	public boolean eliminarVendedor(String cedula) {
		
		return modelFactoryController.eliminarVendedor(cedula);
	}
	
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion, String usuario, String clave) throws VendedorNoExisteException
	{
		return modelFactoryController.actualizarVendedor(cedulaActual, nombre,  apellido, cedula, direccion, usuario,  clave);
	}

	
	
	
	/**
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public void guardarDatos() throws FileNotFoundException, InvocationTargetException, IllegalAccessException, IOException{
		
		modelFactoryController.guardarResourceArchivo();
	}
	
	
	

}
