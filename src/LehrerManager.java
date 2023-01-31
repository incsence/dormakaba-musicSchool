
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class LehrerManager {
    private String stringInput;
    private Connection connection;

    public void setStringInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            stringInput = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error reading input from keyboard.");
            ioe.printStackTrace();
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void doAnfangsgruss(){
        System.out.println("");
        System.out.println("Hallo. Dies ist der Lehrer-Manager. Was wollen Sie tun?");
        System.out.println("1 - alle LehreR anzeigen lassen.");
        System.out.println("2 - einen LehreR hinzufügen.");
        System.out.println("3 - einem LehreR ein Instrument zuordnen.");
        System.out.println("4 - einen LehreR löschen.");
        System.out.print("Wählen Sie bitte unter den angegebenen Ziffern. Zum Verlassen, geben Sie bitte q ein: ");
    }

    public List<String> listAll(){
        // erzeuge struktur zum sammeln der ergebnisdaten
        List<String> results  = new ArrayList<>();
        try {
            //String str = "SELECT lehrerName FROM lehrer;";
            String str = "SELECT l.lehrerID, l.lehrerName, i.instrumentName FROM" +
                    " ((lehrer AS l FULL JOIN lehrer_x_instrument AS lxi ON l.lehrerID=lxi.lehrerID) " +
                    " FULL JOIN instrument AS i ON lxi.instrumentID = i.InstrumentID);";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                // Retrieve by column name
                //füge ergebnis in liste ein
                results.add(rs.getString("lehrerName") + ", " + rs.getString("instrumentName"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // gebe ergebnisliste zurück
        return results;
    }

    public Integer queryLehrerID(String name){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT lehrerID FROM lehrer WHERE lehrerName = '" + name + "';");
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("lehrerID");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return returnInteger;
    }
    public Integer queryInstrumentID(String name){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT instrumentID FROM instrument WHERE instrumentName = '" + name + "';");
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("instrumentID");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return returnInteger;
    }


    public void addLehrer() {
        System.out.print("Bitte geben Sie einen neuen Namen für einEn LehreR ein: ");
        setStringInput();
        String lehrerName = stringInput;
        System.out.print("Wollen Sie wirklich " + lehrerName + " als Lehrernamen hinzufügen? (j/n): ");
        setStringInput();
        if (stringInput.equals("j")) {
            statementToTable("IF NOT EXISTS (SELECT * FROM lehrer WHERE lehrerName = '(" + lehrerName + "')"
                    + " BEGIN INSERT INTO lehrer (lehrerName) VALUES ('" + lehrerName + "') END;");
            System.out.println(lehrerName + " wurde hinzugefügt.");
        } else {
            System.out.println("abgebrochen. Kein Lehrer wurde hinzugefügt.");
        }
    }

    public void connectLehrerToInstrument() {

        System.out.print("Bitte geben Sie einen Namen für den LehreR ein, den Sie verbinden möchten: ");
        setStringInput();
        String lehrerName = stringInput;
        Integer lehrerID = queryLehrerID(lehrerName);
        if (lehrerID != null) {

            Lehrer lehrer1 = new Lehrer();
            lehrer1.setName(lehrerName);
            System.out.println("Wählen Sie ein Instrument :");
            setStringInput();
            System.out.println("Wollen Sie " + stringInput + " als Instrument von " + lehrer1.getName() + " hinzufügen? (j/n): ");
            String lehrerInstrument = stringInput;
            setStringInput();
            if (stringInput.equals("j")) {
                lehrer1.setInstrumentName(lehrerInstrument);

                Integer instrumentID = queryInstrumentID(lehrer1.getInstrumentName());
                if(instrumentID != null) {
                    //statementToTable("IF NOT EXISTS (SELECT * FROM instrument WHERE instrumentName = '(" + lehrer1.getInstrumentName() + "')" +
                    //        "BEGIN INSERT INTO instrument VALUES ('" + lehrer1.getInstrumentName() + "') END;");
                    //statementToTable("INSERT INTO instrument VALUES ('" + lehrer1.getInstrumentName() + "');");


                    statementToTable("INSERT INTO lehrer_x_instrument (lehrerID, instrumentID) VALUES (" + lehrerID + ", " + instrumentID + ");");
                    System.out.println("LehrerID: " + lehrerID + ", Instrument: " + instrumentID);
                    System.out.println("Der Lehrer " + lehrer1.getName() + " ist jetzt mit dem Instrument " + lehrer1.getInstrumentName() + " verbunden.");
                } else {
                    System.out.println("Dieses Instrument gibt es nicht. abgebrochen.");
                };

            } else {
                System.out.println("abgebrochen. Kein Instrument verbunden.");
            }

        } else {
            System.out.println("Diesen LehreR gibt es nicht. abgebrochen. ");
        }



    }

    public void deleteLehrer(){
        System.out.print("Geben Sie den Namen des zu löschenden Lehrers ein: ");
        setStringInput();
        Integer lehrerID = queryLehrerID(stringInput);
        statementToTable("DELETE FROM lehrer_x_instrument WHERE lehrerID = " + lehrerID + ";");
        int manipuliert = statementToTable("DELETE FROM lehrer WHERE lehrerName = '" + stringInput + "';");
        if (manipuliert >= 1) {
            System.out.println(stringInput + " gelöscht.");
        } else if (manipuliert == 0){
            System.out.println("Den LehreR " + stringInput + " gibt es nicht.");
        }
    }

    public int statementToTable(String query){
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

