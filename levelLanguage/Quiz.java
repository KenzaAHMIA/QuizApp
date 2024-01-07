package levelLanguage;

import java.util.List;

public class Quiz {
    // Propriétés représentant un quiz
    private String question;          // La question du quiz
    private List<String> options;     // Les options possibles pour la réponse à la question
    private String correctAnswer;     // La réponse correcte à la question

    /**
     * Constructeur de la classe Quiz.
     *
     * @param question      La question du quiz.
     * @param options       Les options possibles pour la réponse à la question.
     * @param correctAnswer La réponse correcte à la question.
     */
    public Quiz(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Obtient la question du quiz.
     *
     * @return La question du quiz.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Obtient les options possibles pour la réponse à la question.
     *
     * @return Les options possibles pour la réponse à la question.
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Obtient la réponse correcte à la question.
     *
     * @return La réponse correcte à la question.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
