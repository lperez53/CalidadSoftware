/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ConexionBD;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.FrmConexionBD;
import vista.FrmIniciarSesion;
/**
 * 
 * @author Yuliana Rodriguez, Juliana Perez, Leidy Rodriguez.
 */
public class CtrlConexionBD implements ActionListener {

    private FrmConexionBD frmConexionBD;

    public CtrlConexionBD(FrmConexionBD frmConexion) {
        this.frmConexionBD = frmConexion;
        this.frmConexionBD.btnAceptar.addActionListener(this);
        this.frmConexionBD.btnCancelar.addActionListener(this);
    }
/**
 * este metodo hace visible el JFrame para establecer la contraseña de la base de datos
 */
    public void iniciar() {
        this.frmConexionBD.setVisible(true);
        this.frmConexionBD.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == frmConexionBD.btnAceptar) {
            String contraseña;
            contraseña = frmConexionBD.txtPasswordBD.getText();
            ConexionBD.setPassword(contraseña);
            
            frmConexionBD.dispose();
            Persona persona = new Persona();
            PersonaDAO personaDAO = new PersonaDAO();
            FrmIniciarSesion frmSesion = new FrmIniciarSesion();

            CtrlIniciarSesion ctrlSesion = new CtrlIniciarSesion(persona, personaDAO, frmSesion);
            ctrlSesion.iniciar();
        }
        if(ae.getSource() == frmConexionBD.btnCancelar){
            frmConexionBD.dispose();
        }
    }

}
