package Tribonacci;

import java.math.BigInteger;
import java.util.ArrayList;

public class Tribonacci {
    public static String process(String[] inputs) throws Exception {
        int count = Integer.parseInt(inputs[0].trim());
        if (count <= 3)
            throw new Exception("Input must be greater than 3.");
        BigInteger t0 = BigInteger.ZERO, t1 = BigInteger.ZERO, t2 = BigInteger.ONE;
        ArrayList<String> res = new ArrayList<>();
        for (int k = 0; k < count; k++) {
            if (k == 0)
                res.add(t0.toString());
            else if (k == 1)
                res.add(t1.toString());
            else if (k == 2)
                res.add(t2.toString());
            else {
                BigInteger tNext = t0.add(t1).add(t2);
                res.add(tNext.toString());
                t0 = t1;
                t1 = t2;
                t2 = tNext;
            }
        }
        return "This program will find all the terms of the Tribonacci numbers.\nThe Tribonacci numbers are:\n"
                + String.join(", ", res);
    }
}