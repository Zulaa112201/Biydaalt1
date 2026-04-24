package edu.csa311;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.csa311.organizer.RecentMistakesFirstSorter;
import edu.csa311.organizer.WorstFirstSorter;

public class AppTest {

    private Card mathCard;
    private Card historyCard;
    private List<Card> deck;

    @BeforeEach
    public void setUp() {
        mathCard = new Card("2+2=?", "4");
        historyCard = new Card("Монгол улс хэзээ тусгаар тогтносон бэ?", "1911");
        deck = new ArrayList<>();
    }

    @Test
    public void testRecentMistakesFirst() {
        historyCard.recordAttempt(false); 
        mathCard.recordAttempt(true);

        deck.addAll(Arrays.asList(mathCard, historyCard));
        
        new RecentMistakesFirstSorter().organize(deck);
        
        assertEquals(historyCard, deck.get(0), "Хамгийн сүүлд алдсан карт жагсаалтын эхэнд байх ёстой.");
    }

    @Test
    public void testWorstFirst() {
        mathCard.recordAttempt(true); // 100% амжилт
        
        historyCard.recordAttempt(false); // 0% амжилт
        historyCard.recordAttempt(false);
        
        deck.addAll(Arrays.asList(mathCard, historyCard));
        
        new WorstFirstSorter().organize(deck);
        
        assertEquals("Монгол улс хэзээ тусгаар тогтносон бэ?", deck.get(0).getQuestion());
        assertTrue(deck.get(0).getSuccessRate() < deck.get(1).getSuccessRate());
    }

    @Test
    public void testCLIParser() {
        CLIParser parser = new CLIParser();
        String[] mockArgs = {"data.csv", "--order", "recent-mistakes", "--repetitions", "5"};
        
        parser.parse(mockArgs);
        
        assertAll("CLI Parser-ийн утгуудыг шалгах",
            () -> assertEquals("data.csv", parser.filePath),
            () -> assertEquals("recent-mistakes", parser.order),
            () -> assertEquals(5, parser.repetitions)
        );
    }

    @Test
    public void testCardLogic() {
        Card card = new Card("Java гэж юу вэ?", "Програмчлалын хэл");
        
        card.recordAttempt(false);
        card.recordAttempt(true);
        card.recordAttempt(false);
        
        assertEquals(3, card.getTotalAttempts());
        assertEquals(1, card.getCorrectCount());
        assertTrue(card.isLastAttemptFailed());
    }
}
