package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliana Perez
 */
public class RespaldarBD {

    private String base;
    private String user;
    private String password;
   

    public void respaldarBaseDatos() {

        llamarConexionBD();
        System.out.println(base);
        System.out.println(user);
        System.out.println(password);

        try {
            Process p = Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump -u "+user+" -p"+password+" "+base);
            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream("respaldo_sistemaVentas.sql");
            
            JOptionPane.showMessageDialog(null, "BACKUP CREADO EXITOSAMENTE !!");
            

            byte[] buffer = new byte[1000];

            int leido = is.read(buffer);

            while (leido > 0) {
                fos.write(buffer, 0, leido);
                leido = is.read(buffer);
            }
            
            fos.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA COPIA");
            //Logger.getLogger(RespaldarBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
    }

    public void llamarConexionBD() {
        ConexionBD con = new ConexionBD();
        this.base = con.getBase();
        this.password = ConexionBD.getPassword();
        this.user = con.getUser();
        

    }
}
