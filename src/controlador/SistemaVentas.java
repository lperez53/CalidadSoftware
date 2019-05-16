
package controlador;

import vista.FrmConexionBD;


public class SistemaVentas {
    public static void main(String []args){
        FrmConexionBD frmConexion = new  FrmConexionBD();       
        CtrlConexionBD ctrlBD = new CtrlConexionBD(frmConexion);
        ctrlBD.iniciar();                
    }
}
