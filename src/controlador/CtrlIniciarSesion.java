
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

public class CtrlIniciarSesion implements ActionListener{
    
    private Persona persona;
    private PersonaDAO personaDAO;
    private FrmIniciarSesion frmSesion;
    
    /**
     * Este método es el constructor de la clase incializa un objeto de la clase Persona, PersonaDAO, FrmIniciarSesion
     * @param persona
     * @param personaDAO es donde se realiza las consultas CRUD
     * @param frmSesion el JFrame de inicio de secion
     */

    public CtrlIniciarSesion(Persona persona, PersonaDAO personaDAO, FrmIniciarSesion frmSesion) {
        this.persona = persona;
        this.personaDAO = personaDAO;
        this.frmSesion=frmSesion;

        
        this.frmSesion.btnAceptar.addActionListener(this);
        this.frmSesion.btnCancelar.addActionListener(this);
        
    }
    
    /**
     * En este método se iniciliza el frame de FrmIniciarSesion
     */

    public void iniciar(){
        frmSesion.setVisible(true);
        frmSesion.setLocationRelativeTo(null);
    }
    
    /**
     * En este método se valida los datos que ingresa en el usuario y contraseña
     * @param e 
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==frmSesion.btnAceptar){
            if(frmSesion.txtUsuario.getText().equals("")|| frmSesion.txtpContraseña.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe tener todos los campos llenos");
            }else{
                persona.setUsuario(frmSesion.txtUsuario.getText());
                persona.setContraseña(frmSesion.txtpContraseña.getText());
                if(personaDAO.iniciarSesion(persona)){
                    JOptionPane.showMessageDialog(null, "Bienvenido usted ingreso como "+persona.getTipo_empleado());
                    frmSesion.dispose();
                    CtrlPrincipal crtlPrincipal = new CtrlPrincipal(persona);
                    crtlPrincipal.iniciar();
                }else {
                    JOptionPane.showMessageDialog(null, "datos incorrectos");
                }
            }
        }
        if(e.getSource()==frmSesion.btnCancelar){
            frmSesion.dispose();
        }       
    }
}
