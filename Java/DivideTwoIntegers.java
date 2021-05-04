// https://leetcode.com/problems/divide-two-integers/
// #math #binary-search

class Solution {
  public int divide(int dividend, int divisor) {
    // time O(n), space O(1)
    // return divide1(dividend, divisor);

    // time O((logN)^2), space O(1)
    // return divide2(dividend, divisor);

    // time O((logN)^2), space O(1)
    return divide2Positive(dividend, divisor);

    // time O(logN), space O(logN)
    // return divide3(dividend, divisor);
  }

  // Approach 1: - pure Quotient - O(n)
  public int divide1(int dividend, int divisor) {
    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives
     * for the reasons: each positive integer has a corresponding negative integer (Ex: 3, -3), except for Integer.MIN_VALUE
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
      negatives--;
      dividend = -dividend;
    }
    if (divisor > 0) {
      negatives--;
      divisor = -divisor;
    }

    /* Count how many times the divisor has to be added
     * to get the dividend. This is the quotient. */
    int quotient = 0;
    while (dividend - divisor <= 0) {
      quotient--;
      dividend -= divisor;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
      quotient = -quotient;
    }
    return quotient;
  }

  // Approach 2: binary search - exponential - O((logN)^2)
  private static int HALF_INT_MIN = -1073741824;

  public int divide2(int dividend, int divisor) {
    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives
     * for the reasons explained above.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
      negatives--;
      dividend = -dividend;
    }
    if (divisor > 0) {
      negatives--;
      divisor = -divisor;
    }

    /* Count how many times the divisor has to be added
     * to get the dividend. This is the quotient. */
    int quotient = 0;
    while (divisor >= dividend) {
      int powerOfTwo = -1;
      int value = divisor;
      while (value >= HALF_INT_MIN && value + value >= dividend) {
        value += value;
        powerOfTwo += powerOfTwo;
      }
      quotient += powerOfTwo;
      dividend -= value;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
      quotient = -quotient;
    }
    return quotient;
  }

  public int divide2Positive(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }
    int negative = 0;
    if (dividend < 0) {
      negative++;
      dividend = -dividend;
    }
    if (divisor < 0) {
      negative++;
      divisor = -divisor;
    }

    int half = 1073741823; // (Integer.MAX_VALUE / 2)
    int quotient = 0;
    while (dividend - divisor >= 0) {
      int powerOfTwo = 1;
      int value = divisor;
      // prevent needless overflows from occurring and check
      while (value <= half && value + value <= dividend) {
        powerOfTwo += powerOfTwo;
        value += value;
      }
      quotient += powerOfTwo;
      dividend -= value;
    }

    if (negative == 1) {
      return -quotient;
    }
    return quotient;
  }

  // Approach 3: base on approach 2, use more space to reduce time complexity - O(logN) - O(logN)
  public int divide3(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    int negative = 2;
    if (dividend > 0) {
      negative--;
      dividend = -dividend;
    }
    if (divisor > 0) {
      negative--;
      divisor = -divisor;
    }

    // making a list of doubles of 1 and the divisor
    ArrayList<Integer> doubles = new ArrayList<>();
    ArrayList<Integer> powersOfTwo = new ArrayList<>();
    int powerOfTwo = -1;
    while (divisor >= dividend) {
      doubles.add(divisor);
      powersOfTwo.add(powerOfTwo);
      // prevent needless overflows from occurring
      if (divisor < HALF_INT_MIN) {
        break;
      }
      divisor += divisor;
      powerOfTwo += powerOfTwo;
    }

    int quotient = 0;
    // from the larget double to smallest, check if the current double fits
    for (int i = doubles.size() - 1; i >= 0; i--) {
      if (doubles.get(i) >= dividend) {
        // if it does fit, add the current powerOfTwo to the quotient
        quotient += powersOfTwo.get(i);
        // update divident to take into account the bit we're now removed
        dividend -= doubles.get(i);
      }
    }

    // transform to positive if needed
    if (negative != 1) {
      return -quotient;
    }
    return quotient;
  }
}