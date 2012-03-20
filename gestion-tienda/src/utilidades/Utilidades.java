package utilidades;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase de utilidades con las funciones mas usadas habitualmente.
 * <ol>
 * <li>Devolver en forma de String valores de un vector de enteros.</li>
 * <li>Comparacion de cadenas.</li>
 * <li>Generar numeros enteros de forma aleatoria.</li>
 * <li>Guardar coincidencias de dos vectores dados.</li>
 * <li>Imprimir por pantalla una cadena de texto.</li>
 * <li>Imprimir por pantalla una cadena de texto + \n</li>
 * <li>Lectura de cadenas de texto desde teclado.</li>
 * <li>Lectura de numeros Double, con control de errores.</li>
 * <li>Lectura de numeros enteros, con control de errores.</li>
 * <li>Verificar valores repetidos.</li>
 * <li>Evaluar respuesta de tipo "SI/NO".</li>
 * </ol>
 * <br>
 * 
 * @author Sergio Fernandez Garcia <br>
 *         Ultima modificacion: 13/12/2011
 * 
 */

public class Utilidades {
    /**
     * Genera y devuelve un String que separa por comas cada terrmino del array
     * de enteros.
     * 
     * @param listado
     *            Vector de numeros enteros.
     * 
     * @return String
     * 
     */
    public static String cadenaResultados(Integer[] listado) {
	String msg = null;
	msg = listado[0].toString();
	int i = 1;
	while (i < listado.length) {
	    msg += ", " + listado[i].toString();
	    i++;
	}
	msg += ". ";

	return msg;
    }

    /**
     * Compara la primera cadena del vector con el resto de cadenas. <br>
     * <n>Devuelve:</n>
     * <ul>
     * <li><b>True:</b> Si alguna cadena coincide con la primera cadena.
     * <li><b>False:</b> Si no hay coincidencias con la primera cadena.
     * </ul>
     * 
     * No distingue entre mayusculas y minusculas.
     * 
     * @param listado
     *            Vector de tipo String.
     * @return Boolean
     * 
     */
    public static boolean comparaCadenas(String[] listado) {
	boolean correcto = false;

	for (int i = 1; i < listado.length && !correcto; i++) {
	    if (listado[0].compareToIgnoreCase(listado[i]) == 0)
		correcto = true;
	}

	return correcto;
    }

    /**
     * Genera una lista de numeros enteros aleatorios. <br>
     * Se ha de introducir:
     * <ul>
     * <li>La cantidad de numeros generados (total).</li>
     * <li>El rango que en el que ha de estar incluido el numero generado
     * (minimo, maximo)</li>
     * </ul>
     * 
     * @param total
     *            cantidad de numeros que seran generados.
     * @param minimo
     *            valor minimo del rango en el que se generan los numeros.
     * @param maximo
     *            valor maximo del rango en el que se generan los numeros.
     * 
     * @return Integer[]
     */

    public static Integer[] generarAleatorios(int total, int minimo, int maximo) {
	Integer[] lista = new Integer[total];
	for (int i = 0; i < total; i++) {
	    lista[i] = (int) Math.round((Math.random() * (maximo - minimo))
		    + minimo);
	}

	return lista;
    }

    /**
     * Funcion que compara dos vectores de valores enteros.
     * <ul>
     * <li>La comparacion es de todos los valores.</li>
     * <li>No descrimina por tener distinta posicion en los vectores.</li>
     * </ul>
     * <br>
     * Devuelve un vector con los valores repetidos.
     * 
     * @param listado1
     *            Vector de numeros enteros.
     * 
     * @param listado2
     *            Vector de numeros enteros.
     * 
     * 
     * @return Integer[]
     * 
     */

    public static Integer[] guardarCoincidencias(Integer[] listado1,
	    Integer[] listado2) {

	Integer[] coincidencia = null;
	if (listado1 != null) {
	    coincidencia = new Integer[listado1.length];
	    int k = 0;

	    for (int i = 0; i < listado1.length; i++) {

		for (int j = 0; j < listado2.length; j++) {

		    if (listado1[i] == listado2[j]) {
			coincidencia[k] = listado1[i];
			k++;
		    }
		}
	    }
	}

	return coincidencia;
    }

    /**
     * Imprime una cadena de texto en la pantalla.
     * 
     * @param cad
     *            Cadena de texto para ser imprimida en pantalla.
     * 
     * @return Void
     * 
     */
    public static void imprimir(String cad) {
	System.out.print(cad);
    }

    /**
     * Imprime una cadena de texto en la pantalla y hace salto de l�nea.
     * 
     * @param cad
     *            Cadena de texto para ser imprimida en pantalla.
     * 
     * @return Void
     * 
     */
    public static void imprimirLinea(String sCadena) {
	System.out.println(sCadena);
    }

    /**
     * Lectura de cadenas de texto (Strings)
     * 
     * @return String
     */
    public static String lectura() {
	Scanner leer = new Scanner(System.in);
	leer.useLocale(Locale.US);

	return (leer.nextLine());
    }

    /**
     * Lectura de numeros tipo Double,con control de errores.<br>
     * Muestra una cadena de texto antes de solicitar el dato.<br>
     * Si ocurre algun error en la lectura del dato se informa al usuario y se
     * vuelve a solicitar.
     * 
     * @param cad
     *            Cadena de texto que sera mostrada cuando se solicite la
     *            lectura de datos.
     * 
     * @return Double
     */
    public static double leerDouble(String cad) {
	Scanner leer = new Scanner(System.in);
	leer.useLocale(Locale.US);
	double num = 0.0;
	boolean b = false;

	do {
	    try {
		imprimir(cad);
		leer = new Scanner(System.in);
		num = leer.nextDouble();
		b = false;
	    } catch (InputMismatchException e) {
		imprimirLinea("\n  [!] No ha introducido un numero real o esta fuera de rango. [!]\n");
		b = true;
	    } catch (NoSuchElementException e) {
		imprimirLinea("\n  [!] No se ha podido recoger el dato. [!]\n");
		b = true;
	    } catch (IllegalStateException e) {
		imprimirLinea("\n [!] No se ha podido leer el dato. Avise a su administrador. [!]\n");
		b = true;
	    }
	} while (b);

	return num;
    }

    /**
     * Lectura de numeros tipo int,con control de errores.<br>
     * Muestra una cadena de texto antes de solicitar el dato.<br>
     * Si ocurre algun error en la lectura del dato se informa al usuario y se
     * vuelve a solicitar.
     * 
     * @param cad
     *            Cadena de texto que sera mostrada cuando se solicite la
     *            lectura de datos.
     * 
     * @return int
     */
    public static int leerInt(String cad) {
	Scanner leer = new Scanner(System.in);
	leer.useLocale(Locale.US);
	int num = 0;
	boolean b = false;
	do {
	    try {
		imprimir(cad);
		leer = new Scanner(System.in);
		num = leer.nextInt();
		b = false;
	    } catch (InputMismatchException e) {
		imprimirLinea("\n  [!] No ha introducido un numero entero o esta fuera de rango. [!]\n");
		b = true;
	    } catch (NoSuchElementException e) {
		imprimirLinea("\n  [!] No se ha podido recoger el dato. [!]\n");
		b = true;
	    } catch (IllegalStateException e) {
		imprimirLinea("\n  [!] No se ha podido leer el dato. Avise a su administrador. [!]\n");
		b = true;
	    }
	} while (b);

	return num;
    }

    /**
     * Compara todos los valores del array de tipo Integer. <br>
     * <n>Devuelve:</n>
     * <ul>
     * <li><b>True:</b> Si hay algun valor repetido.
     * <li><b>False:</b> Si no hay valores repetidos.
     * </ul>
     * 
     * @param listado
     *            Vector que contiene los valores a comprobar.
     * 
     * @return Boolean
     */
    public static boolean repetido(Integer[] listado) {

	boolean b = false;

	// Cuando se encuentra por primera vez un valor repetido deja de buscar
	// mas.

	for (int i = 0; i < listado.length - 1 && !b; i++) {

	    for (int j = 1 + i; j < listado.length && !b; j++) {
		if (listado[i] == listado[j])
		    b = true;
	    }

	}
	return b;
    }

    /**
     * Funcion creada para las preguntas con respuesta "SI/NO". <n>Devuelve:</n>
     * <ul>
     * <li><b>True:</b> Si se ha introducido la particula "SI" en alguna de sus
     * combinaciones.
     * <li><b>False:</b> Si no se ha introducido el valor deseado.
     * </ul>
     * 
     * @param respuesta
     *            Cadena que compara con "SI".
     * 
     * @return Boolean
     * 
     */
    public static boolean respSiNO(String respuesta) {
	boolean b = false;
	if (respuesta.compareToIgnoreCase("SI") == 0)
	    b = true;
	return b;
    }
}
