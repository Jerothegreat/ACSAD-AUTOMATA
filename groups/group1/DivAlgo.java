import java.util.Scanner;

public class DivAlgo {
   static void print(Object object) {
        System.out.print(object);
    }

    static void println(Object object) {
        System.out.println(object);
    }

    static void calculateDivAlgo(int m, int n) {
        int q = m / n;
        int r = m % n;

        println("\nSOLUTION:");
        println(m + " = " + n + " (" + q + ") + " + r);
        println("");
        println("The dividend is " + m);
        println("The divisor is " + n);
        println("The quotient is " + q + " and the remainder is " + r);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // FIRST INPUT
        print("Enter the first integer: ");
        if (!sc.hasNextInt()) {                          // hindi integer
            println("Invalid input. Please enter an integer.");
            sc.close();
            return;
        }
        int firstInput = sc.nextInt();                   // ngayon lang tayo bumabasa
        if (firstInput <= 0) {                           // ≤ 0? error agad
            println("Invalid input. Please enter a positive integer.");
            sc.close();
            return;
        }

        // SECOND INPUT
        print("Enter the second integer: ");
        if (!sc.hasNextInt()) {                          // hindi integer
            println("Invalid input. Please enter an integer.");
            sc.close();
            return;
        }
        int secondInput = sc.nextInt();
        if (secondInput <= 0) {                          // ≤ 0? error agad
            println("Invalid input. Please enter a positive integer.");
            sc.close();
            return;
        }

        int m = Math.max(firstInput, secondInput);
        int n = Math.min(firstInput, secondInput);

        calculateDivAlgo(m, n);
        sc.close();
    }
}

