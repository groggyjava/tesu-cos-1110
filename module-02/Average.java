/**
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #2: RelativelyPrimeArray
 * <p>
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class Average {
    /**
     * Takes a single integer N as a command line parameter, and then
     * allows the user to enter N names along with three numbers.
     * When user has finished entering data, the names and the numbers associated with each name
     * are printed in a formatted table, along with the average of those numbers.
     */
    public static void main(String[] args) {

        /*
         * USEFUL CONSTANTS
         */
        final String CORRECT_USAGE = """
                Correct usage:
                    java Average N <enter>
                    Name1 a1 b1 c1 <enter>
                    Name2 a2 b2 c2 <enter>
                    ...
                    NameN aN bN cN <enter>
                    <enter>
                where
                    - n is a positive integer denoting how many lines of data to expect.
                    - Name[1...N] is the name compute the average for
                    - a[1...N], b[1...N], c[1...N] are the numbers to average for Name""";

        // number of integer data points to collect per row of data
        final int DATA_POINTS_PER_ROW = 3;
        // formatting
        final String COLUMN_SPACER = "  ";

        /*
         * PARSE COMMAND LINE PARAMETER
         */
        // validate that command-line input has been provided
        if (args == null || args.length != 1) {
            System.out.println(
                    "No number provided.\n" + CORRECT_USAGE);
            return;
        }
        // number of names to collect data for
        int n;
        // validate and parse command-line input
        try {
            // validate that command-line argument is actually an integer (will throw exception if not)
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            // --> exit program with error message.
            System.out.println("Invalid number provided.\n" + CORRECT_USAGE);
            return;
        }


        /*
         * PREPARE TO ACCEPT USER INPUT
         */
        // array of names
        String[] names = new String[n];

        // 2d-array of data points
        float[][] data = new float[n][DATA_POINTS_PER_ROW];

        // array of averages for each name
        float[] averages = new float[n];

        // score-keepers to format columns later
        int maxNameLength = 0;
        int maxDigits = 0;

        /*
         * ACCEPT AND PARSE USER INPUT
         */
        int row = 0; // array index for data arrays
        while (true) {
            // read from keyboard
            String input = StdIn.readLine();

            // if they enter a blank line and have entered all data...
            if (input.isEmpty() && row >= n) { // ...user is finished
                break;
            }

            // parse raw input
            String[] parsed = input.split(" ");

            // if they typed it wrong...
            if (parsed.length != DATA_POINTS_PER_ROW + 1) {  // +1 for name field
                // ...exit program with error
                // seems a bit harsh, but life isn't always fair
                System.out.println("Invalid input provided.\n" + CORRECT_USAGE);
                return;
            }
            if (row >= n) { // they typed valid data, but we're no longer collecting
                continue;
            }
            // get name (just taking whatever they put here)
            names[row] = parsed[0].trim();
            if (names[row].length() > maxNameLength) {
                maxNameLength = names[row].length();
            }

            // running tally of data items per name
            float tally = 0;
            for (int j = 0; j < DATA_POINTS_PER_ROW; j++) {
                try {
                    // validates that data point is an int
                    int c = Integer.parseInt(parsed[j + 1].trim());
                    int w = getIntegerDigitCount(c);
                    if (w > maxDigits) maxDigits = w;
                    data[row][j] = c;
                    tally += c;
                } catch (NumberFormatException ex) {
                    // --> exit program with error message.
                    System.out.println("Invalid number provided.\n" + CORRECT_USAGE);
                    return;
                }
            }
            averages[row] = tally / (float) DATA_POINTS_PER_ROW;

            // only increment counter when they've input n rows
            row++;
        }


        /*
         * OUTPUT RESULTS
         */
        // calculate column format strings
        String nameFormat = "%-" + (maxNameLength) + "s";
        String dataPointFormat = "%" + (maxDigits) + ".0f";
        String averageFormat = "%" + (maxDigits + 3) + ".2f"; // +3 for ".2f"
        // loop over the names
        for (int i = 0; i < n; i++) {
            // name
            System.out.printf(nameFormat, names[i]);
            System.out.print(COLUMN_SPACER);
            // data points (user-input)
            for (int j = 0; j < DATA_POINTS_PER_ROW; j++) {
                System.out.printf(dataPointFormat, data[i][j]);
                System.out.print(COLUMN_SPACER);
            }
            // average
            System.out.printf(averageFormat, averages[i]);
            System.out.print(COLUMN_SPACER);
            System.out.println();
        }


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
            // log base 10 of a positive integer, rounded up, is the number of digits in that integer
            return (int) (Math.ceil(Math.log10(num)));
        } else if (num < 0) {
            // round up log-10 of absolute value and add a digit for sign
            return (int) (Math.ceil(Math.log10(Math.abs(num))) + 1);
        } else {
            // zero is one digit
            return 1;
        }
    }
}
