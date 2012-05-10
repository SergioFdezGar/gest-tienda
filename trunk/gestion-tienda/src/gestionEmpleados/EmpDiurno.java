package gestionEmpleados;

public class EmpDiurno extends Empleado {

    private double retencion;

    public EmpDiurno(int id, String nom, String pass, int lvl, double reten) {
	super(id, nom, pass, lvl);
	this.retencion = reten / 100;
    }

    /**
     * @return the retencion
     */
    public double getRetencion() {
	return retencion;
    }

    /**
     * @param retencion
     *            the retencion to set
     */
    public void setRetencion(double retencion) {
	this.retencion = retencion;
    }

    @Override
    void calcProductividad(double venta) {

	switch (super.getNivel()) {
	case 1:
	    // Retencion 0
	    setProductividad(getProductividad() + GR_NIVEL_1);

	    break;

	case 2:
	    if (venta >= MAX2) {
		setProductividad(getProductividad() + GR_NIVEL_2);
	    } else {
		setProductividad(getProductividad()
			+ (GR_NIVEL_2 - (GR_NIVEL_2 * retencion)));
	    }
	    break;

	case 3:
	    setProductividad(getProductividad()
		    + (GR_NIVEL_3 - (GR_NIVEL_3 * retencion)));

	    break;

	default:
	    setProductividad(0);
	}
    }
}
