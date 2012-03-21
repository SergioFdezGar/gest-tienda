package utilidades;

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
 *        Ultima modificacion: 26/02/2012
 * 
 */

public class Fichero {
    private File fichero;
    private RandomAccessFile canal_IO;

    /**
     * Constructor por defecto
     * 
     */
    public Fichero() {
	fichero = null;
	canal_IO = null;
    }

    public Fichero(File file, String modo) throws IOException {
	abrirArchivo(file, modo);
    }

    public Fichero(String file, String modo) throws IOException {
	// Por defecto se abre como sólo lectura;
	abrirArchivo(file, modo);
    }

    /**
     * Método para crear el fichero de texto, si ya existe lo elimina y crea uno
     * vacio.
     * 
     * @param fich
     *            Variable de tipo File
     * @throws IOException
     */
    public void crearArchivo(File fich, String modo) throws IOException {
	// En el caso de que el fichero exista, lo eliminamos
	if (fich.exists()) {
	    canal_IO.close();
	    fich.delete();
	}
	// Creamos el fichero
	fichero = fich;
	canal_IO = new RandomAccessFile(fichero, modo);
    }

    /**
     * Método para crear el fichero de texto, si ya existe lo elimina y crea uno
     * vacio.
     * 
     * @param s
     *            String con el nombre del fichero de texto o con la ruta y
     *            nombre del fichero.
     * @throws IOException
     */
    public void crearArchivo(String s, String modo) throws IOException {
	crearArchivo(new File(s), modo);
    }

    public void abrirArchivo(File fich, String modo) throws IOException {
	// En el caso de que el fichero no exista, lo creamos
	if (!fich.exists()) {
	    crearArchivo(fich, modo); // El fichero queda enlazado en
				      // crearArchivo()
	} else {
	    fichero = fich;
	}
	canal_IO = new RandomAccessFile(fichero, modo);
    }

    public void abrirArchivo(String s, String modo) throws IOException {
	abrirArchivo(new File(s), modo);
    }

    public void setModo(String modo) throws FileNotFoundException {
	canal_IO = new RandomAccessFile(fichero, modo);
    }

    public long posicionPuntero() throws IOException {
	long pos = 0;

	pos = canal_IO.getFilePointer();
	return pos;
    }

    public String leerLinea() throws IOException {
	String lectura = null;

	lectura = canal_IO.readLine();

	return lectura;
    }

    /**
     * Metodo para escribir en el fichero de texto la cadena que se pasa por
     * parametro. <br>
     * Cierra automaticamente el fichero despues de cada inserccion.
     * 
     * @param cad
     *            String con la cadena de texto a escribir en el texto.
     * @throws IOException
     */
    public void escribir(String cad) throws IOException {

	// Comprobamos si el archivo existe.
	setModo("rw");
	if (fichero.exists()) {
	    // Nos movemos a la ultima posición del archivo.
	    canal_IO.seek(fichero.length());
	    // Escribimos la cadena de texto en el archivo.
	    canal_IO.writeBytes(cad);
	}

	// Cerramos el fichero para que pueda ser utilizado a posteriori.
	cerrar(fichero);

    }

    /**
     * Metodo para cerrar el archivo.
     * 
     * @param fich
     *            File con la informacion del fichero que se va a cerrar.
     * @throws IOException
     */
    public void cerrar(File fich) throws IOException {
	// Si da problemas a la hora de cerrar el archivo descomentar la linea
	// de abajo
	// canal_IO = new RandomAccessFile(fich, "r");
	canal_IO.close();
    }

    /**
     * Metodo para cerrar el archivo.
     * 
     * @param s
     *            con el nombre del fichero de texto o con la ruta y nombre del
     *            fichero.
     * @throws IOException
     */
    public void cerrar(String s) throws IOException {
	cerrar(new File(s));
    }
}
