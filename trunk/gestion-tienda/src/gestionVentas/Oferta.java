package gestionVentas;

public abstract class Oferta {

    private int idOferta;
    private int maximo;
    private String tip_oferta;
    private int tantoporciento;

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
	return maximo;
    }

    public void set_maximo(int maximo) {
	this.maximo = maximo;
    }

    public int get_tantoporciento() {
	return tantoporciento;
    }

    public void set_tantoporciento(int tantoporciento) {
	this.tantoporciento = tantoporciento;
    }
}
