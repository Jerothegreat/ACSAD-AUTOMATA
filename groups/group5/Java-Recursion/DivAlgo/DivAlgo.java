package DivAlgo;

public class DivAlgo {
    public static String process(String[] inputs) throws Exception {
        int a = Integer.parseInt(inputs[0].trim());
        int b = Integer.parseInt(inputs[1].trim());
        if (a <= 0 || b <= 0)
            throw new Exception("Inputs must be positive integers.");
        int dividend = Math.max(a, b);
        int divisor = Math.min(a, b);
        int q = dividend / divisor;
        int r = dividend % divisor;
        return String.format("SOLUTION:\n%d = %d (%d) + %d\n\nDividend: %d\nDivisor: %d\nQuotient: %d\nRemainder: %d",
                dividend, divisor, q, r, dividend, divisor, q, r);
    }
}