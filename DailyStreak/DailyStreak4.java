
/*
=========================================================
LeetCode 3612 - Process String with Special Operations I
=========================================================

Approach:
- Traverse the string from left to right.
- Maintain a StringBuilder (str2) as the current result.

Operations:
1. Lowercase letter (a-z)
   -> Append it to str2.

2. '*'
   -> Remove the last character from str2 if it exists.

3. '#'
   -> Duplicate the current string.
   Example:
      "abc" -> "abcabc"

4. '%'
   -> Reverse the current string.
   Example:
      "abc" -> "cba"

Dry Run:
Input: "a#b%*"

'a' -> "a"
'#' -> "aa"
'b' -> "aab"
'%' -> "baa"
'*' -> "ba"

Output: "ba"

Time Complexity:
- Append: O(1)
- Delete Last: O(1)
- Duplicate: O(n)
- Reverse: O(n)

Overall: O(n)

Space Complexity:
O(n) for storing the result string.
*/




import java.util.Scanner;

class DailyStreak4 {
    public String processStr(String s) {
        StringBuilder str1 = new StringBuilder(s);
        StringBuilder str2 = new StringBuilder();

        int i = 0;
        while (!str1.isEmpty() && i < str1.length()) {
            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') {
                str2.append(str1.charAt(i));
            } else if (str1.charAt(i) == '*') {
                if (!str2.isEmpty()) {
                    str2.deleteCharAt(str2.length() - 1);
                }
            } else if (str1.charAt(i) == '#') {
                str2.append(str2);
            } else {
                str2.reverse();
            }
            i++;
        }
        return str2.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        DailyStreak4 obj = new DailyStreak4();
        System.out.println(obj.processStr(s));

        sc.close();
    }
}
