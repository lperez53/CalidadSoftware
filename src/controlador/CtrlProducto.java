package controlador;

import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.FrmAdministrarProducto;
import vista.FrmPrincipal;
import vista.FrmSolicitudProductos;
import vista.FrmVenderProducto;

public class CtrlProducto implements ActionListener, MouseListener {

    private Producto producto;
    private ProductoDAO productoDAO;
    private FrmPrincipal frmPrincipal;
    private FrmAdministrarProducto frmProducto = new FrmAdministrarProducto();
    private FrmVenderProducto frmVentaProd;
    private FrmSolicitudProductos frmSolProduto;
    private int criterioB = 0;
    private Caja caja = new Caja();
    private CajaDAO cajaDAO = new CajaDAO();

    Object[] columna = new Object[7];

    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel mod2 = new DefaultTableModel();
    public ArrayList<Producto> productos = new ArrayList<>();

    public CtrlProducto(Producto producto, ProductoDAO productoDAO, FrmPrincipal frmPrincipal, FrmVenderProducto frmVenderProducto) {
        this.producto = producto;
        this.productoDAO = productoDAO;
        this.frmPrincipal = frmPrincipal;
        this.frmVentaProd = frmVenderProducto;
        frmProducto.btnBuscar.addActionListener(this);
        frmProducto.btnMostrarProductos.addActionListener(this);
        frmProducto.btnAñadirVenta.addActionListener(this);
        frmProducto.btnLimpiar.addActionListener(this);
        //MouseListener
        frmProducto.tblProducto.addMouseListener(this);

    }

    public CtrlProducto(Producto producto, ProductoDAO productoDAO, FrmPrincipal frmPrincipal) {
        this.producto = producto;
        this.productoDAO = productoDAO;
        this.frmPrincipal = frmPrincipal;
        frmProducto.btnBuscar.addActionListener(this);
        frmProducto.btnEliminar.addActionListener(this);
        frmProducto.btnGuardar.addActionListener(this);
        frmProducto.btnModificar.addActionListener(this);
        frmProducto.btnLimpiar.addActionListener(this);
        frmProducto.cbCategorias.addActionListener(this);
        frmProducto.btnMostrarProductos.addActionListener(this);
        frmProducto.btnAñadirVenta.addActionListener(this);
        //MouseListener
        frmProducto.tblProducto.addMouseListener(this);

    }

    public CtrlProducto(Producto producto, ProductoDAO productoDAO, FrmPrincipal frmPrincipal, FrmSolicitudProductos frmSolProduto) {
        this.producto = producto;
        this.productoDAO = productoDAO;
        this.frmPrincipal = frmPrincipal;
        this.frmSolProduto = frmSolProduto;

        frmProducto.btnBuscar.addActionListener(this);
        frmProducto.btnMostrarProductos.addActionListener(this);
        frmProducto.btnLimpiar.addActionListener(this);
        frmProducto.btnSeleccionar.addActionListener(this);
        //MouseListener
        frmProducto.tblProducto.addMouseListener(this);
        //frmSolProduto.btnAdd.addActionListener(this);
    }

    public void iniciarVenta() {
        this.frmVentaProd.btnGenerarVenta.addActionListener(this);
        this.frmVentaProd.btnGenerarVenta.setEnabled(true);
        
        this.frmPrincipal.dpnEscritorio.add(frmProducto);
        frmProducto.show();
        frmProducto.bgCriterioB.add(frmProducto.rbtnCantidad);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCategoria);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCodigo);
        frmProducto.bgCriterioB.add(frmProducto.rbtnNombre);

        frmProducto.cbCategorias.removeAllItems();
        frmProducto.txtNombreCategoria.setVisible(true);
        frmProducto.btnAñadirVenta.setVisible(true);
        frmProducto.btnEliminar.setEnabled(false);
        frmProducto.btnGuardar.setEnabled(false);
        frmProducto.btnModificar.setEnabled(false);
        frmProducto.cbCategorias.setVisible(false);
        frmProducto.labelCatcombo.setVisible(false);
        frmProducto.btnSeleccionar.setVisible(false);
        frmProducto.txtNombreCategoria.setVisible(true);
        frmProducto.labelCategoria.setVisible(true);

        frmProducto.txtCantidad.setEnabled(false);
        frmProducto.txtCodigo.setEnabled(false);
        frmProducto.txtDescripcion.setEnabled(false);
        frmProducto.txtNombre.setEnabled(false);
        frmProducto.txtNombreCategoria.setEnabled(false);
        frmProducto.txtPrecioCosto.setEnabled(false);
        frmProducto.txtPrecioVenta.setEnabled(false);

        frmProducto.tblProducto.setModel(modelo2);
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Precio Costo");
        modelo2.addColumn("Precio Venta");
        modelo2.addColumn("Descripcion");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Categoria");

        

    }

    public void iniciar() {
        this.frmPrincipal.dpnEscritorio.add(frmProducto);
        frmProducto.show();
        frmProducto.bgCriterioB.add(frmProducto.rbtnCantidad);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCategoria);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCodigo);
        frmProducto.bgCriterioB.add(frmProducto.rbtnNombre);

        frmProducto.cbCategorias.removeAllItems();
        frmProducto.txtNombreCategoria.setVisible(false);
        frmProducto.labelCategoria.setVisible(false);
        frmProducto.btnAñadirVenta.setVisible(false);
        frmProducto.btnSeleccionar.setVisible(false);
        frmProducto.txtCantidad.setEnabled(false);
        cargarBDcomboBox();

        frmProducto.tblProducto.setModel(modelo2);
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Precio Costo");
        modelo2.addColumn("Precio Venta");
        modelo2.addColumn("Descripcion");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Categoria");
    }

    public void iniciarSolicitud() {
        this.frmPrincipal.dpnEscritorio.add(frmProducto);
        frmProducto.show();
        frmProducto.bgCriterioB.add(frmProducto.rbtnCantidad);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCategoria);
        frmProducto.bgCriterioB.add(frmProducto.rbtnCodigo);
        frmProducto.bgCriterioB.add(frmProducto.rbtnNombre);

        frmProducto.cbCategorias.removeAllItems();
        frmProducto.txtNombreCategoria.setVisible(false);
        frmProducto.labelCategoria.setVisible(false);
        frmProducto.btnAñadirVenta.setVisible(false);
        frmProducto.btnSeleccionar.setVisible(true);

        frmProducto.btnEliminar.setEnabled(false);
        frmProducto.btnGuardar.setEnabled(false);
        frmProducto.btnModificar.setEnabled(false);
        frmProducto.cbCategorias.setVisible(false);

        frmProducto.txtCantidad.setEnabled(false);
        frmProducto.txtCodigo.setEnabled(false);
        frmProducto.txtDescripcion.setEnabled(false);
        frmProducto.txtNombre.setEnabled(false);
        frmProducto.txtNombreCategoria.setEnabled(false);
        frmProducto.txtPrecioCosto.setEnabled(false);
        frmProducto.txtPrecioVenta.setEnabled(false);

        frmProducto.tblProducto.setModel(modelo2);
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Precio Costo");
        modelo2.addColumn("Precio Venta");
        modelo2.addColumn("Descripcion");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Categoria");
    }

    public void cargarBDcomboBox() {
        frmProducto.cbCategorias.removeAllItems();
        ArrayList<String> lista;
        lista = productoDAO.listCategorias();

        for (int i = 0; i < lista.size(); i++) {
            if (i == 0) {
                frmProducto.cbCategorias.addItem("seleccione una opcion");
            }
            frmProducto.cbCategorias.addItem(lista.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmProducto.btnBuscar) {

            if (frmProducto.rbtnCodigo.isSelected()) {

                criterioB = 1;
                producto.setCodigo(frmProducto.txtBuscarProducto.getText());
            } else if (frmProducto.rbtnNombre.isSelected()) {

                criterioB = 2;
                producto.setNombre(frmProducto.txtBuscarProducto.getText());
            } else if (frmProducto.rbtnCantidad.isSelected()) {

                criterioB = 3;
                producto.setCantidadenInventario(Integer.parseInt(frmProducto.txtBuscarProducto.getText()));
            } else if (frmProducto.rbtnCategoria.isSelected()) {

                criterioB = 4;
                //si se para aqui entonces toca inicialozar la categoria en producto
                producto.getCategoria().setNombre(frmProducto.txtBuscarProducto.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un criterio de busquda");
            }
            if (frmProducto.txtBuscarProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar el campo de busqueda");
            } else {
                if (productoDAO.read(producto, criterioB)) {
                    llenarTablaPorCriterio(frmProducto.tblProducto, producto);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuentra el resultado");
                }
            }

        }
        if (ae.getSource() == frmProducto.btnMostrarProductos) {
            llenarTabla(frmProducto.tblProducto);
        }

        if (ae.getSource() == frmProducto.btnModificar) {
            String item;
            int indice;
            indice = frmProducto.cbCategorias.getSelectedIndex();
            item = frmProducto.cbCategorias.getItemAt(indice);
            if (frmProducto.txtCodigo.getText().equals("") || frmProducto.txtNombre.getText().equals("")
                    || frmProducto.txtPrecioCosto.getText().equals("") || frmProducto.txtPrecioVenta.getText().equals("")
                    || frmProducto.txtDescripcion.getText().equals("") || frmProducto.txtCantidad.getText().equals("")
                    || item.equals("seleccione una opcion")) {
                JOptionPane.showMessageDialog(null, "debe tener los campos llenos");
            } else {

                if (frmProducto.txtCodigo.getText().equals(producto.getCodigo())
                        && frmProducto.txtNombre.getText().equals(producto.getNombre())
                        && frmProducto.txtPrecioCosto.getText().equals(Float.toString(producto.getPrecioCosto()))
                        && frmProducto.txtPrecioVenta.getText().equals(Float.toString(producto.getPrecioVenta()))
                        && frmProducto.txtDescripcion.getText().equals(producto.getDescripcion())
                        && frmProducto.txtCantidad.getText().equals(Integer.toString(producto.getCantidadenInventario()))
                        && item.equals(producto.getCategoria().getNombre())) {
                    JOptionPane.showMessageDialog(null, "No existen cambios");
                } else {

                    producto.setCodigo(frmProducto.txtCodigo.getText());
                    producto.setNombre(frmProducto.txtNombre.getText());
                    producto.setPrecioCosto(Float.parseFloat(frmProducto.txtPrecioCosto.getText()));
                    producto.setPrecioVenta(Float.parseFloat(frmProducto.txtPrecioVenta.getText()));
                    producto.setDescripcion(frmProducto.txtDescripcion.getText());
                    producto.setCantidadenInventario(Integer.parseInt(frmProducto.txtCantidad.getText()));
                    producto.getCategoria().setNombre(item);

                    if (productoDAO.update(producto)) {
                        int Fila = frmProducto.tblProducto.getSelectedRow();
                        frmProducto.tblProducto.setValueAt(frmProducto.txtCodigo.getText(), Fila, 0);
                        frmProducto.tblProducto.setValueAt(frmProducto.txtNombre.getText(), Fila, 1);
                        frmProducto.tblProducto.setValueAt(frmProducto.txtPrecioCosto.getText(), Fila, 2);
                        frmProducto.tblProducto.setValueAt(frmProducto.txtPrecioVenta.getText(), Fila, 3);
                        frmProducto.tblProducto.setValueAt(frmProducto.txtDescripcion.getText(), Fila, 4);
                        frmProducto.tblProducto.setValueAt(frmProducto.txtCantidad.getText(), Fila, 5);
                        frmProducto.tblProducto.setValueAt(item, Fila, 6);
                        JOptionPane.showMessageDialog(null, "Producto modificado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar");
                    }
                }

            }
        }
        if (ae.getSource() == frmProducto.btnLimpiar) {
            limpiar();
        }
        if (ae.getSource() == frmProducto.btnEliminar) {
            if (frmProducto.txtCodigo.getText().equals("") || frmProducto.txtNombre.getText().equals("")
                    || frmProducto.txtPrecioCosto.getText().equals("") || frmProducto.txtPrecioVenta.getText().equals("")
                    || frmProducto.txtDescripcion.getText().equals("") || frmProducto.txtCantidad.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe seleccionar el producto que desea eliminar");
            } else {
                if (productoDAO.delete(producto)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                    removerEnTabla(frmProducto.tblProducto);
                }
            }
        }
        if (ae.getSource() == frmProducto.btnGuardar) {
            String item;
            int indice;
            indice = frmProducto.cbCategorias.getSelectedIndex();
            item = frmProducto.cbCategorias.getItemAt(indice);
            if (frmProducto.txtCodigo.getText().equals("") || frmProducto.txtNombre.getText().equals("")
                    || frmProducto.txtPrecioCosto.getText().equals("") || frmProducto.txtPrecioVenta.getText().equals("")
                    || frmProducto.txtDescripcion.getText().equals("")
                    || item.equals("seleccione una opcion")) {
                JOptionPane.showMessageDialog(null, "debe tener los campos llenos");
            } else {

                producto.setCodigo(frmProducto.txtCodigo.getText());
                producto.setNombre(frmProducto.txtNombre.getText());
                producto.setPrecioCosto(Float.parseFloat(frmProducto.txtPrecioCosto.getText()));
                producto.setPrecioVenta(Float.parseFloat(frmProducto.txtPrecioVenta.getText()));
                producto.setDescripcion(frmProducto.txtDescripcion.getText());
                producto.setCantidadenInventario(0);
                producto.getCategoria().setNombre(item);
                if (productoDAO.create(producto)) {
                    JOptionPane.showMessageDialog(null, "producto guardado");
                    Object[] f = new Object[7];
                    f[0] = frmProducto.txtCodigo.getText();
                    f[1] = frmProducto.txtNombre.getText();
                    f[2] = frmProducto.txtPrecioCosto.getText();
                    f[3] = frmProducto.txtPrecioVenta.getText();
                    f[4] = frmProducto.txtDescripcion.getText();
                    f[5] = producto.getCantidadenInventario();
                    f[6] = item;
                    this.modelo2.addRow(f);
                    limpiar();
                }
            }
        }
        if (ae.getSource() == frmProducto.btnAñadirVenta) {
            if (frmProducto.txtCodigo.getText().equals("") || frmProducto.txtNombre.getText().equals("")
                    || frmProducto.txtPrecioCosto.getText().equals("") || frmProducto.txtPrecioVenta.getText().equals("")
                    || frmProducto.txtDescripcion.getText().equals("") || frmProducto.txtCantidad.getText().equals("")
                    || frmProducto.txtNombreCategoria.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe debe seleccionar un producto para vender");
            } else {
                frmVentaProd.txtCodigo.setText(producto.getCodigo());
                frmVentaProd.txtNombre.setText(producto.getNombre());
                frmVentaProd.txtDescripcion.setText(producto.getDescripcion());
                frmVentaProd.txtPrecioVenta.setText(Float.toString(producto.getPrecioVenta()));
                frmProducto.dispose();
            }
        }       
        if (ae.getSource() == frmProducto.btnSeleccionar) {
            if (frmProducto.txtCodigo.getText().equals("") || frmProducto.txtNombre.getText().equals("")
                    || frmProducto.txtPrecioCosto.getText().equals("") || frmProducto.txtPrecioVenta.getText().equals("")
                    || frmProducto.txtDescripcion.getText().equals("") || frmProducto.txtCantidad.getText().equals("")
                    || frmProducto.txtNombreCategoria.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe debe seleccionar un producto para vender");
            } else {
                frmSolProduto.txtNombre.setText(producto.getNombre());
                frmSolProduto.txtCodigo.setText(producto.getCodigo());
                frmSolProduto.txtCosto.setText(Float.toString(producto.getPrecioCosto()));
                frmProducto.dispose();
                // JOptionPane.showMessageDialog(null, "ingrese una cantidad");
            }
        }

    }

    public void removerEnTabla(JTable tabla) {
        int fila = frmProducto.tblProducto.getSelectedRow();
        modelo2.removeRow(fila);
    }

    public void limpiar() {
        frmProducto.txtBuscarProducto.setText(null);
        frmProducto.txtCantidad.setText(null);
        frmProducto.txtCodigo.setText(null);
        frmProducto.txtDescripcion.setText(null);
        frmProducto.txtNombreCategoria.setText(null);
        frmProducto.txtNombre.setText(null);
        frmProducto.txtPrecioCosto.setText(null);
        frmProducto.txtPrecioVenta.setText(null);
        cargarBDcomboBox();
    }

    public void llenarTablaPorCriterio(JTable tabla, Producto producto) {

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio Costo");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Categoria");
        this.modelo2 = modelo;

        int numeroRegistros = productoDAO.listProductoPorCriterio(producto).size();

        for (int i = 0; i < numeroRegistros; i++) {

            columna[0] = productoDAO.listProductoPorCriterio(producto).get(i).getCodigo();
            columna[1] = productoDAO.listProductoPorCriterio(producto).get(i).getNombre();
            columna[2] = productoDAO.listProductoPorCriterio(producto).get(i).getPrecioCosto();
            columna[3] = productoDAO.listProductoPorCriterio(producto).get(i).getPrecioVenta();
            columna[4] = productoDAO.listProductoPorCriterio(producto).get(i).getDescripcion();
            columna[5] = productoDAO.listProductoPorCriterio(producto).get(i).getCantidadenInventario();
            columna[6] = productoDAO.listProductoPorCriterio(producto).get(i).getCategoria().getNombre();
            modelo.addRow(columna);
        }

    }

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio Costo");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Categoria");
        this.modelo2 = modelo;

        int numeroRegistros = productoDAO.listProductos().size();
        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = productoDAO.listProductos().get(i).getCodigo();
            columna[1] = productoDAO.listProductos().get(i).getNombre();
            columna[2] = productoDAO.listProductos().get(i).getPrecioCosto();
            columna[3] = productoDAO.listProductos().get(i).getPrecioVenta();
            columna[4] = productoDAO.listProductos().get(i).getDescripcion();
            columna[5] = productoDAO.listProductos().get(i).getCantidadenInventario();
            columna[6] = productoDAO.listProductos().get(i).getCategoria().getNombre();
            modelo.addRow(columna);
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == frmProducto.tblProducto) {
            int fila = frmProducto.tblProducto.getSelectedRow();
            frmProducto.txtCodigo.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 0).toString());
            frmProducto.txtNombre.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 1).toString());
            frmProducto.txtPrecioCosto.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 2).toString());
            frmProducto.txtPrecioVenta.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 3).toString());
            frmProducto.txtDescripcion.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 4).toString());
            frmProducto.txtCantidad.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 5).toString());
            frmProducto.txtNombreCategoria.setText(frmProducto.tblProducto.getModel().getValueAt(fila, 6).toString());

            producto.setCodigo(frmProducto.txtCodigo.getText());
            producto.setNombre(frmProducto.txtNombre.getText());
            producto.setPrecioCosto(Float.parseFloat(frmProducto.txtPrecioCosto.getText()));
            producto.setPrecioVenta(Float.parseFloat(frmProducto.txtPrecioVenta.getText()));
            producto.setDescripcion(frmProducto.txtDescripcion.getText());
            producto.setCantidadenInventario(Integer.parseInt(frmProducto.txtCantidad.getText()));
            producto.getCategoria().setNombre(frmProducto.txtNombreCategoria.getText());

        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
