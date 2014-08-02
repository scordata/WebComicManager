
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adam Najman on 7/28/2014.
 *
 * The Main event! Web Comic Manager's main method is located here
 * This is the glue that ties all the other pieces together and
 * will present you with the program.
 */
public class WebComicManager {

    static boolean loggedIn = false;                            // are we logged in yet?


    /*
    * Main - run to launch everything.
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        PreferencesLoader preferencesLoader = new PreferencesLoader();    // we need to load the preferences
        LogInGui logInGui = new LogInGui();                               // we need to log in .
        ArrayList<NetHandler> netHandlerArrayList =                      // an array list of nethandlers to
                new ArrayList<NetHandler>();                                        // handle connections

        preferencesLoader.loadUsers();

        while(!loggedIn){                                               // while we're not logged in
            Thread.sleep(1000);                                         // check if we've succeeded every second
            System.out.print(loggedIn);
            loggedIn = logInGui.login(preferencesLoader.users);         // this prevents rate-limiting
        }

        preferencesLoader.readPreferences();                            // read the preferences of the user

        int[] comics = preferencesLoader.loadPrefs(logInGui.username);  // store their prefs in an array for
                                                                        // easy access
        for ( int i = 0; i < comics.length; i++){
            netHandlerArrayList.add(                                    // add each to the list (to reference)
                    new NetHandler(
                            preferencesLoader.buildLinkPod(
                                    comics[i])));                       // call fetch() to load their data
            netHandlerArrayList.get(i).fetch();
            logInGui.progressBar.setValue(( i * 25) + 25);              //adjust progress bar as we load (super cool!)
        }

        StripGui.create(netHandlerArrayList);                           //make a card for each comic

    }
}
