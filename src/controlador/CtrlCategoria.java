package controlador;

import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.FrmAdministrarCategoria;
import vista.FrmPrincipal;


public class CtrlCategoria implements ActionListener, MouseListener {

    private Categoria categoria;
    private CategoriaDAO categoriaDAO;
    private final FrmAdministrarCategoria frmCategoria = new FrmAdministrarCategoria();
    private FrmPrincipal frmPrincipal;
    private int criterioB = 0;

    Object[] columna = new Object[2];
    DefaultTableModel modelo2= new DefaultTableModel();
/**
 * Este método es el constructor de la clase, inicializa un objeto de la clase Categoria, CategoriaDAO y JFrame del menu principal
 * @param modCategoria es donde estan todos los atributos de categoria
 * @param categoriaDAO es donde se realizan las consultas CRUD
 * @param frmPrincipal es el JFrame pricipal del programa
 */
    public CtrlCategoria(Categoria modCategoria, CategoriaDAO categoriaDAO, FrmPrincipal frmPrincipal) {

        this.categoria = modCategoria;
        this.categoriaDAO = categoriaDAO;
        this.frmPrincipal = frmPrincipal;

        this.frmCategoria.btnBuscar.addActionListener(this);
        this.frmCategoria.btnEliminar.addActionListener(this);
        this.frmCategoria.btnGuardar.addActionListener(this);
        this.frmCategoria.btnLimpiar.addActionListener(this);
        this.frmCategoria.btnModificar.addActionListener(this);
        this.frmCategoria.btnMostrarCategorias.addActionListener(this);
        //de MouseListener
        this.frmCategoria.tblCategoria.addMouseListener(this); 
        
    }
/**
 * Este método inicializa el Frame de Administrar Categoria
 */
    public void iniciar() {
        this.frmPrincipal.dpnEscritorio.add(frmCategoria);
        frmCategoria.show();
        frmCategoria.bgcriterioB.add(frmCategoria.rbtnNombre);
        frmCategoria.bgcriterioB.add(frmCategoria.rbtnIDcategoria);
        frmCategoria.tblCategoria.setModel(modelo2);
        frmCategoria.btnModificar.setEnabled(false);
        modelo2.addColumn("ID categoria");
        modelo2.addColumn("Nombre");
    }
/**
 * Este método es para que la clase "escuche" los eventos de los componentes del frame
 * @param ae objeto de la clase ActionEvent
 */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmCategoria.btnBuscar) {    

            if (frmCategoria.rbtnIDcategoria.isSelected()) {

                criterioB = 1;
                
                categoria.setIdCategoria(frmCategoria.txtBuscarCategoria.getText());
            } else if (frmCategoria.rbtnNombre.isSelected()) {

                criterioB = 2;
                
                categoria.setNombre(frmCategoria.txtBuscarCategoria.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un criterio de busquda");
            }
            if (frmCategoria.txtBuscarCategoria.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar el campo de busqueda");
            } else {             
                if (categoriaDAO.read(categoria, criterioB)) {
                   
                    llenarTablaPorCriterio(frmCategoria.tblCategoria, categoria);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuentra el resultado");
                }
            }

        }
        if (ae.getSource() == frmCategoria.btnMostrarCategorias) {

            llenarTabla(frmCategoria.tblCategoria);
        }
        if (ae.getSource() == frmCategoria.btnModificar) {

            if (frmCategoria.txtIDcategoria.getText().equals("") || frmCategoria.txtNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe tener los dos campos llenos");
            } else {
                ///
                if (frmCategoria.txtIDcategoria.getText().equals(categoria.getIdCategoria()) && frmCategoria.txtNombre.getText().equals(categoria.getNombre())) {
                    JOptionPane.showMessageDialog(null, "No existen cambios");
                    limpiar();
                } else {
                    categoria.setIdCategoria(frmCategoria.txtIDcategoria.getText());
                    categoria.setNombre(frmCategoria.txtNombre.getText());
                    if (categoriaDAO.update(categoria)) {
                        int Fila = frmCategoria.tblCategoria.getSelectedRow();
                        frmCategoria.tblCategoria.setValueAt(frmCategoria.txtIDcategoria.getText(), Fila, 0);
                        frmCategoria.tblCategoria.setValueAt(frmCategoria.txtNombre.getText(), Fila, 1);
                        JOptionPane.showMessageDialog(null, "Producto modificado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar");
                    }
                }

            }
        }
        if (ae.getSource() == frmCategoria.btnLimpiar) {
            limpiar();
        }
        if (ae.getSource() == frmCategoria.btnEliminar) {
            if (frmCategoria.txtIDcategoria.getText().equals("") || frmCategoria.txtNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "debe seleccionar la categoria que desea eliminar");
                limpiar();
            } else {
                categoria.setIdCategoria(frmCategoria.txtIDcategoria.getText());
                categoria.setNombre(frmCategoria.txtNombre.getText());
                if (categoriaDAO.delete(categoria)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                    removerEnTabla(frmCategoria.tblCategoria);
                }
            }
        }
        if (ae.getSource() == frmCategoria.btnGuardar) {
            
            if (frmCategoria.txtIDcategoria.getText().equals("") || frmCategoria.txtNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "los campos a registrar deben de estar llenos");
            } else {
                categoria.setIdCategoria(frmCategoria.txtIDcategoria.getText());
                categoria.setNombre(frmCategoria.txtNombre.getText());
                if (categoriaDAO.create(categoria)) {

                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    Object[] f = new Object[2];
                    f[0] = frmCategoria.txtIDcategoria.getText();
                    f[1] = frmCategoria.txtNombre.getText();
                    this.modelo2.addRow(f);
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guargar datos");
                    limpiar();
                }
            }

        }

    }
/**
 * Al momento de eliminar, este método borra la fila del dato correspondiente al que se borro
 * @param tabla 
 */
    public void removerEnTabla(JTable tabla) {

        int fila = frmCategoria.tblCategoria.getSelectedRow();
        modelo2.removeRow(fila);

    }
/**
 * Reestablece los txtField en null
 */
    public void limpiar() {
        frmCategoria.txtBuscarCategoria.setText(null);
        frmCategoria.txtIDcategoria.setText(null);
        frmCategoria.txtNombre.setText(null);
    }
/**
 * Este método llena la tabla según el criterio de búsqueda
 * @param tabla 
 * @param categoria 
 */
    public void llenarTablaPorCriterio(JTable tabla, Categoria categoria) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("ID categoria");
        modelo.addColumn("Nombre");
        this.modelo2 = modelo;
        int numeroRegistros = categoriaDAO.listCategoriaPorCriterio(categoria).size();

        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = categoriaDAO.listCategoriaPorCriterio(categoria).get(i).getIdCategoria();
            columna[1] = categoriaDAO.listCategoriaPorCriterio(categoria).get(i).getNombre();
            modelo.addRow(columna);
        }
    }
    
    /**
     * Al momento de seleccionar el criterio, este método llena la tabla con los datos correspondientes
     * @param tabla 
     */

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("ID categoria");
        modelo.addColumn("Nombre");

        this.modelo2 = modelo;
        int numeroRegistros = categoriaDAO.listCategorias().size();
        
        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = categoriaDAO.listCategorias().get(i).getIdCategoria();
            columna[1] = categoriaDAO.listCategorias().get(i).getNombre();
            modelo.addRow(columna);
        }
    }
    /**
     * Esté metodo pone los datos de la tabla a los txtField
     * @param me es el objeto de la clase MouseEvent
     */

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == frmCategoria.tblCategoria) {
            frmCategoria.btnModificar.setEnabled(true);
            int fila = frmCategoria.tblCategoria.getSelectedRow();
            frmCategoria.txtIDcategoria.setText(frmCategoria.tblCategoria.getModel().getValueAt(fila, 0).toString());
            frmCategoria.txtNombre.setText(frmCategoria.tblCategoria.getModel().getValueAt(fila, 1).toString());
            categoria.setIdCategoria(frmCategoria.txtIDcategoria.getText());
            categoria.setNombre(frmCategoria.txtNombre.getText());
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

}
