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

    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     * @param name  nimi
     */
    public void saveScore(String name) {
        loadScores();
        scoreboard.add(new Content(name, steps));   //Lisa nimi ja sammude arv listi
        Collections.sort(scoreboard);   //Sorteerib listi (CompareTo() omaga)
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for(Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps());
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
        scoreboard.clear(); //Tühendab listi
        File file = new File(filename);
        if(!file.exists()) return scoreboard; //Kui faili ei ole, tagastab listi

        try(Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {   //Kui failis on järgmine rida
                String[] parts = sc.nextLine().split(";");
                if(parts.length == 2) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps));
                }
            }
            Collections.sort(scoreboard);   //Sorteerib isti
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}
