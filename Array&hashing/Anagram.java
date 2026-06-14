//Uber question

public class Anagram {
  public boolean isAnagram(String s,String t){
    if(s.length() != t.length()){
      return false;
    }

    // char[] s1 = s.toCharArray();
    // char[] t1 = t.toCharArray();
    // Arrays.sort(s1);
    // Arrays.sort(t1);
    // return Arrays.equals(s1,t1);

    int[] count = new int[26];
    for(int i=0; i<s.length();i++){
      count[s.charAt(i) - 'a']++;
      count[t.charAt(i) - 'a']--;
    }

    for(int val : count){
      if(val != 0){
        return false;
      }
    }
    return  true;
  }

  public static void main(String[] args) {
    Anagram a = new Anagram();
    String s = "listen";
    String t = "silent";
    System.out.println(a.isAnagram(s,t));
  }
}


/*
1. Sorting
Intuition
If two strings are anagrams, they must contain exactly the same characters with the same frequencies.
By sorting both strings, all characters will be arranged in a consistent order.
If the two sorted strings are identical, then every character and its count match, which means the strings are anagrams.

Algorithm
If the lengths of the two strings differ, return false immediately because they cannot be anagrams.
Sort both strings.
Compare the sorted versions of the strings:
If they are equal, return true.
Otherwise, return false.
*/

/* 
2. Hash Map
Intuition
If two strings are anagrams, they must use the same characters with the same frequencies.
Instead of sorting, we can count how many times each character appears in both strings.
By using two hash maps (or dictionaries), we track the frequency of every character in each string.
If both frequency maps match exactly, then the strings contain the same characters with same frequencies, meaning they are anagrams.

Algorithm
If the two strings have different lengths, return false immediately.
Create two hash maps to store character frequencies for each string.
Iterate through both strings at the same time:
Increase the character count for s[i] in the first map.
Increase the character count for t[i] in the second map.
After building both maps, compare them:
If the maps are equal, return true.
Otherwise, return false.
*/