import java.io.IOException;

/**
 * Created by Adam on 7/28/2014.
 */
public class WebComicManager {


    public static void main(String[] args) throws IOException {

        PreferencesLoader preferencesLoader = new PreferencesLoader();
        LogInGui logInGui = new LogInGui();

        preferencesLoader.loadUsers();
        logInGui.login(preferencesLoader.users);



    }




}
