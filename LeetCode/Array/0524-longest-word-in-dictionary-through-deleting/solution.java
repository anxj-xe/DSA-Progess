class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a,b)-> a.length()!=b.length() ? b.length()-a.length() : a.compareTo(b));
        for(int i =0;i<dictionary.size();i++){
            String currelem = dictionary.get(i);
            if(issubseq(currelem,s)) return currelem;
        }
        return "";
    }
    boolean issubseq(String a, String b){
        int i=0,j=0;
        while(i<a.length() && j<b.length()){
            if(a.charAt(i)==b.charAt(j)) i++;
            j++;
        }
        return i==a.length();
    }
}