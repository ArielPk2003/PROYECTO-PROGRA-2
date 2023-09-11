package wsdemo.UserInterface.Ventanas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import wsdemo.CreateDatabaseExample;

public class HumedadActual extends JFrame implements ActionListener {
    private JLabel etiquetaFondo4;
    private JLabel valorHumedadActual;
    // Anadir etiquetas de la humedad de la planta con date-time
    private JButton regresar;
    // private Border bordePersonalizado = BorderFactory.createLineBorder(Color.GREEN, 3);

    public HumedadActual() {
        setTitle("Humedad Actual");
        setLayout(null);
        ImageIcon fondoHA = new ImageIcon("prjDemo\\src\\main\\java\\wsdemo\\UserInterface\\Recursos\\nivelActual.jpg");
        
        valorHumedadActual = new JLabel(CreateDatabaseExample.ultimoDato()+"%");
        valorHumedadActual.setBounds(190, 270, 300, 100);
        Font font = new Font("Arial", Font.BOLD, 26);
        valorHumedadActual.setFont(font);
        
        // Establece el fondo del JLabel como transparente
        valorHumedadActual.setOpaque(false);
    
        // Aplicar el borde al Label
        // valorHumedadActual.setBorder(bordePersonalizado);
    
        // Agrega el JLabel encima de la imagen de fondo
        add(valorHumedadActual);

        // Agrega la imagen de fondo primero
        etiquetaFondo4 = new JLabel(fondoHA);
        etiquetaFondo4.setBounds(0, 0, fondoHA.getIconWidth(), fondoHA.getIconHeight());
        add(etiquetaFondo4);
    
    
        regresar = new JButton("<html><font color='#48cf7c'>Regresar</font></html>");
        regresar.setBounds(125, 535, 107, 66);
        regresar.setBackground(Color.WHITE);
        add(regresar);
        regresar.addActionListener(this);
    }
    

    

    public void mostrarVentanaHumedadA(){
        this.setBounds(0, 0, 1210, 710);
        this.setVisible(true);
        this.setLocationRelativeTo(null);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==regresar){
            this.setVisible(false);
            //IR VENTANA OPCIONES
            Opciones men = new Opciones();
            men.mostrarVentanaMenu();
        }
    }
    
}
