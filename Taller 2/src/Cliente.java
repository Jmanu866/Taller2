public class Cliente {


    /**
     *  Rut
     */

    private String RUT; //


    /**
     *  Nombre
     */

    private String Nombre; //Nombre


    /**
     *  Apellido
     *
     */
    private String Apellido; // Apellido


    /**
     *  Fecha de Nacimiento
     */

    private String FechaNacimiento;

    /**
     *  Tipo de Licencia
     */

    private String TLicencia;

    /**
     * Contructor de Cliente
     * @param RUT
     * @param nombre
     * @param apellido
     * @param FNacimiento
     * @param TLicencia
     */

    public Cliente(String RUT, String nombre, String apellido, String FNacimiento, String TLicencia) {
        this.RUT = RUT;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.FechaNacimiento = FNacimiento;
        this.TLicencia = TLicencia;
    }

    /**
     * Get rut
     * @return
     */

    public String getRUT() {
        return RUT;
    }

    /**
     * Get Nombre
     * @return
     */

    public String getNombre() {
        return Nombre;
    }

    /**
     * Get Apellido
     * @return
     */

    public String getApellido() {
        return Apellido;
    }

    /**
     * Get FechaNacimiento
     * @return
     */

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }


    /**
     *  Get Tipo Licencia
     * @return
     */


    public String getTLicencia() {
        return TLicencia;
    }

















}



