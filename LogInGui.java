import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Adam Najman on 7/28/2014.
 *
 * The Log in Gui. Here we present the user with a log in screen.
 * If they don't have an account, they can register.
 * If and do have an account, they will be presented with their comics.
 * Else, they will be booted for too many wrong tries.
 *
 */
public class LogInGui extends JFrame{

    JButton buttonLogIn = new JButton("Login");
    JButton register = new JButton("Register");
    JPanel panel = new JPanel();
    JProgressBar progressBar = new JProgressBar(0, 100);
    JTextField textFieldUser = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);
    int wrongLogInTries = 0;
    boolean loggedIn = false;
    String username = null;
    boolean userNameExists = false;

    public static void main(String[] args){
        LogInGui logInGui = new LogInGui();
    }


    /*
    *General gui method. Define everything
     */
    LogInGui(){
        super ("Please Log In");
        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);

        textFieldUser.setBounds(70, 30, 150, 20);
        passwordField.setBounds(70, 65, 150, 20);
        buttonLogIn.setBounds(40, 100, 80, 20);
        register.setBounds(150, 100, 100, 20);
        progressBar.setBounds(40, 125, 210, 20);
        progressBar.setStringPainted(true);

        panel.add(buttonLogIn);
        panel.add(textFieldUser);
        panel.add(passwordField);
        panel.add(register);
        panel.add(progressBar);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
    * Here we are taking a hash of the user names and corresponding passwords
    *  Accept/reject depends on supplied combinations
    *
    *  Return boolean to main: true = logged in
     */
    public boolean login(final HashMap<String, String> users){

        buttonLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = textFieldUser.getText();
                String pass = passwordField.getText();

                if(users.containsValue(uname) && pass.equals(users.get(pass))){
                    loggedIn = true;
                    username = uname;

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Wrong Password / Username");
                    textFieldUser.setText("");
                    passwordField.setText("");
                    textFieldUser.requestFocus();
                    wrongLogInTries++;
                    if(wrongLogInTries > 3){
                        JOptionPane.showMessageDialog(null,                 // boot user if they cannot supply
                                "Too many incorrect attempts. GoodBye");    // correct info
                        System.exit(-1);
                    }
                }

            }
        });

        register.addActionListener(new ActionListener() {                   // here we attempt to register a new user
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String uname = textFieldUser.getText();
                String pass = passwordField.getText();

                if (users.containsValue(uname)) userNameExists = true;

                if(userNameExists){
                    JOptionPane.showMessageDialog(null,                     // try again if username exists
                            "Username exists. Please try again");
                    textFieldUser.setText("");
                    passwordField.setText("");
                    textFieldUser.requestFocus();

                } else {
                    //System.out.println("out of try");
                    try {

                        BufferedWriter writer = new BufferedWriter(        //Open file in append mode by adding
                                new FileWriter("src/users.txt", true));    // true to argument list
                        writer.append("\n" + uname + ":" + pass);
                        writer.close();
                                                                            //make them a new user and default
                        BufferedWriter prefs = new BufferedWriter(          // assign them comics
                                new FileWriter("src/prefs.txt", true));
                        prefs.append("\n" + uname + ":1:2:3:4");
                        prefs.close();

                        JOptionPane.showMessageDialog(null, "Exit the pref.txt file to change your preferences.");

                        JOptionPane.showMessageDialog(null, "Thank you for registering, " + uname
                                                    + "! Please restart the program and log in.");
                        textFieldUser.setText("");
                        passwordField.setText("");
                        textFieldUser.requestFocus();
                        System.exit(0);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });


        return loggedIn;
    }



}
