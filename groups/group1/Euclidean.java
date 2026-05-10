import java.util.Scanner;

public class Euclidean {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstText = scanner.hasNextLine() ? scanner.nextLine() : "";
        String secondText = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (!hasValidInput(firstText, secondText)) {
            return;
        }

        int first = Integer.parseInt(firstText.trim());
        int second = Integer.parseInt(secondText.trim());
        int a = first;
        int b = second;

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        System.out.println("First number: " + first);
        System.out.println("Second number: " + second);
        System.out.println("GCD: " + a);
    }

    static boolean hasValidInput(String firstText, String secondText) {
        if (!isInteger(firstText)) {
            System.out.println("Invalid input. Enter the first positive integer.");
            return false;
        }

        if (!isInteger(secondText)) {
            System.out.println("Invalid input. Enter the second positive integer.");
            return false;
        }

        int first = Integer.parseInt(firstText.trim());
        int second = Integer.parseInt(secondText.trim());

        if (first <= 0 || second <= 0) {
            System.out.println("Invalid input. Both numbers must be positive integers.");
            return false;
        }

        return true;
    }

    static boolean isInteger(String value) {
        try {
            Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }
}
