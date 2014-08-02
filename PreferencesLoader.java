import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Adam Najman on 7/27/2014.
 *
 * This is a helper class. There are some methods here that are
 * not employed in the production code - they are mainly for debugging
 * purposes. This loads user info such as names and passwords, and
 * figures out how many comics they want to see.
 *
 */
public class PreferencesLoader {

    public static HashMap<String, String> users = new HashMap<String, String>();
    private static HashMap<String, int[]> preferences = new HashMap<String, int[]>();

    /*
    * TESTING MAIN - only launch without GUI integration
    * NOT TO BE RUN UNLESS FROM THIS THREAD ONLY
     */
    public static void main(String[] args) throws IOException {
        try {
            loadUsers();
            logIn();
            readPreferences();
            buildLinkPod(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * Takes a user, loads their comic strip preferences
     */
    public static int[] loadPrefs(String user){

        int[] userPrefs = new int[4];                           // Max preferences = four
        int[] tmpPrefs = preferences.get(user);                 // Temp for transfer

        for (int i = 1; i < tmpPrefs.length; i++){              // basically array.copy()
            System.out.println(i);
            userPrefs[i-1] = tmpPrefs[i];
        }

        System.out.println("Preferences for "                   // Debug Statement
                + user + " loaded");

        return userPrefs;

    }

    /*
    *   Load the users. This file SHOULD change after a
    *   new user registers. This requires a re-launch of the
    *   program
     */
    public static void loadUsers() throws IOException {
        BufferedReader reader = new BufferedReader(             // load file
                new FileReader("src/users.txt"));

        String next = reader.readLine();                        // temp for while()

        while(next != null){
            String[] userPass = next.split(":");                // split on delimiter
            String user = userPass[0];                          // username
            String pass = userPass[1];                          // their password

            users.put(user, pass);                              // add to hash

            next = reader.readLine();                           // keep going
        }

        System.out.println("Users Loaded");

    }

    /*
    * This class is used to build linkpods, which are general
    * PODs (plain old data). They are used in tandem with NetHandlers
    * to ease their creation. Pattern abstraction
     */
    public static LinkPod buildLinkPod(int i) throws IOException {
        BufferedReader reader = new BufferedReader(                 // open file
                new FileReader("src/links.txt"));
        int lineCount = 1;                                          // counter for debugging
        String[] info = new String[5];                              // max prefs
        String next = reader.readLine();                            // for while()

        while( (next != null) && (lineCount <= i) ) {

            //System.out.println("i " + i);
            //System.out.println("lincecount " + lineCount);
            if (lineCount != i) {                                   //line count makes
                next = reader.readLine();                           // sure we dont read the
                lineCount++;                                        // same info twice, due to
                continue;                                           // changes in user prefs
            }
            info = next.split("\\^");
            //System.out.println("next " + next);
            //System.out.println("length " + info.length);
            for (int x = 0; x < info.length; x++){
                System.out.println(x + " " + info[x]);
            }
            lineCount++;
        }

        if(info.length == 5){                                     // checks if we need a prefix version or not
            return new LinkPod(info[0],new URL(info[1]), info[2],
                                Integer.parseInt(info[3]), info[4]);
        }

        return new LinkPod(info[0],new URL(info[1]), info[2], Integer.parseInt(info[3]));
    }


    /*
    * Reads users preferences from a file. Builds the hash accordingly.
     */
    public static void readPreferences() throws IOException {
        BufferedReader reader = new BufferedReader(                     // open file
                new FileReader("src/prefs.txt"));

        String next = reader.readLine();                                //for while()

        while(next != null){
            String[] prefs = next.split(":");                           //split on delimiter

            int[] comics = new int[5];
            String user = prefs[0];


            for (int i = 1; i < prefs.length; i++){                     //load into memory
                comics[i] = Integer.parseInt(prefs[i]);
            }

            preferences.put(user, comics);                              // build hash

            next = reader.readLine();

        }
    }

    /*
    * DEBUGGING METHOD: use only without GUI. This is to
    * check integrity of hashing algorithm only.
     */
    public static boolean logIn(){

        Scanner scan = new Scanner(System.in);              // user input
        int wrongCount = 0;                                 // wrong tries

        while(wrongCount <= 2) {

            System.out.println("Enter User Name:");

            String tempUser = scan.nextLine();

            if (!users.containsKey(tempUser)) {             // if user doesn't exist, report error
                System.out.println("No such user");
                wrongCount++;                               // increase count
                continue;
            }

            System.out.println("Enter Password");           // else

            String tempPass = scan.nextLine();

            if(!users.get(tempUser).equals(tempPass)){      //if wrong password, increase count
                System.out.println("Wrong Password");
                wrongCount++;
                continue;
            }

            System.out.println("Welcome, " + tempUser);     // else, let them in
            return true;

        }

        System.out.println("Exceeded limit. Exiting...");   // if we ever get three bad tries, we quit.
        return false;

    }


}
