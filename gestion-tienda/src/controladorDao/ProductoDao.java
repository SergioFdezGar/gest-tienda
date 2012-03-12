package controladorDao;

import java.util.ArrayList;

import gestionVentas.Producto;

public class ProductoDao {

	private String fichero;
	
	
	public ProductoDao(String fich){
		fichero=fich;
	}
	
	public void guardarProductos(ArrayList<Producto> list_productos){
		
		Fichero.crearArchivo(fichero);
		
		Fichero.escribirEnArchivo(":\n"+list_productos.size());

		for (int i = 0; i < list_productos.size(); i++) {
			Fichero.escribirEnArchivo("\ncodigo:\n"
									+ list_productos.get(i).get_codigo()+ "\n"
									+ "nombre:\n"
									+ list_productos.get(i).get_nombre() + "\n"
									+ "precio:\n"
									+ list_productos.get(i).get_precio());
		}
		
		Fichero.cerrarFichero(fichero);
		
	}
	
	public  ArrayList<Producto> recuperarProductos(){
		
		int total_emp=0;
		ArrayList <Producto> list_productos=new ArrayList<Producto>();
		
		Fichero.abrirArchivo(fichero);
		
		
		//Primero, vemos el numero de productos que hay.
		Fichero.leerLinea(0);
		total_emp=Integer.parseInt(Fichero.leerLinea(Fichero.devolverPosPuntero()));
		
		//Recuperamos los productos uno a uno
		for(int i=0; i<total_emp; i++){
			
			Producto producto=new Producto();
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			producto.set_codigo(Integer.parseInt(Fichero.leerLinea(Fichero.devolverPosPuntero())));
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			producto.set_nombre(Fichero.leerLinea(Fichero.devolverPosPuntero()));
			
			Fichero.leerLinea(Fichero.devolverPosPuntero());
			producto.set_precio(Double.parseDouble(Fichero.leerLinea(Fichero.devolverPosPuntero())));
			
			list_productos.add(i, producto);
			
		}
		
		return list_productos;
	}
	
}
