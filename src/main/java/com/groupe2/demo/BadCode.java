package com.groupe2.demo;

import java.util.List;

/**
 *  SCÉNARIO 1 — PMD BLOQUE
 * G2-004 : System.out.println
 * G2-005 : Catch vide
 * G2-006 : Plusieurs return
 * G2-003 : Variable "s" trop courte
 */
public class BadCode {

    public BadCode() {
        super();
    }

    // G2-006 + G2-004
    public boolean estAdmis(double moyenne) {
        System.out.println("Vérification...");
        if (moyenne >= 10.0) {
            return true;
        }
        return false;
    }

    // G2-005 + G2-003
    public double calculerMoyenne(List<Integer> notes) {
        double s = 0;
        try {
            for (int note : notes) {
                s += note;
            }
        } catch (Exception e) {
            // catch vide
        }
        return s / notes.size();
    }
}
