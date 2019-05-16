package controlador;

import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.FrmAdministrarProveedor;
import vista.FrmPrincipal;
import vista.FrmSolicitudProductos;

public class CtrlProveedor implements ActionListener, MouseListener {

    private Proveedor proveedor;
    private ProveedorDAO proveedorDAO;
    private final FrmAdministrarProveedor frmProveedor = new FrmAdministrarProveedor();
    private FrmPrincipal frmPrincipal;
    private FrmSolicitudProductos frmSolProduto;

    private int criterioB = 0;

    Object[] columna = new Object[7];
    DefaultTableModel modelo2 = new DefaultTableModel();

    /**
     * este metodo es el constructor de la clase, inicializa un objeto de la
     * clase Proveedor, ProveedorDAO y JFrame del menu principal
     *
     * @param modProveedor es donde estan todos los atributos de Proveedor
     * @param proveedorDAO es donde se realizan las consultas CRUD
     * @param frmPrincipal es el JFrame pricipal del programa
     */
    public CtrlProveedor(Proveedor modProveedor, ProveedorDAO proveedorDAO, FrmPrincipal frmPrincipal) {

        this.proveedor = modProveedor;
        this.proveedorDAO = proveedorDAO;
        this.frmPrincipal = frmPrincipal;

        this.frmProveedor.btnBuscar.addActionListener(this);
        this.frmProveedor.btnEliminar.addActionListener(this);
        this.frmProveedor.btnGuardar.addActionListener(this);
        this.frmProveedor.btnLimpiar.addActionListener(this);
        this.frmProveedor.btnModificar.addActionListener(this);
        this.frmProveedor.btnMostrarProveedores.addActionListener(this);
        this.frmProveedor.tblProveedor.addMouseListener(this);
    }

    public CtrlProveedor(Proveedor proveedor, ProveedorDAO proveedorDAO, FrmPrincipal frmPrincipal, FrmSolicitudProductos frmSolProduto) {
        this.proveedor = proveedor;
        this.proveedorDAO = proveedorDAO;
        this.frmPrincipal = frmPrincipal;
        this.frmSolProduto = frmSolProduto;

        this.frmProveedor.btnBuscar.addActionListener(this);
        this.frmProveedor.btnMostrarProveedores.addActionListener(this);
        this.frmProveedor.tblProveedor.addMouseListener(this);
        this.frmProveedor.btnLimpiar.addActionListener(this);
    }

    /**
     * Este método inicializa el frame de Administrar Proveedor
     */
    public void iniciar() {

        this.frmPrincipal.dpnEscritorio.add(frmProveedor);
        frmProveedor.show();

        frmProveedor.bgcriterioB.add(frmProveedor.rbtnNIT);
        frmProveedor.bgcriterioB.add(frmProveedor.rbtnRazonSocial);

        frmProveedor.bgEstado.add(frmProveedor.rbtnActivo);
        frmProveedor.bgEstado.add(frmProveedor.rbtnInactivo);
        frmProveedor.txtEstado.setVisible(false);
        frmProveedor.btnSeleccionar.setVisible(false);

        frmProveedor.tblProveedor.setModel(modelo2);
        modelo2.addColumn("NIT");
        modelo2.addColumn("Razon Social");
        modelo2.addColumn("Email");
        modelo2.addColumn("Direccion");
        modelo2.addColumn("Telefono");
        modelo2.addColumn("Numero de cuenta");
        modelo2.addColumn("Estado");

    }

    public void iniciarSolicitud() {
        frmProveedor.btnSeleccionar.addActionListener(this);
        this.frmPrincipal.dpnEscritorio.add(frmProveedor);
        frmProveedor.show();

        frmProveedor.bgcriterioB.add(frmProveedor.rbtnNIT);
        frmProveedor.bgcriterioB.add(frmProveedor.rbtnRazonSocial);

        frmProveedor.bgEstado.add(frmProveedor.rbtnActivo);
        frmProveedor.bgEstado.add(frmProveedor.rbtnInactivo);
        frmProveedor.txtEstado.setVisible(false);

        frmProveedor.btnEliminar.setEnabled(false);
        frmProveedor.btnGuardar.setEnabled(false);
        frmProveedor.btnModificar.setEnabled(false);

        frmProveedor.txtDireccion.setEnabled(false);
        frmProveedor.txtEmail.setEnabled(false);
        frmProveedor.txtEmail.setEnabled(false);
        frmProveedor.txtNit.setEnabled(false);
        frmProveedor.txtNumeroCuenta.setEnabled(false);
        frmProveedor.txtRazonSocial.setEnabled(false);
        frmProveedor.txtTelefono.setEnabled(false);

        frmProveedor.tblProveedor.setModel(modelo2);
        modelo2.addColumn("NIT");
        modelo2.addColumn("Razon Social");
        modelo2.addColumn("Email");
        modelo2.addColumn("Direccion");
        modelo2.addColumn("Telefono");
        modelo2.addColumn("Numero de cuenta");
        modelo2.addColumn("Estado");
    }

    /**
     * Este metodo es para que la clase "escuche" los eventos de los componentes
     * del frame
     *
     * @param ae es el objeto de la clase ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmProveedor.btnBuscar) {

            if (frmProveedor.rbtnNIT.isSelected()) {
                criterioB = 1;

                proveedor.setNit(frmProveedor.txtBuscarProveedor.getText());

            } else if (frmProveedor.rbtnRazonSocial.isSelected()) {
                criterioB = 2;
                proveedor.setRazonSocial(frmProveedor.txtBuscarProveedor.getText());

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un criterio de busqueda");
            }
            if (frmProveedor.txtBuscarProveedor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar el campo de busqueda");
            } else {

                if (proveedorDAO.read(proveedor, criterioB)) {
                    llenarTablaPorCriterio(frmProveedor.tblProveedor, proveedor);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuentra el resultado");
                }
            }
        }

        if (ae.getSource() == frmProveedor.btnMostrarProveedores) {

            llenarTabla(frmProveedor.tblProveedor);
        }
        if (ae.getSource() == frmProveedor.btnModificar) {
            String estado = "";

            if (frmProveedor.rbtnActivo.isSelected()) {
                estado = "Activo";
            } else if (frmProveedor.rbtnInactivo.isSelected()) {
                estado = "Inactivo";
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un estado");
            }

            if (frmProveedor.txtNit.getText().equals("") || frmProveedor.txtRazonSocial.getText().equals("")
                    || frmProveedor.txtEmail.getText().equals("") || frmProveedor.txtDireccion.getText().equals("")
                    || frmProveedor.txtNumeroCuenta.getText().equals("") || frmProveedor.txtTelefono.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe tener los campos llenos");

            } else {
                if (frmProveedor.txtNit.getText().equals(proveedor.getNit())
                        && frmProveedor.txtRazonSocial.getText().equals(proveedor.getRazonSocial())
                        && frmProveedor.txtEmail.getText().equals(proveedor.getEmail())
                        && frmProveedor.txtDireccion.getText().equals(proveedor.getDireccion())
                        && frmProveedor.txtNumeroCuenta.getText().equals(Integer.toString(proveedor.getnCuenta()))
                        && frmProveedor.txtTelefono.getText().equals(Integer.toString(proveedor.getTelefono()))
                        && estado.equals(proveedor.getEstado())) {
                    JOptionPane.showMessageDialog(null, "No existen cambios");
                    limpiar();
                } else {

                    proveedor.setNit(frmProveedor.txtNit.getText());
                    proveedor.setRazonSocial(frmProveedor.txtRazonSocial.getText());
                    proveedor.setEmail(frmProveedor.txtEmail.getText());
                    proveedor.setDireccion(frmProveedor.txtDireccion.getText());
                    proveedor.setnCuenta(Integer.parseInt(frmProveedor.txtNumeroCuenta.getText()));
                    proveedor.setTelefono(Integer.parseInt(frmProveedor.txtTelefono.getText()));
                    proveedor.setEstado(estado);

                    if (proveedorDAO.update(proveedor)) {
                        int Fila = frmProveedor.tblProveedor.getSelectedRow();
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtNit.getText(), Fila, 0);
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtRazonSocial.getText(), Fila, 1);
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtEmail.getText(), Fila, 2);
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtDireccion.getText(), Fila, 3);
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtNumeroCuenta.getText(), Fila, 4);
                        frmProveedor.tblProveedor.setValueAt(frmProveedor.txtTelefono.getText(), Fila, 5);
                        frmProveedor.tblProveedor.setValueAt(estado, Fila, 6);
                        JOptionPane.showMessageDialog(null, "Proveedor Modificado");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar");
                    }
                }

            }
        }

        if (ae.getSource() == frmProveedor.btnLimpiar) {
            limpiar();
        }
        if (ae.getSource() == frmProveedor.btnEliminar) {
            if (frmProveedor.txtNit.getText().equals("") || frmProveedor.txtRazonSocial.getText().equals("")
                    || frmProveedor.txtEmail.getText().equals("") || frmProveedor.txtDireccion.getText().equals("")
                    || frmProveedor.txtNumeroCuenta.getText().equals("") || frmProveedor.txtTelefono.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar el proveedor que desea eliminar");
                limpiar();
            } else {
                proveedor.setNit(frmProveedor.txtNit.getText());
                proveedor.setRazonSocial(frmProveedor.txtRazonSocial.getText());
                proveedor.setEmail(frmProveedor.txtEmail.getText());
                proveedor.setDireccion(frmProveedor.txtDireccion.getText());
                proveedor.setnCuenta(Integer.parseInt(frmProveedor.txtNumeroCuenta.getText()));
                proveedor.setTelefono(Integer.parseInt(frmProveedor.txtTelefono.getText()));
                if (proveedorDAO.delete(proveedor)) {
                    JOptionPane.showMessageDialog(null, "Proveedor Eliminado");
                    limpiar();
                    removerEnTabla(frmProveedor.tblProveedor);
                }
            }
        }

        if (ae.getSource() == frmProveedor.btnGuardar) {

            if (frmProveedor.txtNit.getText().equals("") || frmProveedor.txtRazonSocial.getText().equals("")
                    || frmProveedor.txtEmail.getText().equals("") || frmProveedor.txtDireccion.getText().equals("")
                    || frmProveedor.txtNumeroCuenta.getText().equals("") || frmProveedor.txtTelefono.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "los campos a registrar deben de estar llenos");
            } else {
                if (!esEmail(frmProveedor.txtEmail.getText())) {
                    JOptionPane.showMessageDialog(null, "el correo ingresado no es valido");
                } else {
                    String estado = "";

                    if (frmProveedor.rbtnActivo.isSelected()) {
                        estado = "Activo";
                    } else if (frmProveedor.rbtnInactivo.isSelected()) {
                        estado = "Inactivo";
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un estado");
                    }

                    if (estado.equals("Activo") || estado.equals("Inactivo")) {
                        proveedor.setNit(frmProveedor.txtNit.getText());
                        proveedor.setRazonSocial(frmProveedor.txtRazonSocial.getText());
                        proveedor.setEmail(frmProveedor.txtEmail.getText());
                        proveedor.setDireccion(frmProveedor.txtDireccion.getText());
                        proveedor.setnCuenta(Integer.parseInt(frmProveedor.txtNumeroCuenta.getText()));
                        proveedor.setTelefono(Integer.parseInt(frmProveedor.txtTelefono.getText()));
                        proveedor.setEstado(estado);

                        if (proveedorDAO.create(proveedor)) {

                            JOptionPane.showMessageDialog(null, "Registro guardado");
                            Object[] f = new Object[7];
                            f[0] = frmProveedor.txtNit.getText();
                            f[1] = frmProveedor.txtRazonSocial.getText();
                            f[2] = frmProveedor.txtEmail.getText();
                            f[3] = frmProveedor.txtDireccion.getText();
                            f[4] = frmProveedor.txtNumeroCuenta.getText();
                            f[5] = frmProveedor.txtTelefono.getText();
                            f[6] = estado;
                            this.modelo2.addRow(f);
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guargar datos");
                            limpiar();
                        }
                    }
                }
            }
        }
        if (ae.getSource() == frmProveedor.btnSeleccionar) {
            if (frmProveedor.txtNit.getText().equals("") || frmProveedor.txtRazonSocial.getText().equals("")
                    || frmProveedor.txtEmail.getText().equals("") || frmProveedor.txtDireccion.getText().equals("")
                    || frmProveedor.txtNumeroCuenta.getText().equals("") || frmProveedor.txtTelefono.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe escojer un proveedor");
            } else {
                if (proveedor.getEstado().equals("Inactivo")) {
                    JOptionPane.showMessageDialog(null, "Este proveedor esta inactivo, seleccione otro!");
                } else {
                    frmSolProduto.txtProveedor.setText(proveedor.getRazonSocial());
                    frmSolProduto.txtProveedor.setEnabled(false);
                    frmProveedor.dispose();
                }
            }
        }
    }

    /**
     * este metodo es para validar un correo
     *
     * @param correo este parametro lo recibe para verificarlo
     * @return un true o false dependiendo si el correo es valido
     */
    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }

    /**
     * Al momento de eliminar, este metodo borra la fila del dato
     * correspondiente al que se borro
     *
     * @param tabla
     */
    public void removerEnTabla(JTable tabla) {

        int fila = frmProveedor.tblProveedor.getSelectedRow();
        modelo2.removeRow(fila);

    }

    /**
     * Restablece los txtField en null
     */
    public void limpiar() {
        frmProveedor.txtBuscarProveedor.setText(null);
        frmProveedor.txtDireccion.setText(null);
        frmProveedor.txtNit.setText(null);
        frmProveedor.txtRazonSocial.setText(null);
        frmProveedor.txtEmail.setText(null);
        frmProveedor.txtNumeroCuenta.setText(null);
        frmProveedor.txtRazonSocial.setText(null);
        frmProveedor.txtTelefono.setText(null);
        frmProveedor.bgEstado.clearSelection();
    }

    /**
     * Este método llena la tabla segun el criterio de búsqueda marcado.
     *
     * @param tabla
     * @param proveedor
     */
    public void llenarTablaPorCriterio(JTable tabla, Proveedor proveedor) {

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("NIT");
        modelo.addColumn("Razon Social");
        modelo.addColumn("Email");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Numero de cuenta");
        modelo.addColumn("Estado");
        this.modelo2 = modelo;
        int numeroRegistros = proveedorDAO.listProveedorPorCriterio(proveedor).size();

        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getNit();
            columna[1] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getRazonSocial();
            columna[2] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getEmail();
            columna[3] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getDireccion();
            columna[4] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getTelefono();
            columna[5] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getnCuenta();
            columna[6] = proveedorDAO.listProveedorPorCriterio(proveedor).get(i).getEstado();

            modelo.addRow(columna);

        }
    }

    /**
     * Al momento de seleccionar el criterio, este método llena la tabla con los
     * datos correspondientes
     *
     * @param tabla
     */
    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("NIT");
        modelo.addColumn("Razon social");
        modelo.addColumn("Email");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Numero de cuenta");
        modelo.addColumn("Estado");

        this.modelo2 = modelo;
        int numeroRegistros = proveedorDAO.listProveedores().size();
        if (numeroRegistros == 0) {
            JOptionPane.showMessageDialog(null, "No hay proveedores registrados");
        } else {
            for (int i = 0; i < numeroRegistros; i++) {
                columna[0] = proveedorDAO.listProveedores().get(i).getNit();
                columna[1] = proveedorDAO.listProveedores().get(i).getRazonSocial();
                columna[2] = proveedorDAO.listProveedores().get(i).getEmail();
                columna[3] = proveedorDAO.listProveedores().get(i).getDireccion();
                columna[4] = proveedorDAO.listProveedores().get(i).getTelefono();
                columna[5] = proveedorDAO.listProveedores().get(i).getnCuenta();
                columna[6] = proveedorDAO.listProveedores().get(i).getEstado();
                modelo.addRow(columna);
            }
        }

    }

    /**
     * Esté metodo pone los datos de la tabla a los txtField
     *
     * @param me es el objeto de la clase MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == frmProveedor.tblProveedor) {
            int fila = frmProveedor.tblProveedor.getSelectedRow();

            frmProveedor.txtNit.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 0).toString());
            frmProveedor.txtRazonSocial.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 1).toString());
            frmProveedor.txtEmail.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 2).toString());
            frmProveedor.txtDireccion.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 3).toString());
            frmProveedor.txtTelefono.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 4).toString());
            frmProveedor.txtNumeroCuenta.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 5).toString());
            frmProveedor.txtEstado.setText(frmProveedor.tblProveedor.getModel().getValueAt(fila, 6).toString());

            //poner el estado           
            proveedor.setNit(frmProveedor.txtNit.getText());
            proveedor.setRazonSocial(frmProveedor.txtRazonSocial.getText());
            proveedor.setEmail(frmProveedor.txtEmail.getText());
            proveedor.setDireccion(frmProveedor.txtDireccion.getText());
            proveedor.setTelefono(Integer.parseInt(frmProveedor.txtTelefono.getText()));
            proveedor.setnCuenta(Integer.parseInt(frmProveedor.txtNumeroCuenta.getText()));
            proveedor.setEstado(frmProveedor.txtEstado.getText());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
