package co.edu.uniquindio.marketPlace.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerLikeComentario implements Initializable{


	@FXML
	private TextField txtComentario;

	@FXML
	private TitledPane titledPaneMeGusta;

	@FXML
	private Button btnComentar;

	@FXML
	private ComboBox<Producto> comBoxProductos;

	@FXML
	private ComboBox<Vendedor> comBoxVendedores;

	@FXML
	private Accordion titledPaneLikeComentario;

	@FXML
	private TitledPane titledPaneComentarios;


	@FXML
	private TextArea txtAreaMensajes;

	@FXML
	private Button mostrarMensajes;

	@FXML
	private ComboBox<Vendedor> comboVenLike;

	@FXML
	private ComboBox<Producto> comboProdLike;

	@FXML
	private Button btncrearMeGusta;

	@FXML
	private TextArea AreaMeGustas;

	@FXML
	private Button btnMostrarLikes;

	@FXML
	void mostrarLikesAction(ActionEvent event) {

		mostrarMeGustas();
	}

	@FXML
	void crearMeGustaAction(ActionEvent event) throws ProductoNoExisteException, VendedorNoExisteException, ProductoExisteException {

		agregarMeGusta();
	}


	@FXML
	void mostrarMensajesAction(ActionEvent event) {

		//mostrarMensajes();1
		mostrarComentarios();//2
	}

	final static char like = '*';//con esta variable indicamos que producto tiene comentario
	Aplicacion aplicacion;
	static ModelFactoryController modelFactoryController ;
	ObservableList<Comentario> listaComentarios = FXCollections.observableArrayList();
	//ObservableList<Enum> listaTipoCuenta = FXCollections.observableArrayList(TipoCuenta.values());
	ObservableList<MeGusta> listMeGusta = FXCollections.observableArrayList();

	Comentario comentarioSeleccionado;


	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

		modelFactoryController = ModelFactoryController.getInstance();

		comBoxProductos.setItems(getProd());//
		comBoxVendedores.setItems(getVendedores());


		comboVenLike.setItems(getVendedores());//para cargar el vendedor que le da LIKE
		comboProdLike.setItems(getProd());
		//String timeStamp = new SimpleDateFormat("yyyy/MM/dd \nHH:mm:ss").format(Calendar.getInstance().getTime());



	}

	/**
	 * Este Método permite mostrar COMENTARIOS enviados por los vendedores
	 */
	public void mostrarComentarios(){

		txtAreaMensajes.setText("");
		//modelFactoryController.marketPlace.getListaVendedores().get(i).getListaComentarios().size(); i++
		int i;

		for(i = 0 ; i < listaComentarios.size(); i++){

			txtAreaMensajes.appendText( "Comentario: "+listaComentarios.get(i).getTexto()+" "+"Comentó: "+listaComentarios.get(i).getVendedorComentarista()+" "+ 
					"Fecha: "+listaComentarios.get(i).getFecha()+" "+"Hora: "+listaComentarios.get(i).getHora()+" \n");



		}

	}


	/**
	 * Permite mostrar los likes realizados por el vendedor
	 */
	public void mostrarMeGustas(){


		AreaMeGustas.setText("");
		//modelFactoryController.marketPlace.getListaVendedores().get(i).getListaComentarios().size(); i++
		int i;

		for(i = 0 ; i < listMeGusta.size(); i++){

			AreaMeGustas.appendText("Producto: "+ listMeGusta.get(i).getProductoMeGusta().getNombre()+" "+"Le gustó a: " + getVendedores().get(i).getNombre()+" "+ "like "+ listMeGusta.get(i).getLike()+"\n");

		}


	}


	/*public void mostrarComentarios(){

		txtAreaMensajes.setText("");
		int i;

		for(i= 0 ; i<modelFactoryController.obtenerVendedores().size(); i++){
		txtAreaMensajes.appendText(modelFactoryController.obtenerVendedores().get(i).getListaComentarios().get(i).getTexto()+" ");
		}


	}*/



	public void setListaComentarios(ObservableList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**---------------------------------------REVISON---------------------------------------
	 * METODO EN REVISION
	 * @return
	 */
	public ObservableList<Comentario> getListaComentarios() {

		listaComentarios.addAll(modelFactoryController.obtenerComentarios());
		return listaComentarios;
	}

	
	/**
	 * 
	 * @return
	 */
	ObservableList<MeGusta>getListMeGusta(){

		listMeGusta.addAll(modelFactoryController.obtenerMeGustas());
		return listMeGusta;
	}


	/**
	 * Este metodo permite cargar la lista de vendedores
	 * @return
	 */
	public static ObservableList<Vendedor>getVendedores(){

		ObservableList<Vendedor> vendedor;

		vendedor = FXCollections.observableArrayList();

		for(int i=0 ; i<modelFactoryController.getMarketPlace().getListaVendedores().size() ; i++){

			vendedor.addAll(modelFactoryController.getMarketPlace().getListaVendedores().get(i));
		}

		return vendedor;
	}


	/**
	 * Carga la lista de Productos
	 * @return
	 */
	public static ObservableList<Producto>getProd() {

		ObservableList<Producto> producto;
		// String cedula = " ";
		producto = FXCollections.observableArrayList();
      
		int i;

		for( i=0; i< modelFactoryController.getMarketPlace().getListaVendedores().get(i).getListaProductos().size(); i++){

			producto.addAll(modelFactoryController.getMarketPlace().getListaVendedores().get(i).getListaProductos().get(i));
		}
		
		return producto;
	}



	@SuppressWarnings("unchecked")
	public void agregarMeGusta() throws ProductoNoExisteException, VendedorNoExisteException, ProductoExisteException{


		ArrayList<MeGusta>lisLike = new ArrayList<MeGusta>();
		Producto prodMeGusta; 
		Vendedor venMeGusta;
		MeGusta productoMeGusta = new MeGusta();
		boolean bandera;
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now().withNano(0);
		prodMeGusta = comboProdLike.getValue();
		venMeGusta = comboVenLike.getValue();


		bandera = modelFactoryController.agregarMeGusta(prodMeGusta, fecha, hora, like,  venMeGusta);
		if(prodMeGusta!=null && bandera==true){

			productoMeGusta.setProductoMeGusta(prodMeGusta);
			productoMeGusta.setFecha(fecha);
			productoMeGusta.setHora(hora);
			productoMeGusta.setLike(like);
			venMeGusta.setListaMeGustasV(lisLike );//el producto comentado por el vendedor
			//productoMeGusta.setListaVendedorMegusta((ArrayList<Vendedor>) getVendedores());

			lisLike.add(productoMeGusta);//
			listMeGusta.add(productoMeGusta);

			mostrarMensaje("Notificación", "", "Se creó  el Me Gusta", AlertType.INFORMATION);

		}else{

			mostrarMensaje("Notificación", "", "No se pudo crear el Me Gusta", AlertType.ERROR);
		}



	}




	//agregar comentario
	public void crearComentario(){


		boolean agregado;

		Vendedor vendedor;
		Producto productoComentado;


		String texto = txtComentario.getText();
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now().withNano(0);
		vendedor = comBoxVendedores.getValue();
		productoComentado =  comBoxProductos.getValue();

		agregado = modelFactoryController.crearComentario(texto, fecha, hora, vendedor, productoComentado);


		if(agregado == true){

			Comentario comentario = new Comentario();
			comentario.setTexto(texto);
			comentario.setFecha(fecha);
			comentario.setHora(hora);
			comentario.setVendedorComentarista(vendedor);
			comentario.setProductoComentado(productoComentado);
			listaComentarios.add(comentario);



			mostrarMensaje("Notificación vendedor", "", "El comentario fue agreado", AlertType.CONFIRMATION);
		}else{
			mostrarMensaje("Notificación vendedor", "", "El comentario no pudo ser agregado", AlertType.WARNING);
		}



	}



	@FXML
	void comentarAction(ActionEvent event) {

		crearComentario();
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






}
