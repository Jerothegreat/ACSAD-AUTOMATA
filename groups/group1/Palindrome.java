import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String original = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (!hasValidInput(original)) {
            return;
        }

        String cleaned = original.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        if (cleaned.equals(reversed)) {
            System.out.println(original + " is a palindrome.");
        } else {
            System.out.println(original + " is not a palindrome.");
        }
    }

    static boolean hasValidInput(String value) {
        if (value.trim().isEmpty()) {
            System.out.println("Invalid input. String must not be empty.");
            return false;
        }

        return true;
    }
}
