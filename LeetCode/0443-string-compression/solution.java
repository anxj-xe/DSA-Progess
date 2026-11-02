class Solution {
    public int compress(char[] chars) {
        int r=0,w=0;
        while(r<chars.length){
            char curr = chars[r];
            int count = 0;
            while(r<chars.length && curr==chars[r]){
                r++;count++;
            }
            chars[w++] = curr;
            if(count>1){
                for(char c: String.valueOf(count).toCharArray()) chars[w++] = c;
            }
        }
        return w;
    }
}