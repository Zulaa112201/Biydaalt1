package edu.csa311.organizer;



import java.util.ArrayList;
import java.util.List;

import edu.csa311.Card;

// LIFO zarchmaar garah function
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
// FIFO zarchmaar garah function
// public class RecentMistakesFirstSorter implements CardOrganizer {
//     @Override
//     public void organize(List<Card> cards) {
//         cards.sort((c1, c2) -> {
//             if (c1.isLastAttemptFailed() == c2.isLastAttemptFailed()) {
//                 return 0;
//             }
//             return c1.isLastAttemptFailed() ? -1 : 1; // c1 алдсан бол урагшаа
//         });
//     }
// }