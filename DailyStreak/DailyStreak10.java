/*
1189. Maximum Number of Balloon
IDEA:
To form one "balloon", we need:
b = 1
a = 1
l = 2
o = 2
n = 1
Count occurrences of these characters in text.
Since 'l' and 'o' are needed twice, divide their counts by 2.
Answer = minimum among:
b, a, n, l/2, o/
ALGORITHM
1. Initialize counters:
   b, a, l, o, n = 0
2. Traverse every character in text:
   - increment corresponding counter
3. Compute:
   min(b, a, n, l/2, o/2)
4. Return resul
TIME COMPLEXITY:
O(n)
SPACE COMPLEXITY:
O(1)
*/



// Run directly in VS Code

public class DailyStreak10 {

    static class Solution {
        public int maxNumberOfBalloons(String text) {

            int b = 0;
            int a = 0;
            int l = 0;
            int o = 0;
            int n = 0;

            for (char c : text.toCharArray()) {
                switch (c) {
                    case 'b': b++; break;
                    case 'a': a++; break;
                    case 'l': l++; break;
                    case 'o': o++; break;
                    case 'n': n++; break;
                }
            }

            return Math.min(
                    b,
                    Math.min(
                            a,
                            Math.min(
                                    n,
                                    Math.min(l / 2, o / 2)
                            )
                    )
            );
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.maxNumberOfBalloons("nlaebolko"));          // 1
        System.out.println(sol.maxNumberOfBalloons("loonbalxballpoon"));   // 2
        System.out.println(sol.maxNumberOfBalloons("leetcode"));           // 0
    }
}