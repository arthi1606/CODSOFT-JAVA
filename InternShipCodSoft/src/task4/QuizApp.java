package task4;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static Question[] questions;
    private static int score = 0;
    private static final int TIMER_DURATION = 10; // seconds
    private static boolean timeUp = false;
    private static Scanner scanner = new Scanner(System.in);
    

    public static void main(String[] args) {
        initializeQuestions();
        for (Question question : questions) {
            displayQuestionWithTimer(question);
            
            
        }
        displayResults();
    }

    private static void initializeQuestions() {
        questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
            new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 2),
            new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Mark Twain", "J.K. Rowling", "Ernest Hemingway"}, 1)
        };
    }
    

    private static void displayQuestionWithTimer(Question question) {
        question.displayQuestion();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                timeUp = true;
                System.out.println("\nTime's up!");
            }
        }, TIMER_DURATION * 1000);

        int answer = getAnswerFromUser();
        timer.cancel();

        if (!timeUp && question.isCorrect(answer)) {
            score++;
        }

        timeUp = false; 
    }

    private static int getAnswerFromUser() {
        int answer = -1;
        while (!timeUp && (answer < 1 || answer > 4)) {
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                scanner.next(); 
            }
        }
        return answer;
    }

    private static void displayResults() {
        System.out.println("\nQuiz finished!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("\nSummary:");

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("Correct answer: " + question.getOptions()[question.getCorrectAnswer() - 1]);
            System.out.println();
        }
    }
}
