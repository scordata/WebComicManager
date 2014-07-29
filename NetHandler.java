import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Adam Najman on 7/27/2014.
 *
 * NetHandler:
 *      This class is used to do the heavy lifting for our program.
 *      Once supplied with inputs, the user can call fetch() to download
 *      the source code from the appropriate web server, parse the HTML
 *      find the Image, and save it in memory. This, in turn, will be supplied
 *      to the GUI to display for the user.
 *
 *      VARIABLES:
 *
 *          String comicName        = Name of comic, used to referencing and debugging.
 *
 *          URL comicURL            = Where the web comic is hosted. ex www.dilbert.com
 *
 *          Image comicImage        = What we want to extract.
 *                                      NOT DEFINED IN CONSTRUCTOR
 *                                      EXPECT NULL UNTIL AFTER fetch() IS CALLED.
 *
 *          Pattern comicPattern    = The RegEx pattern used to find our image location.
 *
 *          int regExGroupLocation  = We need to supply the group location of the image after parsing.
 *                                      This might be able to be deduced with some ML or AI algorithm,
 *                                      but I'm not that smart. This is provided a priori. (Found by experimentation)
 *
 *          String prependURLString = Some img links are stored with relative referencing. This means we have to
 *                                      prepend the url with the host name. This will be provided on an as-need
 *                                      basis.
 *
 */
public class NetHandler {

    private String comicName = null;
    private URL comicURL = null;
    private Image comicImage = null;
    private Pattern comicPattern = null;
    private int regExGroupLocation = 0;
    private String prependURLString = null;


    /*
    * NetHandler Constructor
    *
    *   INPUT:
    *           String str      = Our comic's name (Dilbert, Garfield, etc)
    *
    *           URL url         = Hosting location of the Web comic. NOT IMAGE LOCATION (www.xkcd.com)
    *
    *           String pattern  = The RegEx to find the image in the HTML soup.
    *
    *           int pos         = The group number we anticipate to find the image URL.
     */
    public NetHandler(String str, URL url, String pattern, int pos){
        comicName = str;
        comicURL = url;
        comicPattern = Pattern.compile(pattern);
        regExGroupLocation = pos;
    }

    /*
    * NetHandler Constructor with prependURLString
    *
    *   INPUT:
    *           String str      = Our comic's name (Dilbert, Garfield, etc)
    *
    *           URL url         = Hosting location of the Web comic. NOT IMAGE LOCATION (www.xkcd.com)
    *
    *           String pattern  = The RegEx to find the image in the HTML soup.
    *
    *           int pos         = The group number we anticipate to find the image URL.
    *
    *           String pre      = The url header we need for image prepending.
     */
    public NetHandler(String str, URL url, String pattern, int pos, String pre){
        comicName = str;
        comicURL = url;
        comicPattern = Pattern.compile(pattern);
        regExGroupLocation = pos;
        prependURLString = pre;
    }

    public NetHandler(LinkPod linkPod){

        comicName = linkPod.comicName;
        comicURL = linkPod.url;
        comicPattern = Pattern.compile(linkPod.patternString);
        regExGroupLocation = linkPod.group;

        if (linkPod.prepend != null){
            System.out.println(linkPod.comicName + " has prepend");
            prependURLString = linkPod.prepend;
        }
    }

    /*
    * Code from Prof Goldberg
    * Used to fetch and interpret Web Server response
    * This should (hopefully) be HTML
    *
    * Object composition of:
    *       BufferedReader -> InputStreamReader -> URL().openStream()
    *
    * Useful for while(!null) stream loops.
    *
    * INPUT:
    *       String url = The url you want to query
    *
    * OUTPUT:
    *       (Object) BufferedReader = Interface for web server response.
    *
     */
    public static BufferedReader read(String url) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        new URL(url).openStream()));
    } // read


    public void fetch(){

        System.out.println("fetching info for: " + comicName);

        try{

            BufferedReader reader = read(comicURL.toString());
            String line = reader.readLine();

            ArrayList<String> comicArray = new ArrayList<String>();

            while(line != null){
                Matcher comicMatcher = comicPattern.matcher(line);
                if(comicMatcher.find()){
                    //System.out.println("FOUND!!!!!!!!!!!!!");
                    //System.out.println(line);
                    Pattern p = Pattern.compile("([^\"\']*)");
                    Matcher m = p.matcher(line);
                    int count = 0;
                    while(m.find()) {
                        String tmp = m.group();
                        if(tmp != null) comicArray.add(tmp);
                        //System.out.println("HERE IT IS: " + count + "  " + tmp);
                        count++;
                    }
                    break;
                }
                //System.out.println(line);
                line = reader.readLine();
            }

            System.out.println(comicName + " MATCHED TO: " + comicArray.get(regExGroupLocation));

            URL comicImageUrl;

            if(prependURLString == null){
                comicImageUrl = new URL(comicArray.get(regExGroupLocation));
            } else {
                comicImageUrl = new URL(prependURLString + comicArray.get(regExGroupLocation));
            }

            comicImage = ImageIO.read(comicImageUrl);


        } catch (IOException e){
            System.out.println("ERROR FETCHING " + comicName);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } // end try{}


    } // end fetch()


    // Accessor section for class Variables...
    public String getComicName() {
        return comicName;
    }

    public URL getComicURL(){
        return comicURL;
    }

    public Image getComicImage(){
        return comicImage;
    }

    // REMINDER: The NetHandler is passed a RegEx string during construction,
    //              but this method will return a compiled Pattern.
    public Pattern getComicPattern(){
        return comicPattern;
    }

    public int getRegExGroupLocation(){
        return regExGroupLocation;
    }

    public String getPrependURLString(){
        if (prependURLString.equals(null)) return "";
        return prependURLString;
    }

}
