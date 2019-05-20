package modelo;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class Producto {

    private String codigo;
    private String nombre;
    private float precioCosto;
    private float precioVenta;
    private String descripcion;
    private int cantidadenInventario;
    private int cantidadenVenta;
    private Categoria categoria = new Categoria();

    public String getCodigo() {
        return codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public float getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(float precioCosto) {
        this.precioCosto = precioCosto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadenInventario() {
        return cantidadenInventario;
    }

    public void setCantidadenInventario(int cantidadenInventario) {
        this.cantidadenInventario = cantidadenInventario;
    }

    public void descontarInventario(int cantidad) {
        this.cantidadenVenta=cantidad;
        this.cantidadenInventario = this.cantidadenInventario - cantidad;
    }
    public void AñadirAInventario(int cantidad) {
        this.cantidadenInventario = this.cantidadenInventario + cantidad;
    }

    public int getCantidadenVenta() {
        return cantidadenVenta;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precioCosto=" + precioCosto + ", precioVenta=" + precioVenta + ", descripcion=" + descripcion + ", cantidad=" + cantidadenInventario + ", categoria=" + categoria + '}';
    }

}
