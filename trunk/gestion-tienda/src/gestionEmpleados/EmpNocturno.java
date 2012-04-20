package gestionEmpleados;

public class EmpNocturno extends Empleado {

    private double plus;

    /**
     * @param id
     * @param nom
     * @param pass
     * @param lvl
     * @param plus
     */
    public EmpNocturno(int id, String nom, String pass, int lvl, double plus) {
	super(id, nom, pass, lvl);
	this.plus = plus;
    }

    @Override
    void calcProductividad(double venta) {

	switch (getNivel()) {
	case 1:
	    if (venta >= MAX2) {
		setProductividad(getProductividad()
			+ (2 * GR_NIVEL_1 + venta * plus));
	    } else {
		setProductividad(getProductividad()
			+ (GR_NIVEL_1 + venta * plus));
	    }
	    break;
	case 2:
	    setProductividad(getProductividad() + (GR_NIVEL_2 + venta * plus));
	    break;

	case 3:
	    setProductividad(getProductividad() + (GR_NIVEL_3 + venta * plus));
	    break;

	default:
	    setProductividad(0);
	}
    }
}
