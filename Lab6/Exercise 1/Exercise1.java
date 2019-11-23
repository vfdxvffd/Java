import java.util.Scanner;
public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a string:");
        StringBuilder s = new StringBuilder(in.nextLine());
        System.out.println(s.reverse());
    }
}
