# 🚀 Demo GitHub Actions — Groupe 2

Pipeline CI/CD complet : **GitHub Actions** orchestrant **PMD** (Groupe 1) et **SonarQube** (Groupe 4).

---

## 🗂️ Structure du projet

```
demo-groupe2/
├── .github/
│   └── workflows/
│       └── ci-quality.yml          ← Le pipeline GitHub Actions
├── src/
│   ├── main/java/com/groupe2/demo/
│   │   ├── BadCode.java            ← ❌ Scénario ROUGE (violations PMD)
│   │   └── GoodCode.java           ← ✅ Scénario VERT (code propre)
│   └── test/java/com/groupe2/demo/
│       └── GoodCodeTest.java       ← Tests unitaires JUnit 5
├── pom.xml                         ← Maven + PMD + SonarQube configurés
└── README.md
```

---

## ⚙️ Configuration initiale (à faire UNE SEULE FOIS)

### 1. Créer le repo GitHub

```bash
git init
git remote add origin https://github.com/TON_USERNAME/demo-groupe2.git
git branch -M main
```

### 2. Configurer SonarCloud (gratuit)

1. Aller sur [sonarcloud.io](https://sonarcloud.io) → **Log in with GitHub**
2. Cliquer **+** → **Analyze new project** → choisir ton repo
3. Copier :
   - `Organization Key` → ex: `rassoul-mboup`
   - `Project Key` → ex: `rassoul-mboup_demo-groupe2`

### 3. Mettre à jour le `pom.xml`

Remplacer dans `pom.xml` :
```xml
<sonar.organization>rassoul-mboup</sonar.organization>
<sonar.projectKey>rassoul-mboup_demo-groupe2</sonar.projectKey>
```

### 4. Ajouter les secrets GitHub

Dans ton repo → **Settings → Secrets and variables → Actions** :

| Type | Nom | Valeur |
|------|-----|--------|
| Secret | `SONAR_TOKEN` | Token généré sur SonarCloud (Account → Security) |
| Variable | `SONAR_ORGANIZATION` | ex: `rassoul-mboup` |
| Variable | `SONAR_PROJECT_KEY` | ex: `rassoul-mboup_demo-groupe2` |

---

## 🎬 Scénario de démonstration

### ❌ Scénario 1 — Pipeline ROUGE (PMD fail)

**Préparer :**
```bash
# Supprimer GoodCode, garder BadCode comme classe principale
git rm src/main/java/com/groupe2/demo/GoodCode.java
git commit -m "demo: ajout BadCode avec violations PMD"
git push origin main
```

**Résultat attendu dans GitHub Actions :**
```
Job "Analyse PMD"    → ❌ FAILED
  [PMD] EmptyCatchBlock dans BadCode.java:30
  [PMD] UnusedLocalVariable dans BadCode.java:22
  [PMD] SystemPrintln dans BadCode.java:39
  BUILD FAILURE

Job "Scan SonarQube" → ⏭️ SKIPPED (PMD a fail)
Job "Déploiement CD" → ⏭️ SKIPPED (dépend des deux)
```

**Message à dire en préso :**
> "On voit que PMD a détecté 3 violations. GitHub Actions a immédiatement stoppé le pipeline.
> SonarQube n'a même pas été déclenché. Le déploiement est bloqué automatiquement —
> c'est exactement la Quality Gate dont on parle dans la slide 7."

---

### ✅ Scénario 2 — Pipeline VERT (succès complet)

**Préparer :**
```bash
git checkout -b demo-fix
# Remettre GoodCode, supprimer BadCode
git rm src/main/java/com/groupe2/demo/BadCode.java
git add src/main/java/com/groupe2/demo/GoodCode.java
git commit -m "fix: remplacement BadCode par GoodCode — 0 violation"
git push origin demo-fix
```

**Résultat attendu :**
```
Job "Analyse PMD"    → ✅ SUCCESS (0 violation)
Job "Scan SonarQube" → ✅ SUCCESS (Quality Gate passed)
Job "Déploiement CD" → 🚀 SUCCESS
  ════════════════════════════════════════
  ✅ PMD     : AUCUNE VIOLATION DÉTECTÉE
  ✅ SONAR   : QUALITY GATE PASSED
  🚀 DÉPLOIEMENT EN PRODUCTION...
  ✅ APPLICATION DÉPLOYÉE AVEC SUCCÈS !
  ════════════════════════════════════════
```

---

## 🔥 Points clés à souligner en présentation

| Élément | Ce que ça démontre |
|---------|-------------------|
| `needs: analyse-pmd` dans le YAML | GHA orchestre l'ordre d'exécution |
| `failOnViolation: true` dans PMD | Le Groupe 1 définit les règles, GHA les applique |
| `qualitygate.wait=true` dans Sonar | GHA attend le verdict SonarCloud avant de continuer |
| Jobs en cascade rouge | Un seul fail bloque toute la chaîne |

---

## 📦 Violations PMD dans BadCode.java

| Violation | Ligne | Gravité | Explication |
|-----------|-------|---------|-------------|
| `EmptyCatchBlock` | 30 | HIGH | Exception avalée silencieusement |
| `UnusedLocalVariable` | 22 | MEDIUM | Variable déclarée jamais utilisée |
| `SystemPrintln` | 39 | MEDIUM | `System.out.println` interdit en prod |
| `SimplifyBooleanReturns` | 47 | LOW | `if (x) return true; else return false;` |
| `AvoidDuplicateLiterals` | 56 | MEDIUM | "Insuffisant" répété 3 fois |
