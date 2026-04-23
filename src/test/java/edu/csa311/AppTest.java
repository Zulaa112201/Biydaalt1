package edu.csa311;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.csa311.organizer.RecentMistakesFirstSorter;
import edu.csa311.organizer.WorstFirstSorter;

public class AppTest {

    @Test
    public void testRecentMistakesFirst() {
        Card c1 = new Card("Q1", "A1"); // Зөв
        Card c2 = new Card("Q2", "A2"); // Буруу
        c2.recordAttempt(false);
        
        List<Card> cards = new ArrayList<>(Arrays.asList(c1, c2));
        new RecentMistakesFirstSorter().organize(cards);
        
        assertEquals("Q2", cards.get(0).getQuestion(), "Буруу хариулсан карт хамгийн эхэнд байх ёстой.");
    }

    @Test
    public void testWorstFirst() {
        Card best = new Card("Best", "A");
        best.recordAttempt(true); // 100% 
        
        Card worst = new Card("Worst", "B");
        worst.recordAttempt(false); // 0% 
        
        List<Card> cards = new ArrayList<>(Arrays.asList(best, worst));
        new WorstFirstSorter().organize(cards);
        
        assertEquals("Worst", cards.get(0).getQuestion(), "Амжилтын хувь хамгийн бага карт эхэнд байх ёстой.");
    }

    @Test
    public void testCLIParser() {
        CLIParser parser = new CLIParser();
        String[] args = {"cards.txt", "--order", "worst-first", "--repetitions", "3"};
        parser.parse(args);
        
        assertEquals("cards.txt", parser.filePath);
        assertEquals("worst-first", parser.order);
        assertEquals(3, parser.repetitions);
    }

    @Test
    public void testCardLogic() {
        Card card = new Card("Q", "A");
        card.recordAttempt(true);
        card.recordAttempt(false);
        
        assertEquals(2, card.getTotalAttempts());
        assertEquals(1, card.getCorrectCount());
        assertTrue(card.isLastAttemptFailed(), "Сүүлийн оролдлого буруу байх ёстой.");
    }
}