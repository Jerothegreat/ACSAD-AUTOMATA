import java.util.Scanner;

public class EuclideanAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EUCLIDEAN ALGORITHM =====");

            int a = readPositiveInt(scanner, "Enter the first integer: ");
            int b = readPositiveInt(scanner, "Enter the second integer: ");

            // Higher = dividend (m), lower = divisor (n)
            int m = Math.max(a, b);
            int n = Math.min(a, b);

            int originalM = m;
            int originalN = n;

            System.out.println("\nSOLUTION:");

            int gcd = n;

            while (true) {
                int q = m / n;
                int r = m % n;

                if (r == 0) {
                    // No remainder line — format: "m = n(q)"
                    System.out.printf("%d = %d(%d)%n", m, n, q);
                    gcd = n;
                    break;
                } else {
                    System.out.printf("%d = %d(%d) + %d%n", m, n, q, r);
                }

                m = n;
                n = r;
            }

            long lcm = (long) originalM * originalN / gcd;

            System.out.println("\nThe integers are " + originalM + " and " + originalN);
            System.out.println("The greatest common divisor of " + originalM + " and " + originalN + " is " + gcd);
            System.out.println("The least common multiple of " + originalM + " and " + originalN + " is " + lcm + ".");

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

            // Reject decimals explicitly
            if (input.contains(".")) {
                System.out.println("  ERROR: Decimals are not allowed. Please enter a whole number.");
                continue;
            }

            // Reject anything that isn't an optional sign followed by digits
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
