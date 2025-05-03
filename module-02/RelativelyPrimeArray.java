/**
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #2: RelativelyPrimeArray
 * <p>
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class RelativelyPrimeArray {
    /**
     * Accepts single number, n, formatted as integer, as command-line input.
     * Then creates an x n 2d array of type boolean and sets the value of each
     * array element [i,j]
     * - True, if i & j are relatively prime (have no common factors).
     * - False, if not.
     * Then Generates formatted output displaying T or F for each array element [i,j],
     * leaving blank space for where i == j.
     *
     * @param args single integer for which conduct this exercise.
     */
    public static void main(String[] args) {

        // constants used to format output
        final int COLUMN_SPACING = 2;

        final String CORRECT_USAGE = """
                Correct usage:
                    java RelativelyPrimeArray [n]
                where
                    - n is a valid integer.""";

        // number of elements requested
        int n;

        // validate that command-line input has been provided
        if (args == null || args.length != 1) {
            System.out.println(
                    "No number provided.\n" + CORRECT_USAGE);
            return;
        }

        // validate and parse command-line input
        try {
            // validate that command-line argument is actually an integer (will throw exception if not)
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            // --> exit program with error message.
            System.out.println("Invalid number provided.\n" + CORRECT_USAGE);
            return;
        }

        // instantiate 2d array large enough to hold
        // every pair of numbers 1..n
        // NOTE: only using n+1 because assignment.
        boolean[][] rsa = new boolean[n + 1][n + 1];

        /*
         * Populate Array
         */
        // use nested loop to populate array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // i & j are both incremented from 1...n for use
                // both as the numeric values to be tested
                // and as the indices for the appropriate array element
                rsa[i][j] = AreRelativelyPrime(i, j);
            }
        }

        int columnWidth = getIntegerDigitCount(n) + COLUMN_SPACING;
        String formatN = "%" + columnWidth + "d";
        String formatS = "%" + columnWidth + "s";


        /*
         * Output Array
         */
        // Blank space for row indices
        System.out.printf(formatS, "");

        // Column headers for 1...n
        for (int i = 1; i <= n; i++) {
            System.out.printf(formatN, i);
        }
        System.out.println();

        // Loop over rows, 0..n-1
        for (int i = 1; i <= n; i++) {
            // start with the row title, which is i.
            System.out.printf(formatN, i);
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    // print blank where i == j
                    System.out.printf(formatS, "");
                } else {
                    System.out.printf(formatS, rsa[i][j] ? 'T': 'F');
                }
            }
            System.out.println();
        }
    }

    /**
     * Determines if two non-zero integers are relatively prime
     * (i.e. they do not share any factors other than one).
     *
     * @param a First integer
     * @param b Second integer
     * @return Whether these two integers are relatively prime.
     */
    private static boolean AreRelativelyPrime(int a, int b) {
        // Find the greatest common divisor (GCD) of a and b using Euclidean algo,
        // which works on the observation
        // where a > b:
        //   - the GCD of a and b == GCD for b and a & b.
        int temp; // placeholder variable.
        while (b != 0) {

            // ensure b is the smaller of the two integers
            if (a < b) {
                temp = a;
                a = b;
                b = temp;
            }
            // put the starting value of b aside
            temp = b;

            // put a % b into b
            // (spoiler: if it's zero, we hit the GCD)
            b = a % b;

            // put b's starting value into a
            a = temp;
        }
        // these two numbers are only relatively prime
        // if their greatest common divisor is 1.
        return (a == 1);
    }

    /**
     * Gets number of digits in an integer.
     * Includes one extra digit for sign if negative.
     *
     * @param num Integer to get digit count for.
     * @return Digit count (with one extra for sign if negative).
     */
    private static int getIntegerDigitCount(int num) {
        if (num > 0) {
            // log base 10 of a positive integer rounded up is the number of digits in that integer
            return (int) (Math.ceil(Math.log10(num)));
        } else if (num < 0) {
            // round up log-10 of absolute value and add a digit
            return (int) (Math.ceil(Math.log10(Math.abs(num))) + 1);
        } else {
            // zero is one digit
            return 1;
        }
    }
}
