_BTS SIO - Option SLAM_

_Année 2025 / 2026_

#** CuisineWeek**  
Application Mobile Android

**CAHIER DES CHARGES**

| **Projet**   | CuisineWeek - Recettes & Liste de courses |
| ------------ | ----------------------------------------- |
| **Type**     | Application mobile Android (Kotlin)       |
| **Contexte** | Projet de fin de BTS SIO SLAM             |
| **Date**     | Mars 2026                                 |
| **Version**  | v1.0                                      |

## **1\. Présentation du projet**

### **1.1 Situation professionnelle**

**Contexte**

Une startup spécialisée dans le bien-être alimentaire souhaite proposer une application mobile permettant à ses utilisateurs de planifier leurs repas de la semaine et de générer automatiquement une liste de courses optimisée. L'objectif est de réduire le gaspillage alimentaire, de simplifier les achats hebdomadaires et d'aider les utilisateurs à mieux s'organiser en cuisine.

### **1.2 Objectifs généraux**

- Permettre aux utilisateurs de consulter un catalogue de recettes de cuisine
- Offrir la possibilité de planifier un menu sur la semaine (petit-déjeuner, déjeuner, dîner)
- Générer automatiquement une liste de courses basée sur le menu sélectionné
- Permettre à l'utilisateur d'ajouter ses propres recettes personnalisées
- Estimer une tendance de prix pour la liste de courses générée

### **1.3 Public cible**

L'application s'adresse à toute personne souhaitant organiser ses repas de façon simple et efficace, sans nécessiter de compte utilisateur. Toutes les données sont stockées localement sur l'appareil Android de l'utilisateur.

## **2\. Périmètre fonctionnel**

### **2.1 Fonctionnalités principales (MVP)**

Les fonctionnalités suivantes constituent le cœur de l'application et doivent être livrées en priorité :

**F01 - Catalogue de recettes**

- Affichage d'une liste de recettes avec photo, nom, durée et difficulté
- Consultation du détail d'une recette (ingrédients, étapes, nombre de personnes)
- Filtrage par catégorie (entrée, plat, dessert, végétarien, rapide…)
- Barre de recherche par mot-clé sur le nom ou les ingrédients

**F02 - Gestion du menu de la semaine**

- Affichage d'un planning hebdomadaire (Lundi → Dimanche)
- Ajout d'une recette à un jour et un type de repas (matin / midi / soir)
- Suppression ou remplacement d'une recette dans le menu
- Persistance du menu en stockage local (Room Database)

**F03 - Génération de la liste de courses**

- Calcul automatique des ingrédients nécessaires à partir du menu sélectionné
- Regroupement et addition des quantités pour les ingrédients communs
- Adaptation des quantités selon le nombre de personnes paramétré
- Affichage de la liste triée par catégorie d'ingrédient
- Possibilité de cocher les articles déjà achetés
- Partage de la liste (export texte via Android Intent - SMS, e-mail, etc.)

**F04 - Ajout de recettes personnalisées**

- Formulaire de création d'une recette (nom, description, temps, difficulté)
- Ajout d'ingrédients avec quantité et unité
- Saisie des étapes de préparation
- Photo optionnelle depuis la galerie ou l'appareil photo
- Modification et suppression d'une recette personnalisée

### **2.2 Fonctionnalités secondaires (si temps disponible)**

Ces fonctionnalités enrichissent l'expérience utilisateur mais ne bloquent pas la livraison du MVP :

**F05 - Estimation du prix de la liste de courses**

- Affichage d'un prix indicatif par ingrédient (saisi manuellement ou via API OpenFoodFacts)
- Calcul du coût total estimé de la liste de courses
- Évolution visuelle du budget semaine après semaine (graphique tendance)
- Note : cette fonctionnalité dépend de la disponibilité des données de prix - l'API OpenFoodFacts est gratuite mais ne couvre pas tous les produits

## **3\. Contraintes techniques**

### **3.1 Stack technologique**

| **Composant**          | **Technologie choisie**        | **Justification**                                      |
| ---------------------- | ------------------------------ | ------------------------------------------------------ |
| Langage                | Kotlin                         | Enseigné en cours - natif Android                      |
| IDE                    | Android Studio                 | Environnement officiel Android                         |
| Base de données locale | Room Database (SQLite)         | Bibliothèque Android Jetpack, stable et performante    |
| Architecture           | MVVM (Model-View-ViewModel)    | Séparation logique / interface, recommandée par Google |
| Navigation             | Navigation Component (Jetpack) | Gestion des écrans et transitions                      |
| Listes & UI            | RecyclerView + ViewBinding     | Affichage dynamique des recettes et ingrédients        |
| Partage                | Android Intent (ACTION_SEND)   | Partage natif sans librairie externe                   |

### **3.2 Contraintes de stockage**

- Toutes les données sont stockées localement sur l'appareil (pas de serveur distant)
- Les recettes de base sont pré-chargées lors de la première installation (données seed)
- Les recettes personnalisées et le menu hebdomadaire sont sauvegardés en base Room
- La liste de courses est générée dynamiquement (pas de persistance nécessaire)

### **3.3 Contraintes de compatibilité**

- Version Android minimale : Android 8.0 (API 26)
- Résolution cible : smartphones standards (360dp et plus)
- Application en mode portrait uniquement dans un premier temps

## **4\. User Stories**

Les user stories suivent le format : En tant que \[rôle\], je veux \[action\] afin de \[bénéfice\].

| **ID** | **Fonctionnalité** | **User Story**                                                                                                      | **Critère d'acceptation**                                                                             |
| ------ | ------------------ | ------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| US01   | Catalogue          | En tant qu'utilisateur, je veux voir la liste des recettes disponibles afin de choisir ce que je vais cuisiner.     | La liste s'affiche avec photo, nom, durée et difficulté. La liste est scrollable.                     |
| US02   | Catalogue          | En tant qu'utilisateur, je veux consulter le détail d'une recette afin de connaître les ingrédients et les étapes.  | Le détail affiche tous les ingrédients avec quantités et toutes les étapes numérotées.                |
| US03   | Catalogue          | En tant qu'utilisateur, je veux filtrer les recettes par catégorie afin de trouver rapidement ce que je cherche.    | Les filtres sont cliquables. La liste se met à jour instantanément.                                   |
| US04   | Catalogue          | En tant qu'utilisateur, je veux rechercher une recette par mot-clé afin de la retrouver facilement.                 | La recherche fonctionne sur le nom et les ingrédients. Les résultats s'affichent en temps réel.       |
| US05   | Menu               | En tant qu'utilisateur, je veux ajouter une recette à un jour du menu afin de planifier mes repas.                  | Je peux choisir le jour et le type de repas. La recette apparaît dans le planning.                    |
| US06   | Menu               | En tant qu'utilisateur, je veux visualiser mon menu de la semaine afin d'avoir une vue d'ensemble.                  | Le planning semaine est affiché de façon claire avec toutes les recettes ajoutées.                    |
| US07   | Courses            | En tant qu'utilisateur, je veux générer ma liste de courses depuis le menu afin d'éviter d'oublier des ingrédients. | Les ingrédients sont regroupés et les quantités additionnées. Le tri par catégorie est appliqué.      |
| US08   | Courses            | En tant qu'utilisateur, je veux cocher les articles achetés afin de suivre mes achats en magasin.                   | Chaque article est cochable. Les articles cochés sont visuellement différenciés.                      |
| US09   | Courses            | En tant qu'utilisateur, je veux partager ma liste de courses afin de l'envoyer à quelqu'un.                         | Un bouton de partage ouvre le menu de partage Android avec la liste en texte.                         |
| US10   | Perso              | En tant qu'utilisateur, je veux créer ma propre recette afin de l'ajouter à mes favoris.                            | Le formulaire permet de saisir tous les champs. La recette est sauvegardée et apparaît dans la liste. |
| US11   | Perso              | En tant qu'utilisateur, je veux modifier ou supprimer une recette personnalisée afin de la corriger.                | La modification pré-remplit le formulaire. La suppression demande une confirmation.                   |
| US12   | Prix\*             | En tant qu'utilisateur, je veux voir le coût estimé de ma liste de courses afin de gérer mon budget.                | Un total estimé s'affiche en bas de la liste de courses. (Fonctionnalité secondaire)                  |

_\* Fonctionnalité secondaire_

## **5\. Architecture de données (Room Database)**

### **5.1 Schéma des entités**

| **Table**         | **Champs principaux**                                                                                                 | **Type de données**                                   | **Rôle**                                                |
| ----------------- | --------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------- | ------------------------------------------------------- |
| Recette           | id, nom, description, temps_prep, temps_cuisson, nb_personnes, difficulte, categorie_id, image_uri, est_personnalisee | INT, TEXT, TEXT, INT, INT, INT, TEXT, INT, TEXT, BOOL | Stocke toutes les recettes (catalogue + personnalisées) |
| Categorie         | id, nom, icone                                                                                                        | INT, TEXT, TEXT                                       | Classifie les recettes (plat, dessert, végétarien…)     |
| Ingredient        | id, nom, unite_defaut, categorie_courses                                                                              | INT, TEXT, TEXT, TEXT                                 | Référentiel des ingrédients disponibles                 |
| RecetteIngredient | recette_id (FK), ingredient_id (FK), quantite, unite                                                                  | INT, INT, REAL, TEXT                                  | Table de liaison recette ↔ ingrédient avec quantités    |
| MenuSemaine       | id, semaine_du, nb_personnes                                                                                          | INT, DATE, INT                                        | Représente un menu pour une semaine donnée              |
| MenuRecette       | id, menu_id (FK), recette_id (FK), jour, type_repas                                                                   | INT, INT, INT, TEXT, TEXT                             | Table de liaison menu ↔ recette avec jour et repas      |

### **5.2 Génération de la liste de courses**

**Logique de calcul**

La liste de courses n'est pas stockée en base de données. Elle est calculée dynamiquement à partir des recettes présentes dans le menu actif. Pour chaque recette du menu, on récupère ses ingrédients avec leurs quantités, on les regroupe par ingrédient, on additionne les quantités (en tenant compte du nombre de personnes), puis on trie le résultat par catégorie de courses (fruits & légumes, produits frais, épicerie, etc.).

## **6\. Écrans et navigation**

### **6.1 Liste des écrans**

| **N°** | **Nom de l'écran**       | **Description**                                                                                 |
| ------ | ------------------------ | ----------------------------------------------------------------------------------------------- |
| E01    | Accueil / Menu semaine   | Vue principale - affichage du planning de la semaine avec les recettes assignées à chaque repas |
| E02    | Catalogue de recettes    | Liste scrollable des recettes avec filtres par catégorie et barre de recherche                  |
| E03    | Détail d'une recette     | Affichage complet : photo, ingrédients, étapes, temps, difficulté, bouton "Ajouter au menu"     |
| E04    | Ajouter au menu          | Dialogue ou écran de sélection du jour et du type de repas                                      |
| E05    | Liste de courses         | Liste des ingrédients générée, regroupée par catégorie, avec cases à cocher et bouton partage   |
| E06    | Mes recettes             | Liste des recettes personnalisées de l'utilisateur avec accès à la création/modification        |
| E07    | Créer / Modifier recette | Formulaire complet de création ou d'édition d'une recette personnalisée                         |

### **6.2 Navigation principale**

L'application utilise une Bottom Navigation Bar avec 4 onglets principaux :

- Semaine (E01) - Accès au planning hebdomadaire
- Recettes (E02) - Catalogue de toutes les recettes
- Courses (E05) - Liste de courses générée
- Mes recettes (E06) - Recettes personnalisées

## **7\. Planification du développement**

### **7.1 Découpage en sprints**

| **Sprint** | **Durée** | **Tâches**                                                                                                                           | **Livrable**              |
| ---------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------ | ------------------------- |
| Sprint 1   | 1 semaine | Mise en place du projet Android Studio, architecture MVVM, création de la base de données Room, insertion des données de test (seed) | BDD fonctionnelle         |
| Sprint 2   | 1 semaine | Écrans Catalogue (E02) et Détail recette (E03) : RecyclerView, filtres, recherche, navigation                                        | Consultation des recettes |
| Sprint 3   | 1 semaine | Écran Menu semaine (E01 + E04) : planning, ajout et suppression de recettes par jour/repas                                           | Menu hebdomadaire         |
| Sprint 4   | 1 semaine | Écran Liste de courses (E05) : génération dynamique, cases à cocher, partage Android Intent                                          | Liste de courses          |
| Sprint 5   | 1 semaine | Écrans recettes personnalisées (E06 + E07) : formulaire, photo, CRUD complet                                                         | Recettes perso            |
| Sprint 6\* | 1 semaine | Estimation des prix : saisie manuelle ou intégration API OpenFoodFacts, graphique tendance                                           | Prix (optionnel)          |
| Sprint 7   | 1 semaine | Tests, corrections de bugs, polish UI, préparation du dossier BTS                                                                    | Version finale            |

_\* Sprint optionnel selon disponibilité_

## **8\. Critères de réussite**

### **8.1 Critères fonctionnels**

- L'utilisateur peut consulter au moins 10 recettes pré-chargées
- L'utilisateur peut construire un menu complet sur 7 jours
- La liste de courses générée regroupe correctement les doublons d'ingrédients
- L'utilisateur peut ajouter, modifier et supprimer une recette personnalisée
- La liste de courses peut être partagée via Android Intent

### **8.2 Critères techniques**

- L'application ne plante pas (aucun crash sur les parcours utilisateurs principaux)
- Les données persistent après fermeture de l'application (Room Database)
- L'architecture MVVM est respectée (séparation ViewModel / Repository / DAO)
- Le code est versionné avec Git

### **8.3 Critères pour le dossier BTS**

- Rédaction d'un dossier projet complet (contexte, analyse, conception, réalisation, tests)
- Maquettes des écrans incluses (Figma ou draw.io)
- Schéma de base de données (MCD ou diagramme de classes)
- Jeux de tests documentés
- Démonstration fonctionnelle lors de la présentation orale

## **9\. Glossaire**

| **Terme**      | **Définition**                                                                     |
| -------------- | ---------------------------------------------------------------------------------- |
| MVP            | Minimum Viable Product - version minimale fonctionnelle du produit                 |
| MVVM           | Model-View-ViewModel - pattern d'architecture Android recommandé par Google        |
| Room Database  | Bibliothèque Android Jetpack offrant une couche d'abstraction sur SQLite           |
| DAO            | Data Access Object - interface définissant les requêtes SQL dans Room              |
| RecyclerView   | Composant Android pour afficher des listes dynamiques et scrollables               |
| Seed           | Données initiales insérées en base au premier lancement de l'application           |
| Android Intent | Mécanisme Android permettant la communication entre applications (ex : partage)    |
| OpenFoodFacts  | Base de données ouverte sur les produits alimentaires, accessible via API gratuite |

**Note finale**

Ce cahier des charges est un document vivant. Il peut être mis à jour au fil du développement si de nouvelles contraintes apparaissent ou si des fonctionnalités sont repriorisées.
