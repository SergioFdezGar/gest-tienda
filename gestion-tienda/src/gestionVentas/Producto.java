package gestionVentas;

//clase abstracta
public class Producto {

    protected int codigo;
    protected String nombre;
    protected double precio;
    protected int unidades;

    public Producto(int codigo, String nombre, double precio, int unidades) {

	this.codigo = codigo;
	this.nombre = nombre;
	this.precio = precio;
	this.unidades = unidades;
    }// Fin constructor

    // Metodos de consulta y modificacion del codigo

    public int get_codigo() {

	return codigo;
    }

    public void set_codigo(int codg) {

	codigo = codg;
    }

    // Metodos de consulta y modificacion del nombre

    public String get_nombre() {

	return nombre;
    }

    public void set_nombre(String nomb) {

	nombre = nomb;
    }

    // Metodos de consulta y modificacion del precio

    public double get_precio() {
	return precio;
    }

    public void set_precio(double prec) {

	precio = prec;
    }

    public int get_unidades() {
	return unidades;
    }

    public void set_unidades(int uni) {
	unidades = uni;
    }
}// Fin Class Producto