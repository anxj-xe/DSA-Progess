class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(!stack.isEmpty()){
                char last = stack.peek();
                if(ispair(cur,last)){
                    stack.pop();
                    continue;
                }
            }
            stack.push(cur);
        }
        return stack.isEmpty();
    }
    boolean ispair(char cur,char last){
        return (last=='(' && cur == ')') ||
                (last=='{' && cur=='}') ||
                (last=='[' && cur==']');
    }
}