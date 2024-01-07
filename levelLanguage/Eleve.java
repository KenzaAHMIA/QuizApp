package levelLanguage;

public class Eleve extends Utilisateur {
    private static int dernierId = 0;
    private int id;
    private String nom;
    private int score;

    /**
     * Constructeur de la classe Eleve.
     *
     * @param nom             Le nom de l'élève.
     * @param nomUtilisateur Le nom d'utilisateur de l'élève.
     * @param motDePasse      Le mot de passe de l'élève.
     */
    public Eleve(String nom, String nomUtilisateur, String motDePasse) {
        super(nomUtilisateur, motDePasse);
        setId(++dernierId); // Incrémente l'ID statique et l'assigne à l'élève.
        this.nom = nom;
        this.score = 0; // Initialise le score à zéro lors de la création de l'élève.
    }

    /**
     * Obtient le nom de l'élève.
     *
     * @return Le nom de l'élève.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient l'ID de l'élève.
     *
     * @return L'ID de l'élève.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'ID de l'élève.
     *
     * @param id Le nouvel ID de l'élève.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le score de l'élève.
     *
     * @return Le score de l'élève.
     */
    public int getScore() {
        return score;
    }

    /**
     * Incrémente le score de l'élève.
     */
    public void incrementerScore() {
        score++;
    }
}
