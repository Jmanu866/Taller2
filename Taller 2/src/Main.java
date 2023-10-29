
public class Main {
    public static void main(String[] args)  {


        SistemaImpl sistema = new SistemaImpl();

        //LECTURA DE ARCHIVOS

        sistema.LeerArchivoVehiculo();
        sistema.LeerArchivoArriendo();
        sistema.LeerArchivoCliente();


        // MENU
        sistema.menu();


        //GUARDAR ARCHIVOS

        sistema.GArchivoVehiculo();
        sistema.GArchivoCliente();
        sistema.GArchivoArriendos();



    }










}