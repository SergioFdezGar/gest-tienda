package gestionVentas;

public class Oferta {

    private int idOferta;

    private String tip_oferta;

    public Oferta(int idOferta, String tip_oferta) {
	this.idOferta = idOferta;
	this.tip_oferta = tip_oferta;
    }

    public int get_idOferta() {
	return idOferta;
    }

    public void set_idOferta(int idOf) {
	idOferta = idOf;
    }

    public String get_tip_oferta() {
	return tip_oferta;
    }

    public void set_tip_oferta(String tip_of) {
	tip_oferta = tip_of;
    }

}
