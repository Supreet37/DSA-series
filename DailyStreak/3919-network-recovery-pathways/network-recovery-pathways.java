import java.util.*;

class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;
        int m = edges.length;

        int[] head = new int[n];
        Arrays.fill(head, -1);

        int[] next = new int[m];
        int[] to = new int[m];
        int[] wt = new int[m];

        int idx = 0;
        int low = Integer.MAX_VALUE;
        int high = 0;

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            if (!online[u] || !online[v]) continue;

            to[idx] = v;
            wt[idx] = w;
            next[idx] = head[u];
            head[u] = idx++;

            low = Math.min(low, w);
            high = Math.max(high, w);
        }

        if (!online[0] || !online[n - 1]) return -1;

        if (low == Integer.MAX_VALUE) return -1;

        if (!possible(low, head, next, to, wt, n, k))
            return -1;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (possible(mid, head, next, to, wt, n, k))
                low = mid;
            else
                high = mid - 1;
        }

        return low;
    }

    private boolean possible(int limit, int[] head, int[] next,
                             int[] to, int[] wt, int n, long k) {

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {

            long[] cur = pq.poll();

            int u = (int) cur[0];
            long d = cur[1];

            if (d != dist[u]) continue;

            if (u == n - 1) return true;

            for (int e = head[u]; e != -1; e = next[e]) {

                if (wt[e] < limit) continue;

                int v = to[e];
                long nd = d + wt[e];

                if (nd <= k && nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{v, nd});
                }
            }
        }

        return false;
    }
}