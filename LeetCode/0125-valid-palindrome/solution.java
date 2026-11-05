class Solution {
    public boolean isPalindrome(String s) {
        String s1 = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int i=0;
        while(i<s1.length()){
            char c = s1.charAt(i);
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
            i++;
        }
        return isPal(sb.toString());
    }
    boolean isPal(String s){
        int left=0;
        int right=s.length()-1;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)) return false;
            left++;right--;
        }
        return true;
    }
}