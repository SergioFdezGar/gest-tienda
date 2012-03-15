/**
 * Clase Main
 * 	
 * Version 1.0
 * 
 * Realizado por Sergio Fernandez y Jose Alberto Granados 
 * 
 */

package main;

import gestionEmpleados.GestorEmpleado;
import gestionVentas.GestorVentas;
import gestionVentas.Producto;

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
	boolean valido;

	// Arranque del sub_sistema de Empleados

	try {
	    GestorEmpleado ges_empleado = new GestorEmpleado("empleados.txt");
	    ges_empleado.recuperar();

	    // Arranque del sub-sistema de GestorVentas
	    GestorVentas ges_ventas = new GestorVentas("productos.txt");
	    ges_ventas.recuperar();
	    // Solicitud del usuario y password.

	    do {
		// Bucle para solicitud de los datos.

		System.out
			.println("\n\t=========[Sistema de Autenticacion]=========\n");
		valido = autenticacion(ges_empleado);

		if (!valido) {
		    System.out
			    .println("\n  [!] Usuario y/o password no validas, intentelo de nuevo [!]\n\n");
		}

		// Bucle para mostrar el menú principal y dentro de él, los
		// sub-menus
		while ((valido)) {
		    int opcion = -1;
		    // Mostramos el menu principal
		    opcion = menuPrincipal();

		    switch (opcion) {

		    case 1: // Hacer Pedido

			// Solicitar el numero de productos que desea comprar.

			// Realizar bucle *1
			// Mostrar el menú
			System.out.println("Cuantos productos desea comprar?");
			int cantidad = leer.nextInt();
			pedido(ges_ventas, cantidad);

			// Si se añade pedido (opcion 1)
			// --> Listado cde los productos
			// --> Solicitamos el código que desea añadir.
			// --> Comprobación de la existencia y si ha sido
			// agregado anteriormente (¡Mensaje de error!)
			// --> Si hay error volver a menuPedido()
			//
			// Si se han añadido el numero de productos que se
			// solicitaron, no dejar añadir más productos (¡Mensaje
			// de error!)

			// Si quiere visualizar el precio total (opcion 2)
			// --> Mostar el precio del pedido en ese momento.

			// Si quiere imprmir la factura (opcion 3)
			// --> Datos de cada producto adquirido.
			// --> Precio final.
			// --> Persona que lo atendió.

			// Terminar pedido (opcion 4)
			// --> Se sale del menu y vuelve al principal.
			// Terminar bucle *1

			break;

		    case 2: // Modificar producto

			int producto = -1;
			// Primero listar los productos
			listarProductos(ges_ventas);
			// Solicitar cual es el producto a modificar

			do {
			    System.out
				    .print("\n  # del producto a modificar? [0-"
					    + (ges_ventas.totalProductos() - 1)
					    + "]: ");
			    producto = leer.nextInt();

			    if (producto < 0
				    || producto > (ges_ventas.totalProductos() - 1)) {
				System.out
					.printf("\n\t[!] NO EXISTE EL PRODUCTO, respete el rango mostrado. [!]\n");
			    }

			} while (producto < 0
				|| producto > (ges_ventas.totalProductos() - 1));

			modificarProducto(menuModificarProducto(), producto,
				ges_ventas);
			ges_ventas.guardar();

			break;

		    case 3: // Cambiar contraseña
			cambioPass(ges_empleado);
			break;

		    case 4: // Log Out
			valido = ges_empleado.logOut();
			break;

		    default: // Volver a solicitar un valor correcto
			System.out
				.println("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n");
		    }
		}

	    } while (true);

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

    private static void modificarProducto(int seleccion, int producto,
	    GestorVentas ges_ventas) {

	boolean cambio = false;

	do {

	    System.out.print("  Introduza nuevo ");

	    switch (seleccion) {
	    case 1: // Nombre
		System.out.print(" nombre: ");
		String nombre = leer.next();

		if (ges_ventas.posNombre(nombre) < 0) {
		    ges_ventas.modificar_nombre(producto, nombre);
		    cambio = true;
		}

		break;

	    case 2: // Precio
		System.out.print(" precio: ");
		double precio = leer.nextDouble();
		ges_ventas.modificar_precio(producto, precio);
		cambio = true;

		break;

	    case 3: // Codigo
		System.out.print(" codigo: ");
		int codigo = leer.nextInt();
		if (ges_ventas.posCodigo(codigo) < 0) {
		    ges_ventas.modificar_codigo(producto, codigo);
		    cambio = true;
		}
		break;

	    }

	    if (cambio) {
		System.out
			.println("El producto ha sido modificado satisfactoriamente.");
	    } else {
		System.out
			.println("El campo que desea modificar ya existe, vuelva a intentarlo.");
	    }

	} while (!cambio);

    }

    private static boolean autenticacion(GestorEmpleado gest_emp) {
	int cod = 0;
	String pass = null;
	boolean b = false;

	System.out.print("  -> Codigo de acceso: ");
	cod = Integer.parseInt(leer.next());
	System.out.print("  -> Password: ");
	pass = leer.next();

	if (gest_emp.logIn(cod, pass) >= 0) {
	    b = true;
	}

	return b;
    }

    private static int menuPrincipal() {
	int opcion;

	System.out.println("\n\n\t=========[Menu Principal]=========\n");
	System.out.println("1. Hacer pedido");
	System.out.println("2. Modificar producto");
	System.out.println("3. Cambiar password empleado");
	System.out.println("4. Log out");
	System.out.printf("  Elija una opcion [1-4]: ");

	opcion = leer.nextInt();
	return opcion;
    }

    private static void menuPedido() {

	System.out.println("1.1 Aniadir pedido");
	System.out.println("1.2 Visualizar precio total");
	System.out.println("1.3 Imprimir factura");
	System.out.println("1.4 Terminar pedido");
	System.out.printf("  Elija una opcion [1-4]: ");

    }

    private static int menuModificarProducto() {
	int opcion;

	do {
	    System.out.println("\n\n\t==>[Modificar Atributo]<==\n");
	    System.out.println("1. Modificar nombre");
	    System.out.println("2. Modificar precio");
	    System.out.println("3. Modificar codigo");
	    System.out.print("  Elija una opción [1-3]: ");

	    opcion = leer.nextInt();

	    if (opcion < 1 || opcion > 3) {
		System.out
			.printf("\n\t[!] VALOR INCORRECTO, elija [1-3] [!]\n");
	    }

	} while (opcion < 1 || opcion > 3);

	return opcion;

    }

    private static boolean cambioPass(GestorEmpleado ges_emp) {
	boolean correcto = false;

	String nueva = null;
	String repeticion = null;

	System.out.println("\n\n\t=========[Modificar Password]=========\n");

	do {
	    System.out.print("Introduzca nueva password: ");
	    nueva = leer.next();
	    System.out.print("Repita nueva password: ");
	    repeticion = leer.next();

	    if (nueva.equals(repeticion)) {
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
		correcto = true;
	    } else {
		System.out
			.println("\n  [!] Password no coincide, intentelo de nuevo [!]\n\n");
	    }

	} while (!correcto);

	return correcto;
    }

    private static void errorGesEmpleados() {
	System.out
		.println("\n\t\t[!] ERROR: No se ha podido cargar a los EMPLEADOS [!]\n");
	System.out.println("Se ha detenido la ejecucion del programa");
    }

    private static void pedido(GestorVentas ges_ventas, int cantidad) {
	int opcion;
	ArrayList<Producto> factura = new ArrayList<Producto>();

	do {
	    menuPedido();
	    opcion = leer.nextInt();
	    switch (opcion) {

	    case 1:
		listarProductos(ges_ventas);

		for (int i = 1; i <= cantidad; i++) {

		    if (i != 1) {
			System.out
				.println("Introduce el numero del producto que desee agregar a la posicion "
					+ i);
			opcion = leer.nextInt();
			System.out.println("El producto seleccionado es "
				+ ges_ventas.consultar_nombre(opcion));
			ges_ventas.aniadir_factura(opcion);
		    } else {
			System.out
				.println("Introduce el numero del producto que desee agregar a la posicion "
					+ i);
			opcion = leer.nextInt();
			System.out.println("El producto seleccionado es "
				+ ges_ventas.consultar_nombre(opcion));
			ges_ventas.aniadir_factura(opcion);
		    }
		}// Fin del for
		break;
	    case 2:
		System.out.println("El precio total es:"
			+ ges_ventas.calculo_factura() + "Euros\n");
		break;
	    case 3:
		System.out.println("\n\n\t\t=========[SHOP]=========\n");
		ArrayList<Producto> fact = ges_ventas.get_factura();
		for (int i = 0; i < fact.size(); i++) {
		    System.out.println("\t\t" + fact.get(i).get_codigo() + "\t"
			    + fact.get(i).get_nombre() + "\t\t"
			    + fact.get(i).get_precio() + " Euros");
		}
		System.out.println("\t\t------------------------");
		System.out.println("\t\tTotal Precio:\t\t"
			+ ges_ventas.calculo_factura() + " Euros" + "\n");
		break;

	    }

	} while (opcion != 4);
    }

    private static void listarProductos(GestorVentas ges_ventas) {
	System.out.println("\n\n\t=========[LISTADO DE PRODUCTOS]=========\n");
	System.out.print("\t --------------------------------------\n");
	System.out.print("\t|  # | Cod.  | Nombre         | PRECIO |\n");

	for (int i = 0; i < ges_ventas.totalProductos(); i++) {

	    System.out.print("\t|----|-------|----------------|--------|\n");
	    System.out.printf("\t|%3d | %5d | %14s |  %3.2f |\n", i,
		    ges_ventas.consultar_codigo(i),
		    ges_ventas.consultar_nombre(i),
		    ges_ventas.consultar_precio(i));
	}
	System.out.print("\t --------------------------------------\n\n");
    }
}
