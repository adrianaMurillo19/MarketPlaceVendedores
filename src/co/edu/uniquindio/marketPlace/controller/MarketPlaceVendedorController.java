package co.edu.uniquindio.marketPlace.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import co.edu.uniquindio.marketPlace.Aplicacion;
import co.edu.uniquindio.marketPlace.exception.ProductoExisteException;
import co.edu.uniquindio.marketPlace.exception.ProductoNoExisteException;
import co.edu.uniquindio.marketPlace.exception.VendedorNoExisteException;
import co.edu.uniquindio.marketPlace.model.Estado;
import co.edu.uniquindio.marketPlace.model.MarketPlace;
import co.edu.uniquindio.marketPlace.model.Producto;
import co.edu.uniquindio.marketPlace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Proyecto Final MarketPlace 
 * 
 * @author Adriana Lucia Murillo
 * 
 *
 */



public class MarketPlaceVendedorController implements Initializable{



	@FXML
	private Pane panelProducto;

	@FXML
	private Button btnLikeComentario;//Nuevo

	@FXML
	private TextField txtMensaje;//Nuevo

	@FXML
	private Button btnMensaje;//Nuevo
	
	@FXML
    private TextField txtBuscarVendedor;//Nuevo

	@FXML
    private Button btnBuscarVendedor;//Nuevo

	@FXML
	private Button btnImagen;

	@FXML
	private ImageView imageView;

	@FXML
	private Label labelTiempoActualPro;

	@FXML
	private Button btnEliminarPro;

	@FXML
	private TextField txtPrecio;

	@FXML
	private ComboBox comBoxEstado;

	@FXML
	private TableColumn<Producto, String> columnaFecha;

	@FXML
	private Button btnAgregarPro;

	@FXML
	private Label labelHoraPro;

	@FXML
	private TableView<Producto> tableVProducto;

	@FXML
	private AnchorPane anchoPane;

	@FXML
	private Label labelNomProducto;

	@FXML
	private TextField txtCategoria;

	@FXML
	private TableColumn<Producto, String> columnaNomPro;

	@FXML
	private TextField txtNomProducto;

	@FXML
	private Label labelPrecio;

	@FXML
	private TableColumn<Producto, String> columnaCategoria;

	@FXML
	private TableColumn<Producto, Double> columnaPrecio;

	@FXML
	private Label labelCategoria;

	@FXML
	private TextField txtCodigo;

	@FXML
	private Button btnNuevoPro;

	@FXML
	private Button btnActualizarPro;

	@FXML
	private Label labelCodigo;

	@FXML
	private ImageView imagenViewPro;

	@FXML
	private Label labelProducto;

	@FXML
	private TableColumn<Producto, Estado> columnaEstado;

	@FXML
	private Button btnCerrarSesionPro;

	@FXML
	private Label labelFechaPro;

	private Producto productoSeleccionado;

	Aplicacion aplicacion;
	ModelFactoryController modelFactoryController;
	CrudProductoVendedorController crudProductoVendedorController;

	//es la lista de PRODUCTOS que vamos a mostrar en la interfaz
	ObservableList<Producto>listadoProductos = FXCollections.observableArrayList();

	//
	ObservableList<Enum> listaTipoEstado = FXCollections.observableArrayList(Estado.values());



	@Override
	public void initialize(java.net.URL location, ResourceBundle resources){

		modelFactoryController = ModelFactoryController.getInstance();
		crudProductoVendedorController = new CrudProductoVendedorController(modelFactoryController); //se crea Instancia del crud de Productos



		//Visualizar la tabla de los Productos
		inicializarProductoView();

		tableVProducto.setItems(listadoProductos);
		tableVProducto.getItems().clear();

		mostrarFechaHora();

		comBoxEstado.setItems(listaTipoEstado);

		//para agregar imagen de los productos
		seleccionarImagen();



	}


	public void inicializarProductoView(){



		this.columnaNomPro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		this.columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		//
		tableVProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
			if(newSelection != null){
				productoSeleccionado = newSelection;
				mostrarInfoProducto();
			}
		});



	}


	/*------------------------------------------------------
	 * ----------------EVENTOS PRODUCTOS--------------------
	 * -----------------------------------------------------
	 * */


	@FXML
	void agregarImagenAction(ActionEvent event) {

		seleccionarImagen();
	}

	@FXML
	void nuevoProductoAction(ActionEvent event) {

		txtNomProducto.setText("");
		txtCodigo.setText("");
		txtCategoria.setText("");
		txtPrecio.setText("");
		comBoxEstado.setItems(listaTipoEstado);
	}

	@FXML
	void agregarProductoAction(ActionEvent event) throws ProductoExisteException, VendedorNoExisteException {

		agregarProducto();

	}

	@FXML
	void actualizarProductoAction(ActionEvent event) {

		//actulizarProducto();
	}

	@FXML
	void eliminarProAction(ActionEvent event) throws ProductoNoExisteException {

		//eliminarProducto();
	}


	@FXML
	void cerrarSesionAction(ActionEvent event) {
		cerrarSesion();
	}

	@FXML
    void buscarVendedorAction(ActionEvent event) {

    }

    @FXML
    void enviarMensajeAction(ActionEvent event) {

    }

    @FXML
    void irVentanaLikeComentarioAction(ActionEvent event) {

    }





	//---------------------------------------------------------
	//------------------------ CRUD PRODUCTOS------------------
	//---------------------------------------------------------


	/**
	 * Permite mostrar la informacion de los productos
	 */
	public void mostrarInfoProducto(){

		if(productoSeleccionado != null){
			txtNomProducto.setText(productoSeleccionado.getNombre());
			txtCodigo.setText(productoSeleccionado.getCodigo());
			imagenViewPro.setImage(productoSeleccionado.getImagen());
			txtCategoria.setText(productoSeleccionado.getCategoria());;
			productoSeleccionado.getPrecio();
			comBoxEstado.setItems(listaTipoEstado);	
			columnaFecha.setText(productoSeleccionado.getCodigo());

		}
	}


	/**
	 * Permite seleccionar una imagen desde nuestros archivos del computador
	 */
	public void seleccionarImagen(){

		btnImagen.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Buscar Imagen");

			// Agregar filtros para facilitar la busqueda
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("All Images", "*.*"),
					new FileChooser.ExtensionFilter("JPG", "*.jpg"),
					new FileChooser.ExtensionFilter("PNG", "*.png")
					);

			// Obtener la imagen seleccionada
			File imgFile = fileChooser.showOpenDialog(null);

			// Mostar la imagen
			if (imgFile != null) {
				Image image = new Image("file:" + imgFile.getAbsolutePath());

				imagenViewPro.setImage(image);
			}
		});

	}


	/**
	 * 
	 * @throws ProductoExisteException
	 * @throws VendedorNoExisteException 
	 */
	public void agregarProducto() throws VendedorNoExisteException{

		String nombre = txtNomProducto.getText();
		String codigo = txtCodigo.getText();
		Image imagen  = imagenViewPro.getImage();
		String categoria = txtCategoria.getText();
		double precio = Double.parseDouble(txtPrecio.getText());
		Estado estado = (Estado) comBoxEstado.getValue();

		//if(datosValidosProductos(nombre, codigo, imagen, categoria, precio, estado)){
		//crearProducto(nombre, codigo, imagen, categoria, precio, estado);

		//Producto producto = null;
		Producto producto = modelFactoryController.crearProducto(nombre, codigo, imagen, categoria, precio, estado);

		if(producto != null){
			listadoProductos.add(producto);
			mostrarMensaje("Notificación vendedor", "Producto no registrado", "El producto no pudo ser registrado", AlertType.INFORMATION);
		}else{
			mostrarMensaje("Notificacion Producto", "Producto no registrado", "El Producto no fue registrado",
					AlertType.ERROR);
		}

		//}
	}


	/*
	public void crearProducto(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado) throws ProductoExisteException, VendedorNoExisteException{

		Producto productoAgregado = null;
		 productoAgregado = crudProductoVendedorController.CrearProducto(nombre, codigo, imagen, categoria, precio, estado);
		if(productoAgregado != null){
			listadoProductos.add(productoAgregado);
			mostrarMensaje("Notificación vendedor", "Producto registrado", "El producto fue registrado", AlertType.INFORMATION);
		}
		else{
			mostrarMensaje("Notificación vendedor", "Producto no registrado", "El producto no pudo ser registrado", AlertType.ERROR);
		}
	}*/



	public void eliminarProducto() throws ProductoNoExisteException{

		if(productoSeleccionado != null){
			if(crudProductoVendedorController.eliminarProducto()){
				mostrarMensaje("Notificación", "Producto seleccionado", "Producto no ha sido eliminado", AlertType.CONFIRMATION);
			}else{
				mostrarMensaje("Notificación", "Producto seleccionado", "Producto ha sido eliminado", AlertType.INFORMATION);
			}
			listadoProductos.remove(productoSeleccionado);
		}	
	}


	public void actulizarProducto(){

		String nombreNuevo = txtNomProducto.getText();
		String codigoNuevo = txtCodigo.getText();
		Image imagenNuevo  = imagenViewPro.getImage();
		String categoriaNuevo = txtCategoria.getText();
		double precioNuevo = Double.parseDouble(txtPrecio.getText());
		Estado estadoNuevo = (Estado) comBoxEstado.getValue();

		if(productoSeleccionado != null){
			crudProductoVendedorController.actualizarProducto(nombreNuevo, codigoNuevo, imagenNuevo, categoriaNuevo, precioNuevo, estadoNuevo);
			productoSeleccionado.setNombre(nombreNuevo);
			productoSeleccionado.setCodigo(codigoNuevo);
			productoSeleccionado.setCategoria(categoriaNuevo);
			productoSeleccionado.setPrecio(precioNuevo);
			productoSeleccionado.setEstado(estadoNuevo);

			mostrarMensaje("Producto Selección", "Producto Selección", "Fue actualizado", AlertType.INFORMATION);
			tableVProducto.refresh();
		}else{
			mostrarMensaje("Producto Selección", "Producto Selección", "No se ha selecioando ningún producto", AlertType.INFORMATION);
		}

	}




	/**
	 * 
	 * @param nombre
	 * @param codigo
	 * @param imagen2
	 * @param categoria
	 * @param precio
	 * @param estado
	 * @return
	 */
	public boolean datosValidosProductos(String nombre, String codigo, Image imagen, String categoria, double precio, Estado estado){

		String notificacion ="";

		if(nombre == null || nombre.equals("")){
			notificacion += "El nombre es invalido\n";
		}
		if(codigo == null || codigo.equals(" ")){
			notificacion += "El codigo es invalido\n";
		}
		if(imagen == null || imagen.equals("")){
			notificacion += "La imagen es invalida\n";
		}
		if(categoria == null || categoria.equals("")){
			notificacion += "La categoria es invalido\n";
		}
		if(precio == 0){
			notificacion += "El precio es invalido\n";
		}
		if(estado == null || estado.equals("")){
			notificacion += "La clave es invalido\n";
		}
		if(notificacion.equals("")){
			return true;
		}

		//notificar al usuario
		mostrarMensaje("Notificación Administrador", "Información del Producto inválida", "", AlertType.WARNING);

		return false;

	}


	private ObservableList<Producto> getListProductos(){
		//listadoProductos.addAll(crudProductoVendedorController.obtenerProductos());
		listadoProductos.addAll(modelFactoryController.getVendedorActual().getListaProductos());

		return listadoProductos;
	}






	/**
	 *Este metodo muestra un mensaja al usuario
	 *@param titulo
	 *@param header
	 *@param contenido
	 *@param AlertType
	 * */
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){


		Alert alert = new Alert (alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}



	/*
	 * Este metodo permite cerrar la ventana actual
	 * */
	public void cerrarSesion(){

		Stage stage = (Stage) this.btnCerrarSesionPro.getScene().getWindow();
		stage.close();;
		stage.setResizable(false);
	}


	/**
	 * Permite mostrar la fecha y hora actual
	 * 
	 */
	private void mostrarFechaHora(){

		String timeStamp = new SimpleDateFormat("yyyy/MM/dd \nHH:mm:ss").format(Calendar.getInstance().getTime());
		labelTiempoActualPro.setText(timeStamp);
	}






	public void setAplicacion ( Aplicacion aplicacion )
	{
		this.aplicacion = aplicacion;
		//this.marketPlace = aplicacion.getMarketPlace();

		//Vendedor es el que tiene el listado de Productos
		//tableVProducto.setItems(getProductos());
		//tableVProducto.getItems().clear();
	}


}
