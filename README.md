````md
# Guide d’installation d’une application mobile Kotlin depuis GitHub

## Objectif

Ce guide explique comment récupérer, configurer et lancer une application Android développée en Kotlin à partir d’un dépôt GitHub.

---

# 1. Prérequis

Avant de commencer, installe les outils suivants.

## Outils nécessaires

### Android Studio

Télécharge et installe Android Studio :

- Site officiel : https://developer.android.com/studio

Pendant l’installation :

- Installer le SDK Android
- Installer Android Virtual Device (AVD)
- Installer Gradle

---

### Git

Télécharge Git :

- Site officiel : https://git-scm.com/

Vérifie l’installation :

```bash
git --version
````

---

# 2. Cloner le dépôt GitHub

Ouvre un terminal puis exécute :

```bash
git clone https://github.com/UTILISATEUR/NOM_DU_DEPOT.git
```

Exemple :

```bash
git clone https://github.com/monprofil/MyKotlinApp.git
```

Ensuite :

```bash
cd NOM_DU_DEPOT
```

---

# 3. Ouvrir le projet dans Android Studio

1. Ouvrir Android Studio
2. Cliquer sur :

```text
Open
```

3. Sélectionner le dossier du projet cloné
4. Attendre la synchronisation Gradle

Android Studio peut afficher :

```text
Gradle Sync in Progress
```

Attendre la fin complète.

---

# 4. Configurer le SDK Android

Si Android Studio demande un SDK manquant :

1. Aller dans :

```text
File > Settings > Android SDK
```

2. Installer :

* Android SDK Platform
* Android SDK Build-Tools
* Android Emulator

---

# 5. Vérifier les dépendances Gradle

Le projet utilise généralement deux fichiers importants :

## build.gradle (Project)

Exemple :

```kotlin
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
```

---

## build.gradle (Module)

Exemple :

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}
```

Si nécessaire :

```text
File > Sync Project with Gradle Files
```

---

# 6. Configurer un émulateur Android

## Créer un appareil virtuel

1. Ouvrir :

```text
Tools > Device Manager
```

2. Cliquer sur :

```text
Create Device
```

3. Choisir un modèle :

* Pixel 6
* Pixel 7
* Pixel Tablet

4. Télécharger une image système Android
5. Finaliser la création

---

# 7. Lancer l’application

1. Sélectionner l’émulateur
2. Cliquer sur :

```text
Run ▶
```

Ou utiliser le raccourci :

```text
Shift + F10
```

Android Studio va :

* Compiler le projet
* Installer l’application
* Lancer l’application

---

# 8. Lancer sur un téléphone Android réel

## Activer le mode développeur

Sur le téléphone :

```text
Paramètres > À propos du téléphone > Numéro de build
```

Appuyer 7 fois.

---

## Activer le débogage USB

```text
Paramètres > Options développeur > Débogage USB
```

---

## Connecter le téléphone

1. Brancher le téléphone en USB
2. Autoriser le débogage
3. Sélectionner l’appareil dans Android Studio
4. Cliquer sur Run

---

# 9. Générer un APK

## APK Debug

Dans Android Studio :

```text
Build > Build APK(s)
```

Le fichier sera généré dans :

```text
app/build/outputs/apk/debug/
```

---

## APK Release

```text
Build > Generate Signed Bundle / APK
```

Créer ou sélectionner une clé de signature.

---

# 10. Résolution des erreurs fréquentes

## Erreur Gradle

Solution :

```bash
./gradlew clean
```

Puis :

```bash
./gradlew build
```

---

## SDK introuvable

Configurer le chemin du SDK :

```text
File > Settings > Android SDK
```

---

## Dépendance introuvable

Vérifier :

* La connexion internet
* Les versions Gradle
* Le fichier repositories

Exemple :

```kotlin
repositories {
    google()
    mavenCentral()
}
```

---

## Émulateur lent

Activer :

* Virtualisation BIOS
* Accélération matérielle
* RAM supplémentaire

---

# 11. Structure classique d’un projet Kotlin Android

```text
app/
 ├── src/
 │    ├── main/
 │    │    ├── java/
 │    │    ├── res/
 │    │    └── AndroidManifest.xml
 │
 ├── build.gradle
 └── proguard-rules.pro
```

---

# 12. Commandes utiles

## Compiler le projet

```bash
./gradlew build
```

---

## Nettoyer le projet

```bash
./gradlew clean
```

---

## Lancer les tests

```bash
./gradlew test
```

---

## Installer l’application

```bash
./gradlew installDebug
```

---

# 13. Bonnes pratiques

* Utiliser un README clair
* Séparer la logique métier de l’UI
* Utiliser Git branches
* Ajouter un fichier `.gitignore` Android
* Stocker les clés API dans `local.properties`
* Utiliser `ViewModel` et `Jetpack Compose` si possible

---

# 14. Exemple de README GitHub minimal

````md
# MyKotlinApp

Application Android développée en Kotlin.

## Installation

```bash
git clone https://github.com/user/repo.git
```

## Lancement

Ouvrir le projet avec Android Studio puis lancer l’émulateur.
````

---

# Conclusion

L’installation d’une application Kotlin Android suit toujours la même logique :

1. Installer les outils
2. Cloner le dépôt GitHub
3. Ouvrir dans Android Studio
4. Synchroniser Gradle
5. Lancer sur émulateur ou téléphone

Une fois cette base maîtrisée, tu peux automatiser le build, le déploiement et les tests avec GitHub Actions, Firebase ou Docker Android.

```
```
