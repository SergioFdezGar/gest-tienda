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

import tienda.NoAccessException;
import tienda.VentasException;
import utilidades.Utilidades;

public class Main {

    // private static Utilidades inOut = new Utilidades();
    private static Scanner leer = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {
	boolean valido;

	/* Arranque del sub_sistema de Empleados */

	try {
	    GestorEmpleado ges_empleado = new GestorEmpleado("empleados.txt");
	    ges_empleado.recuperar();

	    /* Arranque del sub-sistema de GestorVentas */
	    GestorVentas ges_ventas = new GestorVentas("productos.txt",
		    "ofertas.txt");
	    ges_ventas.recuperar();
	    /* Parte en la que se solicita el password */
	    do {
		// Bucle para solicitud de los datos.

		Utilidades
			.imprimirLinea("\n\t=========[Sistema de Autenticacion]=========\n");

		valido = autenticacion(ges_empleado);

		// if (!valido) {
		// Utilidades
		// .imprimirLinea("\n  [!] Usuario y/o password no validas, intentelo de nuevo [!]\n\n");
		// }

		/**
		 * Bucle para mostrar el menu principal y dentro de el, los
		 * sub-menus
		 */
		while ((valido)) {
		    int opcion = -1;

		    opcion = menuPrincipal(); /* Mostramos el menu principal */

		    switch (opcion) {

		    case 1: // Hacer Pedido
			hacerPedido(ges_ventas, ges_empleado);
			break;

		    case 2: // Modificar producto

			int producto = -1;
			// Primero listar los productos
			listarProductos(ges_ventas);
			// Solicitar cual es el producto a modificar

			do {
			    producto = Utilidades
				    .leerInt("\n Numero (#) del producto a modificar? [0-"
					    + (ges_ventas.totalProductos() - 1)
					    + "]: ");

			    if (producto < 0
				    || producto > (ges_ventas.totalProductos() - 1)) {
				Utilidades
					.imprimirLinea("\n\t[!] NO EXISTE EL PRODUCTO, respete el rango mostrado. [!]\n");
			    }

			} while (producto < 0
				|| producto > (ges_ventas.totalProductos() - 1));

			modificarProducto(menuModificarProducto(), producto,
				ges_ventas);
			ges_ventas.guardar();

			break;

		    case 3: // Cambiar contrasenia
			cambioPass(ges_empleado);
			break;

		    case 4: // Log Out
			valido = ges_empleado.logOut();
			break;

		    default: // Volver a solicitar un valor correcto
			Utilidades
				.imprimirLinea("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n");
		    }
		}

	    } while (true);

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    errorFile();
	    System.exit(0);
	} catch (IOException e) {
	    e.printStackTrace();
	    errorIo();
	}

    }

    private static void hacerPedido(GestorVentas ges_ventas,
	    GestorEmpleado ges_empleado) {
	int opcion;
	int cantidad; /* Numero de productos que desea pedir */
	int maximo = ges_ventas.totalProductos(); /* Productos disponibles */

	do {
	    cantidad = Utilidades
		    .leerInt("\nCuantos productos desea comprar? ");

	    if (cantidad < 1 || cantidad > maximo) {
		Utilidades.imprimirLinea("\n\t[!] Comom minimo 1, maximo "
			+ maximo + ". [!]\n");
	    }

	} while (cantidad < 1 || cantidad > maximo);

	/* Desde aqui controlamos las acciones del sub-menu 1. Hacer Pedido */
	do {

	    opcion = menuPedido();

	    switch (opcion) {

	    case 1: /* Agregar producto a la factura */

		agregarProducto(ges_ventas, cantidad);

		break;

	    case 2: /* Mostramos el total de la factura */
		System.out.printf("\nEl precio total es: %.2f  Euros\n",
			ges_ventas.calculo_factura());
		break;

	    case 3:

		Utilidades
			.imprimirLinea("\n\n\t\t============[FACTURA]============\n");
		ArrayList<Producto> fact = ges_ventas.get_factura();
		ArrayList<Integer> uni = ges_ventas.get_unidades();

		for (int i = 0; i < fact.size(); i++) {
		    Utilidades.imprimirLinea("\t\t" + fact.get(i).get_codigo()
			    + "\t" + fact.get(i).get_nombre() + "\t"
			    + uni.get(i) + "\t" + fact.get(i).get_precio()
			    + " Euros");
		}
		Utilidades
			.imprimirLinea("\t\t----------------------------------");
		System.out.printf("\t\tTotal Precio:\t\t%.2f Euros\n",
			ges_ventas.calculo_factura());

		/* Agregamos el empleado que realiza el pedido */
		Utilidades.imprimirLinea("\t\tAtendido por: "
			+ ges_empleado.nombreActivo());
		break;

	    case 4: /* Termina el pedido */
		/* Se vuelve a iniciaclizar la variable */
		ges_ventas.resetear_factura();

	    }

	} while (opcion != 4);
	// -----------------------------------------------------------
    }

    private static void modificarProducto(int seleccion, int producto,
	    GestorVentas ges_ventas) {

	boolean cambio = false;
	int unidades;

	do {

	    Utilidades.imprimir("  Introduza nuevo ");

	    switch (seleccion) {
	    case 1: // Nombre
		Utilidades.imprimir(" nombre: ");
		String nombre = Utilidades.lectura();

		if (ges_ventas.posNombre(nombre) < 0) {
		    ges_ventas.modificar_nombre(producto, nombre);
		    cambio = true;
		}

		break;

	    case 2: // Precio
		double precio = Utilidades.leerDouble(" precio: ");
		ges_ventas.modificar_precio(producto, precio);
		cambio = true;

		break;

	    case 3: // Codigo
		int codigo = Utilidades.leerInt(" codigo: ");
		if (ges_ventas.posCodigo(codigo) < 0) {
		    ges_ventas.modificar_codigo(producto, codigo);
		    cambio = true;
		}
		break;
	    case 4: // Unidades
		do {
		    unidades = Utilidades.leerInt(" unidades: ");
		    if (unidades <= 0)
			Utilidades
				.imprimirLinea("\n\t[!] Error al introducir las unidades, intentelo de nuevo [!]\n");
		} while (unidades <= 0);
		ges_ventas.modificar_unidades(producto,
			(unidades + ges_ventas.consultar_unidades(producto)));
		cambio = true;
		break;

	    }

	    if (cambio) {
		Utilidades
			.imprimirLinea("El producto ha sido modificado satisfactoriamente.");
	    } else {
		Utilidades
			.imprimirLinea("El campo que desea modificar ya existe, vuelva a intentarlo.");
	    }

	} while (!cambio);

    }

    private static boolean autenticacion(GestorEmpleado gest_emp) {
	int cod = 0;
	String pass = null;
	boolean b = false;

	cod = Utilidades.leerInt("  -> Codigo de acceso: ");

	Utilidades.imprimir("  -> Password: ");
	pass = Utilidades.lectura();
	try {
	    if (gest_emp.logIn(cod, pass) >= 0) {
		b = true;
	    }

	} catch (NoAccessException e) {
	    Utilidades.imprimirLinea("\n  [!] " + e.getMessage() + " [!]\n\n");
	}
	return b;
    }

    private static int menuPrincipal() {
	int opcion;

	do {
	    Utilidades
		    .imprimirLinea("\n\n\t=========[Menu Principal]=========\n");
	    Utilidades.imprimirLinea("1. Hacer pedido");
	    Utilidades.imprimirLinea("2. Modificar producto");
	    Utilidades.imprimirLinea("3. Cambiar password empleado");
	    Utilidades.imprimirLinea("4. Log out");

	    opcion = Utilidades.leerInt("  Elija una opcion [1-4]: ");

	    if (opcion < 1 || opcion > 4) {
		Utilidades
			.imprimir("\n\t[!] VALOR INCORRECTO, respete el intervalo [!]\n");
	    }

	} while (opcion < 1 || opcion > 4);

	return opcion;
    }

    private static int menuPedido() {
	int opcion;

	do {
	    Utilidades
		    .imprimirLinea("\n\n\t\t=========[HACER PEDIDO]=========\n");
	    Utilidades.imprimirLinea("1.1 Agregar un tipo de producto");
	    Utilidades.imprimirLinea("1.2 Visualizar precio total");
	    Utilidades.imprimirLinea("1.3 Imprimir factura");
	    Utilidades.imprimirLinea("1.4 Terminar pedido");

	    opcion = Utilidades.leerInt("  Elija una opcion [1-4]: ");

	    if (opcion < 1 || opcion > 4) {
		Utilidades
			.imprimir("\n\t[!] VALOR INCORRECTO, por favor, respete el intervalo [!]\n");
	    }

	} while (opcion < 1 || opcion > 4);

	return opcion;

    }

    private static int menuModificarProducto() {
	int opcion;

	do {
	    Utilidades.imprimirLinea("\n\n\t==>[Modificar Producto]<==\n");
	    Utilidades.imprimirLinea("1. Modificar nombre");
	    Utilidades.imprimirLinea("2. Modificar precio");
	    Utilidades.imprimirLinea("3. Modificar codigo");
	    Utilidades.imprimirLinea("4. Modificar unidades");

	    opcion = Utilidades.leerInt("  Elija una opcion [1-4]: ");

	    if (opcion < 1 || opcion > 4) {
		Utilidades
			.imprimir("\n\t[!] VALOR INCORRECTO, respete el intervalo [!]\n");
	    }

	} while (opcion < 1 || opcion > 4);

	return opcion;

    }

    private static boolean cambioPass(GestorEmpleado ges_emp) {
	boolean correcto = false;

	String nueva = null;
	String repeticion = null;

	Utilidades
		.imprimirLinea("\n\n\t=========[Modificar Password]=========\n");

	do {
	    System.out.print("Introduzca nueva password: ");
	    nueva = leer.next();
	    System.out.print("Repita nueva password: ");
	    repeticion = leer.next();

	    if (nueva.equals(repeticion)) {
		try {
		    ges_emp.modificarPass(nueva);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		    errorFile();
		    System.exit(0);
		} catch (IOException e) {
		    e.printStackTrace();
		    errorIo();
		}
		Utilidades.imprimirLinea("\n  [*] Password modificada [*]\n\n");
		correcto = true;
	    } else {
		Utilidades
			.imprimirLinea("\n  [!] Password no coincide, intentelo de nuevo [!]\n\n");
	    }

	} while (!correcto);

	return correcto;
    }

    private static void errorFile() {
	Utilidades
		.imprimirLinea("\n\t\t[!] ERROR: No se han podido cargar los archivos necesarios. [!]\n");
	Utilidades
		.imprimirLinea("\n\t\t[*] Se ha detenido la ejecucion del programa. [*]");
    }

    private static void errorIo() {
	Utilidades
		.imprimirLinea("\n\t\t[!] ATENCION: Proceso en el archivo NO completado. [!]\n");

    }

    private static void agregarProducto(GestorVentas ges_ventas, int cantidad) {
	int prod_selec;
	int unidades;
	int maximo_unidades;
	int maximo = ges_ventas.totalProductos() - 1;
	boolean continuar = true;

	/* Verificamos que se pueden meter mas productos en la factura */
	if (cantidad == ges_ventas.totalProductosFactura()) {
	    /* Mensaje de error y vuelta al primer menu */
	    Utilidades
		    .imprimirLinea("\n\t[!] ATENCION: No puede agregar mas productos [!]\n");
	    return;
	}

	listarProductos(ges_ventas);

	do {
	    prod_selec = Utilidades.leerInt("\nQue producto desea agregar?: ");

	    if (prod_selec < 0 || prod_selec > maximo) {
		Utilidades.imprimir("\n\t[!] Producto fuera de rango [!]\n");
	    } else {
		/* Comprobar si se ha introducido antes el producto. */
		if (ges_ventas.comprobarFactura(prod_selec) >= 0) {
		    /* Mensaje de error */
		    Utilidades
			    .imprimirLinea("\n\t[!] El producto ya existe en la factura [!]\n");
		    prod_selec = -1;
		}

	    }
	} while ((prod_selec < 0) || (prod_selec > maximo));
	/* Cuantas unidades desea agregar del producto */

	do {
	    unidades = Utilidades
		    .leerInt("\nCuantas unidades del producto desea? [1-"
			    + ges_ventas.consultar_unidades(prod_selec) + "] ");
	    maximo_unidades = ges_ventas.consultar_unidades(prod_selec);

	    if (unidades <= 0 || unidades > maximo_unidades) {
		if (unidades <= 0)
		    Utilidades
			    .imprimirLinea("\n\t[!] Error al introducir unidades, valor fuera de rango [!]");
		try {
		    if (unidades > maximo_unidades)
			throw new VentasException(333);
		} catch (VentasException e) {
		    System.out.print(e.getMessage());
		    continuar = false;
		}
	    } else {
		// restar unidades a existencias
		ges_ventas.modificar_unidades(prod_selec,
			ges_ventas.consultar_unidades(prod_selec) - unidades);
		ges_ventas.unidades_pro(unidades);
		continuar = true;
	    }
	} while (unidades <= 0 || unidades > maximo_unidades || !continuar);
	ges_ventas.facturar(prod_selec);
	Utilidades
		.imprimirLinea("\n\t[*] Producto y unidades agregados satisfactoriamente [*]\n");

    }

    private static void listarProductos(GestorVentas ges_ventas) {
	Utilidades
		.imprimirLinea("\n\n\t=========[LISTADO DE PRODUCTOS]=========\n");
	Utilidades
		.imprimir("\t -------------------------------------------------\n");
	Utilidades
		.imprimir("\t|  # | Cod.  | Nombre         | PRECIO | Unidades |\n");

	for (int i = 0; i < ges_ventas.totalProductos(); i++) {

	    System.out
		    .print("\t|----|-------|----------------|--------|----------|\n");
	    System.out.printf("\t|%3d | %5d | %14s |  %3.2f | %8d |\n", i,
		    ges_ventas.consultar_codigo(i),
		    ges_ventas.consultar_nombre(i),
		    ges_ventas.consultar_precio(i),
		    ges_ventas.consultar_unidades(i));
	}
	Utilidades
		.imprimir("\t -------------------------------------------------\n\n");
    }
}
