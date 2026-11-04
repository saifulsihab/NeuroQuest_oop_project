package neuroquest;

public class MultipleChoiceQ extends Question {
    private String[] options;

    public MultipleChoiceQ(String questionText, String correctAnswer, String difficulty, String[] options) {
        super(questionText, correctAnswer, difficulty);
        this.options = options;
    }

    public void displayOptions() {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        
        try {
            int index = Integer.parseInt(userAnswer) - 1;
            if (index >= 0 && index < options.length) {
                return options[index].equalsIgnoreCase(getCorrectAnswer());
            }
        } catch (NumberFormatException e) {
            
        }

        
        return userAnswer.equalsIgnoreCase(getCorrectAnswer());
    }
}