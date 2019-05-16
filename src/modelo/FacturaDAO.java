package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LEIDY RODRIGUEZ
 */
public class FacturaDAO extends ConexionBD {

    public boolean createFact(Factura fact) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO factura( codigo, fechaPago, totalPagar ) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, fact.getCodigoFact());            
            ps.setString(2, fact.getFechaPago());
            ps.setFloat(3, fact.getTotalPagar());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    public boolean createProdVenta(int codigoFact, ProductoVenta prodV) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        Connection con = getConexion();
        String sql = "INSERT INTO productoventa( codigoProd, nombreProd, precioUnidad, cantidad ) VALUES (?,?,?,?)";
        String sql2 = "INSERT INTO itemfact( codigoFact, codigoProd ) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);
            ps.setString(1, prodV.getCodigoProd());            
            ps.setString(2, prodV.getNombre());
            ps.setFloat(3, prodV.getPrecioUnidad());
            ps.setInt(4, prodV.getCantidad());
            ps.execute();
            
            ps2.setInt(1, codigoFact);
            ps2.setString(2, prodV.getCodigoProd());
            ps2.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    /*public boolean read(Factura fact) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;
        String sql = "SELECT * FROM factura";

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fact.setCodigoFact(Integer.parseInt(rs.getString(1)));
                fact.setNombre(rs.getString(2));
                fact.setCantidad(Integer.parseInt(rs.getString(3)));
                fact.setPrecioUnidad(Float.parseFloat(rs.getString(4)));
                fact.setFechaPago(rs.getString(5));
                fact.setTotalPagar(Float.parseFloat(rs.getString(6)));
                consultaHecha = true;
                return consultaHecha;
            }
            consultaHecha = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            consultaHecha = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return consultaHecha;
    }
*/
}
