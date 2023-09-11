package wsdemo;

import java.util.StringTokenizer;
import com.fazecast.jSerialComm.SerialPort;

public class dataArduino {
    public static final String ACCOUNT_SID = "ACc3729b481b8ae398ece611c12eb51909";
    public static final String AUTH_TOKEN = "30b5a9bb3ca70945b72b88d6e802c671";

    public static Integer inicializacion() {
        // Establece la comunicacin con el Arduino
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort arduinoPort = null;

        for (SerialPort port : ports) {
            if (port.getDescriptivePortName().contains("Arduino")) {
                arduinoPort = port;
                break;
            }
        }

        if (arduinoPort != null) {
            if (arduinoPort.openPort()) {
                System.out.println("Conexion establecida con exito.");
            } else {
                System.out.println("No se pudo abrir el puerto del Arduino.");
                return null;
            }
        } else {
            System.out.println("Arduino no encontrado.");
            return null;
        }

        // Enva el comando al Arduino para iniciar la captura de datos
        String comandoInicio = "START";
        arduinoPort.writeBytes(comandoInicio.getBytes(), comandoInicio.length());

        // Variable para almacenar los datos del Arduino
        String datosArduino = "";

        // Recibe los datos del Arduino y almacnalos en la variable
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = arduinoPort.readBytes(buffer, buffer.length)) > 0 || datosArduino.isEmpty()){
            String data = new String(buffer, 0, bytesRead);
            datosArduino += data;

        }
            StringTokenizer tokenizer = new StringTokenizer(datosArduino, ".");
            String tokenAnterior = "";

            while (tokenizer.hasMoreTokens()) {
                String tokenActual = tokenizer.nextToken();

                // Verificamos si ya tenemos un token anterior (no es la primera iteración)
                if (!tokenAnterior.isEmpty()) {
                    // Obtenemos los dos caracteres anteriores al delimitador
                    if(tokenAnterior.length()>=3){
                        datosArduino = tokenAnterior.substring(tokenAnterior.length() - 2);

                    }
                }
                tokenAnterior = tokenActual;
            }

       arduinoPort.closePort();
       int numero = 0;
            try {
                 numero = (int) Double.parseDouble(datosArduino);
            } catch (Exception e) {
                System.out.println("Hubo problema con el dato obtenido ....");
            }
            if (numero>100) {
                numero = numero/10;
            }

        return numero;
    }

}
