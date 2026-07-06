int cmp(const void *a, const void *b){
    int *arr1 = *(int**)a;
    int *arr2 = *(int**)b;

    if(arr1[0] == arr2[0]){
        return arr2[1]-arr1[1];
    }
    return arr1[0] - arr2[0];
}


int removeCoveredIntervals(int** intervals, int intervalsSize, int* intervalsColSize) {
    qsort(intervals,intervalsSize,sizeof(int*),cmp);
    int ret = intervalsSize;
    int k = INT_MIN;
    for(int i=0;i<intervalsSize;i++){
        if(k < intervals[i][1]){
            k = intervals[i][1];
        }else{
            ret--;
        }
    }
    return ret;
}