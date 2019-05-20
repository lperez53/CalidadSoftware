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
public class CategoriaDAO extends ConexionBD {

    private int criterioB;
    
    /**
     * Con este método estamos creando una nueva categoria
     * @param categoria
     * @return 
     */    

    public boolean create(Categoria categoria) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO categoria( id, nombre ) VALUES (?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getIdCategoria());
            ps.setString(2, categoria.getNombre());
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
    
    /**
     * Con este método estamos leyendo el criterio de busqueda marcado y realizando la consulta
     * @param categoria
     * @param criterioB es la opción que el usuario escoje en el radio bottom
     * del jframe para hacer la consulta
     * @return si retorna true es que hizo bien la consulta
     */

    public boolean read(Categoria categoria, int criterioB) {
        this.criterioB = criterioB;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;

        String sql = "SELECT * FROM categoria WHERE id =?";
        String sql2 = "SELECT * FROM categoria WHERE nombre =?";
        if (criterioB == 1) {

            try {

                ps = con.prepareStatement(sql);
                ps.setString(1, categoria.getIdCategoria());
                rs = ps.executeQuery();

                //este while agrega a la tabla los datos
                while (rs.next()) {
                    categoria.setIdCategoria(rs.getString("id"));
                    categoria.setNombre(rs.getString("nombre"));

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
            try {
                ps = con.prepareStatement(sql2);
                ps.setString(1, categoria.getNombre());
                rs = ps.executeQuery();
                //este while agrega a la tabla los datos
                while (rs.next()) {
                    categoria.setIdCategoria(rs.getString("id"));
                    categoria.setNombre(rs.getString("nombre"));

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
        }

        return consultaHecha;
    }
    
    /**
     * Este método modifica los campos que le realicemos algún cambio
     * @param categoria
     * @return 
     */

    public boolean update(Categoria categoria) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE categoria SET nombre=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getIdCategoria());
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
    
    /**
     * Con este método eliminamos los datos seleccionados
     * @param categoria
     * @return 
     */

    public boolean delete(Categoria categoria) {

        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        Connection con = getConexion();

        String sql2 = "UPDATE producto SET idCategoria=? WHERE idCategoria=?";
        String sql = "DELETE FROM categoria WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);

            ps2.setString(1, "1004");
            ps2.setString(2, categoria.getIdCategoria());
            ps2.execute();

            ps.setString(1, categoria.getIdCategoria());
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

    public ArrayList<Categoria> listCategoriaPorCriterio(Categoria categoria) {
        ArrayList listaCategoria = new ArrayList();
        Categoria cat;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM categoria WHERE id=?";
            String sql2 = "SELECT * FROM categoria WHERE nombre=?";
            if (criterioB == 1) {
                ps = con.prepareStatement(sql);
                ps.setString(1, categoria.getIdCategoria());
                rs = ps.executeQuery();
                while (rs.next()) {
                    cat = new Categoria();
                    cat.setIdCategoria(rs.getString(1));
                    cat.setNombre(rs.getString(2));
                    listaCategoria.add(cat);
                }
            }
            if (criterioB == 2) {
                ps2 = con.prepareStatement(sql2);
                ps2.setString(1, categoria.getNombre());
                rs = ps2.executeQuery();
                while (rs.next()) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(rs.getString("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    listaCategoria.add(categoria);
                }
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
    
    /**
     * Se recorre la lista de categorias
     * @return 
     */
    

    public ArrayList<Categoria> listCategorias() {
        ArrayList listaCategoria = new ArrayList();
        Categoria categoria;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String sql = "SELECT * FROM categoria WHERE id != 'abcdefghijklm12'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getString(1));
                categoria.setNombre(rs.getString(2));
                listaCategoria.add(categoria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listaCategoria;
    }
}
