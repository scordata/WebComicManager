import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Adam on 7/28/2014.
 */
public class LogInGui extends JFrame{

    JButton buttonLogIn = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField textFieldUser = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);




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
        buttonLogIn.setBounds(110, 100, 80, 20);

        panel.add(buttonLogIn);
        panel.add(textFieldUser);
        panel.add(passwordField);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //login();
    }

    public void login(final HashMap<String, String> users){

        buttonLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = textFieldUser.getText();
                String pass = passwordField.getText();

                if(users.containsValue(uname) && pass.equals(users.get(pass))){
                    hello hello = new hello();
                    String[] n = null;
                    try {
                        hello.main(n);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    textFieldUser.setText("");
                    passwordField.setText("");
                    textFieldUser.requestFocus();
                }

            }
        });

    }



}