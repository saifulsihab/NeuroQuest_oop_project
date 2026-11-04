package neuroquest;

public class TrueFalseQ extends Question {

    public TrueFalseQ(String questionText, String correctAnswer, String difficulty) {
        super(questionText, correctAnswer, difficulty);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(getCorrectAnswer());
    }
}