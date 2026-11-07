class Solution {
    public String reverseVowels(String s) {
        // HashSet<Character> set = new HashSet<>();
        // char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        // for(char vowel: vowels) set.add(vowel);
        Character[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        HashSet<Character> set = new HashSet<>(Arrays.asList(vowels));
        int i=0,j=s.length()-1;
        char[] arr = s.toCharArray();
        while(i<j){
            if(set.contains(arr[i]) && set.contains(arr[j])){
                swap(arr,i,j);
                i++;j--;
            }
            else if(set.contains(arr[i]) && !set.contains(arr[j])) j--;
            else if(!set.contains(arr[i]) && set.contains(arr[j])) i++;
            else{
                i++;j--;
            }
        }
        String s1 = new String(arr);
        return s1;
    }
    void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}