package edu.csa311;

public class Card {
    private String question;
    private String answer;
    private int correctCount = 0;
    private int totalAttempts = 0;
    private boolean lastAttemptFailed = false;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public int getCorrectCount() { return correctCount; }
    public int getTotalAttempts() { return totalAttempts; }
    public boolean isLastAttemptFailed() { return lastAttemptFailed; }

    public void invert() {
        String temp = question;
        question = answer;
        answer = temp;
    }

    public void recordAttempt(boolean correct) {
        totalAttempts++;
        if (correct) {
            correctCount++;
            lastAttemptFailed = false;
        } else {
            lastAttemptFailed = true;
        }
    }

    public double getSuccessRate() {
        if (totalAttempts == 0) {
            return 0.0;
        }
        return (double) correctCount / totalAttempts;
    }
}
