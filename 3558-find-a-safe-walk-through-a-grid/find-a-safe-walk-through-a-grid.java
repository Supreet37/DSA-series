class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int n = grid.size();
        int m = grid.get(0).size();

        Queue<int[]> q = new LinkedList<>();
        int[][] vis = new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(vis[i],-1);
        }

        health -= grid.get(0).get(0);

        q.offer(new int[]{0,0,health});
        vis[0][0] = health;

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        while(!q.isEmpty()){
            int[] temp = q.poll();

            int r = temp[0];
            int c = temp[1];
            int hp = temp[2];

            if(r == n-1 && c == m-1){
                return true;
            }

            for(int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >=n || nc >= m){
                    continue;
                }

                int left = hp - grid.get(nr).get(nc);

                if(left > 0 && left > vis[nr][nc]){
                    vis[nr][nc] = left;
                    q.offer(new int[]{nr,nc,left});
                }
            }
        }
        return false;
    }
}