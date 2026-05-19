package Euclidean;

public class Euclidean {
    public static String process(String[] inputs) throws Exception {
        int num1 = Integer.parseInt(inputs[0].trim());
        int num2 = Integer.parseInt(inputs[1].trim());
        if (num1 <= 0 || num2 <= 0) throw new Exception("Positive integers only.");
        int a = Math.max(num1, num2), b = Math.min(num1, num2);
        int origA = a, origB = b;
        StringBuilder sb = new StringBuilder("SOLUTION:\n");
        while (b > 0) {
            int q = a / b;
            int r = a % b;
            if (r == 0) sb.append(a).append(" = ").append(b).append(" (").append(q).append(")\n");
            else sb.append(a).append(" = ").append(b).append(" (").append(q).append(") + ").append(r).append("\n");
            a = b;
            b = r;
        }
        sb.append("\nGCD: ").append(a);
        sb.append("\nLCM: ").append((origA * origB) / a);
        return sb.toString();
    }
}