package modelo;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class Caja {

    private String codigo;
    private float montoInicial;
    private float montoFinal;
    private float cantidad;

    public void ingresarDinero(float cantidad) {
        this.cantidad=cantidad;
        this.montoFinal = this.montoFinal + cantidad;
    }

    public float getCantidad() {
        return cantidad;
    }


    public void RetirarDinero(float cantidad) {
        this.montoFinal = this.montoFinal - cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(float montoInicial) {
        this.montoInicial = montoInicial;
    }

    public float getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(float montoFinal) {
        this.montoFinal = montoFinal;
    }

}
