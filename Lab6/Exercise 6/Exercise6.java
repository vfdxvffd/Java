import java.util.Scanner;
public class Exercise6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string and I will convert it to Pig Latin");
        String input = in.nextLine();
        String[] arr = input.split(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].substring(1)+arr[i].charAt(0)+"AY ");
        }
    }
}
