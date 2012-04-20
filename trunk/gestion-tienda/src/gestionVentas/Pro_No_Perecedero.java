package gestionVentas;

public class Pro_No_Perecedero extends Producto {

    private String tip_oferta;

    private int idOferta;

    private int max;

    private double t_p;

    public Pro_No_Perecedero(int codigo, String nombre, double precio,
	    int unidades, int idOferta) {

	super(codigo, nombre, precio, unidades);
	this.idOferta = idOferta;
    }

    public Pro_No_Perecedero(int codigo, String nombre, double precio,
	    int unidades, int idOferta, String tip_oferta, int max, double t_p) {

	super(codigo, nombre, precio, unidades);

	this.tip_oferta = tip_oferta;
	this.idOferta = idOferta;
	this.max = max;
	this.t_p = t_p;
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

    // Posible metodo de interface

    public double precio_no_pere(int cantidad) {

	int gratis;

	if (tip_oferta.equals("porcentaje"))
	    precio = cantidad * precio * (100 - t_p) / 100 - (max - 3) * precio;
	else {
	    if (tip_oferta.equals("2x1")) {
		if (cantidad % 2 != 0) {
		    cantidad = cantidad / 2 + 1;
		    precio = cantidad * precio;
		} else {
		    cantidad = cantidad / 2;
		    precio = precio * cantidad;
		}
	    } else {
		if (cantidad == 2)
		    precio = cantidad * precio;
		else {
		    gratis = cantidad / 3;
		    cantidad = cantidad - gratis;
		    precio = precio * cantidad;
		}
	    }

	}
	return precio;
    }
}
