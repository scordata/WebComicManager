import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Adam Najman on 7/28/2014.
 *
 * This class works in tandem with LogInGui.
 * After the registered user logs in, the this class launches,
 * and presents them with a slide-show.
 *
 */
public class StripGui extends JPanel {


    private static final JPanel strips =                            //Use a cardlayout for slideshow
            new JPanel(new CardLayout());
    private final String title;

    /*
    * The constructor pulls all the info from the NetHandler
    * There's no way in instantiate this class without one.
    * THEY ARE TIGHTLY COUPLED.
     */
    public StripGui(NetHandler netHandler){
        this.title = netHandler.getComicName();
        this.add(new JLabel(title));
        ImageIcon icon = new ImageIcon(netHandler.getComicImage());
        JLabel img = new JLabel();
        img.setIcon(icon);
        this.add(img);
    }

    @Override
    public String toString(){
        return title;
    }

    /*
    * Here we create a bunch of cards, as assign each one a title,
    * image, etc. The AbstractActions listen for button clicks to
    * scroll though the cards
     */
    public static void create(ArrayList<NetHandler> netHandlers){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < netHandlers.size(); i++){
            StripGui sg = new StripGui(netHandlers.get(i));
            strips.add(sg, sg.toString());
        }
        JPanel controller = new JPanel();
        controller.add(new JButton(new AbstractAction("\u22b2Prev") {       //go back
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) strips.getLayout();
                cardLayout.previous(strips);
            }
        }));
        controller.add(new JButton(new AbstractAction("Next\u22b3") {       // go forward
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) strips.getLayout();
                cardLayout.next(strips);
            }
        }));
        f.add(strips, BorderLayout.CENTER);                                 // put it all together
        f.add(controller, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }


}
