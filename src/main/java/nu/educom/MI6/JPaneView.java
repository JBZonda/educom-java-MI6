package nu.educom.MI6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JPaneView implements IView, ActionListener {
    static JFrame f;
    static JButton b;
    static JTextField t1;
    static JTextField t2;
    static JLabel l1;
    static JLabel l2;
    static JPanel p;
    IPresentor pres;
    LogInInput lIA;
    final Object lockObj = new Object();
    public JPaneView(){
        f = new JFrame("Agent log in");

        // create a label to display text
        l1 = new JLabel("Agent Number");
        l2 = new JLabel("Secret Sentence");
        // create a new button
        b = new JButton("submit");

        // addActionListener to button
        b.addActionListener(this);

        t1 = new JTextField(16);
        t2 = new JTextField(16);

        p = new JPanel(new GridLayout(6,1));

        // make panel
        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(b);
        f.add(p);

        // frame settings
        f.setSize(380, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {

            lIA = new LogInInput(t1.getText(), t2.getText());
            t1.setText("");
            t2.setText("");

            // unlock
            synchronized (lockObj) {
                lockObj.notifyAll();
            }

        }
    }
    @Override
    public void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg,"Message", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showWelcome(String msg) {
        JOptionPane.showMessageDialog(null, msg,"Message", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void triggerAskLogin() {
        // lock
        synchronized (lockObj) {
            try {
                lockObj.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        pres.handleLogin();
    }

    @Override
    public String getServiceNumber() {
        return lIA.getAgentNumber();
    }

    @Override
    public void triggerAskPassword() {
        // is already set during the AskLogin
        pres.handlePassword();
    }

    @Override
    public String getPassPhrase() {
        return lIA.getSecretSentence();
    }

    @Override
    public void close() {
    }

    @Override
    public void addPresentorListener(IPresentor p) {
        pres = p;
    }

    public void showTimeOut(int totalTimeSec){
        int sec = totalTimeSec % 60;
        int minT = totalTimeSec / 60;
        int min = minT % 60;
        int hrT = minT / 60;
        int hr = hrT % 24;
        int day = hrT / 24;
        showError("you are timed out for: " + day + " days " + hr + " hours " + min + " minutes " + sec + " seconds");
    }
}
