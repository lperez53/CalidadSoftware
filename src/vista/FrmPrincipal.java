
package vista;

public class FrmPrincipal extends javax.swing.JFrame {

    public FrmPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        dpnEscritorio = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbcargo = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuInicio = new javax.swing.JMenu();
        mnuSalir = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mnuInicioEmpleado = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuInicioProveedor = new javax.swing.JMenuItem();
        mnuSolicitarProductos = new javax.swing.JMenuItem();
        mnuInicioProducto = new javax.swing.JMenuItem();
        mnuInicioCategoria = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuInicioRespaldarBD = new javax.swing.JMenuItem();
        mnuMovimientos = new javax.swing.JMenu();
        mnuVenderProducto = new javax.swing.JMenuItem();
        mnuConsultarCaja = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dpnEscritorio.setBackground(new java.awt.Color(204, 204, 255));
        dpnEscritorio.setPreferredSize(new java.awt.Dimension(937, 471));

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Empleado.png"))); // NOI18N
        jLabel2.setText("EMPLEADO:");

        lbcargo.setText("<cargo>");

        lbNombre.setText("<nombre>");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Fecha.png"))); // NOI18N
        jLabel5.setText("FECHA:");

        lbFecha.setText("<fecha>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbcargo)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lbFecha)
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFecha)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbcargo)
                        .addGap(18, 18, 18)
                        .addComponent(lbNombre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Inicio.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(25, 25, 25))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Pan.gif"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Jugo.gif"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Leche.gif"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Spray.gif"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Queso.gif"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/PapelH.gif"))); // NOI18N

        dpnEscritorio.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpnEscritorio.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpnEscritorioLayout = new javax.swing.GroupLayout(dpnEscritorio);
        dpnEscritorio.setLayout(dpnEscritorioLayout);
        dpnEscritorioLayout.setHorizontalGroup(
            dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpnEscritorioLayout.createSequentialGroup()
                .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dpnEscritorioLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpnEscritorioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(1, 1, 1)))
                .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dpnEscritorioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addGroup(dpnEscritorioLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        dpnEscritorioLayout.setVerticalGroup(
            dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpnEscritorioLayout.createSequentialGroup()
                .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpnEscritorioLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel6)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel7)
                        .addGap(16, 16, 16))
                    .addGroup(dpnEscritorioLayout.createSequentialGroup()
                        .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dpnEscritorioLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpnEscritorioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addGap(6, 6, 6)))
                        .addGroup(dpnEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dpnEscritorioLayout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel8)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel4)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jMenuBar1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(136, 34));
        jMenuBar1.setVerifyInputWhenFocusTarget(false);

        mnuInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home.png"))); // NOI18N
        mnuInicio.setText("Inicio");

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N
        mnuSalir.setText("Cerrar Sesión");
        mnuInicio.add(mnuSalir);

        jMenuBar1.add(mnuInicio);

        mnuMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Tienda.png"))); // NOI18N
        mnuMantenimiento.setText("Mantenimiento");

        mnuInicioEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Empleado.png"))); // NOI18N
        mnuInicioEmpleado.setText("Empleado");
        mnuMantenimiento.add(mnuInicioEmpleado);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Proveedores.png"))); // NOI18N
        jMenu3.setText("Proveedor");

        mnuInicioProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/proveedor.png"))); // NOI18N
        mnuInicioProveedor.setText("Administrar Proveedor");
        jMenu3.add(mnuInicioProveedor);

        mnuSolicitarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/solicitar Productos Pro.png"))); // NOI18N
        mnuSolicitarProductos.setText("Solicitar Productos a Proveedores");
        jMenu3.add(mnuSolicitarProductos);

        mnuMantenimiento.add(jMenu3);

        mnuInicioProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Productos.png"))); // NOI18N
        mnuInicioProducto.setText("Producto");
        mnuMantenimiento.add(mnuInicioProducto);

        mnuInicioCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Categoria.png"))); // NOI18N
        mnuInicioCategoria.setText("Categoria");
        mnuMantenimiento.add(mnuInicioCategoria);
        mnuMantenimiento.add(jSeparator1);

        mnuInicioRespaldarBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/RespaldarBD.png"))); // NOI18N
        mnuInicioRespaldarBD.setText("Respaldar BD");
        mnuMantenimiento.add(mnuInicioRespaldarBD);

        jMenuBar1.add(mnuMantenimiento);

        mnuMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Movimientos.png"))); // NOI18N
        mnuMovimientos.setText("Movimientos");

        mnuVenderProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Mercado.png"))); // NOI18N
        mnuVenderProducto.setText("Vender Producto");
        mnuMovimientos.add(mnuVenderProducto);

        mnuConsultarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/caja.png"))); // NOI18N
        mnuConsultarCaja.setText("Consultar Caja");
        mnuMovimientos.add(mnuConsultarCaja);

        jMenuBar1.add(mnuMovimientos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dpnEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpnEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane dpnEscritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JLabel lbFecha;
    public javax.swing.JLabel lbNombre;
    public javax.swing.JLabel lbcargo;
    public javax.swing.JMenuItem mnuConsultarCaja;
    private javax.swing.JMenu mnuInicio;
    public javax.swing.JMenuItem mnuInicioCategoria;
    public javax.swing.JMenuItem mnuInicioEmpleado;
    public javax.swing.JMenuItem mnuInicioProducto;
    public javax.swing.JMenuItem mnuInicioProveedor;
    public javax.swing.JMenuItem mnuInicioRespaldarBD;
    public javax.swing.JMenu mnuMantenimiento;
    public javax.swing.JMenu mnuMovimientos;
    public javax.swing.JMenuItem mnuSalir;
    public javax.swing.JMenuItem mnuSolicitarProductos;
    public javax.swing.JMenuItem mnuVenderProducto;
    // End of variables declaration//GEN-END:variables
}
