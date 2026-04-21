package edu.csa311;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.csa311.organizer.CardOrganizer;
import edu.csa311.organizer.RecentMistakesFirstSorter;
import edu.csa311.organizer.WorstFirstSorter;

public class FlashcardGame {
    private List<Card> cards;
    private CLIParser config;
    private AchievementManager achManager;
    private CardOrganizer organizer;

    public FlashcardGame(List<Card> cards, CLIParser config) {
        this.cards = cards;
        this.config = config;
        this.achManager = new AchievementManager();

        if (config.invertCards) {
            for (Card c : cards)
                c.invert();
        }

        // FlashcardGame.java-ийн constructor дотор:
        if ("recent-mistakes-first".equals(config.order)) {
            this.organizer = new RecentMistakesFirstSorter();
        } else if ("worst-first".equals(config.order)) {
            this.organizer = new WorstFirstSorter();
        } else {
            this.organizer = list -> Collections.shuffle(list);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean allFinished = false;

        while (!allFinished) {
            organizer.organize(cards);
            allFinished = true;
            boolean hasMistakeInRound = false;
            int cardsPlayedInRound = 0;

            long roundStartTime = System.currentTimeMillis();

            for (Card card : cards) {
                if (card.getCorrectCount() >= config.repetitions)
                    continue;

                allFinished = false;
                cardsPlayedInRound++;

                System.out.println("\nQuestion: " + card.getQuestion());
                System.out.print("Your answer: ");
                String answer = scanner.nextLine();

                if (answer.trim().equalsIgnoreCase(card.getAnswer())) {
                    System.out.println("Correct!");
                    card.recordAttempt(true);
                } else {
                    System.out.println("Incorrect. The correct answer was: " + card.getAnswer());
                    card.recordAttempt(false);
                    hasMistakeInRound = true;
                }
                achManager.checkCardAchievements(card);
            }

            long roundEndTime = System.currentTimeMillis();

            if (cardsPlayedInRound > 0) {
                double totalTimeSeconds = (roundEndTime - roundStartTime) / 1000.0;
                double avgTimePerCard = totalTimeSeconds / cardsPlayedInRound;
                achManager.checkTimeAchievement(avgTimePerCard);

                if (!hasMistakeInRound) {
                    achManager.checkRoundAchievement();
                }
            }
        }
        System.out.println("\nCongratulations! You have learned all cards!");
        scanner.close();
    }
}