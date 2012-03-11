package practica;
import java.util.*;
public class Ventas {
	
//iniciamos el arraylist
	
	private ArrayList catalogo = new ArrayList();

	private Producto [] productos;
	private int total_productos;
	private boolean vacio;
	private boolean existencia;
	public Ventas(Producto [] productos){
		this.productos=productos;
	}

//Le pasamos al ArrayList la matriz de objetos para operar ya solo con el ArrayList
	
	public void ArrayList(){
		for(int i=0;i<productos.length;i++){
			catalogo.add(i,productos[i]);
		}
	}

//Metodo para añadir mas productos
	
	public void set_producto(Producto producto){
		catalogo.add(producto);
	}

//Metodos para eliminar productos segun que se le pase, posicion o nombre
	
	public void remove_pro(int i){
		catalogo.remove(i);
	}
	
	public void remove_pro(Producto producto){
		catalogo.remove(producto);
	}

//Metodo para saber cuantos productos hay en el ArrayList
	
	public int cantidad_pro(){
		total_productos=catalogo.size();
		return total_productos;
	}

//Metodo para saber si hay productos en el ArrayList
	
	public boolean catalogoVa(){
		vacio=catalogo.isEmpty();
		return vacio;
	}
	
//Metodo para limpiar el ArrayList
	
	public void vaciar(){
		catalogo.clear();
	}
	
//Metodo para saber si esta un producto o no dentro del ArrayList
	
	public boolean existenacia(Producto producto){
		existencia=catalogo.contains(producto);
		return existencia;
	}

//Metodos de modificacion (No se hacerlos mediante ArrayList)
	
	public void modificar_codigo(int i, int codg){
		productos[i].set_codigo(codg);
	}
	
	public void modificar_nombre(int i, String nomb){
		productos[i].set_nombre(nomb);
	}
	
	public void modificar_precio(int i, double prec){
		productos[i].set_precio(prec);
	}

//Mas metodos de consulta(Tampoco se hacerlos con ArrayList)
	
	public int consultar_codigo(int i){
		return productos[i].get_codigo();
	}
	
	public String consultar_nombre(int i){
		return productos[i].get_nombre();
	}
	
	public double consultar_precio(int i){
		return productos[i].get_precio();
	}

}//Fin class ventas
