class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs,(a,b)->b.length()-a.length());
        int n = strs.length;
        for(int i=0;i<n;i++){
            boolean isUncommon = true;
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(issubseq(strs[i],strs[j])){
                    isUncommon = false;
                    break;
                }
            }
            if(isUncommon) return strs[i].length();
        }
        return -1;
    }
    boolean issubseq(String a,String b){
        int a1 = 0,b1=0;
        while(a1<a.length() && b1<b.length()){
            if(a.charAt(a1)==b.charAt(b1)) a1++;
            b1++;
        }
        return a1==a.length();
    }
}