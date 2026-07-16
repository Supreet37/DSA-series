#include <stdlib.h>

int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

int compare(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

long long gcdSum(int* nums, int numsSize) {
    int* prefixGCD = (int*)malloc(sizeof(int) * numsSize);

    long long sumGcd = 0;
    int max = nums[0];

    for (int i = 0; i < numsSize; i++) {
        if (nums[i] > max)
            max = nums[i];
        prefixGCD[i] = gcd(max, nums[i]);
    }

    qsort(prefixGCD, numsSize, sizeof(int), compare);

    int i = 0, j = numsSize - 1;
    while (i < j) {
        sumGcd += gcd(prefixGCD[i], prefixGCD[j]);
        i++;
        j--;
    }

    free(prefixGCD);
    return sumGcd;
}