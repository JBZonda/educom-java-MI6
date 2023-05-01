package nu.educom.MI6;

import javax.swing.*;
import java.awt.event.*;
public class View extends JFrame {
    static JFrame f;
    static JButton b;
    static JTextField t1;
    static JTextField t2;
    static JLabel l1;
    static JLabel l2;

    LogInAttempt logInAttempt;

    boolean ready = false;
    public View(){
        f= new JFrame("Agent log in");

        // create a label to display text
        l1 = new JLabel("Agent Number");
        l2 = new JLabel("Secret Sentence");

        // create a new button
        b = new JButton("submit");

        // addActionListener to button
        b.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("submit")) {
                // set the text of the label to the text of the field
                //System.out.println(t1.getText());
                //System.out.println(t2.getText());
                setLogInAttempt(t1.getText(),t2.getText());
                ready = true;
                // set the text of field to blank
                t1.setText("");
                t2.setText("");
            }
        }
        });

        t1 = new JTextField(16);
        t2 = new JTextField(16);

        JPanel p = new JPanel();

        // make panel
        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(b);
        f.add(p);


        // frame settings
        f.setSize(350, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }





    private void setLogInAttempt(String an, String ss){
        logInAttempt = new LogInAttempt(an,ss);
    }
    public LogInAttempt getLogIn(){
        if (ready) {
            //ready = false;
            return logInAttempt;
        } else{
            return null;
        }

    }
}
