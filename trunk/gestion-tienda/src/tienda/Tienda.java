/**
 * Clase Tienda
 *      
 * Version 1.0
 * 
 * Realizado por Sergio Fernandez y Jose Alberto Granados 
 * 
 */

package tienda;

import gestionEmpleados.GestorEmpleado;
import gestionEmpleados.NoAccessException;
import gestionVentas.GestorVentas;
import gestionVentas.Producto;

import java.io.IOException;
import java.util.ArrayList;

public class Tienda {

    private GestorEmpleado ges_empleado;
    private GestorVentas ges_ventas;

    public Tienda(String f_emp, String f_prod, String f_ofer)
	    throws IOException {

	this.ges_empleado = new GestorEmpleado(f_emp);
	this.ges_empleado.recuperar();

	this.ges_ventas = new GestorVentas(f_prod, f_ofer);
	this.ges_ventas.recuperar();

    }

    /**
     * public static void main(String[] args) { boolean valido;
     * 
     * try {
     * 
     * 
     * while ((valido)) { int opcion = -1;
     * 
     * opcion = menuPrincipal();
     * 
     * switch (opcion) {
     * 
     * case 1: // Hacer Pedido hacerPedido(ges_ventas, ges_empleado); break;
     * 
     * case 2: // Modificar producto
     * 
     * int producto = -1; // Primero listar los productos
     * listarProductos(ges_ventas); // Solicitar cual es el producto a modificar
     * 
     * do { producto = Utilidades
     * .leerInt("\n Numero (#) del producto a modificar? [0-" +
     * (ges_ventas.totalProductos() - 1) + "]: ");
     * 
     * if (producto < 0 || producto > (ges_ventas.totalProductos() - 1)) {
     * Utilidades .imprimirLinea(
     * "\n\t[!] NO EXISTE EL PRODUCTO, respete el rango mostrado. [!]\n"); }
     * 
     * } while (producto < 0 || producto > (ges_ventas.totalProductos() - 1));
     * 
     * modificarProducto(menuModificarProducto(), producto, ges_ventas);
     * ges_ventas.guardar();
     * 
     * break;
     * 
     * case 3: // Cambiar contrasenia cambioPass(ges_empleado); break;
     * 
     * case 4: // Log Out valido = ges_empleado.logOut(); break;
     * 
     * default: // Volver a solicitar un valor correcto Utilidades
     * .imprimirLinea("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n"); } }
     * 
     * } while (true);
     * 
     * } catch (FileNotFoundException e) { e.printStackTrace(); errorFile();
     * System.exit(0); } catch (IOException e) { e.printStackTrace(); errorIo();
     * }
     * 
     * }
     **/

    public boolean autenticacion(int cod, String pass) throws NoAccessException {
	boolean b = false;

	if (this.ges_empleado.logIn(cod, pass) >= 0) {
	    b = true;
	}

	return b;
    }

    public int totalProductos() {
	return this.ges_ventas.totalProductos();
    }

    public int consultar_codigo(int i) {

	return this.ges_ventas.consultar_codigo(i);
    }

    public String consultar_nombre(int i) {

	return this.ges_ventas.consultar_nombre(i);
    }

    public double consultar_precio(int i) {
	return this.ges_ventas.consultar_precio(i);
    }

    public int consultar_unidades(int i) {
	return this.ges_ventas.consultar_unidades(i);
    }

    public double calculo_factura() {
	return this.ges_ventas.calculo_factura();
    }

    public void eliminarFactura() {
	this.ges_ventas.eliminarFactura();

    }

    public int totalProductosFactura() {
	return this.ges_ventas.totalProductosFactura();
    }

    public int comprobarFactura(int prod_selec) {
	return this.ges_ventas.comprobarFactura(prod_selec);
    }

    public void modificar_unidades(int prod_selec, int i) {
	this.ges_ventas.modificar_unidades(prod_selec, i);
    }

    public void unidades_pro(int unidades) {
	this.ges_ventas.unidades_pro(unidades);

    }

    public void facturar(int prod_selec) {
	this.ges_ventas.facturar(prod_selec);

    }

    public int posNombre(String nombre) {
	return this.ges_ventas.posNombre(nombre);
    }

    public void modificar_nombre(int producto, String nombre) {
	this.ges_ventas.modificar_nombre(producto, nombre);

    }

    public void modificar_precio(int producto, double precio) {
	this.ges_ventas.modificar_precio(producto, precio);
    }

    public int posCodigo(int codigo) {
	return this.ges_ventas.posCodigo(codigo);
    }

    public void modificar_codigo(int producto, int codigo) {
	this.ges_ventas.modificar_codigo(producto, codigo);

    }

    public void modificarPass(String nueva) throws Exception {
	this.ges_empleado.modificarPass(nueva);
    }

    public boolean logOut() {
	return this.ges_empleado.logOut();
    }

    public ArrayList<Producto> get_factura() {

	return this.ges_ventas.get_factura();
    }

    public ArrayList<Integer> get_unidades() {

	return this.ges_ventas.get_unidades();
    }

    public String nombreActivo() {
	return this.ges_empleado.nombreActivo();
    }

}
