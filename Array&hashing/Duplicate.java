
import java.util.HashSet;

class Duplicate{
  public boolean containsDuplicate(int[] nums){

    /*BRUTE FORCE APPROACH */

    // int  n = nums.length;
    // for(int i=0;i<n-1;i++){
    //   for(int j=i+1;j<n;j++){
    //     if(nums[i] == nums[j]){
    //       return true;
    //     }
    //   }
    // }
    // return false;

    /*SORTING APPROACH */

    // Arrays.sort(nums);
    // int n = nums.length;
    // for(int i = 1; i<n;i++){
    //   if(nums[i] == nums[i-1])
    //     return true;
    // }
    // return false;

    /*HASH SET APPROACH */
    HashSet<Integer> seen = new HashSet<>();
    for(int num: nums){
      if(seen.contains(num)){
        return true;
      }
      seen.add(num);
    }
    return false;
  }

  public static void main(String[] args) {
      Duplicate d = new Duplicate();
      int nums[] = {1,2,6,5};
      System.out.println(d.containsDuplicate(nums));
  }
}