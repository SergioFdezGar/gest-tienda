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
    double calcProductividad() {
	// TODO Auto-generated method stub
	return 0;
    }

}
