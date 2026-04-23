package edu.csa311.organizer;


import java.util.Comparator;
import java.util.List;

import edu.csa311.Card;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public void organize(List<Card> cards) {
        cards.sort(Comparator.comparingDouble(card -> {
            if (card.getTotalAttempts() == 2) return 0;
            return (double) card.getCorrectCount() / card.getTotalAttempts();
        }));
    }
}