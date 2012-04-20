package gestionEmpleados;

abstract class Empleado {

    protected int cod_acceso;
    protected String nombre;
    protected String password;
    protected int nivel;
    protected double productividad;

    public Empleado(int id, String nom, String pass, int lvl) {
	this.cod_acceso = id;
	this.nombre = nom;
	this.password = pass;
	this.nivel = lvl;
	this.productividad = 0;
    }

    public Empleado() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the cod_acceso
     */
    public int getCod_acceso() {
	return cod_acceso;
    }

    /**
     * @param acceso
     *            the cod_acceso to set
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
     * @param nombre
     *            the nombre to set
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
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
	return nivel;
    }

    /**
     * @param nivel
     *            the nivel to set
     */
    public void setNivel(int nivel) {
	this.nivel = nivel;
    }

    /**
     * @return the productividad
     */
    public double getProductividad() {
	return productividad;
    }

    /**
     * @param productividad
     *            the productividad to set
     */
    public void setProductividad(double productividad) {
	this.productividad = productividad;
    }

    abstract double calcProductividad(double venta);
}
