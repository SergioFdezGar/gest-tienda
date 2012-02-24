package gestionEmpleado;
/**
 * 
 */

/**
 * @author v4lm0nt
 *
 */
public class Empleado {
	
	private int cod_acceso;
	private String nombre;
	private String password;
	
	public Empleado(int id, String nom, String pass){
		cod_acceso=id;
		nombre=nom;
		password=pass;
	}

	/**
	 * @return the cod_acceso
	 */
	public int getCod_acceso() {
		return cod_acceso;
	}

	/**
	 * @param acceso the cod_acceso to set
	 */
	public void setCod_acceso(int acceso) {
		this.cod_acceso = acceso;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
