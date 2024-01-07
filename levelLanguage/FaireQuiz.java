package levelLanguage;

import java.util.List;
import java.util.Scanner;

public class FaireQuiz {
    private Eleve eleveConnecte;

    public FaireQuiz(Eleve eleveConnecte) {
        this.eleveConnecte = eleveConnecte;
    }

    public void faireQuiz(Ecole ecole) {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'élève de choisir la langue et le niveau
        System.out.println("Choisissez la langue du Quiz (francais/anglais/arabe/berbere) : ");
        String langue = scanner.nextLine().toLowerCase();

        System.out.println("Choisissez le niveau du Quiz (facile/intermédiaire/difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        // Récupérer les quizzes disponibles
        List<Quiz> quizzes = ecole.getQuizzesDisponibles(langue, niveau);

        // Si aucun quiz n'est disponible, informer l'élève et sortir de la méthode
        if (quizzes.isEmpty()) {
            System.out.println("Aucun quiz disponible pour la langue " + langue + " et le niveau " + niveau + ".");
            scanner.close();
            return;
        }

        // Afficher les détails des quizzes
        System.out.println("Quizzes disponibles :");
        for (Quiz quiz : quizzes) {
            System.out.println("Question : " + quiz.getQuestion());
            System.out.println("Options : " + quiz.getOptions());
        }

        // Répondre aux quizzes
        for (Quiz quiz : quizzes) {
            System.out.println("Question : " + quiz.getQuestion());
            System.out.println("Options : " + quiz.getOptions());

            // Demander à l'élève de répondre à la question
            System.out.print("Votre réponse : ");
            String reponseEleve = scanner.nextLine();

            // Vérifier si la réponse est correcte
            if (reponseEleve.equalsIgnoreCase(quiz.getCorrectAnswer())) {
                System.out.println("Correct ! Vous avez bien répondu.\n");
                eleveConnecte.incrementerScore();
            } else {
                System.out.println("Incorrect. La réponse correcte était : " + quiz.getCorrectAnswer() + "\n");
            }
        }

        // Afficher le score final
        System.out.println("Votre score final : " + eleveConnecte.getScore() + "/" + quizzes.size());

        scanner.close();
    }
}
