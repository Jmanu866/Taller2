/**
 * Clase Vehiculo
 */

public class Vehiculo {

    /**
     * Patente
     */


    private String Patente;


    /**
     * Numero de Ruedas
     */


    private int NumeroRuedas;

    /**
     * Tipo de Vehiculo
     */

    private String TVehiculo;

    /**
     * Numero de Asientos
     */

    private int NumeroAsientos;

    /**
     *  Estado Vehiculo
     */


    private String Estado;

    /**
     * Constructor Vehiculo
     * @param patente
     * @param numeroRuedas
     * @param TVehiculo
     * @param numeroAsientos
     * @param estado
     */

    public Vehiculo(String patente, int numeroRuedas, String TVehiculo, int numeroAsientos, String estado) {
        this.Patente = patente;
        this.NumeroRuedas = numeroRuedas;
        this.TVehiculo = TVehiculo;
        this.NumeroAsientos = numeroAsientos;
        this.Estado = estado;
    }

    /**
     * Get Patente
     * @return
     */

    public String getPatente() {
        return Patente;
    }

    /**
     * Get Numero de Ruedas
     * @return
     */
    public int getNumeroRuedas() {
        return NumeroRuedas;
    }

    /**
     * Get Tipo de Vehiculo
     * @return
     */

    public String getTVehiculo() {
        return TVehiculo;
    }

    /**
     *  Get Numero de Asientos
     * @return
     */

    public int getNumeroAsientos() {
        return NumeroAsientos;
    }

    /**
     * Get Estado
     * @return
     */

    public String getEstado() {
        return Estado;
    }
}
