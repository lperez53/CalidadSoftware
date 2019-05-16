package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Caja;
import modelo.CajaDAO;
import modelo.Credito;
import modelo.Factura;
import modelo.FacturaDAO;

import modelo.Persona;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.ProductoVenta;
import vista.FrmPrincipal;
import vista.FrmVenderProducto;

public class CtrlVentaProducto implements ActionListener, MouseListener {

    private Producto producto;
    private ProductoDAO productoDAO;
    private FrmPrincipal frmPrincipal;
    private Persona persona;
    private final ArrayList<Producto> productos = new ArrayList<>();
    private final ArrayList<ProductoVenta> productosVenta = new ArrayList<>();

    private FrmVenderProducto frmVenderProducto = new FrmVenderProducto();
    private Caja caja = new Caja();
    private CajaDAO cajaDAO = new CajaDAO();

    DefaultTableModel modelo = new DefaultTableModel();
    Object[] f = new Object[7];
    float conIva = 0;
    float suma = 0;
    int numeroAleatorio2;

    public CtrlVentaProducto(Producto producto, ProductoDAO productoDAO, FrmPrincipal frmPrincipal, Persona persona) {
        this.producto = producto;
        this.productoDAO = productoDAO;
        this.frmPrincipal = frmPrincipal;
        this.persona = persona;
        this.frmVenderProducto.btnNuevaVenta.addActionListener(this);
        this.frmVenderProducto.btnGenerarVenta.addActionListener(this);
        this.frmVenderProducto.btnCancelar.addActionListener(this);
        this.frmVenderProducto.tblVenderProducto.setModel(modelo);
        this.frmVenderProducto.tblVenderProducto.addMouseListener(this);
        this.frmVenderProducto.btnBuscar.addActionListener(this);
        this.frmVenderProducto.btnAdd.addActionListener(this);
        this.frmVenderProducto.btnLess.addActionListener(this);
        this.frmVenderProducto.btnCancelar.addActionListener(this);
    }

    public void iniciar() {

        this.frmPrincipal.dpnEscritorio.add(frmVenderProducto);
        frmVenderProducto.show();

        frmVenderProducto.bgMedioPago.add(frmVenderProducto.rbtnEfectivo);
        frmVenderProducto.bgMedioPago.add(frmVenderProducto.rbtnTarjetaCredito);
        frmVenderProducto.bgMedioPago.add(frmVenderProducto.rbtnTarjetaDebito);

        frmVenderProducto.txtCargo.setText(persona.getTipo_empleado());
        frmVenderProducto.txtNombreEmpleado.setText(persona.getNombre());

        frmVenderProducto.txtCodigo.setEnabled(false);
        frmVenderProducto.txtNombre.setEnabled(false);
        frmVenderProducto.txtDescripcion.setEnabled(false);
        frmVenderProducto.txtPrecioVenta.setEnabled(false);
        frmVenderProducto.txtPrecioTotal.setEnabled(false);
        frmVenderProducto.btnGenerarVenta.setEnabled(false);
        frmVenderProducto.btnBuscar.setEnabled(false);
        frmVenderProducto.btnAdd.setEnabled(false);
        frmVenderProducto.btnLess.setEnabled(false);
        frmVenderProducto.txtIVA.setEnabled(false);

        frmVenderProducto.txtCodigotb.setVisible(false);
        frmVenderProducto.txtTotaltb.setVisible(false);
        frmVenderProducto.lbCodeventa.setVisible(true);
        frmVenderProducto.txtCodigoVenta.setVisible(false);

        Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        frmVenderProducto.labelfecha.setText(dia + "-" + mes + "-" + ano);
        modelo.addColumn("Codigo Venta");
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio por unidad");
        modelo.addColumn("Total");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == frmVenderProducto.btnNuevaVenta) {
            frmVenderProducto.btnBuscar.setEnabled(true);
            frmVenderProducto.btnAdd.setEnabled(true);
            frmVenderProducto.btnLess.setEnabled(true);
            frmVenderProducto.btnNuevaVenta.setEnabled(false);
            int numeroAleatorio = (int) (Math.random() * 999999 + 1);
            frmVenderProducto.lbCodeventa.setText(Integer.toString(numeroAleatorio));
        }

        if (ae.getSource() == frmVenderProducto.btnBuscar) {
            CtrlProducto ctrlProducto = new CtrlProducto(producto, productoDAO, frmPrincipal, frmVenderProducto);
            ctrlProducto.iniciarVenta();
        }
        if (ae.getSource() == frmVenderProducto.btnAdd) {
            if (frmVenderProducto.txtCantidad.getText().equals("")
                    || frmVenderProducto.txtCodigo.getText().equals("")
                    || frmVenderProducto.txtDescripcion.getText().equals("")
                    || frmVenderProducto.txtNombre.getText().equals("")
                    || frmVenderProducto.txtPrecioVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe tener los campos llenos");
            } else {
                if (Integer.parseInt(frmVenderProducto.txtCantidad.getText()) > producto.getCantidadenInventario()) {
                    JOptionPane.showMessageDialog(null, "No puede vender mas de la cantidad en inventario");
                } else {
                    Producto prod = new Producto();
                    prod.setCodigo(producto.getCodigo());
                    prod.setNombre(producto.getNombre());
                    prod.setDescripcion(producto.getDescripcion());
                    prod.setCantidadenInventario(producto.getCantidadenInventario());
                    prod.setPrecioCosto(producto.getPrecioCosto());
                    prod.setPrecioVenta(producto.getPrecioVenta());
                    prod.setCategoria(producto.getCategoria());

                    ProductoVenta prodV = new ProductoVenta();

                    numeroAleatorio2 = (int) (Math.random() * 999 + 1);

                    prodV.setCodigoProd(Integer.toString(numeroAleatorio2));
                    prodV.setCantidad(Integer.parseInt(frmVenderProducto.txtCantidad.getText()));
                    prodV.setNombre(frmVenderProducto.txtNombre.getText());
                    prodV.setPrecioUnidad(Float.parseFloat(frmVenderProducto.txtPrecioVenta.getText()));

                    productosVenta.add(prodV);

                    float mult;
                    mult = Float.parseFloat(frmVenderProducto.txtPrecioVenta.getText()) * Float.parseFloat(frmVenderProducto.txtCantidad.getText());
//el numero aleatrio agregarlo a la tabla
                    f[0] = numeroAleatorio2;
                    f[1] = frmVenderProducto.txtCodigo.getText();
                    f[2] = frmVenderProducto.txtNombre.getText();
                    f[3] = frmVenderProducto.txtDescripcion.getText();
                    f[4] = frmVenderProducto.txtCantidad.getText();
                    f[5] = frmVenderProducto.txtPrecioVenta.getText();
                    f[6] = mult;

                    modelo.addRow(f);

                    suma = suma + mult;

                    conIva = (float) (conIva + mult + (mult * 0.19));

                    frmVenderProducto.txtIVA.setText(Float.toString(suma));
                    frmVenderProducto.txtPrecioTotal.setText(Float.toString(conIva));
                    prod.descontarInventario(Integer.parseInt(frmVenderProducto.txtCantidad.getText()));
                    productos.add(prod);
                    limpiar();
                }
            }
        }
        if (ae.getSource() == frmVenderProducto.btnLess) {
            if (frmVenderProducto.txtCodigotb.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tiene que seleccionar un producto de la tabla");
            } else {
                int fila = frmVenderProducto.tblVenderProducto.getSelectedRow();
                modelo.removeRow(fila);
                suma = suma - Float.parseFloat(frmVenderProducto.txtTotaltb.getText());
                conIva = (float) (conIva - ((Float.parseFloat(frmVenderProducto.txtTotaltb.getText()) * 0.19) + (Float.parseFloat(frmVenderProducto.txtTotaltb.getText()))));

                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getCodigo().equals(frmVenderProducto.txtCodigotb.getText())) {

                        System.out.println(productos.get(i).getCodigo());
                        productos.remove(i);

                    }
                }
                //al momento de establecer el codigoVenta en el txt ya se puede conparar para borrar el objeto
                for (int i = 0; i < productosVenta.size(); i++) {
                    if (productosVenta.get(i).getCodigoProd().equals(frmVenderProducto.txtCodigoVenta.getText())) {
                        productosVenta.remove(i);
                    }
                }

                frmVenderProducto.txtCodigotb.setText(null);
                frmVenderProducto.txtPrecioTotal.setText(Float.toString(conIva));
                frmVenderProducto.txtIVA.setText(Float.toString(suma));
                frmVenderProducto.txtTotaltb.setText(null);
                frmVenderProducto.txtCodigoVenta.setText(null);
            }
        }
        if (ae.getSource() == frmVenderProducto.btnGenerarVenta) {

            if (frmVenderProducto.txtPrecioTotal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe vender por lo menos un producto");
            } else {
                if (frmVenderProducto.rbtnEfectivo.isSelected()) {
                    JOptionPane.showMessageDialog(null, "El total de la venta es: " + frmVenderProducto.txtPrecioTotal.getText());
                    movimientoCaja();
                } else if (frmVenderProducto.rbtnTarjetaCredito.isSelected()) {
                    Credito credito = new Credito();
                    int cuotas;
                    try {

                        cuotas = Integer.parseInt(JOptionPane.showInputDialog("A cuantas cuotas desea pagar?"));
                        credito.setnCuotas(cuotas);

                        float total = Float.parseFloat(frmVenderProducto.txtPrecioTotal.getText());

                        float nuevoTotal = (float) (total + (total * 0.06));
                        float mensual = nuevoTotal / cuotas;
                        JOptionPane.showMessageDialog(null, "con una tasa de interes de 6% pagara mensual :" + mensual + " pesos");
                        JOptionPane.showMessageDialog(null, "El total de la venta es: " + nuevoTotal);
                        frmVenderProducto.txtPrecioTotal.setText(Float.toString(nuevoTotal));
                        movimientoCaja();
                    } catch (NumberFormatException e) {

                    }
                } else if (frmVenderProducto.rbtnTarjetaDebito.isSelected()) {
                    JOptionPane.showMessageDialog(null, "El total de la venta es: " + frmVenderProducto.txtPrecioTotal.getText());
                    movimientoCaja();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un medio de pago");
                }
            }
        }
        if (ae.getSource() == frmVenderProducto.btnCancelar) {

            frmVenderProducto.btnNuevaVenta.setEnabled(true);
            reiniciar();
            for (int i = 0; i < productos.size(); i++) {
                productos.remove(i);
            }
        }
    }

    public void movimientoCaja() {
        Factura fact = new Factura();
        FacturaDAO factDAO = new FacturaDAO();

        fact.setCodigoFact(Integer.parseInt(frmVenderProducto.lbCodeventa.getText()));
        fact.setFechaPago(frmVenderProducto.labelfecha.getText());
        fact.setTotalPagar(Float.parseFloat(frmVenderProducto.txtPrecioTotal.getText()));

        if (cajaDAO.read(caja)) {
            caja.ingresarDinero(Float.parseFloat(frmVenderProducto.txtPrecioTotal.getText()));
            if (cajaDAO.update(caja)) {
                JOptionPane.showMessageDialog(null, "La venta se ha hecho con exito");
                for (Producto pro : productos) {
                    productoDAO.updateCant(pro);
                }
                reiniciar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR al ingresar dinero a la caja");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR al consultar caja");
        }

        if (factDAO.createFact(fact)) {

            for (ProductoVenta proV : productosVenta) {
                factDAO.createProdVenta(fact.getCodigoFact(), proV);
            }

            int opcion;
            opcion = JOptionPane.showConfirmDialog(null, "¡Desea generar una factura de venta?");
            if (opcion == JOptionPane.YES_OPTION) {
                CtrlReporte ctrlReporte = new CtrlReporte();
                ctrlReporte.iniciar(fact.getCodigoFact());
            } else if (opcion == JOptionPane.NO_OPTION) {

            }

        } else {
            JOptionPane.showMessageDialog(null, "ERROR al guardar venta");
        }
        for (int i = 0; i < productosVenta.size(); i++) {
            productosVenta.remove(i);
        }

        frmVenderProducto.btnNuevaVenta.setEnabled(true);
    }

    public void reiniciar() {
        frmVenderProducto.txtCodigo.setEnabled(false);
        frmVenderProducto.txtNombre.setEnabled(false);
        frmVenderProducto.txtDescripcion.setEnabled(false);
        frmVenderProducto.txtPrecioVenta.setEnabled(false);
        frmVenderProducto.txtPrecioTotal.setEnabled(false);
        frmVenderProducto.btnGenerarVenta.setEnabled(false);
        frmVenderProducto.btnBuscar.setEnabled(false);
        frmVenderProducto.btnAdd.setEnabled(false);
        frmVenderProducto.btnLess.setEnabled(false);
        suma = 0;
        conIva = 0;
        limpiarTodo();
        reiniciarJTable(frmVenderProducto.tblVenderProducto);
    }

    public void reiniciarJTable(JTable Tabla) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    public void limpiar() {
        frmVenderProducto.txtCantidad.setText(null);
        frmVenderProducto.txtCodigo.setText(null);
        frmVenderProducto.txtDescripcion.setText(null);
        frmVenderProducto.txtNombre.setText(null);
        frmVenderProducto.txtPrecioVenta.setText(null);
        frmVenderProducto.txtCodigotb.setText(null);
    }

    public void limpiarTodo() {
        frmVenderProducto.txtCantidad.setText(null);
        frmVenderProducto.txtCodigo.setText(null);
        frmVenderProducto.txtDescripcion.setText(null);
        frmVenderProducto.txtNombre.setText(null);
        frmVenderProducto.txtPrecioVenta.setText(null);
        frmVenderProducto.txtCodigotb.setText(null);
        frmVenderProducto.txtPrecioTotal.setText(null);
        frmVenderProducto.txtIVA.setText(null);
        frmVenderProducto.lbCodeventa.setText(null);
        frmVenderProducto.txtCodigoVenta.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == frmVenderProducto.tblVenderProducto) {
            int fila = frmVenderProducto.tblVenderProducto.getSelectedRow();
            frmVenderProducto.txtCodigoVenta.setText(frmVenderProducto.tblVenderProducto.getModel().getValueAt(fila, 0).toString());
            frmVenderProducto.txtCodigotb.setText(frmVenderProducto.tblVenderProducto.getModel().getValueAt(fila, 1).toString());
            frmVenderProducto.txtTotaltb.setText(frmVenderProducto.tblVenderProducto.getModel().getValueAt(fila, 6).toString());

        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
