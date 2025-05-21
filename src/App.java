/**
 * Põhifail rakenduse käivitamiseks
 */
public class App {
    /**
     * Sellega käivitatakse rakendus
     * @param args käsurea argumendid
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.start(); //Käivita mäng
    }
}
