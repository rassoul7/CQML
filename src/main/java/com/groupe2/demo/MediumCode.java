package com.groupe2.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ⚠️ SCÉNARIO 2 — PMD PASSE, SONARQUBE BLOQUE
 * PMD (règles G2) : 0 violation
 * SonarQube détecte :
 *   - java:S2077  SQL Injection      (CRITICAL)
 *   - java:S2068  Password hardcodé  (CRITICAL)
 *   - java:S2259  Null dereference   (MAJOR)
 */
public class MediumCode {

    // SONAR S2068 : mot de passe hardcodé
    private static final String PASSWORD = "admin1234";
    private static final String URL = "jdbc:mysql://localhost/ecole";

    public MediumCode() {
        super();
    }

    // SONAR S2077 : injection SQL
    public String chercherEtudiant(String nom) throws Exception {
        String requete = "SELECT * FROM etudiants WHERE nom = '" + nom + "'";
        Connection conn = DriverManager.getConnection(URL, "root", PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(requete);
        // SONAR S2259 : peut retourner null → NullPointerException
        return res.getString("nom").toUpperCase();
    }
}