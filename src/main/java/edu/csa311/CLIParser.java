package edu.csa311;


public class CLIParser {
    public String filePath = null;
    public String order = "random";
    public int repetitions = 1;
    public boolean invertCards = false;
    public boolean helpRequested = false;

    public void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help")) {
                helpRequested = true;
                return;
            } else if (args[i].equals("--order") && i + 1 < args.length) {
                order = args[++i];
            } else if (args[i].equals("--repetitions") && i + 1 < args.length) {
                repetitions = Integer.parseInt(args[++i]);
            } else if (args[i].equals("--invertCards")) {
                invertCards = true;
            } else if (!args[i].startsWith("--") && filePath == null) {
                filePath = args[i];
            }
        }
    }

    public void printHelp() {
        System.out.println("Flashcard System");
        System.out.println("Usage: java -cp target/classes mn.edu.num.csa311.Main <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("  --help                Show help information");
        System.out.println("  --order <order>       Set card order [random, worst-first, recent-mistakes-first]");
        System.out.println("  --repetitions <num>   Set how many times a card must be answered correctly");
        System.out.println("  --invertCards         Swap questions and answers");
    }
}