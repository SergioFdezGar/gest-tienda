package gestionVentas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import tienda.VentasException;

public class GestorVentas {

    private ArrayList<Producto> list_productos;
    private ArrayList<Oferta> list_ofertas;
    private ArrayList<Producto> factura = new ArrayList<Producto>();
    private ProductoDao produ_dao;
    private OfertaDao ofer_dao;
    private ArrayList<Integer> unidades = new ArrayList<Integer>();

    public GestorVentas(String fich_1, String fich_2)
	    throws NullPointerException, IOException {
	produ_dao = new ProductoDao(fich_1);
	ofer_dao = new OfertaDao(fich_2);

    }

    public void recuperar() throws NumberFormatException, IOException {
	list_productos = new ArrayList<Producto>();
	list_productos = produ_dao.recuperar();
	list_ofertas = new ArrayList<Oferta>();
	list_ofertas = ofer_dao.recuperar();
    }

    public void guardar() throws FileNotFoundException, IOException {
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

    public void modificar_unidades(int i, int cant) throws VentasException {
	if (cant < 0)
	    throw new VentasException(333);
	else
	    list_productos.get(i).set_unidades(cant);
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

    public int consultar_unidades(int i) {
	return list_productos.get(i).get_unidades();
    }

    public void facturar(int i, int cant) {
	factura.add(list_productos.get(i));
	Integer total = (Integer) cant;
	unidades.add(total);
    }

    public double calculo_factura() {
	double total_factura = 0;
	int idOferta;
	// se aniaden las unidades a los productos
	for (int i = 0; i < factura.size(); i++) {
	    factura.get(i).set_cantidad(unidades.get(i));
	}
	for (int i = 0; i < factura.size(); i++) {
	    if (factura.get(i) instanceof Pro_Perecedero) {
		total_factura += ((Pro_Perecedero) factura.get(i))
			.precio_total();
	    } else {
		idOferta = ((Pro_No_Perecedero) factura.get(i)).get_idOferta();
		for (int z = 0; z < list_ofertas.size(); z++) {
		    if (idOferta == list_ofertas.get(z).get_idOferta())
			total_factura += ((Pro_No_Perecedero) factura.get(i))
				.precio_total(list_ofertas.get(z));
		}
	    }
	}
	return total_factura;
    }

    public ArrayList<Producto> get_factura() {
	return factura;
    }

    // Parte que se encarga de decirnos si existe un producto segun su nombre
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

    // Parte que se encarga de decirnos si existe un producto según su
    // categoria
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

    public void unidades_pro(int unidades) {
	this.unidades.add(unidades);
    }

    public ArrayList<Integer> get_unidades() {
	return unidades;
    }

    public void resetear_factura() {
	factura.clear();
	unidades.clear();
    }
}
