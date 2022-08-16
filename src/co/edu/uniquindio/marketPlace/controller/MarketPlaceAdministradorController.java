package co.edu.uniquindio.marketPlace.controller;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version 1
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

import co.edu.uniquindio.marketPlace.Aplicacion;
import co.edu.uniquindio.marketPlace.exception.*;
import co.edu.uniquindio.marketPlace.model.*;//comboTipoCurso.setItems(listaTipoCurso);
import co.edu.uniquindio.marketPlace.persistence.Persistencia;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MarketPlaceAdministradorController implements Initializable{


	@FXML 
	private ResourceBundle resources;

	@FXML
	private Button btnAgregarV;

	@FXML
	private Button btnImagen;

	@FXML
	private TextField txtApellidoV;

	@FXML
	private TextField txtUsuarioV;


	@FXML
	private Label labelHoraPro;

	@FXML
	private Label labelCedulaV;

	@FXML
	private AnchorPane anchoPane;

	@FXML
	private Label txtTituloV;

	@FXML
	private Button btnNuevoV;

	@FXML
	private Pane panelAdmin;

	@FXML
	private TableColumn<Vendedor, String> columnaCedula;

	@FXML
	private Label labelUsuarioV;


	@FXML
	private TextField txtCedulaV;

	@FXML
	private TextField txtClaveV;

	@FXML
	private ImageView Imagen;

	@FXML
	private Label labelDireccionV;

	@FXML
	private Label labelApellidoV;

	@FXML
	private TableColumn<Vendedor, String> columnaUsuario;

	@FXML
	private TextField txtNombreV;

	@FXML
	private TableColumn<Vendedor, String> columnaNombre;

	@FXML
	private Label labelFechaPro;

	@FXML
	private TextField txtDireccionV;

	@FXML 
	private TableView<Vendedor> tableV; 

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private TableColumn<Vendedor, String> columnaContrasenia;

	@FXML
	private Button btnEliminarV;

	@FXML
	private Label labelFechaActualPro;

	@FXML
	private Button btnActualizar;

	@FXML
	private TextField txtCategoria;

	@FXML
	private Label labelNombreV;

	@FXML
	private Label labelClaveV;

	@FXML
	private Button btnNuevoPro;


	Aplicacion aplicacion;
	ModelFactoryController modelFactoryController;
	CrudVendedorController crudVendedorController;
	//MarketPlace marketPlace;
	Vendedor vendedorSeleccionado;
	//es la lista de vendedores que vamos a mostrar en la interfaz
	ObservableList<Vendedor>listadoVendedores = FXCollections.observableArrayList();


	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// agregar eventos, inicializar comboBox, Tablas, cambiar colores,

		modelFactoryController = ModelFactoryController.getInstance();
		crudVendedorController = new CrudVendedorController(modelFactoryController);

		inicializarVendedorView();

		//tableV.getItems().clear(); //limpiar las casillas
        tableV.setItems(listadoVendedores);

		//muestra fecha y hora del sistema
		mostrarFechaHora();

		//para agregar imagen de los productos
		//seleccionarImagen();
	}
	

	
	//Revisar
	public  void inicializarVendedorView(){

		this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));//debe llamarse igual en el modelo
		this.columnaCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		this.columnaContrasenia.setCellValueFactory(new PropertyValueFactory<>("password"));


		//fila seleccionada en la tabla de Vendedores
		tableV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{

			vendedorSeleccionado = newSelection;
			mostrarInfoVendedor(vendedorSeleccionado);//antes no tenia parametro

		});



	}







	/**
	 * 
	 */
	private void mostrarInfoVendedor(Vendedor vendedorSeleccionado) {

		if(vendedorSeleccionado != null){
			txtNombreV.setText(vendedorSeleccionado.getNombre());
			txtApellidoV.setText(vendedorSeleccionado.getApellido());
			txtCedulaV.setText(vendedorSeleccionado.getCedula());
			txtDireccionV.setText(vendedorSeleccionado.getDireccion());
			txtUsuarioV.setText(vendedorSeleccionado.getUsuario());
			txtClaveV.setText(vendedorSeleccionado.getPassword());

			
		}
	}

	









	/**
	 * Permite mostrar la fecha y hora actual
	 * 
	 */
	private void mostrarFechaHora(){

		String timeStamp = new SimpleDateFormat("yyyy/MM/dd \nHH:mm:ss").format(Calendar.getInstance().getTime());
		labelFechaActualPro.setText(timeStamp);
		//columnaFecha.setText(timeStamp);
	}


	/*
	 * ----------------EVENTOS VENDEDORES--------------------
	 * */



	@FXML
	void cerrarSesionAction(ActionEvent event) {
		cerrarSesion();
	}

	@FXML
	void mostrarFechaHoraAction(ActionEvent event) {
		mostrarFechaHora();
	}





	@FXML
	void nuevoVendedorAction(ActionEvent event) {

		txtNombreV.setText("");
		txtApellidoV.setText("");
		txtCedulaV.setText(" ");
		txtDireccionV.setText(" ");
		txtUsuarioV.setText(" ");
		txtClaveV.setText(" ");
		txtCedulaV.setText("");
	}

	@FXML
	void agregarVendedorAction(ActionEvent event) throws VendedorExisteException, FileNotFoundException, InvocationTargetException, IllegalAccessException, IOException {

		agregarVendedor();
	}

	@FXML
	void actualizarVendedorAction(ActionEvent event) throws VendedorNoExisteException {

		actualizarVendedor();
	}

	@FXML
	void eliminarVendedorAction(ActionEvent event) throws VendedorNoExisteException {


		eliminarVendedor();
	}



	//-------------------------------------------------



	//---------------------------------------------------------------
	//------------------------CRUD VENDEDOR--------------------------
	//---------------------------------------------------------------


	public void agregarVendedor() throws FileNotFoundException, InvocationTargetException, IllegalAccessException, IOException {

		String  nombre  = txtNombreV.getText();
		String  apellido = txtApellidoV.getText();
		String  cedula = txtCedulaV.getText();
		String  direccion = txtDireccionV.getText();
		String  usuario = txtUsuarioV.getText();
		String  clave = txtClaveV.getText();

		if(datosValidos(nombre, apellido, cedula, direccion, usuario, clave)){
			try {
				crearVendedor(nombre, apellido, cedula, direccion, usuario, clave);
			} catch (VendedorExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	
	
	public void crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String clave) throws VendedorExisteException, FileNotFoundException, InvocationTargetException, IllegalAccessException, IOException {
     
         Vendedor vendedorAgregado = null;
		 vendedorAgregado = crudVendedorController.crearVendedor(nombre, apellido, cedula, direccion, usuario, clave); //crearVendedor(nombre, apellido, cedula, direccion, usuario, password);
		if( vendedorAgregado!=null){
			listadoVendedores.add(vendedorAgregado);//F1. No borrar ninguno
			Persistencia.guardarPrueba(listadoVendedores);//
			mostrarMensaje("Notificación Administrador", "Vendedor registrado", "El vendedor fue registrado", AlertType.INFORMATION);
			limpiarCamposVendedor();
			
		}else
		{
		mostrarMensaje("Notificación administrador", "Vendedor No creado", "por favor vuelva a revisar los datos ingresados", AlertType.ERROR);//throw new VendedorExisteException ("Ya existe el vendedor");
		}
		}

	

	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param cedula
	 * @param direccion
	 * @param usuario
	 * @param password
	 * @return boolean
	 */
	public boolean datosValidos(String nombre, String apellido, String cedula, String direccion, String usuario, String clave){

		String notificacion ="";

		if(nombre == null || nombre.equals("")){
			notificacion += "El nombre es invalido\n";
		}
		if(apellido == null || apellido.equals("")){
			notificacion += "El apellido es invalido\n";
		}
		if(cedula == null || cedula.equals("")){
			notificacion += "La cedula es invalida\n";
		}
		if(direccion == null || direccion.equals("")){
			notificacion += "La direccion es invalido\n";
		}
		if(usuario == null || usuario.equals("")){
			notificacion += "El usuario es invalido\n";
		}
		if(clave == null || clave.equals("")){
			notificacion += "La clave es invalido\n";
		}
		if(notificacion.equals("")){
			return true;

		}

		//notificar al usuario

		mostrarMensaje("Notificación Administrador", "Información del vendedor inválida", "", AlertType.WARNING);

		return false;
	}

	/**
	 * Permite eliminar el vendedor que se haya seleccionado
	 * @throws VendedorNoExisteException
	 */
	/**
	 * Permite eliminar el vendedor que se haya seleccionado
	 * @throws VendedorNoExisteException
	 */
	public void eliminarVendedor() throws VendedorNoExisteException{

		
		boolean vendedorEliminado = false;
		
		if(vendedorSeleccionado != null){

			if(mostrarMensajeConfirmacion("¿Esta seguro de eliminar ese vendedor?")==true){
				vendedorEliminado = crudVendedorController.eliminarVendedor(vendedorSeleccionado.getCedula());
				
				if(vendedorEliminado == true){
					listadoVendedores.remove(vendedorSeleccionado);
					vendedorSeleccionado = null;
					
					tableV.getSelectionModel().clearSelection();
					limpiarCamposVendedor();
					mostrarMensaje("Notificación administrador", "Vendedor eliminado", "", AlertType.INFORMATION);
				}else{
					mostrarMensaje("Notificación administrador", "", "", AlertType.ERROR);
			}
				}
		} else{
			mostrarMensaje("Notificación administrador", "", "", AlertType.WARNING);
		}
	}



	public void actualizarVendedor() throws VendedorNoExisteException {

		String  nombre  = txtNombreV.getText();
		String  apellido = txtApellidoV.getText();
		String cedula = txtCedulaV.getText();
		String direccion = txtDireccionV.getText();
		String usuario = txtUsuarioV.getText();
		String clave = txtClaveV.getText();
       boolean vendedorActualizado = false;
		

		if(vendedorSeleccionado != null){
          
			if(datosValidos(nombre, apellido, cedula, direccion, usuario, clave)==true){
				
				vendedorActualizado = crudVendedorController.actualizarVendedor(vendedorSeleccionado.getCedula(),nombre, apellido, cedula, direccion, usuario, clave);
			
				if(vendedorActualizado == true){
					tableV.refresh();
					mostrarMensaje("Notificación administrador", "", "", AlertType.INFORMATION);
				limpiarCamposVendedor();
				}else{
					
					mostrarMensaje("Notificación administrador", "", "", AlertType.INFORMATION);
				}
				}else{
					mostrarMensaje("Notificación administrador", "", "", AlertType.ERROR);
				}
			}
		}




	//------------------------------------------------------------

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


	//Metodo

	/**
	 * lista de los vendedores
	 * @return ObservableLis
	 */
	private ObservableList<Vendedor> getVendedores() {

		//para manipular la lista de vendedores
		listadoVendedores.addAll(crudVendedorController.obtenerVendedores());
		return listadoVendedores;
	}


	public void setAplicacion(Aplicacion aplicacion) {

		this.aplicacion = aplicacion;
	
	}

	
	
	
	
	
	
	/*
	 * Este metodo permite cerrar la ventana actual
	 * */
	public void cerrarSesion(){


		Stage stage = (Stage) this.btnCerrarSesion.getScene().getWindow();
		stage.close();;
		stage.setResizable(false);

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

	private void limpiarCamposVendedor(){

		txtNombreV.setText("");
		txtApellidoV.setText("");
		txtCedulaV.setText("");
		txtDireccionV.setText("");
		txtUsuarioV.setText("");
		txtClaveV.setText("");
	}

}
