package co.edu.uniquindio.marketPlace.controller;

import java.awt.HeadlessException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class ControllerVendedorSieteView implements Initializable{

	@FXML
    private ComboBox<Vendedor> comboBoxContactos;

    @FXML
    private Button btnInteraccion;

    @FXML
    private TableColumn<Vendedor, String> columnaApeContacto;

    @FXML
    private TextArea txtAreaMostrarMensajes;

    @FXML
    private Button btnBuscarContacto;

    @FXML
    private Button btnEnviarMensaje;

    @FXML
    private TextField txtBuscarContacto;

    @FXML
    private Button btnMostarMensajes;

    @FXML
    private TableView<Vendedor> TableViewContacto;

    @FXML
    private TextField txtEnviarMensajes;

   

    @FXML
    private TableColumn<Vendedor, String> columnaNomContacto;

    /*Date objDate = new Date(); esto se debe agregar al mensaje*/
    
    ModelFactoryController modelFactoryController;
    Aplicacion aplicacion;
    ObservableList<Vendedor>listadoAmigos = FXCollections.observableArrayList();
    Vendedor vendedorSeleccionado;
    
    
    public Aplicacion getAplicacion() {
		return aplicacion;
	}


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}


	public void buscarAmigos() throws HeadlessException, VendedorNoExisteException{
		
		boolean bandera;
		
		Vendedor vendedor = null;
		
		String usuario = txtBuscarContacto.getText();
		
		
		if(modelFactoryController.getMarketPlace().verificarVendedor(usuario)!=null){
			
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
	
	
	public void abrirVentanaLikeComentario(){

		aplicacion.mostrarVentanaLikeComentario();
	}
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		modelFactoryController = ModelFactoryController.getInstance();
	
		TableViewContacto.setItems(listadoAmigos);
	
	     comboBoxContactos.setItems(listadoAmigos);
	
	     inicializarAmigosView();
	}
    
    
    
    
    
    //------------------EVENTOS---------------------------------------
    @FXML
    void buscarContactoAction(ActionEvent event) throws HeadlessException, VendedorNoExisteException {
    	buscarAmigos();
    }

    @FXML
    void mostrarMensajesAction(ActionEvent event) {

    }

    @FXML
    void irVentanaInteraccion(ActionEvent event) {

    	abrirVentanaLikeComentario();
    }

   

	

	//-------------------------------------------------------------
	
    
    private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}
	
	

}
