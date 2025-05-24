/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pia;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @Gustavo Arreola Almaguer 2074164
 */
public class CasillasTest {
    
    public CasillasTest() {
    }


    @Test
    public void testIsMina() {
        System.out.println("isMina");
        Casillas instance = new Casillas(0, 0);
        instance.setMina(true);
        assertTrue(instance.isMina());
    }

    @Test
    public void testSetMina() {
        System.out.println("setMina");
        Casillas instance = new Casillas(0, 0);
        instance.setMina(true);
        assertTrue(instance.isMina());
        
    }

    @Test
    public void testIsCasillaAbierta() {
        System.out.println("isCasillaAbierta");
        Casillas instance = new Casillas(0, 0);
        instance.setCasillaAbierta(true);
        assertTrue(instance.isCasillaAbierta());
    }

    @Test
    public void testSetCasillaAbierta() {
        System.out.println("setCasillaAbierta");
        Casillas instance = new Casillas(0, 0);
        instance.setCasillaAbierta(true);
        assertTrue(instance.isCasillaAbierta());
    }
    
}
