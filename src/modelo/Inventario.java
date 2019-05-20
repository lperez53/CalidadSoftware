
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 *
 */
public class Inventario {
    private String codigo;
    private Date   fechaCreacion;
    private ArrayList<Categoria> categorias = new ArrayList<> ();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
    

    
    
}
