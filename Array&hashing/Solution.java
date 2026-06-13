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


/*
This code solves the "Concatenation of Array" problem (LeetCode 1929). The goal is to create a new array that is twice the length of nums, containing the elements of nums repeated twice.
Instead of using a traditional for loop (which is commented out), it uses two System.arraycopy() calls to build the result with optimal performance.
## Step-by-Step Execution Break Down
Assume the input is nums = [1, 2, 3]. The length n is 3.

   1. int arr[] = new int[2*n];
   * Creates a new destination array of size 6.
      * Initial state of arr: [0, 0, 0, 0, 0, 0]
   2. System.arraycopy(nums, 0, arr, 0, n);
   * What it does: Copies all n elements from the beginning of nums into the first half of arr.
      * Parameters: From nums (index 0) $\rightarrow$ to arr (index 0) $\rightarrow$ copy 3 items.
      * State of arr after this line: [1, 2, 3, 0, 0, 0]
   3. System.arraycopy(nums, 0, arr, n, n);
   * What it does: Copies all n elements from nums again, but pastes them into the second half of arr.
      * Parameters: From nums (index 0) $\rightarrow$ to arr (index 3) $\rightarrow$ copy 3 items.
      * State of arr after this line: [1, 2, 3, 1, 2, 3]
   4. return arr;
   * Returns the fully concatenated array.
   
------------------------------
## Why this is better than the commented for loop

* Speed: The for loop requires $N$ iterations, updating two indices individually per loop. System.arraycopy() uses highly optimized native C/C++ memory block copying (memmove), which can copy large blocks of data simultaneously at the hardware layer.
* Readability: It explicitly defines the array setup in two chunks (the first block and the second block) rather than relying on index math tricks inside a loop.

If you are prepping for coding platforms, I can show you how to write the one-line alternative using Arrays.copyOf() or explain how to handle edge cases like null checks. Which direction should we go?


 */