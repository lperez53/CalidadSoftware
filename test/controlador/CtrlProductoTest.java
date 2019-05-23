/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import modelo.Producto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ThinkPad
 */
public class CtrlProductoTest {
    
    public CtrlProductoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of iniciarVenta method, of class CtrlProducto.
     */
    @Test
    public void testIniciarVenta() {
        System.out.println("iniciarVenta");
        CtrlProducto instance = null;
        instance.iniciarVenta();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciar method, of class CtrlProducto.
     */
    @Test
    public void testIniciar() {
        System.out.println("iniciar");
        CtrlProducto instance = null;
        instance.iniciar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciarSolicitud method, of class CtrlProducto.
     */
    @Test
    public void testIniciarSolicitud() {
        System.out.println("iniciarSolicitud");
        CtrlProducto instance = null;
        instance.iniciarSolicitud();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarBDcomboBox method, of class CtrlProducto.
     */
    @Test
    public void testCargarBDcomboBox() {
        System.out.println("cargarBDcomboBox");
        CtrlProducto instance = null;
        instance.cargarBDcomboBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class CtrlProducto.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent ae = null;
        CtrlProducto instance = null;
        instance.actionPerformed(ae);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerEnTabla method, of class CtrlProducto.
     */
    @Test
    public void testRemoverEnTabla() {
        System.out.println("removerEnTabla");
        JTable tabla = null;
        CtrlProducto instance = null;
        instance.removerEnTabla(tabla);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of limpiar method, of class CtrlProducto.
     */
    @Test
    public void testLimpiar() {
        System.out.println("limpiar");
        CtrlProducto instance = null;
        instance.limpiar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llenarTablaPorCriterio method, of class CtrlProducto.
     */
    @Test
    public void testLlenarTablaPorCriterio() {
        System.out.println("llenarTablaPorCriterio");
        JTable tabla = null;
        Producto producto = null;
        CtrlProducto instance = null;
        instance.llenarTablaPorCriterio(tabla, producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llenarTabla method, of class CtrlProducto.
     */
    @Test
    public void testLlenarTabla() {
        System.out.println("llenarTabla");
        JTable tabla = null;
        CtrlProducto instance = null;
        instance.llenarTabla(tabla);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseClicked method, of class CtrlProducto.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent me = null;
        CtrlProducto instance = null;
        instance.mouseClicked(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductos method, of class CtrlProducto.
     */
    @Test
    public void testGetProductos() {
        System.out.println("getProductos");
        CtrlProducto instance = null;
        ArrayList<Producto> expResult = null;
        ArrayList<Producto> result = instance.getProductos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class CtrlProducto.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent me = null;
        CtrlProducto instance = null;
        instance.mousePressed(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class CtrlProducto.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent me = null;
        CtrlProducto instance = null;
        instance.mouseReleased(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseEntered method, of class CtrlProducto.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent me = null;
        CtrlProducto instance = null;
        instance.mouseEntered(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseExited method, of class CtrlProducto.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent me = null;
        CtrlProducto instance = null;
        instance.mouseExited(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
