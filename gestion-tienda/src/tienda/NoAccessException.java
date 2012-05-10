package tienda;

@SuppressWarnings("serial")
public class NoAccessException extends Exception {

    private int codigo;

    public NoAccessException(int cod) {
	this.codigo = cod;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
	String msg = null;
	switch (codigo) {
	case 1111:
	    msg = "Error. Login incorrecto.";
	    break;
	case 2222:
	    msg = "Error. Password incorrecto.";
	    break;
	default:
	    msg = "Codigo: " + codigo + "de error, desconocido.";
	}

	return msg;
    }
}
