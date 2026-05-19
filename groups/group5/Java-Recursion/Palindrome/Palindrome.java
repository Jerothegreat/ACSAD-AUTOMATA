package Palindrome;

public class Palindrome {
    public static String process(String[] inputs) throws Exception {
        String str = inputs[0].trim();
        if (str.isEmpty())
            throw new Exception("String cannot be empty.");
        String cleanStr = "";
        for (char c : str.toCharArray()) {
            if (c != ' ') {
                if (c >= 'A' && c <= 'Z')
                    cleanStr += (char) (c + 32);
                else
                    cleanStr += c;
            }
        }
        char[] chars = cleanStr.toCharArray();
        int left = 0, right = chars.length - 1;
        boolean isPal = true;
        while (left < right) {
            if (chars[left] != chars[right]) {
                isPal = false;
                break;
            }
            left++;
            right--;
        }
        return "Original String: " + str + "\nLength: " + str.length() + "\nResult: " + str
                + (isPal ? " is a palindrome!!!!" : " is NOT a palindrome!!!!");
    }
}