class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int i=0,j=1;
        int count=0;
        while(i<nums.length && j<nums.length){
            if(i==j || nums[j]-nums[i]<k) j++;
            else if(nums[j]-nums[i]>k) i++;
            else{
                count++;
                i++;
                while(i<nums.length && nums[i]==nums[i-1]) i++;
                j= Math.max(j,i+1);
            }
        }
        return count;


        // int j=0;
        // int count=0;
        // for(int i=0;i<nums.length;i++){
        //     while(j<nums.length){
        //         if(i!=j && Math.abs(nums[i]-nums[j])==k){
        //             count++;
        //         }
        //         j++;
        //     }
        //     j=i+1;
        // }
        // return count;
    }
}