import java.util.HashSet;
import java.util.Set;
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for(int num : nums){
            seen.add(num);
        }

        return nums.length != seen.size();
    }
}