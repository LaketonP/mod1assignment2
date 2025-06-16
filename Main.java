import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // used for replit
        Scanner i = new Scanner(System.in); // scan for input
        System.out.print("Enter a credit card number as a long integer: ");
        long number = i.nextLong(); // gets long after enter

        if (isValid(number)) { // checks for valid long
            System.out.println(number + " is valid");
        } else {
            System.out.println(number + " is invalid");
        }
    }

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        int size = getSize(number);
        boolean validPrefix = prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37) || prefixMatched(number, 6); // prefix match boolean

        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);

        return (size >= 13 && size <= 16) && validPrefix && (sum % 10 == 0); // size between 13 and 16, and vaild prefix
    }

    /** Get the result from Step 2: double even-place digits and sum */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 2; i >= 0; i -= 2) { // iterates through even digits
            int digit = Character.getNumericValue(numStr.charAt(i)); // gets int from char
            sum += getDigit(digit * 2);
        }
        return sum;
    }

    /** Return this number if it is a single digit, otherwise return sum of digits */
    public static int getDigit(int number) {
        if (number < 10) { // digits must be less than 10 to be valid
            return number;
        } else {
            return number / 10 + number % 10; // if more it divides 10 then gets remainder
        }
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 1; i >= 0; i -= 2) { // start from rightmost digit and go through odd digits
            sum += Character.getNumericValue(numStr.charAt(i)); // gets int from char and sums it
        }
        return sum;
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        int prefLength = getSize(d);
        return getPrefix(number, prefLength) == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    /** Return the first k number of digits from number */
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (numStr.length() < k) { // if k is larger than number of digits returns number
            return number;
        }
        return Long.parseLong(numStr.substring(0, k)); // returns substring of k otherwise
    }
}
