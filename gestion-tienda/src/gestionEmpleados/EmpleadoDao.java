package gestionEmpleados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import utilidades.Fichero;


public class EmpleadoDao {

	private String path=null;
	private Fichero fichero=null;
	
	public EmpleadoDao(String fich) throws FileNotFoundException{
		path=fich;
		fichero= new Fichero(path, "r");
	}

	public  ArrayList<Empleado> recuperar() throws IOException{
		
		int total_emp=0;
		ArrayList <Empleado> list_empleados=new ArrayList<Empleado>();
		
		
		//Primero, vemos el numero de empleados que hay.
		fichero.leerLinea();
		total_emp=Integer.parseInt(fichero.leerLinea());
		
		//Recuperamos los empleados uno a uno
		for(int i=0; i<total_emp; i++){
			
			Empleado empleado=new Empleado();
			
			fichero.leerLinea();
			empleado.setNombre(fichero.leerLinea());
			
			fichero.leerLinea();
			empleado.setCod_acceso(Integer.parseInt(fichero.leerLinea()));
			
			fichero.leerLinea();
			empleado.setPassword(fichero.leerLinea());
			
			list_empleados.add(i, empleado);
			
		}
		
		return list_empleados;
	}
	
	
	public void guardar(ArrayList<Empleado> list_empleados) throws IOException{
		
		fichero.crearArchivo(path, "rw");
		
		fichero.escribir("empleados:\n"+list_empleados.size());

		for (int i = 0; i < list_empleados.size(); i++) {
			fichero.escribir("\nnombre:\n"
									+ list_empleados.get(i).getNombre() + "\n"
									+ "codigo:\n"
									+ list_empleados.get(i).getCod_acceso() + "\n"
									+ "password:\n"
									+ list_empleados.get(i).getPassword());
		}
		
		fichero.cerrar(path);
		
	}
	
}
