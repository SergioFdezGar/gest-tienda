package gestionVentas;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Utilidades;

public class GestorVentas {

    private ArrayList<Producto> list_productos;
    private ArrayList<Oferta> list_ofertas;
    private ArrayList<Producto> factura = new ArrayList<Producto>();
    private ProductoDao produ_dao;
    private OfertaDao ofer_dao;
    private ArrayList<Integer> unidades = new ArrayList<Integer>();

    public GestorVentas(String fich_1, String fich_2) throws IOException {
	produ_dao = new ProductoDao(fich_1);
	ofer_dao = new OfertaDao(fich_2);

    }

    public void recuperar() throws IOException {
	list_productos = new ArrayList<Producto>();
	list_productos = produ_dao.recuperar();
	list_ofertas = new ArrayList<Oferta>();
	list_ofertas = ofer_dao.recuperar();
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
	int idOferta;
	// se a�aden las unidades a los productos
	for (int i = 0; i < factura.size(); i++) {
	    factura.get(i).set_cantidad(unidades.get(i));
	}
	for (int i = 0; i < factura.size(); i++) {
	    if (factura.get(i) instanceof Pro_Perecedero) {
		total_factura = total_factura
			+ ((Pro_Perecedero) factura.get(i)).precio_total();
	    } else {
		idOferta = ((Pro_No_Perecedero) factura.get(i)).get_idOferta();
		for (int z = 0; z < list_ofertas.size(); z++) {
		    if (idOferta == list_ofertas.get(z).get_idOferta())
			total_factura = total_factura
				+ ((Pro_No_Perecedero) factura.get(i))
					.precio_total(list_ofertas.get(z));
		}
	    }
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

    public void PedirUnidades(int prod_selec) throws VentasException {
	int unidad;
	int maximo_unidades;
	do {
	    unidad = Utilidades
		    .leerInt("\nCuantas unidades del producto desea? [1-"
			    + consultar_unidades(prod_selec) + "] ");
	    maximo_unidades = consultar_unidades(prod_selec);

	    if (unidad <= 0 || unidad > maximo_unidades) {
		if (unidad <= 0)
		    Utilidades
			    .imprimirLinea("\n\t[!] Error al introducir unidades, valor fuera de rango [!]");
		if (unidad > maximo_unidades)
		    throw new VentasException(333);
	    } else {
		// restar unidades a existencias
		modificar_unidades(prod_selec, consultar_unidades(prod_selec)
			- unidad);
		unidades_pro(unidad);
	    }
	} while (unidad <= 0 || unidad > maximo_unidades);
    }
}
