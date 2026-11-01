class Solution {
    public int strStr(String haystack, String needle) {
        return KMP(haystack,needle);
    }
    public int[] lps(String pat){
        int m = pat.length();
        int[] lps = new int[m];
        int prev = 0,i=1;
        while(i<m){
            if(pat.charAt(i)==pat.charAt(prev)) lps[i++] = ++prev;
            else{
                if(prev>0) prev = lps[prev-1];
                else lps[i++] = 0; 
            }
        }
        return lps;
    }
    public int KMP(String text,String pat){
        int n = text.length();
        int m = pat.length();
        if(m==0) return 0;
        if(m>n) return -1;
        int[] lps = lps(pat);
        int i=0,j=0;
        while(i<n){
            if(text.charAt(i)==pat.charAt(j)){
                i++;
                j++;
            }
            if(j==m) return i-m;
            else if(i<n && text.charAt(i)!=pat.charAt(j)){
                if(j>0) j = lps[j-1];
                else i++;
            }
        }
        return -1;
    }
}