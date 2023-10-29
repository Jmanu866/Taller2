import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.princeton.cs.stdlib.In;
import edu.princeton.cs.stdlib.Out;


public class SistemaImpl implements Sistema {


    List<Vehiculo> ListaV = new ArrayList<>(); //Lista para Vehiculos
    List<Cliente> ListaC = new ArrayList<>(); // Lista para Clientes

    List<Arriendo> ListaA = new ArrayList<>(); //Lista para Arriendos

    /**
     * Metodo que realiza la lectura de archivo de Vehiculos.txt
     */
    public void LeerArchivoVehiculo() {

        In in = new In("Vehiculos.txt");

        while (in.hasNextLine()) {
            String linea = in.readLine();

            String[] campos = linea.split(",");


            if (campos.length >= 4){
            String Patente = campos[0];
            int NumeroR = Integer.parseInt(campos[1]);
            String TVehiculo = campos[2];
            int NumeroA = Integer.parseInt(campos[3]);
            String Estado = campos[4];

            Vehiculo v = new Vehiculo(Patente, NumeroR, TVehiculo, NumeroA, Estado);
            ListaV.add(v);}
            else {
                continue;
            }
        }



    }



    /**
     * Metodo que realiza la lectura de archivo de clientes.txt
     */
    public void LeerArchivoCliente() {

        In in = new In("clientes.txt");

        while (in.hasNextLine()) {
            String linea = in.readLine();

            String[] campos = linea.split(",");


            String Rut = campos[0];
            String nombre = campos[1];
            String apellido = campos[2];
            String FNacimiento = campos[3];
            String TLiciencia = campos[4];

            Cliente c = new Cliente(Rut, nombre, apellido, FNacimiento, TLiciencia);

            ListaC.add(c);}

        }




    /**
     * Metodo que realiza la lectura de archivo de Arriendos.txt
     */

    public void LeerArchivoArriendo() {

        In in = new In("Arriendos.txt");


        while (in.hasNextLine()) {

            String linea = in.readLine();

            String[] campos = linea.split(",");

            if (campos.length >= 4){
                String Patente = campos[0];
                String Rut = campos[1];
                String FechaI = campos[2];
                String FechaF = campos[3];


                Arriendo a = new Arriendo(Patente, Rut, FechaI, FechaF);
                ListaA.add(a);
            } else {
                continue;
            }



        }

    }


    /**
     * Menu del Sistema
     */
    public void menu() {
        Scanner s = new Scanner(System.in);
        ImprimirMenu();
        String opcion = s.nextLine();

        while (!opcion.equals("6")) {

            switch (opcion) {
                case "1" -> iNuevoCliente();
                case "2" -> IngresarNuevoVehiculo();
                case "3" -> RArriendo();
                case "4" -> GDevolucion();
                case "5" -> DEstadisticas();
                default -> System.out.println("ERROR INGRESE UNA DE LAS OPCIONES CORRRECTAS");
            }
            ImprimirMenu();
            opcion = s.nextLine();
        }

        System.out.println("**********************************");
        System.out.println("Saliendo y Guardando el Sistema");
        System.out.println("GRACIAS POR USAR EL SISTEMA RENTA2");
        System.out.println("**********************************");

    }

    /**
     * Metood que imprime el menu
     */
    public void ImprimirMenu() {
        System.out.println("**********************************");
        System.out.println("\tSistema de Arriendos Rentados ");
        System.out.println("[1] Ingresar Cliente ");
        System.out.println("[2] Ingresar Vehiculo ");
        System.out.println("[3] Registrar Arriendo ");
        System.out.println("[4] Gestionar Devolucion ");
        System.out.println("[5] Estadisticas ");
        System.out.println("[6] Salir y Guardar ");

        System.out.println("**********************************");

    }

    /**
     * Metodo de Ingresar Nuevo Cliente
     */

    public void iNuevoCliente() {

        System.out.println("**********************************");
        System.out.println("\t INGRESAR NUEVO CLIENTE");


        String rut;
        String TipoLicencia;
        String FechaNacimiento;

        rut = ValidRut();


        if (rut == null) {
            System.out.println("ERROR NO ES EL FORMATO QUE SE PIDE");
        } else {

            if (VerificarRutNoEnListaC(ListaC, rut) == null) {
                System.out.println("ERROR RUT SE ENCUENTRA EN EL SISTEMA");
            } else {

                Scanner S = new Scanner(System.in);

                System.out.print("Ingrese Nombre: ");
                String nombre = S.nextLine();

                System.out.print("Ingrese Apellido:");
                String apellido = S.nextLine();

                System.out.println("Ingrese Fecha Nacimiento de la forma");
                FechaNacimiento = ValidFecha();

                if (FechaNacimiento == null) {
                    System.out.println("ERROR INGRESE EL TIPO DE FECHA CORRECTA");
                } else {

                    TipoLicencia = ValidLicencias();

                    if (TipoLicencia == null) {
                        System.out.println("ERROR INGRESE UN TIPO DE LICENCIA CORRECTA");
                    } else {
                        Cliente C = new Cliente(rut, nombre, apellido, FechaNacimiento, TipoLicencia);
                        ListaC.add(C);


                    }
                }
            }
        }


    }


    /**
     * Metodo para validar el Rut
     */
    public String ValidRut() {

        try {

            System.out.print("Ingrese Rut (forma XX.XXX.XXX-X):");
            Scanner R = new Scanner(System.in);
            String rut = R.nextLine();

            Pattern pattern = Pattern.compile("[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9kK]"); //PATRON
            Matcher placaMatcher = pattern.matcher(rut); // COMPRUEBA
            boolean PlacaSI = placaMatcher.find(); // ES O NO ES

            if (PlacaSI) {
                return rut;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metood para validar fecha
     */

    public String ValidFecha() {

        try {
            System.out.print("(dd/mm/yyyy) :");
            Scanner R = new Scanner(System.in);
            String Fecha = R.nextLine();

            Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9][0-9][0-9][0-9]"); //PATRON
            Matcher placaMatcher = pattern.matcher(Fecha); // COMPRUEBA
            boolean PlacaSI = placaMatcher.find(); // ES O NO ES

            if (PlacaSI) {
                return Fecha;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * metodo para validar licencias
     */
    private String ValidLicencias() {


        String[] Lista = {"B", "C", "A1", "A2", "A3"};


        boolean estaEnLaArray = false;


        try {
            System.out.println("TIPOS DE LICENCIA B - C - A1 - A2 - A3 ");
            System.out.print("Ingrese su Tipo de Licencia:");

            Scanner R = new Scanner(System.in);

            String TipoLicencia = R.nextLine().toUpperCase();

            for (String elemento : Lista) {
                if (elemento.equals(TipoLicencia)) {
                    estaEnLaArray = true;
                    break;
                }
            }

            if (estaEnLaArray) {
                return TipoLicencia;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    /**
     *  Metodo para ingresar un nuevo Vehiculo
     */


    public void IngresarNuevoVehiculo() {

        System.out.println("**********************************");
        System.out.println("\t INGRESAR NUEVO VEHICULO");

        String Patente;
        int NumeroRuedas;
        int NumeroA;
        String Estado;

        Patente = ValidPatente();

        if (Patente == null) {
            System.out.println("ERROR NO ES EL FORMATO QUE SE PIDE");
        } else {
            VerificarPatenteEnListaV(ListaV, Patente);
            if (VerificarPatenteNOEnListaV(ListaV, Patente) == null) {
                System.out.println("LA PATENTE SE ENCUENTRA REGISTRADA");
            } else {
                System.out.print("Ingrese Numero de Ruedas:");
                NumeroRuedas = ValidInt();
                if (NumeroRuedas == -1) {
                    System.out.println("ERROR LAS RUEDAS NO PUEDEN SER NEGATIVAS/CERO O LETRAS");
                } else {
                    Scanner S = new Scanner(System.in);

                    System.out.print("Ingrese Tipo de Vehiculo:");
                    String TVehiculo = S.nextLine();
                    System.out.print("Ingrese Numero de Asientos:");
                    NumeroA = ValidInt();
                    if (NumeroA == -1) {
                        System.out.println("ERROR LAS RUEDAS NO PUEDEN SER NEGATIVAS/CERO O LETRAS");
                    } else {
                        Estado = ValidEstado();
                        if (Estado == null) {
                            System.out.println("ERROR DEBES DECIR USADO O NUEVO");
                        } else {
                            Vehiculo V = new Vehiculo(Patente, NumeroRuedas, TVehiculo, NumeroA, Estado);
                            System.out.println("AUTO INGRESADO CORRECTAMENTE");
                            ListaV.add(V);
                        }
                    }
                }
            }
        }


    }


    /**
     * Metodo para validar una patente
     */


    public String ValidPatente() {
        try {
            System.out.print("Ingrese patente (Forma XXXX-NN) :");
            Scanner R = new Scanner(System.in);
            String Patente = R.nextLine().toUpperCase();


            Pattern pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]-[0-9][0-9]"); //PATRON
            Matcher placaMatcher = pattern.matcher(Patente); // COMPRUEBA
            boolean PlacaSI = placaMatcher.find(); // ES O NO ES

            if (PlacaSI) {
                return Patente;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo para validar un int
     *
     */
    public int ValidInt() {
        try {
            Scanner R = new Scanner(System.in);
            int Valid = R.nextInt();

            if (Valid <= 0) {
                return -1;
            } else {
                return Valid;
            }
        } catch (Exception e) {
            return -1;
        }
    }


    /**
     * Metodo para validar un Estado (Nuevo o usado)
     *
     */

    public String ValidEstado() {
        System.out.print("Ingrese Estado del Vehiculo (USADO O NUEVO) :");
        Scanner R = new Scanner(System.in);
        String Valid = R.nextLine().toUpperCase();
        if (Valid.equals("NUEVO") || Valid.equals("USADO")) {
            return Valid;
        } else {
            return null;
        }
    }

    /**
     * Metodo que Imprime los Vehiculos de la lista vehiculos
     * @param Vehiculos se usa la listaV que vendria siendo lista Vehiculos en el sistema
     * @return returna true o  false para ver si esque hay o no una lista activa
     */

    public boolean ImprimirVehiculo(List<Vehiculo> Vehiculos){
        if(Vehiculos.isEmpty()){

            return false;
        } else {
            System.out.println("**********************************");
            System.out.println("\t VEHICULOS EN SISTEMA: ");
            for (Vehiculo vehiculos : Vehiculos) {

                System.out.println("Vehiculo: " + vehiculos.getPatente() );
                System.out.println("Tipo: " + vehiculos.getTVehiculo() );
                System.out.println("Estado: " + vehiculos.getEstado() );
                System.out.println();


            }
            return true;
        }
    }

    /**
     * Metodo que imprime la  Lista  de los Arriendos activos
     * @param Arriendos se usa la listaA que vendria siendo lista Arriendos
     * @return falso o true en caso de ser vacia o termina de completar los arriendos activos
     */

    public boolean imprimirArriendos(List<Arriendo> Arriendos) {
        if (Arriendos.isEmpty()) {
            return false;
        } else {
            System.out.println("**********************************");
            System.out.println("\t ARRIENDOS ACTIVOS: ");



            for (Arriendo v : Arriendos) {

                LocalDate FechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String fechaTerminoSt = v.getFechaTermino();
                LocalDate fechaTermino = LocalDate.parse(fechaTerminoSt, formatter);

                if(fechaTermino.isAfter((FechaActual))){

                    System.out.println("Patente: " + v.getPatente());
                    System.out.println("Fecha de Inicio: " + v.getFechaInicio());
                    System.out.println("Fecha de Termino: " + v.getFechaTermino());
                    System.out.println();

                } else {
                    System.out.println();}
            }
            return true;
        }
    }

    /**
     * Metodo que verifica si una patente se encuentra en la Lista Vehiculos
     * @param ListaV  = Lista vehiculos
     * @param patente = la patente pedida por consola
     * @return = retorna patente al encontrarla o retorna null en caso de no encotrar una patente
     */
    public String VerificarPatenteEnListaV(List<Vehiculo> ListaV, String patente) {
        for (Vehiculo vehiculo : ListaV) {
            if (vehiculo.getPatente().equals(patente)) {
                return patente; // La patente se encuentra en la lista
            }
        }
        return null;
    }

    /**
     * Metodo que verifica que NO se encuentra una patente y retorna la misma patente
     * @param ListaV Lista vehiculos
     * @param patente = la patente pedida por consola
     * @return retorna null en caso de encontrar una y retorna patente en caso de no encontrarla
     */

    public String VerificarPatenteNOEnListaV(List<Vehiculo> ListaV, String patente) {
        for (Vehiculo vehiculo : ListaV) {
            if (vehiculo.getPatente().equals(patente)) {
                return null; // La patente se encuentra en la lista
            }
        }
        return patente;
    }

    /**
     *  Metodo que verifica si un rut se encuentra en la lista clientes
     * @param ListaC = Lista Clientes
     * @param rut = rut pedido por consola
     * @return retorna null en caso de no encontrar un rut y retorna rut en caso de encontrarla
     */




    public String VerificarRutEnListaC(List<Cliente> ListaC, String rut) {
        for (Cliente cliente : ListaC) {
            if (cliente.getRUT().equals(rut)) {
                return rut; // el rut se encuentra en la lista
            }
        }
        return null; // no se encuentra
    }


    /**
     * Metodo que verifica si un rut NO se encuentra en la lista Clientes
     * @param ListaC = Lista Clientes
     * @param rut = rut pedido por consola
     * @return retorna null en caso de encontrarla y retorna rut en caso de NO encontrarla
     */



    public String VerificarRutNoEnListaC(List<Cliente> ListaC, String rut) {
        for (Cliente cliente : ListaC) {
            if (cliente.getRUT().equals(rut)) {
                return null; //
            }
        }
        return rut;
    }


    /**
     * Metodo que verifica si una fecha se encuentra activa pidiendo una patente en la lista arriendos
     * @param ListaA = lista arriendo
     * @param patente patente pedido por consola
     * @return null en caso de no encontrar y retorna
     */


    public String VerificarFecha(List<Arriendo> ListaA, String patente){

        LocalDate FechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de fecha adecuado, ajusta esto a tu formato real.

        for (Arriendo arriendo : ListaA) {
            if (arriendo.getPatente().equals(patente)){
                String fechaTerminoStr = arriendo.getFechaTermino();
                LocalDate fechaTermino = LocalDate.parse(fechaTerminoStr, formatter);
                if(fechaTermino.isBefore((FechaActual))){
                    System.out.println(fechaTerminoStr);
                    return  patente;
                } if (fechaTermino.isAfter(FechaActual)){
                    return  null;
                } else {
                    return patente;
                }

            } else {
                return patente;
            }
        }
        return patente;
    }

    /**
     * Metodo que verifica si un rut NO se encuentra en la lista Arriendos
     * @param ListaA = Lista Arriendos
     * @param rut rut pedido por cosola
     * @return null en caso de que este y return rut en caso no este en la lista Arriendos
     */


    public String VerificarNoRutEnListaA(List<Arriendo> ListaA, String rut) {
        for (Arriendo arriendo : ListaA) {
            if (arriendo.getRUT().equals(rut)) {
                return null ; // si esque esta
            } else {
                return rut;
            }
        }
        return rut;
    }

    /**
     * Metodo que verifica si un rut SI se encuentra en la Lista Arriendos
     * @param ListaA = Lista Arriendos
     * @param rut rut pedido por consola
     * @return retorna null en caso de no encontrar un rut y retorna rut en caso de encontrarlo en la lista
     */

    public String VerificarSiRutEnListaA(List<Arriendo> ListaA, String rut) {
        for (Arriendo arriendo : ListaA) {
            if (arriendo.getRUT().equals(rut)) {
                return rut ; // si esque esta
            }
        }
        return null; // si esque no esta
    }

    /**
     * Metodo para registrar un arriendo
     */

    public void RArriendo() {
        System.out.println("**********************************");
        System.out.println("\t REGISTRAR ARRIENDO:");

        String patente;
        String rut;
        LocalDate FechaActual = LocalDate.now();
        String FechaInicioS = FechaActual.toString();
        int Dias;


        if (!ImprimirVehiculo(ListaV)) {
            System.out.println("NO SE ENCUENTRAN VEHICULOS PARA ARRENDAR ");
        } else {
            patente = ValidPatente();
            if (patente == null) {
                System.out.println("ERROR NO ES EL FORMATO QUE SE PIDE");
            } else {
                VerificarPatenteEnListaV(ListaV, patente);
                if (VerificarPatenteEnListaV(ListaV, patente) == null) {
                    System.out.println("NO SE ENCUENTRA EN LA LISTA DE VEHICULOS");
                } else {
                    VerificarFecha(ListaA, patente);
                    if (VerificarFecha(ListaA, patente) == null) {
                        System.out.println("ERROR LA PATENTE SE ENCUENTRA EN EL SISTEMA DE ARRIENDOS");
                    } else {
                        rut = ValidRut();
                        if (rut == null) {
                            System.out.println("ERROR NO ES EL FORMATO QUE SE PIDE");
                        } else {
                            VerificarRutEnListaC(ListaC, rut);
                            if (VerificarRutEnListaC(ListaC, rut) == null) {
                                System.out.println("NO SE ENCUENTRA EN LA LISTA DE CLIENTES");
                            } else {
                                rut = VerificarNoRutEnListaA(ListaA,rut);
                                if (VerificarNoRutEnListaA(ListaA, rut) == null) {
                                    System.out.println("ERROR RUT SE ENCUENTRA CON UN ARRIENDO ACTIVO O PENDIENTE");
                                } else {
                                    System.out.println("Ingrese la cantidad de dias que arrendara el vehiculo");
                                    Dias = ValidInt();
                                    if (Dias == -1) {
                                        System.out.println("ERROR LAS RUEDAS NO PUEDEN SER NEGATIVAS/CERO O LETRAS");
                                    } else {
                                        LocalDate FechaTermino = FechaActual.plusDays(Dias);
                                        System.out.println("USTED ARRENDARA EL AUTO HASTA " + FechaTermino);
                                        System.out.println("AÑO - MES - DIA ");
                                        String FechaTerminoS = FechaTermino.toString();
                                        Arriendo arriendo = new Arriendo(patente, rut , FechaInicioS, FechaTerminoS);
                                        ListaA.add(arriendo);
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }
        }


    /**
     * Metodo para gestionar un devolucion
     */
    public void GDevolucion() {

        if(!imprimirArriendos(ListaA)){
            System.out.println("NO SE ENCUENTRAN AUTOS ARRENDADOS");
        } else {
            System.out.println("INGRSE SU RUT PARA HACER VALIDO SU DEVOLUCION DE VEHICULO");
            String rut = ValidRut();

            if (rut == null) {
                System.out.println("ERROR INGRESE UN RUT VALIDO");
            } else {
                VerificarSiRutEnListaA(ListaA, rut);
                if (VerificarSiRutEnListaA(ListaA, rut) == null) {
                    System.out.println("ERROR  NO SE ENCUENTRA EL RUT EN EL ARCHIVO");
                } else {
                    EliminarDeLaLista(ListaA,rut);

                }

            }
        }
    }

    /**
     * Metodo que elimina los elementos de la lista arriendos para devolver un arriendo
     * @param ListaA lista arriendo
     * @param rut rut pedido por consola
     */

    public void EliminarDeLaLista(List<Arriendo> ListaA, String rut){


        for (Arriendo arriendo : ListaA){
            if(arriendo.getRUT().equals(rut)){
                ListaA.remove(arriendo);
                System.out.println("SU ARRIENDO FUE DEVUELTO");
                break;
            }else {
                System.out.println("NO se encontro un Arriendo en este rut");
            }
        }



    }


    /**
     * Metodo que Imprime el Menu de Estdistiicas
     */


    private void ImprimirMenuEstadisticas(){
        System.out.println("**********************************");
        System.out.println("DESPLEGANDO ESTADISTICAS");
        System.out.println("[1] Vehiculo con mas arriendos ");
        System.out.println("[2] Porcentaje de vehiculos arrendados y no devueltos segun el total ");
        System.out.println("[3] Cantidad de Arriendos durante el mes Ingresado.");
        System.out.println("[4] Cantidad de vehiculos arrendados los ultimos 30 dias");
        System.out.println("[5] Volver");
    }

    /**
     * Metodo que muestra las opciones de estadisticas
     */

    public void DEstadisticas() {
        Scanner s = new Scanner(System.in);
        ImprimirMenuEstadisticas();
        String opcion = s.nextLine();

        while (!opcion.equals("5"))
        { switch (opcion) {
            case "1" -> vehiculosMasArriendos(); //TODO FALTA
            case "2" -> porcentajeDeArriendosNoDevueltos(ListaA,ListaV);
            case "3" -> cantidadDeArriendoMes(ListaA);
            case "4" -> cantidadDeVehiculos30D(ListaA);
            default -> System.out.println("ERROR INGRESE UNA DE LAS OPCIONES CORRRECTAS") ;

        }
            ImprimirMenuEstadisticas();
            opcion = s.nextLine();

        }
        System.out.println("VOLVIENDO AL MENU PRINCIPAL");


    }

    /**
     * Metodo con vehiculo con mas arriendo
     */

    public void vehiculosMasArriendos(){



        System.out.println("PRONTO NUEVA ACTUALIZACION :D");

    }

    /**
     * metodo que muestra los porcentajes de arriendo no devueltos
     */
    public void porcentajeDeArriendosNoDevueltos(List<Arriendo> ListaA, List<Vehiculo> ListaV){
        float contadorDeArriendos = 0;
        float contadorDeNoArriendos = 0;
        float totalDeArriendos = 0;

        LocalDate FechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");




            for (Arriendo arriendo : ListaA) {
                String fechaTermino  = arriendo.getFechaTermino();
                LocalDate fechaTerminoS = LocalDate.parse(fechaTermino,formatter);

                if (fechaTerminoS.isBefore(FechaActual)) {
                    contadorDeNoArriendos++;
                    totalDeArriendos++;
                } else {
                    contadorDeArriendos++;
                    totalDeArriendos++;
                }

            }
            float PorcentajeNoArriendos =  (contadorDeNoArriendos / totalDeArriendos) * (100) ;
            float PorcentajeArriendos = (contadorDeArriendos/totalDeArriendos) * (100); // calcular porcentaje

            System.out.println("EL porcentaje de arriendos :" + PorcentajeArriendos + "%" );
            System.out.println("El porcentaje de arriendos NO devueltos :" + PorcentajeNoArriendos + "%" );


    }

    /**
     * Metodo que imprime la cantidad de arriendos por mes
     */
    public void cantidadDeArriendoMes(List<Arriendo> ListaA) {
        System.out.print("Ingrese el mes para desplegar mas info");
        int Contador = 0;
        int Mes = ValidInt();
        if (Mes == -1) {
            System.out.println("ERROR EL MES NO PUEDEN SER NEGATIVAS/CERO/MAYOR A 12 O LETRAS");
        }
        if (Mes >= 1 && Mes <= 12) {
            for (Arriendo arriendo : ListaA) {

                LocalDate fechaInicio = LocalDate.parse(arriendo.getFechaInicio());


                if (fechaInicio.getMonthValue() == Mes) {
                    Contador++;
                }
            }
            System.out.println("Arriendo en el mes " + Mes + ": " + Contador);

        } else {
            System.out.println("ERRO INGRESE UN MES VALIDO");
        }


    }

    /**
     *  metodo que imprime la cantidad de vehiculos los ultimos 30 dias
     */
    public void cantidadDeVehiculos30D(List<Arriendo> ListaA){

        LocalDate fechaActual = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fecha30DiasAtras = LocalDate.now().minusDays(30);


        int contadorDeVehiculos = 0;

        if(ListaA.isEmpty()){
            System.out.println("NO HAY ARRIENDOS ACTIVOS");
        } else {
            for ( Arriendo arriendo : ListaA){
                String fechaTermino  = arriendo.getFechaTermino();
                LocalDate fechaTerminoS = LocalDate.parse(fechaTermino,formatter);
                if (fechaTerminoS.isAfter(fecha30DiasAtras)){
                    if(fechaTerminoS.isBefore(fechaActual)){
                        contadorDeVehiculos++;
                    }

                }

            }

            System.out.println("LA cantidad de vehiculos arrendados los ultimos 30 dias son " + contadorDeVehiculos );

        }








    }


   public  void GArchivoVehiculo(){
        Out out = new Out("Vehiculos.txt");

       for (Vehiculo vehiculo : ListaV) {

           String ListaVehiculos = vehiculo.getPatente() + "," + vehiculo.getNumeroRuedas() + "," + vehiculo.getTVehiculo() + "," + vehiculo.getNumeroAsientos() + "," + vehiculo.getEstado();

           out.println(ListaVehiculos);


       }
   }

   public void GArchivoCliente(){
       Out out = new Out("clientes.txt");

       for (Cliente cliente : ListaC){

           String ListaCliente = cliente.getRUT() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getFechaNacimiento() + "," + cliente.getTLicencia();

           out.println(ListaCliente);

       }
   }

    public void GArchivoArriendos(){
        Out out = new Out("Arriendos.txt");

        for (Arriendo arriendo : ListaA){
            String ListaArriendo = arriendo.getPatente() + "," + arriendo.getRUT() + "," +  arriendo.getFechaInicio() + "," + arriendo.getFechaTermino() ;

            out.println(ListaArriendo);
        }
    }





























    }














