package gestionVentas;

public class Pro_Perecedero extends Producto implements Calculo {

    private int dias;

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

    public double precio_total() {
	if (dias == 1)
	    precio = precio * dia1;
	else {
	    if (dias == 2)
		precio = precio * dia2;
	    else {
		precio = precio * dia3;
	    }
	}
	return precio;
    }

}
