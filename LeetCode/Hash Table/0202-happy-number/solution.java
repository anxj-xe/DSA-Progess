class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> hash = new HashSet<>();
        while(n!=1){
            if(hash.contains(n)) return false;
            hash.add(n);
            n = square(n);
        }
        return true;


        // int slow =n,fast=n;
        // do{
        //     slow=square(slow);
        //     fast=square(square(fast));
        // }while(slow!=fast);
        // return slow==1;
    }
    int square(int num){
        int ans =0;
        while(num>0){
            int rem = num%10;
            ans += rem*rem;
            num/=10;
        }
        return ans;
    }
}