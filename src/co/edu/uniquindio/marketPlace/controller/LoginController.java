package co.edu.uniquindio.marketPlace.controller;

import java.io.IOException;

/**
 * Proyecto Final MarketPlace Primera entrega
 * 
 * @author Adriana Lucia Murillo
 * Version 1
 *
 */
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketPlace.Aplicacion;
import co.edu.uniquindio.marketPlace.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private Label labelTituloEmpresa;

	@FXML
	private Label labelUsuarioClave;

	@FXML
	private Label labelUsuarioSesion;

	@FXML
	private Label labelSesion;

	@FXML
	private PasswordField paswordUsuario;

	@FXML
	private TextField txtUsuarioSesion;

	@FXML
	private Button btnIngresar;

	private Aplicacion aplicacion;

	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
	public void setAplicacion ( Aplicacion aplicacion )
	{
		this.aplicacion = aplicacion;
	}

	/**
	 * Muestra la interface de Vendedor
	 * 
	 * */
	/*private void showEmployee ()
	{
		try
		{
			FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketPlace/view/VendedorView.fxml"));
			Parent root = (Parent) fxmlLouder.load();
			MarketPlaceVendedorController vendedorController = fxmlLouder.getController();
			vendedorController.setAplicacion(aplicacion);

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			//stage.setResizable(false);
		} catch ( Exception e )
		{
			e.printStackTrace();
		}

	}*/

	/**
	 * Permite mostrar la interface de Administrador
	 * */
	public void showAdminLogin() {

		try{


			FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketPlace/view/AdministradorView.fxml"));

			Parent root1 = (Parent) loader.load();
			MarketPlaceAdministradorController admController = loader.getController();
			admController.setAplicacion(aplicacion);

			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
			stage.setResizable(false);
		}catch( Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * Permite abrir la Ventana de los productos
	 */
	public void mostrarProductos(){
		
		try
		{

			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketPlace/view/ProductoView.fxml"));
			Parent root1 = (Parent) loader.load();
			ControllerProducto controladorProducto = loader.getController();


			controladorProducto.setAplicacion(aplicacion);

			// Muestra la escena que contiene el anchorPane
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
			stage.setTitle("Gestión de Productos de vendedores");
			stage.setResizable(false);;

		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

	public void iniciarSesion() throws Exception{

		String usuario = txtUsuarioSesion.getText();
		String contrasenia = paswordUsuario.getText();
		boolean confirmar;

		// Comprobación para verificar que el usuario ha rellenado los campos
		if ( usuario.isEmpty() || contrasenia.isEmpty() )
		{
			mostrarMensaje("Error", "Por favor, rellene todos los campos.", "",AlertType.ERROR);

		} else
		{
			if ( modelFactoryController.verificarUsuario(usuario,contrasenia) )
			{
				mostrarMensaje("Notificación", "Ha iniciado sesión como Administrador.","Bienvenido!!", AlertType.INFORMATION);
				
				vaciarCampos();
				
				showAdminLogin();
			} else
			{
				confirmar = aplicacion.getMarketPlace().confirmar(usuario, contrasenia);
			
				if(confirmar == true)
				{
					mostrarMensaje("Notificación", "Ha iniciado sesión como Vendedor","Bienvenido!!", AlertType.INFORMATION);


					//listaVendedores.get(i).getCedula().equals(idDocumento)
					vaciarCampos();
					mostrarProductos();
					//showEmployee();
				}else{
					mostrarMensaje("Notificación", "Datos erroneos", "", AlertType.WARNING);
				}
			}
		}
	}


	/*-----------------EVENTOS-----------------*/
	@FXML
	void iniciarSesion(ActionEvent event) throws Exception {
		iniciarSesion();
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}



	/*Este metodo muestra un mensaje al usuario
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

	/**
	 * Metodo para vaciar los campos de escritura luego de presionar nuevo
	 */
	private void vaciarCampos ()
	{
		txtUsuarioSesion.setText("");
		paswordUsuario.setText("");
	}


	;


}
