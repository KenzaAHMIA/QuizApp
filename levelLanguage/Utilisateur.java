package levelLanguage;

/**
 * La classe Utilisateur représente un utilisateur de base dans le système.
 * Elle contient des informations générales telles que le nom d'utilisateur
 * et le mot de passe.
 */
public class Utilisateur {
    protected String nomUtilisateur;  // Le nom d'utilisateur de l'utilisateur
    protected String motDePasse;      // Le mot de passe de l'utilisateur

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param nomUtilisateur Le nom d'utilisateur de l'utilisateur.
     * @param motDePasse     Le mot de passe de l'utilisateur.
     */
    public Utilisateur(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    /**
     * Obtient le nom d'utilisateur de l'utilisateur.
     *
     * @return Le nom d'utilisateur.
     */
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe.
     */
    public String getMotDePasse() {
        return motDePasse;
    }
}
