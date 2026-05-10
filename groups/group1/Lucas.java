import java.util.Scanner;

public class Lucas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String termsText = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (!hasValidInput(termsText)) {
            return;
        }

        int terms = Integer.parseInt(termsText.trim());
        System.out.println("Lucas sequence:");
        for (int i = 0; i < terms; i++) {
            System.out.print(lucas(i));
            if (i < terms - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int lucas(int n) {
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        return lucas(n - 1) + lucas(n - 2);
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
