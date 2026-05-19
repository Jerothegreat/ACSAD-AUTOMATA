package Collatz;

import java.util.ArrayList;

public class Collatz {
    public static String process(String[] inputs) throws Exception {
        int start = Integer.parseInt(inputs[0].trim());
        if (start <= 0 || start % 2 == 0)
            throw new Exception("Please provide a positive odd integer.");
        ArrayList<Integer> seq = new ArrayList<>();
        int current = start, peak = start;
        seq.add(current);
        while (current > 1) {
            current = (current % 2 == 0) ? current / 2 : (3 * current) + 1;
            if (current > peak)
                peak = current;
            seq.add(current);
        }
        StringBuilder sb = new StringBuilder("Collatz sequence for ").append(start).append(":\n");
        for (int i = 0; i < seq.size(); i++)
            sb.append(seq.get(i)).append(i == seq.size() - 1 ? "" : ", ");
        sb.append("\n\nTerms: ").append(seq.size()).append("\nSteps: ").append(seq.size() - 1).append("\nPeak value: ")
                .append(peak);
        return sb.toString();
    }
}