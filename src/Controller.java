import java.util.List;

/**
 * Käivitab mängu.
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Kontrolleri konstruktor
     * @param model app failist
     * @param view  app failist
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Start mängu loogika
     */
    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu(); //Näita menüüd
            int choice = view.getMenuChoice(); // Küsi kasutajale menüü valikut 1,2 või 3
            switch (choice) {
                case 1:
                    model.initGame();   //Loo uus mäng
                    //view.showMessage(String.valueOf(model.getPc_number())); //Test
                    Stopwatch stopwatch = new Stopwatch();  //Loome stopperi
                    stopwatch.start();  //Käivitame stopperi
                    while (!model.isGame_over()) {  //Kui mäng ei ole läbi
                        int guess = view.askGuess(); //Küsi kasutajalt nr
                        view.showMessage(model.checkGuess(guess));  //Kontrolli ja väljasta tulemus
                    }
                    stopwatch.stop();   //Peata stopper
                    // Näita aega järgmnevalt: "Mängu aeg: 00:00:00 (000000)
                    view.showMessage("Mängu aeg " + stopwatch.getElapsedTime() + " ("+ stopwatch.getElapsedMillis() +")");
                    String name = view.askName();   //Küsi nime
                    model.saveScore(name);
                    break;
                case 2:
                    // Näita edetabelit
                    view.showScoreboard(model.loadScores());
                    break;
                case 3:
                    running = false;
                    view.showMessage("Head aega!");
                    break;
                default:
                    view.showMessage("Vigane valik");

            }
        }
    }
}
