class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int maxrad=0,i=0;
        for(int house:houses){
            while(i<heaters.length-1 && Math.abs(heaters[i+1]-house)<=Math.abs(heaters[i]-house)){
                i++;
            }
            maxrad = Math.max(maxrad,Math.abs(heaters[i]-house));
        }
        return maxrad;
    }
}