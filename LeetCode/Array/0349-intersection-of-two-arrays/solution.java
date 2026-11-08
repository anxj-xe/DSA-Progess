class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> seen = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int num1: nums1){
            if(!seen.contains(num1)) seen.add(num1);
        }
        for(int num2: nums2){
            if(seen.contains(num2)){
                ans.add(num2);
                seen.remove(num2);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}