import javax.swing.*;

/** SCRATCH PAD CODE - PLEASE IGNORE
 * Created by Adam on 7/27/2014.
 */
public class WebComicGUI {


    public static void display(NetHandler netHandler) {
        JFrame frame = new JFrame(netHandler.getComicName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(netHandler.getComicImage());

        //Add the label
        JLabel label = new JLabel(netHandler.getComicName());
        label.setIcon(icon);
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
