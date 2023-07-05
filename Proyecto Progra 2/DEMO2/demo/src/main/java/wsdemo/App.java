package wsdemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.jsonwebtoken.io.IOException;

public class App {
    public static final String ACCOUNT_SID = "ACc3729b481b8ae398ece611c12eb51909";
    public static final String AUTH_TOKEN = "30b5a9bb3ca70945b72b88d6e802c671";

    public static void main(String[] args) throws java.io.IOException, InterruptedException {
        // Establece la comunicación con el Arduino
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
                System.out.println("Conexión establecida con éxito.");
            } else {
                System.out.println("No se pudo abrir el puerto del Arduino.");
                return;
            }
        } else {
            System.out.println("Arduino no encontrado.");
            return;
        }

        // Envía el comando al Arduino para iniciar la captura de datos
        String comandoInicio = "START";
        
        arduinoPort.writeBytes(comandoInicio.getBytes(), comandoInicio.length());

        // Recibe los datos del Arduino y almacénalos en un archivo
        String nombreArchivo = "DEMO2\\demo\\src\\main\\java\\wsdemo\\datos.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Leer datos del puerto serie del Arduino y escribirlos en el archivo
            InputStream inputStream = arduinoPort.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String linea;
            while (true) {
                if (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                // Escribe los datos en el archivo
                bw.write(linea);
                bw.newLine();
                bw.flush();
                } else {
                    // Espera un tiempo antes de volver a intentar leer datos
                    Thread.sleep(10);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Realiza cualquier limpieza o cierre de la comunicación con el Arduino
        arduinoPort.closePort();

        // Envía un mensaje de WhatsApp usando la API de Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("whatsapp:+593979094232"), // Número de destino de WhatsApp
                new PhoneNumber("whatsapp:+14155238886"), // Número de Twilio
                "Pene al ronny" // Contenido del mensaje
        ).create();

        System.out.println(message.getSid());
    }
}

