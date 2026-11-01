class Solution {
    public int[] twoSum(int[] nums, int target) {
        // HashMap<Integer,Integer> map = new HashMap<>();
        // for(int i=0;i<nums.length;i++){
        //     int complement = target-nums[i];
        //     if(map.containsKey(complement)) return new int[]{map.get(complement),i};
        //     map.put(nums[i],i);
        // }
        // return new int[]{-1,-1};

        int left = 0,right=nums.length-1;
        HashMap<Integer,Integer> map = new HashMap<>();
        while(left<=right){
            if(map.containsKey(target-nums[left])){
                return new int[]{left,map.get(target-nums[left])};
            }
            map.put(nums[left],left);
            if(map.containsKey(target-nums[right])){
                return new int[]{right,map.get(target-nums[right])};
            }
            map.put(nums[right],right);
            right--;left++;
        }
        return new int[]{-1,-1};
    }
}