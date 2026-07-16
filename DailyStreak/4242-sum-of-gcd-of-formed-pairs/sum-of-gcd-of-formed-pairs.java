class Solution {
    public long gcdSum(int[] nums) {
        int[] prefixGCD = new int[nums.length];
        long sumGcd = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            prefixGCD[i] = getGcd(max, nums[i]);
        }

        Arrays.sort(prefixGCD);

        for (int i = 0, j = prefixGCD.length - 1; i < j; i++, j--) {
            sumGcd += getGcd(prefixGCD[i], prefixGCD[j]);
        }

        return sumGcd;
    }

    private int getGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}