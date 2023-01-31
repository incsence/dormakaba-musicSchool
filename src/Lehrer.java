public class Lehrer {
    private int id;
    private String name;
    private String instrumentName;

    public Lehrer() {
    }

    public Lehrer(int id) {
        this.id = id;
    }

    public Lehrer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Lehrer(int id, String name, String instrumentName) {
        this.id = id;
        this.name = name;
        this.instrumentName = instrumentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
