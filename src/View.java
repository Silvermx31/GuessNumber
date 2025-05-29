import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb konsoolis.
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Mängu menüü näitamine
     */
    public void showMenu() {
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee valik: ");
    }

    /**
     * Tagastab kasutaja sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Väljastab teate konsooli
     * @param message teade mida väljastada
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Küsib kasutajalt numbrit
     * @return kasutaja sisestatud nr
     */
    public int askGuess() {
        int number;
        while (true) {
            System.out.print("Sisesta number: ");
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Viga! Palun sisesta reaalne number.");
            }
        }
        return number;
    }

    /**
     * Küsib kasutaja nime mängu lõpus
     * @return nimi
     */
    public String askName() {
        System.out.print("Sisesta nimi: ");
        return scanner.nextLine();
    }

    /**
     * Näitab konsooli edetabelit
     * @param scores    faili sisu
     */
    public void showScoreboard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println("------------------");
        for (Content c : scores) {
            System.out.println(c.formattedData());
        }
        System.out.println();   //Tühi rida
    }
}
