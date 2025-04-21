/**
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #1: AndOp
 * 
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class AndOp {

  /**
   * Accepts two numbers, formatted as doubles, as command-line input,
   * and if both are greater than zero and less than one, prints "true"
   * and if not, prints "false".
   *
   * @param args numbers to be tested.
   */
  public static void main(String[] args) {
    // validate that command-line input has been provided
    if (args == null || args.length != 2) {
      // incorrect number of inputs provided -- exit program with error message.
      System.out.println("Incorrect number of command-line parameters provided.");
      System.out.println("Correct usage java AndOp [x] [y], where x and y are valid double values.");
      return;
    }

    // variable to hold command-line parameters
    double x, y;

    // validate that command-line input is indeed an integer
    try {
      // this will fail if the first command-line parameter
      // is not actually an integer
      x = Double.parseDouble(args[0]);
      y = Double.parseDouble(args[1]);

    } catch (NumberFormatException ex) {
      // first command-line parameter is not a valid integer. -- exit program with
      // error message.
      System.out.println("One or more Command-line parameters not a valid integer.");
      System.out.println("Correct usage java AndOp [x] [y], where x and y are valid double values.");

      // this may be overkill
      System.out.println("Exception message: " + ex.getMessage());
      return;
    }

    if (x > 0 && x < 1 && y > 0 && y < 1) {
      System.out.println("true");
    } else {
      System.out.println("false");
    }
  }
}