import javax.swing.*;
public class Exercise4 {
    public static class Password{
        final static int MIN_PASSWORD_LENGTH = 6;
        String pass;

        public Password(String x){
            pass = x;
        }

        public boolean hasUpperCase(){
            return pass.matches(".*[A-Z]+.*");
        }

        public boolean hasLowerCase(){
            return pass.matches(".*[a-z]+.*");
        }

        public boolean hasDigit(){
            return pass.matches(".*[0-9]+.*");
        }

        public boolean isValid(){
            if(hasUpperCase() && hasLowerCase() && hasDigit() && pass.length() >= MIN_PASSWORD_LENGTH)
                return true;
            return false;
        }
    }
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a password:");
        Password p = new Password(input);
        if(p.isValid())
            JOptionPane.showMessageDialog(null,"Valid password.");
        else
            JOptionPane.showMessageDialog(null,"Not valid password.");
    }
}
