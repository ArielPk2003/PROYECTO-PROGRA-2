package wsdemo.UserInterface.Ventanas;
import javax.swing.*;
import java.awt.*;

public class Pantallazo {
    private JWindow pantallazoInicial;
    private static final int ancho = 1210;
    private static final int alto = 710;

    public boolean mostrarPantallazo () {

        pantallazoInicial= new JWindow();
        pantallazoInicial.setSize(ancho, alto);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pantallazoInicial.setLocation(
            ((screenSize.width - ancho) / 2),
            ((screenSize.height - alto) / 2)
        );

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        JLabel label = new JLabel(new ImageIcon("prjDemo\\src\\main\\java\\wsdemo\\UserInterface\\Recursos\\logo.gif"));
        content.add(label, BorderLayout.CENTER);

        pantallazoInicial.getContentPane().add(content);
        pantallazoInicial.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pantallazoInicial.setVisible(false);

        pantallazoInicial.dispose();  
        return true;
    }

}
