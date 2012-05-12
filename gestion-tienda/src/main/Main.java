/**
 * Clase Tienda
 *      
 * Version 1.0
 * 
 * Realizado por Sergio Fernandez y Jose Alberto Granados 
 * 
 */

package main;

import gestionVentas.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tienda.NoAccessException;
import tienda.Tienda;
import tienda.VentasException;
import utilidades.Utilidades;

public class Main {

    // private static Utilidades inOut = new Utilidades();
    private static Scanner leer = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {

	Tienda tienda;
	try {
	    tienda = new Tienda("empleados.txt", "productos.txt", "ofertas.txt");

	    do {
		boolean valido;
		do {
		    // Bucle para solicitud de los datos.
		    valido = login(tienda);
		    while ((valido)) {
			int opcion = -1;

			opcion = menuPrincipal(); /* Mostramos el menu principal */

			switch (opcion) {
			case 1: // Hacer Pedido
			    hacerPedido(tienda);

			    break;

			case 2: // Modificar producto int producto = -1; //
				// Primero listar los productos
				// tienda.listarProductos();
			    int producto = -1; // Primero listar los productos
			    listarProductos(tienda); // Solicitar cual es el
						     // producto a modificar
			    // Solicitar cual es el producto a modificar

			    do {
				producto = Utilidades
					.leerInt("\n Numero (#) del producto a modificar? [0-"
						+ (tienda.totalProductos() - 1)
						+ "]: ");

				if (producto < 0
					|| producto > (tienda.totalProductos() - 1)) {
				    Utilidades
					    .imprimirLinea("\n\t[!] NO EXISTE EL PRODUCTO, respete el rango mostrado. [!]\n");
				}

			    } while (producto < 0
				    || producto > (tienda.totalProductos() - 1));

			    modificarProducto(menuModificarProducto(),
				    producto, tienda);
			    break;

			case 3: // Cambiar contrasenia

			    cambioPass(tienda);

			    break;

			case 4: // Log Out
				// Mostramos los datos del empleado desconectado
			    productividadEmpleado(tienda);
			    valido = tienda.logOut();
			    break;

			default: // Volver a solicitar un valor correcto
			    Utilidades
				    .imprimirLinea("\n\t[!] El valor es incorrecto, elija [1-4] [!]\n");
			}
		    }

		} while (true);
	    } while (true);
	} catch (IOException e) {
	    errorFile();
	}
    }

    private static void productividadEmpleado(Tienda shop) {

	Utilidades
		.imprimirLinea("\n\n\t\t============[Datos del empleado desconectado.]============\n");
	Utilidades.imprimirLinea(" -> Codigo: " + shop.codigoEmpleado());
	Utilidades.imprimirLinea(" -> Nombre: " + shop.nombreEmpleado());
	Utilidades.imprimirLinea(" -> Nivel: " + shop.nivelEmpleado());
	Utilidades.imprimirLinea(" -> Turno: " + shop.turnoEmpleado());
	System.out.printf(" -> Productividad: %.2f",
		shop.productividadEmpleado());

    }

    private static void hacerPedido(Tienda shop) {
	int opcion;
	int cantidad; /* Numero de productos que desea pedir */
	int maximo = shop.totalProductos(); /* Productos disponibles */

	do {
	    cantidad = Utilidades
		    .leerInt("\nCuantos productos desea comprar? ");

	    if (cantidad < 1 || cantidad > maximo) {
		Utilidades.imprimirLinea("\n\t[!] Como minimo 1, maximo "
			+ maximo + ". [!]\n");
	    }

	} while (cantidad < 1 || cantidad > maximo);

	/* Desde aqui controlamos las acciones del sub-menu 1. Hacer Pedido */
	do {

	    opcion = menuPedido();

	    switch (opcion) {

	    case 1: /* Agregar producto a la factura */
		agregarProducto(shop, cantidad);

		break;

	    case 2: /* Mostramos el total de la factura */
		System.out.printf("\nEl precio total es: %.2f Euros\n",
			shop.calculo_factura());
		break;

	    case 3:
		Utilidades
			.imprimirLinea("\n\n\t\t============[FACTURA]============\n");

		ArrayList<Producto> fact = shop.get_factura();
		ArrayList<Integer> uni = shop.get_unidades();

		for (int i = 0; i < fact.size(); i++) {
      System.out.printf("\t\t %d\t %s \t %d\t %.2f Euros", fact
      .get(i).get_codigo(), fact.get(i).get_nombre(), uni
      .get(i), fact.get(i).get_precio());
		}

		Utilidades
			.imprimirLinea("\t\t----------------------------------");
		System.out.printf("\t\tTotal Precio:\t\t%.2f Euros\n",
			shop.calculo_factura());

		/* Agregamos el empleado que realiza el pedido */
		Utilidades.imprimirLinea("\t\tAtendido por: "
			+ shop.nombreActivo());

		break;

	    case 4: /* Termina el pedido */
		/* Comprobamos que ha finalizado la venta */
		if (cantidad == shop.totalProductosFactura()) {
		    shop.calcProductividad(shop.calculo_factura());
		}
		/* Se vuelve a iniciaclizar la variable */
		shop.eliminarFactura();

	    }

	} while (opcion != 4);
	// -----------------------------------------------------------
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
	    Utilidades.imprimirLinea("1.1 Agregar al pedido");
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

    private static void modificarProducto(int seleccion, int producto,
	    Tienda shop) {

	boolean cambio = false;
	int unidades;

	do {

	    Utilidades.imprimir("  Introduza nuevo ");

	    switch (seleccion) {
	    case 1: // Nombre
		Utilidades.imprimir(" nombre: ");
		String nombre = Utilidades.lectura();

		if (shop.posNombre(nombre) < 0) {
		    shop.modificar_nombre(producto, nombre);
		    cambio = true;
		}

		break;

	    case 2: // Precio
		do {
		    double precio = Utilidades.leerDouble(" precio: ");
		    if (precio >= 0) {
			shop.modificar_precio(producto, precio);
			cambio = true;
		    } else {
			Utilidades
				.imprimirLinea("\n\t[!] No puede contener un valor negativo. [!]\n");
			Utilidades.imprimir("  Introduza nuevo ");
		    }
		} while (!cambio);

		break;

	    case 3: // Codigo
		do {
		    int codigo = Utilidades.leerInt(" codigo: ");
		    if (codigo >= 0) {

			if (shop.posCodigo(codigo) < 0) {
			    shop.modificar_codigo(producto, codigo);
			    cambio = true;
			}
		    } else {
			Utilidades
				.imprimirLinea("\n\t[!] No puede contener un valor negativo. [!]\n");
			Utilidades.imprimir("  Introduza nuevo ");
		    }
		} while (!cambio);

		break;
	    case 4: // Unidades
		do {
		    unidades = Utilidades.leerInt(" unidades: ");
		    if (unidades <= 0)
			Utilidades
				.imprimirLinea("\n\t[!] Error al introducir las unidades, intentelo de nuevo [!]\n");
		} while (unidades < 0);
		try {
		    shop.modificar_unidades(producto,
			    (unidades + shop.consultar_unidades(producto)));
		} catch (VentasException e) {
		    // Ya está controlado por código que no salte la excepcion
		}
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

    public static void listarProductos(Tienda tienda) {
	Utilidades
		.imprimirLinea("\n\n\t=========[LISTADO DE PRODUCTOS]=========\n");
	Utilidades
		.imprimir("\t -------------------------------------------------\n");
	Utilidades
		.imprimir("\t|  # | Cod.  | Nombre         | PRECIO | Unidades |\n");

	for (int i = 0; i < tienda.totalProductos(); i++) {

	    System.out
		    .print("\t|----|-------|----------------|--------|----------|\n");
	    System.out.printf("\t|%3d | %5d | %14s |  %3.2f | %8d |\n", i,
		    tienda.consultar_codigo(i), tienda.consultar_nombre(i),
		    tienda.consultar_precio(i), tienda.consultar_unidades(i));
	}
	Utilidades
		.imprimir("\t -------------------------------------------------\n\n");
    }

    private static boolean login(Tienda shop) {
	Utilidades
		.imprimirLinea("\n\t=========[Sistema de Autenticacion]=========\n");
	int cod = 0;
	String pass = null;
	boolean b = false;

	cod = Utilidades.leerInt("  -> Codigo de acceso: ");

	Utilidades.imprimir("  -> Password: ");
	pass = Utilidades.lectura();

	try {
	    if (shop.autenticacion(cod, pass)) {
		b = true;

	    }

	} catch (NoAccessException e) {
	    Utilidades.imprimirLinea("\n  [!] " + e.getMessage() + " [!]\n\n");
	}
	return b;
    }

    private static void errorFile() {
	Utilidades
		.imprimirLinea("\n\t\t[!] ERROR: No se han podido cargar los archivos necesarios. [!]\n");
	Utilidades
		.imprimirLinea("\n\t\t[*] Se ha detenido la ejecucion del programa. [*]");
    }

    private static boolean cambioPass(Tienda shop) {
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
		shop.modificarPass(nueva);
		Utilidades.imprimirLinea("\n  [*] Password modificada [*]\n\n");
		correcto = true;
	    } else {
		Utilidades
			.imprimirLinea("\n  [!] Password no coincide, intentelo de nuevo [!]\n\n");
	    }

	} while (!correcto);

	return correcto;
    }

    public static void agregarProducto(Tienda shop, int cantidad) {
	int prod_selec;
	int unidades;
	int maximo = shop.totalProductos() - 1;
	boolean repetir = false;

	/* Verificamos que se pueden meter mas productos en la factura */
	if (cantidad == shop.totalProductosFactura()) {
	    /* Mensaje de error y vuelta al primer menu */
	    Utilidades
		    .imprimirLinea("\n\t[!] ATENCION: No puede agregar mas productos [!]\n");
	} else {

	    listarProductos(shop);

	    do {
		repetir = false;
		prod_selec = Utilidades
			.leerInt("\nQue producto desea agregar?: ");

		if (prod_selec < 0 || prod_selec > maximo) {
		    Utilidades
			    .imprimir("\n\t[!] Producto fuera de rango [!]\n");
		    repetir = true;
		} else {
		    /* Comprobar si se ha introducido antes el producto. */
		    if (shop.comprobarFactura(prod_selec) >= 0) {
			/* Mensaje de error */
			Utilidades
				.imprimirLinea("\n\t[!] El producto ya existe en la factura [!]\n");
			prod_selec = -1;
			repetir = true;
		    }
		}

	    } while (repetir);

	    /* Cuantas unidades desea aniaadir del producto */
	    do {
		unidades = Utilidades
			.leerInt("\nCuantas unidades del producto desea? [1-"
				+ shop.consultar_unidades(prod_selec) + "] ");
		// maximo_unidades = shop.consultar_unidades(prod_selec);

		if (unidades <= 0) {
		    Utilidades
			    .imprimirLinea("\n\t[!] Error al introducir unidades, valor fuera de rango [!]");
		    repetir = true;
		} else {

		    try {
			if (unidades > shop.consultar_unidades(prod_selec))
			    throw new VentasException(333);
			shop.modificar_unidades(prod_selec,
				shop.consultar_unidades(prod_selec) - unidades);
			repetir = false;

		    } catch (VentasException e) {
			Utilidades.imprimirLinea("\n\t[*] " + e.getMessage()
				+ " [*]");
			repetir = true;
		    }

		}
	    } while (repetir);

	    if (unidades > 0) {
		shop.facturar(prod_selec, unidades);
		Utilidades
			.imprimirLinea("\n\t[*] Unidades agregadas satisfactoriamente [*]\n");
		Utilidades.imprimirLinea("\n\t[*] Producto agregado [*]\n");
	    }
	}

    }
}
