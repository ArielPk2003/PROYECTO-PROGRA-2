package wsdemo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class ApiWs {
    
    public static final String ACCOUNT_SID = "ACc3729b481b8ae398ece611c12eb51909";
    public static final String AUTH_TOKEN = "4d2331995b93b5b3fbefbdb02a4f96a6";
    public static int numeroMensaje = 0;


    public static int numeroDecision () {
        porcentajeDeCambio();
        String mensaje;
        switch (numeroMensaje) {
            case 1:
                mensaje = "La planta esta con un nivel bueno de agua :D ";
                break;
            case 2:
                mensaje = "Aun hay agua pero debes estar pendiente .-. ";
                break;
            case 3:
                mensaje = "Es necesario llenar de agua tu planta D:";
                break;
            default:
                mensaje = "Número no válido";
                break;
        }
        // Envía el mensaje con Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message enviarMensaje = Message.creator(
                new PhoneNumber("whatsapp:+593979094232"), // Número a quien va dirigido el mensaje
                new PhoneNumber("whatsapp:+14155238886"),  // Número de Twilio
                mensaje).create();

        System.out.println(enviarMensaje.getSid());

        return 0;
    }
    public static void porcentajeDeCambio () {
        
        int numeroAleatorio = dataArduino.inicializacion();

        if (numeroAleatorio>= 40) {
            numeroMensaje = 1;
        }else if(numeroAleatorio>=20){
            numeroMensaje = 2;
        }else if (numeroAleatorio>=0) {
            numeroMensaje = 3;
        }   
    }
}