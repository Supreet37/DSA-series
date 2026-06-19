/* 
Algorithm / Approach:
 1. The biker starts at altitude = 0.
 2. Maintain two variables:
    - current = current altitude
    - maxAltitude = highest altitude reached so far
 3. Traverse each value in the gain array:
    - Add gain[i] to current altitude.
    - Update maxAltitude = max(maxAltitude, current).
 4. After processing all gains, return maxAltitude.
 Key Idea:
 - gain[i] represents the altitude change between two consecutive points.
 - By continuously adding gains, we can reconstruct each altitude.
 - Track the maximum altitude encountered during traversal.

 Example:
 gain = [-5, 1, 5, 0, -7]

 Start:
 current = 0, maxAltitude = 0

 -5 -> current = -5, maxAltitude = 0
  1 -> current = -4, maxAltitude = 0
  5 -> current =  1, maxAltitude = 1
  0 -> current =  1, maxAltitude = 1
 -7 -> current = -6, maxAltitude = 1

 Answer = 1

 Time Complexity:
 O(n)
 - We traverse the gain array only once.

 Space Complexity:
 O(1)
 - Only two extra variables (current and maxAltitude) are used.

 Pattern:
 Prefix Sum / Running Sum

 Interview Tip:
 Instead of storing all altitudes in another array,
# compute the running altitude on the fly and keep track of the maximum value encountered.
*/


public class DailyStreak7 {
  public int largestAltitude(int[] gain) {
        int max = 0;
        int current = 0;

        for (int g : gain) {
            current = current + g;
            max = Math.max(max, current);
        }

        return max;
    }

    public static void main(String[] args) {
        DailyStreak7 obj = new DailyStreak7();

        int[] gain = {-5, 1, 5, 0, -7};

        int result = obj.largestAltitude(gain);

        System.out.println("Largest Altitude = " + result);
    }
}
