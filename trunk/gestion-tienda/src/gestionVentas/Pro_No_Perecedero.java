package gestionVentas;

public class Pro_No_Perecedero extends Producto {

    private String tip_oferta;

    private int idOferta;

    private int max;

    private double t_p;

    private int cantidad;

    public Pro_No_Perecedero(int codigo, String nombre, double precio,
	    int unidades, int idOferta) {

	super(codigo, nombre, precio, unidades);
	this.idOferta = idOferta;
    }

    public String get_tip_oferta() {
	return tip_oferta;
    }

    public void set_tip_oferta(String tip_ofer) {
	tip_oferta = tip_ofer;
    }

    public int get_idOferta() {
	return idOferta;
    }

    public void set_idOferta(int idOfer) {
	idOferta = idOfer;
    }

    public int get_maximo() {
	return max;
    }

    public void set_maximo(int maximo) {
	this.max = maximo;
    }

    public int get_cantidad() {
	return cantidad;
    }

    public void set_cantidad(int cantidad) {
	this.cantidad = cantidad;
    }

    // Posible metodo de interface

    public double precio_total() {

	int gratis;

	if (tip_oferta.equals("porcentaje"))
	    set_precio(cantidad * get_precio() * (100 - t_p) / 100 - (max - 3)
		    * get_precio());
	else {
	    if (tip_oferta.equals("2x1")) {
		if (cantidad % 2 != 0) {
		    cantidad = cantidad / 2 + 1;
		    set_precio(cantidad * get_precio());
		} else {
		    cantidad = cantidad / 2;
		    set_precio(cantidad * get_precio());
		}
	    } else {
		if (cantidad == 2)
		    set_precio(cantidad * get_precio());
		else {
		    gratis = cantidad / 3;
		    cantidad = cantidad - gratis;
		    set_precio(cantidad * get_precio());
		}
	    }

	}
	return get_precio();
    }
}
