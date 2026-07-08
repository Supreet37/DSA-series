import java.util.List;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long M = 1_000_000_007L;

        // p[i] stores the formed number value up to index i-1
        long[] p = new long[n + 1];
        // q[i] stores the sum of digits up to index i-1
        long[] q = new long[n + 1];
        // w[i] stores the count of non-zero digits up to index i-1
        int[] w = new int[n + 1];
        
        // Precompute powers of 10
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % M;
        }

        // 1. Build prefix arrays, skipping zeros for positional value
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            
            if (digit > 0) {
                p[i + 1] = (p[i] * 10 + digit) % M;
                w[i + 1] = w[i] + 1;
            } else {
                p[i + 1] = p[i]; // Value doesn't change, no base-10 shift
                w[i + 1] = w[i]; // Count of non-zero digits stays same
            }
            q[i + 1] = q[i] + digit; // Zeros add 0, so this is safe
        }

        // 2. Process each query
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            
            // The actual number of non-zero digits in this specific sub-segment
            int nonZeroLen = w[r + 1] - w[l];

            // Strip out the prefix part. Shift by nonZeroLen instead of total length.
            long numVal = (p[r + 1] - (p[l] * pow10[nonZeroLen]) % M + M) % M;
            long sumVal = q[r + 1] - q[l];

            res[i] = (int) ((numVal * (sumVal % M)) % M);
        }
        
        return res;
    }
}
