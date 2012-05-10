package gestionVentas;

public class VentasException extends Exception {

    private int codigo;

    public VentasException(int codigo) {
	this.codigo = codigo;
    }

    public String getMessage() {
	String msg = null;
	switch (codigo) {
	case 333:
	    msg = "\n[!] Error 333. Numero insuficiente de productos disponibles";
	    break;
	}
	return msg;
    }
}
