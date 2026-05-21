package com.groupe2.demo;

import java.util.*;

/**
 * ⚠️ SCÉNARIO 1 — PIPELINE ROUGE
 *
 * Ce fichier contient des violations PMD INTENTIONNELLES.
 * Elles vont BLOQUER le pipeline dès le Job 1 (PMD).
 * SonarQube ne sera jamais atteint. Déploiement impossible.
 *
 * Violations incluses :
 *  - [PMD] EmptyCatchBlock       : catch vide → avale silencieusement les erreurs
 *  - [PMD] UnusedLocalVariable   : variable inutilisée = code mort
 *  - [PMD] SystemPrintln         : System.out.println en production = interdit
 *  - [PMD] AvoidDuplicateLiterals: même chaîne répétée 5 fois
 *  - [PMD] SimplifyBooleanReturns: return condition == true inutilement verbeux
 */
public class BadCode {

    /**
     * Calcule la moyenne d'une liste d'entiers.
     * VIOLATION : catch vide + println + variable inutilisée
     */
    public double calculerMoyenne(List<Integer> notes) {
        // ❌ VIOLATION PMD : UnusedLocalVariable
        String statusInutile = "calcul en cours";

        double somme = 0;
        try {
            for (int note : notes) {
                somme += note;
            }
        } catch (Exception e) {
            // ❌ VIOLATION PMD : EmptyCatchBlock — l'erreur est avalée silencieusement !
        }

        double moyenne = somme / notes.size();

        // ❌ VIOLATION PMD : SystemPrintln — interdit en prod
        System.out.println("Moyenne calculée : " + moyenne);

        return moyenne;
    }

    /**
     * Vérifie si un étudiant est admis.
     * VIOLATION : SimplifyBooleanReturns + AvoidDuplicateLiterals
     */
    public boolean estAdmis(double moyenne) {
        // ❌ VIOLATION PMD : SimplifyBooleanReturns
        // Correct serait : return moyenne >= 10.0;
        if (moyenne >= 10.0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne le niveau d'un étudiant.
     * VIOLATION : AvoidDuplicateLiterals
     */
    public String getNiveau(double moyenne) {
        // ❌ VIOLATION PMD : AvoidDuplicateLiterals — "Insuffisant" répété
        if (moyenne < 5.0)  return "Insuffisant";
        if (moyenne < 8.0)  return "Insuffisant";   // répété !
        if (moyenne < 10.0) return "Insuffisant";   // répété !
        if (moyenne < 12.0) return "Passable";
        if (moyenne < 14.0) return "Assez Bien";
        if (moyenne < 16.0) return "Bien";
        return "Très Bien";
    }
}
