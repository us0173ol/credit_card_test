package com.clara;

import java.util.Scanner;

/**
 * Created by we4954cp on 8/31/2016.
 */
public class CreditCard {

    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ask user for credit card number. store number as a String.
        System.out.println("Please enter the credit card number, digits only:");
        String ccNumber = stringScanner.nextLine();
        //create a boolean variable that calls isValidCreditCard method
        boolean isValid = isValidCreditCard(ccNumber);
        //if the card is valid tell the user
        if (isValid) {
            System.out.println("This seems to be a valid credit card number");
            //if the card is invalid tell the user
        } else {
            System.out.println("This is **not** a valid credit card number.");
        }

        stringScanner.close();
    }
    //method that checks if the card is valid
    public static boolean isValidCreditCard(String cc) {
        //recreate string with argument sent to method
        String card = cc;
        //char variable for the check number
        char checkNumber = cc.charAt((cc.length()-1));
        //convert check number to string
        String checkString = "" + checkNumber;
        //convert the check number to an integer value
        int check = Integer.valueOf(checkString);
        //remove the check number for calculating the sum of the digits
        card = card.substring(0, ( card.length())-1);
        //reverse the order of the card number once check number is removed
        String cardReverse = new StringBuilder(card).reverse().toString();
        /*create two char arrays, one for the numbers that need to be doubled
        * and one for the numbers that dont need to be doubled*/
        char [] cccArray = cc.toCharArray();
        char [] ccArray = cardReverse.toCharArray();
        /*once each array is created, convert them to integer arrays
        * for calculating the sum of all of the numbers in the card*/
        int [] everyOtherRev = new int[ccArray.length];
        int [] everyOther = new int[cccArray.length];
        //variable for adding up the sum of the numbers
        int sumOfDigits = 0;
        //Get the integer value for every number in the array that needs to be doubled
        for(int i=0; i<ccArray.length; i+=2) {
            everyOtherRev[i] = Character.getNumericValue(ccArray[i]);
        }
        //Get the integer value for every number that doesnt need to be doubled
        for(int i=1; i<ccArray.length; i+=2){
            everyOther[i] = Character.getNumericValue( cccArray[i]);
        }
        //loop through the numbers that need to be doubled and double them
        for (int j=0; j<everyOtherRev.length; j++)
        {//this eliminates calculations for numbers located at the odd element number locations
            if ( (j % 2)==0)
            {
                everyOtherRev[j] = everyOtherRev[j]*2;
            }
        }
        //loop through the doubled numbers for numbers that are now double digits or greater than 9
        for (int k=0; k<everyOtherRev.length ; k++)
        {/*if the doubled number is a double digit then subtract 9 to get the sum of both numbers individually
        for example if the dooubled number is now 14, 1+4=5 is the same as 14-9=5 and doubled numbers cannot
        exceed a value of 18 therefore subtracting 9 will always give a single digit value*/
            if (everyOtherRev[k]>9)
            {
                everyOtherRev[k] = everyOtherRev[k]-9;
            }
        }
        //numbers that are not doubled are added to the sumOfDigits total first
        for(int l = 0; l < everyOther.length; l++)
        {
            sumOfDigits+=everyOther[l];
        }
        //numbers that are doubled are added to the sumOfDigits total second
        for (int l = 0; l < everyOtherRev.length; l++)
        {
            sumOfDigits+= everyOtherRev[l];
        }
        //after all numbers have been totaled, add the check number back to the sumOfDigits total
        sumOfDigits+=check;
        //if there is no remainder when the sumOfDigits total is divided by 10, then all tests should pass
        if(sumOfDigits % 10 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
/*http://stackoverflow.com/questions/20725761/validate-credit-card-number-using-luhn-algorithm
* the majority of this code was found on stack overflow, many adjustments and additions needed
* to be added and changed however.  This program is probably the one that I have learned the most
* from.  The debugger was my best friend on this one and helped me to figure out all of my bugs from
* what I originally had coded.  I feel like I have a better understanding of arrays, methods, arguments,
* boolean values, and just the process it takes to get something right.*/