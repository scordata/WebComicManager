import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Adam on 7/28/2014.
 */
public class StripGui extends JPanel {


    private static final JPanel strips = new JPanel(new CardLayout());
    private final String title;
    //private final JLabel label = null;

    public StripGui(NetHandler netHandler){
        this.title = netHandler.getComicName();
        this.add(new JLabel(title));
        ImageIcon icon = new ImageIcon(netHandler.getComicImage());
        //this.label.setIcon(icon);
        JLabel img = new JLabel();
        img.setIcon(icon);
        this.add(img);
    }

    @Override
    public String toString(){
        return title;
    }

    public static void create(ArrayList<NetHandler> netHandlers){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < netHandlers.size(); i++){
            //System.out.println("THE FIRST ONE! " + netHandlers.get(i).getComicName());
            StripGui sg = new StripGui(netHandlers.get(i));
            strips.add(sg, sg.toString());
        }
        JPanel controller = new JPanel();
        controller.add(new JButton(new AbstractAction("\u22b2Prev") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) strips.getLayout();
                cardLayout.previous(strips);
            }
        }));
        controller.add(new JButton(new AbstractAction("Next\u22b3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) strips.getLayout();
                cardLayout.next(strips);
            }
        }));
        f.add(strips, BorderLayout.CENTER);
        f.add(controller, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }


}
