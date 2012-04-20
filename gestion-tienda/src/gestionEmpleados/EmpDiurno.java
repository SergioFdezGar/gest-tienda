package gestionEmpleados;

public class EmpDiurno extends Empleado {

    private double retencion;

    public EmpDiurno(int id, String nom, String pass, int lvl, double reten) {
	super(id, nom, pass, lvl);
	this.retencion = reten;
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
    double calcProductividad(double venta) {
	// TODO Auto-generated method stub
	return 0;
    }

}
