
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Main {
    Connection connection;
    InstrumenteManager instrumenteManager;
    SchuelerManager schuelerManager;
    LehrerManager lehrerManager;
    UnterrichtsStundeManager unterrichtsStundeManager;

    String stringInput;
    //Integer intInput;

    public Main(Connection connection, InstrumenteManager instrumenteManager, SchuelerManager schuelerManager, LehrerManager lehrerManager, UnterrichtsStundeManager unterrichtsStundeManager){
        this.connection = connection;
        this.instrumenteManager = instrumenteManager;
        this.schuelerManager = schuelerManager;
        this.lehrerManager = lehrerManager;
        this.unterrichtsStundeManager = unterrichtsStundeManager;

        instrumenteManager.setConnection(connection);
        schuelerManager.setConnection(connection);
        lehrerManager.setConnection(connection);
        unterrichtsStundeManager.setConnection(connection);
    }

    private static Connection connectToDatabase(String db_name){

        String DB_URL = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=" +db_name+ ";";
        Connection connection = null;
        try {//databaseName=testDatenbank

            connection = DriverManager.getConnection(DB_URL, "java", "java");
            System.out.println("Connection successful");
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return connection;
    }

    public void setStringInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter String: ");
        try {
            stringInput = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error reading input from keyboard.");
            ioe.printStackTrace();
        }
    }

    public void doAnfangsgruss(){
        System.out.println("\nHallo. Dies ist die Verwaltung der DormaKaba MusicSchool. Was wollen Sie tun?");
        System.out.println("1 - Instrumentmanager (Instrumente anzeigen, anlegen und löschen)");
        System.out.println("2 - Schülermanager (SchüleR anzeigen, anlegen und löschen)");
        System.out.println("3 - Lehrermanager (LehreR anzeigen, anlegen, mit Instrument verbinden und löschen)");
        System.out.println("4 - UnterrichtsstundeManager (Stundenpläne anzeigen lassen. Stunden anlegen und löschen.)");
        System.out.print("Wählen Sie bitte unter den angegebenen Ziffern. Zum Verlassen, geben Sie q ein: ");
    }
    public void doInstrumentManagerEventLoop(InstrumenteManager instrumenteManager) {
        boolean go = true;
        while (go) {
            instrumenteManager.anfangsgruss();
            setStringInput();
            switch (stringInput) {
                case "1" -> instrumenteManager.queryInstrumentTable("SELECT instrumentName FROM instrument");
                case "2" -> instrumenteManager.addinstrument();
                case "3" -> instrumenteManager.deleteInstrument();
                case "q" -> go = false;
                default -> System.out.println("Ihre Eingabe war ungültig.");
            }
        }
    }

    public void doSchuelerManagerEventLoop(SchuelerManager schuelerManager) {
        boolean go = true;
        while (go) {
            schuelerManager.doAnfangsgruss();
            setStringInput();
            switch (stringInput) {
                case "1" -> schuelerManager.listAll();


                /*
                List<String> instruments = ...;
                for(String instrument : instruments)
                {
                    System.out.println(instrument);
                }*/
                case "2" -> schuelerManager.addStudent();
                case "3" -> schuelerManager.deleteSchueler();
                case "q" -> go = false;
                default -> System.out.println("Ihre Eingabe war ungültig.");
            }
        }
    }
    public void doLehrerManagerEventLoop (LehrerManager lehrerManager) {
        boolean go = true;
        while (go) {
            lehrerManager.doAnfangsgruss();
            setStringInput();
            switch (stringInput) {
                case "1" -> {
                    List<String> results = lehrerManager.listAll();
                    for (String resultString : results) {
                        System.out.println(resultString);
                    }
                }
                case "2" -> lehrerManager.addLehrer();
                case "3" -> lehrerManager.connectLehrerToInstrument();
                case "4" -> lehrerManager.deleteLehrer();
                case "q" -> go = false;
                default -> System.out.println("Ihre Eingabe war ungültig.");
            }
        }
    }
    public void doUnterrichtsStundeManagerEventLoop (UnterrichtsStundeManager unterrichtsStundeManager) {
        boolean go = true;
        while (go) {
            unterrichtsStundeManager.doAnfangsgruss();
            setStringInput();
            switch (stringInput) {
                /*case "" -> {
                    List<String> results = unterrichtsStundeManager.listAll();
                    for (String resultString : results) {
                        System.out.println(resultString);
                    }
                }*/
                case "1" -> unterrichtsStundeManager.showWholeTimeTable();
                case "2" -> unterrichtsStundeManager.showLehrerTimetable();
                case "3" -> unterrichtsStundeManager.showSchuelerTimetable();
                case "4" -> unterrichtsStundeManager.showRaumTimetable();
                case "5" -> unterrichtsStundeManager.addUnterrichtsStunde();
                case "6" -> unterrichtsStundeManager.deleteUnterrichtsStunde();
                case "q" -> go = false;
                default -> System.out.println("Ihre Eingabe war ungültig.");
            }
        }
    }
    public void doMainLoop (Main main) {
        boolean go = true;
        while (go) {
            main.doAnfangsgruss();
            main.setStringInput();
            switch(main.stringInput) {
                case "1" -> main.doInstrumentManagerEventLoop(main.instrumenteManager);
                case "2" -> main.doSchuelerManagerEventLoop(main.schuelerManager);
                case "3" -> main.doLehrerManagerEventLoop(main.lehrerManager);
                case "4" -> main.doUnterrichtsStundeManagerEventLoop(main.unterrichtsStundeManager);
                case "q" -> go = false;
                default -> System.out.println("Ihre Eingabe war ungültig.");
            }
        }
    }

    public static void main(String[] args){
        Connection connection = connectToDatabase("musicSchool");
        InstrumenteManager instrumenteManager = new InstrumenteManager();
        SchuelerManager schuelerManager = new SchuelerManager();
        LehrerManager lehrerManager = new LehrerManager();
        UnterrichtsStundeManager unterrichtsStundeManager = new UnterrichtsStundeManager();

        Main main = new Main(connection, instrumenteManager, schuelerManager, lehrerManager, unterrichtsStundeManager);
        main.doMainLoop(main);




    }
}
