import java.net.URL;

/**
 * Created by Adam on 7/29/2014.
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
