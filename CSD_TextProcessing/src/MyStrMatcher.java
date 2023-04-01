/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class MyStrMatcher {
    //Bruth Force approach
    // Matching first index of the pattern p in the string S
    public static int indexOf_BF(String S, String pattern) {
        int n= S.length();
        int m= pattern.length();
        int i,j;
        for(i=0; i<n-m+1; i++) {
            j=0;
            while(j<m) {
                if(S.charAt(i+j)!=pattern.charAt(j))
                    break;
                j++;
                if(j==m)
                    return i;  //found
            }
        }
        return -1; //not found
    }
    //Matching last index of the pattern p in the string s
    public static int lastIndexOf_BF(String S, String pattern) {
        int n = S.length();
        int m= pattern.length();
        int i,j;
        for(i=n-m; i>=0; i--) {
            j=0;
            while(j<m) {
                if(S.charAt(i+j)!=pattern.charAt(j))
                    break;
                j++;
                if(j==m)
                    return i; //found
            }
        }
        return -1; //not found
    }
    
    // Implementing Knut-Morris-Pratt algorithm
    //Create Longest proper Prefix which is also Suffix
    public static int[] computeFailKMP(char[] pattern) {
        int m=pattern.length;
        int[] fail= new int[m];
        fail[0]=0; //first element having no previous element
        int j=1; //position of the array fail will be assign value
        int k=0;  //value will be assign to elements in the array fail
        while(j<m) { //assign values to fail[1]..fail[m-1]
            if(pattern[j]==pattern[k]) {  // 2 characters are the same
                fail[j]=k+1; //skip k+! characters
                k++;
            }
            else if(k>0) k=fail[k-1];  //get back previous value
            else fail[j]=0;  //no improvement
            j++;
        }
        return fail;
    }
    public static int indexOf_KMP(char S[], char p[]) {
        int n=S.length;
        int m=p.length;
        int[] prefixes = computeFailKMP(p);
        int j=0;
        int k=0;
        while(j<n) { //traverse S
                if(S[j]==p[k]) {
                    if(k==m-1)  return j-m+1; //update position in p
                }
                else 
                    if(k>0)  k=prefixes[k-1];
                j++;
                k++;
                }
        return -1;  //not found
    }
    public static int indexOf_KMP(String S, String pattern) {
        return indexOf_KMP(S.toCharArray(), pattern.toCharArray());
    }
    public static void printArray(int a[]) {
        for(int x:a)
            System.out.print(x+", ");
    }
    
    public static void main(String [] args) {
        //Test Bruth Force algorithm
        System.out.println("\tTEST BRUTH FORCE ALGORITHM");
        String S1 = "Jack! go away, and you, please do not go away tonight";
        String p1="go";
        String p2="away";
        int pos1=indexOf_BF(S1, p1);
        int pos2=indexOf_BF(S1, p2);
        int pos3=lastIndexOf_BF(S1, p2);
        System.out.println("S1="+S1);
        System.out.println("p1="+p1);
        System.out.println("Index of p1 in S1: "+pos1);
        System.out.println("p2="+p2);
        System.out.println("Index of p2 in S1: "+pos2);
        System.out.println("Last index of p2 in S1: "+pos3);
        //Test KMP algorithm
        System.out.println("\tTEST KMP ALGORITHM");
        String S2= "bacbabababacaca";
        String pattern ="ababaca";  //found at the position 6
        int fail[] = computeFailKMP(pattern.toCharArray());
        System.out.println("S2:"+S2);
        System.out.println("Pattern:"+pattern);
        System.out.println("fail[] :");
        printArray(fail);
        System.out.println("\nPattern at position in S2:"+indexOf_KMP(S2,pattern));
    }
}
