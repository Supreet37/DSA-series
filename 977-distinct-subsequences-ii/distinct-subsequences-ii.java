class Solution {
    public int distinctSubseqII(String s) {
        int MOD = (int)1e9 + 7;

        long[] count = new long[26];
        long sum = 0;

        for (char c : s.toCharArray()) {
            long total = (1 + sum) % MOD;
            int idx = c - 'a';

            sum = (sum + total - count[idx] + MOD) % MOD;
            count[idx] = total;
        }

        return (int)sum;
    }
}