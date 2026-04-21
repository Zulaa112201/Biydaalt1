package edu.csa311;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.csa311.organizer.RecentMistakesFirstSorter;

import java.util.Arrays;
import java.util.List;

public class AppTest {
    @Test
    public void testRecentMistakesFirst() {
        Card c1 = new Card("Q1", "A1"); // Зөв
        Card c2 = new Card("Q2", "A2"); // Буруу
        c2.recordAttempt(false);
        
        List<Card> cards = Arrays.asList(c1, c2);
        new RecentMistakesFirstSorter().organize(cards);
        
        assertEquals("Q2", cards.get(0).getQuestion());
    }
}