import java.util.Scanner;

public class Tribonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String termsText = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (!hasValidInput(termsText)) {
            return;
        }

        int terms = Integer.parseInt(termsText.trim());
        System.out.println("Tribonacci sequence:");
        for (int i = 0; i < terms; i++) {
            System.out.print(tribonacci(i));
            if (i < terms - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    static boolean hasValidInput(String termsText) {
        if (!isInteger(termsText)) {
            System.out.println("Invalid input. Enter the number of terms.");
            return false;
        }

        int terms = Integer.parseInt(termsText.trim());
        if (terms <= 0) {
            System.out.println("Invalid input. Number of terms must be positive.");
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
