package com.groupe2.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ⚠️ SCÉNARIO 2 — PMD passe, SonarQube bloque
 *
 * PMD (nos règles G2) : 0 violation
 * SonarQube détecte :
 *   - java:S2077  SQL Injection       (VULNERABILITY - CRITICAL)
 *   - java:S2068  Hardcoded password  (VULNERABILITY - CRITICAL)
 *   - java:S2259  Null dereference    (BUG - MAJOR)
 */
public class MediumCode {

    // SONAR S2068 : mot de passe hardcodé dans le code source
    private static final String PASSWORD = "admin1234";
    private static final String URL = "jdbc:mysql://localhost/ecole";

    public MediumCode() {
        super();
    }

    /**
     * Recherche un étudiant par son nom.
     * SONAR S2077 : injection SQL — le paramètre est concaténé directement
     */
    public String chercherEtudiant(String nom) throws Exception {
        //  Injection SQL : un attaquant peut passer "' OR '1'='1"
        String requete = "SELECT * FROM etudiants WHERE nom = '" + nom + "'";

        Connection conn = DriverManager.getConnection(URL, "root", PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(requete);

        // SONAR S2259 : res.getString() peut retourner null → NullPointerException
        return res.getString("nom").toUpperCase();
    }
}