package advjava.Competition;

import java.util.Scanner;

/**
 * @author Nathan Boehning
 * Date: 10/7/2017
 * Purpose: 
 */
public class ProblemI {
    public static void main(String[] args) {
        int numGroups;
        int groupLength;
        int prevNum = 0;
        Scanner scan = new Scanner(System.in);
        
        numGroups = scan.nextInt();
        
        int[] kingPos = new int[numGroups];
        for(int i = 0; i < numGroups; i++) {
            groupLength = scan.nextInt();
            for(int j = 0; j < groupLength; j++) {
                if(j==0) {
                    prevNum = scan.nextInt();
                }
                else {
                    int curNum = scan.nextInt();
                    if(curNum != prevNum + 1)
                        kingPos[i] = j + 1;
                    else {
                        prevNum = curNum;
                    }
                }
            }
        }
        for(int i = 0; i < numGroups; i++)
            System.out.println(kingPos[i]);
    }
}
