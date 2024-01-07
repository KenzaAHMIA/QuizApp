package levelLanguage;

import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Utilisateur {
    private static int dernierId = 0;

    private int id;
    private String nom;
    private String langueEnseignee;
    private List<Quiz> quizzesCrees;

    /**
     * Constructeur de la classe Enseignant.
     *
     * @param nom             Le nom de l'enseignant.
     * @param langueEnseignee La langue enseignée par l'enseignant.
     * @param nomUtilisateur Le nom d'utilisateur de l'enseignant.
     * @param motDePasse      Le mot de passe de l'enseignant.
     */
    public Enseignant(String nom, String langueEnseignee, String nomUtilisateur, String motDePasse) {
        super(nomUtilisateur, motDePasse);
        this.setId(++dernierId); // Incrémente l'ID statique et l'assigne à l'enseignant.
        this.nom = nom;
        this.langueEnseignee = langueEnseignee;
        this.quizzesCrees = new ArrayList<>(); // Initialise la liste des quizzes créés.
    }

    /**
     * Obtient le nom de l'enseignant.
     *
     * @return Le nom de l'enseignant.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient la langue enseignée par l'enseignant.
     *
     * @return La langue enseignée par l'enseignant.
     */
    public String getLangueEnseignee() {
        return langueEnseignee;
    }

    /**
     * Obtient l'ID de l'enseignant.
     *
     * @return L'ID de l'enseignant.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'ID de l'enseignant.
     *
     * @param id Le nouvel ID de l'enseignant.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient la liste des quizzes créés par l'enseignant.
     *
     * @return La liste des quizzes créés par l'enseignant.
     */
    public List<Quiz> getQuizzesCrees() {
        return quizzesCrees;
    }

    /**
     * Ajoute un quiz à la liste des quizzes créés par l'enseignant.
     *
     * @param quiz Le quiz à ajouter.
     */
    public void ajouterQuizCree(Quiz quiz) {
        quizzesCrees.add(quiz);
    }
}
