import java.net.URL;

/**
 * Created by Adam Najman on 7/29/2014.
 *
 * This is solely a POD (plain old data)
 * Used with NetHandlers. Only for simplifying parameter list
 * in NetHandler constructor. Used to abstract url/regex construction
 * patters.
 *
 */
public class LinkPod {
    public String comicName = null;
    public URL url = null;
    public String patternString = null;
    public int group = 0;
    public String prepend = null;

    public LinkPod(String comicName, URL url, String patternString, int group, String prepend){
        this.comicName = comicName;
        this.url = url;
        this.patternString = patternString;
        this.group = group;
        this.prepend = prepend;
    }

    public LinkPod(String comicName, URL url, String patternString, int group){
        this.comicName = comicName;
        this.url = url;
        this.patternString = patternString;
        this.group = group;
    }



}
