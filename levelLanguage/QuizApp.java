package levelLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// La classe QuizApp fait office de point d'entrée principal pour l'application
public class QuizApp {
    private static Ecole ecole = new Ecole();
    private static Eleve eleveConnecte = null;
    private static Enseignant enseignantConnecte = null;
    private static Scanner scanner = new Scanner(System.in);

    // Point d'entrée principal de l'application
    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application de quiz!");
        
        boolean quitter = false;

        while (!quitter) {
            if (enseignantConnecte == null && eleveConnecte == null) {
                afficherMenuPrincipal();
                int choix = saisirChoix();
                traiterChoixMenuPrincipal(choix);
            } else if (enseignantConnecte != null) {
                afficherMenuEnseignant();
                int choix = saisirChoix();
                traiterChoixMenuEnseignant(choix);
            } else {
                afficherMenuEleve();
                int choix = saisirChoix();
                traiterChoixMenuEleve(choix);
            }
        }
    }
    
    // Méthode pour saisir un choix utilisateur (entier)
    private static int saisirChoix() {
        while (true) {
            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne
                return choix;
            } else {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Consommer l'entrée incorrecte
            }
        }
    }

    // Méthode pour afficher le menu principal
    private static void afficherMenuPrincipal() {
        System.out.println("1. Se connecter");
        System.out.println("2. Créer un compte");
        System.out.println("3. Quitter");
        System.out.print("Votre choix : ");
    }

    // Méthode pour afficher le sous-menu enseignant
    private static void afficherMenuEnseignant() {
        System.out.println("1. Afficher la liste des élèves");
        System.out.println("2. Créer un nouveau Quiz");
        System.out.println("3. Se déconnecter");
        System.out.print("Votre choix : ");
    }
    
    // Méthode pour afficher le sous-menu élève
    private static void afficherMenuEleve() {
        System.out.println("Menu élève :");
        System.out.println("1. Faire un quiz");
        System.out.println("2. Se déconnecter");
        System.out.print("Votre choix : ");
    }

    // Méthode pour traiter le choix du menu principal
    private static void traiterChoixMenuPrincipal(int choix) {
        switch (choix) {
            case 1:
                connecterUtilisateur();
                break;
            case 2:
                creerCompteUtilisateur();
                break;
            case 3:
                quitterApplication();
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }

    // Méthode pour traiter le choix du sous-menu enseignant
    private static void traiterChoixMenuEnseignant(int choix) {
        switch (choix) {
            case 1:
                // Afficher la liste des élèves
                ecole.afficherListeEleves();
                break;
            case 2:
                // Vérifier si un enseignant est connecté
                if (enseignantConnecte != null) {
                    // Créer un nouveau Quiz
                    creerEtAjouterQuiz();
                } else {
                    System.out.println("Aucun enseignant n'est connecté. Veuillez vous connecter en tant qu'enseignant.");
                }
            	break;
            case 3:
                // Déconnecter l'enseignant
                deconnecterUtilisateur();
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }
    
    // Méthode pour traiter le choix du sous-menu élève
    private static void traiterChoixMenuEleve(int choix) {
        switch (choix) {
            case 1:
            	// Faire un quiz
                faireQuiz();
                break;
            case 2:
                // Déconnecter l'élève
                deconnecterUtilisateur();
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }

    // Méthode pour connecter un élève
    private static void connecterEleve() {
        boolean connected = false;

        while (!connected) {

            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();

            Utilisateur utilisateurConnecte = ecole.connecter(nomUtilisateur, motDePasse, TypeUtilisateur.ELEVE);

            if (utilisateurConnecte instanceof Eleve) {
                eleveConnecte = (Eleve) utilisateurConnecte;
                System.out.println("Connexion réussie. Bienvenue, " + eleveConnecte.getNom() + "!");
                connected = true;
            } else {
                gestionEchecConnexion();
            }
        }
    }
    
    // Méthode pour connecter un enseignant
    private static void connecterEnseignant() {
        boolean connected = false;

        while (!connected) {

            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();

            Utilisateur utilisateurConnecte = ecole.connecter(nomUtilisateur, motDePasse, TypeUtilisateur.ENSEIGNANT);

            if (utilisateurConnecte instanceof Enseignant) {
                enseignantConnecte = (Enseignant) utilisateurConnecte;
                System.out.println("Connexion réussie. Bienvenue, " + enseignantConnecte.getNom() + "!");
                connected = true;
            } else {
                gestionEchecConnexion();
                }
            }
        }

    // Méthode pour gérer l'échec de la connexion
    private static void gestionEchecConnexion() {
        System.out.println("Échec de la connexion. Veuillez vérifier vos informations d'identification.");
        System.out.println("Voulez-vous réessayer ? (O/N)");
        String reponse = scanner.nextLine().toLowerCase();
    
        if (reponse.equals("N")) {
            System.out.println("Fermeture de l'application.");
            System.exit(0);
        }
    }

    // Méthode pour créer un compte utilisateur
    private static void creerCompteUtilisateur() {
        System.out.print("Vous voulez créer un compte élève ou enseignant ? (eleve/enseignant) : ");
        String typeUtilisateur = scanner.nextLine().toLowerCase();

        if (typeUtilisateur.equals("eleve")) {
            creerCompteEleve();
        } else if (typeUtilisateur.equals("enseignant")) {
            creerCompteEnseignant();
        } else {
            System.out.println("Nouvelle tentative..");
        }
    }
    
    // Méthode pour créer un compte élève
    private static void creerCompteEleve() {
        boolean compteCree = false;

        while (!compteCree) {

            System.out.print("Nom : ");
            String nom = scanner.nextLine();
            
            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();

            // Appel de la méthode creerCompte de l'école
            ecole.creerCompte(nom, "", nomUtilisateur, motDePasse, TypeUtilisateur.ELEVE);
            
            // Sortir de la boucle après avoir créé le compte
            compteCree = true;

            gestionCreationCompte();
        }
    }
    
    // Méthode pour créer un compte enseignant
    private static void creerCompteEnseignant() {
        boolean compteCree = false;

        while (!compteCree) {

            System.out.print("Nom : ");
            String nom = scanner.nextLine();
            
            System.out.print("Langue enseignée : ");
            String langueEnseignee = scanner.nextLine();

            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();

            // Appel de la méthode creerCompte de l'école
            ecole.creerCompte(nom, langueEnseignee, nomUtilisateur, motDePasse, TypeUtilisateur.ENSEIGNANT);

            // Sortir de la boucle après avoir créé le compte
            compteCree = true;

            // Gestion de la création du compte
            gestionCreationCompte();
        }
    }
    
    // Méthode pour gérer la création du compte
    private static void gestionCreationCompte() {
        System.out.println("Voulez-vous vous connecter à présent ? (O/N)");
        String reponse = scanner.nextLine().toLowerCase();

        if (reponse.equals("N")) {
            System.out.println("Fermeture de l'application.");
            System.exit(0);
        }
    }

    // Méthode pour connecter un utilisateur
    private static void connecterUtilisateur() {
        System.out.print("Êtes-vous un élève ou un enseignant ? (eleve/enseignant) : ");
        String typeUtilisateur = scanner.nextLine().toLowerCase();

        if (typeUtilisateur.equals("eleve")) {
            connecterEleve();
        } else if (typeUtilisateur.equals("enseignant")) {
            connecterEnseignant();
        } else {
            System.out.println("Nouvelle tentative..");
        }
    }
    
    // Méthode pour quitter l'application
    private static void quitterApplication() {
        System.out.println("Fermeture de l'application.");
        System.exit(0);
    }

    // Méthode pour déconnecter un utilisateur
    private static void deconnecterUtilisateur() {
        if (eleveConnecte != null) {
            eleveConnecte = null;
            System.out.println("Déconnexion de l'élève réussie.");
        } else if (enseignantConnecte != null) {
            enseignantConnecte = null;
            System.out.println("Déconnexion de l'enseignant réussie.");
        }
    }

    // Méthode pour créer et ajouter un quiz
    private static void creerEtAjouterQuiz() {
        System.out.println("Choisissez la langue du Quiz (francais/anglais/arabe/berbere) : ");
        String langue = scanner.nextLine().toLowerCase();

        System.out.println("Choisissez le niveau du Quiz (facile/intermediaire/difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        System.out.println("Entrez la question du Quiz : ");
        String question = scanner.nextLine();

        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Entrez l'option " + i + " : ");
            options.add(scanner.nextLine());
        }

        System.out.println("Entrez la réponse correcte : ");
        String reponseCorrecte = scanner.nextLine();

        Quiz nouveauQuiz = new Quiz(question, options, reponseCorrecte);
        ecole.ajouterQuiz(enseignantConnecte, nouveauQuiz, langue, niveau);
    }
    
    // Méthode pour permettre à l'élève de faire un quiz    
    private static void faireQuiz() {
        // Demander à l'élève de choisir la langue du quiz
        System.out.println("Choisissez la langue du Quiz (francais/anglais/arabe/berbere) : ");
        String langue = scanner.nextLine().toLowerCase();

        // Demander à l'élève de choisir le niveau du quiz
        System.out.println("Choisissez le niveau du Quiz (facile/intermédiaire/difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        // Récupérer la liste des quizzes disponibles pour la langue et le niveau choisis
        List<Quiz> quizzesDisponibles = ecole.getQuizzesDisponibles(langue, niveau);

        if (quizzesDisponibles.isEmpty()) {
            System.out.println("Aucun quiz disponible pour la langue et le niveau sélectionnés.");
            return; // Sortir de la méthode s'il n'y a pas de quiz disponible
        }

        // Afficher la liste des quizzes disponibles
        System.out.println("Quizzes disponibles :");
        for (int i = 0; i < quizzesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + quizzesDisponibles.get(i).getQuestion());
        }

        // Demander à l'élève de choisir le numéro du quiz
        System.out.print("Choisissez le numéro du quiz : ");
        int numeroQuiz = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne après la saisie du nombre

        // Vérifier si le numéro du quiz est valide
        if (numeroQuiz < 1 || numeroQuiz > quizzesDisponibles.size()) {
            System.out.println("Numéro de quiz invalide. Veuillez réessayer.");
            return; // Sortir de la méthode en cas de numéro invalide
        }

        // Obtenez le quiz sélectionné
        Quiz quizSelectionne = quizzesDisponibles.get(numeroQuiz - 1);

        // Laissez l'élève répondre au quiz sélectionné
        boolean reponseCorrecte = repondreAuQuiz(quizSelectionne);

        // Mettez à jour le score de l'élève
        if (reponseCorrecte) {
            eleveConnecte.incrementerScore(); // Ajoutez la méthode incrementerScore() à la classe Eleve
            System.out.println("Bravo ! Vous avez répondu correctement.");
        } else {
            System.out.println("Dommage ! Votre réponse est incorrecte.");
        }
    }
    private static boolean repondreAuQuiz(Quiz quiz) {
        // Afficher la question du quiz
        System.out.println("Question : " + quiz.getQuestion());

        // Afficher les options du quiz
        List<String> options = quiz.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        // Demander à l'élève de choisir une réponse
        System.out.print("Votre réponse (1-" + options.size() + ") : ");
        int choixUtilisateur = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne après la saisie du nombre

        // Vérifier si la réponse de l'élève est correcte
        String reponseCorrecte = quiz.getCorrectAnswer();
        boolean isCorrect = options.get(choixUtilisateur - 1).equalsIgnoreCase(reponseCorrecte);

        return isCorrect;
    }

}

