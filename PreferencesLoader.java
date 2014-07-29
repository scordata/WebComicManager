import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Adam on 7/27/2014.
 */
public class PreferencesLoader {

    public static HashMap<String, String> users = new HashMap<String, String>();
    private static HashMap<String, int[]> preferences = new HashMap<String, int[]>();

    public static void main(String[] args) throws IOException {
        try {
            loadUsers();
            logIn();
            readPreferences();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int[] loadPrefs(String user){

        int[] userPrefs = new int[4];
        int[] tmpPrefs = preferences.get(user);

        for (int i = 1; i < tmpPrefs.length; i++){
            userPrefs[i-1] = tmpPrefs[i];
        }

        System.out.println("Preferences for " + user + " loaded");

        return userPrefs;

    }
    public static void loadUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/users.txt"));

        String next = reader.readLine();

        while(next != null){
            String[] userPass = next.split(":");
            String user = userPass[0];
            String pass = userPass[1];

            users.put(user, pass);

            next = reader.readLine();
        }

        System.out.println("Users Loaded");

    }

    public static void readPreferences() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/prefs.txt"));

        String next = reader.readLine();

        while(next != null){
            String[] prefs = next.split(":");

            int[] comics = new int[5];
            String user = prefs[0];


            for (int i = 1; i < prefs.length; i++){
                comics[i] = Integer.parseInt(prefs[i]);
            }

            preferences.put(user, comics);

            next = reader.readLine();

        }


    }

    public static boolean logIn(){

        Scanner scan = new Scanner(System.in);
        int wrongCount = 0;

        while(wrongCount <= 2) {

            System.out.println("Enter User Name:");

            String tempUser = scan.nextLine();

            if (!users.containsKey(tempUser)) {
                System.out.println("No such user");
                wrongCount++;
                continue;
            }

            System.out.println("Enter Password");

            String tempPass = scan.nextLine();

            if(!users.get(tempUser).equals(tempPass)){
                System.out.println("Wrong Password");
                wrongCount++;
                continue;
            }

            System.out.println("Welcome, " + tempUser);
            return true;

        }

        System.out.println("Exceeded limit. Exiting...");
        return false;

    }


}
