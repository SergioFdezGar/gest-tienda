package gestionEmpleados;

public class EmpDiurno extends Empleado {

    private double retencion;

    /**
     * 
     */
    public EmpDiurno(int id, String nom, String pass, int lvl, double reten) {
	super(id, nom, pass, lvl);
	this.retencion = reten;
    }

    /**
     * @param id
     * @param nom
     * @param pass
     * @param lvl
     */
    public EmpDiurno(int id, String nom, String pass, int lvl) {
	super(id, nom, pass, lvl);
	// TODO Auto-generated constructor stub
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
    double calcProductividad() {
	// TODO Auto-generated method stub
	return 0;
    }

}
