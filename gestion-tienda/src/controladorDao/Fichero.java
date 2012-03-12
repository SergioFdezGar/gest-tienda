package controladorDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase que maneja las acciones sobre un fichero de texto. <br>
 * <br>
 * Permite:
 * <ul>
 * <li>Crear ficero de texto.</li>
 * <li>Escribir en fichero de texto.</li>
 * <li>Cerrar el fichero de texto.</li>
 * </ul>
 * <br>
 * 
 * @autor Sergio Fernandez Garcia <br>
 *         Ultima modificacion: 26/02/2012
 * 
 */

public class Fichero {
	private static File fichero;
	private static RandomAccessFile canalSalida;

	/**
	 * Método para crear el fichero de texto, si ya existe lo elimina y crea uno
	 * vacio.
	 * 
	 * @param fich
	 *            Variable de tipo File
	 */

	public static void crearArchivo(File fich) {
		// En el caso de que el fichero exista, lo eliminamos
		if (fich.exists())
			fich.delete();
		// Creamos el fichero
		fichero = fich;
	}
	
	/**
	 * Método para crear el fichero de texto, si ya existe lo elimina y crea uno
	 * vacio.
	 * 
	 * @param s
	 *            String con el nombre del fichero de texto o con la ruta y
	 *            nombre del fichero.
	 */

	public static void crearArchivo(String s) {
		crearArchivo(new File(s));
	}

	
	public static void abrirArchivo(File fich){
		// En el caso de que el fichero no exista, lo creamos
		if (!fich.exists()){
			crearArchivo(fich); //El fichero queda enlazado en crearArchivo()
		}
		else{
			fichero=fich;
		}
	}	
	
	public static void abrirArchivo(String s){
		abrirArchivo(new File(s));
	}
	
	
	public static long devolverPosPuntero(){
		long pos=0;
		try {
			pos=canalSalida.getFilePointer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
		
	}
	
	
	public static String leerLinea(long pos){
		
		String lectura = null;
		
//------------------------------------------------------------------------------
		try {
			// Creamos el canal de acceso direccto al fichero.
			canalSalida = new RandomAccessFile(fichero, "r");
			// Comprobamos si el archivo existe.
			if (fichero.exists()) {
				//Nos movemos hasta la posicion deseada
				canalSalida.seek(pos);
				//Lectura de la línea.
				lectura=canalSalida.readLine();
				
			} else {
				System.out.println("No existe el archivo");
			}

		} catch (FileNotFoundException e) {
			// En el caso de que el archivo no exista mostrará un error.
			e.printStackTrace();

		} catch (IOException e) {
			// Si el archivo no ha podido ser creado mostrará el siguiente
			// error.
			e.printStackTrace();
		}
		// Cerramos el fichero para que pueda ser utilizado a posteriori.
		//cerrarFichero(fichero);		

//------------------------------------------------------------------------------
		return lectura;
	}
	
	
	/**
	 * Metodo para escribir en el fichero de texto la cadena que se pasa por
	 * parametro. <br>
	 * Cierra automaticamente el fichero despues de cada inserccion.
	 * 
	 * @param cad
	 *            String con la cadena de texto a escribir en el texto.
	 */
	public static void escribirEnArchivo(String cad) {

		try {
			// Creamos el canal de acceso direccto al fichero.
			canalSalida = new RandomAccessFile(fichero, "rw");

			// Comprobamos si el archivo existe.
			if (fichero.exists()) {
				// Nos movemos a la ultima posición del archivo.
				canalSalida.seek(fichero.length());
				// Escribimos la cadena de texto en el archivo.
				canalSalida.writeBytes(cad);
			} else {
				System.out.println("No existe el archivo");
			}

		} catch (FileNotFoundException e) {
			// En el caso de que el archivo no exista mostrará un error.
			e.printStackTrace();

		} catch (IOException e) {
			// Si el archivo no ha podido ser creado mostrará el siguiente
			// error.
			e.printStackTrace();
		}
		// Cerramos el fichero para que pueda ser utilizado a posteriori.
		cerrarFichero(fichero);

	}

	/**
	 * Metodo para cerrar el archivo.
	 * 
	 * @param fich
	 *            File con la informacion del fichero que se va a cerrar.
	 */
	public static void cerrarFichero(File fich) {
		try {
			canalSalida = new RandomAccessFile(fich, "r");
			canalSalida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para cerrar el archivo.
	 * 
	 * @param s
	 *            con el nombre del fichero de texto o con la ruta y nombre del
	 *            fichero.
	 */
	public static void cerrarFichero(String s) {
		cerrarFichero(new File(s));
	}
}
