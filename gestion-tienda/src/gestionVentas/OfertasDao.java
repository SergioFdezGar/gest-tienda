package gestionVentas;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Fichero;

public class OfertasDao {

    private String path = null;
    private Fichero fichero = null;
    private int idOferta;
    private int maximo;
    private String tip_oferta;
    private int Tantoporciento;

    public OfertasDao(String fich) throws IOException {
	path = fich;
	fichero = new Fichero(path, "r");
    }

    public ArrayList<Oferta> recuperar_ofertas() throws IOException {

	int total_ofertas;

	ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

	fichero.leerLinea();

	total_ofertas = Integer.parseInt(fichero.leerLinea());

	for (int i = 0; i < total_ofertas; i++) {

	    Oferta oferta = new Oferta();

	    fichero.leerLinea();

	    idOferta = Integer.parseInt(fichero.leerLinea());

	    oferta.set_idOferta(idOferta);

	    if (idOferta == 12) {

		fichero.leerLinea();

		tip_oferta = fichero.leerLinea();

		oferta.set_tip_oferta(tip_oferta);

		fichero.leerLinea();

		Tantoporciento = Integer.parseInt(fichero.leerLinea());

		oferta.set_tantoporciento(Tantoporciento);

		fichero.leerLinea();

		maximo = Integer.parseInt(fichero.leerLinea());

		oferta.set_maximo(maximo);
	    } else {
		fichero.leerLinea();

		tip_oferta = fichero.leerLinea();

		oferta.set_tip_oferta(tip_oferta);

	    }
	    ofertas.add(oferta);
	}

	return ofertas;

    }

}
