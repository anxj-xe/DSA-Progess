class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        int start=0,maxlen=1;
        for(int i=0;i<len;i++){
            int len1 = palin(s,i,i);
            int len2 = palin(s,i,i+1);
            int len3 = Math.max(len1,len2);
            while(len3>maxlen){
                maxlen=len3;
                start=i-(len3-1)/2;
            }
        }
        return s.substring(start,start+maxlen);

        // int i=0,j=1;
        // while(i<s.length() || j<s.length()){
        //     if(isPal(s.substring(i,j))){
        //         j++;
        //     }else{
        //         i++;
        //         j++;
        //     }
        // }
        // return s.substring(i,j+1);
    }
    int palin(String s,int start,int end){
        while(start>=0 && end<s.length() && s.charAt(start)==s.charAt(end)){
            start--;
            end++;
        }
        return end-start-1;
    }

    // boolean isPal(String s){
    //     if(s==null) return false;
    //     String revs = new StringBuilder(s).reverse().toString();
    //     return s.equalsIgnoreCase(revs);
    // }
}