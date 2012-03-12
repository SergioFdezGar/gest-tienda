/**
 * 
 */
package main;

import gestionEmpleados.*;
import gestionVentas.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author v4lm0nt
 *
 */
public class Main {

	/**
	 * @param args
	 */
	private static Scanner leer = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcion;
		boolean valido;
		// Arranque del sub_sistema de Empleados
		
		
		try {
			GestorEmpleado ges_empleado = new GestorEmpleado("empleados.txt");
			ges_empleado.recuperar();
		
			// Arranque del sub-sistema de GestorVentas
			GestorVentas ges_ventas=new GestorVentas("productos.txt");
			ges_ventas.recuperar();
			// Solicitud del usuario y password.
			
			
			do{
				//Bucle para solicitud de los datos.
				
				System.out.println("\n\t=========[Sistema de Autenticacion]=========\n");
				valido=autenticacion(ges_empleado);
				
				if(!valido){
					System.out.println("\n  [!] Usuario y/o password no validas, intentelo de nuevo [!]\n\n");
				}
				
				
				//Bucle para mostrar el men√∫ principal y dentro de √©l, los sub-menus
				while((valido)){
					
					
					//Mostramos el menu principal
					opcion=menuPrincipal();
					
					switch (opcion){
					
						case 1: //Hacer Pedido
								
							
								//Solicitar el numero de productos que desea comprar.
								
							
								//Realizar bucle *1
									//Mostrar el men√∫
									System.out.println("Cuantos productos desea comprar?");
									int cantidad=leer.nextInt();
									pedido(ges_ventas,cantidad);
									
									// Si se a√±ade pedido (opcion 1)
									//  --> Listado cde los productos
									//  --> Solicitamos el c√≥digo que desea a√±adir.
									//  --> Comprobaci√≥n de la existencia y si ha sido agregado anteriormente (¬°Mensaje de error!)
									//  --> Si hay error volver a menuPedido()
									//
									// Si se han a√±adido el numero de productos que se solicitaron, no dejar a√±adir m√°s productos (¬°Mensaje de error!)
									
									//Si quiere visualizar el precio total (opcion 2)
									//  --> Mostar el precio del pedido en ese momento.
									
									// Si quiere imprmir la factura (opcion 3)
									// --> Datos de cada producto adquirido.
									// --> Precio final.
									// --> Persona que lo atendi√≥.
									
									// Terminar pedido (opcion 4)
									//  --> Se sale del menu y vuelve al principal.
								//Terminar bucle *1
															
								break;
						
						case 2: //Modificar producto
							
								// Primero listar los productos
								System.out.println("LISTADO PRODUCTOS....");
								System.out.print(ges_ventas.listar());
								//Solicitar el codigo del producto (y comprobaciones posteriores)
								//funcion para leer el codigo--> AQUI
							
							    //Mostrar el menu de modificaci√≥n
								menuModificarProducto();
								
								//Solicitar nuevo dato
								
								//Actualizar producto.						
								break;
						
						case 3: //Cambiar contrase√±a
								cambioPass(ges_empleado);
								break;
							
						case 4: //Log Out
								valido=ges_empleado.logOut();
								break;
						
						default: //  Volver a solicitar un valor correcto
								System.out.println("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n");
					}
				}
				
			}while(true);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorGesEmpleados();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorGesEmpleados();
		}		

	}

	private static boolean autenticacion(GestorEmpleado gest_emp){
		int cod=0;
		String pass=null;
		boolean b = false;
		
		System.out.print("  -> Codigo de acceso: ");
		cod=Integer.parseInt(leer.next());
		System.out.print("  -> Password: ");
		pass=leer.next();
		
		if(gest_emp.logIn(cod, pass)>=0){
			b=true;
		}
		
		return b;
	}
	
	private static int menuPrincipal() {
		int opcion;
		
		System.out.println("\n\n\t=========[Menu Principal]=========\n");
		System.out.println("1. Hacer pedido");
		System.out.println("2. Modificar producto");
		System.out.println("3. Cambiar contraseÒa empleado");
		System.out.println("4. Log out");
		System.out.printf("  Elija una opcion [1-4]: "+"\n");
		
		opcion=leer.nextInt();
		return opcion;
	}

	
	private static void menuPedido() {
		
		System.out.println("1.1 AÒadir pedido");
		System.out.println("1.2 Visualizar precio total");
		System.out.println("1.3 Imprimir factura");
		System.out.println("1.4 Terminar pedido");
		System.out.printf("  Elija una opcion [1-4]: "+"\n");
		
	}
	
	private static int menuModificarProducto() {
		int opcion;
		
		System.out.println("1. Modificar nombre");
		System.out.println("2. Modificar precio");
		System.out.println("3. Modificar c√≥digo");
		System.out.printf("  Elija una opci√≥n [1-3]: "+"\n");
		
		opcion=leer.nextInt();
		return opcion;
	}	
	
	private static boolean cambioPass(GestorEmpleado ges_emp) {
		boolean correcto=false;
		
		String nueva=null;
		String repeticion=null;
		
		System.out.println("\n\n\t=========[Modificar Password]=========\n");
		
		do{
			System.out.print("Introduzca nueva password: ");
			nueva=leer.next();
			System.out.print("Repita nueva password: ");
			repeticion=leer.next();
			
			if(nueva.equals(repeticion)){
				try {
					ges_emp.modificarPass(nueva);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorGesEmpleados();
					System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorGesEmpleados();
				}
				System.out.println("\n  [!] Password modificada [!]\n\n");
				correcto=true;
			}
			else{
				System.out.println("\n  [!] Password no coincide, intentelo de nuevo [!]\n\n");
			}
			
		}while(!correcto);
		
		return correcto;
	}
	
	private static void errorGesEmpleados(){
		System.out.println("\n\t\t[!] ERROR: No se ha podido cargar a los EMPLEADOS [!]\n");
		System.out.println("Se ha detenido la ejecucion del programa");		
	}
	
	private static void pedido(GestorVentas ges_ventas, int cantidad){
		int opcion;
		ArrayList<Producto> factura=new ArrayList<Producto>();
		
		do{
			menuPedido();
			opcion=leer.nextInt();
			switch(opcion){
			
			case 1:
				System.out.println("La lista de productos en la tienda es: "+ges_ventas.listar());
				
				for(int i=1;i<=cantidad;i++){
					
					if(i!=1){
						System.out.println("Introduce el numero del producto que desee aÒadir a la posicion "+i);
						opcion=leer.nextInt();
						System.out.println("El producto seleccionado es "+ges_ventas.consultar_nombre(opcion));
						ges_ventas.aÒadir_factura(opcion);
					}
					else{
						System.out.println("Introduce el numero del producto que desee aÒadir a la posicion "+i);
						opcion=leer.nextInt();
						System.out.println("El producto seleccionado es "+ges_ventas.consultar_nombre(opcion));
						ges_ventas.aÒadir_factura(opcion);
					}
				}//Fin del for
				break;
			case 2:
				System.out.println("El precio total es:"+ges_ventas.calculo_factura()+"Ä\n");
				break;
			case 3:
				System.out.println("\n\n\t\t=========[SHOP]=========\n");
				ArrayList<Producto> fact=ges_ventas.get_factura();
				for(int i=0;i<fact.size();i++){
				System.out.println("\t\t"+fact.get(i).get_codigo()+"\t"+fact.get(i).get_nombre()+"\t\t"+fact.get(i).get_precio()+" Ä");
				}
				System.out.println("\t\t------------------------");
				System.out.println("\t\tTotal Precio:\t\t"+ges_ventas.calculo_factura()+" Ä"+"\n");
				break;
		    
			}
			
		}while(opcion!=4);
	}
	
}
