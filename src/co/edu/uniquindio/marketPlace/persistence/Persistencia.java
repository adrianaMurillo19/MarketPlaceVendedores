package co.edu.uniquindio.marketPlace.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import co.edu.uniquindio.marketPlace.persistence.*;
import javafx.collections.ObservableList;
import co.edu.uniquindio.marketPlace.model.*;

/**
 * 
 * @author Adriana Lucia Murillo
 *
 */
public class Persistencia {

	//Archivos-------------------------------------------------------------------------
	public static final String RUTA_ARCHIVO_VENDEDORES = "src/resources/archivoVendedores.txt";

	//ruta del archivo BINARIO---------------------------------------------------------------------------
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "C://td//persistencia//model.dat";
	
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_XML = "C://td//persistencia//model.xml";

	//Archivo de registro del sistema-------------------------------------------------------------
	public static final String RUTA_ARCHIVO_LOG = "C://td//persistencia//log//MarketPlace_Log.txt";

	//Archivo de objetos de marketPlace-------------------------------------------------------------------
	public static final String RUTA_ARCHIVO_OBJETOS = "C://td//persistencia//archivos//ArchivoObjetos.txt";


	/**
	 * 
	 * @param marketPlace
	 * @throws IOException
	 */
	public static void cargarDatosArchivos(MarketPlace marketPlace) throws IOException, FileNotFoundException{


		//Cargar archivos vendedores 
		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();

		if(vendedoresCargados.size() > 0)
			marketPlace.getListaVendedores().addAll(vendedoresCargados);


		//cargar .....

	}




	/**
	 * Este metodo permite indicar que la informacion ingresada de 
	 * los vendedores se guarde en un archivo
	 * @param listVendedores
	 * @throws IOException
	 */
	/*public static void guardarVendedores(ArrayList<Vendedor>listVendedores) throws IOException{

		String contenido = "";

		for(Vendedor vendedor : listVendedores){

			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellido()+"@@"+vendedor.getCedula()+"@@"+vendedor.getDireccion()+"@@"
					+vendedor.getUsuario()+"@@"+vendedor.getPassword()+"\n";
		}

		ArchivoUtil.guardarAchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
	}*/
	
	
	public static void guardarPrueba(ObservableList<Vendedor>listVendedores) throws IOException{

		String contenido = "";

		for(Vendedor vendedor : listVendedores){

			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellido()+"@@"+vendedor.getCedula()+"@@"+vendedor.getDireccion()+"@@"
					+vendedor.getUsuario()+"@@"+vendedor.getPassword()+"\n";
		}

		ArchivoUtil.guardarAchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
	}
	
	
	/**
	 * 
	 */
	/*public static void  guardarProductos(ObservableList<Producto>listProductos){
		
		
		String contenido = " ";
		
		for(Producto producto : listProductos )
		
	}*/
	
	
	
	/*	// TODO Auto-generated method stub
		String contenido = "";

		for(Empleado empleado:listaEmpleados)
		{
			contenido+= empleado.getNombre()+","+empleado.getApellido()+","+empleado.getCedula()+","+empleado.getFechaNacimiento()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
	}
*/

	/**
	 * 
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Vendedor>cargarVendedores() throws IOException{

		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

		ArrayList<String>cadenaVendedores = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);

		String linea="";

		for(int i=0; i < cadenaVendedores.size(); i++){

			linea = cadenaVendedores.get(i);
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@@")[0]);//un Arreglo con cada una de esa posiciones---> eso devuelve el .SPLIT
			vendedor.setApellido(linea.split("@@")[1]);
			vendedor.setCedula(linea.split("@@")[2]);
			vendedor.setDireccion(linea.split("@@")[3]);
			vendedor.setUsuario(linea.split("@@")[4]);
			vendedor.setPassword(linea.split("@@")[5]);
			vendedores.add(vendedor);
			//marketPlace.getListaVendedores().add(vendedor);

		}	

		return vendedores;
	}


	/**
	 * Este metodo permite Leer un Recurso Binario
	 * @return
	 * @throws Exception
	 */
	public static MarketPlace leerMarketPlace() throws Exception{

		Object object = ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);

		MarketPlace marketPlace = (MarketPlace) object;

		return marketPlace;

	}


	//--------------------------------
	//----------LOG MarketPlace-------
	//--------------------------------

	/*
	 * permite indicarle a ArchivoUtil que guarde el re
	 */
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion){

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);

	}

	


	public static void guardarObjetos(ArrayList<Vendedor>listVendedores, String ruta) throws IOException{

		String contenido = "";

		for(Vendedor vendedorAux: listVendedores){

			contenido+= vendedorAux.getNombre()+"@@"+vendedorAux.getApellido()+"@@"+
					vendedorAux.getCedula()+"@@"+vendedorAux.getDireccion()+"@@"+
					vendedorAux.getUsuario()+"@@"+vendedorAux.getPassword()+"\n";
		}

          ArchivoUtil.guardarAchivo(ruta, contenido, true);
	}




	public static MarketPlace cargarRecursoMarketPlaceBinario() {


		MarketPlace marketPlace = null;
		
		try{
			
			marketPlace = (MarketPlace)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return marketPlace;
	}




	public static void guardarRecursoMarketPlaceBinario(MarketPlace marketPlace) {
		
		try{
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, marketPlace);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}




	public static MarketPlace cargarRecursoMarketPlaceXML() {
		
		MarketPlace marketPlace = null;
		
		try{
			marketPlace = (MarketPlace)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return marketPlace;
	}




	public static void guardarRecursoMarketPlaceXML(MarketPlace marketPlace) {
		
		try{
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, marketPlace);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}



	
	
	/*
	public boolean validarUsuario(String usuario, String contrasenia) throws IOException{
		int i = 0;
		ArrayList<Vendedor>vendedor = new ArrayList<>();


		ArrayList<Vendedor>verificarVendedores = Persistencia.cargarVendedores(RUTA_ARCHIVO_VENDEDORES);

		for(String vendedores : verificarVendedores){

			Vendedor vendedorAux = vendedor.get(i);
			if(vendedorAux.getUsuario().equalsIgnoreCase(usuario) && vendedorAux.equals(contrasenia)){
				return true;
			}

		}

		return false;
	}*/


}
