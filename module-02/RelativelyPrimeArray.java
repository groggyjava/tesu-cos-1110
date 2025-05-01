/**
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #2: RelativelyPrimeArray
 * 
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class RelativelyPrimeArray {
  /**
   * 1. Accepts single number, n, formatted as integer, as command-line input.
   * 2. Creates a n x n 2d array of type boolean and sets the value of each
   * array element [i,j]
   * - True, if i & j are relatively prime (have no common factors).
   * - False, if not.
   * 3. Generates formatted output displaying T or F for each array element [i,j],
   * leaving blank space for where i == j.
   * 
   * Example:
   * 1 2 3 4 5 6 7 8 9
   * 1 T T T T T T T T
   * 2 T T F T F T F T
   * 3 T T T T F T T F
   * 4 T F T T F T F T
   * 5 T T T T T T T T
   * 6 T F F F T T F F
   * 7 T T T T T T T T
   * 8 T F T F T F T T
   * 9 T T F T T F T T
   *
   * 
   * @param args numbers to be tested.
   */
  public static void main(String[] args) {

    final int COLUMN_SPACING = 2;
    final char PADDING_CHARACTER = ' ';

    // validate that command-line input has been provided
    if (args == null || args.length == 0) {
      // no command-line input provided
      // --> exit program with error message.
      System.out.println(
          "No command-line parameters provided.\n"
              + "Correct usage: java RelativelyPrimeArray [n], where n is a valid integer.");
      return;
    }

    // use this variable to hold the size of the array to generate.
    int n = 0;

    // validate that command-line input is indeed an integer
    try {
      // this will fail if the first command-line parameter
      // is not actually an integer
      n = Integer.parseInt(args[0]);
    } catch (NumberFormatException ex) {
      // first command-line parameter is not a valid integer.
      // --> exit program with error message.
      System.out.println(
          "Command-line parameter not a valid integer..\n"
              + "Correct usage java: RelativelyPrimeArray [n], where n is a valid integer.");

      // this may be overkill
      System.out.println("Exception message: " + ex.getMessage());
      return;
    }

    // instaniate 2d array large enough to hold
    // every pair of numbers 1..n
    // NOTE: only using n+1 because assignment.
    boolean[][] rsa = new boolean[n + 1][n + 1];

    /*
     * Populate Array
     */
    // use nested loop to populate array
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        // i & j are incremented from 1..n for use as numeric values.
        rsa[i][j] = AreRelativelyPrime(i, j);
      }
    }
    int columnWidth = getIntegerDigitCount(n) + COLUMN_SPACING;

    /*
     * Output Array
     */
    // Blank space for row indices
    System.out.print(getPaddedString(PADDING_CHARACTER, columnWidth));

    // Column headers for 1..n
    for (int i = 1; i <= n; i++) {
      System.out.print(getPaddedString(i, PADDING_CHARACTER, columnWidth));
    }
    System.out.println();

    // Loop over rows, 0..n-1
    for (int i = 1; i <= n; i++) {
      System.out.print(getPaddedString(i, PADDING_CHARACTER, columnWidth));
      for (int j = 1; j <= n; j++) {
        if (i == j) {
          // print blank where i == j
          System.out.print(getPaddedString(PADDING_CHARACTER, columnWidth));
        } else {
          System.out.print(getPaddedString(rsa[i][j], PADDING_CHARACTER, columnWidth));
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
   * @return Whether these two integers are realtively prime.
   */
  private static boolean AreRelativelyPrime(int a, int b) {
    // Find greatest common divisor (GCD) of a and b
    // using Euclidean algo which works on the observation
    // that where a > b:
    // the GCD of a and b is the same as the GCD for b and a & b.
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
   * 
   * @param num Integer to get digit count for.
   * @return Digit count
   */
  private static int getIntegerDigitCount(int num) {
    if (num == 0) {
      return 1;
    } else {
      return (int) (Math.log10(num) + 1);
    }
  }

  /**
   * Creates a padded string from an integer.
   * Example:
   * getPaddedString(2125, '.', 8)
   * returns "....2125"
   *
   * @param num Integer used to create padded string
   * @param p   Character used for padding
   * @param w   Desired string width
   * @return Right-justified, padded string
   */
  private static String getPaddedString(int num, char p, int w) {
    // get digit count of number
    int numWidth = getIntegerDigitCount(num);

    // check against programmer error
    if (numWidth > w)
      throw new IllegalArgumentException("Number exceeds maximum width.");

    // create padded string
    String padded = String.valueOf(p).repeat(w - numWidth) + num;

    //
    return (padded);
  }

  /**
   * Creates a padded string from an boolean value.
   * 
   * @param bool Boolean used to create padded string
   * @param p    Character used for padding
   * @param w    Desired string width
   * @return Right-justified, padded string
   */
  private static String getPaddedString(boolean bool, char p, int w) {
    // create padded string
    return String.valueOf(p).repeat(w - 1) + (bool ? 'T' : 'F');
  }

  /**
   * Returns a string filled nothing but the padding char
   * 
   * @param p Character used for padding
   * @param w Desired string width
   * @return Right-justified, padded string
   */
  private static String getPaddedString(char p, int w) {
    return String.valueOf(p).repeat(w);
  }
}
