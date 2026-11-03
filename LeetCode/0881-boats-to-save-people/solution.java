class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left=0,right=people.length-1;
        int boat = 0;
        while(left<=right){
           if(people[left]+people[right]<=limit) left++;
           right--;
           boat++; 
        }
        return boat;


        // int i=0,count=0;
        // while(i<people.length-1){
        //     if(people[i]+people[i+1]>limit){
        //         count++;
        //         i++;
        //     }else if(people[i]+people[i+1]==limit){
        //         count++;
        //         i+=2;
        //     }else i++;
        //     while(i<people.length){
        //         count++;
        //         i++;
        //     }
        // }
        // return count;
    }
}