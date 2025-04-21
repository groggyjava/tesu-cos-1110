/**
 * COS-1110: INTRODUCTION TO PROGRAMMING
 * Assignment #1: LC
 * 
 * Student: Ken Ehrman (kenneth.ehrman@students.tesu.edu)
 */
public class LC {
  /**
   * Prints the integers from 1 to 100 with ten integers per line.
   *
   * @param args ignored.
   */
  public static void main(String[] args) {

    // main for-loop uses iterator varable to step through
    // the integers 1 to 100, separated by spaces.
    for (int i = 1; i <= 100; i++) {
      System.out.print(i + "  ");

      // use modulo operator to test whether iterator is currently a multiple of 10.
      if (i % 10 == 0) {

        // iterator is multiple of 10, so start a new line
        System.out.println();
      }
    }
  }
}
