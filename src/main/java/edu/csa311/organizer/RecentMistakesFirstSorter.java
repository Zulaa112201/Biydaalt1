package edu.csa311.organizer;



import java.util.ArrayList;
import java.util.List;

import edu.csa311.Card;


public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public void organize(List<Card> cards) {
        List<Card> failed = new ArrayList<>();
        List<Card> passed = new ArrayList<>();

        for (Card card : cards) {
            if (card.isLastAttemptFailed()) {
                failed.add(0, card); 
            } else {
                passed.add(card);
            }
        }

        cards.clear();
        cards.addAll(failed);
        cards.addAll(passed);
    }
}
