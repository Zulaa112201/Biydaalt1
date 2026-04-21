package edu.csa311;

public class AchievementManager {
    public void checkCardAchievements(Card card) {
        if (card.getTotalAttempts() > 5) { 
            System.out.println("ACHIEVEMENT UNLOCKED: [REPEAT] You tried this card more than 5 times.");
        }
        if (card.getCorrectCount() >= 3) {
            System.out.println("ACHIEVEMENT UNLOCKED: [CONFIDENT] You answered this card correctly at least 3 times.");
        }
    }

    public void checkRoundAchievement() {
        System.out.println("ACHIEVEMENT UNLOCKED: [CORRECT] You answered all cards correctly in this round!");
    }

    public void checkTimeAchievement(double avgTimeSeconds) {
        if (avgTimeSeconds < 5.0) {
            System.out.println("ACHIEVEMENT UNLOCKED: [FAST] Your average response time was " + String.format("%.2f", avgTimeSeconds) + " seconds.");
        }
    }
}