package controladorDao;

import java.util.ArrayList;

import gestionEmpleados.Empleado;

public class EmpleadoDao {

	private String fichero;
	
	
	public EmpleadoDao(String fich){
		fichero=fich;
	}
	
	public void guardarEmpleados(ArrayList<Empleado> list_empleados){
		
		Fichero.crearArchivo(fichero);
		
		Fichero.escribirEnArchivo("empleados:\n"+list_empleados.size());

		for (int i = 0; i < list_empleados.size(); i++) {
			Fichero.escribirEnArchivo("\nnombre:\n"
									+ list_empleados.get(i).getNombre() + "\n"
									+ "codigo:\n"
									+ list_empleados.get(i).getCod_acceso() + "\n"
									+ "password:\n"
									+ list_empleados.get(i).getPassword());
		}
		
		Fichero.cerrarFichero(fichero);
		
	}
	
	public  ArrayList<Empleado> recuperarEmpleados(){
		
		int total_emp=0;
		ArrayList <Empleado> list_empleados=new ArrayList<Empleado>();
		
		Fichero.abrirArchivo(fichero);
		
		
		//Primero, vemos el numero de empleados que hay.
		Fichero.leerLinea(0);
		total_emp=Integer.parseInt(Fichero.leerLinea(Fichero.devolverPosPuntero()));
		
		//Recuperamos los empleados uno a uno
		for(int i=0; i<total_emp; i++){
			
			Empleado empleado=new Empleado();
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			empleado.setNombre(Fichero.leerLinea(Fichero.devolverPosPuntero()));
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			empleado.setCod_acceso(Integer.parseInt(Fichero.leerLinea(Fichero.devolverPosPuntero())));
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			empleado.setPassword(Fichero.leerLinea(Fichero.devolverPosPuntero()));
			
			list_empleados.add(i, empleado);
			
		}
		
		return list_empleados;
	}
	
}
