package wsdemo.UserInterface.Ventanas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingreso extends JFrame implements ActionListener, ChangeListener {
    private JLabel etiquetaFondo;
    private JTextField nombre, numCelular;
    private JPasswordField contrasena;
    private JButton botonIngreso;
    static Ingreso label = new Ingreso();
    String nombreVerif, celularVerif;
    char [] contrasenaVerif;

    public Ingreso () {
        setTitle("Ingreso");
        setLayout(null);
        ImageIcon fondo = new ImageIcon("prjDemo\\src\\main\\java\\wsdemo\\UserInterface\\Recursos\\loginFondoFinal.jpg");
        etiquetaFondo= new JLabel(fondo);
        etiquetaFondo.setBounds(-63, -35, 1280, 730);

        botonIngreso = new JButton("<html><font color='#48cf7c'>Iniciar sesión</font></html>");
        botonIngreso.setBounds(810, 557, 200, 50);
        botonIngreso.setBackground(Color.WHITE);
        add(botonIngreso);
        botonIngreso.setVisible(true);
        botonIngreso.addActionListener(this);

        add(etiquetaFondo);

        nombre = new JTextField();
        numCelular= new JTextField();
        contrasena= new JPasswordField();
        nombre.setBounds(700, 215, 380, 50);
        numCelular.setBounds(700,335,380,50);
        contrasena.setBounds(700, 455, 380, 50);
        add(nombre);
        add(numCelular);
        add(contrasena);
        
    }

    public static void inicioApp() {
       label.mostrarVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nombreVerif = nombre.getText();
        celularVerif= numCelular.getText();
        char[] contrasenaVerif = contrasena.getPassword();
        if(e.getSource()==botonIngreso && (!nombreVerif.isEmpty())&&(!celularVerif.isEmpty())&& (isEmptyChar(contrasenaVerif)==false)){
            JOptionPane.showMessageDialog(null,"Inicio de Sesion exitoso");
            label.setVisible(false);
           // CONECTAR A LA VENTANA DE BIENVENIDO USUARIO
           Opciones men = new Opciones();
           men.mostrarVentanaMenu();

        } else if (e.getSource()==botonIngreso && (nombreVerif.isEmpty() ||(celularVerif.isEmpty())||(isEmptyChar(contrasenaVerif)==true) ) ){
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            nombre.setText("");
            numCelular.setText("");
            contrasena.setText("");
        }
    }

    public void mostrarVentana(){
        Pantallazo splash = new Pantallazo();
        splash.mostrarPantallazo();
        label.setBounds(0, 0, 1210, 710);
        label.setVisible(true);
        label.setLocationRelativeTo(null);
    }

    public static boolean isEmptyChar(char[] array) {
        return array == null || array.length == 0;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stateChanged'");
    }
}

