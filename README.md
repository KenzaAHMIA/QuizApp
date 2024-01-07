# QuizApp

**QuizApp** est une application de quiz conçue pour une école, offrant des fonctionnalités d'inscription et de connexion. Les enseignants peuvent créer des quiz dans différentes langues et niveaux, tandis que les élèves peuvent s'auto-évaluer.

## Fichiers de la Base de Données
Veuillez modifier les chemins des fichiers dans la classe `Ecole` avant de tester l'application :
- `eleves.txt`
- `enseignants.txt`
- `quizzes.txt`

## Attribution des Responsabilités
- **Classe QuizApp (Main) :** Point d'entrée de l'application, gestion du flux global de l'application.
- **Classe Ecole :** Gestion des utilisateurs (élèves et enseignants), des fichiers de base de données et des quizzes.
- **Classe Eleve :** Modélisation des élèves avec des méthodes pour la création et la connexion des comptes.
- **Classe Enseignant :** Modélisation des enseignants avec des méthodes pour la création et la connexion des comptes, et la gestion des quizzes.
- **Classe Utilisateur :** Classe parente d'Eleve et Enseignant, avec des méthodes communes pour la connexion et la création de compte.
- **Enum TypeUtilisateur :** Définition des types d'utilisateurs (ENSEIGNANT, ELEVE) pour faciliter le login.
- **Classe Quiz :** Représentation des quizzes avec des méthodes associées.
- **Classe CreerQuiz :** Interface pour la création de quiz par l'enseignant.
- **Classe FaireQuiz :** Gestion de la procédure pour qu'un élève réponde à un quiz.

## Problèmes Rencontrés
### Bugs Non Résolus
- L'utilisation de la méthode `sauvegarderQuizzesDansFichier` pour enregistrer les quizzes saisis par l'enseignant n'a pas fonctionné. Les exercices ont dû être entrés manuellement en suivant le format de la méthode `chargerQuizzesDepuisFichier`.

### À Améliorer
- **Flexibilité des Chemins de Fichiers :** Permettre une gestion plus flexible des chemins des fichiers.
- **Modification des Mots de Passe :** Ajouter des méthodes pour permettre la modification des mots de passe des élèves et des enseignants.
- **Gestion des Quizzes :** Améliorer les méthodes de modification des quizzes, en particulier pour les opérations de modification ou de suppression.

## Retour sur le Projet
- Le projet a pris plus de temps que prévu en raison de sa complexité, notamment avec des réinitialisations fréquentes.
- La durée limitée et le manque d'exercices pratiques en classe pour ce type d'application complexe ont posé des défis.
- Malgré les difficultés, une compréhension plus approfondie de la programmation orientée objet et de la gestion de fichiers a été acquise.
- Un investissement de plus de 30 heures a été nécessaire, avec plusieurs révisions à partir de zéro en raison de bugs insolubles.
