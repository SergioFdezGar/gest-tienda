package gestionVentas;

public class Pro_No_Perecedero extends Producto {

    private int idOferta;

    private int cantidad;

    public Pro_No_Perecedero(int codigo, String nombre, double precio,
	    int unidades, int idOferta) {

	super(codigo, nombre, precio, unidades);
	this.idOferta = idOferta;
    }

    public int get_idOferta() {
	return idOferta;
    }

    public void set_idOferta(int idOfer) {
	idOferta = idOfer;
    }

    public int get_cantidad() {
	return cantidad;
    }

    public void set_cantidad(int cantidad) {
	this.cantidad = cantidad;
    }

    public double precio_total(Oferta oferta) {

	int gratis;
	int cantidad_parcial;

	double precio_parcial;

	if (oferta.get_tip_oferta().equalsIgnoreCase("porcentaje"))
	    precio_parcial = cantidad * get_precio()
		    * (100 - ((Ofer_Porcentaje) oferta).get_porcentaje()) / 100
		    - (((Ofer_Porcentaje) oferta).get_maximo() - 3)
		    * get_precio();
	else {
	    if (oferta.get_tip_oferta().equals("2x1")) {
		if (cantidad == 1) {
		    precio_parcial = get_precio();
		} else {
		    cantidad_parcial = cantidad / 2;
		    gratis = cantidad - (2 * cantidad_parcial);
		    cantidad = cantidad - gratis;
		    precio_parcial = cantidad * get_precio();
		}

	    } else {
		if (oferta.get_tip_oferta().equals("3x2")) {
		    if (cantidad == 1) {
			precio_parcial = get_precio();
		    } else {
			if (cantidad == 2)
			    precio_parcial = cantidad * get_precio();
			else {
			    gratis = cantidad / 3;
			    cantidad = cantidad - gratis;
			    precio_parcial = cantidad * get_precio();
			}
		    }
		} else {
		    precio_parcial = cantidad * get_precio();
		}
	    }

	}
	return precio_parcial;
    }

}
