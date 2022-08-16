package co.edu.uniquindio.marketPlace.controller;

import java.awt.HeadlessException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.marketPlace.Aplicacion;
import co.edu.uniquindio.marketPlace.exception.VendedorNoExisteException;
import co.edu.uniquindio.marketPlace.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerVendedorUnoView implements Initializable{

	@FXML
	private ComboBox<Vendedor> comboBoxContactos;

	@FXML
	private Button btnInteraccion;

	@FXML
	private TableColumn<Vendedor, String> columnaApeContacto;

	@FXML
	private Button btnBuscarContacto;

	@FXML
	private Button btnEnviarMensaje;

	@FXML
	private TextField txtMostrarMensajes;

	@FXML
	private TextField txtBuscarContacto;

	@FXML
	private Button btnMostarMensajes;

	@FXML
	private TableView<Vendedor> TableViewContacto;

	@FXML
	private TextArea txtEscribirMensaje;

	@FXML
	private TableColumn<Vendedor, String> columnaNomContacto;


	@FXML
	private Button btnEnviarSolicitud;

	@FXML
	private Button btnAceptarSolicitud;

	/*Date objDate = new Date(); esto se debe agregar al mensaje*/

	ModelFactoryController modelFactoryController;
	Aplicacion aplicacion;
	ObservableList<Vendedor>listadoAmigos = FXCollections.observableArrayList();
	Vendedor vendedorSeleccionado;






	//-----------------EVENTOS--------------------
	@FXML
	void buscarContactoAction(ActionEvent event) throws VendedorNoExisteException {

		agregarAmigos();
	}



	/**
	 * @throws VendedorNoExisteException 
	 * @throws HeadlessException 
	 * 
	 */
	public void agregarAmigos() throws  VendedorNoExisteException{


		boolean bandera = false;
		Vendedor vendedor = null;
		//String usuario = JOptionPane.showInputDialog("Ingrese cedula: ");

		String usuario = txtBuscarContacto.getText();

		if(modelFactoryController.getMarketPlace().verificarVendedor(usuario) != null){


			vendedor = modelFactoryController.getMarketPlace().agregarAmigo(usuario);//debo crear el metodo desde Singleton

			if(vendedor != null){
				modelFactoryController.getMarketPlace().verificarVendedor(usuario).getListaVendedoresAliadosAmigo().add(vendedor);
				listadoAmigos.add(vendedor);
				mostrarMensaje("Notifación vendedor", "", "contacto encontrado "+listadoAmigos, AlertType.INFORMATION);
			
			}else{
				mostrarMensaje("Notificación vendedor", "No se encontró", "Contacto no encontrado", AlertType.ERROR);
			}
		}
	}



	public void inicializarAmigosView(){

		this.columnaNomContacto.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.columnaApeContacto.setCellValueFactory(new PropertyValueFactory<>("Apellido"));


		TableViewContacto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{

			vendedorSeleccionado = newSelection;
			mostrarInfoVendedor();//antes no tenia parametro


		});




	}



	private void mostrarInfoVendedor() {

		if(vendedorSeleccionado != null){

			vendedorSeleccionado.getNombre();
			vendedorSeleccionado.getApellido();
			txtBuscarContacto.setText(vendedorSeleccionado.getCedula());
		}

	}



	@FXML
	void mostrarMensajesAction(ActionEvent event) {

	}

	@FXML
	void irVentanaInteraccion(ActionEvent event) {

		abrirVentanaLikeComentario();
	}




	public void abrirVentanaLikeComentario(){

		aplicacion.mostrarVentanaLikeComentario();
	}
	//--------------------------------------------

	@FXML
	void enviarSolicitudAction(ActionEvent event) {

	}

	@FXML
	void aceptarSolicitudAction(ActionEvent event) {

	}


	public void enviarSolicitud() throws Exception{

		Vendedor vendedor = null;

		LoginController loginController = new LoginController();

		loginController.iniciarSesion();
		vendedor.sugerirContacto();
	}



	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableViewContacto.setItems(listadoAmigos);
		inicializarAmigosView();
		modelFactoryController = ModelFactoryController.getInstance();

		comboBoxContactos.setItems(listadoAmigos);



	}


}
