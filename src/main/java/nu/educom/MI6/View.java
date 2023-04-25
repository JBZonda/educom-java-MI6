package nu.educom.MI6;

import javax.swing.*;

public class View {
    JFrame frame;
    public View(){
        frame = new JFrame("Agent log in");

    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this.frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String showInput(String message){
        String input = JOptionPane.showInputDialog(this.frame, message);
        return input;
    }
}
