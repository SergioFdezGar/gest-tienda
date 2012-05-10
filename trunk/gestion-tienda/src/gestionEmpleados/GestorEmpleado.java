package gestionEmpleados;

import java.io.IOException;
import java.util.ArrayList;

import tienda.NoAccessException;

public class GestorEmpleado {

    private ArrayList<Empleado> list_empleados = new ArrayList<Empleado>();
    private EmpleadoDao emp_dao;
    private int emp_activo = -1;

    public GestorEmpleado(String fich) throws IOException {
	emp_dao = new EmpleadoDao(fich);
    }

    public void recuperar() throws IOException {
	list_empleados = new ArrayList<Empleado>();
	list_empleados = emp_dao.recuperar();
    }

    public int logIn(int cod, String pass) throws NoAccessException {

	// Buscamos en la lista si coincide con algun empleado.
	for (int i = 0; i < list_empleados.size(); i++) {
	    if (cod == list_empleados.get(i).getCod_acceso()) {
		if (pass.equals(list_empleados.get(i).getPassword())) {
		    emp_activo = i;
		    break;
		} else {
		    throw new NoAccessException(2222);
		}
	    } else if (i == list_empleados.size() - 1) {
		throw new NoAccessException(1111);
	    }
	}
	return emp_activo;
    }

    public boolean logOut() {
	emp_activo = -1;
	return false;
    }

    public void guardar() throws IOException {
	emp_dao.guardar(list_empleados);
    }

    public void modificarPass(String pass) throws IOException {
	if (emp_activo >= 0) {
	    list_empleados.get(emp_activo).setPassword(pass);
	    guardar();
	}
    }

    public String nombreActivo() {
	String nombre = null;

	if (emp_activo >= 0) {
	    nombre = list_empleados.get(emp_activo).getNombre();
	}
	return nombre;
    }

    public int codigoEmpleado() {
	int cod = 0;

	if (emp_activo >= 0) {
	    cod = list_empleados.get(emp_activo).getCod_acceso();
	}
	return cod;
    }

    public int nivelActivo() {
	int lvl = 0;

	if (emp_activo >= 0) {
	    lvl = list_empleados.get(emp_activo).getNivel();
	}
	return lvl;
    }

    public double productividadActivo() {
	double prod = 0;

	if (emp_activo >= 0) {
	    prod = list_empleados.get(emp_activo).getProductividad();
	}
	return prod;

    }

    public String turnoEmpleado() {
	String turno = null;

	if (emp_activo >= 0) {
	    turno = list_empleados.get(emp_activo).getTurno();
	}
	return turno;
    }

    public void calcProducitivad(double d) {

	if (emp_activo >= 0) {
	    list_empleados.get(emp_activo).calcProductividad(d);
	}
    }

}
