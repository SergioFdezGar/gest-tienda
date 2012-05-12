package gestionVentas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utilidades.Fichero;

public class ProductoDao {

    private String path = null;
    private Fichero fichero = null;
    private int codigo;
    private String nombre;
    private double precio;
    private int unidades;
    private String perecedero;
    private int dias;
    private int oferta;

    public ProductoDao(String fich) throws NullPointerException, IOException {
	path = fich;
	fichero = new Fichero(path, "r");
    }

    public ArrayList<Producto> recuperar() throws NumberFormatException,
	    IOException {

	int total_prod = 0;
	ArrayList<Producto> list_productos = new ArrayList<Producto>();

	// Primero, vemos el numero de productos que hay.
	fichero.leerLinea();
	total_prod = Integer.parseInt(fichero.leerLinea());

	// Recuperamos los productos uno a uno
	for (int i = 0; i < total_prod; i++) {

	    Producto producto;

	    fichero.leerLinea();
	    codigo = Integer.parseInt(fichero.leerLinea());

	    fichero.leerLinea();
	    nombre = fichero.leerLinea();

	    fichero.leerLinea();
	    precio = Double.parseDouble(fichero.leerLinea());

	    fichero.leerLinea();
	    unidades = Integer.parseInt(fichero.leerLinea());

	    fichero.leerLinea();
	    perecedero = fichero.leerLinea();

	    if (perecedero.equalsIgnoreCase("si")) {

		fichero.leerLinea();
		dias = Integer.parseInt(fichero.leerLinea());

		producto = new Pro_Perecedero(codigo, nombre, precio, unidades,
			dias);
		list_productos.add(i, producto);

	    } else {

		fichero.leerLinea();
		oferta = Integer.parseInt(fichero.leerLinea());

		producto = new Pro_No_Perecedero(codigo, nombre, precio,
			unidades, oferta);
		list_productos.add(i, producto);
	    }

	}

	return list_productos;
    }

    public void guardar(ArrayList<Producto> list_productos)
	    throws FileNotFoundException, IOException {

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
