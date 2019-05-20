package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class ProveedorDAO extends ConexionBD {

    private int criterioB;
    
    /**
     * Con este método estamos creando un nuevo Proveedor
     * @param proveedor
     * @return 
     */

    public boolean create(Proveedor proveedor) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO proveedor (NIT, razonSocial, email, direccion, telefono, nCuenta, estado) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNit());
            ps.setString(2, proveedor.getRazonSocial());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getDireccion());
            ps.setInt(5, proveedor.getTelefono());
            ps.setInt(6, proveedor.getnCuenta());
            ps.setString(7, proveedor.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * @param proveedor le pasa el proveedor
     * @param criterioB es la opción que el usuario escoje en el radio bottom
     * del jframe para hacer la consulta
     * @return si retorna true es que hizo bien la consulta
     */
    public boolean read(Proveedor proveedor, int criterioB) {
        this.criterioB = criterioB;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;

        String sql = "SELECT * FROM proveedor WHERE NIT =?";
        String sql2 = "SELECT * FROM proveedor WHERE razonSocial =?";
        if (criterioB == 1) {
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, proveedor.getNit());
                rs = ps.executeQuery();
                while (rs.next()) {
                    proveedor.setNit(rs.getString("NIT"));
                    proveedor.setRazonSocial(rs.getString("razonSocial"));
                    proveedor.setEmail(rs.getString("email"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setTelefono(Integer.parseInt(rs.getString("telefono")));
                    proveedor.setnCuenta(Integer.parseInt(rs.getString("nCuenta")));
                    proveedor.setEstado(rs.getString("estado"));
                    consultaHecha = true;
                    return consultaHecha;
                }
                consultaHecha = false;
            } catch (SQLException e) {
                System.err.println(e);
                consultaHecha = false;
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }
        if (criterioB == 2) {
            System.out.println("entro aqui leidy gaga");
            try {
                ps = con.prepareStatement(sql2);
                ps.setString(1, proveedor.getRazonSocial());
                rs = ps.executeQuery();
                while (rs.next()) {
                    proveedor.setNit(rs.getString("NIT"));
                    proveedor.setRazonSocial(rs.getString("razonSocial"));
                    proveedor.setEmail(rs.getString("email"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setTelefono(Integer.parseInt(rs.getString("telefono")));
                    proveedor.setnCuenta(Integer.parseInt(rs.getString("nCuenta")));
                    proveedor.setEstado(rs.getString("estado"));
                    consultaHecha = true;
                    return consultaHecha;
                }
                consultaHecha = false;
            } catch (SQLException e) {
                System.err.println(e);
                consultaHecha = false;
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        return consultaHecha;
    }
    
    /**
     * Este método modifica los campos que le realicemos algún cambio
     * @param proveedor
     * @return 
     */

    public boolean update(Proveedor proveedor) {
        PreparedStatement ps = null;
        Connection con = getConexion();        
        String sql = "UPDATE proveedor SET razonSocial=?, email=?, direccion=?, telefono=?, nCuenta=?, estado=? WHERE NIT=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getEmail());
            ps.setString(3, proveedor.getDireccion());
            ps.setInt(4, proveedor.getTelefono());
            ps.setInt(5, proveedor.getnCuenta());
            ps.setString(6, proveedor.getEstado());
            ps.setString(7, proveedor.getNit());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Con este método eliminamos los datos seleccionados
     * @param proveedor
     * @return 
     */

    public boolean delete(Proveedor proveedor) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM proveedor WHERE NIT=?";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, proveedor.getNit());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Rcorre la lista de proveedor por cirterio de busqueda
     * @param proveedor
     * @return 
     */

    public ArrayList<Proveedor> listProveedorPorCriterio(Proveedor proveedor) {
        ArrayList listaProveedor = new ArrayList();
        Proveedor prov;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM proveedor WHERE NIT=?";
            String sql2 = "SELECT * FROM proveedor WHERE razonSocial=?";
            if (criterioB == 1) {

                ps = con.prepareStatement(sql);
                ps.setString(1, proveedor.getNit());
                rs = ps.executeQuery();
                while (rs.next()) {
                    prov = new Proveedor();
                    prov.setNit(rs.getString(1));
                    prov.setRazonSocial(rs.getString(2));
                    prov.setEmail(rs.getString(3));
                    prov.setDireccion(rs.getString(4));
                    prov.setTelefono(Integer.parseInt(rs.getString(5)));
                    prov.setnCuenta(Integer.parseInt(rs.getString(6)));
                    prov.setEstado(rs.getString(7));
                    listaProveedor.add(prov);
                }
            }
            if (criterioB == 2) {

                ps2 = con.prepareStatement(sql2);
                ps2.setString(1, proveedor.getRazonSocial());
                rs = ps2.executeQuery();
                while (rs.next()) {
                    prov = new Proveedor();
                    prov.setNit(rs.getString(1));
                    prov.setRazonSocial(rs.getString(2));
                    prov.setEmail(rs.getString(3));
                    prov.setDireccion(rs.getString(4));
                    prov.setTelefono(Integer.parseInt(rs.getString(5)));
                    prov.setnCuenta(Integer.parseInt(rs.getString(6)));
                    prov.setEstado(rs.getString(7));
                    listaProveedor.add(prov);
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listaProveedor;
    }
    
    /**
     * Recorre la lista de proveedores
     * @return 
     */

    public ArrayList<Proveedor> listProveedores() {
        ArrayList listaProveedor = new ArrayList();
        Proveedor proveedor;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM proveedor";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setNit(rs.getString(1));
                proveedor.setRazonSocial(rs.getString(2));
                proveedor.setEmail(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(Integer.parseInt(rs.getString(5)));
                proveedor.setnCuenta(Integer.parseInt(rs.getString(6)));
                proveedor.setEstado(rs.getString(7));
                listaProveedor.add(proveedor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listaProveedor;
    }

}
