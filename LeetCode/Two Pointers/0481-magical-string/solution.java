class Solution {
    public int magicalString(int n) {
        ArrayList<Integer> arr = new ArrayList<>(List.of(1,2,2));
        if(n<=3) return 1;
        int curr=2,next=1;
        while(arr.size()<n){
            for(int i=0;i<arr.get(curr);i++){
                arr.add(next);
            }
            next = 3-next;
            curr++;
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(arr.get(i)==1) count++;
        }
        return count;
    }
}