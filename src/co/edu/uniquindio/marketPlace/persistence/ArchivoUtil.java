package co.edu.uniquindio.marketPlace.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Esta clase teine metodo estaticos que permite usarlos sin crear instancias de la clase
 * Lo que se hizo fue crear esta libreria para el manejo de los archivos
 * @author Admin
 *
 */
public class ArchivoUtil {

	static String fechaSistema = "";

	/**
	 * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
	 * @param ruta es la ruta o path donde esta ubicado el archivo
	 * @throws IOException
	 */

	public static void guardarAchivo(String ruta, String contenido, boolean flagAnexarContenido) throws IOException{

		FileWriter fw      = new FileWriter(ruta, flagAnexarContenido);
		BufferedWriter bfw = new BufferedWriter(fw);
		bfw.write(contenido);
		bfw.close();
		fw.close();

	}

	/**
	 * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
	 * @param ruta
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> leerArchivo(String ruta) throws IOException{

		ArrayList<String> contenido = new ArrayList<String>();//va guardando en este array lo va leyendo del archivo
		FileReader fr = new FileReader(ruta);
		BufferedReader bfr = new BufferedReader(fr);
		String linea="";

		while((linea = bfr.readLine())!=null){
			contenido.add(linea);
		}
		bfr.close();
		fr.close();
		return contenido; 
	}

	/**
	 * 
	 * @param mensajeLog
	 * @param nivel
	 * @param accion
	 * @param rutaArchivo
	 */
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo){

		String log="";
		Logger LOGGER = Logger.getLogger(accion);
		FileHandler fileHandler = null;

		cargarFechaSistema();

		try{
			fileHandler = new FileHandler(rutaArchivo, true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);

			switch(nivel){

			case 1: 
				LOGGER.log(Level.INFO,accion+","+ mensajeLog+","+ fechaSistema);
				break;

			case 2:
				LOGGER.log(Level.WARNING,accion+","+mensajeLog+","+ fechaSistema);
				break;

			case 3: 
				LOGGER.log(Level.SEVERE, accion+","+mensajeLog+","+ fechaSistema);
				break;

			default:
				break;
			}
		}catch(SecurityException e){
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}catch(IOException e){
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		finally{
			fileHandler.close();
		} 
	}

	/**
	 * 
	 */
	public static void cargarFechaSistema(){

		String diaN  = "";
		String mesN  = "";
		String anioN = "";

		Calendar cal1 = Calendar.getInstance();

		int dia  = cal1.get(Calendar.DATE);
		int mes  = cal1.get(Calendar.MONTH)+1;
		int anio = cal1.get(Calendar.YEAR);
		int minuto = cal1.get(Calendar.MINUTE);

		if(dia<10){
			diaN+="0"+dia;
		}else{
			diaN+=""+dia;
		}if(mes<10){
			mesN+="0"+mes;
		}else{
			mesN+=""+mes;
		}

		fechaSistema = anio+"-"+mesN+"-"+diaN;
	}

	//------------------SERIALIZACIÓN & XML-----------

	/**
	 * Escribe en el fichero que se le pasa el objeto que se le envia
	 * @param rutaArchivo
	 *  path del fichero que se quiere escribir
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Object cargarRecursoSerializado(String rutaArchivo) throws Exception{

		Object aux = null;
		//Empresa empresa = null;
		ObjectInputStream ois = null;

		try{
			//se crea un ObjetoInputStream
			ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

			aux = ois.readObject();
		}catch(Exception e2){
			throw e2;
		}finally{
			if(ois != null)
				ois.close();
		}
		return aux;
	}

	/**
	 * 
	 * @param rutaArchivo
	 * @param object
	 * @throws Exception
	 */
	public static void salvarRecursoSerializado(String rutaArchivo, Object object) throws Exception{
		
		ObjectOutputStream oos = null;
		
		try{
			oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
			oos.writeObject(object);
		}catch(Exception e){
			throw e;
		}finally{
			if(oos != null)
				oos.close();
		}
	}
	
	/**
	 * 
	 * @param rutaArchivo
	 * @return
	 * @throws IOException
	 */
	public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

		XMLDecoder decodificadorXML;
		Object objetoXML;

		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
		objetoXML = decodificadorXML.readObject();
		decodificadorXML.close();
		return objetoXML;

	}
	
	/**
	 * 
	 * @param rutaArchivo
	 * @param objeto
	 * @throws IOException
	 */
	public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {
		
		XMLEncoder codificadorXML;
		
		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.writeObject(objeto);
		codificadorXML.close();
	}















}
