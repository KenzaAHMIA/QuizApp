package levelLanguage;

import java.io.*;
import java.util.*;

public class Ecole implements Serializable {
    // Chemins vers les fichiers de données
    private static final String FICHIER_ELEVES = "/Users/kenza/Documents/etudes/M2_TAL/semestre1/programmation_O/Projet/src/levelLanguage/eleves.txt";
    private static final String FICHIER_ENSEIGNANTS = "/Users/kenza/Documents/etudes/M2_TAL/semestre1/programmation_O/Projet/src/levelLanguage/enseignants.txt";
    private static final String FICHIER_QUIZZES = "/Users/kenza/Documents/etudes/M2_TAL/semestre1/programmation_O/Projet/src/levelLanguage/quizzes.txt";

    private static final long serialVersionUID = 1L;
    private List<Eleve> baseDeDonneesEleves;
    private List<Enseignant> baseDeDonneesEnseignants;
    private Map<String, Map<String, List<Quiz>>> quizParLangue = new HashMap<>();

    public Ecole() {
        // ... Initialisation des listes et chargement des données depuis les fichiers ...
        this.baseDeDonneesEleves = new ArrayList<>();
        chargerElevesDepuisFichier(); // Charger les élèves depuis le fichier à la création de l'école
        this.baseDeDonneesEnseignants = new ArrayList<>();
        chargerEnseignantsDepuisFichier();
        this.quizParLangue = new HashMap<>();
        chargerQuizzesDepuisFichier(); // Charger les quizzes lors de la création de l'école
    }

    public void creerCompte(String nom, String langueEnseignee, String nomUtilisateur, String motDePasse, TypeUtilisateur typeUtilisateur) {
        if (typeUtilisateur == TypeUtilisateur.ENSEIGNANT) {
            Enseignant nouvelEnseignant = new Enseignant(nom, langueEnseignee, nomUtilisateur, motDePasse);
            baseDeDonneesEnseignants.add(nouvelEnseignant);
            System.out.println("Compte créé avec succès pour l'enseignant : " + nouvelEnseignant.getNom() + ".");
            sauvegarderEnseignantsDansFichier();
        } else if (typeUtilisateur == TypeUtilisateur.ELEVE) {
            Eleve nouvelEleve = new Eleve(nom, nomUtilisateur, motDePasse);
            baseDeDonneesEleves.add(nouvelEleve);
            System.out.println("Compte créé avec succès pour l'élève : " + nouvelEleve.getNom() + ".");
            sauvegarderElevesDansFichier();
        } else {
            System.out.println("Type d'utilisateur non pris en charge.");
        }
    }

    // Login Eleve
    public void creerCompteEleve(String nom, String nomUtilisateur, String motDePasse) {
        // ... Création d'un compte élève et sauvegarde dans le fichier ...
    	Eleve nouvelEleve = new Eleve(nom, nomUtilisateur, motDePasse);
        baseDeDonneesEleves.add(nouvelEleve);
        System.out.println("Compte créé avec succès pour l'élève : " + nouvelEleve.getNom() + ".");
        sauvegarderElevesDansFichier();
    }
    
    public void creerCompteEnseignant(String nom, String langueEnseignee, String nomUtilisateur, String motDePasse) {
        // ... Création d'un compte enseignant et sauvegarde dans le fichier ...
    	Enseignant nouvelEnseigant = new Enseignant(nom, langueEnseignee, nomUtilisateur, motDePasse);
        baseDeDonneesEnseignants.add(nouvelEnseigant);
        System.out.println("Compte créé avec succès pour l'enseignant : " + nouvelEnseigant.getNom() + ".");
        
        sauvegarderEnseignantsDansFichier(); 
    }

    public Eleve connecterEleve(String nomUtilisateur, String motDePasse) {
        // ... Connexion d'un élève et retour de l'instance d'Élève ...
        for (Eleve eleve : baseDeDonneesEleves) {
            if (eleve.getNomUtilisateur().equals(nomUtilisateur) && eleve.getMotDePasse().equals(motDePasse)) {
                System.out.println("Connexion réussie. Bienvenue, " + eleve.getNom() + "!");
                return eleve;
            }
        }
        System.out.println("Échec de la connexion. Veuillez vérifier vos informations d'identification.");
        return null;
    }
        
    public Enseignant connecterEnseignant(String nomUtilisateur, String motDePasse) {
        // ... Connexion d'un enseignant et retour de l'instance d'Enseignant ...
    	for (Enseignant enseignant : baseDeDonneesEnseignants) {
            if (enseignant.getNomUtilisateur().equals(nomUtilisateur) && enseignant.getMotDePasse().equals(motDePasse)) {
                System.out.println("Connexion réussie. Bienvenue, " + enseignant.getNom() + "!");
                return enseignant;
            }
        }
        System.out.println("Échec de la connexion. Veuillez vérifier vos informations d'identification.");
        return null;
    }

    public Utilisateur connecter(String nomUtilisateur, String motDePasse, TypeUtilisateur typeUtilisateur) {
        // ... Connexion d'un utilisateur (Élève ou Enseignant) et retour de l'instance correspondante ...
    	if (typeUtilisateur == TypeUtilisateur.ELEVE) {
            return connecterEleve(nomUtilisateur, motDePasse);
        } else if (typeUtilisateur == TypeUtilisateur.ENSEIGNANT) {
            return connecterEnseignant(nomUtilisateur, motDePasse);
        } else {
            System.out.println("Type d'utilisateur non valide.");
            return null;
        }
    }
    
    // File Eleves
    private void creerFichierEleves() {
        // ... Création du fichier eleves.txt ...
        try (FileWriter writer = new FileWriter(FICHIER_ELEVES)) {
            System.out.println("Fichier eleves.txt créé avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier eleves.txt : " + e.getMessage());
        }
    }
    
    private void sauvegarderElevesDansFichier() {
        // ... Sauvegarde des élèves dans le fichier ...
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_ELEVES))) {
            for (Eleve eleve : baseDeDonneesEleves) {
                // Écrire les informations de chaque élève dans le fichier
                writer.write(eleve.getId() + ", " + eleve.getNom() + ", " + eleve.getNomUtilisateur() + ", " + eleve.getMotDePasse());
                
                writer.newLine(); // Nouvelle ligne pour chaque élève
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier eleves.txt : " + e.getMessage());
        }
    }

    private void chargerElevesDepuisFichier() {
        // ... Chargement des élèves depuis le fichier ...
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_ELEVES))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infosEleve = ligne.split(",");
                if (infosEleve.length == 4) {
                    int id = Integer.parseInt(infosEleve[0].replaceAll("\\D", "").trim());
                    String nom = infosEleve[1].trim();
                    String nomUtilisateur = infosEleve[2].trim();
                    String motDePasse = infosEleve[3].trim();
                    
                    Eleve eleve = new Eleve(nom, nomUtilisateur, motDePasse);
                    eleve.setId(id); 

                    baseDeDonneesEleves.add(eleve);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors de la lecture du fichier eleves.txt : " + e.getMessage());
        
            // Si le fichier n'existe pas, créez-le et écrivez dedans
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("Le fichier eleves.txt n'existe pas. Création du fichier...");
                creerFichierEleves();
            }
        }
    }
        public void afficherListeEleves() {
        // ... Affichage de la liste des élèves ...
        if (baseDeDonneesEleves.isEmpty()) {
            System.out.println("La liste des élèves est vide.");
            return;
        }

        System.out.println("Liste des élèves dans la base de données :");
        for (Eleve eleve : baseDeDonneesEleves) {
            System.out.println("ID : " + eleve.getId() + ", Nom : " + eleve.getNom());
        }
    }
    
    // Files Enseignants
        private void creerFichierEnseignants() {
            // ... Création du fichier enseignants.txt ...
            try (FileWriter writer = new FileWriter(FICHIER_ENSEIGNANTS)) {
                System.out.println("Fichier enseignants.txt créé avec succès.");
            } catch (IOException e) {
                System.out.println("Erreur lors de la création du fichier enseignants.txt : " + e.getMessage());
            }
        }

        private void sauvegarderEnseignantsDansFichier() {
            // ... Sauvegarde des enseignants dans le fichier ...
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_ENSEIGNANTS))) {
                for (Enseignant enseignant : baseDeDonneesEnseignants) {
                    // Écrire les informations de chaque enseignant dans le fichier
                    writer.write(enseignant.getId() + ", " + enseignant.getNom() + ", " + enseignant.getLangueEnseignee() + ", " + enseignant.getNomUtilisateur() + ", " + enseignant.getMotDePasse());
                    writer.newLine(); // Nouvelle ligne pour chaque enseignant
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de l'écriture du fichier enseignants.txt : " + e.getMessage());
            }
        }

    private void chargerEnseignantsDepuisFichier() {
        // ... Chargement des enseignants depuis le fichier ...
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_ENSEIGNANTS))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infosEnseignant = ligne.split(",");
                if (infosEnseignant.length == 5) {
                    int id = Integer.parseInt(infosEnseignant[0].replaceAll("\\D", "").trim());
                    String nom = infosEnseignant[1].trim();
                    String langueEnseignee = infosEnseignant[2].trim();
                    String nomUtilisateur = infosEnseignant[3].trim();
                    String motDePasse = infosEnseignant[4].trim();
                    
                    Enseignant enseignant = new Enseignant(nom, langueEnseignee, nomUtilisateur, motDePasse);
                    enseignant.setId(id); 

                    baseDeDonneesEnseignants.add(enseignant);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors de la lecture du fichier enseignants.txt : " + e.getMessage());
        
            // Si le fichier n'existe pas, créez-le et écrivez dedans
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("Le fichier enseignants.txt n'existe pas. Création du fichier...");
                creerFichierEnseignants();
            }
        }
    }
        
    public void afficherListeEnseignant() {
        // ... Affichage de la liste des enseignants ...
        System.out.println("Liste des enseignants dans la base de données :");
        for (Enseignant enseignant : baseDeDonneesEnseignants) {
            System.out.println(enseignant.getId() + ", Nom : " + enseignant.getNom());
        }
    }
    
    // Creer des Quiz
    // Ajouter cette méthode pour créer des quizzes et les stocker dans la base de données
    public void creerQuizzes(Enseignant enseignant) {
        // Créer des quizzes pour le français
        Quiz quizFrancaisFacile = new Quiz("Question facile en français ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizFrancaisIntermediaire = new Quiz("Question intermédiaire en français ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizFrancaisDifficile = new Quiz("Question difficile en français ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");

        // Stocker les quizzes dans la base de données
        ajouterQuiz(enseignant, quizFrancaisFacile, "francais", "facile");
        ajouterQuiz(enseignant, quizFrancaisIntermediaire, "francais", "intermediaire");
        ajouterQuiz(enseignant, quizFrancaisDifficile, "francais", "difficile");

        // Créer des quizzes pour l'anglais
        Quiz quizAnglaisFacile = new Quiz("Question facile en anglais ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizAnglaisIntermediaire = new Quiz("Question intermediaire en anglais ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizAnglaisDifficile = new Quiz("Question difficile en anglais ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");

        // Stocker les quizzes dans la base de données
        ajouterQuiz(enseignant, quizAnglaisFacile, "anglais", "facile");
        ajouterQuiz(enseignant, quizAnglaisIntermediaire, "anglais", "intermediaire");
        ajouterQuiz(enseignant, quizAnglaisDifficile, "anglais", "difficile");
        
        // Créer des quizzes pour l'arabe
        Quiz quizArabeFacile = new Quiz("Question facile en arabe ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizArabeIntermediaire = new Quiz("Question intermediaire en arabe ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizArabeDifficile = new Quiz("Question difficile en arabe ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");

        // Stocker les quizzes dans la base de données
        ajouterQuiz(enseignant, quizArabeFacile, "arabe", "facile");
        ajouterQuiz(enseignant, quizArabeIntermediaire, "arabe", "intermediaire");
        ajouterQuiz(enseignant, quizArabeDifficile, "arabe", "difficile");
        
        // Créer des quizzes pour le berbère
        Quiz quizBebereFacile = new Quiz("Question facile en berbère ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizBebereIntermediaire = new Quiz("Question intermediaire en berbère ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");
        Quiz quizBebereDifficile = new Quiz("Question difficile en berbère ?", List.of("Option 1", "Option 2", "Option 3"), "Correct");

        // Stocker les quizzes dans la base de données
        ajouterQuiz(enseignant, quizBebereFacile, "berbère", "facile");
        ajouterQuiz(enseignant, quizBebereIntermediaire, "berbère", "intermediaire");
        ajouterQuiz(enseignant, quizBebereDifficile, "berbère", "difficile");

        sauvegarderQuizzesDansFichier(); // sauvegarder les quizzes après les avoir créés

    }
 
 /** "// Créer le fichier des quizzes s'il n'existe pas
    private void creerFichierQuizzes() {
        try (FileWriter writer = new FileWriter(FICHIER_QUIZZES)) {
            System.out.println("Fichier quizzes.txt créé avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier quizzes.txt : " + e.getMessage());
        }
    } */
    
 // Sauvegarder les quizzes dans le fichier texte
    private void sauvegarderQuizzesDansFichier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_QUIZZES))) {
            for (Map.Entry<String, Map<String, List<Quiz>>> entryLangue : quizParLangue.entrySet()) {
                String langue = entryLangue.getKey();
                for (Map.Entry<String, List<Quiz>> entryNiveau : entryLangue.getValue().entrySet()) {
                    String niveau = entryNiveau.getKey();
                    for (Quiz quiz : entryNiveau.getValue()) {
                        // Écrire les informations de chaque quiz dans le fichier
                        writer.write("Langue : " + langue);
                        writer.newLine();
                        writer.write("Niveau : " + niveau);
                        writer.newLine();
                        writer.write("Question : " + quiz.getQuestion());
                        writer.newLine();
                        writer.write("Options : " + String.join(", ", quiz.getOptions()));
                        writer.newLine();
                        writer.write("Réponse Correcte : " + quiz.getCorrectAnswer());
                        writer.newLine();
                        writer.newLine(); // Ligne vide entre chaque quiz
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier quizzes.txt : " + e.getMessage());
        }
    }

 // Charger les quizzes depuis un fichier texte
    private void chargerQuizzesDepuisFichier() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_QUIZZES))) {
            String ligne;
            String langueActuelle = null;
            String niveauActuel = null;

            while ((ligne = reader.readLine()) != null) {
                if (ligne.startsWith("Langue : ")) {
                    langueActuelle = ligne.substring("Langue : ".length());
                } else if (ligne.startsWith("Niveau : ")) {
                    niveauActuel = ligne.substring("Niveau : ".length());
                } else if (ligne.startsWith("Question : ")) {
                    String question = ligne.substring("Question : ".length());
                    List<String> options = Arrays.asList(reader.readLine().substring("Options : ".length()).split(", "));
                    String reponseCorrecte = reader.readLine().substring("Réponse Correcte : ".length());

                    Quiz quiz = new Quiz(question, options, reponseCorrecte);
                    ajouterQuiz(null, quiz, langueActuelle, niveauActuel); 
                }
            }

            System.out.println("Chargement des quizzes réussi.");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des quizzes : " + e.getMessage());
        }
    }

    // Ajout d'un quiz à la base de données
    public void ajouterQuiz(Enseignant enseignant, Quiz quiz, String langue, String niveau) {
        // Vérifier si la langue existe déjà dans la map
        if (quizParLangue.containsKey(langue)) {
            Map<String, List<Quiz>> quizzesParNiveau = quizParLangue.get(langue);
            // Si le niveau existe, ajouter le quiz à la liste existante
            if (quizzesParNiveau.containsKey(niveau)) {
                quizzesParNiveau.get(niveau).add(quiz);
            } else {
                // Si le niveau n'existe pas, créer une nouvelle liste avec le quiz
                List<Quiz> nouveauxQuizzes = new ArrayList<>();
                nouveauxQuizzes.add(quiz);
                quizzesParNiveau.put(niveau, nouveauxQuizzes);
            }
        } else {
            // Si la langue n'existe pas, créer une nouvelle map avec le niveau et le quiz
            Map<String, List<Quiz>> nouveauxQuizzesParNiveau = new HashMap<>();
            List<Quiz> nouveauxQuizzes = new ArrayList<>();
            nouveauxQuizzes.add(quiz);
            nouveauxQuizzesParNiveau.put(niveau, nouveauxQuizzes);
            quizParLangue.put(langue, nouveauxQuizzesParNiveau);
        }
    }
    
    // Méthode pour récupérer et répondre à un quiz pour un élève
    public void repondreQuiz(Eleve eleve) {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'élève de choisir la langue
        System.out.println("Choisissez la langue (franais, anglais, arabe, berbere) :");
        String langue = scanner.nextLine().toLowerCase();

        // Vérifier si la langue existe dans la base de données
        if (quizParLangue.containsKey(langue)) {
            // Afficher les niveaux disponibles pour cette langue
            System.out.println("Niveaux disponibles pour la langue " + langue + " :");
            quizParLangue.get(langue).keySet().forEach(System.out::println);

            // Demander à l'élève de choisir le niveau
            System.out.println("Choisissez le niveau :");
            String niveau = scanner.nextLine().toLowerCase();

            // Vérifier si le niveau existe dans la base de données
            if (quizParLangue.get(langue).containsKey(niveau)) {
                List<Quiz> quizzes = quizParLangue.get(langue).get(niveau);

                // Parcourir tous les quizzes disponibles pour ce niveau
                for (Quiz quiz : quizzes) {
                    // Afficher la question et les options
                    System.out.println("Question : " + quiz.getQuestion());
                    System.out.println("Options : " + quiz.getOptions());

                    // Demander à l'élève de répondre à la question
                    System.out.println("Votre réponse : ");
                    String reponseEleve = scanner.nextLine();

                    // Vérifier si la réponse est correcte
                    if (reponseEleve.equalsIgnoreCase(quiz.getCorrectAnswer())) {
                        System.out.println("Correct ! Vous avez bien répondu.\n");
                        eleve.incrementerScore();
                    } else {
                        System.out.println("Incorrect. La réponse correcte était : " + quiz.getCorrectAnswer() + "\n");
                    }
                }
            } else {
                System.out.println("Aucun quiz disponible pour le niveau " + niveau + " dans la langue " + langue + ".");
            }
        } else {
            System.out.println("Langue non prise en charge.");
        }
        scanner.close();
    }
 // Méthode pour récupérer les quizzes disponibles pour une langue et un niveau donnés
    public List<Quiz> getQuizzesDisponibles(String langue, String niveau) {
        List<Quiz> quizzes = new ArrayList<>();

        // Vérifier si la langue existe dans la map
        if (quizParLangue.containsKey(langue)) {
            Map<String, List<Quiz>> quizzesParNiveau = quizParLangue.get(langue);
            // Si le niveau existe, récupérer les quizzes pour ce niveau
            if (quizzesParNiveau.containsKey(niveau)) {
                quizzes = quizzesParNiveau.get(niveau);
            }
        }

        return quizzes;		
	}

    
}