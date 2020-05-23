package Cliente;

import Log.Log;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Verificar los parametros recibidos
        String ipAdress;
        int port;
        if (args.length != 2) {
            System.err.println("Los parametros recibidos son incorrectos,"
                    + " se requiere direccion ip y puerto del servidor a conectar");
            return;
        } else {
            ipAdress = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Puerto ingresado no valido");
                return;
            }
        }

        //Iniciar el Log
        try {
            Log.startLog("LogCliente.txt");
        } catch (SecurityException | IOException ex) {
            System.out.println("->TestCliente: no se pudo iniciar el Log");
        }

        //Crear consulta
        String[] horoscopo, fecha;

        //Consulta compleja
        horoscopo = new String[]{"CC", "CC", "GE", "CC", "LE", "VG", "LB", "ES", "SA", "CP", "AC", "PI", "AA", "ABC"};
        fecha = new String[]{"05-05-2020", "05-05-2020", "AA-05-2020", "00-05-2020", "01-33-2020", "-33-2020", "01--2020", "01-05-", "29-02-2019", "32-01-2019", "32-04-2019", "32-AA-2019", "05-05-AAAA", "030-05-2016"};

        //Consulta básica
//        horoscopo = {"CC","CC","CC"};
//        fecha = {"05-05-2020","05-05-2020","05-05-2020"};

        //Crear e iniciar los Clientes
        for (int i = 0; i < fecha.length; i++) {
            Log.logInfo("Cliente " + i, "Se crea el Cliete");
            new Thread(new Cliente("Cliente " + i, horoscopo[i], fecha[i], ipAdress, port)).start();
        }

    }
}
