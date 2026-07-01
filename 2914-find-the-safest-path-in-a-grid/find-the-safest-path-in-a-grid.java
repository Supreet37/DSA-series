class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        int[][] distance = new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(distance[i],-1);
        }

        Queue<int[]> queue = new LinkedList<>();

        //Put all thieves into the queue
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j) == 1){
                    distance[i][j] = 0;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        //Multi-source BFS
        while(!queue.isEmpty()){
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for(int[] d:dir){
                int newRow = row + d[0];
                int newCol = col + d[1];

                if(newRow >= 0 && newRow < n && newCol >=0 && newCol < n && distance[newRow][newCol] == -1){
                    distance[newRow][newCol] = distance[row][col] + 1;
                    queue.offer(new int[]{newRow,newCol});
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{distance[0][0],0,0});

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int safeness = current[0];
            int row = current[1];
            int col = current[2];

            if(visited[row][col])
            continue;

            visited[row][col] = true;

            if(row == n-1 && col == n-1)
            return safeness;

            for(int[] d : dir){
                int newRow = row + d[0];
                int newCol = col + d[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol]){
                    int newSafeness = Math.min(safeness , distance[newRow][newCol]);
                    pq.offer(new int[]{newSafeness,newRow,newCol});
                }
            }
        }
        return 0;
    }
}