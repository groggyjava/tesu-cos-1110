/*
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #1: RanNumGen
 * 
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class RanNumGen {

  // using these constants to contrain our app
  static final int MAX_RAN_VALUE = 100;
  static final int MIN_RAN_VALUE = 1;

  /**
   * Accepts single number, formatted as integer, and genrates that
   * many random integers between the values 1 and 100, inclusive,
   * printing each separated by spaces.
   *
   * When complete, it prints the value of the minimum and maximum
   * random numbers generated.
   * 
   * @param args number of random numbers to be generated.
   */
  public static void main(String[] args) {

    // validate that command-line input has been provided
    if (args == null || args.length == 0) {
      // no command-line input provided -- exit program with error message.
      System.out.println(
          "No command-line parameters provided.\nCorrect usage java RanNumGen [n], where n is a valid integer.");
      return;
    }

    // use this variable to hold the number of random #'s to generate.
    // n is the traditional variable name for the count of data items
    // being considered (in this case, generated) by a program/algorithm.
    int n = 0;

    // validate that command-line input is indeed an integer
    try {
      // this will fail if the first command-line parameter
      // is not actually an integer
      n = Integer.parseInt(args[0]);
    } catch (NumberFormatException ex) {
      // first command-line parameter is not a valid integer. -- exit program with
      // error message.
      System.out.println(
          "Command-line parameter not a valid integer..\nCorrect usage java RanNumGen [n], where n is a valid integer.");

      // this may be overkill
      System.out.println("Exception message: " + ex.getMessage());
      return;
    }

    // before generating all the values requested,
    // get first random value and create score-keeping variables.
    // note: the first random number will be both the minimum and maximum value.
    int rand = getRandomIntegerInRange(MIN_RAN_VALUE, MAX_RAN_VALUE);
    int maximum = rand;
    int minimum = maximum;

    // note: using n - 1 as limiting condition because
    // first random value was already generated
    for (int i = 0; i < n - 1; i++) {
      System.out.print(rand + "    ");
      rand = getRandomIntegerInRange(MIN_RAN_VALUE, MAX_RAN_VALUE);
      if (rand > maximum) {
        maximum = rand;
      }
      if (rand < minimum) {
        minimum = rand;
      }
    }
    System.out.println();

    // report results.
    System.out.println("The minimum value is " + minimum);
    System.out.println("The maximum value is " + maximum);
  }

  /**
   * Returns a random integer which is greater than or equal to some minimum value
   * and less than or equal to some maximum value. (i.e. inclusive)
   * 
   * @param min minimum integer value allowed
   * @param max maximum integer value allowed
   * @return Random integer
   */
  private static int getRandomIntegerInRange(int min, int max) {

    // generate raw random value (double between 0..1)
    double raw = Math.random();

    // calculate the range of values as if the minimum were zero.
    int range = max - min + 1; // adding one ensures maximum value is included

    // this maps the random double to integer into that range of values
    // and trims off the decimal portion, effectively rounding down.
    int rand = (int) (raw * range);

    /*
     * adjust random number into actual range of values by adding minimum value
     */
    rand += min;

    // ship out result
    return rand;
  }

}
