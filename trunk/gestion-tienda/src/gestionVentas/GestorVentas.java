package gestionVentas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GestorVentas {

    private ArrayList<Producto> list_productos;
    private ArrayList<Producto> factura = new ArrayList<Producto>();
    private ProductoDao produ_dao;

    public GestorVentas(String fich) throws FileNotFoundException {
	produ_dao = new ProductoDao(fich);
    }

    public void recuperar() throws IOException {
	list_productos = produ_dao.recuperar();
    }

    public void guardar() throws IOException {
	produ_dao.guardar(list_productos);
    }

    public void modificar_codigo(int i, int codg) {
	list_productos.get(i).set_codigo(codg);
    }

    public void modificar_nombre(int i, String nomb) {
	list_productos.get(i).set_nombre(nomb);
    }

    public void modificar_precio(int i, double prec) {
	list_productos.get(i).set_precio(prec);
    }

    public int consultar_codigo(int i) {
	return list_productos.get(i).get_codigo();
    }

    public String consultar_nombre(int i) {
	return list_productos.get(i).get_nombre();
    }

    public double consultar_precio(int i) {
	return list_productos.get(i).get_precio();
    }

    public void facturar(int i) {
	factura.add(list_productos.get(i));
    }

    public double calculo_factura() {
	double total_factura = 0;
	for (int i = 0; i < factura.size(); i++) {
	    total_factura = total_factura + factura.get(i).get_precio();
	}
	return total_factura;
    }

    public ArrayList<Producto> get_factura() {
	return factura;
    }

    // Parte que se encarga de decirnos si existe un producto según su nombre
    public int posNombre(String nom) {

	int posicion = -1;

	// Buscamos en la lista si coincide con algun empleado.
	for (int i = 0; i < list_productos.size(); i++) {
	    if (nom.equals(consultar_nombre(i))) {
		posicion = i;
		break;
	    }
	}
	return posicion;
    }

    // Parte que se encarga de decirnos si existe un producto según su categoria
    public int posCodigo(int cod) {

	int posicion = -1;

	// Buscamos en la lista si coincide con algun empleado.
	for (int i = 0; i < list_productos.size(); i++) {
	    if (cod == consultar_codigo(i)) {
		posicion = i;
		break;
	    }
	}
	return posicion;
    }

    public int totalProductos() {
	return list_productos.size();
    }

    public int comprobarFactura(int opcion) {
	int posicion = -1;

	/* Buscamos en la lista si coincide con algun empleado. */
	for (int i = 0; i < factura.size(); i++) {
	    if (factura.get(i).get_codigo() == consultar_codigo(opcion)) {
		posicion = i;
		break;
	    }
	}
	return posicion;
    }

    public int totalProductosFactura() {
	return factura.size();
    }

    public void eliminarFactura() {
	this.factura = new ArrayList<Producto>();
    }

}
