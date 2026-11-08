class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        ArrayList<Integer> ans = new ArrayList<>();
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]==nums2[j]){
                ans.add(nums1[i++]);
                j++;
            }else if(nums1[i]>nums2[j]) j++;
            else i++;
        }
        int[] res = new int[ans.size()];
        int k=0;
        for(int num: ans) res[k++] = num;
        return res;

        //CANT THINK
        /*
        HashSet<Integer> set = new HashSet<>();
        for(int num1: nums1) set.add(num1);
        // HashSet<Integer> get = new HashSet<>();
        ArrayList<Integer> get = new ArrayList<>();
        for(int num2: nums2){
            if(set.contains(num2)) get.add(num2);
        }
        int[] res = new int[get.size()];
        int i=0;
        for(int num: get) res[i++] = num;
        return res;
        */
    }
}