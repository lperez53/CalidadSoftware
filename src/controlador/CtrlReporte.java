package controlador;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.ConexionBD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CtrlReporte {

    public void iniciar(int codigoFact) {
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.getConexion();

            JasperReport reporte = null;

            //String path = "src\\reportes\\FacturaReporte.jasper";
            
            Map parametro = new HashMap();
            parametro.put("cod", codigoFact);
            
            //reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/FacturaReporte.jasper"));
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        
        } catch (JRException ex) {
            Logger.getLogger(CtrlReporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
