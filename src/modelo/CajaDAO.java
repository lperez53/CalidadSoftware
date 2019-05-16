package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CajaDAO extends ConexionBD {

    public boolean read(Caja caja) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;
        String sql = "SELECT * FROM caja";

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                caja.setCodigo(rs.getString(1));
                caja.setMontoInicial(Float.parseFloat(rs.getString(2)));
                caja.setMontoFinal(Float.parseFloat(rs.getString(3)));
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

    public boolean update(Caja caja) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE caja SET montoFinal=? WHERE codigo=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setFloat(1, caja.getMontoFinal());
            ps.setString(2, caja.getCodigo());
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
}
