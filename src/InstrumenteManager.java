import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstrumenteManager {
    private String input;
    private Connection connection;

    public void stringInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter String: ");
        try {
            input = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error reading input from keyboard.");
            ioe.printStackTrace();
        }
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void anfangsgruss(){
        System.out.println("\nHallo. Dies ist der Instrumente-Manager. Was wollen Sie tun?");
        System.out.println("1 - alle Instrumente anzeigen lassen.");
        System.out.println("2 - ein Instrument hinzufügen.");
        System.out.println("3 - ein Instrument löschen.");
        System.out.print("Wählen Sie bitte unter den angegebenen Ziffern. Zum Verlassen, geben Sie bitte q ein: ");
    }

    public void addinstrument(){
        System.out.print("Bitte geben Sie einen neuen Namen für ein Instrument ein: ");
        stringInput();
        String instrumentName = input;
        System.out.print("Wollen Sie wirklich " + input + " als Namen für ein Instrument hinzufügen? (j/n): ");
        stringInput();
        if (input.equals("j")) {
            statementInstrumentTable ("IF NOT EXISTS (SELECT * FROM instrument WHERE name = '" + instrumentName + "') BEGIN INSERT INTO instrument values ('"+ instrumentName +"') END");
            System.out.println(instrumentName + " wurde hinzugefügt.");
        } else {
            System.out.println("abgebrochen. Kein Instrument wurde hinzugefügt.");
        }
    }

    public void deleteInstrument(){
        System.out.print("Geben Sie den Namen des zu löschenden Instrumentes ein: ");
        stringInput();

        int manipuliert = statementInstrumentTable("DELETE FROM instrument WHERE name = '" + input + "';");
        if (manipuliert >= 1) {
            System.out.println(input + " gelöscht.");
        } else if (manipuliert == 0){
            System.out.println("Das Instrument " + input + " gibt es nicht.");
        }
    }

    public Boolean queryInstrumentTable(String query){
        // Das hier ist z. B. in LehrerManager besser geloest
        Boolean exists = false;
        try {
            java.sql.Statement stmt = connection.createStatement();

            java.sql.ResultSet rs = stmt.executeQuery(query);
            if(rs.isBeforeFirst()){
                exists = true;
                while (rs.next()) {
                    // Retrieve by column name
                    System.out.println(rs.getString("instrumentName"));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return exists;

    }

    public int statementInstrumentTable(String query){
        int einInteger = 0;
        try {
            java.sql.Statement stmt = connection.createStatement();
            einInteger = stmt.executeUpdate(query);
            //stmt.execute(query);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return einInteger;
    }

}
