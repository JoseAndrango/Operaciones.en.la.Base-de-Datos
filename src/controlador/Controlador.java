
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.TransaccionesBD;
import vista.VistaEstudiante;


public class Controlador implements ActionListener {
    VistaEstudiante vista;
    TransaccionesBD modelo;
    Estudiante estudiantes;

    public Controlador(VistaEstudiante vista, TransaccionesBD modelo, Estudiante estudiantes) {
        this.vista = vista;
        this.modelo = modelo;
        this.estudiantes = estudiantes;
        vista.btnInsertar.addActionListener(this);
        
    }
    public void iniciar()
    {
        vista.setTitle("Estudiante");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnInsertar)
        {
            estudiantes.setNombre(vista.txtNombre.getText());
            estudiantes.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiantes.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiantes.setMateria(vista.cbMateria.getSelectedItem().toString());
            if(modelo.insertar(estudiantes)){
                JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null,"No se inserto el registro");
            }
        }
    }
    
}
