package com.groupe2.demo;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodCodeTest {

    private final GoodCode code = new GoodCode();

    @Test
    void testMoyenneNormale() {
        List<Integer> notes = Arrays.asList(12, 14, 16, 10, 8);
        assertEquals(12.0, code.calculerMoyenne(notes), 0.001);
    }

    @Test
    void testAdmisAuDessus() {
        assertTrue(code.estAdmis(12.5));
    }

    @Test
    void testRefusEnDessous() {
        assertFalse(code.estAdmis(9.5));
    }

    @Test
    void testNiveauTresBien() {
        assertEquals("Très Bien", code.getNiveau(18.0));
    }

    @Test
    void testNiveauInsuffisant() {
        assertEquals("Insuffisant", code.getNiveau(7.0));
    }
}
