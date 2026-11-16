class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans=0;
        for(int k=nums.length-1;k>=2;k--){
            int i=0,j=k-1;
            while(i<j){
                if(nums[i]+nums[j]>nums[k]){
                    ans += j-i;
                    j--;
                }else i++;
            }    
        }
        return ans;


        // int start =1,end=nums.length-1;
        // for(int i=0;i<nums.length;i++){
        //     int var = nums[i];
        //     while(start<end){
        //         if(istrian(var,nums[start],nums[end])){
        //             ans++;
        //         }
        //         start++;end--;
        //     }
        // }
        // return ans;

    }
    // boolean istrian(int a,int b,int c){
    //     if(a+b>c && b+c>a && a+c>b) return true;
    //     return false;
    // }
}