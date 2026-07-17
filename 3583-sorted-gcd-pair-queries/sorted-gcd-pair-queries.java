class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for(int x:nums) max = Math.max(max,x);

        int[] freq = new int[max+1];
        for(int x : nums) freq[x]++;

        long[] multiples = new long[max+1];
        for(int g = max; g >= 1; g--){
            long cnt = 0;
            for(int m = g;m<=max;m+=g){
                cnt += freq[m];
            }
            multiples[g] = cnt;
        }

        long[] exact = new long[max+1];
        for(int g = max; g>=1;g--){
            long total = multiples[g] * (multiples[g]-1) / 2;
            for(int m = 2*g; m<=max;m+=g){
                total -= exact[m];
            }
            exact[g] = total;
        }

        long[] pref = new long[max+1];
        for(int g=1;g<=max;g++){
            pref[g] = pref[g-1] + exact[g];
        }

        int[] ans = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            long k = queries[i]+1;
            int low = 1;
            int high = max;
            while(low<high){
                int mid = (low+high) >>> 1;
                if(pref[mid] >= k) high = mid;
                else low = mid + 1;
            }
            ans[i] = low;
        }
        return ans;
    }
}