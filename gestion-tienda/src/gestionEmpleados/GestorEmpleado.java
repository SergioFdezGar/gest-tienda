package gestionEmpleados;

import java.util.ArrayList;
import controladorDao.EmpleadoDao;

public class GestorEmpleado {

	private ArrayList <Empleado> list_empleados;
	private EmpleadoDao emp_dao;
	private int emp_activo=-1;
	
	public GestorEmpleado(String fich){
		emp_dao=new EmpleadoDao(fich);
	}
	
	public  void recuperar(){
		list_empleados=emp_dao.recuperarEmpleados();
	}
		
	
	public int logIn(int cod, String pass){
		
		//Buscamos en la lista si coincide con algun empleado.
		for(int i=0; i<list_empleados.size(); i++){
			if((cod==list_empleados.get(i).getCod_acceso())
				&&
				(pass.equals(list_empleados.get(i).getPassword()))){
				emp_activo=i;
				break;
			}
		}
		return emp_activo;
	}
	
	public boolean logOut(){
		emp_activo=-1;
		return false;
	}
	
	public void guardar(){
		emp_dao.guardarEmpleados(list_empleados);
	}
	

	public void modificarPass(String pass){
		if(emp_activo>=0){
			list_empleados.get(emp_activo).setPassword(pass);
			guardar();
		}	
	}
	
	

}
