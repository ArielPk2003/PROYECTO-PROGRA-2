package wsdemo;

public class TimerExample {

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void temporizador() {
        
        int segundosPorMinuto = 1000;
        long tiempoDeEsperaEnMilisegundos = segundosPorMinuto*3; // 1 segundo
        try {
            while (true) {
                limpiarPantalla();
                // Tu código aquí: coloca lo que quieras que se repita cada minuto.
                System.out.println("Este es un ciclo con delay de 10 segundos");
                
                // Pausa el programa durante 1 minuto
                CreateDatabaseExample.ingresoDatos(dataArduino.inicializacion());
                CreateDatabaseExample.extractorDatos();
                Thread.sleep(tiempoDeEsperaEnMilisegundos);
                ApiWs.numeroDecision();
            }
        } catch (InterruptedException e) {
            // Manejo de excepciones si es necesario
            e.printStackTrace();
        }
    }
}
