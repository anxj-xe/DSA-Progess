class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int i =0;
        for(int j=0;j<=arr.length;j++){
            if(j==arr.length || arr[j] == ' '){
                rev(arr,i,j-1);
                i = j+1;
            }
        }
        return new String(arr);
    }
    void rev(char[] arr,int start,int end){
        while(start<end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;end--;
        }
    }
}