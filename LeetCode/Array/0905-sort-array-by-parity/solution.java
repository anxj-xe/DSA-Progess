class Solution {
    public int[] sortArrayByParity(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        int i=0,j=nums.length-1;
        while(i<j){
            if(nums[i]%2==0) i++;
            else{
                if(nums[j]%2!=0) j--;
                else{
                    swap(nums,i,j);
                    i++;j--;
                }
            }
        }
        return nums;

        // while(i<nums.length && nums[i]%2==0) arr.add(nums[i]);
        // while(j<nums.length && nums[j]%2!=0) arr.add(nums[i]);
        // int[] ans = new int[arr.size()];
        // for(int k=0;k<ans.length;k++) ans[k] = arr.get(k);
        // return ans;
    }
    void swap(int[] arr,int start,int end){
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }
}