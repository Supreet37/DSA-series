/*
3614. Process String with Special Operations II

# 3614. Process String with Special Operations II
#
# Algorithm:
#
# Phase 1: Compute final string length without actually building the string.
#
# For each character c in s:
#   - If c is a lowercase letter:
#         len++
#
#   - If c == '*':
#         Delete last character
#         if len > 0:
#             len--
#
#   - If c == '#':
#         Duplicate current string
#         len = len * 2
#
#   - If c == '%':
#         Reverse operation
#         Length remains unchanged
#
# If k >= len:
#     return '.'
#
# --------------------------------------------------
#
# Phase 2: Traverse from right to left to find
# the kth character without constructing the string.
#
# For i from s.length()-1 to 0:
#
#   1. If current character is a letter:
#         If k == len-1:
#             return current letter
#         len--
#
#   2. If current character is '#':
#         Before duplication length was len/2
#         len = len/2
#         k = k % len
#
#   3. If current character is '%':
#         Reverse operation
#         Mirror the index:
#         k = len - 1 - k
#
#   4. If current character is '*':
#         One character was deleted earlier.
#         Restore previous length:
#         oldLen = len + 1
#
#         If k == oldLen - 1:
#             Deleted character cannot be recovered
#             return '.'
#
#         len = oldLen
#
# Return '.'
#
# --------------------------------------------------
#
# Key Idea:
# Instead of building the huge final string,
# track only its length and reverse the operations
# from right to left to locate the kth character.
#
# Time Complexity : O(n)
# Space Complexity: O(1)
 */

public class DailyStreak5 {
    public char processStr(String s, long k) {
        long len = 0;

        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                len++;
            } else if (c == '*') {
                if (len > 0)
                    len--;
            } else if (c == '#') {
                len *= 2;
            }
        }

        if (k >= len)
            return '.';

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                if (k == len - 1) {
                    return c;
                }
                len--;
            } else if (c == '#') {
                len /= 2;
                k %= len;
            } else if (c == '%') {
                k = len - 1 - k;
            } else {
                long oldLen = len + 1;
                if (k == oldLen - 1) {
                    return '.';
                }
                len = oldLen;
            }
        }
        return '.';
    }

    public static void main(String[] args) {
        DailyStreak5 obj = new DailyStreak5();

        String s = "ab#c";
        long k = 2;

        char result = obj.processStr(s, k);

        System.out.println("Result: " + result);
    }
}