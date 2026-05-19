package Lucas;

import java.math.BigInteger;
import java.util.ArrayList;

public class Lucas {
    public static String process(String[] inputs) throws Exception {
        int limit = Integer.parseInt(inputs[0].trim());
        if (limit <= 2)
            throw new Exception("Value must be greater than 2");
        BigInteger p2 = BigInteger.valueOf(2), p1 = BigInteger.ONE;
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i == 0)
                res.add(p2.toString());
            else if (i == 1)
                res.add(p1.toString());
            else {
                BigInteger curr = p2.add(p1);
                res.add(curr.toString());
                p2 = p1;
                p1 = curr;
            }
        }
        return "This program will find all the terms of the Lucas numbers.\nThe Lucas numbers are:\n"
                + String.join(", ", res);
    }
}