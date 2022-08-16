package co.edu.uniquindio.marketPlace;

import java.io.IOException;

import javax.swing.JOptionPane;

import co.edu.uniquindio.marketPlace.controller.*;
import co.edu.uniquindio.marketPlace.exception.*;
import co.edu.uniquindio.marketPlace.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Proyecto Final MarketPlace 
 * 
 * @author Adriana Lucia Murillo
 * Version 
 *
 */

public class Aplicacion extends Application{



	Stage primaryStage;//Global Para pasarcelo a las demas View y Controladores
	private MarketPlace marketPlace = new MarketPlace("");//variable conectada con el Modelo
	//private Vendedor vendedorMarketPlace = new Vendedor();


	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.marketPlace  = marketPlace;
		this.primaryStage.setTitle("Gestión Marketplace Vendedores");

		mostrarVentanPrincipal();
		//mostrarVentanaLikeComentario();
		//mostrarProductos();
		//mostrarVendedorUno();

	}

	public void mostrarVentanPrincipal(){

		//Se encarga de cargar la interface
		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/LoginView.fxml"));
			AnchorPane anchorPane = loader.load();
			LoginController loginController = loader.getController();


			loginController.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void mostrarVentanaLikeComentario(){

		try{


			FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketPlace/view/ComentarioView.fxml"));

			Parent root1 = (Parent) loader.load();
			ControllerLikeComentario controladorLikComentario = loader.getController();
			controladorLikComentario.setAplicacion(this);

			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
			stage.setResizable(false);
		}catch( Exception e){
			e.printStackTrace();
		}

	}



	//co.edu.uniquindio.marketPlace.controller.ControllerVendedorUnoView
	public void mostrarVendedorUno(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorUnoView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorUnoView controladorVendedorUno = loader.getController();


			controladorVendedorUno.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Uno");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorDos(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorDosView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorUnoView controladorVendedorDos = loader.getController();


			controladorVendedorDos.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor dos");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorTres(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorTresView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorTresView controladorVendedorTres = loader.getController();


			controladorVendedorTres.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Tres");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorCuatro(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorCuatroView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorCuatroView controladorVendedorCuatro = loader.getController();


			controladorVendedorCuatro.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Cuatro");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorCinco(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorCincoView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorCincoView controladorVendedorCinco = loader.getController();


			controladorVendedorCinco.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Cinco");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorSeis(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorSeisView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorSeisView controladorVendedorSeis = loader.getController();


			controladorVendedorSeis.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Seis");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorSiete(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorSieteView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorSieteView controladorVendedorSiete = loader.getController();


			controladorVendedorSiete.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Siete");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void mostrarVendedorOcho(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorOchoView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorOchoView controladorVendedorOcho = loader.getController();


			controladorVendedorOcho.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Ocho");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void mostrarVendedorNueve(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorNueveView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorNueveView controladorVendedorNueve = loader.getController();


			controladorVendedorNueve.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Nueve");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void mostrarVendedorDiez(){

		try
		{

			// Cargar la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketPlace/view/VendedorDiezView.fxml"));
			AnchorPane anchorPane = loader.load();
			ControllerVendedorDiezView controladorVendedorDiez = loader.getController();


			controladorVendedorDiez.setAplicacion(this);

			// Muestra la escena que contiene el anchorPane
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Perfil Vendedor Diez");
		} catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * Dependiendo de la clave del vendedor va abrir la ventana de ese vendedor
	 */
	public void verificarPerfil(){

		String contrasenia = JOptionPane.showInputDialog("Ingrese su clave");
		int i;
		//for(i=0; i< marketPlace.getListaVendedores().size();i++)
		if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("19")){

			JOptionPane.showMessageDialog(null, "Bienvenido!!");

			mostrarVendedorUno();
		}else{
			if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("12")){

				JOptionPane.showMessageDialog(null, "Bienvenido!!");
				mostrarVendedorDos();
			}else{
				if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("123")){

					JOptionPane.showMessageDialog(null, "Bienvenido!!");
					mostrarVendedorTres();
				}else{
					if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1234")){

						JOptionPane.showMessageDialog(null, "Bienvenido!!");
						mostrarVendedorCuatro();
					}else{
						if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1235")){

							JOptionPane.showMessageDialog(null, "Bienvenido!!");
							mostrarVendedorCinco();
						}else{
							if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1236")){

								JOptionPane.showMessageDialog(null, "Bienvenido!!");
								mostrarVendedorSeis();
							}else{
								if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1237")){

									JOptionPane.showMessageDialog(null, "Bienvenido!!");
									mostrarVendedorSiete();
								}else{
									if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1238")){

										JOptionPane.showMessageDialog(null, "Bienvenido!!");
										mostrarVendedorOcho();
									}else{
										if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1239")){

											JOptionPane.showMessageDialog(null, "Bienvenido!!");
											mostrarVendedorNueve();
										}else{
											if(marketPlace.mostrarPerfil(contrasenia)==true && contrasenia.equalsIgnoreCase("1239")){

												JOptionPane.showMessageDialog(null, "Bienvenido!!");
												mostrarVendedorDiez();
											}else{
												JOptionPane.showMessageDialog(null, "Error");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}





	public static void main(String[] args) {
		launch(args);

	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}





























}
