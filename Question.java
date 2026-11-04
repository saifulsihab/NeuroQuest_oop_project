package neuroquest;

public abstract class Question {
    private String questionText;
    private String correctAnswer;
    private String difficulty;

    public Question(String questionText, String correctAnswer, String difficulty) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    
    public abstract boolean checkAnswer(String userAnswer);
}