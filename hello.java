import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * SCRATCH PAD CODE - PLEASE IGNORE
 *
 * Created by adam on 7/18/14.
 *
 * ideas:
 *      dilbert
 *      xkcd
 *      grad school comics
 *      abtruse goose
 *
 */
public class hello {

    /*
    public static BufferedReader read(String url) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        new URL(url).openStream()));
    } // read
    */


    public static void main(String[] args) throws IOException {

        HashMap<String, String> hm = new HashMap<String, String>();

        hm.put("Adam", "Adam");

        System.out.println("hello");

        boolean ya = true;

        Scanner scan = new Scanner(System.in);

        while(ya){
            System.out.println("EnterName:");
            String ja = scan.nextLine();
            if (hm.containsKey(ja)){
                System.out.println("User " + hm.get(ja) + " accepted.");
                ya = false;
            }
        }


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

        /*
        for(int i = 0; i < anh.size(); i++){
            anh.get(i).fetch();
            WebComicGUI.display(anh.get(i));
        }
        */



        /*

        Image xkcdImage = null;
        Image dilbertImage = null;
        Image abtrooseImage = null;
        Image phdImage = null;

        URL xkcd = new URL("https://xkcd.com/");
        URL dilbert = new URL("http://www.dilbert.com/");
        URL abtroose = new URL("http://abstrusegoose.com/");
        URL phd = new URL("http://phdcomics.com/comics.php");

        //String xkcdPatStr = "<img src=\"http://imgs.xkcd.com/comics/";
        Pattern xkcdPattern = Pattern.compile(xkcdPatStr);
        System.out.println("looking for: " + xkcdPattern);

        */



        /*
        try{
           // URL url = new URL("http://www.google.com/logos/logo.gif");
           // image = ImageIO.read(url);

            //URL xkcd = new URL("https://xkcd.com/");
            //URL dilbert = new URL("http://www.dilbert.com/");
            //URL abtroose = new URL("http://abstrusegoose.com/");
            //URL phd = new URL("http://phdcomics.com/comics.php");

            System.out.println("PRINTING XKCD");

            String sxkcd = "https://xkcd.com";

            BufferedReader reader = read(sxkcd);
            String line = reader.readLine();

            ArrayList<String> xkcdArray = new ArrayList<String>();





            while(line != null){
                Matcher xkcdMatcher = xkcdPattern.matcher(line);
                if(xkcdMatcher.find()){
                    System.out.println("FOUND!!!!!!!!!!!!!");
                    System.out.println(line);
                    Pattern p = Pattern.compile("([^\"]*)");
                    Matcher m = p.matcher(line);
                    //line = m;
                    while(m.find()) {
                        //if(m.group() != null) xkcdArray.add(m.group());
                        String tmp = m.group();
                        if(tmp != null) xkcdArray.add(tmp);
                        //System.out.println("HERE IT IS: " + tmp);
                    }
                    break;
                }
                //System.out.println(line);
                line = reader.readLine();
            }

            System.out.println("XKCD MATCHED TO: " + xkcdArray.get(2));
            URL xkcdImageUrl = new URL(xkcdArray.get(2));


            // Start Dilbert Matching pattern

            String sdilbert = "http://www.dilbert.com/";
            BufferedReader dilbertBuf = read(sdilbert);
            String dilbertLine = dilbertBuf.readLine();

            ArrayList<String> dilbertArray = new ArrayList<String>();


            Pattern dilbertPattern = Pattern.compile("<img src=\"/dyn/str_strip/");
            System.out.println("looking for: " + dilbertPattern);

            while(dilbertLine != null){
                Matcher dilbertMatcher = dilbertPattern.matcher(dilbertLine);
                if(dilbertMatcher.find()){
                    System.out.println("FOUND!!!!!!!!!!!!!");
                    System.out.println(dilbertLine);
                    Pattern p = Pattern.compile("([^\"]*)");
                    Matcher m = p.matcher(dilbertLine);
                    //line = m;
                    while(m.find()) {
                        //if(m.group() != null) xkcdArray.add(m.group());
                        String tmp = m.group();
                        if(tmp != null) {
                            dilbertArray.add(tmp);
                            System.out.println("HERE IT IS: " + tmp);
                        }
                        //System.out.println("HERE IT IS: " + tmp);
                    }
                    break;
                }
                //System.out.println(dilbertLine);
                dilbertLine = dilbertBuf.readLine();
            }

            //System.out.println("Group3 = " + dilbertArray.get(2));
            System.out.println("Dilbert MATCHED TO: " +  "http://www.dilbert.com/" + dilbertArray.get(2));
            String dilbertTemp = "http://www.dilbert.com/" + dilbertArray.get(2);
            URL dilbertImageUrl = new URL(dilbertTemp);



            xkcdImage = ImageIO.read(xkcdImageUrl);
            dilbertImage = ImageIO.read(dilbertImageUrl);
            abtrooseImage = ImageIO.read(abtroose);
            phdImage = ImageIO.read(phd);


        } catch (IOException e){
            System.out.println("ERROR");
        } catch (Exception e) {
            e.printStackTrace();
        }

        File f = new File("C:\\Users\\Adam\\IdeaProjects\\hello\\src\\logo.gif");
        //(hello.class.getProtectionDomain().getCodeSource().getLocation() + "logo.gif");//
        //"C:\\Users\\Adam\\IdeaProjects\\hello\\out\\production\\hello\\logo.gif");
        //URL logo = hello.class.getProtectionDomain().getCodeSource().getLocation();


        // System.out.println("URL LOCAL: " + logo + "logo.gif" );

        System.out.println("FETCHING LOCALLY: " + f);


        System.out.println(xkcdImage);
        ImageIO.write((java.awt.image.RenderedImage) xkcdImage, "gif", f);
        */

        /*
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(dilbertImage);//xkcdImage);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Comic");
        label.setIcon(icon);
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        */





        /*

        NetHandler xkcdNetHandler = new NetHandler("xkcd", xkcd, xkcdPatStr, 2);
        xkcdNetHandler.fetch();

        JFrame frame = new JFrame(xkcdNetHandler.getComicName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(xkcdNetHandler.getComicImage());//xkcdImage);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel(xkcdNetHandler.getComicName());
        label.setIcon(icon);
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);


        //dilbert test
        String dilParStr = "<img src=\"/dyn/str_strip/";
        NetHandler dilbertNetHandler = new NetHandler("Dilbert", dilbert, dilParStr, 2, "http://www.dilbert.com/");
        dilbertNetHandler.fetch();

        JFrame dframe = new JFrame(dilbertNetHandler.getComicName());
        dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon dicon = new ImageIcon(dilbertNetHandler.getComicImage());//xkcdImage);

        //Add the ubiquitous "Hello World" label.
        JLabel dlabel = new JLabel(dilbertNetHandler.getComicName());
        dlabel.setIcon(dicon);
        dframe.getContentPane().add(dlabel);

        //Display the window.
        dframe.pack();
        dframe.setVisible(true);

        //phd test

        String phdPatStr = "href='http://www.phdcomics.com/comics/archive/"; //"<link rel='image_src' href='http://www.phdcomics.com/comics/archive/";
        NetHandler phdNetHandler = new NetHandler("PHD Comics", phd, phdPatStr, 26);
        phdNetHandler.fetch();

        JFrame pframe = new JFrame(phdNetHandler.getComicName());
        pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picon = new ImageIcon(phdNetHandler.getComicImage());

        //Add the ubiquitous "Hello World" label.
        JLabel plabel = new JLabel(phdNetHandler.getComicName());
        plabel.setIcon(picon);
        pframe.getContentPane().add(plabel);

        //Display the window.
        pframe.pack();
        pframe.setVisible(true);

        //abtroose goose

        String abtPatStr = "<img src=\"http://abstrusegoose.com/strips/";
        NetHandler abtNetHandler = new NetHandler("Abtroose Goose", abtroose, abtPatStr, 2);
        abtNetHandler.fetch();

        JFrame aframe = new JFrame(abtNetHandler.getComicName());
        aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon aicon = new ImageIcon(abtNetHandler.getComicImage());
        //Add the ubiquitous "Hello World" label.
        JLabel alabel = new JLabel(abtNetHandler.getComicName());
        alabel.setIcon(aicon);
        aframe.getContentPane().add(alabel);

        //Display the window.
        aframe.pack();
        aframe.setVisible(true);

        */




    }


}