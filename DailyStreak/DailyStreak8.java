/* 
1840. Maximum Building Height
Pattern:
Greedy + Constraint Propagation + Peak Calculation
Core Observation:
1. Building 1 must have height 0.
2. Adjacent buildings can differ by at most 1.
3. Therefore, if building A has height h,
   then building B at distance d can have at most h + d.
---------------------------------------------------
Step 1: Sort restrictions by building index
---------------------------------------------------
We process buildings from left to right.
Example:
restrictions = [[2,1],[5,3],[7,4]]
Sorting helps propagate constraints efficiently.
---------------------------------------------------
Step 2: Left → Right Constraint Propagation
---------------------------------------------------
If previous restricted building is:
(idx, h)
Then current building x can have at most:
h + (x - idx)
because height can increase only by 1 per step.
Therefore:
restriction[x] =
min(originalRestriction,
    h + distance)
This removes impossible large heights.
---------------------------------------------------
Step 3: Right → Left Constraint Propagation
---------------------------------------------------
Similar logic from the opposite side.
If right building has height hr,
then current building can be at most:
hr + distance
Update:
currentHeight =
min(currentHeight,
    rightHeight + distance)
Now every restriction is globally valid.
After these two passes,
all restrictions satisfy the adjacent-difference rule.
---------------------------------------------------
Step 4: Find Maximum Peak Between Restrictions
---------------------------------------------------
Consider two valid restriction points:
(idx1, h1)
(idx2, h2)
distance = idx2 - idx1
We can increase height by 1 each step
until reaching a peak,
then decrease by 1 to meet the second restriction.
Example:
h1 = 2
h2 = 4
distance = 6
      peak
       ^
2 /\/\/\/\ 4
Available extra steps:
steps =
distance - |h2 - h1|
Remaining steps are used symmetrically:
extraPeak = steps / 2
Therefore:
peakHeight =
max(h1,h2) + steps/2
Update answer with this peak.
---------------------------------------------------
Step 5: Handle Buildings After Last Restriction
---------------------------------------------------
Suppose last restriction:
(idx, h)
Buildings after it can keep increasing
by 1 until building n.
Maximum possible:
h + (n - idx)
Update answer.
---------------------------------------------------
Example
---------------------------------------------------
n = 5
restrictions = [[2,1],[4,1]]
After propagation:
(1,0)
(2,1)
(4,1)
Between 2 and 4:
distance = 2
|1-1| = 0
steps = 2
peak = 1 + 2/2
     = 2
Answer = 2
---------------------------------------------------
Time Complexity
---------------------------------------------------
Sorting restrictions:
O(m log m)
Left pass:
O(m)
Right pass:
O(m)
Peak calculation:
O(m)
Total:
O(m log m)
where m = restrictions.length
---------------------------------------------------
Space Complexity
---------------------------------------------------
O(1) extra space
(excluding sorting space used internally)
---------------------------------------------------
Interview One-Liner
---------------------------------------------------
Sort restrictions, propagate valid maximum heights
from both directions, then compute the highest
achievable peak between every pair of restricted
buildings using the remaining distance.
*/





import java.util.Arrays;

public class DailyStreak8 {

    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0) {
            return n - 1;
        }

        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        int idx = 1, h = 0;

        // Left -> Right pass
        for (int i = 0; i < restrictions.length; i++) {
            int x = restrictions[i][0];
            int y = restrictions[i][1];

            restrictions[i][1] = Math.min(y, x - idx + h);

            idx = x;
            h = restrictions[i][1];
        }

        // Right -> Left pass
        for (int i = restrictions.length - 2; i >= 0; i--) {
            restrictions[i][1] = Math.min(
                restrictions[i][1],
                restrictions[i + 1][1] + restrictions[i + 1][0] - restrictions[i][0]
            );
        }

        int res = n - restrictions[restrictions.length - 1][0]
                + restrictions[restrictions.length - 1][1];

        idx = 1;
        h = 0;

        // Calculate maximum peak
        for (int[] r : restrictions) {
            int x = r[0];
            int y = r[1];

            int steps = x - idx - Math.abs(y - h);
            int higher = Math.max(y, h);

            res = Math.max(res, higher + steps / 2);

            idx = x;
            h = y;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] restrictions = {
            {5, 3},
            {2, 5},
            {7, 4},
            {10, 3}
        };

        System.out.println(new DailyStreak8().maxBuilding(n, restrictions));
    }
}