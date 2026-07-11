package Strings;

public class PalindromeString  {
    public static boolean isPalindrome(String str){
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) != str.charAt(str.length() - i -1)){
                //not a palindrome
                return  false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "racecar";
        System.out.print(isPalindrome(str));
    }
}
