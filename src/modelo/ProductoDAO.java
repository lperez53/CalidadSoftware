package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez, Leidy Rodriguez
 */
public class ProductoDAO extends ConexionBD {

    private int criterioB;
    
    /**
     * Con este método creamos un nuevo producto
     * @param producto
     * @return 
     */

    public boolean create(Producto producto) {
        PreparedStatement ps = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "INSERT INTO producto (codigo, nombre, precioCosto, precioVenta, descripcion, cantidad, idCategoria) VALUES (?,?,?,?,?,?,?)";
        String sql3 = "SELECT id FROM categoria WHERE nombre=?";

        try {

            ps = con.prepareStatement(sql);
            ps3 = con.prepareStatement(sql3);

            ps3.setString(1, producto.getCategoria().getNombre());
            rs = ps3.executeQuery();
            while (rs.next()) {
                producto.getCategoria().setIdCategoria(rs.getString("id"));
            }

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getPrecioCosto());
            ps.setFloat(4, producto.getPrecioVenta());
            ps.setString(5, producto.getDescripcion());
            ps.setInt(6, producto.getCantidadenInventario());
            ps.setString(7, producto.getCategoria().getIdCategoria());
            ps.execute();            

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    
    /**
     * 
     * @param producto le pasa el producto
     * @param criterioB es la opción que el usuario escoje en el radio bottom
     * del jframe para hacer la consulta
     * @return si la consulta esta bien hecha retorna a true
     */

    public boolean read(Producto producto, int criterioB) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;
        this.criterioB = criterioB;
        String sql = "SELECT * FROM infocompletoproducto WHERE codigo=?";
        String sql2 = "SELECT * FROM infocompletoproducto WHERE nombre =?";
        String sql3 = "SELECT * FROM infocompletoproducto WHERE cantidad =?";
        String sql4 = "SELECT  * FROM infocompletoproducto WHERE nombreCategoria=?";        

        if (criterioB == 1) {
            try {
                
                ps = con.prepareStatement(sql);                
                ps.setString(1, producto.getCodigo());
                rs = ps.executeQuery();
                while (rs.next()) {
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecioCosto(Float.parseFloat(rs.getString("precioCosto")));
                    producto.setPrecioVenta(Float.parseFloat(rs.getString("precioVenta")));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setCantidadenInventario(Integer.parseInt(rs.getString("cantidad")));
                    producto.getCategoria().setNombre(rs.getString("nombreCategoria"));
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
                    System.err.println(e);
                }
            }
        }
        if (criterioB == 2) {
            try {
                ps = con.prepareStatement(sql2);
                ps.setString(1, producto.getNombre());
                rs = ps.executeQuery();
                while (rs.next()) {
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecioCosto(Float.parseFloat(rs.getString("precioCosto")));
                    producto.setPrecioVenta(Float.parseFloat(rs.getString("precioVenta")));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setCantidadenInventario(Integer.parseInt(rs.getString("cantidad")));
                    producto.getCategoria().setNombre(rs.getString("nombreCategoria"));
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
                    System.err.println(e);
                }
            }
        }
        if (criterioB == 3) {
            try {
                ps = con.prepareStatement(sql3);
                ps.setString(1, String.valueOf(producto.getCantidadenInventario()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecioCosto(Float.parseFloat(rs.getString("precioCosto")));
                    producto.setPrecioVenta(Float.parseFloat(rs.getString("precioVenta")));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setCantidadenInventario(Integer.parseInt(rs.getString("cantidad")));
                    producto.getCategoria().setNombre(rs.getString("nombreCategoria"));
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
                    System.err.println(e);
                }
            }
        }
        if (criterioB == 4) {
            try {
                ps = con.prepareStatement(sql4);
                ps.setString(1, String.valueOf(producto.getCategoria().getNombre()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecioCosto(Float.parseFloat(rs.getString("precioCosto")));
                    producto.setPrecioVenta(Float.parseFloat(rs.getString("precioVenta")));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setCantidadenInventario(Integer.parseInt(rs.getString("cantidad")));
                    producto.getCategoria().setNombre(rs.getString("nombreCategoria"));
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
     * @param producto
     * @return 
     */

    public boolean update(Producto producto) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        ResultSet rs = null;

        Connection con = getConexion();

        String sql = "UPDATE producto SET nombre=?, precioCosto=?, precioVenta=?, descripcion=?, cantidad=?, idCategoria=? WHERE codigo=?";
        String sql2 = "SELECT id FROM categoria WHERE nombre=?";        
        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);
            //
            ps2.setString(1, producto.getCategoria().getNombre());
            rs = ps2.executeQuery();
            while (rs.next()) {
                producto.getCategoria().setIdCategoria(rs.getString("id"));
            }
            //
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecioCosto());
            ps.setFloat(3, producto.getPrecioVenta());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getCantidadenInventario());
            ps.setString(6, producto.getCategoria().getIdCategoria());
            ps.setString(7, producto.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    /**
     * Este método actualiza la cantidad de productos
     * @param producto
     * @return 
     */

    public boolean updateCant(Producto producto) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE producto SET cantidad=? WHERE codigo=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getCantidadenInventario());
            ps.setString(2, producto.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    /**
     * Este método elimina un producto
     * @param producto
     * @return 
     */

    public boolean delete(Producto producto) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        Connection con = getConexion();        
        String sql = "DELETE FROM producto WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getCodigo());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    /**
     * Recorre una lista de productos por criterio de búsqueda
     * @param producto
     * @return 
     */
  

    public ArrayList<Producto> listProductoPorCriterio(Producto producto) {
        ArrayList listaProducto = new ArrayList();
        Producto prod;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM infocompletoproducto WHERE codigo=?";
            String sql2 = "SELECT * FROM infocompletoproducto WHERE nombre =?";
            String sql3 = "SELECT * FROM infocompletoproducto WHERE cantidad =?";
            String sql4 = "SELECT  * FROM infocompletoproducto WHERE nombreCategoria=?";
            if (criterioB == 1) {

                ps = con.prepareStatement(sql);
                ps.setString(1, producto.getCodigo());
                rs = ps.executeQuery();
                while (rs.next()) {
                    prod = new Producto();
                    prod.setCodigo(rs.getString(1));
                    prod.setNombre(rs.getString(2));
                    prod.setPrecioCosto(Float.parseFloat(rs.getString(3)));
                    prod.setPrecioVenta(Float.parseFloat(rs.getString(4)));
                    prod.setDescripcion(rs.getString(5));
                    prod.setCantidadenInventario(Integer.parseInt(rs.getString(6)));
                    prod.getCategoria().setNombre(rs.getString(7));
                    listaProducto.add(prod);
                }
            }
            if (criterioB == 2) {
                ps = con.prepareStatement(sql2);
                ps.setString(1, producto.getNombre());
                rs = ps.executeQuery();
                while (rs.next()) {
                    prod = new Producto();
                    prod.setCodigo(rs.getString(1));
                    prod.setNombre(rs.getString(2));
                    prod.setPrecioCosto(Float.parseFloat(rs.getString(3)));
                    prod.setPrecioVenta(Float.parseFloat(rs.getString(4)));
                    prod.setDescripcion(rs.getString(5));
                    prod.setCantidadenInventario(Integer.parseInt(rs.getString(6)));
                    prod.getCategoria().setNombre(rs.getString(7));
                    listaProducto.add(prod);
                }
            }
            if (criterioB == 3) {
                ps = con.prepareStatement(sql3);
                ps.setInt(1, producto.getCantidadenInventario());
                rs = ps.executeQuery();
                while (rs.next()) {
                    prod = new Producto();
                    prod.setCodigo(rs.getString(1));
                    prod.setNombre(rs.getString(2));
                    prod.setPrecioCosto(Float.parseFloat(rs.getString(3)));
                    prod.setPrecioVenta(Float.parseFloat(rs.getString(4)));
                    prod.setDescripcion(rs.getString(5));
                    prod.setCantidadenInventario(Integer.parseInt(rs.getString(6)));
                    prod.getCategoria().setNombre(rs.getString(7));
                    listaProducto.add(prod);
                }
            }
            if (criterioB == 4) {
                ps = con.prepareStatement(sql4);
                ps.setString(1, producto.getCategoria().getNombre());
                rs = ps.executeQuery();
                while (rs.next()) {
                    prod = new Producto();
                    prod.setCodigo(rs.getString(1));
                    prod.setNombre(rs.getString(2));
                    prod.setPrecioCosto(Float.parseFloat(rs.getString(3)));
                    prod.setPrecioVenta(Float.parseFloat(rs.getString(4)));
                    prod.setDescripcion(rs.getString(5));
                    prod.setCantidadenInventario(Integer.parseInt(rs.getString(6)));
                    prod.getCategoria().setNombre(rs.getString(7));
                    listaProducto.add(prod);
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listaProducto;
    }
    
    /**
     * Recorre una lista de productos
     * @return 
     */

    public ArrayList<Producto> listProductos() {
        ArrayList listaProductos = new ArrayList();
        Producto producto;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            // String sql2 = "SELECT * FROM infocompletoproducto";
            String sql = "SELECT * FROM infocompletoproducto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setCodigo(rs.getString(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCosto(Float.parseFloat(rs.getString(3)));
                producto.setPrecioVenta(Float.parseFloat(rs.getString(4)));
                producto.setDescripcion(rs.getString(5));
                producto.setCantidadenInventario(Integer.parseInt(rs.getString(6)));
                producto.getCategoria().setNombre(rs.getString(7));
                listaProductos.add(producto);
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
        return listaProductos;
    }
    
    /**
     * Recorre una lista de categorias
     * @return 
     */

    public ArrayList<String> listCategorias() {

        ArrayList<String> listaCategoria = new ArrayList<>();
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "SELECT nombre FROM categoria";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                listaCategoria.add(rs.getString(1));;
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
        return listaCategoria;
    }

}
