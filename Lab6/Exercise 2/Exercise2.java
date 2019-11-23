import javax.swing.*;
public class Exercise2 {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a string.");
        int count = input.split(" ").length;
        JOptionPane.showMessageDialog(null,"That " +
                "string has "+count+"word(s) in it.");
    }
}
