class Solution {
    public int minScore(int n, int[][] roads) {
        int[] root = new int[n+1];

        for(int i=0;i<=n;i++){
            root[i] = i;
        }

        for(int[] road : roads){
            int x = road[0];
            int y = road[1];
            root[find(root,x)] = find(root,y);
        }

        int res = Integer.MAX_VALUE;
        int group = find(root,1);

        for(int[] road : roads){
            if(find(root,road[0]) == group){
                res = Math.min(res,road[2]);
            }
        }
        return res;
    }
    private int find(int[] root,int x){
        if(root[x] != x){
            root[x] = find(root,root[x]);
        }
        return root[x];
    }
}