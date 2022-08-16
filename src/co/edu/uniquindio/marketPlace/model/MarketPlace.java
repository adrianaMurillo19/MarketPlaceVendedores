package co.edu.uniquindio.marketPlace.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import co.edu.uniquindio.marketPlace.exception.*;
import javafx.scene.AccessibleRole;
import javafx.scene.image.Image;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version Final
 *
 */


public class MarketPlace implements Serializable{


	private static final long serialVersionUID = 1L;

	private String nomMarket;
	Vendedor vendedor;
	ArrayList<Vendedor>listaVendedores = new ArrayList<>();
	Administrador administrador;







	// Generar constructores
	/**
	 * Constructor de la clase MarketPlcae
	 * 
	 * @param nombre
	 * 
	 */
	public MarketPlace(String nomMarket) {


		this.nomMarket = nomMarket;

		Vendedor vendedor = new Vendedor();

		vendedor.setNombre("Adriana");
		vendedor.setApellido("Murillo");
		vendedor.setCedula("01");
		vendedor.setDireccion("Calle 10");
		vendedor.setUsuario("adriana");
		vendedor.setPassword("19");
		listaVendedores.add(vendedor);

		Vendedor vendedors = new Vendedor();

		vendedors.setNombre("Manuel");
		vendedors.setApellido("Mendez");
		vendedors.setCedula("14");
		vendedors.setDireccion("Calle 27");
		vendedors.setUsuario("manuel");
		vendedors.setPassword("1235");
		listaVendedores.add(vendedors);






	}

	public MarketPlace(){


	}



	// --------------------------------------------------------------------------
	// ---------------------------- CRUD VENDEDOR -------------------------------
	// --------------------------------------------------------------------------




	/**
	 * Metodo para  crear Vendedor
	 * @throws VendedorExisteException 
	 * @retunr Vendedor
	 */
	public Vendedor agregarVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String password) throws VendedorExisteException    {

		boolean vendedorExiste = false;

		if(vendedorExiste==true){
			throw new VendedorExisteException ("Ya existe el vendedor con cedula"+ cedula+"no se puede crear, ya existe");
		}else{

			Vendedor vendedor = new Vendedor();

			vendedor.setNombre(nombre);
			vendedor.setApellido(apellido);
			vendedor.setCedula(cedula);
			vendedor.setDireccion(direccion);
			vendedor.setUsuario(usuario);
			vendedor.setPassword(password);
			getListaVendedores().add(vendedor);
			//vendedor.listaAliados.add(vendedor);//para tambien ir agregandolo en la lista de vendedores aliados

			vendedor.setListaAliados(getListaVendedores());

			return vendedor;
		}
	}


	/**
	 * 
	 * @param cedula
	 * @return boolean
	 */
	public boolean existeVendedor(String cedula){
		boolean vendedorExiste =  false;

		for(Vendedor vendedor : listaVendedores){
			if(vendedor.getCedula().equalsIgnoreCase(cedula)){
				vendedorExiste = true;
				break;
			}
		}
		return vendedorExiste;
	}



	/**
	 * Metodo para buscar Vendedor
	 * 
	 * @param numeroDocumentoNuevo
	 * @throws Throwable 
	 * @return Vendedor
	 */
	public boolean buscarVendedor(String idDocumento) throws VendedorNoExisteException{

		boolean ven = false;

		for(Vendedor vendedor: listaVendedores){
			if(vendedor.getCedula().equalsIgnoreCase(idDocumento)){
				ven = true;
				break;
			}
		}
		return ven ;
	}




	/**
	 * Metodo para eliminar Vendedor
	 * 
	 * @param numeroDocumento
	 * @throws VendedorNoExisteException 
	 * @throws Throwable 
	 * @return boolean
	 */

	public boolean eliminarVendedor(String cedula) throws VendedorNoExisteException  {

		boolean flagEliminado = false;
		Vendedor vendedor = null;

		vendedor =  verificarVendedor(cedula);

		if(vendedor != null){

			getListaVendedores().remove(vendedor);
			flagEliminado = true;
		}else{
			throw new VendedorNoExisteException("El empleado con cédula "+ cedula+" no se puede eliminar. No existe");
		}

		return flagEliminado;
	}

	/**
	 * Este Métod permite buscar el vendedor mediante el numero de cedula y retornarlo
	 * @param cedula
	 * @return
	 * @throws VendedorNoExisteException
	 */
	public Vendedor verificarVendedor(String cedula) throws VendedorNoExisteException{

		Vendedor vendedorEncontrado = null;
		for(Vendedor vendedor : listaVendedores){

			if(vendedor.getCedula().equalsIgnoreCase(cedula)){
				vendedorEncontrado = vendedor;
				break;
			}
			//mensajeException
		}
		return vendedorEncontrado;
	}

	/**
	 * Metodo para actualizar Vendedor
	 * 
	 * @param cedulaNuevo
	 * @throws VendedorNoExisteException 
	 * 
	 */
	public boolean actualizarVendedor(String cedualActual, String nombre, String apellido, String cedula, String direccion, String usuario, String clave) throws VendedorNoExisteException  {


		Vendedor vendedor = null;
		boolean vendedorActualizado = false;

		vendedor = verificarVendedor(cedualActual);

		if(vendedor!=null){

			vendedor.setNombre(nombre);
			vendedor.setApellido(apellido);
			vendedor.setCedula(cedula);
			vendedor.setDireccion(direccion);
			vendedor.setUsuario(usuario);
			vendedor.setPassword(clave);
			return vendedorActualizado = true;

		}
		return vendedorActualizado;
	}




	//-----------------------PRODUCTO VERSION  2 ----------------------



	public boolean agregarProductoVendedor(String cedula, String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado, LocalDate fecha, LocalTime hora) throws VendedorNoExisteException {

		//fechaHora = new SimpleDateFormat("yyyy/MM/dd \nHH:mm:ss").format(Calendar.getInstance().getTime());
		Producto producto = new Producto();
		boolean bandera = false;

		Vendedor vendedor =  verificarVendedor(cedula);;
		//
		try{

			if(verificarVendedor(cedula) != null){

				producto.setNombre(nombre);
				producto.setCodigo(codigo);
				producto.setImagen(imagen);
				producto.setCategoria(categoria);
				producto.setPrecio(precio);
				producto.setEstado(estado);
				producto.setFecha(fecha);
				producto.setHora(hora);
				vendedor.getListaProductos().add(producto);

				//vendedor.setListaProductos(listProductos);


				return bandera = true;

			}else{

				throw new VendedorNoExisteException("El empleado con cédula "+ cedula+" no existe");
			}
		}catch(Exception e){
			System.out.println("Error" + e);
		}
		return bandera;
	}


	/**
	 * Permite eliminar algún producto de acuerdo a la validación de seguridad
	 * @param codigo
	 * @param vendedorActual
	 * @return
	 * @throws ProductoNoExisteException
	 * @throws VendedorNoExisteException
	 */
	public boolean eliminarProductos(String codigo, String vendedorActual) throws ProductoNoExisteException, VendedorNoExisteException{

		boolean bandera = false;

		Producto producto = obtenerProducto(codigo);
		int i = 0;
		Vendedor vendedor = verificarVendedor(vendedorActual);

		if(producto != null){

			vendedor.getListaProductos().remove(producto);//remove(producto);
			bandera = true;
		}else{
			throw new ProductoNoExisteException("El Producto con codigo"+ codigo+" no se puede eliminar. No existe");
		}
		return bandera;
	}

	/**
	 * Permite Actualizar los  datos del Producto que fue buscado
	 * @param nombre
	 * @param codigo
	 * @param imagen
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @param vendedorPro
	 * @return boolean
	 * @throws VendedorNoExisteException 
	 * @throws ProductoNoExisteException 
	 */
	public boolean actualizarProductos(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado) throws ProductoNoExisteException, VendedorNoExisteException {

		Producto producto = null;
		boolean productoActualizado = false;

		producto = obtenerProducto(codigo);

		if(producto != null){

			producto.setNombre(nombre);
			producto.setCodigo(codigo);
			producto.setImagen(imagen);
			producto.setCategoria(categoria);
			producto.setPrecio(precio);
			producto.setEstado(estado);
			return productoActualizado = true;
		}

		return productoActualizado;
	}


	//-----------------------------------------------------------------













	// --------------------------------------------------------------------------
	// ---------------------------- CRUD PRODUCTO-------------------------------
	// --------------------------------------------------------------------------

	/**
	 * Permite crear los productos
	 * @param nombre
	 * @param codigo
	 * @param imagen
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @param vendedorActual
	 * @return
	 * @throws VendedorNoExisteException
	 */
	public Producto CrearProducto(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado, String vendedorActual) throws VendedorNoExisteException {

		//verificar = verificarProductoExistente(codigo);
		Vendedor vendedor = verificarVendedor(vendedorActual);
		Producto producto = new Producto();

		producto.setNombre(nombre);
		producto.setCodigo(codigo);
		producto.setImagen(imagen);
		producto.setCategoria(categoria);
		producto.setPrecio(precio);
		producto.setEstado(estado);
		ArrayList<Producto>listProductos = vendedor.getListaProductos();
		listProductos.add(producto);
		vendedor.setListaProductos(listProductos);
		return producto;

	}

	/**
	 * Permite actualizar el productos
	 * @param nombre
	 * @param codigo
	 * @param imagen
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @param VendedorActual
	 * @return
	 * @throws VendedorNoExisteException
	 */
	/*public boolean actualizarProducto(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado, String VendedorActual) throws VendedorNoExisteException{

		Producto producto = null;
		producto = new Producto();

		Vendedor vendedor = verificarVendedor(VendedorActual);
		boolean productoActualizado = false;

		producto.setNombre(nombre);
		producto.setCodigo(codigo);
		producto.setImagen(imagen);
		producto.setCategoria(categoria);
		producto.setPrecio(precio);
		producto.setEstado(estado);
		ArrayList<Producto>listProductos = vendedor.getListaProductos();
		for(Producto producto1  : listProductos){
			if(producto1.getNombre().equalsIgnoreCase(nombre)){
				vendedor.getListaProductos().remove(producto1);
				vendedor.getListaProductos().add(producto);
				producto1 = producto;
				vendedor.setListaProductos(vendedor.getListaProductos());
				productoActualizado  = true;
			}
		}
		return productoActualizado ;
	}*/

	/**
	 * Permite eliminar un productos
	 * @param codigo
	 * @param vendedorActual
	 * @return
	 * @throws ProductoNoExisteException 
	 * @throws VendedorNoExisteException
	 */
	/*
	public boolean eliminarProducto(String codigo, String vendedorActual) throws VendedorNoExisteException {

		Producto producto1 = null;
		producto1 = new Producto();
		boolean productoEliminado = false;
		Vendedor vendedor = buscarVendedor(vendedorActual);

		for (Producto producto : vendedor.getListaProductos()) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				vendedor.getListaProductos().remove(producto);
				productoEliminado = true;
			}
		}
		return productoEliminado;

	}*/

	/*public Producto eliminarProducto(String codigo, String idVendedor) throws ProductoNoExisteException, VendedorNoExisteException{

		Vendedor vendedor = verificarVendedor(idVendedor);

		for (Producto producto : vendedor.getListaProductos()) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				vendedor.getListaProductos().remove(producto);
				return producto ;
			}
		}
		throw new ProductoNoExisteException("El producto no se encuentra en la lista");
	}/

	/**
	 * 
	 * @param codigo
	 * @param idVendedor
	 * @return
	 * @throws ProductoNoExisteException
	 */
	public Producto obtenerProductos(String codigo, Vendedor vendedor) throws ProductoNoExisteException{

		Producto producto = null;
		int i;
		for(i =0;i<vendedor.getListaProductos().size();i++){

			producto = vendedor.getListaProductos().get(i);
			if(producto.getCodigo().equals(codigo)){
				return producto;
			}

		}
		throw new ProductoNoExisteException(" El producto no existe  ");
	}


	public Producto obtenerProducto(String codigo) throws ProductoNoExisteException, VendedorNoExisteException{


		int i;
		Producto producto = null;
		;
		for(i = 0; i < 	getListaVendedores().get(i).listaProductos.size(); i++){
			if(getListaVendedores().get(i).listaProductos.get(i).getCodigo().equals(codigo)){
				producto = listaVendedores.get(i).listaProductos.get(i);
			}
			return producto;
		}



		throw new ProductoNoExisteException(" El producto no existe  ");
	}


	public Producto buscarProducto(String codigo) throws VendedorNoExisteException, ProductoExisteException{

		int j = 0;
		int i;	

		String cedula = getListaVendedores().get(j).getCedula();
		Vendedor vendedor = verificarVendedor(cedula);

		Producto producto = null;

		for(i = 0; i < listaVendedores.size(); i++){//vendedor.getListaProductos().size()
			if(producto.getCodigo().equals(codigo)){

				return producto = vendedor.getListaProductos().get(i);

			}else{
				throw new ProductoExisteException("El producto noe existe");
			}
		}


		return producto;
	}
	/**
	 * Permite conocer si el producto se encuentra o no
	 * @param codigo
	 * @param idVendedor
	 * @return
	 */
	public boolean buscarProducto(String codigo, Vendedor idVendedor){

		Producto producto = null;

		for(int i = 0;i<idVendedor.getListaProductos().size();i++){
			producto = idVendedor.getListaProductos().get(i);
			if(producto.getCodigo().equals(codigo));
			return true;
		}
		return false;
	}



	/**
	 * Permite eliminar el producto elegido
	 * @param codigo
	 * @return boolean
	 * @throws ProductoNoExisteException
	 * @throws VendedorNoExisteException 
	 */
	/*public boolean eliminarProducto(String codigo) throws VendedorNoExisteException, ProductoNoExisteException {

			boolean borrado = false;
			int i;
			int j = 0;
             Vendedor vendedor = listaVendedores.get(j);

			for(Producto producto : vendedor.getListaProductos()){
				if(producto.getCodigo().equalsIgnoreCase(codigo)){
					vendedor.getListaProductos().remove(producto);
					break;
				}
				throw new ProductoNoExisteException("El producto no se encuentra en la lista");
			}
			return borrado;
		}*/

	/*Vendedor vendedor = verificarVendedor(idVendedor);

		for (Producto producto : vendedor.getListaProductos()) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				vendedor.getListaProductos().remove(producto);
				return producto ;
			}
		}
		throw new ProductoNoExisteException("El producto no se encuentra en la lista");*/




	//--------------------------------
	//--------------Mensajes-----------
	//---------------------------------

	
	
	/**
	 * Crear un chat donde un vendedor le pueda enviar algún mensaje a otro vendedor
     * de los contactos que tiene
	 */
	
	
	
	
	
	
	
	
	
	

	//---------------------------------


	/*public boolean confirmar(String usuario, String contrasenia){


		for(int i= 0; i<listaVendedores.size() ;i++){
			if(listaVendedores.get(i).getUsuario().equalsIgnoreCase(usuario) && listaVendedores.get(i).getPassword().equals(contrasenia) ){
				break;
			}
		}

		return true;

	}
	 */



	/**
	 * Este Método permite encontrar un vendedor de acuerdo a su usuario
	 * @param usuario
	 * @return
	 * @throws VendedorNoExisteException 
	 */
	/*public Vendedor encontrarVendedor(String usuario) throws VendedorNoExisteException {

		Vendedor gan = null;
		int i;

		for (i = 0; i < listaVendedores.size() ; i++) {
			if (listaVendedores.get(i).getUsuario().equals(usuario)) //.getCedula().equals(c))
			{
				gan = listaVendedores.get(i);//retorna los datos de cada posicion, pero se debe es mostrar la edad

			}

			return gan;
		}
		return gan;
	}*/


	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws VendedorNoExisteException
	 */
	public Vendedor encontrarAmigo(String usuario) throws VendedorNoExisteException{

		Vendedor vendedorEncontrado = null;
		for(Vendedor vendedor : listaVendedores){

			if(vendedor.getUsuario().equals(usuario)){
				vendedorEncontrado = vendedor;
				break;
			}
			//mensajeException
		}
		return vendedorEncontrado;
	}






	//------------------------------------------------------------------------------------------
	//--------------------------------------AMIGOS----------------------------------------------

	/**
	 * Este Método permite agregar
	 * @param usuario
	 * @return
	 * @throws VendedorNoExisteException 
	 */
	public Vendedor agregarAmigo(String usuario) throws VendedorNoExisteException{


		Vendedor vendedor = verificarVendedor(usuario);

		if(verificarVendedor(usuario)!=null ){

			vendedor.listaVendedoresAliadosAmigo.add(vendedor);

		}
		return vendedor ;


	}









	//------------------------------------------------------------------------------------------






	//--------------------------------------------------------------------------------------------------
	//-----------------LIKE & COMENTARIOS---------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------

	/**
	 * Permite crear un comentario
	 * @param texto
	 * @param hora
	 * @param fecha
	 * @param vendedorComentarista
	 * @param productoComentado
	 * @return
	 */
	public boolean agregarComentario(String texto, LocalDate fecha, LocalTime hora, Vendedor vendedorComentarista, Producto productoComentado){

		Comentario comentarioProducto = new Comentario();
		//Producto productoComentado = new Producto();

		comentarioProducto.setTexto(texto);
		comentarioProducto.setFecha(fecha);
		comentarioProducto.setHora(hora);
		comentarioProducto.setVendedorComentarista(vendedorComentarista);
		comentarioProducto.setProductoComentado(productoComentado);

		//productoComentado.getListaComentarios().add(comentarioProducto);
		vendedorComentarista.getListaComentarios().add(comentarioProducto);

		//vendedorComentarista.listaComentarios.add(comentarioProducto);

		return true;
	}

	/*public boolean agregarMeGusta(Vendedor vendedores,  LocalDate fecha, LocalTime hora, Producto producto){

		MeGusta meGusta = new MeGusta();
		
		
		
		meGusta.setFecha(fecha);
		meGusta.setHora(hora);
		meGusta.setProductoMeGusta(producto);
		vendedores.getListaMeGustasV().add(meGusta);
		
		
		return true;

	}*/
	
     public boolean  crearMeGusta(Producto prodMeGusta, LocalDate fecha, LocalTime hora, char like, Vendedor venMeGusta) throws ProductoNoExisteException, VendedorNoExisteException, ProductoExisteException {
    	 
    	 
    	 
    	 boolean bandera = false;
    	 MeGusta productoMeGusta = new MeGusta();
    	 MeGusta gusta = new MeGusta();
    	 
    	 if(prodMeGusta!=null){
    		 
    		 productoMeGusta.setProductoMeGusta( prodMeGusta);//producto
    		 productoMeGusta.setFecha(fecha);//fecha
    		 productoMeGusta.setHora(hora);//hora
    		 productoMeGusta.setLike(like);//**
    		 productoMeGusta.setListaVendedorMegusta(listaVendedores);//revision
    		 prodMeGusta.getListaMeGustas().add(productoMeGusta);
    		 venMeGusta.getListaMeGustasV().add(productoMeGusta);
    		 gusta.getListaVendedorMegusta().add(venMeGusta);
    		  
    		 
    		 return bandera = true;
    	 }
    	 
    	 
    	 
    	 return bandera;
    	 
    	 
    	 
     }



	
	
	
	


	/**
	 * 
	 * @param texto
	 * @param fechaHora
	 * @param vendedorComentarista
	 * @param productoComentado
	 */
	public void crearComentario(String texto, LocalDate fecha, LocalTime hora, Vendedor vendedorComentarista, Producto productoComentado){

		Comentario comentarioProducto = new Comentario();
		comentarioProducto.setTexto(texto);
		comentarioProducto.setFecha(fecha);
		comentarioProducto.setHora(hora);
		comentarioProducto.setVendedorComentarista(vendedorComentarista);
		comentarioProducto.setProductoComentado(productoComentado);
		productoComentado.getListaComentarios().add(comentarioProducto);
		
		//Ya
		vendedorComentarista.getListaComentarios().add(comentarioProducto);
		
	}



	/*public void agregarComentario(String comentario, Producto productoSeleccionado, Vendedor vendedorActual) {
		Comentarios comentario1 = new Comentarios();
		comentario1.setComentario(comentario);
		comentario1.setProductoComentado(productoSeleccionado);
		comentario1.setVendedorEnviado(vendedorActual);
		comentario1.setApellido(vendedorActual.getApellido());
		comentario1.setNombre(vendedorActual.getNombre());
		productoSeleccionado.getListaComentarios().add(comentario1);
	}
	 */


	//--------------------------------------------





	// Generar Set & Get

	/**
	 * Metodo accesor
	 * 
	 * @return 
	 */
	public String getNomMarket() {
		return nomMarket;
	}

	/**
	 * Metodo modificador
	 * @param nomMarket
	 */
	public void setNomMarket(String nomMarket) {
		this.nomMarket = nomMarket;
	}

	/**
	 * Metodo accesor
	 * @param 
	 * 
	 * @return 
	 */
	public Administrador getAdministrador() {
		return administrador;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param 
	 */
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}


	/**
	 * Metodo modificador
	 * @param listaVendedores
	 */
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}





	/**
	 * Permite verificar si los datos ingresados son del ADMINISTRADOR
	 * @param usuario
	 * @param contrasenia
	 * @return
	 */
	public boolean verificarUsuario(String usuario, String contrasenia) {

		if(administrador.getUsuario().equals(usuario) && administrador.getPassword().equals(contrasenia)){
			return true;
		}
		return false;
	}

     
	/**
	 * se verifica el usuario para mostrar el perfil de cada uno de ellos
	 * @param contrasenia
	 * @return
	 */
	public boolean mostrarPerfil(String contrasenia){
		
		int i;
		for( i= 0; i<listaVendedores.size() ;i++){
			
			if(listaVendedores.get(i).getPassword().equalsIgnoreCase(contrasenia)){
				break;
			}
		}
		
		return true;
	}
	
	
	

	/**
	 * permite confirmar los datos de login del vendedor
	 * @param usuario
	 * @param contrasenia
	 * @return boolean
	 */
	public boolean confirmar(String usuario, String contrasenia){


		for(int i= 0; i<listaVendedores.size() ;i++){
			if(listaVendedores.get(i).getUsuario().equalsIgnoreCase(usuario) && listaVendedores.get(i).getPassword().equals(contrasenia) ){
				break;
			}
		}

		return true;

	}

       







}
