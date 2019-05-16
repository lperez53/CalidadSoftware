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
import modelo.*;
import vista.FrmAdministrarProveedor;
import vista.FrmPrincipal;
import vista.FrmSolicitudProductos;

public class CtrlSolicitudProductos implements ActionListener, MouseListener {

    private Producto producto;
    private Proveedor proveedor;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;
    private FrmPrincipal frmPrincipal;
    private final ArrayList<Producto> productos = new ArrayList<>();
    private FrmSolicitudProductos frmSolProducto = new FrmSolicitudProductos();
    private FrmAdministrarProveedor frmProveedor;
    private Caja caja = new Caja();
    private CajaDAO cajaDAO = new CajaDAO();

    DefaultTableModel modelo = new DefaultTableModel();
    Object[] f = new Object[6];
    float suma = 0;

    public CtrlSolicitudProductos(Producto producto, Proveedor proveedor, ProductoDAO productoDAO, ProveedorDAO proveedorDAO, FrmPrincipal frmPrincipal) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.productoDAO = productoDAO;
        this.proveedorDAO = proveedorDAO;
        this.frmPrincipal = frmPrincipal;

        this.frmSolProducto.btnBuscarProducto.addActionListener(this);
        this.frmSolProducto.btnBuscarProveedor.addActionListener(this);
        this.frmSolProducto.btnCancelar.addActionListener(this);
        this.frmSolProducto.btnNuevo.addActionListener(this);
        this.frmSolProducto.btnSolicitar.addActionListener(this);
        this.frmSolProducto.btnAdd.addActionListener(this);
        this.frmSolProducto.btnLess.addActionListener(this);
        this.frmSolProducto.tblSolicitudProductos.addMouseListener(this);
        frmSolProducto.tblSolicitudProductos.setModel(modelo);
    }

    public void iniciar() {
        this.frmPrincipal.dpnEscritorio.add(frmSolProducto);
        frmSolProducto.show();
        Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        frmSolProducto.lbFecha.setText(dia + "-" + mes + "-" + ano);

        frmSolProducto.btnSolicitar.setEnabled(false);
        frmSolProducto.btnAdd.setEnabled(false);
        frmSolProducto.btnLess.setEnabled(false);
        frmSolProducto.btnBuscarProveedor.setEnabled(false);
        frmSolProducto.btnBuscarProducto.setEnabled(false);

        frmSolProducto.txtProveedor.setEnabled(false);
        frmSolProducto.txtCodigo.setEnabled(false);
        frmSolProducto.txtNombre.setEnabled(false);
        frmSolProducto.txtCosto.setEnabled(false);
        frmSolProducto.txtTotalPagar.setEnabled(false);
        frmSolProducto.txtCodigoOc.setVisible(false);
        frmSolProducto.txtTotaltb.setVisible(false);

        modelo.addColumn("Proveedor");
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio por unidad");
        modelo.addColumn("Total");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmSolProducto.btnNuevo) {
            frmSolProducto.btnSolicitar.setEnabled(true);
            frmSolProducto.btnAdd.setEnabled(true);
            frmSolProducto.btnLess.setEnabled(true);
            frmSolProducto.btnBuscarProveedor.setEnabled(true);
            frmSolProducto.btnBuscarProducto.setEnabled(true);
            frmSolProducto.btnNuevo.setEnabled(false);

        }
        if (ae.getSource() == frmSolProducto.btnBuscarProveedor) {

            CtrlProveedor ctrlProveedor = new CtrlProveedor(proveedor, proveedorDAO, frmPrincipal, frmSolProducto);
            ctrlProveedor.iniciarSolicitud();

        }
        if (ae.getSource() == frmSolProducto.btnBuscarProducto) {
            CtrlProducto ctrlProducto = new CtrlProducto(producto, productoDAO, frmPrincipal, frmSolProducto);
            ctrlProducto.iniciarSolicitud();
        }

        if (ae.getSource() == frmSolProducto.btnAdd) {
            if (frmSolProducto.txtCantidad.getText().equals("") || frmSolProducto.txtCodigo.getText().equals("")
                    || frmSolProducto.txtCosto.getText().equals("") || frmSolProducto.txtNombre.getText().equals("")
                    || frmSolProducto.txtProveedor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe tener los campos llenos");
            } else {
                if (Integer.parseInt(frmSolProducto.txtCantidad.getText()) > 240) {
                    JOptionPane.showMessageDialog(null, "No puede solicitar mas de 20 docenas al proveedor");
                } else {

                    Producto prod = new Producto();
                    prod.setCodigo(producto.getCodigo());
                    prod.setNombre(producto.getNombre());
                    prod.setDescripcion(producto.getDescripcion());
                    prod.setCantidadenInventario(producto.getCantidadenInventario());
                    prod.setPrecioCosto(producto.getPrecioCosto());
                    prod.setPrecioVenta(producto.getPrecioVenta());
                    prod.setCategoria(producto.getCategoria());

                    float mult;
                    mult = Float.parseFloat(frmSolProducto.txtCosto.getText()) * Float.parseFloat(frmSolProducto.txtCantidad.getText());
                    f[0] = frmSolProducto.txtProveedor.getText();
                    f[1] = frmSolProducto.txtCodigo.getText();
                    f[2] = frmSolProducto.txtNombre.getText();
                    f[3] = frmSolProducto.txtCantidad.getText();
                    f[4] = frmSolProducto.txtCosto.getText();
                    f[5] = mult;
                    modelo.addRow(f);

                    suma = suma + mult;
                    frmSolProducto.txtTotalPagar.setText(Float.toString(suma));
                    prod.AñadirAInventario(Integer.parseInt(frmSolProducto.txtCantidad.getText()));
                    productos.add(prod);
                    limpiar();
                }
            }
        }
        if (ae.getSource() == frmSolProducto.btnLess) {

            if (frmSolProducto.txtCodigoOc.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tiene que seleccionar un producto de la tabla");
            } else {
                int fila = frmSolProducto.tblSolicitudProductos.getSelectedRow();
                modelo.removeRow(fila);
                suma=suma-Float.parseFloat(frmSolProducto.txtTotaltb.getText());
                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getCodigo().equals(frmSolProducto.txtCodigoOc.getText())) {
                        
                        System.out.println(productos.get(i).getCodigo());
                        productos.remove(i);
                        
                    }
                }
                for (Producto pro : productos) {
                    System.out.println(pro.toString());
                }
                frmSolProducto.txtTotalPagar.setText(Float.toString(suma));
                frmSolProducto.txtCodigoOc.setText(null);
                frmSolProducto.txtTotaltb.setText(null);
            }
        }
        if (ae.getSource() == frmSolProducto.btnSolicitar) {
            if (frmSolProducto.txtTotalPagar.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe solicitar por lo menos un producto a un proveedor");
            } else {
                if (cajaDAO.read(caja)) {
                    caja.RetirarDinero(Float.parseFloat(frmSolProducto.txtTotalPagar.getText()));
                    if (cajaDAO.update(caja)) {
                        JOptionPane.showMessageDialog(null, "La compra se ha hecho con exito");
                        for (Producto pro : productos) {
                            productoDAO.updateCant(pro);
                        }
                        reiniciar();

                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR al retirar dinero de la caja");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR al consultar caja");
                }
            }
        }
        if (ae.getSource() == frmSolProducto.btnCancelar) {
            reiniciar();
            for (int i = 0; i < productos.size(); i++) {
                productos.remove(i);
            }
        }
    }

    public void reiniciar() {
        frmSolProducto.btnSolicitar.setEnabled(false);
        frmSolProducto.btnAdd.setEnabled(false);
        frmSolProducto.btnLess.setEnabled(false);
        frmSolProducto.btnBuscarProveedor.setEnabled(false);
        frmSolProducto.btnBuscarProducto.setEnabled(false);
        frmSolProducto.btnNuevo.setEnabled(true);
        limpiarTodo();
        reiniciarJTable(frmSolProducto.tblSolicitudProductos);
    }

    public void reiniciarJTable(JTable Tabla) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    public void limpiarTodo() {
        frmSolProducto.txtCantidad.setText(null);
        frmSolProducto.txtCosto.setText(null);
        frmSolProducto.txtCodigo.setText(null);
        frmSolProducto.txtNombre.setText(null);
        frmSolProducto.txtProveedor.setText(null);
        frmSolProducto.txtTotalPagar.setText(null);
        frmSolProducto.txtCodigoOc.setText(null);
        frmSolProducto.txtTotaltb.setText(null);
    }

    public void limpiar() {
        frmSolProducto.txtCantidad.setText(null);
        frmSolProducto.txtCosto.setText(null);
        frmSolProducto.txtCodigo.setText(null);
        frmSolProducto.txtNombre.setText(null);
        frmSolProducto.txtCodigoOc.setText(null);
        frmSolProducto.txtTotaltb.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == frmSolProducto.tblSolicitudProductos) {
            int fila = frmSolProducto.tblSolicitudProductos.getSelectedRow();
            frmSolProducto.txtCodigoOc.setText(frmSolProducto.tblSolicitudProductos.getModel().getValueAt(fila, 1).toString());
            frmSolProducto.txtTotaltb.setText(frmSolProducto.tblSolicitudProductos.getModel().getValueAt(fila, 5).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
