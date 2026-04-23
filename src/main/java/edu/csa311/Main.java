package edu.csa311;

import java.util.List;


public class Main { 
    public static void main(String[] args) {
    
        
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        CLIParser parser = new CLIParser();//comand unshih
        parser.parse(args);
        if (parser.helpRequested) {
            parser.printHelp();//tuslamj 
            return;
        }
        if (parser.filePath == null) {
            System.out.println("Error: Please provide the path to the cards file.");
            parser.printHelp();
            return;
        }

        try {
            List<Card> cards = CardLoader.loadCards(parser.filePath);
            if (cards.isEmpty()) {
                System.out.println("Error: File not found or doesn't contain valid cards.");
                return;
            }
            FlashcardGame game = new FlashcardGame(cards, parser);
            game.start();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}