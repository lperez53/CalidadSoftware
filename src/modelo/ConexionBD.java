
package modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yuliana Rodriguez, Juliana Rodriguez
 * 
 */
public class ConexionBD {

    private final String base = "sistema_ventas";
    private final String user = "root";
    private static String password;
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    private Connection con =null;
    
    
    public Connection getConexion () {
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return con;
        
    }

    public static void setPassword(String password) {
        ConexionBD.password = password;
    }

    public String getBase() {
        return base;
    }

    public String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public Connection getCon() {
        return con;
    }

    
    
}
    
    
    

