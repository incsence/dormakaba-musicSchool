import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.SQLException;




public class SchuelerManager {
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
        System.out.println("");
        System.out.println("Hallo. Dies ist der Schüler-Manager. Was wollen Sie tun?");
        System.out.println("1 - alle SchüleR anzeigen lassen.");
        System.out.println("2 - einen SchüleR hinzufügen.");
        System.out.println("3 - einen SchüleR löschen.");
        System.out.print("Wählen Sie bitte unter den angegebenen Ziffern. Zum Verlassen, geben Sie bitte q ein: ");
    }
    public void listAll(){
        // erzeuge struktur zum sammeln der ergebnisdaten
        //List<String> result = new ArrayList<>();
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT name, geburtsjahr FROM schueler");
            while (rs.next()) {
                // Retrieve by column name
                System.out.print(rs.getString("name") + " *");
                System.out.println(rs.getString("geburtsjahr"));

            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // gebe ergebnisliste zurück
        //return result;
    }
    public void addStudent(){
        System.out.print("Bitte geben Sie einen neuen Namen für einen Schüler ein: ");
        setStringInput();
        String schuelerName = stringInput;
        System.out.print("Wollen Sie wirklich " + stringInput + " als Schülernamen hinzufügen? (j/n): ");
        setStringInput();
        if (stringInput.equals("j")) {
            Schueler schueler1 = new Schueler();
            schueler1.setName(schuelerName);

            System.out.println(schuelerName + " wurde hinzugefügt.");
            System.out.println("Wählen Sie ein Geburtsjahr :");
            setIntInput();
            System.out.println("Wollen Sie " + intInput + " als Geburtsjahr von " + schueler1.getName() + " hinzufügen? (j/n): ");
            setStringInput();
            if (stringInput.equals("j")){
                schueler1.setGeburtsjahr(intInput);
            }
            statementSchuelerTable("INSERT INTO schueler (name, geburtsjahr) VALUES ('" + schueler1.getName() + "', " + schueler1.getGeburtsjahr() + ");");
            System.out.println("Dar Schüler mit Namen " + schueler1.getName() + " und Geburtsjahr " + schueler1.getGeburtsjahr() + " wurde hinzugefügt.");

        } else {
            System.out.println("abgebrochen. Kein Schüler wurde hinzugefügt.");
        }
    }
    public void deleteSchueler(){
        System.out.print("Geben Sie den Namen des zu löschenden Schülers ein: ");
        setStringInput();

        int manipuliert = statementSchuelerTable("DELETE FROM schueler WHERE name = '" + stringInput + "';");
        if (manipuliert >= 1) {
            System.out.println(stringInput + " gelöscht.");
        } else if (manipuliert == 0){
            System.out.println("Das Instrument " + stringInput + " gibt es nicht.");
        }
    }



    public int statementSchuelerTable(String query){
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
