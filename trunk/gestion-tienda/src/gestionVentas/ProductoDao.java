package gestionVentas;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Fichero;

public class ProductoDao {

    private String path = null;
    private Fichero fichero = null;

    public ProductoDao(String fich) throws IOException {
	path = fich;
	fichero = new Fichero(path, "r");
    }

    public ArrayList<Producto> recuperar() throws IOException {

	int total_prod = 0;
	ArrayList<Producto> list_productos = new ArrayList<Producto>();

	// Primero, vemos el numero de productos que hay.
	fichero.leerLinea();
	total_prod = Integer.parseInt(fichero.leerLinea());

	// Recuperamos los productos uno a uno
	for (int i = 0; i < total_prod; i++) {

	    Producto producto = new Producto();

	    fichero.leerLinea();
	    producto.set_codigo(Integer.parseInt(fichero.leerLinea()));

	    fichero.leerLinea();
	    producto.set_nombre(fichero.leerLinea());

	    fichero.leerLinea();
	    producto.set_precio(Double.parseDouble(fichero.leerLinea()));

	    list_productos.add(i, producto);

	}

	return list_productos;
    }

    public void guardar(ArrayList<Producto> list_productos) throws IOException {

	fichero.crearArchivo(path, "rw");

	fichero.escribir("productos:\n" + list_productos.size());

	for (int i = 0; i < list_productos.size(); i++) {
	    fichero.escribir("\ncodigo:\n" + list_productos.get(i).get_codigo()
		    + "\n" + "nombre:\n" + list_productos.get(i).get_nombre()
		    + "\n" + "precio:\n" + list_productos.get(i).get_precio());
	}

	fichero.cerrar(path);

    }

}
