package modelo;


/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class Persona {

    private String codigo;
    private String nombre;
    private int cedula;
    private int telefono;
    private String email;
    private String usuario; //sesion
    private String contraseña; //sesion
    private String inicioDiaLabor;
    private String finDiaLabor;
    private int id_sesion; //sesion
    private String tipo_empleado; //tipo_empleado

    public String getCodigo() {
        return codigo;
    }

    public String getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(String tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getInicioDiaLabor() {
        return inicioDiaLabor;
    }

    public void setInicioDiaLabor(String inicioDiaLabor) {
        this.inicioDiaLabor = inicioDiaLabor;
    }

    public String getFinDiaLabor() {
        return finDiaLabor;
    }

    public void setFinDiaLabor(String finDiaLabor) {
        this.finDiaLabor = finDiaLabor;
    }



    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

}
