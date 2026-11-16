class Solution {
    public boolean judgeSquareSum(int c) {
        long i=0;
        long j=(long) Math.sqrt(c);
        while(i<=j){
            if(square(i,j)==c) return true;
            else if(square(i,j)>c) j--;
            else i++;
        }
        return false;
    }
    long square(long a,long b){
        return a*a+b*b;
    }
}