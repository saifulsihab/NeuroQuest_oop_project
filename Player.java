package neuroquest;

public class Player {
    private String name;
    private int correctCount;
    private int wrongCount;

    public Player(String name) {
        this.name = name;
        this.correctCount = 0;
        this.wrongCount = 0;
    }

    public void updateScore(boolean isCorrect) {
        if (isCorrect) {
            correctCount++;
        } else {
            wrongCount++;
        }
    }

    public double calculateAccuracy() {
        int total = correctCount + wrongCount;
        if (total == 0) return 0.0;
        return (double) correctCount / total * 100;
    }

    public String getName() { return name; }
    public int getCorrectCount() { return correctCount; }
    public int getWrongCount() { return wrongCount; }
}