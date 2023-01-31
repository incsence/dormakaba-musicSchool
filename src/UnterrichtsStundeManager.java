import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;


public class UnterrichtsStundeManager {
    private String stringInput;
    private Integer intInput;
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
    public void setIntInput() {
        intInput = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = br.readLine();
            if(str.isEmpty()) {
                System.out.println("Sie haben nichts eingegeben.");
                return;
            }
            intInput = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            nfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Error reading input from keyboard.");
            ioe.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void doAnfangsgruss(){
        System.out.println("\nHallo. Dies ist der UnterrichtsStunde-Manager. Was wollen Sie tun?");
        System.out.println("1 - Gesamten Stundenplan anzeigen");
        System.out.println("2 - Stundenplan eines LehreRs anzeigen");
        System.out.println("3 - Stundenplan eines SchüleRs anzeigen");
        System.out.println("4 - Raum-Zeitplan anzeigen");
        System.out.println("5 - eine regelmäßige Unterrichtsstunde hinzufügen.");
        System.out.println("6 - eine Unterrichtsstunde löschen.");
        System.out.print("Wählen Sie bitte unter den angegebenen Ziffern. Zum Verlassen, geben Sie q ein: ");
    }


public List<String> listAll(){
        // Diese Methode gibt eine Liste zurück. Die anderen drucken alle den Wert direkt aus. Sollte das übernommen werden?
        List<String> results  = new ArrayList<>();
        try {
            String str = "SELECT uStunde.id, sch.name, i.instrumentName, l.lehrerName, t.tageName, s.anfang, s.ende, r.raumName FROM " +
                    "((((((unterrichtsStunde AS uStunde JOIN schueler AS sch ON uStunde.schuelerID = sch.id)" +
                    "JOIN instrument AS i ON uStunde.instrumentID = i.InstrumentID)" +
                    "JOIN lehrer AS l ON uStunde.lehrerID = l.lehrerID)" +
                    "JOIN tag AS t ON uStunde.wochentagID = t.tageID)" +
                    "JOIN stunde AS s ON uStunde.stundeID = s.stundenID)" +
                    "JOIN raum AS r ON uStunde.raumID = r.raumID)";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                // Retrieve by column name
                // System.out.println(rs.getString("instrumentName"));
                // Füge Ergebnis in liste ein
                results.add(
                    rs.getString("id") + ", " +
                    rs.getString("name") + ", " +
                    rs.getString("instrumentName") + ", " +
                    rs.getString("lehrerName") + ", " +
                    rs.getString("tageName") + ", " +
                    rs.getString("anfang") + ", " +
                    rs.getString("ende") + ", " +
                    rs.getString("raumName")
                );
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return results;
    }
    public void showWholeTimeTable(){
        try {
            String str = "select t.tageName, s.anfang, s.ende, sch.name, l.lehrerName, i.instrumentName, r.raumName \n" +
                    "from tag t\n" +
                    "full join stunde s on 1=1\n" +
                    "left join unterrichtsStunde u on u.stundeID=s.stundenID and u.wochentagID=t.tageID \n" +
                    "left join schueler sch on u.schuelerID = sch.id\n" +
                    "left join lehrer l on u.lehrerID = l.lehrerID\n" +
                    "left join instrument i on u.instrumentID = i.InstrumentID\n" +
                    "left join raum r on u.raumID = r.raumID\n" +
                    "order by  t.tageID, s.anfang ";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            System.out.println("Wochentag      Std.-Beginn    Std.-Ende      SchüleR        LehreR         Instrument     Raum");
            System.out.println("----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s" , rs.getString("tageName"));
                System.out.printf("%-15.8s", rs.getString("anfang"));
                System.out.printf("%-15.8s", rs.getString("ende"));
                System.out.printf("%-15s" , rs.getString("name"));
                System.out.printf("%-15s" , rs.getString("lehrerName"));
                System.out.printf("%-15s" , rs.getString("instrumentName"));
                System.out.printf("%-15s%n", rs.getString("raumName"));

            }
        } catch(SQLException sqlException){sqlException.printStackTrace();}
    }
    public void showLehrerTimetable(){
        System.out.print("Bitte wählen Sie eine LehrerID: ");
        setIntInput();
        try {
            String str = "select  t.tageName, s.anfang, s.ende, sch.name, l.lehrerName, i.instrumentName, r.raumName \n" +
                    "from tag t\n" +
                    "full join stunde s on 1=1 \n" +
                    "left join unterrichtsStunde u on u.stundeID=s.stundenID and u.wochentagID=t.tageID and u.lehrerID = " + intInput + " " +
                    "left join schueler sch on u.schuelerID = sch.id\n" +
                    "left join lehrer l on l.lehrerID = " + intInput + " " +
                    "left join instrument i on u.instrumentID = i.InstrumentID\n" +
                    "left join raum r on u.raumID = r.raumID\n" +
                    "order by t.tageID, s.anfang";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            System.out.println("Wochentag      Std.-Beginn    Std.-Ende      SchüleR        LehreR         Instrument     Raum");
            System.out.println("----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s" , rs.getString("tageName"));
                System.out.printf("%-15.8s", rs.getString("anfang"));
                System.out.printf("%-15.8s", rs.getString("ende"));
                System.out.printf("%-15s" , rs.getString("name"));
                System.out.printf("%-15s" , rs.getString("lehrerName"));
                System.out.printf("%-15s" , rs.getString("instrumentName"));
                System.out.printf("%-15s%n", rs.getString("raumName"));

            }
        } catch(SQLException sqlException){sqlException.printStackTrace();}
    }
    public void showSchuelerTimetable(){
        System.out.println("Waehlen Sie eine SchuelerID: ");
        setIntInput();
        try {
            String str = "select t.tageName, s.anfang, s.ende, sch.name, l.lehrerName, i.instrumentName, r.raumName \n" +
                    "from tag t\n" +
                    "full join stunde s on 1=1\n" +
                    "left join unterrichtsStunde u on (u.stundeID=s.stundenID and u.wochentagID=t.tageID)\n" +
                    "left join schueler sch on u.schuelerID = sch.id\n" +
                    "left join lehrer l on u.lehrerID = l.lehrerID\n" +
                    "left join instrument i on u.instrumentID = i.InstrumentID\n" +
                    "left join raum r on u.raumID = r.raumID\n" +
                    "where schuelerID = " + intInput + " " +
                    "order by t.tageID, s.anfang";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            System.out.println("Wochentag      Std.-Beginn    Std.-Ende      SchüleR        LehreR         Instrument     Raum");
            System.out.println("----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s" , rs.getString("tageName"));
                System.out.printf("%-15.8s", rs.getString("anfang"));
                System.out.printf("%-15.8s", rs.getString("ende"));
                System.out.printf("%-15s" , rs.getString("name"));
                System.out.printf("%-15s" , rs.getString("lehrerName"));
                System.out.printf("%-15s" , rs.getString("instrumentName"));
                System.out.printf("%-15s%n", rs.getString("raumName"));

            }
        } catch(SQLException sqlException){sqlException.printStackTrace();}
    }
    public void showRaumTimetable(){
        System.out.println("Waehlen Sie eine RaumID: ");
        setIntInput();
        try {
            String str = "select t.tageName, s.anfang, s.ende, sch.name, l.lehrerName, i.instrumentName, r.raumName \n" +
                    "from tag t\n" +
                    "full join stunde s on 1=1\n" +
                    "left join unterrichtsStunde u on u.stundeID=s.stundenID and u.wochentagID=t.tageID and u.raumID = " + intInput  + " " +
                    "left join schueler sch on u.schuelerID = sch.id\n" +
                    "left join lehrer l on u.lehrerID = l.lehrerID\n" +
                    "left join instrument i on u.instrumentID = i.InstrumentID\n" +
                    "left join raum r on u.raumID = r.raumID\n" +
                    "order by t.tageID, s.anfang";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(str);
            System.out.println("Wochentag      Std.-Beginn    Std.-Ende      SchüleR        LehreR         Instrument     Raum");
            System.out.println("----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s" , rs.getString("tageName"));
                System.out.printf("%-15.8s", rs.getString("anfang"));
                System.out.printf("%-15.8s", rs.getString("ende"));
                System.out.printf("%-15s" , rs.getString("name"));
                System.out.printf("%-15s" , rs.getString("lehrerName"));
                System.out.printf("%-15s" , rs.getString("instrumentName"));
                System.out.printf("%-15s%n", rs.getString("raumName"));

            }
        } catch(SQLException sqlException){sqlException.printStackTrace();}
    }

    public Integer querySchuelerID(String name){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT id FROM schueler WHERE name = '" + name + "';");
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("id");
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
    public Integer queryTageID(String name){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT tageID FROM tag WHERE tageName = '" + name + "';");
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("tageID");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return returnInteger;
    }
    public Integer queryIDFromLehrer_x_Instrument(int lehrerID, int instrumentID){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(
                    "SELECT id FROM lehrer_x_instrument WHERE lehrerID = " + lehrerID + " AND instrumentID = " + instrumentID + ";"
            );
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("id");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return returnInteger;
    }
    public Integer queryUnterrichtsStundeID(String spaltenName, int spaltenID, int wochentagID, int stundeID){
        Integer returnInteger = null;
        try {

            java.sql.Statement stmt = connection.createStatement();
            //SELECT id FROM unterrichtsStunde WHERE schuelerID = 4 and stundeID = 1 and wochentagID = 5
            java.sql.ResultSet rs = stmt.executeQuery(
                    "SELECT id FROM unterrichtsStunde WHERE " + spaltenName + " = " + spaltenID + " AND stundeID = " + stundeID + " and wochentagID = " + wochentagID + ";"
            );
            while (rs.next()) {
                // Retrieve by column name
                returnInteger = rs.getInt("id");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return returnInteger;
    }
    public void printSchuelerNames(){
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT name FROM schueler;");
            while (rs.next()) {
                // Retrieve by column name
                System.out.println(rs.getString("name"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public void printInstrumentNames(){
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT instrumentName FROM instrument;");
            while (rs.next()) {
                // Retrieve by column name
                System.out.println(rs.getString("instrumentName"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public void printLehrerNames(){
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT lehrerName FROM lehrer;");
            while (rs.next()) {
                // Retrieve by column name
                System.out.println(rs.getString("lehrerName"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }



    public void addUnterrichtsStunde() {
        // Schueler
        printSchuelerNames();
        System.out.print("Bitte geben Sie den Namen des SchüleRs ein, für den Sie eine Unterrichtsstunde erstellen möchten: ");
        setStringInput();
        Integer id = querySchuelerID(stringInput);
            if(id != null) {
                UnterrichtsStunde unterrichtsStunde1 = new UnterrichtsStunde();
                unterrichtsStunde1.setSchuelerID(id);

                // Instrument
                printInstrumentNames();
                System.out.println("Bitte geben Sie den Namen des Instrumentes ein, in dem der SchüleR unterrichtet werden soll: ");
                setStringInput();
                id = queryInstrumentID(stringInput);
                if(id != null) {
                    unterrichtsStunde1.setInstrumentID(id);

                    //Lehrer
                    printLehrerNames();
                    System.out.println("Bitte geben Sie den Namen des LehreRs ein, bei dem der SchüleR unterrichtet werden soll: ");
                    setStringInput();
                    id = queryLehrerID(stringInput);
                    if(id != null) {
                        unterrichtsStunde1.setLehrerID(id);

                        // Wochentag
                        System.out.println("Bitte geben Sie einen Wochentag (Montag-Freitag) ein: ");
                        setStringInput();
                        id = queryTageID(stringInput);
                        if(id != null) {
                            unterrichtsStunde1.setWochentagID(id);

                            // Stunde
                            System.out.println("Bitte wählen Sie eine Stunde (1 - 6), in der unterrichtet werden soll: ");
                            setIntInput();
                            if( 1 <= intInput && intInput <= 6  ) {
                                unterrichtsStunde1.setStundeID(intInput);

                                //Raum
                                System.out.println("Bitte wählen Sie einen Raum (1 - 2), in dem unterrichtet werden soll: ");
                                setIntInput();
                                if( 1 <= intInput && intInput <= 2  ) {
                                    unterrichtsStunde1.setRaumID(intInput);

                                    unterrichtsStunde1.printAllExceptID();
                                    //Tests
                                    //Passen Lehrer und Instrument überhaupt zusammen?
                                    id = queryIDFromLehrer_x_Instrument(unterrichtsStunde1.getLehrerID(), unterrichtsStunde1.getInstrumentID() );
                                    if( id != null ){
                                        //Die Verbindung Lehrer_X_Instrument gibt es.

                                        //Hat der Schüler zu dem Zeitpunkt noch frei?
                                        id = queryUnterrichtsStundeID("schuelerID", unterrichtsStunde1.getSchuelerID(), unterrichtsStunde1.getWochentagID(), unterrichtsStunde1.getStundeID() );
                                        if( id == null) {
                                            //Der Schüler hat noch frei. Diese Stunde gibt es noch nicht.

                                            //Hat der Lehrer zu dem Zeitpunkt noch frei?
                                            id = queryUnterrichtsStundeID("lehrerID", unterrichtsStunde1.getLehrerID(), unterrichtsStunde1.getWochentagID(), unterrichtsStunde1.getStundeID() );
                                            if ( id == null) {
                                                //Der Lehrer hat noch frei. Diese Stunde gibt es noch nicht.

                                                //Ist der Raum zu der Zeit noch frei?
                                                id = queryUnterrichtsStundeID("raumID", unterrichtsStunde1.getRaumID(), unterrichtsStunde1.getWochentagID(), unterrichtsStunde1.getStundeID() );
                                                if (id == null) {
                                                    //Der Raum ist frei.
                                                    System.out.println("Keine Terminkonflikte. Füge den Termin hinzu.");
                                                    statementToTable("INSERT INTO unterrichtsStunde (schuelerID, instrumentID, lehrerID, wochentagID, stundeID, raumID) VALUES ( " +
                                                            unterrichtsStunde1.getSchuelerID() + "," +
                                                            unterrichtsStunde1.getInstrumentID() + "," +
                                                            unterrichtsStunde1.getLehrerID() + "," +
                                                            unterrichtsStunde1.getWochentagID() + "," +
                                                            unterrichtsStunde1.getStundeID() + "," +
                                                            unterrichtsStunde1.getRaumID() + ");"
                                                    );
                                                } else {
                                                    System.out.println("Leider gibt es schon eine Stunde für diesen Raum zu diesem Zeitpunkt. nichts hinzugefügt.");
                                                }

                                            } else {
                                                System.out.println("Leider gibt es schon eine Stunde für den Lehrer zu diesem Zeitpunkt. nichts hinzugefügt.");
                                            }

                                        } else {
                                            System.out.println("Leider gibt es schon eine Stunde für den Schüler zu diesem Zeitpunkt. nichts hinzugefügt.");
                                        }

                                    } else {
                                        System.out.println("Leider gibt es die Verbindung zwischen Lehrer und Instrument noch nicht. nichts hinzugefügt.");
                                    }

                                } else {
                                    System.out.println("Sie haben einen Raum ausserhalb von 1-2 gewählt. abgebrochen.");
                                }
                            } else {
                                System.out.println("Sie haben eine Stunde ausserhalb von 1-6 gewählt. abgebrochen.");
                            }
                        } else {
                            System.out.println("Diesen Wochentag gibt es leider nicht. abgebrochen.");
                        }
                    } else {
                        System.out.println("Diesen Lehrer gibt es leider noch nicht. abgebrochen.");
                    }

                } else {
                    System.out.println("Dieses Instrument gibt es leider noch nicht. abgebrochen.");
                }
            } else {
                System.out.println("Diesen Schüler gibt es leider noch nicht. abgebrochen.");
            }
    }

    public void deleteUnterrichtsStunde(){
        List<String> results = listAll();
        for(String resultString : results)
        {
            System.out.println(resultString);
        }
        System.out.print("Geben Sie die ID der zu löschenden UnterrichtsStunde ein: ");
        setIntInput();
        int manipuliert = statementToTable("DELETE FROM unterrichtsStunde WHERE id = " + intInput + ";");
        if (manipuliert >= 1) {
            System.out.println(intInput + " gelöscht.");
        } else if (manipuliert == 0){
            System.out.println("Die ID gibt es nicht. Nichts gelöscht.");
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
