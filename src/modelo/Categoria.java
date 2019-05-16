
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez, Leidy Rodriguez
 */
public class Categoria {
    private String idCategoria;
    private String nombre;
    private ArrayList<Producto> productos;

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    
    
}
