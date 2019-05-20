package modelo;

import java.util.ArrayList;

/**
 *
 * @author Yuliana Rodriguez, Juliana Perez
 */
public class Negocio {

    private String nombre;
    private String direccion;
    private String zonaRecidencial;
    private int telefono;
    private Caja caja;
    private Inventario inventario;
    private ArrayList<Persona> personas;

    public String getNombre() {
        return nombre;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZonaRecidencial() {
        return zonaRecidencial;
    }

    public void setZonaRecidencial(String zonaRecidencial) {
        this.zonaRecidencial = zonaRecidencial;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

}
