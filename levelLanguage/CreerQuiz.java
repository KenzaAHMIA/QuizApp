package levelLanguage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe CreerQuiz permet à un enseignant connecté de créer un quiz
 * en saisissant les informations nécessaires telles que la question, la langue,
 * le niveau, les options et la réponse correcte.
 */
public class CreerQuiz {
    private Ecole ecole;  // L'école associée à la création du quiz
    private Enseignant enseignantConnecte;  // L'enseignant connecté qui crée le quiz
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructeur de la classe CreerQuiz.
     *
     * @param ecole              L'école associée à la création du quiz.
     * @param enseignantConnecte L'enseignant connecté qui crée le quiz.
     */
    public CreerQuiz(Ecole ecole, Enseignant enseignantConnecte) {
        this.ecole = ecole;
        this.enseignantConnecte = enseignantConnecte;
    }

    /**
     * Méthode pour permettre à l'enseignant de créer un quiz en saisissant les informations nécessaires.
     */
    public void creerExercice() {
        if (enseignantConnecte == null) {
            System.out.println("Vous devez être connecté en tant qu'enseignant pour créer un quiz.");
            return;
        }

        // Demander à l'enseignant de saisir les informations du quiz
        System.out.print("Entrez la question du quiz : ");
        String question = scanner.nextLine();

        System.out.print("Entrez la langue du quiz (francais/anglais/berbere/arabe) : ");
        String langue = scanner.nextLine().toLowerCase();

        System.out.print("Entrez le niveau du quiz (facile/intermédiaire/difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        // Demander à l'enseignant de saisir les options du quiz
        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Entrez l'option " + i + " : ");
            options.add(scanner.nextLine());
        }

        // Demander à l'enseignant de saisir la réponse correcte
        System.out.print("Entrez la réponse correcte : ");
        String reponseCorrecte = scanner.nextLine();

        // Créer un objet Quiz avec les informations saisies
        Quiz quiz = new Quiz(question, options, reponseCorrecte);

        // Ajouter le quiz à la liste des quiz créés par l'enseignant
        enseignantConnecte.ajouterQuizCree(quiz);

        // Appeler une méthode dans Ecole pour ajouter le quiz à la liste appropriée en fonction de la langue
        ecole.ajouterQuiz(enseignantConnecte, quiz, langue, niveau);
    }
}
