import java.util.Scanner;

public class Collatz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberText = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (!hasValidInput(numberText)) {
            return;
        }

        int number = Integer.parseInt(numberText.trim());
        System.out.println("Collatz sequence for " + number + ":");

        while (number != 1) {
            System.out.print(number + " ");
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = (number * 3) + 1;
            }
        }

        System.out.println(1);
    }

    static boolean hasValidInput(String numberText) {
        if (!isInteger(numberText)) {
            System.out.println("Invalid input. Enter a positive integer greater than 0.");
            return false;
        }

        int number = Integer.parseInt(numberText.trim());
        if (number <= 0) {
            System.out.println("Invalid input. Number must be greater than 0.");
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
