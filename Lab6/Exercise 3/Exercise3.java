import javax.swing.*;
public class Exercise3 {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a string.");
        String[] str = input.split("\\.");
        String output = "";
        for (int i = 0; i < str.length; i++) {
            String s1 = str[i].substring(0,1).toUpperCase();
            String s2 = str[i].substring(1);
            output = output+s1+s2+".";
        }
        JOptionPane.showMessageDialog(null,output.substring(0,output.length()-1));
    }
}
