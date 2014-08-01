import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Adam on 7/28/2014.
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
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    textFieldUser.setText("");
                    passwordField.setText("");
                    textFieldUser.requestFocus();
                    wrongLogInTries++;
                    if(wrongLogInTries > 3){
                        JOptionPane.showMessageDialog(null, "Too many incorrect attempts. GoodBye");
                        System.exit(-1);
                    }
                }

            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String uname = textFieldUser.getText();
                String pass = passwordField.getText();

                if (users.containsValue(uname)) userNameExists = true;

                if(userNameExists){
                    JOptionPane.showMessageDialog(null, "Username exists. Please try again");
                    textFieldUser.setText("");
                    passwordField.setText("");
                    textFieldUser.requestFocus();

                } else {
                    System.out.println("out of try");
                    try {
                        //Open file in append mode by adding true to argument list
                        BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.txt", true));
                        writer.append("\n" + uname + ":" + pass);
                        writer.close();

                        BufferedWriter prefs = new BufferedWriter(new FileWriter("src/prefs.txt", true));
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
