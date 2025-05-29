import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Content implements Comparable<Content> {
    private final String name;         // Mängija nimi
    private final int steps;           // Sammude arv
    private final String timestamp;    // Mängu lõpetamise aeg kujul AAAA-KK-PP HH:MM:SS
    private final long durationMillis;  //Mängu kestvus millisekundites

    public Content(String name, int steps, long durationMillis, String timestampParam) {
        this.name = name;
        this.steps = steps;
        this.durationMillis = durationMillis;

        if (timestampParam == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.timestamp = LocalDateTime.now().format(formatter);
        } else {
            this.timestamp = timestampParam;
        }
    }

    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    @Override
    public int compareTo(Content o) {
        int stepCompare = Integer.compare(this.steps, o.steps);
        if (stepCompare != 0) {
            return stepCompare;
        }

        // Sammude arv on võrdne – kontrollime kas nimi on sama
        if (this.name.equals(o.name)) {
            // Võrdleme timestampi – varasem eespool
            return this.timestamp.compareTo(o.timestamp);
        }

        // Kui nimed erinevad, jääb nende omavaheline järjestus määramatuks
        return 0;
    }

    public String formattedData() {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter estonianFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        String eestiKuupaev = LocalDateTime.parse(timestamp, inputFormat).format(estonianFormat);
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);
        String n = String.format("%-15s", displayName);
        String s = String.format("%3d", steps);
        String t = String.format("%6d", durationMillis); // kestus ms

        return eestiKuupaev + "  " + n + s + "  " + t + " ms";
    }
}
