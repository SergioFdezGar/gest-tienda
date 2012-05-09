package gestionVentas;

public class VentasException extends Exception {
    private int codigo;

    public VentasException(int cod) {
	this.codigo = cod;
    }

    public String getMessage() {
	String msg = null;
	switch (codigo) {
	case 333:
	    msg = "Error. Numero insuficiente de productos disponibles.";
	    break;
	}

	return msg;
    }
}
