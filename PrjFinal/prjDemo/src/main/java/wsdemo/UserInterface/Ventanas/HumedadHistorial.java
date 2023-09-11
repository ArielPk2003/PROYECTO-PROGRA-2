package wsdemo.UserInterface.Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import wsdemo.CreateDatabaseExample;

public class HumedadHistorial extends JFrame implements ActionListener {
    private JLabel etiquetaFondo5;
    //Anadir tabla desde la base de datos
    private JButton regresar2;
    public HumedadHistorial() {
        
        setTitle("Historial de Humedad");
        setLayout(new FlowLayout());
        ImageIcon fondoHH= new ImageIcon("prjDemo\\src\\main\\java\\wsdemo\\UserInterface\\Recursos\\historial.jpg");
        etiquetaFondo5= new JLabel(fondoHH);
        etiquetaFondo5.setBounds(-63, -13, 1280, 730);
        showTable();
        add(etiquetaFondo5);
// Sobre Poner componentes sobre la imagen
        

        
        regresar2 = new JButton("<html><font color='#48cf7c'>Regresar</font></html>");
        regresar2.setBounds(125, 535, 107, 66);
        regresar2.setBackground(Color.WHITE);
        add(regresar2);
        regresar2.addActionListener(this);

    }

    public void mostrarVentanaHumedadH (){
        this.setBounds(0, 0, 1210, 710);
        this.setVisible(true);
        this.setLocationRelativeTo(null); 
    }


    public void showTable(){
        List<List<Object>> matrizDatos = CreateDatabaseExample.preTabla();
        JTable tabla = createTable(matrizDatos);

        // Agrega la tabla a un JScrollPane para que sea desplazable
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private JTable createTable(List<List<Object>> data) {
        String[] columnNames = {"ID", "Fecha/Hora", "Humedad"};
        Object[][] rowData = new Object[data.size()][3];

        for (int i = 0; i < data.size(); i++) {
            List<Object> fila = data.get(i);
            rowData[i][0] = fila.get(0);
            rowData[i][1] = fila.get(1);
            rowData[i][2] = fila.get(2);
        }

        return new JTable(rowData, columnNames);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==regresar2){
            this.setVisible(false);
            //IR VENTANA OPCIONES
            Opciones men = new Opciones();
            men.mostrarVentanaMenu();
        }
    }
    
}
