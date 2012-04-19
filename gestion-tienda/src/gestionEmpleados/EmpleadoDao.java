package gestionEmpleados;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Fichero;

public class EmpleadoDao {

    private String path = null;
    private Fichero fichero = null;

    // Atributos princiales de los empleados
    private int cod;
    private String nombre;
    private String password;
    private int nivel;

    public EmpleadoDao(String fich) throws IOException {
	path = fich;
	fichero = new Fichero(path, "r");
    }

    public void guardar(ArrayList<Empleado> list_empleados) throws IOException {

	fichero.crearArchivo(path, "rw");

	fichero.escribir("empleados:\n" + list_empleados.size());

	for (int i = 0; i < list_empleados.size(); i++) {
	    fichero.escribir("\nnombre:\n" + list_empleados.get(i).getNombre()
		    + "\n" + "codigo:\n"
		    + list_empleados.get(i).getCod_acceso() + "\n"
		    + "password:\n" + list_empleados.get(i).getPassword());
	}

	fichero.cerrar(path);

    }

    public ArrayList<Empleado> recuperar() throws IOException {

	int total_emp = 0;
	ArrayList<Empleado> list_empleados = new ArrayList<Empleado>();

	// Primero, vemos el numero de empleados que hay.
	fichero.leerLinea();
	total_emp = Integer.parseInt(fichero.leerLinea());

	// Recuperamos los empleados uno a uno
	for (int i = 0; i < total_emp; i++) {

	    fichero.leerLinea();
	    this.nombre = fichero.leerLinea();

	    fichero.leerLinea();
	    this.cod = Integer.parseInt(fichero.leerLinea());

	    fichero.leerLinea();
	    this.password = fichero.leerLinea();

	    fichero.leerLinea();
	    this.nivel = Integer.parseInt(fichero.leerLinea());

	    // Comprobamos que tipo de empleado es
	    fichero.leerLinea();

	    if ("nocturno".equals(fichero.leerLinea())) {
		fichero.leerLinea();
		double plus = Double.parseDouble(fichero.leerLinea());
		EmpNocturno empleado = new EmpNocturno(cod, nombre, password,
			nivel, plus);
		list_empleados.add(i, empleado);
	    } else {
		fichero.leerLinea();
		double retencion = Double.parseDouble(fichero.leerLinea());
		EmpDiurno empleado = new EmpDiurno(cod, nombre, password,
			nivel, retencion);
		list_empleados.add(i, empleado);
	    }
	}

	return list_empleados;
    }

}
