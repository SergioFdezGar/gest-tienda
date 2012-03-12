package gestionVentas;

public class Producto {
  
	private int codigo;
	private String nombre;
	private double precio;

	public Producto() {
		// TODO Auto-generated constructor stub
	}
	
	public Producto(int codigo, String nombre, double precio){		
		this.codigo=codigo;
		this.nombre=nombre;
		this.precio=precio;
	}//Fin constructor
	
	public int get_codigo(){
		return codigo;
	}
	
	public void set_codigo(int codg){
		codigo=codg;
	}
	
	public String get_nombre(){
		return nombre;
	}
	
	public void set_nombre(String nomb){
		nombre=nomb;
	}
	
	public double get_precio(){
		return precio;
	}
	
	public void set_precio(double prec){
		precio=prec;
	}

}//Fin Class Producto
