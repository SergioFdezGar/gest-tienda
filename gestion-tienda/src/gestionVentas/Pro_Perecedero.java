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
	double precio_parcial = get_precio();
	switch (dias) {
	case 1:
	    precio_parcial /= 4;
	    break;
	case 2:
	    precio_parcial /= 3;
	    break;
	case 3:
	    precio_parcial /= 2;
	    break;
	}

	return precio_parcial * cantidad;
    }

}
