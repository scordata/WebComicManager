import javafx.concurrent.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adam on 7/28/2014.
 */
public class WebComicManager {

    static boolean loggedIn = false;


    public static void main(String[] args) throws IOException, InterruptedException {

        PreferencesLoader preferencesLoader = new PreferencesLoader();
        LogInGui logInGui = new LogInGui();
        ArrayList<NetHandler> netHandlerArrayList = new ArrayList<NetHandler>();

        preferencesLoader.loadUsers();




        while(!loggedIn){
            Thread.sleep(1000);
            System.out.print(loggedIn);
            loggedIn = logInGui.login(preferencesLoader.users);
        }

        preferencesLoader.readPreferences();

        int[] comics = preferencesLoader.loadPrefs(logInGui.username);





        for ( int i = 0; i < comics.length; i++){
            netHandlerArrayList.add(new NetHandler(preferencesLoader.buildLinkPod(comics[i])));
            netHandlerArrayList.get(i).fetch();
            logInGui.progressBar.setValue(( i * 25) + 25);
        }

        StripGui.create(netHandlerArrayList);

    }
}
