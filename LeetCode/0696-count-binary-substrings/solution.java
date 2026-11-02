class Solution {
    public int countBinarySubstrings(String s) {
        int len = s.length();
        int count=0,i=1,prev=0,curr=1;
        while(i<len){
            if(s.charAt(i-1)==s.charAt(i)) curr++;
            else{
                count+=Math.min(prev,curr);
                prev=curr;
                curr=1;
            }
            i++;
        }
        return count+=Math.min(prev,curr);
    }
}