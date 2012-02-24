/**
 * 
 */
package main;

import gestionEmpleado.*;
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
		// Arranque del sub-sistema de Ventas
		
		// Solicitud del usuario y password.
		do{
			//Bucle para solicitud de los datos.
			//Parte
			
			System.out.print("\n \t\t Autentificacion... \n");
			valido=true;
			
			//Bucle para mostrar el menú principal y dentro de él, los sub-menus
			while((valido)){
				//Mostramos el menu principal
				opcion=menuPrincipal();
				
				switch (opcion){
				
					case 1: //Hacer Pedido
							
						
							//Solicitar el numero de productos que desea comprar.
							
						
							//Realizar bucle *1
								//Mostrar el menú
								menuPedido();
								
								// Si se añade pedido (opcion 1)
								//  --> Listado cde los productos
								//  --> Solicitamos el código que desea añadir.
								//  --> Comprobación de la existencia y si ha sido agregado anteriormente (¡Mensaje de error!)
								//  --> Si hay error volver a menuPedido()
								//
								// Si se han añadido el numero de productos que se solicitaron, no dejar añadir más productos (¡Mensaje de error!)
								
								//Si quiere visualizar el precio total (opcion 2)
								//  --> Mostar el precio del pedido en ese momento.
								
								// Si quiere imprmir la factura (opcion 3)
								// --> Datos de cada producto adquirido.
								// --> Precio final.
								// --> Persona que lo atendió.
								
								// Terminar pedido (opcion 4)
								//  --> Se sale del menu y vuelve al principal.
							//Terminar bucle *1
														
							break;
					
					case 2: //Modificar producto
						
							// Primero listar los productos
						
							//Solicitar el codigo del producto (y comprobaciones posteriores)
							//funcion para leer el codigo--> AQUI
						
						    //Mostrar el menu de modificación
							menuModificarProducto();
							
							//Solicitar nuevo dato
							
							//Actualizar producto.						
							break;
					
					case 3: //Cambiar contraseña
							cambioPass(null);
							break;
						
					case 4: //Log Out
							valido=false;
							break;
					
					default: //  Volver a solicitar un valor correcto
							System.out.println("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n");
				}
			}
			
		}while(true);
		

	}

	private static int menuPrincipal() {
		int opcion;
		
		System.out.println("1. Hacer pedido");
		System.out.println("2. Modificar producto");
		System.out.println("3. Cambiar contraseña empleado");
		System.out.println("4. Log out");
		System.out.printf("  Elija una opción [1-4]: ");
		
		opcion=leer.nextInt();
		return opcion;
	}

	
	private static int menuPedido() {
		int opcion;
		
		System.out.println("1.1 Añadir pedido");
		System.out.println("1.2 Visualizar precio total");
		System.out.println("1.3 Imprimir factura");
		System.out.println("1.4 Terminar pedido");
		System.out.printf("  Elija una opción [1-4]: ");
		
		opcion=leer.nextInt();
		return opcion;
	}
	
	private static int menuModificarProducto() {
		int opcion;
		
		System.out.println("1. Modificar nombre");
		System.out.println("2. Modificar precio");
		System.out.println("3. Imprimir factura");
		System.out.println("4. Modificar código");
		System.out.printf("  Elija una opción [1-4]: ");
		
		opcion=leer.nextInt();
		return opcion;
	}	
	
	private static void cambioPass(Empleado usuario) {
		String nueva;
		
		System.out.printf("Introduzca nueva password: ");
		nueva=leer.next();
		
		System.out.printf("La contraseña introducida es: "+nueva);
		//Parte en la que se llama a un método del sub-sistema de Empleados
		
	}
}
