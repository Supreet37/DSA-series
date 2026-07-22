import java.util.*;

class Solution {

    static class Run {
        int c, a, b, d;
        long f;
        Run(int c, int a, int b, int d, long f) {
            this.c = c; this.a = a; this.b = b; this.d = d; this.f = f;
        }
    }

    static class SegTree {
        long[] tree;
        int n;
        final long NEG = Long.MIN_VALUE / 4;

        SegTree(int n) {
            this.n = n;
            tree = new long[n * 4];
            Arrays.fill(tree, NEG);
        }

        void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, long val) {
            if (l == r) { tree[node] = Math.max(tree[node], val); return; }
            int mid = (l + r) >>> 1;
            if (idx <= mid) update(node * 2, l, mid, idx, val);
            else update(node * 2 + 1, mid + 1, r, idx, val);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        long query(int L, int R) {
            if (L > R) return NEG;
            return query(1, 0, n - 1, L, R);
        }

        private long query(int node, int l, int r, int L, int R) {
            if (L <= l && r <= R) return tree[node];
            if (r < L || l > R) return NEG;
            int mid = (l + r) >>> 1;
            return Math.max(query(node * 2, l, mid, L, R), query(node * 2 + 1, mid + 1, r, L, R));
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int totalOnes = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == '1') totalOnes++;

        // build blocks
        List<int[]> blocks = new ArrayList<>(); // {start, end, val}
        int i = 0;
        while (i < n) {
            int j = i;
            char ch = s.charAt(i);
            while (j + 1 < n && s.charAt(j + 1) == ch) j++;
            blocks.add(new int[]{i, j, ch - '0'});
            i = j + 1;
        }

        int m = blocks.size();
        int[] posToBlock = new int[n];
        for (int bi = 0; bi < m; bi++) {
            int[] bl = blocks.get(bi);
            for (int p = bl[0]; p <= bl[1]; p++) posToBlock[p] = bi;
        }

        // runs: 1-block flanked by 0-blocks on both sides -> used for "fully contained" case via segment tree
        List<Run> runs = new ArrayList<>();
        for (int k = 1; k + 1 < m; k++) {
            int[] cur = blocks.get(k);
            if (cur[2] != 1) continue;
            if (blocks.get(k - 1)[2] != 0 || blocks.get(k + 1)[2] != 0) continue;
            int c = blocks.get(k - 1)[0];
            int a = cur[0];
            int b = cur[1];
            int d = blocks.get(k + 1)[1];
            long gain = (long) (a - c) + (d - b);
            runs.add(new Run(c, a, b, d, gain));
        }

        int q = queries.length;
        long[] bestGain = new long[q];

        // ---------- CASE 1: run fully contained in [l, r] (c>=l and d<=r) ----------
        {
            Integer[] ordRuns = new Integer[runs.size()];
            for (int t = 0; t < runs.size(); t++) ordRuns[t] = t;
            Arrays.sort(ordRuns, Comparator.comparingInt(x -> runs.get(x).d));

            Integer[] ordQ = new Integer[q];
            for (int t = 0; t < q; t++) ordQ[t] = t;
            Arrays.sort(ordQ, Comparator.comparingInt(x -> queries[x][1]));

            SegTree seg = new SegTree(Math.max(n, 1));
            int ptr = 0;

            for (int id : ordQ) {
                int r = queries[id][1];
                int l = queries[id][0];

                while (ptr < ordRuns.length && runs.get(ordRuns[ptr]).d <= r) {
                    Run run = runs.get(ordRuns[ptr++]);
                    seg.update(run.c, run.f);
                }

                long val = seg.query(l, n - 1);
                if (val > bestGain[id]) bestGain[id] = val;
            }
        }

        // ---------- CASE 2 & 3: partial merges via direct block lookup ----------
        for (int id = 0; id < q; id++) {
            int l = queries[id][0];
            int r = queries[id][1];

            // From l: is l strictly inside a "Z1" zero block (left flank of some run)?
            {
                int bi = posToBlock[l];
                int[] bl = blocks.get(bi);
                if (bl[2] == 0 && l > bl[0] && bi + 2 < m) {
                    int[] onesB = blocks.get(bi + 1);
                    int[] rightB = blocks.get(bi + 2);
                    if (onesB[2] == 1 && rightB[2] == 0) {
                        int a = onesB[0], b = onesB[1], d = rightB[1];
                        long gain;
                        if (d <= r) {
                            gain = (long) (a - l) + (d - b); // full right
                        } else if (b < r) {
                            gain = (long) (a - l) + (r - b); // partial right (case4)
                        } else {
                            gain = Long.MIN_VALUE / 4; // r <= b, invalid
                        }
                        if (gain > bestGain[id]) bestGain[id] = gain;
                    }
                }
            }

            // From r: is r strictly inside a "Z2" zero block (right flank of some run)?
            {
                int bj = posToBlock[r];
                int[] br = blocks.get(bj);
                if (br[2] == 0 && r < br[1] && bj - 2 >= 0) {
                    int[] onesB = blocks.get(bj - 1);
                    int[] leftB = blocks.get(bj - 2);
                    if (onesB[2] == 1 && leftB[2] == 0) {
                        int a = onesB[0], b = onesB[1], c = leftB[0];
                        long gain;
                        if (l <= c) {
                            gain = (long) (a - c) + (r - b); // full left
                        } else if (c < l && l < a) {
                            gain = (long) (a - l) + (r - b); // partial left (case4)
                        } else {
                            gain = Long.MIN_VALUE / 4; // l >= a, invalid
                        }
                        if (gain > bestGain[id]) bestGain[id] = gain;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>(q);
        for (int idx = 0; idx < q; idx++) {
            long res = totalOnes + Math.max(0L, bestGain[idx]);
            ans.add((int) res);
        }
        return ans;
    }
}