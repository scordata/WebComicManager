import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Adam on 7/28/2014.
 */
public class WebComicManager {


    public static void main(String[] args) throws IOException {

        PreferencesLoader preferencesLoader = new PreferencesLoader();
        LogInGui logInGui = new LogInGui();

        preferencesLoader.loadUsers();
        while(!logInGui.login(preferencesLoader.users)){}

        ArrayList<NetHandler> anh = new ArrayList<NetHandler>();

        URL xkcd = new URL("https://xkcd.com/");
        URL dilbert = new URL("http://www.dilbert.com/");
        URL abtroose = new URL("http://abstrusegoose.com/");
        URL phd = new URL("http://phdcomics.com/comics.php");

        String dilParStr = "<img src=\"/dyn/str_strip/";
        String phdPatStr = "href='http://www.phdcomics.com/comics/archive/";
        String abtPatStr = "<img src=\"http://abstrusegoose.com/strips/";
        String xkcdPatStr = "<img src=\"http://imgs.xkcd.com/comics/";

        NetHandler xkcdNetHandler = new NetHandler("xkcd", xkcd, xkcdPatStr, 2);

        NetHandler dilbertNetHandler = new NetHandler("Dilbert", dilbert, dilParStr, 2, "http://www.dilbert.com/");

        NetHandler phdNetHandler = new NetHandler("PHD Comics", phd, phdPatStr, 26);

        NetHandler abtNetHandler = new NetHandler("Abtroose Goose", abtroose, abtPatStr, 2);

        anh.add(xkcdNetHandler);
        anh.add(dilbertNetHandler);
        anh.add(phdNetHandler);
        anh.add(abtNetHandler);

        for(int i = 0; i < anh.size(); i++) {
            anh.get(i).fetch();
        }
        StripGui.create(anh);

    }




}
