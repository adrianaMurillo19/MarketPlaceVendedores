package co.edu.uniquindio.marketPlace.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import co.edu.uniquindio.marketPlace.exception.ProductoExisteException;
import co.edu.uniquindio.marketPlace.exception.ProductoNoExisteException;
import co.edu.uniquindio.marketPlace.exception.VendedorExisteException;
import co.edu.uniquindio.marketPlace.exception.VendedorNoExisteException;
import co.edu.uniquindio.marketPlace.model.Administrador;
import co.edu.uniquindio.marketPlace.model.Comentario;
import co.edu.uniquindio.marketPlace.model.Estado;
import co.edu.uniquindio.marketPlace.model.MarketPlace;
import co.edu.uniquindio.marketPlace.model.MeGusta;
import co.edu.uniquindio.marketPlace.model.Producto;
import co.edu.uniquindio.marketPlace.model.Vendedor;
import co.edu.uniquindio.marketPlace.persistence.Persistencia;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 * Esta 
 * @author Adriana Lucia Murillo
 *
 */


public class ModelFactoryController implements Runnable{

	Vendedor vendedores = new Vendedor();
	
	
	public Vendedor getVendedores() {
		return vendedores;
	}


	public void setVendedores(Vendedor vendedores) {
		this.vendedores = vendedores;
	}

	MarketPlace marketPlace;
	Vendedor vendedorActual;
	Producto producto;
	String logVendedor = "";
	String mensajeLog  ="";

     

	//Implementación de hilos para el Manejo de Persistencia
	Thread registrarAccionesSistema;

	Thread cargarResourceXML;
	Thread guardarResourceXML;

	Thread cargarResourceBinario;
	Thread guardarResourceBinario;
	Thread cargarDatosArchivo;
    Thread guardarVendedores;


	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		//
		
		Thread currentThread =  Thread.currentThread(); //Obtiene el objeto de hilo actual

		if(currentThread == cargarResourceXML){
			Persistencia.cargarRecursoMarketPlaceXML();
		}

		if(currentThread == guardarResourceXML){
			Persistencia.guardarRecursoMarketPlaceXML(marketPlace);
		}

		if(currentThread == cargarResourceBinario){
			Persistencia.cargarRecursoMarketPlaceBinario();
		}

		if(currentThread == guardarResourceBinario){
			Persistencia.guardarRecursoMarketPlaceBinario(marketPlace);
		}

		if(currentThread == cargarDatosArchivo){
			try {
				Persistencia.cargarDatosArchivos(marketPlace);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentThread == registrarAccionesSistema){
			Persistencia.guardarRegistroLog(mensajeLog, 1, logVendedor);
		}
		




	}







	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}


	//Constructor
	public ModelFactoryController(){



		//Registrar la accion del sistema
		//Persistencia.guardarRegistroLog("Inicio: administrador", 1, "Inicio de sesión");


		//FALTABA CREAR EL OBJETO MarketPlace
		marketPlace = new MarketPlace("MarketPlace de vendedores");
		iniciarDatos();







		//Persistencia.guardarRecursoMarketPlaceXML(marketPlace);





	}


	public void iniciarDatos(){


		//Info Administrador

		Administrador administrador = new Administrador();
		administrador.setUsuario("adm");
		administrador.setPassword("123");
		marketPlace.setAdministrador(administrador);



		Vendedor vendedor1 = new Vendedor();

		vendedor1.setNombre("Santiago");
		vendedor1.setApellido("Holguin");
		vendedor1.setCedula("02");
		vendedor1.setDireccion("Cr21");
		vendedor1.setUsuario("santiago");
		vendedor1.setPassword("1234");
		marketPlace.getListaVendedores().add(vendedor1);


		System.out.println("MarketPlace inicializado");
		
		cargarResourceXML = new Thread(this);
		cargarResourceXML.start();
		
		cargarDatosArchivo = new Thread(this);
		cargarDatosArchivo.start();

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
	 * Lista de vendedores
	 * @return ArrayList de vendedores
	 */
	public ArrayList<Vendedor> obtenerVendedores() {

		return marketPlace.getListaVendedores();
	}



	@SuppressWarnings("unchecked")
	public void guardarResourceArchivo() throws FileNotFoundException, IOException, InvocationTargetException, IllegalAccessException{


		//Persistencia.guardarVendedores(getMarketPlace().getListaVendedores());
		Persistencia.guardarPrueba((ObservableList<Vendedor>) getMarketPlace().getListaVendedores());
	}


	/**
	 * 
	 * @return ArrayList 
	 */
	public ArrayList<Producto> obtenerProductos() {

		ArrayList<Vendedor> obtenerVendedores = vendedorActual.getListaAliados();
		ArrayList<Producto> listaProductos = new ArrayList<>();


		listaProductos.addAll(vendedorActual.getListaProductos());
		for(Vendedor vendedor : obtenerVendedores){
			listaProductos.addAll(vendedor.getListaProductos());
		}

		return listaProductos;
	}



	//--------------------------------------------------------------
	//---------------------CRUD VENDEDOR----------------------------
	//--------------------------------------------------------------
	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param cedula
	 * @param direccion
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws VendedorExisteException 
	 */
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario,
			String clave) throws VendedorExisteException{ //InvocationTargetException 

		//Creamos el hilo que nos va permetir guardar el archivo serializado XML
		guardarResourceXML = new Thread(this);
		guardarResourceXML.start();
		guardarResourceBinario = new Thread(this);
		guardarResourceBinario.start();
	
		

		Vendedor vendedor = null;
		try{
			vendedor = marketPlace.agregarVendedor(nombre, apellido, cedula, direccion, usuario, clave);

			
			mensajeLog  += "Se guardó";
			logVendedor = vendedor.getNombre();
			//guardarVendedores.start();//guardar el vendedor
		}catch(VendedorExisteException e){
			e.getMessage();
		}

		
		registrarAccionesSistema = new Thread(this);
		registrarAccionesSistema.start();//Iniciamos el hilo
		
		
		
		return vendedor;

	}

	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public boolean eliminarVendedor(String cedula) {

		boolean vendedorEliminado = false;

		try{
			vendedorEliminado = getMarketPlace().eliminarVendedor(cedula);
		}catch(VendedorNoExisteException e){
			e.getMessage();
		}
		return vendedorEliminado;
	}



	/*public Vendedor buscarYaVendedor(String usuario) throws VendedorNoExisteException{

	Vendedor vendedor = null;

	//vendedor = marketPlace.encontrarVendedor(usuario);
	return vendedor;
}*/

	/**
	 * 
	 * @param cedulaActual
	 * @param nombre
	 * @param apellido
	 * @param cedula
	 * @param direccion
	 * @param usuario
	 * @param clave
	 * @return boolean
	 * @throws VendedorNoExisteException
	 */
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula,
			String direccion, String usuario, String clave) throws VendedorNoExisteException {
		return getMarketPlace().actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion, usuario, clave);
	}

	/*public Vendedor verificarVendedor(String cedula){
		//TODO
		return null;
	}*/




	//--------------------------------------------------------------
	//---------------------CRUD PRODUCTO----------------------------
	//--------------------------------------------------------------

	/**
	 * 
	 * @param nombre
	 * @param codigo
	 * @param imagen
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @return Producto
	 * @throws VendedorNoExisteException 
	 */
	public Producto crearProducto(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado) throws VendedorNoExisteException {

		Producto producto = marketPlace.CrearProducto(nombre, codigo, imagen, categoria, precio, estado, vendedorActual.getCedula());

		return producto;
	}



	/*public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario,
			String clave) throws VendedorExisteException{ //InvocationTargetException 


	    Vendedor vendedor = marketPlace.agregarVendedor(nombre, apellido, cedula, direccion, usuario, clave);

		return vendedor;

	}*/

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	/*public Producto eliminarProducto(String codigo){

		Producto productoEliminado = null;

		try{
			productoEliminado = getMarketPlace().eliminarProducto(codigo, vendedorActual.getCedula());
		}catch(Exception e){
			e.getMessage();
		}
		return productoEliminado;
	}*/

	/**
	 * 
	 * @param nombre
	 * @param codigo
	 * @param imagen
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @return
	 * @throws VendedorNoExisteException
	 */

	/*public boolean actualizarProducto(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado) throws VendedorNoExisteException{

		return getMarketPlace().actualizarProducto(nombre, codigo, imagen, categoria, precio, estado, vendedorActual.getCedula());


	}*/

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean buscarProducto(String codigo){

		return marketPlace.buscarProducto(codigo, vendedorActual);
	}

	/*public Vendedor agregarAmigo(String usuario) throws VendedorNoExisteException{

	Vendedor vendedor = encontrarVendedor(usuario);


	if(encontrarVendedor(usuario)!=null){

		vendedor.listaVendedoresAliadosAmigo.add(vendedor);

	}
	return vendedor ;



	//return amigo;
}*/

	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	/**
	 * Metodo accesor
	 * @return
	 */
	public Vendedor getVendedorActual() {
		return vendedorActual;
	}

	/**
	 * Metodo modificador
	 * @param vendedorActual
	 */
	public void setVendedorActual(Vendedor vendedorActual) {
		this.vendedorActual = vendedorActual;
	}



	void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alerta = new Alert(alertType);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}

	public boolean verificarUsuario(String usuario, String contrasenia) {
		// TODO Auto-generated method stub
		return marketPlace.verificarUsuario(usuario,contrasenia);
	}



	//------------------------------------------------------------------------------------------------
	//------------------------------COMENTARIO--------------------------------------------------------
	//------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param texto
	 * @param timeStamp
	 * @param vendedor
	 * @param producto
	 * @return
	 */
	public boolean crearComentario(String texto, LocalDate fecha, LocalTime hora, Vendedor vendedor, Producto productoComentado) {

		boolean bandera = true;
		bandera = marketPlace.agregarComentario(texto, fecha, hora, vendedor, productoComentado);
		return bandera;
	}

	//----------------------REVISION
	public ArrayList<Comentario> obtenerComentarios() {

		return producto.getListaComentarios();
	}

	//lista de los productos con me gusta
	public ArrayList<MeGusta> obtenerMeGustas(){
		
		return producto.getListaMeGustas();
	}
	
	//---------------------------------------------------------------------------------------------------





	//-------------------------------------------------------------------------------------
	//---------------------VERSION NUEVA PRODUCTOS-----------------------------------------

	public boolean agregarProducto(String documento, String nombre, String codigo, Image imagen, String categoria, double precio,
			Estado estado, LocalDate fecha, LocalTime hora) throws VendedorNoExisteException {
		boolean bandera;
		bandera = marketPlace.agregarProductoVendedor(documento, nombre, codigo, imagen, categoria, precio, estado, fecha, hora);
		return bandera;
	}

	/**
	 * Revisar
	 * @param codigo
	 * @return
	 * @throws VendedorNoExisteException
	 */
	public boolean eliminarProducto(String codigo, String vendedorActual) throws VendedorNoExisteException {

		boolean bandera = false;

		try{
			bandera = marketPlace.eliminarProductos(codigo, vendedorActual);
		}catch(ProductoNoExisteException e){
			e.printStackTrace();
		}
		return bandera;
	}


	public boolean agregarMeGusta(Producto prodMeGusta, LocalDate fecha, LocalTime hora, char like,
			Vendedor venMeGusta) throws ProductoNoExisteException, VendedorNoExisteException, ProductoExisteException {
		boolean bandera;
		return bandera = getMarketPlace().crearMeGusta(prodMeGusta, fecha, hora, like, venMeGusta);
	}


	public void actualizarProducto(String nombreNuevo, String codigoNuevo, Image imagenNuevo, String categoriaNuevo,
			double precioNuevo, Estado estadoNuevo) throws ProductoNoExisteException, VendedorNoExisteException {
		
		marketPlace.actualizarProductos(nombreNuevo, codigoNuevo, imagenNuevo, categoriaNuevo, precioNuevo, estadoNuevo);
		
	}



	//---------------------------------------------------------------------------------------












	/*public void cargarResourceBinario(){

	//marketPlace = Persistencia.cargarRecursoMarketPlaceBinario();

}*/

	/*public void guardarResourceBinario(){

	Persistencia.guardarRecursoMarketPlaceBinario(marketPlace);
}*/

	/*public void cargarResourceXML(){

		marketPlace = Persistencia.cargarRecursoMarketPlaceXML();
	}*/

	/*
public void guardarResourceXML(){

	Persistencia.guardarRecursoMarketPlaceXML(marketPlace);
}*/







	//LOG
	/*public void registrarAccionesSistema(String mensaje, int nivel, String accion){

		Persistencia.guardarRegistroLog(mensaje, nivel, accion);


	}*/




}
