package co.edu.uniquindio.marketPlace.controller;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.marketPlace.Aplicacion;
import co.edu.uniquindio.marketPlace.exception.ProductoExisteException;
import co.edu.uniquindio.marketPlace.exception.ProductoNoExisteException;
import co.edu.uniquindio.marketPlace.exception.VendedorNoExisteException;
import co.edu.uniquindio.marketPlace.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ControllerProducto implements Initializable {

	@FXML
	private ImageView imagenViewPro;

	@FXML
	private Button btnImagen;

	@FXML
	private Text textFechaHora;

	@FXML
	private Button btnEliminarProducto;

	@FXML
	private TableColumn<Producto, String> columnaHora;

	@FXML
	private TableColumn<Producto, String> columnaCodigo;

	@FXML
	private TableColumn<Producto, String> columnaNom;

	@FXML
	private TableColumn<Producto, Double> columnaPrecio;

	@FXML
	private TableColumn<Producto, Estado> columnaEstado;

	@FXML
	private TableView<Producto> tableViewProductos;

	@FXML
	private TableColumn<Producto, String> columnaFecha;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnNuevo;


	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtCategoria;

	@FXML
	private TextField txtCodigo;

	@FXML
	private Button btnAgregar;

	@FXML
	private ComboBox comBoxEstado;

	@FXML
	private TextField txtPrecio;

	@FXML
	private Button btnVendedorUno;


	@FXML
	void abrirVendedorUnoAction(ActionEvent event) {

		aplicacion.verificarPerfil();
	}


	ObservableList<Enum>listEstado = FXCollections.observableArrayList(Estado.values());
	ObservableList<Producto>listProductos = FXCollections.observableArrayList();

	ModelFactoryController modelFactoryController;
	Aplicacion aplicacion;
	Vendedor vendedorProducto;
	Producto productoSeleccionado;

	public Aplicacion getAplicacion() {
		return aplicacion;
	}


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}


	@FXML
	void agregarProductoAction(ActionEvent event) throws VendedorNoExisteException {

		agregarProducto();
	}


	@FXML
	void seleccionarImagenAction(ActionEvent event) {

		seleccionarImagen();
	}

	@FXML
	void eliminarProductoAction(ActionEvent event) throws VendedorNoExisteException, ProductoNoExisteException, ProductoExisteException {

		//eliminar() ;
		eliminarProducto();
	}

	@FXML
	void actualizarProductoAction(ActionEvent event) throws ProductoNoExisteException, VendedorNoExisteException {
 
		actualizarProducto();
	}

	@FXML
	void nuevoProductoAction(ActionEvent event) {

		txtNombre.setText("");
		txtCodigo.setText("");
		txtCategoria.setText("");
		txtPrecio.setText("");

	}


	/*/*public void mostrarInfoProducto(){

		if(productoSeleccionado != null){
			txtNomProducto.setText(productoSeleccionado.getNombre());
			txtCodigo.setText(productoSeleccionado.getCodigo());
			imagenViewPro.setImage(productoSeleccionado.getImagen());
			txtCategoria.setText(productoSeleccionado.getCategoria());;
			productoSeleccionado.getPrecio();
			comBoxEstado.setItems(listaTipoEstado);	
			columnaFecha.setText("");

		}
	}*/

	public void mostrarInfoProductos(){

		//String fechaHora = new SimpleDateFormat("yyyy/MM/dd \nHH:mm:ss").format(Calendar.getInstance().getTime());

		if(productoSeleccionado != null){

			txtNombre.setText(productoSeleccionado.getNombre());
			txtCodigo.setText(productoSeleccionado.getCodigo());
			imagenViewPro.setImage(productoSeleccionado.getImagen());
			txtCategoria.setText(productoSeleccionado.getCategoria());
			productoSeleccionado.getPrecio();
			comBoxEstado.setItems(listEstado);
			//textFechaHora.setInputMethodRequests(productoSeleccionado.getFechaHora());
			productoSeleccionado.getFecha();
			productoSeleccionado.getHora(); 
		}

	}


	//Estado estado = (Estado) comBoxEstado.getValue();
	/**
	 * Este metodo permite ir agregando los datos de los Productos
	 * @throws VendedorNoExisteException
	 */
	@SuppressWarnings("unchecked")
	public void agregarProducto() throws VendedorNoExisteException{



		boolean bandera;
		String documento;
		Producto producto = null;

		String nombre = txtNombre.getText();
		String codigo = txtCodigo.getText();
		Image imagen = imagenViewPro.getImage();
		String categoria = txtCategoria.getText();
		double precio = Double.parseDouble(txtPrecio.getText());
		Estado estado = (Estado) comBoxEstado.getValue();
		LocalDate fecha = LocalDate.now();//LocalDate fecha = LocalDate.parse("2022-05-25");
		LocalTime hora = LocalTime.now().withNano(0);
		//labelTiempoActualPro.setText(timeStamp);

		documento = JOptionPane.showInputDialog(null, "Ingrese numero de documento del vendedor: ");

		bandera = modelFactoryController.agregarProducto(documento, nombre, codigo, imagen, categoria, precio, estado, fecha, hora);

		if(documento != null && bandera != false){

			producto = new Producto();

			producto.setNombre(nombre);
			producto.setCodigo(codigo);
			producto.setImagen(imagen);
			producto.setCategoria(categoria);
			producto.setPrecio(precio);
			producto.setEstado(estado);
			producto.setFecha(fecha);//setFechaHora(fechaHora);
			producto.setHora(hora);

			//cedula.getListaProductos().add(producto);
			listProductos.add(producto);
			//vendedorProducto.setListaProductos((ArrayList<Producto>) listProductos);


			JOptionPane.showMessageDialog(null, modelFactoryController.getMarketPlace().verificarVendedor(documento).getNombre()+" "+" su producto: "+ producto.getNombre()+" "+", fue agregado ");
		}

		else{
			if(documento != null || bandera != false){
				JOptionPane.showMessageDialog(null, "error");
			}

		}

	}


	/**
	 * Este método permite que se elimine el producto que hayamos seleccionado
	 * @throws ProductoNoExisteException 
	 * @throws VendedorNoExisteException 
	 * @throws ProductoExisteException 
	 */
	public void eliminarProducto() throws VendedorNoExisteException, ProductoNoExisteException, ProductoExisteException{


		boolean producto = false;
		String codigo, cedula;

		codigo = JOptionPane.showInputDialog(null, "Ingrese el número de código: ");

		cedula = JOptionPane.showInputDialog(null, "Ingrese su número de cedula: ");

		try {
			if(modelFactoryController.getMarketPlace().obtenerProducto(codigo)!=null && modelFactoryController.getMarketPlace().verificarVendedor(cedula) != null){

				if(mostrarMensajeConfirmacion("¿Esta seguro de eliminar el Producto? ") == true){

					producto = modelFactoryController.eliminarProducto(codigo, cedula);

					if(producto == true){
						listProductos.remove(productoSeleccionado);
						tableViewProductos.getSelectionModel().clearSelection();
						mostrarMensaje("Notificación vendedor", "El Producto fue eliminado", " ", AlertType.INFORMATION );
					}else
						mostrarMensaje("Notificación vendedor", "El producto no se pudo eliminar", "", AlertType.INFORMATION);
				}
			}else{

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//producto=modelFactoryController.eliminarProducto(productoSeleccionado.getCodigo(), vendedorProducto.getCedula());
	}



	/**
	 * Este Método permite que 
	 * @throws VendedorNoExisteException 
	 * @throws ProductoNoExisteException 
	 */
	public void actualizarProducto() throws ProductoNoExisteException, VendedorNoExisteException{

		String nombreNuevo =  txtNombre.getText();    
		String codigoNuevo = txtCodigo.getText();
		Image imagenNuevo  = imagenViewPro.getImage();
		String categoriaNuevo = txtCategoria.getText();
		double precioNuevo = Double.parseDouble(txtPrecio.getText());
		Estado estadoNuevo = (Estado) comBoxEstado.getValue();

		if(productoSeleccionado != null){
			
			//aplicacion.actualizarProducto(nombreNuevo, codigoNuevo, imagenNuevo, categoriaNuevo, precioNuevo, estadoNuevo);
			modelFactoryController.actualizarProducto(nombreNuevo, codigoNuevo, imagenNuevo, categoriaNuevo, precioNuevo, estadoNuevo);
			productoSeleccionado.setNombre(nombreNuevo);
			productoSeleccionado.setCodigo(codigoNuevo);
			productoSeleccionado.setCategoria(categoriaNuevo);
			productoSeleccionado.setPrecio(precioNuevo);
			productoSeleccionado.setEstado(estadoNuevo);

			mostrarMensaje("Producto Selección", "Producto Selección", "Fue actualizado", AlertType.INFORMATION);
			tableViewProductos.refresh();
		}else{
			mostrarMensaje("Producto Selección", "Producto Selección", "No se ha selecioando ningún producto", AlertType.INFORMATION);
		}

	}

	/*public void actulizarProducto(){



	}*/



	//---------------------------------------------------------------------------------
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


	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub




		modelFactoryController = ModelFactoryController.getInstance();

		comBoxEstado.setItems(listEstado);

		tableViewProductos.setItems(listProductos);


		inicializarProductoView();
		//columnaFecha.setText(timeStamp);

	}


	public void inicializarProductoView(){



		//	this.columnaNomPro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnaNom.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
		this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
		this.columnaEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
		this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.columnaHora.setCellValueFactory(new PropertyValueFactory<>("Hora"));


		tableViewProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
			if(newSelection != null){
				productoSeleccionado = newSelection;
				mostrarInfoProductos();
			}
		});
	}



	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}









}