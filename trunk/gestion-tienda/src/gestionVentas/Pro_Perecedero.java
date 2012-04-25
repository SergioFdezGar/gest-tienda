package gestionVentas;

public class Pro_Perecedero extends Producto {

    private int dias;
    private int cantidad;

    public Pro_Perecedero(int codigo, String nombre, double precio,
	    int unidades, int dias) {

	super(codigo, nombre, precio, unidades);

	this.dias = dias;
    }

    public int get_dias() {
	return dias;
    }

    public void set_dias(int dias) {
	this.dias = dias;
    }

    public void set_cantidad(int cant) {
	cantidad = cant;
    }

    public double precio_total() {
	double precio_parcial;
	if (dias == 1)
	    precio_parcial = get_precio() / 4;
	else {
	    if (dias == 2)
		precio_parcial = get_precio() / 3;
	    else {
		precio_parcial = get_precio() / 2;
	    }
	}
	return precio_parcial * cantidad;
    }

}
