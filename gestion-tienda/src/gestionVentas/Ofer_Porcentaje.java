package gestionVentas;

public class Ofer_Porcentaje extends Oferta {

    private int maximo;

    private int porcentaje;

    public Ofer_Porcentaje(int idOferta, String tip_oferta, int maximo,
	    int porcentaje) {
	super(idOferta, tip_oferta);

	this.maximo = maximo;
	this.porcentaje = porcentaje;
    }

    public int get_maximo() {
	return maximo;
    }

    public void set_maximo(int max) {
	maximo = max;
    }

    public int get_porcentaje() {
	return porcentaje;
    }

    public void set_porcentaje(int porcent) {
	porcentaje = porcent;
    }

}
