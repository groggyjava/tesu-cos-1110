import java.security.InvalidParameterException;

public class Distribution100 {
    /**
     * Accepts a list of integers, the first of which denotes
     * how many integer parameters to expect.
     * The determines, of the command-line parameter which are between 1 and 100 (inclusive),
     * how many of those
     *
     * @param args single integer for which conduct this exercise.
     */
    public static void main(String[] args) {

        final String CORRECT_USAGE = """
                Correct usage: java Distribution100 [a1, a2, a3, ... aN]
                  where:
                      - a1 is a valid integer
                      - a1 equals the number of additional parameters provided [a2, a3, ... aN]
                      - a2...aN are integers between 1 and 100.
                """;

        final int UPPER_BOUND = 100;
        final int LOWER_BOUND = 1;
        final int THRESHOLD = 50;

        // number of expected integer command-line parameters.
        int n = 0;

        // array to hold the numbers received.
        int[] numbers;

        // validate and parse command-line input
        try {
            // validate that command-line input has been provided
            if (args == null || args.length == 0) {
                throw new IllegalArgumentException("You must specify at least one argument.");
            }
            // validate that first command-line parameter is actually an integer (will throw exception if not)
            n = Integer.parseInt(args[0]);

            // validate number of additional arguments
            if (n != args.length - 1) {
                throw new IllegalArgumentException(
                        "Incorrect number of integers to be tested. Expected " + n + ", got " + (args.length - 1));
            }

            // validate and parse additional arguments (i.e. the ints to be tested)
            numbers = new int[n];
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(args[i + 1]);
                if (a < LOWER_BOUND || a > UPPER_BOUND) {
                    throw new IllegalArgumentException(
                            "All numbers to be tested must be between " + LOWER_BOUND + " and " + UPPER_BOUND
                                    + ". Offending number is  " + a);
                }
                numbers[i] = a;
            }
        } catch (NumberFormatException ex) {
            // thrown by Integer.parseInt
            // --> exit program with error message.
            System.out.println("Invalid command-line arguments.\n" + CORRECT_USAGE);
            return;
        } catch (IllegalArgumentException ex) {
            // first command-line parameter is not a valid integer.
            // --> exit program with error message.
            System.out.println(ex.getMessage() + "\n" + CORRECT_USAGE);
            return;
        }

        // Process values
        int under = 0; // count less than or equal to THRESHOLD
        int over = 0; // count greater than THRESHOLD

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > THRESHOLD) {
                over++;
            } else {
                under++;
            }
        }

        // Output results
        System.out.println(under + " numbers entered are less than or equal to " + THRESHOLD + ".");
        System.out.println(over + " numbers entered are greater than " + THRESHOLD + ".");
    }
}