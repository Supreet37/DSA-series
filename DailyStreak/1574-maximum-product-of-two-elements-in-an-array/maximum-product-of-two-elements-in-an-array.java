class Solution {
    public int maxProduct(int[] nums) {
        int x1 = Integer.MIN_VALUE;
        int x2 = Integer.MAX_VALUE;

        for(int i : nums){
            if(i > x1){
                x2 = x1;
                x1 = i;
            }else if(i > x2){
                x2 = i;
            }
        }
        return (x1 - 1) * (x2-1);
    }
}