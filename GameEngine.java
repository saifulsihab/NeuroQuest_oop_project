package neuroquest;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Player player;
    private ArrayList<Question> easyQuestions;
    private ArrayList<Question> mediumQuestions;
    private ArrayList<Question> hardQuestions;
    private Scanner scanner;
    private Random rand;

    public GameEngine() {
        scanner = new Scanner(System.in);
        rand = new Random();
        easyQuestions = new ArrayList<>();
        mediumQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        // Easy Questions
        easyQuestions.add(new MultipleChoiceQ("What is 2 + 2?", "4", "EASY", new String[]{"3", "4", "5", "6"}));
        easyQuestions.add(new TrueFalseQ("The sky is blue.", "true", "EASY"));
        easyQuestions.add(new MultipleChoiceQ("What is the capital of France?", "Paris", "EASY", new String[]{"London", "Berlin", "Madrid", "Paris"}));

        // Medium Questions
        mediumQuestions.add(new MultipleChoiceQ("What is 12 * 12?", "144", "MEDIUM", new String[]{"124", "144", "154", "164"}));
        mediumQuestions.add(new MultipleChoiceQ("Which planet is known as the Red Planet?", "Mars", "MEDIUM", new String[]{"Venus", "Mars", "Jupiter", "Saturn"}));
        mediumQuestions.add(new TrueFalseQ("Water boils at 100 degrees Celsius.", "true", "MEDIUM"));

        // Hard Questions
        hardQuestions.add(new MultipleChoiceQ("What is the square root of 144?", "12", "HARD", new String[]{"10", "11", "12", "13"}));
        hardQuestions.add(new TrueFalseQ("The mitochondria is the powerhouse of the cell.", "true", "HARD"));
        hardQuestions.add(new MultipleChoiceQ("Who wrote 'Romeo and Juliet'?", "Shakespeare", "HARD", new String[]{"Shakespeare", "Hemingway", "Tolkien", "Rowling"}));
    }

    public void startGame() {
        System.out.println("========================================");
        System.out.println("         Welcome to NeuroQuest!          ");
        System.out.println("   An Adaptive Educational Quiz Game     ");
        System.out.println("========================================");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);

        String currentDifficulty = "EASY";
        int totalQuestions = 10;

        for (int i = 1; i <= totalQuestions; i++) {
            System.out.println("\n--- Question " + i + " (Level: " + currentDifficulty + ") ---");
            
            Question q = getQuestionByDifficulty(currentDifficulty);
            if (q == null) {
                System.out.println("No questions available for this level.");
                break;
            }

            System.out.println("Q: " + q.getQuestionText());
            
            
            if (q instanceof MultipleChoiceQ) {
                ((MultipleChoiceQ) q).displayOptions();
                System.out.print("Enter option number (1-4) or type the answer text: ");
            } else if (q instanceof TrueFalseQ) {
                
                System.out.println("(Type 'true' for True, or 'false' for False)");
                System.out.print("Your answer: ");
            }
            // ------------------------------------

            String userAnswer = scanner.nextLine();

            boolean isCorrect = q.checkAnswer(userAnswer);
            player.updateScore(isCorrect);

            if (isCorrect) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! Correct answer: " + q.getCorrectAnswer());
            }

            double accuracy = player.calculateAccuracy();
            if (accuracy >= 80.0) {
                currentDifficulty = "HARD";
            } else if (accuracy >= 50.0) {
                currentDifficulty = "MEDIUM";
            } else {
                currentDifficulty = "EASY";
            }
        }

        //Final Score & Grade Calculation
        double finalAccuracy = player.calculateAccuracy();
        
        String grade;
        String statusMessage;

        if (finalAccuracy >= 90.0) {
            grade = "A+ (Outstanding)";
            statusMessage = "🌟 Excellent! You are a NeuroMaster!";
        } else if (finalAccuracy >= 80.0) {
            grade = "A (Very Good)";
            statusMessage = "Great job! Keep learning!";
        } else if (finalAccuracy >= 70.0) {
            grade = "B (Good)";
            statusMessage = "Good effort! Try harder topics.";
        } else if (finalAccuracy >= 60.0) {
            grade = "C (Average)";
            statusMessage = "📖 Needs more practice. Review your basics.";
        } else if (finalAccuracy >= 50.0) {
            grade = "D (Below Average)";
            statusMessage = "Don't give up! Focus on Easy questions first.";
        } else {
            grade = "F (Fail)";
            statusMessage = "🔄 Let's restart! You can do better next time.";
        }

        System.out.println("\n========================================");
        System.out.println("🏁 Game Over, " + player.getName() + "!");
        System.out.println("Correct Answers : " + player.getCorrectCount());
        System.out.println("Wrong Answers   : " + player.getWrongCount());
        System.out.println("Final Accuracy  : " + String.format("%.2f", finalAccuracy) + "%");
        System.out.println("Performance Grade: " + grade);
        System.out.println("Status          : " + statusMessage);
        System.out.println("========================================");
        scanner.close();
    }

    private Question getQuestionByDifficulty(String difficulty) {
        if (difficulty.equalsIgnoreCase("EASY") && !easyQuestions.isEmpty()) {
            int index = rand.nextInt(easyQuestions.size());
            return easyQuestions.remove(index);
        } else if (difficulty.equalsIgnoreCase("MEDIUM") && !mediumQuestions.isEmpty()) {
            int index = rand.nextInt(mediumQuestions.size());
            return mediumQuestions.remove(index);
        } else if (difficulty.equalsIgnoreCase("HARD") && !hardQuestions.isEmpty()) {
            int index = rand.nextInt(hardQuestions.size());
            return hardQuestions.remove(index);
        } else {
            if (!easyQuestions.isEmpty()) return easyQuestions.remove(0);
            if (!mediumQuestions.isEmpty()) return mediumQuestions.remove(0);
            if (!hardQuestions.isEmpty()) return hardQuestions.remove(0);
            return null;
        }
    }
}