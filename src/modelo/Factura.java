package modelo;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez, Leidy Rodriguez
 */
public class Factura {

    private int codigoFact;    
    private String fechaPago;
    private float totalPagar;
    //private ProductoVenta prodVenta;

    public int getCodigoFact() {
        return codigoFact;
    }

    public void setCodigoFact(int codigoFact) {
        this.codigoFact = codigoFact;
    }
    
    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(float totalPagar) {
        this.totalPagar = totalPagar;
    }

}
