package gestionVentas;

public class Producto {
  
	private int codigo;
	private String nombre;
	private double precio;

//Constructor
	
	public Producto(){
		
	}
	
	public Producto(int codigo, String nombre, double precio){
		
		this.codigo=codigo;
		this.nombre=nombre;
		this.precio=precio;
	}//Fin constructor

//Metodos de consulta y modificacion del codigo
	
	public int get_codigo(){
		
		return codigo;
	}
	
	public void set_codigo(int codg){
		
		codigo=codg;
	}

//Metodos de consulta y modificacion del nombre
	
	public String get_nombre(){
		
		return nombre;
	}
	
	public void set_nombre(String nomb){
		
		nombre=nomb;
	}

//Metodos de consulta y modificacion del precio
	
	public double get_precio(){
		return precio;
	}
	
	public void set_precio(double prec){
		
		precio=prec;
	}

}//Fin Class Producto
