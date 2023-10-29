public class Arriendo {

    /**
     * Patente
     */

    private String Patente ;

    /**
     * RUT
     */

    private String RUT;

    /**
     *  Fecha de Inicio de un Arriendo
     */

    private String FechaInicio;

    /**
     *  Fecha de Termino de un Arriendo
     */

    private String FechaTermino;

    /**
     * Constructor de la Clase Arriendo
     * @param patente
     * @param RUT
     * @param fechaInicio
     * @param fechaTermino
     */

    public Arriendo(String patente, String RUT, String fechaInicio, String fechaTermino) {
        Patente = patente;
        this.RUT = RUT;
        FechaInicio = fechaInicio;
        FechaTermino = fechaTermino;
    }


    /**
     * Get Patente
     * @return
     */

    public String getPatente() {
        return Patente;
    }

    /**
     * Get RUT
     * @return
     */

    public String getRUT() {
        return RUT;
    }

    /**
     * Get Fecha Inicio
     * @return
     */

    public String getFechaInicio() {
        return FechaInicio;
    }

    /**
     * Get Fecha Termino
     * @return
     */
    public String getFechaTermino() {
        return FechaTermino;
    }
}
