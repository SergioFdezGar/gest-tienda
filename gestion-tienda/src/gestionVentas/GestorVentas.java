package gestionVentas;

import java.io.IOException;
import java.util.ArrayList;

public class GestorVentas {

    private ArrayList<Producto> list_productos;
    private ArrayList<Oferta> list_ofertas;
    private ArrayList<Producto> factura = new ArrayList<Producto>();
    private ProductoDao produ_dao;
    private OfertasDao ofer_dao;
    private ArrayList<Integer> unidades = new ArrayList<Integer>();

    public GestorVentas(String fich_1, String fich_2) throws IOException {
	produ_dao = new ProductoDao(fich_1);

	ofer_dao = new OfertasDao(fich_2);

    }

    public void recuperar() throws IOException {
	list_productos = new ArrayList<Producto>();
	list_productos = produ_dao.recuperar();
	list_ofertas = new ArrayList<Oferta>();
	list_ofertas = ofer_dao.recuperar_ofertas();
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

    public void modificar_unidades(int i, int unidades) {
	list_productos.get(i).set_unidades(unidades);
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

    public void facturar(int i) {
	factura.add(list_productos.get(i));
    }

    public double calculo_factura() {
	double total_factura = 0;
	for (int i = 0; i < factura.size(); i++) {
	    factura.get(i).set_cantidad(unidades.get(i));
	    total_factura = total_factura + factura.get(i).precio_total();

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

    public void eliminarFactura() {
	this.factura = new ArrayList<Producto>();
    }

    public void unidades_pro(int unidades) {
	this.unidades.add(unidades);
    }

    public ArrayList<Integer> get_unidades() {
	return unidades;
    }

    public void Asociar() {

	for (int i = 0; i < list_productos.size(); i++) {

	    if (list_productos.getClass().getName()
		    .equalsIgnoreCase("Pro_No_Perecedero")) {
		if (((Pro_No_Perecedero) list_productos.get(i)).get_idOferta() == list_ofertas
			.get(i).get_idOferta()) {
		    if (list_ofertas.get(i).get_idOferta() != 12) {
			((Pro_No_Perecedero) list_productos.get(i))
				.set_tip_oferta(list_ofertas.get(i)
					.get_tip_oferta());
		    } else {
			((Pro_No_Perecedero) list_productos.get(i))
				.set_tip_oferta(list_ofertas.get(i)
					.get_tip_oferta());
			((Pro_No_Perecedero) list_productos.get(i))
				.set_maximo(list_ofertas.get(i).get_maximo());
		    }
		}
	    }

	}
    }
}
