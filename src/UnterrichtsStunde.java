


public class UnterrichtsStunde {
    private int id;
    private int schuelerID;
    private int instrumentID;
    private int lehrerID;
    private int wochentagID;
    private int stundeID;
    private int raumID;


    public UnterrichtsStunde() {
    }

    public UnterrichtsStunde(int id) {
        this.id = id;
    }

    public UnterrichtsStunde(int id, int schuelerID, int instrumentID, int lehrerID, int wochentagID, int stundeID, int raumID) {
        this.id = id;
        this.schuelerID = schuelerID;
        this.instrumentID = instrumentID;
        this.lehrerID = lehrerID;
        this.wochentagID = wochentagID;
        this.stundeID = stundeID;
        this.raumID = raumID;
    }

    public int getId() {
        return id;
    }

    public int getSchuelerID() {
        return schuelerID;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public int getLehrerID() {
        return lehrerID;
    }

    public int getWochentagID() {
        return wochentagID;
    }

    public int getStundeID() {
        return stundeID;
    }

    public int getRaumID() {
        return raumID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchuelerID(int schuelerID) {
        this.schuelerID = schuelerID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public void setLehrerID(int lehrerID) {
        this.lehrerID = lehrerID;
    }

    public void setWochentagID(int wochentagID) {
        this.wochentagID = wochentagID;
    }

    public void setStundeID(int stundeID) {
        this.stundeID = stundeID;
    }

    public void setRaumID(int raumID) {
        this.raumID = raumID;
    }
    public void printAllExceptID() {
        System.out.println( "" + this.schuelerID + " " + this.instrumentID + " " +  this.lehrerID +  " " + this.wochentagID +  " " + this.stundeID +  " " + this.raumID );
    }
}
