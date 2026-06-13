import java.util.Arrays;

public class Solution {

    // public int[] getConcatenation(int[] nums) {
    //     int[] ans = new int[2 * nums.length];
    //     int idx = 0;

    //     for (int i = 0; i < 2; i++) {
    //         for (int num : nums) {
    //             ans[idx++] = num;
    //         }
    //     }

    //     return ans;
    // }

    public int[] getConcatenation(int[] nums){
      int n = nums.length;
      int ans[] = new int[2*n];
      for(int i=0;i<n;i++){
        ans[i] = ans[i+n]=nums[i];
      }
      return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {1, 2, 3};

        int[] result = sol.getConcatenation(nums);

        System.out.println(Arrays.toString(result));
    }
}