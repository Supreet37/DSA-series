class Solution {
    public int numberOfSubstrings(String s) {
        int[] arr = new int[3];
        int ans = 0;
        int left = 0;

        for(char ch : s.toCharArray()){
            arr[ch-'a']++;

            while(arr[0] > 0 && arr[1] > 0 && arr[2] > 0){
                arr[s.charAt(left++) - 'a']--;
            }
            ans += left;
        }
        return ans;
    }
}