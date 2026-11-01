class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') st.push(i);
            else{
                st.pop();
                if(st.isEmpty()) st.push(i);
                else max = Math.max(max,i-st.peek());
            }
        }
        return max;
        // if(s.length()==0) return 0;
        // int count=0;
        // Stack<Character> st = new Stack<>();
        // for(int i=0;i<s.length();i++){
        //     char cur = s.charAt(i);
    //         if(!st.isEmpty()){
    //             char last = st.peek();
    //             if(ispair(last,cur)) count+=i;
    //         }else if(st.isEmpty() && cur==')') 
    //         st.push(cur);
    //     }
    //     return count;
    // }
    // boolean ispair(char last,char cur){
    //     return (last=='('&&cur==')');
    }
}