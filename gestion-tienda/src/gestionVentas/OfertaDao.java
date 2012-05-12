package gestionVentas;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Fichero;

public class OfertaDao {
    private String path = null;
    private Fichero fichero = null;
    private int idOferta;
    private String tipo_oferta;
    private int t_p;
    private int maximo;

    public OfertaDao(String fich) throws NullPointerException, IOException {
	path = fich;
	fichero = new Fichero(path, "r");
    }

    public ArrayList<Oferta> recuperar() throws NumberFormatException,
	    IOException {
	int total_ofertas;
	Oferta oferta;
	ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	// Coge todas las ofertas que puede haber
	fichero.leerLinea();
	total_ofertas = Integer.parseInt(fichero.leerLinea());
	for (int i = 0; i < total_ofertas; i++) {
	    // Coge idOferta
	    fichero.leerLinea();
	    idOferta = Integer.parseInt(fichero.leerLinea());
	    // Comprueba que el idOferta no sea 12
	    if (idOferta != 12) {
		// Coge el tipo de oferta
		fichero.leerLinea();
		tipo_oferta = fichero.leerLinea();
		oferta = new Oferta(idOferta, tipo_oferta);
		ofertas.add(oferta);
	    } else {
		// Coge el tipo de oferta
		fichero.leerLinea();
		tipo_oferta = fichero.leerLinea();
		// Coge el tantoporciento
		fichero.leerLinea();
		t_p = Integer.parseInt(fichero.leerLinea());
		// Coge el maximo
		fichero.leerLinea();
		maximo = Integer.parseInt(fichero.leerLinea());
		oferta = new Ofer_Porcentaje(idOferta, tipo_oferta, t_p, maximo);
		ofertas.add(oferta);
	    }

	}
	return ofertas;
    }
}