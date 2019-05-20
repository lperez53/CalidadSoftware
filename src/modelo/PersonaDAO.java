package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class PersonaDAO extends ConexionBD {
    
    private int criterioB;
    /**
     * Con este método creamos un nuevo usuario 
     * @param persona
     * @param tipo_empleado se especifica el tipo de empleado, Administrador, Vendedor, Tecnico
     * @return 
     */

    public boolean create(Persona persona, int tipo_empleado) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        Connection con = getConexion();
        String sql = "INSERT INTO empleado (codigo, nombre, cedula, telefono, email, inicio_Dia_Lab, fin_Dia_Lab, id_sesion) VALUES (?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO sesion (usuario, contraseña, id_tipo_emp) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);

            ps.setString(1, persona.getCodigo());
            ps.setString(2, persona.getNombre());
            ps.setInt(3, persona.getCedula());
            ps.setInt(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());
            ps.setString(6, persona.getInicioDiaLabor());
            ps.setString(7, persona.getFinDiaLabor());
            ps.setInt(8, persona.getId_sesion()); //hacer en el controlador la condicion
            ps.execute();
            //DATOS DE SESION
            ps2.setString(1, persona.getUsuario());
            ps2.setString(2, persona.getContraseña());
            if (tipo_empleado == 1) {
                ps2.setInt(3, 1);
            }
            if (tipo_empleado == 2) {
                ps2.setInt(3, 2);
            }
            if (tipo_empleado == 3) {
                ps2.setInt(3, 3);
            }
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
     * Con este método se lee el criterio de busqueda que marcó para la consulta
     * @param persona
     * @param criterioB criterio de busqueda marcado en el Radio Buttom
     * @return 
     */

    public boolean read(Persona persona, int criterioB) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        boolean consultaHecha = false;

        String sql = "SELECT * FROM infoCompletoEmpleado WHERE codigo=?";
        String sql2 = "SELECT * FROM infoCompletoEmpleado WHERE cedula =?";
        String sql3 = "SELECT * FROM infoCompletoEmpleado WHERE nombre =?";
        if (criterioB == 1) {
            try {
                //al metodo prepareStatement le asigna la primera consulta sql que busca por codigo
                ps = con.prepareStatement(sql);
                //como se busca por codigo entonces a ps.setString se le pasa el getCodigo
                //asi con las demas de acuerdo al criterio de busqueda 
                ps.setString(1, persona.getCodigo());
                rs = ps.executeQuery();
                while (rs.next()) {
                    persona.setCodigo(rs.getString("codigo"));
                    persona.setNombre(rs.getString("nombre"));
                    persona.setCedula(Integer.parseInt(rs.getString("cedula")));
                    persona.setTelefono(Integer.parseInt(rs.getString("telefono")));
                    persona.setEmail(rs.getString("email"));
                    persona.setInicioDiaLabor(rs.getString("diaInicioLab"));
                    persona.setFinDiaLabor(rs.getString("diaFinLab"));
                    persona.setTipo_empleado(rs.getString("tipoEmpleado"));

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

                ps.setInt(1, persona.getCedula());
                rs = ps.executeQuery();
                while (rs.next()) {
                    persona.setCodigo(rs.getString("codigo"));
                    persona.setNombre(rs.getString("nombre"));
                    persona.setCedula(Integer.parseInt(rs.getString("cedula")));
                    persona.setTelefono(Integer.parseInt(rs.getString("telefono")));
                    persona.setEmail(rs.getString("email"));
                    persona.setInicioDiaLabor(rs.getString("diaInicioLab"));
                    persona.setFinDiaLabor(rs.getString("diaFinLab"));
                    persona.setTipo_empleado(rs.getString("tipoEmpleado"));

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

                ps.setString(1, persona.getNombre());
                rs = ps.executeQuery();
                while (rs.next()) {
                    persona.setCodigo(rs.getString("codigo"));
                    persona.setNombre(rs.getString("nombre"));
                    persona.setCedula(Integer.parseInt(rs.getString("cedula")));
                    persona.setTelefono(Integer.parseInt(rs.getString("telefono")));
                    persona.setEmail(rs.getString("email"));
                    persona.setInicioDiaLabor(rs.getString("diaInicioLab"));
                    persona.setFinDiaLabor(rs.getString("diaFinLab"));
                    persona.setTipo_empleado(rs.getString("tipoEmpleado"));

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
        return consultaHecha;
    }
    
    /**
     * Con este método se modifica los campos los cuales tuvieron algun cambio en los datos
     * @param persona
     * @param tipoEmpleado
     * @return 
     */
    

    public boolean update(Persona persona, int tipoEmpleado) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        ResultSet rs = null;
        
        Connection con = getConexion();
        //en esta consulta no se le establebece (SET) a codigo porque esta en el where
        //lo que vas hacer con el resto es coger las claves primareas y ponerlas en el where y no en el SET
        String sql = "UPDATE empleado SET nombre=?, cedula=?, telefono=?, email=?, inicio_Dia_Lab=?, fin_Dia_Lab=? WHERE codigo=?";
        String sql2 = "UPDATE sesion SET id_tipo_emp=? WHERE id=?";//este id es id_sesion en empleado
        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);

            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getCedula());
            ps.setInt(3, persona.getTelefono());
            ps.setString(4, persona.getEmail());
            ps.setString(5, persona.getInicioDiaLabor());
            ps.setString(6, persona.getFinDiaLabor());
            ps.setString(7, persona.getCodigo());
            ps.execute();
            //actualiza en la tabla sesion
            if (tipoEmpleado == 1) {
                ps2.setInt(1, 1);
                ps2.setInt(2, persona.getId_sesion());
                ps2.execute();
            }
            if (tipoEmpleado == 2) {
                ps2.setInt(1, 2);
                ps2.setInt(2, persona.getId_sesion());
                ps2.execute();
            }
            if (tipoEmpleado == 3) {
                ps2.setInt(1, 3);
                ps2.setInt(2, persona.getId_sesion());
                ps2.execute();
            }

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
     * Con este método eliminamos una persona
     * @param persona
     * @return 
     */

    public boolean delete(Persona persona) {
        PreparedStatement ps = null;
        Connection con = getConexion();        
        String sql = "DELETE FROM empleado WHERE codigo=?";
        try {
            ps = con.prepareStatement(sql);
            //estableces en la consulta la clave primaria que pusiste en el where, en este caso esta codigo, entonces es get codigo
            ps.setString(1, persona.getCodigo());
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
     * Pemite iniciar sesion segun los datos ingresados en usuario y contraseña
     * @param persona
     * @return 
     */

    public boolean iniciarSesion(Persona persona) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM loginUsuario WHERE usuario =?";
        try {

            ps = con.prepareStatement(sql);//prepara la consulta
            ps.setString(1, persona.getUsuario());//se elvia el valor
            rs = ps.executeQuery();//busca un nombre de usuario en la bd
            if (rs.next()) {

                if (persona.getContraseña().equals(rs.getString(10))) {
                    
                    persona.setCodigo(rs.getString(1));
                    persona.setNombre(rs.getString(2));
                    persona.setCedula(Integer.parseInt(rs.getString(3)));
                    persona.setTelefono(Integer.parseInt(rs.getString(4)));
                    persona.setEmail(rs.getString(5));
                    persona.setInicioDiaLabor(rs.getString(6));
                    persona.setFinDiaLabor(rs.getString(7));
                   // persona.setId_sesion(Integer.parseInt(rs.getString(8)));
                    persona.setUsuario(rs.getString(9));
                    persona.setContraseña(rs.getString(10));
                    persona.setTipo_empleado(rs.getString(11));
                    
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public ArrayList<Persona> listPersonaPorCriterio (Persona persona) {
        ArrayList listaPersona = new ArrayList ();
        Persona pers;
        Connection con = getConexion();
        
        try {
            PreparedStatement ps= null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;
            
            String sql= "SELECT * FROM persona WHERE codigo =?";
            String sql2= "SELECT * FROM persona WHERE cedula =?";
            String sql3= "SELECT * FROM persona WHERE nombre=?";
            
            
            if (criterioB==1){
            
                 ps =con.prepareStatement(sql);
                 ps.setString(1,persona.getCodigo());
                 rs= ps.executeQuery();
                 
                 while (rs.next()){
                     pers= new Persona();
                     pers.setCodigo(rs.getString(1));
                     pers.setCedula(Integer.parseInt(rs.getString(2)));
                     pers.setNombre(rs.getString(3));
                     pers.setEmail(rs.getString(4));
                     pers.setInicioDiaLabor(rs.getString(5));
                     pers.setFinDiaLabor(rs.getString(6));
                     pers.setTipo_empleado(rs.getString(7));
                     pers.setUsuario(rs.getString(8));
                     pers.setContraseña(rs.getString(9));
                    
                     //pers.setId_sesion(Integer.parseInt(rs.getString(9)));
                     listaPersona.add(pers);
                     
                 }
        }
          
            if (criterioB==2){
                
                ps =con.prepareStatement(sql2);
                ps.setInt(1, persona.getCedula());
                rs= ps.executeQuery();
                
                while (rs.next()) {
                    pers =new Persona();
                    pers.setCodigo(rs.getString(1));
                     pers.setCedula(Integer.parseInt(rs.getString(2)));
                     pers.setNombre(rs.getString(3));
                     pers.setTelefono(Integer.parseInt(rs.getString(4)));
                     pers.setEmail(rs.getString(5));
                     pers.setInicioDiaLabor(rs.getString(6));
                     pers.setFinDiaLabor(rs.getString(7));
                     pers.setTipo_empleado(rs.getString(8));
                     pers.setUsuario(rs.getString(9));
                     pers.setContraseña(rs.getString(10));
                     //pers.setId_sesion(Integer.parseInt(rs.getString(11)));
                     listaPersona.add(pers);
                }
            }
            
            if (criterioB ==3){
                
                ps =con.prepareStatement(sql3);
                ps.setString(1, persona.getNombre());
                rs= ps.executeQuery();
                
                while (rs.next()) {
                    pers =new Persona();
                    pers.setCodigo(rs.getString(1));
                     pers.setCedula(Integer.parseInt(rs.getString(2)));
                     pers.setNombre(rs.getString(3));
                     pers.setTelefono(Integer.parseInt(rs.getString(4)));
                     pers.setEmail(rs.getString(5));
                     pers.setInicioDiaLabor(rs.getString(6));
                     pers.setFinDiaLabor(rs.getString(7));
                     pers.setTipo_empleado(rs.getString(8));
                     pers.setUsuario(rs.getString(9));
                     pers.setContraseña(rs.getString(10));
                     //pers.setId_sesion(Integer.parseInt(rs.getString(11)));
                     listaPersona.add(pers);
                }
                
            }
            
        } catch (SQLException e ) {
           JOptionPane.showMessageDialog(null, e);
        } finally {
            try{
                con.close();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    return listaPersona;    
    }
    
public ArrayList<Persona> listPersona() {
        ArrayList listaPersona = new ArrayList();
        Persona pers;
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            
            String sql = "SELECT * FROM infocompletoempleado";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                   pers =new Persona();
                    pers.setCodigo(rs.getString(1));
                     pers.setCedula(rs.getInt(2));
                     pers.setNombre(rs.getString(3));
                     pers.setTelefono(rs.getInt(4));
                     pers.setEmail(rs.getString(5));
                     pers.setInicioDiaLabor(rs.getString(6));
                     pers.setFinDiaLabor(rs.getString(7));
                     pers.setTipo_empleado(rs.getString(8));
                     pers.setUsuario(rs.getString(9));
                     pers.setContraseña(rs.getString(10));
                     //pers.setId_sesion(Integer.parseInt(rs.getString(11)));
                     listaPersona.add(pers);
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
        return listaPersona;
    }



public ArrayList <String> listTipo_Empleado () {
    ArrayList <String> listaTipo_Empleado = new ArrayList <> ();
    Connection con = getConexion();
    
    try {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql= "SELECT nombre FROM tipo_empleado";
        ps= con.prepareStatement(sql);
        rs= ps.executeQuery();
        
        while  (rs.next()) {
            
            listaTipo_Empleado.add(rs.getString(1));;
            
        }}
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listaTipo_Empleado;
        
    }
    
}


