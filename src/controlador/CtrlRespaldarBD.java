package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.RespaldarBD;
import vista.FrmPrincipal;
import vista.FrmRespaldarBD;

/**
 *
 * @author Juliana Perez
 */
public class CtrlRespaldarBD implements ActionListener {

    private RespaldarBD respaldarBD;
    private final FrmRespaldarBD frmRespaldarBD = new FrmRespaldarBD();    
    private FrmPrincipal frmPrincipal;

    public CtrlRespaldarBD(RespaldarBD respaldarBD, FrmPrincipal frmPrincipal) {
        
        this.respaldarBD = respaldarBD;
        this.frmPrincipal = frmPrincipal;
        
        this.frmRespaldarBD.btnRespaldar.addActionListener(this);
        this.frmRespaldarBD.btnCancelar.addActionListener(this);
    }

    public void iniciar() {
        this.frmPrincipal.dpnEscritorio.add(frmRespaldarBD);
        frmRespaldarBD.show();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmRespaldarBD.btnRespaldar) {
            RespaldarBD respaldarBD = new RespaldarBD();
            respaldarBD.respaldarBaseDatos();

        }
        if (ae.getSource()== frmRespaldarBD.btnCancelar){
            frmRespaldarBD.dispose();
        }
    }

}
