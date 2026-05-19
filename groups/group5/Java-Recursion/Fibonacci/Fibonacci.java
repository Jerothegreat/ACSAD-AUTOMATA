package Fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;

public class Fibonacci {
    public static String process(String[] inputs) throws Exception {
        int terms = Integer.parseInt(inputs[0].trim());
        if (terms <= 2)
            throw new Exception("Input must be greater than 2.");
        BigInteger a = BigInteger.ZERO, b = BigInteger.ONE;
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < terms; i++) {
            if (i == 0)
                res.add(a.toString());
            else if (i == 1)
                res.add(b.toString());
            else {
                BigInteger next = a.add(b);
                res.add(next.toString());
                a = b;
                b = next;
            }
        }
        return "This program will find all the terms of the Fibonacci numbers.\nThe Fibonacci numbers are:\n"
                + String.join(", ", res);
    }
}