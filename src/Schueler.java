public class Schueler {
    private int id;
    private String name;
    private Integer geburtsjahr;


    public Schueler(int id, String name, Integer geburtsjahr)
    {
        this.id=id;
        this.name=name;
        this.geburtsjahr=geburtsjahr;
    }

    public Schueler(int id, String name)
    {
        this.id=id;
        this.name=name;
    }

    public Schueler(String name)
    {
        this.name = name;
    }

    public  Schueler()
    {

    }

    //Methoden
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

    public Integer getGeburtsjahr() {
        return geburtsjahr;
    }

    public void setGeburtsjahr(Integer geburtsjahr) {
        this.geburtsjahr = geburtsjahr;
    }



}
