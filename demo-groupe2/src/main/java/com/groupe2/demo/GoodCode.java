package com.groupe2.demo;

import java.util.List;
import java.util.logging.Logger;

/**
 * ✅ SCÉNARIO 2 — PIPELINE VERT
 *
 * Ce fichier est conforme à toutes les règles PMD.
 * Le pipeline passera toutes les étapes :
 *   Job 1 → PMD ✅ (0 violation)
 *   Job 2 → SonarQube ✅ (Quality Gate passed)
 *   Job 3 → Déploiement 🚀
 *
 * Bonnes pratiques appliquées :
 *  ✅ Logger au lieu de System.out.println
 *  ✅ Gestion des exceptions explicite
 *  ✅ Pas de variables inutilisées
 *  ✅ Return booléen simplifié
 *  ✅ Constantes pour les littéraux répétés
 */
public class GoodCode {

    private static final Logger LOGGER = Logger.getLogger(GoodCode.class.getName());

    // ✅ Constantes nommées pour éviter les littéraux répétés
    private static final double SEUIL_ADMISSION = 10.0;
    private static final double SEUIL_PASSABLE  = 12.0;
    private static final double SEUIL_ASSEZ_BIEN = 14.0;
    private static final double SEUIL_BIEN       = 16.0;

    /**
     * Calcule la moyenne d'unee liste de notes.
     *
     * @param notes liste d'entiers représentant les notes
     * @return la moyenne ou 0.0 si la liste est vide
     */

    public double calculerMoyenne(final List<Integer> notes) {
        final double resultat;
        if (notes == null || notes.isEmpty()) {
            LOGGER.warning("Liste vide.");
            resultat = 0.0;
        } else {
            double somme = 0.0;
            for (final int note : notes) {
                somme += note;
            }
            resultat = somme / notes.size();
            if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                LOGGER.info("Moyenne : " + resultat);
            }
        }
        return resultat;  // ✅ seul return
    }

    /**
     * Détermine si un étudiant est admis.
     *
     * @param moyenne la moyenne de l'étudiant
     * @return true si moyenne >= 10.0
     */
    public boolean estAdmis(double moyenne) {
        // ✅ Return direct — pas de if/else inutile
        return moyenne >= SEUIL_ADMISSION;
    }

    /**
     * Retourne le niveau académique correspondant à la moyenne.
     *
     * @param moyenne la moyenne de l'étudiant
     * @return le niveau sous forme de chaîne
     */
    public String getNiveau(final double moyenne) {
        final String niveau;
        if (moyenne < SEUIL_ADMISSION) {
            niveau = "Insuffisant";
        } else if (moyenne < SEUIL_PASSABLE) {
            niveau = "Passable";
        } else if (moyenne < SEUIL_ASSEZ_BIEN) {
            niveau = "Assez Bien";
        } else if (moyenne < SEUIL_BIEN) {
            niveau = "Bien";
        } else {
            niveau = "Très Bien";
        }
        return niveau;  //
    }
}
