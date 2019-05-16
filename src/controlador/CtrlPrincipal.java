    package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import modelo.*;
import vista.FrmCaja;
import vista.FrmPrincipal;

public class CtrlPrincipal implements ActionListener {
    
    private final FrmPrincipal frmPrincipal = new FrmPrincipal();
    private Persona persona;

    /**
     * En este método se añaden los componentes del framePrincipal los
     * ActionListener
     *
     * @param persona
     */
    public CtrlPrincipal(Persona persona) {
        this.persona = persona;
        
        this.frmPrincipal.mnuInicioCategoria.addActionListener(this);
        this.frmPrincipal.mnuInicioEmpleado.addActionListener(this);
        this.frmPrincipal.mnuInicioProducto.addActionListener(this);
        this.frmPrincipal.mnuInicioProveedor.addActionListener(this);
        this.frmPrincipal.mnuVenderProducto.addActionListener(this);
        this.frmPrincipal.mnuSolicitarProductos.addActionListener(this);
        this.frmPrincipal.mnuConsultarCaja.addActionListener(this);
        this.frmPrincipal.mnuSalir.addActionListener(this);
        this.frmPrincipal.mnuInicioRespaldarBD.addActionListener(this);
    }

    /**
     * En este método se inicializa el frame del FrmPrincipal
     */
    public void iniciar() {
        frmPrincipal.setVisible(true);
        frmPrincipal.setLocationRelativeTo(null);
        
        frmPrincipal.lbcargo.setText(persona.getTipo_empleado());
        frmPrincipal.lbNombre.setText(persona.getNombre());
        
        Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        frmPrincipal.lbFecha.setText(dia + "-" + mes + "-" + ano);
        if (persona.getTipo_empleado().equals("ADMINISTRADOR")) {
            frmPrincipal.mnuInicioRespaldarBD.setEnabled(true);
        }
        if (persona.getTipo_empleado().equals("VENDEDOR")) {
            frmPrincipal.mnuMantenimiento.setEnabled(false);
        }
        if (persona.getTipo_empleado().equals("TECNICO")) {
            frmPrincipal.mnuConsultarCaja.setEnabled(false);
            frmPrincipal.mnuInicioCategoria.setEnabled(false);
            frmPrincipal.mnuInicioEmpleado.setEnabled(false);
            frmPrincipal.mnuInicioProveedor.setEnabled(false);
            frmPrincipal.mnuSolicitarProductos.setEnabled(false);
            frmPrincipal.mnuMovimientos.setEnabled(false);
        }
        
    }

    /**
     * Este método sirve para escuchar los "clicks" de los botones desde el
     * controlador
     *
     * @param e es una instancia de la clase ActionEvent para llamar al metodo
     * getSource()
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmPrincipal.mnuInicioCategoria) {
            Categoria categoria = new Categoria();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            CtrlCategoria ctrlCategoria = new CtrlCategoria(categoria, categoriaDAO, frmPrincipal);
            ctrlCategoria.iniciar();
        }
        
        if (e.getSource() == frmPrincipal.mnuInicioProducto) {
            Producto producto = new Producto();
            ProductoDAO productoDAO = new ProductoDAO();
            CtrlProducto ctrlProducto = new CtrlProducto(producto, productoDAO, frmPrincipal);
            ctrlProducto.iniciar();
            
        }
        
        if (e.getSource() == frmPrincipal.mnuInicioProveedor) {
            
            Proveedor proveedor = new Proveedor();
            ProveedorDAO proveedorDAO = new ProveedorDAO();
            CtrlProveedor ctrlProveedor = new CtrlProveedor(proveedor, proveedorDAO, frmPrincipal);
            ctrlProveedor.iniciar();
            
        }
        
        if (e.getSource() == frmPrincipal.mnuInicioEmpleado) {
                //PersonaDAO personaDAO = new PersonaDAO();
                //CtrlPersona ctrlPersona = new CtrlPersona(persona, personaDAO, frmPrincipal);
                //ctrlPersona.iniciar();
        }
        if (e.getSource() == frmPrincipal.mnuVenderProducto) {
            Administrador admin = new Administrador();
            Vendedor vendedor = new Vendedor();
            if (persona.getTipo_empleado().equals("ADMINISTRADOR")) {
                
                admin.setCedula(persona.getCedula());
                admin.setCodigo(persona.getCodigo());
                admin.setContraseña(persona.getContraseña());
                admin.setEmail(persona.getContraseña());
                admin.setFinDiaLabor(persona.getFinDiaLabor());
                admin.setId_sesion(persona.getId_sesion());
                admin.setInicioDiaLabor(persona.getInicioDiaLabor());
                admin.setNombre(persona.getNombre());
                admin.setTelefono(persona.getTelefono());
                admin.setTipo_empleado(persona.getTipo_empleado());
                admin.setUsuario(persona.getUsuario());
                Producto producto = new Producto();
                ProductoDAO productoDAO = new ProductoDAO();
                CtrlVentaProducto ctrlVentaProd = new CtrlVentaProducto(producto, productoDAO, frmPrincipal, admin);
                ctrlVentaProd.iniciar();
                
            }
            if (persona.getTipo_empleado().equals("VENDEDOR")) {                              
                
                vendedor.setCedula(persona.getCedula());
                vendedor.setCodigo(persona.getCodigo());
                vendedor.setContraseña(persona.getContraseña());
                vendedor.setEmail(persona.getContraseña());
                vendedor.setFinDiaLabor(persona.getFinDiaLabor());
                vendedor.setId_sesion(persona.getId_sesion());
                vendedor.setInicioDiaLabor(persona.getInicioDiaLabor());
                vendedor.setNombre(persona.getNombre());
                vendedor.setTelefono(persona.getTelefono());
                vendedor.setTipo_empleado(persona.getTipo_empleado());
                vendedor.setUsuario(persona.getUsuario());
                Producto producto = new Producto();
                ProductoDAO productoDAO = new ProductoDAO();
                CtrlVentaProducto ctrlVentaProd = new CtrlVentaProducto(producto, productoDAO, frmPrincipal, vendedor);
                ctrlVentaProd.iniciar();
            }
        }
        if (e.getSource() == frmPrincipal.mnuSolicitarProductos) {
            Producto producto = new Producto();
            ProductoDAO productoDAO = new ProductoDAO();
            Proveedor proveedor = new Proveedor();
            ProveedorDAO proveedorDAO = new ProveedorDAO();
            CtrlSolicitudProductos ctrlSolicitudProductos = new CtrlSolicitudProductos(producto, proveedor, productoDAO, proveedorDAO, frmPrincipal);
            ctrlSolicitudProductos.iniciar();
        }
        
        if (e.getSource() == frmPrincipal.mnuConsultarCaja) {
            FrmCaja frmCaja = new FrmCaja();
            CajaDAO cajaDAO = new CajaDAO();
            Caja caja = new Caja();
            this.frmPrincipal.dpnEscritorio.add(frmCaja);
            frmCaja.show();
            
            if (cajaDAO.read(caja)) {
                frmCaja.lbCodigo.setText(caja.getCodigo());
                frmCaja.lbMontoI.setText(Float.toString(caja.getMontoInicial()));
                frmCaja.lbMontoF.setText(Float.toString(caja.getMontoFinal()));
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR al consultar la caja");
            }
        }
        if (e.getSource() == frmPrincipal.mnuSalir) {
            frmPrincipal.dispose();
        }
        if (e.getSource() == frmPrincipal.mnuInicioRespaldarBD) {
            RespaldarBD respaldarBD = new RespaldarBD();
            CtrlRespaldarBD ctrlRespaldarBD = new CtrlRespaldarBD(respaldarBD, frmPrincipal);
            ctrlRespaldarBD.iniciar();
            
        }
    }
}
