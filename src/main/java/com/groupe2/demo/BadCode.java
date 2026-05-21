package com.groupe2.demo;

import java.util.List;

/**
 * ❌ SCÉNARIO ROUGE — Violations des règles Groupe 2
 * G2-004 : System.out.println
 * G2-005 : Catch vide
 * G2-006 : Plusieurs return dans une méthode
 * G2-003 : Variable nommée "s" (trop court)
 */
public class BadCode {

    public BadCode() {
        super();
    }

    // Déclenche G2-006 (plusieurs return) + G2-004 (System.out)
    public boolean estAdmis(double moyenne) {
        System.out.println("Vérification admissibilité...");  // G2-004
        if (moyenne >= 10.0) {
            return true;   // G2-006
        }
        return false;      // G2-006
    }

    // Déclenche G2-005 (catch vide) + G2-003 (variable "s")
    public double calculerMoyenne(List<Integer> notes) {
        double s = 0;  // G2-003 : "s" trop court
        try {
            for (int note : notes) {
                s += note;
            }
        } catch (Exception e) {
            // G2-005 : catch vide !
        }
        return s / notes.size();
    }
}