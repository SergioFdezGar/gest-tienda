package gestionVentas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GestorVentas {

	private ArrayList<Producto> list_productos;
	private ProductoDao produ_dao;
	private String lista = "\n";
	private ArrayList<Producto> factura=new ArrayList<Producto>();

	public GestorVentas(String fich) throws FileNotFoundException {
		produ_dao = new ProductoDao(fich);
	}

	
	public void recuperar() throws IOException{
		list_productos=produ_dao.recuperar();
	}
	
	public String listar() {
		for (int i = 0; i < list_productos.size(); i++) {
			lista = lista + "(" + i + ") " + list_productos.get(i).get_nombre()
					+ "    " + list_productos.get(i).get_precio() + "�" + "\n";
		}
		return lista;
	}

	public void resetear_listar() {
		lista = "\n";
	}

	public void modificar_codigo(int i, int codg) {
		list_productos.get(i).set_codigo(codg);
	}

	public void modificar_nombre(int i, String nomb) {
		list_productos.get(i).set_nombre(nomb);
	}

	public void modificar_precio(int i, double prec) {
		list_productos.get(i).set_precio(prec);
	}

	public int consultar_codigo(int i) {
		return list_productos.get(i).get_codigo();
	}

	public String consultar_nombre(int i) {
		return list_productos.get(i).get_nombre();
	}

	public double consultar_precio(int i) {
		return list_productos.get(i).get_precio();
	}
	
	public void a�adir_factura(int i){
		factura.add(list_productos.get(i));
	}
	
	public double calculo_factura(){
		double total_factura=0;
		for(int i=0;i<factura.size();i++){
			total_factura=total_factura+factura.get(i).get_precio();
		}
		return total_factura;
	}
	
	public ArrayList<Producto> get_factura(){
		return factura;
	}
}
