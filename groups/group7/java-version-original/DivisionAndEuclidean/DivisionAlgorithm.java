import java.util.Scanner;
import java.util.InputMismatchException;

public class DivisionAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== DIVISION ALGORITHM =====");

            int a = readPositiveInt(scanner, "Enter the first integer: ");
            int b = readPositiveInt(scanner, "Enter the second integer: ");

            // Higher integer = dividend (m), lower = divisor (n)
            int m = Math.max(a, b);
            int n = Math.min(a, b);

            int q = m / n;
            int r = m % n;

            System.out.println("\nSOLUTION:");
            // Format: "m = n(q) + r"  — no space before '(' per activity spec
            System.out.println(m + " = " + n + "(" + q + ") + " + r);
            System.out.println("The dividend is " + m);
            System.out.println("The divisor is " + n);
            System.out.println("The quotient is " + q + " and the remainder is " + r);

            System.out.print("\nCompute again? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("y")) {
                System.out.println("Goodbye!");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Reads a strictly positive integer from the user.
     * Handles: letters, symbols, decimals, negatives, zero, overflow.
     */
    static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("  ERROR: Input cannot be empty. Please enter a positive integer.");
                continue;
            }

            // Reject decimals explicitly before parseInt swallows them as exceptions
            if (input.contains(".")) {
                System.out.println("  ERROR: Decimals are not allowed. Please enter a whole number.");
                continue;
            }

            // Reject anything that isn't an optional '-' or '+' followed by digits
            if (!input.matches("[+-]?\\d+")) {
                System.out.println("  ERROR: \"" + input + "\" is not a valid integer. No letters or symbols allowed.");
                continue;
            }

            int value;
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ERROR: Number is too large. Please enter a value within the int range.");
                continue;
            }

            if (value <= 0) {
                System.out.println("  ERROR: Only positive integers (greater than 0) are accepted.");
                continue;
            }

            return value;
        }
    }
}
