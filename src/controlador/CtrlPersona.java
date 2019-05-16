package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista. FrmAdministrarEmpleado;
import vista.FrmPrincipal;

public class CtrlPersona implements ActionListener, MouseListener {

    

    private Persona persona;
    private PersonaDAO personaDAO;
    private FrmPrincipal frmPrincipal;
    private final  FrmAdministrarEmpleado frmPersona = new  FrmAdministrarEmpleado();
    private int criterioB = 0;

    Object[] columna = new Object[10];
    DefaultTableModel modelo2 = new DefaultTableModel();

    public CtrlPersona(Persona persona, PersonaDAO personaDAO, FrmPrincipal frmPrincipal) {

        this.persona = persona;
        this.personaDAO = personaDAO;
        this.frmPrincipal = frmPrincipal;

        this.frmPersona.btnBuscar.addActionListener(this);
        this.frmPersona.btnMostarEmpleados.addActionListener(this);
        this.frmPersona.btnModificar.addActionListener(this);
        this.frmPersona.btnGuardar.addActionListener(this);
        this.frmPersona.btnEliminar.addActionListener(this);
        this.frmPersona.btnLimpiar.addActionListener(this);

        this.frmPersona.tblEmpleado.addMouseListener(this);

    }

    public void iniciar() {
        this.frmPrincipal.dpnEscritorio.add(frmPersona);
        frmPersona.show();
        frmPersona.add(frmPersona.rbtnCodigo);
        frmPersona.bgCriterioB.add(frmPersona.rbtnCedula);
        frmPersona.bgCriterioB.add(frmPersona.rbtnNombre);
        frmPersona.tblEmpleado.setModel(modelo2);
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Cedula");
        modelo2.addColumn("Nombre");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmPersona.btnBuscar) {
            if (frmPersona.rbtnCodigo.isSelected()) {
                criterioB = 1;
                persona.setCodigo(frmPersona.txtBuscarEmpleado.getText());
            } else if (frmPersona.rbtnCedula.isSelected()) {
                criterioB = 2;
                persona.setCedula(Integer.parseInt(frmPersona.txtBuscarEmpleado.getText()));
            } else if (frmPersona.rbtnNombre.isSelected()) {
                criterioB = 3;
                persona.setNombre(frmPersona.txtBuscarEmpleado.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un criterio de busqueda");
            }
            if (frmPersona.txtBuscarEmpleado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Llene el campo de busqueda");
            } else {
                if (personaDAO.read(persona, criterioB)) {
                    llenarTablaPorCriterio(frmPersona.tblEmpleado, persona);
                } else {
                    JOptionPane.showMessageDialog(null, "No se escontro resultado");
                }
            }
        }
        if (e.getSource() == frmPersona.btnMostarEmpleados) {
            llenarTabla(frmPersona.tblEmpleado);
        }
        if (e.getSource() == frmPersona.btnModificar) {

            String item;
            int indice;
            indice = frmPersona.jCBtipoEmpleado.getSelectedIndex();
            item = frmPersona.jCBtipoEmpleado.getItemAt(indice);
            int tipo_empleado = 0;

            if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "ADMINISTRADOR") {
                tipo_empleado = 1;
            } else if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "EMPLEADO") {
                tipo_empleado = 2;
            } else if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "TECNICO") {
                tipo_empleado = 3;
            }

            if (frmPersona.txtCodigo.getText().equals("") || frmPersona.txtNombre.getText().equals("")
                    || frmPersona.txtCedula.getText().equals("") || frmPersona.txtTelefono.getText().equals("")
                    || frmPersona.txtEmail.getText().equals("") || frmPersona.txtInicioDiaLabor.getText().equals("")
                    || frmPersona.txtFinDiaLabor.getText().equals("") || frmPersona.txtUsuario.getText().equals("")
                    || frmPersona.txtContaseña.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe tener todos los campos llenos");

            } else {
                if (frmPersona.txtCodigo.getText().equals(persona.getCodigo())
                        && frmPersona.txtNombre.getText().equals(persona.getNombre())
                        && frmPersona.txtCedula.getText().equals(persona.getCedula())
                        && frmPersona.txtTelefono.getText().equals(persona.getTelefono())
                        && frmPersona.txtEmail.getText().equals(persona.getEmail())
                        && frmPersona.txtInicioDiaLabor.getText().equals(persona.getInicioDiaLabor())
                        && frmPersona.txtFinDiaLabor.getText().equals(persona.getFinDiaLabor())
                        && frmPersona.txtUsuario.getText().equals(persona.getUsuario())
                        && frmPersona.txtContaseña.getText().equals(persona.getContraseña())) {
                    JOptionPane.showMessageDialog(null, "No existen cambios");
                    limpiar();
                } else {
                    persona.setCodigo(frmPersona.txtCodigo.getText());
                    persona.setNombre(frmPersona.txtNombre.getText());
                    persona.setCedula(Integer.parseInt(frmPersona.txtCedula.getText()));
                    persona.setTelefono(Integer.parseInt(frmPersona.txtTelefono.getText()));
                    persona.setEmail(frmPersona.txtEmail.getText());
                    persona.setInicioDiaLabor(frmPersona.txtInicioDiaLabor.getText());
                    persona.setFinDiaLabor(frmPersona.txtFinDiaLabor.getText());
                    persona.setUsuario(frmPersona.txtUsuario.getText());
                    persona.setContraseña(frmPersona.txtContaseña.getText());

                    if (personaDAO.update(persona, tipo_empleado)) {
                        int fila = frmPersona.tblEmpleado.getSelectedRow();
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtCodigo.getText(), fila, 0);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtNombre.getText(), fila, 1);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtCedula.getText(), fila, 2);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtTelefono.getText(), fila, 3);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtEmail.getText(), fila, 4);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtInicioDiaLabor.getText(), fila, 5);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtFinDiaLabor.getText(), fila, 6);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtUsuario.getText(), fila, 7);
                        frmPersona.tblEmpleado.setValueAt(frmPersona.txtContaseña.getText(), fila, 8);

                        JOptionPane.showMessageDialog(null, "Persona modificada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar");
                    }

                }

            }
        }

        if (e.getSource() == frmPersona.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == frmPersona.btnEliminar) {

            if (frmPersona.txtCodigo.getText().equals("") || frmPersona.txtNombre.getText().equals("")
                    || frmPersona.txtCedula.getText().equals("") || frmPersona.txtTelefono.getText().equals("")
                    || frmPersona.txtEmail.getText().equals("") || frmPersona.txtInicioDiaLabor.getText().equals("")
                    || frmPersona.txtFinDiaLabor.getText().equals("") || frmPersona.txtUsuario.getText().equals("")
                    || frmPersona.txtContaseña.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar la persona a eliminar");
                limpiar();
            } else {
                persona.setCodigo(frmPersona.txtCodigo.getText());
                persona.setNombre(frmPersona.txtNombre.getText());
                persona.setCedula(Integer.parseInt(frmPersona.txtCedula.getText()));
                persona.setTelefono(Integer.parseInt(frmPersona.txtTelefono.getText()));
                persona.setEmail(frmPersona.txtEmail.getText());
                persona.setInicioDiaLabor(frmPersona.txtInicioDiaLabor.getText());
                persona.setFinDiaLabor(frmPersona.txtFinDiaLabor.getText());
                persona.setUsuario(frmPersona.txtUsuario.getText());
                persona.setContraseña(frmPersona.txtContaseña.getText());
                if (personaDAO.delete(persona)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                    removerEnTabla(frmPersona.tblEmpleado);

                }
            }
        }

        if (e.getSource() == frmPersona.btnGuardar) {

            String item;
            int indice;
            indice = frmPersona.jCBtipoEmpleado.getSelectedIndex();
            item = frmPersona.jCBtipoEmpleado.getItemAt(indice);
            int tipo_empleado = 0;

            if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "ADMINISTRADOR") {
                tipo_empleado = 1;
            } else if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "EMPLEADO") {
                tipo_empleado = 2;
            } else if (frmPersona.jCBtipoEmpleado.getItemAt(indice) == "TECNICO") {
                tipo_empleado = 3;
            }

            if (frmPersona.txtCodigo.getText().equals("") || frmPersona.txtNombre.getText().equals("")
                    || frmPersona.txtCedula.getText().equals("") || frmPersona.txtTelefono.getText().equals("")
                    || frmPersona.txtEmail.getText().equals("") || frmPersona.txtInicioDiaLabor.getText().equals("")
                    || frmPersona.txtFinDiaLabor.getText().equals("") || frmPersona.txtUsuario.getText().equals("")
                    || frmPersona.txtContaseña.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos deben estar llenos");
            } else {
                persona.setCodigo(frmPersona.txtCodigo.getText());
                persona.setNombre(frmPersona.txtNombre.getText());
                persona.setCedula(Integer.parseInt(frmPersona.txtCedula.getText()));
                persona.setTelefono(Integer.parseInt(frmPersona.txtTelefono.getText()));
                persona.setEmail(frmPersona.txtEmail.getText());
                persona.setInicioDiaLabor(frmPersona.txtInicioDiaLabor.getText());
                persona.setFinDiaLabor(frmPersona.txtFinDiaLabor.getText());
                persona.setUsuario(frmPersona.txtUsuario.getText());
                persona.setContraseña(frmPersona.txtContaseña.getText());
                if (personaDAO.create(persona, tipo_empleado)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    Object[] f = new Object[2];
                    f[0] = frmPersona.txtCodigo.getText();
                    f[1] = frmPersona.txtNombre.getText();
                    f[2] = frmPersona.txtCedula.getText();
                    f[3] = frmPersona.txtTelefono.getText();
                    f[4] = frmPersona.txtEmail.getText();
                    f[5] = frmPersona.txtInicioDiaLabor.getText();
                    f[6] = frmPersona.txtFinDiaLabor.getText();
                    f[7] = frmPersona.txtUsuario.getText();
                    f[8] = frmPersona.txtContaseña.getText();
                    this.modelo2.addRow(f);
                    limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar Datos");
                    limpiar();
                }
            }
        }
    }

    public void removerEnTabla(JTable tabla) {
        int fila = frmPersona.tblEmpleado.getSelectedRow();
        modelo2.removeRow(fila);
    }

    public void limpiar() {
        frmPersona.txtBuscarEmpleado.setText(null);
        frmPersona.txtCodigo.setText(null);
        frmPersona.txtNombre.setText(null);
        frmPersona.txtCedula.setText(null);
        frmPersona.txtTelefono.setText(null);
        frmPersona.txtEmail.setText(null);
        frmPersona.txtInicioDiaLabor.setText(null);
        frmPersona.txtFinDiaLabor.setText(null);
        frmPersona.txtUsuario.setText(null);
        frmPersona.txtContaseña.setText(null);
        cargarBDcomboBox();

    }

    public void llenarTablaPorCriterio(JTable tabla, Persona persona) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cédula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Inicio dia labor");
        modelo.addColumn("Fin dia labor");
        modelo.addColumn("Tipo_Empleado");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        this.modelo2 = modelo;

        int numeroRegistros = personaDAO.listPersonaPorCriterio(persona).size();

        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = personaDAO.listPersonaPorCriterio(persona).get(i).getCodigo();
            columna[1] = personaDAO.listPersonaPorCriterio(persona).get(i).getNombre();
            columna[2] = personaDAO.listPersonaPorCriterio(persona).get(i).getCedula();
            columna[3] = personaDAO.listPersonaPorCriterio(persona).get(i).getTelefono();
            columna[4] = personaDAO.listPersonaPorCriterio(persona).get(i).getEmail();
            columna[5] = personaDAO.listPersonaPorCriterio(persona).get(i).getInicioDiaLabor();
            columna[6] = personaDAO.listPersonaPorCriterio(persona).get(i).getFinDiaLabor();
            columna[7] = personaDAO.listPersonaPorCriterio(persona).get(i).getTipo_empleado();
            columna[8] = personaDAO.listPersonaPorCriterio(persona).get(i).getUsuario();
            columna[9] = personaDAO.listPersonaPorCriterio(persona).get(i).getContraseña();
            modelo.addRow(columna);

        }
    }

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cédula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Inicio dia labor");
        modelo.addColumn("Fin dia labor");
        modelo.addColumn("Tipo_Empleado");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");

        this.modelo2 = modelo;
        int numeroRegistros = personaDAO.listPersona().size();
        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = personaDAO.listPersona().get(i).getCodigo();
            columna[1] = personaDAO.listPersona().get(i).getNombre();
            columna[2] = personaDAO.listPersona().get(i).getCedula();
            columna[3] = personaDAO.listPersona().get(i).getTelefono();
            columna[4] = personaDAO.listPersona().get(i).getEmail();
            columna[5] = personaDAO.listPersona().get(i).getInicioDiaLabor();
            columna[6] = personaDAO.listPersona().get(i).getFinDiaLabor();
            columna[7] = personaDAO.listPersona().get(i).getTipo_empleado();
            columna[8] = personaDAO.listPersona().get(i).getUsuario();
            columna[9] = personaDAO.listPersona().get(i).getContraseña();
            modelo.addRow(columna);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmPersona.tblEmpleado) {
            int fila = frmPersona.tblEmpleado.getSelectedRow();

            frmPersona.txtCodigo.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 0).toString());
            frmPersona.txtNombre.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 2).toString());
            frmPersona.txtCedula.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 1).toString());
            frmPersona.txtTelefono.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 3).toString());
            frmPersona.txtEmail.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 4).toString());
            frmPersona.txtInicioDiaLabor.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 5).toString());
            frmPersona.txtFinDiaLabor.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 6).toString());
            frmPersona.txtUsuario.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 7).toString());
            //frmPersona.txtContraseña.setText(frmPersona.tblEmpleado.getModel().getValueAt(fila, 8).toString());

            persona.setCodigo(frmPersona.txtCodigo.getText());
            persona.setNombre(frmPersona.txtNombre.getText());
            persona.setCedula(Integer.parseInt(frmPersona.txtCedula.getText()));
            persona.setTelefono(Integer.parseInt(frmPersona.txtTelefono.getText()));
            persona.setEmail(frmPersona.txtEmail.getText());
            persona.setInicioDiaLabor(frmPersona.txtInicioDiaLabor.getText());
            persona.setFinDiaLabor(frmPersona.txtFinDiaLabor.getText());
            persona.setUsuario(frmPersona.txtUsuario.getText());
            //persona.setContraseña(frmPersona.txtContraseña.getText());
        }
    }
    
    public void cargarBDcomboBox() {
        frmPersona.jCBtipoEmpleado.removeAllItems();
        ArrayList<String> lista;
        lista = personaDAO.listTipo_Empleado();

        for (int i = 0; i < lista.size(); i++) {
            if (i == 0) {
                frmPersona.jCBtipoEmpleado.addItem("seleccione una opcion");
            }
            frmPersona.jCBtipoEmpleado.addItem(lista.get(i));
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


}
