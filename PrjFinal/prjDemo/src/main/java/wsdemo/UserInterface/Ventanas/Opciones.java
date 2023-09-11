package wsdemo.UserInterface.Ventanas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import wsdemo.CreateDatabaseExample;
import wsdemo.dataArduino;

public class Opciones extends JFrame implements ActionListener {
    private JLabel etiquetaFondo3;
    private JButton humedadActual, humedadHistorial;

    public Opciones(){
        setTitle("Menu");
        setLayout(null);
        ImageIcon fondoM = new ImageIcon("prjDemo\\src\\main\\java\\wsdemo\\UserInterface\\Recursos\\menu.jpg");
        etiquetaFondo3 = new JLabel(fondoM);
        //etiquetaUsuario = new JLabel(Ingreso.correo.getText());
        etiquetaFondo3.setBounds(-63, -13, 1280, 730);
        //etiquetaUsuario.setBounds(513, 84, 185,61);
        //add(etiquetaUsuario);

        humedadActual = new JButton("<html><font color='#78BE7B'>Monitorear Humedad</font></html>");
        humedadActual.setBounds(299, 320, 245, 166);
        humedadActual.setBackground(Color.WHITE);
        add(humedadActual);
        humedadActual.addActionListener(this);
        humedadHistorial = new JButton("<html><font color='#78BE7B'>Historial Humedad</font></html>");
        humedadHistorial.setBounds(679, 320, 245, 166);
        humedadHistorial.setBackground(Color.WHITE);
        add(humedadHistorial);
        humedadHistorial.addActionListener(this);

        add(etiquetaFondo3);

     humedadActual.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            CreateDatabaseExample.ingresoDatos(dataArduino.inicializacion());
        }
     });

       
    }

    public void mostrarVentanaMenu(){
        this.setBounds(0, 0, 1210, 710);
        this.setVisible(true);
        this.setLocationRelativeTo(null);  
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==humedadActual){
            this.setVisible(false);
            // IR VENTANA Humedad Actual
            HumedadActual ha = new HumedadActual();
            ha.mostrarVentanaHumedadA();
        }

        if (e.getSource()==humedadHistorial){
            this.setVisible(false);
            // IR VENTANA HUMEDAD HISTORIAL
            HumedadHistorial hh = new HumedadHistorial();
            hh.mostrarVentanaHumedadH();
        }
    }
    
}
