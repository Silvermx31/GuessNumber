/**
 * Klass mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;    //Mängija nimi
    private final int steps;  // sammude arv

    /**
     * Objekti loomise konstruktor
     * @param name      mängija nimi
     * @param steps     sammude arv
     */
    public Content(String name, int steps) {
        this.name = name;
        this.steps = steps;
    }

    /**
     * Tagastab mängija nime failist
     * @return nimi
     */
    public String getName() {
        return name;
    }

    /**
     * Tagastab mängija sammud
     * @return  sammud
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Sorteerime sammuda järgi kohanevalt
     * @param o objekt mida võrrelda.
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        //return Integer.compare(o.steps, steps); //Kahanevalt
        return  Integer.compare(steps, o.steps);    //Kasvavalt on .sort() .reversed()
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);      //15 ehk esimesed 15 märki
        String n = String.format("%-15s", displayName); //s ehk teksti vormindamine
        String s = String.format("%3d", steps); //d täisarvu vormindamine
        return n + s;
    }
}
