class Solution {
    public int nextGreaterElement(int n) {
        char[] arr = Integer.toString(n).toCharArray();
        int len = arr.length;
        int i= len-2;
        while(i>=0 && arr[i]>=arr[i+1]) i--;
        if(i<0) return -1;
        int j = len-1;
        while(arr[j]<=arr[i]) j--;
        swap(arr,i,j);
        reverse(arr,i+1,len-1);
        long res = Long.parseLong(new String(arr));
        return res>Integer.MAX_VALUE ? -1 : (int) res;
    }
    void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    void reverse(char[] arr,int start,int end){
        while(start<end){
            swap(arr,start,end);
            start++;end--;
        }
    }
}