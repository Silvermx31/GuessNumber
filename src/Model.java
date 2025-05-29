import java.io.*;
import java.util.*;

/**
 * Kogu mängu loogika asub siin.
 */
public class Model {
    private final String filename = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>();

    private int pc_number;  // Arvuti mõeldud nr
    private int steps;  // käikude lugeja
    private boolean game_over;  //Mäng läbi

    /**
     * Uue mängu loomine
     */
    public void initGame() {
        int MINIMUM = 1;
        int MAXIMUM = 100;
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
    }

    //Getters

    /**
     * Arvuti mõeldud nr
     * @return juhuslik nr vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * Kas mäng on läbi
     * @return true on läbi, false ei ole läbi
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * Kontrollib kasutaja sisestust ja tagastab sobiva teksti
     * @param guess number mida kontrollida
     * @return tagastab teksti mida nädatakse kasutajale
     */
    public String checkGuess(int guess) {
        if (guess == 1000) {
            game_over = true;
            steps = 1000;  // Et teada, et see oli backdoor
            return "Tagauks avatud!";
        }

        steps++;    //Käikude arv kasvab
        if(guess == pc_number) {
            game_over = true;
            return "Sa võitsid!" + steps + "sammuga.";
        } else if(guess < pc_number) {
            return "Liiga väike";
        } else {
            return "Liiga suur";
        }
    }
    public int getSteps() {
        return steps;
    }


    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     * @param name  nimi
     */
    public void saveScore(String name, long durationMillis) {
        if (steps == 1000) {
            System.out.println("Backdoor, Tulemust ei salvestata.");
            return;
        }

        loadScores();
        scoreboard.add(new Content(name, steps, durationMillis, null)); // Lisa uus Content, mille sees on ka aeg
        Collections.sort(scoreboard);

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for(Content c : scoreboard) {
                // kirjuta ka aeg
                out.println(c.getTimestamp() + ";" + c.getName() + ";" + c.getSteps() + ";" + c.getDurationMillis());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Laeb edetabelis oleva listi ja lisab sisu listi
     * @return  list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); // Tühendab listi
        File file = new File(filename);
        if (!file.exists()) return scoreboard;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (parts.length == 4) {
                    // uus formaat: timestamp;name;steps;durationMillis
                    String timestamp = parts[0]; // timestampit ei kasuta otse, Content genereerib ise
                    String name = parts[1];
                    int steps = Integer.parseInt(parts[2]);
                    long duration = Long.parseLong(parts[3]);

                    scoreboard.add(new Content(name, steps, duration, timestamp));
                } else if (parts.length == 2) {
                    // vana formaat: name;steps
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);

                    scoreboard.add(new Content(name, steps, 0, null));  // kestus puudub
                } else {
                    System.out.println("Vigane rida failis: " + String.join(";", parts));
                }
            }
            Collections.sort(scoreboard); // Sorteeri skoorid
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}
