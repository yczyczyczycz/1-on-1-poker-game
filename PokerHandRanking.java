/**Program Name:        PokerHandRanking.java
 **Author:              Bruce Yi Chen Zhao
 **ID:                  100298997
 **Date Submitted:      Nov.29, 2017
 **Course:              CPSC 1181
 **Compiler:            JDK 1.8
 */
import java.lang.Math;

//Written by myself completely specifically for my Five Card Draw program.
//This class contains a private constructor and only static methods.
//The basics of this program is that I assign a value for each card and sort
//them from small to big. Then after that it is much easier to recognize poker
//hand ranking. For example, it is very easy to tell that 44448 is a four of a kind.
public class PokerHandRanking
{
   //Poker hand rankings from strongest to weakest.
   private static final int STRAIGHT_FLUSH  = 9;
   private static final int FOUR_OF_A_KIND  = 8;
   private static final int FULL_HOUSE      = 7;
   private static final int FLUSH           = 6;
   private static final int STRAIGHT        = 5;
   private static final int THREE_OF_A_KIND = 4;
   private static final int TWO_PAIR        = 3;
   private static final int ONE_PAIR        = 2;
   private static final int HIGH_CARD       = 1;
   

/**Constructor.
 **Private construtor to prevent instantiation.
 */    
   private PokerHandRanking()
   {
   }

/**Tests to see if the 5 cards make a flush.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a flush and false otherwise.
 */    
   public static boolean isFlush(int[] arr)
   {
      //spade
      if (arr[0] <= 12 && arr[1] <= 12 && arr[2] <= 12 && arr[3] <= 12 && arr[4] <= 12)
      {
         return true;
      }
      
      //heart
      else if (arr[0] <= 25 && arr[1] <= 25 && arr[2] <= 25 && arr[3] <= 25 && arr[4] <= 25 &&
               arr[0] >= 13 && arr[1] >= 13 && arr[2] >= 13 && arr[3] >= 13 && arr[4] >= 13)
      {
         return true;
      }   
      
      //club
      else if (arr[0] <= 38 && arr[1] <= 38 && arr[2] <= 38 && arr[3] <= 38 && arr[4] <= 38 &&
               arr[0] >= 26 && arr[1] >= 26 && arr[2] >= 26 && arr[3] >= 26 && arr[4] >= 26)
      {
         return true;
      }  
      
      //diamond
      else if (arr[0] >= 39 && arr[1] >= 39 && arr[2] >= 39 && arr[3] >= 39 && arr[4] >= 39)
      {
         return true;
      } 
      
      else
      {
         return false;
      }
   }

/**Assigns a value for a card. Ace is 14. Numbers are what they are. J is 11, Q is 12 and K is 13.
 **@param num a number that represents a poker card.
 **@return an int that is the value assigned
 */    
   public static int assignValue(int num)
   {
      int value = 0;
      
      //Ace
      if (num == 0 || num == 13 || num == 26 || num == 39)
      {
         value = 14;
      }
      
      if (num == 1 || num == 14 || num == 27 || num == 40)
      {
         value = 2;
      }
      
      if (num == 2 || num == 15 || num == 28 || num == 41)
      {
         value = 3;
      }
      
      if (num == 3 || num == 16 || num == 29 || num == 42)
      {
         value = 4;
      }
      
      if (num == 4 || num == 17 || num == 30 || num == 43)
      {
         value = 5;
      }
      
      if (num == 5 || num == 18 || num == 31 || num == 44)
      {
         value = 6;
      }
      
      if (num == 6 || num == 19 || num == 32 || num == 45)
      {
         value = 7;
      }
      
      if (num == 7 || num == 20 || num == 33 || num == 46)
      {
         value = 8;
      }
      
      if (num == 8 || num == 21 || num == 34 || num == 47)
      {
         value = 9;
      }
      
      if (num == 9 || num == 22 || num == 35 || num == 48)
      {
         value = 10;
      }
      
      //Jack
      if (num == 10 || num == 23 || num == 36 || num == 49)
      {
         value = 11;
      }
      
      //Queen
      if (num == 11 || num == 24 || num == 37 || num == 50)
      {
         value = 12;
      }
      
      //King
      if (num == 12 || num == 25 || num == 38 || num == 51)
      {
         value = 13;
      }
      
      return value;
   } 

/**Stores the assigned values into an array and returns that array.
 **@param arr an array of int representing the poker hand.
 **return an int array of length 5 that contains the assigned value for the poker cards.
 */      
   public static int[] assignHandValue(int[] arr)
   {
      int[] handValue = new int[5];
      
      handValue[0] = assignValue(arr[0]);
      handValue[1] = assignValue(arr[1]);
      handValue[2] = assignValue(arr[2]);
      handValue[3] = assignValue(arr[3]);
      handValue[4] = assignValue(arr[4]);
      
      return handValue;
   }
   
/**A bubble sort that sorts the content of the int array from smallest to biggest.
 **@param arr an int array that will be sorted by this method.
 */ 
   public static void sortFromSmallToBig(int[] arr)
   {
      int temp;
      int i;
      
      while (!(arr[0] <= arr[1] && arr[1] <= arr[2] && arr[2] <= arr[3] && arr[3] <= arr[4]))
      {
         for (i = 0; i < 4; i++)
         {
            if (arr[i] > arr[i+1])
            {
               temp = arr[i];
               arr[i] = arr[i+1];
               arr[i+1] = temp;
            }
         }
      }
   }

/**Test to see if the 5 cards make a straight.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a straight and false otherwise.
 */       
   public static boolean isStraight(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //Ace's value is 14 so it won't be sorted to the left so this statement is here to make sure A2345 gets recognized as a straight.
      if (handValue[0] == 2 && handValue[1] == 3 && handValue[2] == 4 && handValue[3] == 5 && handValue[4] == 14)
      {
         return true;
      }
      
      //If then next number is 1 larger than the last then it is a straight.
      else if (handValue[0] + 1 == handValue[1] && handValue[1] + 1 == handValue[2] && handValue[2] + 1 == handValue[3] && handValue[3] + 1 == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a straight flush.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a straight flush and false otherwise.
 */      
   public static boolean isStraightFlush(int[] arr)
   {
      //If it's both a flush and a straight it is a straight flush.
      if (isStraight(arr) && isFlush(arr))
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a four of a kind.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a four of a kind and false otherwise.
 */      
   public static boolean isFourOfAKind(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //For example 22223.
      if (handValue[0] == handValue[1] && handValue[1] == handValue[2] && handValue[2] == handValue[3])
      {
         return true;
      }
      
      //For example 35555.
      else if (handValue[1] == handValue[2] && handValue[2] == handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a full house.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a full house and false otherwise.
 */       
   public static boolean isFullHouse(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //For example 55566
      if (handValue[0] == handValue[1] && handValue[1] == handValue[2] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      //For example 77888
      else if (handValue[0] == handValue[1] && handValue[2] == handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a two pair.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a two pair and false otherwise.
 */       
   public static boolean isTwoPair(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //For example 44556
      if (handValue[0] == handValue[1] && handValue[1] != handValue[2] && handValue[2] == handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 45566
      else if (handValue[0] != handValue[1] && handValue[1] == handValue[2] && handValue[2] != handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      //For example 44566
      else if (handValue[0] == handValue[1] && handValue[1] != handValue[2] && handValue[2] != handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a three of a kind.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a three of a kind and false otherwise.
 */       
   public static boolean isThreeOfAKind(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //For example 77789
      if (handValue[0] == handValue[1] && handValue[1] == handValue[2] && handValue[2] != handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 36669
      else if (handValue[0] != handValue[1] && handValue[1] == handValue[2] && handValue[2] == handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 45888
      else if (handValue[0] != handValue[1] && handValue[1] != handValue[2] && handValue[2] == handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Test to see if the 5 cards make a pair.
 **@param arr an int array that contains 5 integers representing 5 cards.
 **@return a boolean value that is true if the cards make a pair and false otherwise.
 */      
   public static boolean isOnePair(int[] arr)
   {
      int[] handValue = assignHandValue(arr);
      sortFromSmallToBig(handValue);
      
      //For example 22345
      if (handValue[0] == handValue[1] && handValue[1] != handValue[2] && handValue[2] != handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 23345
      else if (handValue[0] != handValue[1] && handValue[1] == handValue[2] && handValue[2] != handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 23445
      else if (handValue[0] != handValue[1] && handValue[1] != handValue[2] && handValue[2] == handValue[3] && handValue[3] != handValue[4])
      {
         return true;
      }
      
      //For example 23455
      else if (handValue[0] != handValue[1] && handValue[1] != handValue[2] && handValue[2] != handValue[3] && handValue[3] == handValue[4])
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }

/**Takes an int array and assigns a hand ranking to the hand represented by the array.
 **@param arr an int array representing the poker hand.
 **@return an int representing the hand strength.
 */    
   public static int assignHandRanking(int[] arr)
   {
      int ranking = 0;
      
      if(isStraightFlush(arr))
      {
         ranking = STRAIGHT_FLUSH;
      }
      
      else if(isFourOfAKind(arr))
      {
         ranking = FOUR_OF_A_KIND;
      }
      
      else if(isFullHouse(arr))
      {
         ranking = FULL_HOUSE;
      }
      
      else if(isFlush(arr))
      {
         ranking = FLUSH;
      }
      
      else if(isStraight(arr))
      {
         ranking = STRAIGHT;
      }
      
      else if(isThreeOfAKind(arr))
      {
         ranking = THREE_OF_A_KIND;
      }
      
      else if(isTwoPair(arr))
      {
         ranking = TWO_PAIR;
      }
      
      else if (isOnePair(arr))
      {
         ranking = ONE_PAIR;
      }
      
      else
      {
         ranking = HIGH_CARD;
      }
      
      return ranking;
   }

/**Compares two poker hands and returns a value representing the winner.
 **@param arr1 an int array represnting hand 1.
 **@param arr2 an int array represnting hand 2.
 **@return an int representing the winner.  1 if hand 1 wins, 2 if hand 2 wins and 3 if both hand are tied.
 */    
   public static int compareTwoHands(int[] arr1, int[] arr2)
   {
      final int PLAYER1_WON = 1;
      final int PLAYER2_WON = 2;
      final int TIE         = 3;
      
      int handRanking1 = assignHandRanking(arr1);
      int handRanking2 = assignHandRanking(arr2);
      
      int[] handValue1 = assignHandValue(arr1);
      sortFromSmallToBig(handValue1);
      int[] handValue2 = assignHandValue(arr2);
      sortFromSmallToBig(handValue2);
      
      if (handRanking1 > handRanking2)
      {
         return PLAYER1_WON;
      }
      
      else if (handRanking1 < handRanking2)
      {
         return PLAYER2_WON;
      }
      
      //When the ranking is the same the highest card wins.(bigger pair or bigger card when there is no pair).
      else
      {
         return tieBreaker(handValue1, handValue2, handRanking1);
      }
   }

/**A function that runs when both hands have the same ranking. It determines the winner between the two hands.
 **@param arr1 an int array represnting hand 1.
 **@param arr2 an int array represnting hand 2.
 **@param ranking an int representing the hank ranking of both hands.
 **@return an int representing the winner.1 if hand 1 wins, 2 if hand 2 wins and 3 if both hand are tied.
 */    
   public static int tieBreaker(int[] arr1, int[] arr2, int ranking)
   {
      final int PLAYER1_WON = 1;
      final int PLAYER2_WON = 2;
      final int TIE         = 3;
      int i;
      
      //Straight flush and straight behave the same so I grouped these two together.
      if (ranking == STRAIGHT_FLUSH || ranking == STRAIGHT)
      {
         //Straight is tricky because ace can be low as in A2345 and high as in 10 J Q K A.
         if (arr1[4] == 14 && arr2[4] == 14)
         {
            if (arr1[0] == 2 && arr2[0] == 2)
            {
               return TIE;
            }
            
            else if (arr1[0] == 10  && arr2[0] == 2)
            {
               return PLAYER1_WON;
            }
            
            else if (arr1[0] == 2  && arr2[0] == 10)
            {
               return PLAYER2_WON;
            }
            
            else
            {
               return TIE;
            }
         }
         
         else if (arr1[4] == 14 && arr1[0] == 2)
         {
            return PLAYER2_WON;
         }   
         
         else if (arr2[4] == 14 && arr2[0] == 2)
         {
            return PLAYER1_WON;
         }
         
         //If neither hand contains an ace then the highest last card will win.   
         else if (arr1[4] > arr2[4])
         {
            return PLAYER1_WON;
         }
            
         else if (arr1[4] < arr2[4])
         {
            return PLAYER2_WON;
         }
            
         else
         {
            return TIE;
         }
      }
      
      
      //High card and flush behave the same so these two are together.
      else if (ranking == HIGH_CARD || ranking == FLUSH)
      {
            //Compare the biggest card, if same then go to the next one until a winner is decided or a tie is declared.
            for (i = 4; i >= 0; i--)
            {
               if (arr1[i] > arr2[i])
               {
                  return PLAYER1_WON;
               }
               
               else if (arr1[i] < arr2[i])
               {
                  return PLAYER2_WON;
               }
               
               else
               {
                  continue;
               }
            }
            
            return TIE;

      }
      
      //Hands that contains a three of a kind or four of a kind can be grouped together.
      //Testing these is really easy, just grab the middle card and comapre that card.
      //This is because the middle card is always the trips card.
      else if (ranking == FOUR_OF_A_KIND || ranking == FULL_HOUSE || ranking == THREE_OF_A_KIND)
      { 
         if (arr1[2] > arr2[2])
         {
            return PLAYER1_WON;
         }
         
         else
         {
            return PLAYER2_WON;
         }
      }
      
      //Compare the bigger pair first, then the smaller pair, then the single cards.
      else if (ranking == TWO_PAIR)
      {
         int bigPair1;
         int smallPair1;
         int single1;
         int bigPair2;
         int smallPair2;
         int single2;
         
         if (arr1[4] != arr1[3])
         {
            single1 = arr1[4];
            
            if(arr1[0] > arr1[2])
            {
               bigPair1 = arr1[0];
               smallPair1 = arr1[2];
            }
            
            else
            {
               bigPair1 = arr1[2];
               smallPair1 = arr1[0];
            }
         }
         
         else if (arr1[0] != arr1[1])
         {
            single1 = arr1[0];
            
            if(arr1[1] > arr1[3])
            {
               bigPair1 = arr1[1];
               smallPair1 = arr1[3];
            }
            
            else
            {
               bigPair1 = arr1[3];
               smallPair1 = arr1[1];
            }
         }
         
         else
         {
            single1 = arr1[2];
            
            if(arr1[0] > arr1[4])
            {
               bigPair1 = arr1[0];
               smallPair1 = arr1[4];
            }
            
            else
            {
               bigPair1 = arr1[4];
               smallPair1 = arr1[0];
            }   
         }
         
         if (arr2[4] != arr2[3])
         {
            single2 = arr2[4];
            
            if(arr2[0] > arr2[2])
            {
               bigPair2 = arr2[0];
               smallPair2 = arr2[2];
            }
            
            else
            {
               bigPair2 = arr2[2];
               smallPair2 = arr2[0];
            }
         }
         
         else if (arr2[0] != arr2[1])
         {
            single2 = arr2[0];
            
            if(arr2[1] > arr2[3])
            {
               bigPair2 = arr2[1];
               smallPair2 = arr2[3];
            }
            
            else
            {
               bigPair2 = arr2[3];
               smallPair2 = arr2[1];
            }
         }
         
         else
         {
            single2 = arr2[2];
            
            if(arr2[0] > arr2[4])
            {
               bigPair2 = arr2[0];
               smallPair2 = arr2[4];
            }
            
            else
            {
               bigPair2 = arr2[4];
               smallPair2 = arr2[0];
            }   
         }
         
         if(bigPair1 > bigPair2)
         {
            return PLAYER1_WON;
         }
         
         else if (bigPair1 < bigPair2)
         {
            return PLAYER2_WON;
         }
         
         else
         {
            if(smallPair1 > smallPair2)
            {
               return PLAYER1_WON;
            }
         
            else if (smallPair1 < smallPair2)
            {
               return PLAYER2_WON;
            }
            
            else
            {
               if(single1 > single2)
               {
                  return PLAYER1_WON;
               }
         
               else if (single1 < single2)
               {
                  return PLAYER2_WON;
               }
               
               else
               {
                  return TIE;
               }
            }
         }         
      }
      
      //This is for one pair.
      //Compare the pair first, then the three single cards.
      //The code is hard to read but to explain what I did:
      //I assigned the number that is the pair then assigned the three single cards into three variables and compare them.
      //I had to do this process twice for both hands.
      else
      {
         int pair1;
         int highCard1;
         int midCard1;
         int lowCard1;
         int pair2;
         int highCard2;
         int midCard2;
         int lowCard2;
         
         if (arr1[0] == arr1[1])
         {
            pair1 = arr1[0];
            highCard1 = Math.max(Math.max(arr1[2], arr1[3]), arr1[4]);
            lowCard1  = Math.min(Math.min(arr1[2], arr1[3]), arr1[4]);
            
            if (arr1[2] > lowCard1 && arr1[2] < highCard1)
            {
               midCard1 = arr1[2];
            }
            
            else if (arr1[3] > lowCard1 && arr1[3] < highCard1)
            {
               midCard1 = arr1[3];
            }
            
            else
            {
               midCard1 = arr1[4];
            }
         }
         
         else if (arr1[1] == arr1[2])
         {
            pair1 = arr1[1];
            highCard1 = Math.max(Math.max(arr1[0], arr1[3]), arr1[4]);
            lowCard1  = Math.min(Math.min(arr1[0], arr1[3]), arr1[4]);
            
            if (arr1[0] > lowCard1 && arr1[0] < highCard1)
            {
               midCard1 = arr1[0];
            }
            
            else if (arr1[3] > lowCard1 && arr1[3] < highCard1)
            {
               midCard1 = arr1[3];
            }
            
            else
            {
               midCard1 = arr1[4];
            }
         }
         
         else if (arr1[2] == arr1[3])
         {
            pair1 = arr1[2];
            highCard1 = Math.max(Math.max(arr1[0], arr1[1]), arr1[4]);
            lowCard1  = Math.min(Math.min(arr1[0], arr1[1]), arr1[4]);
            
            if (arr1[0] > lowCard1 && arr1[0] < highCard1)
            {
               midCard1 = arr1[0];
            }
            
            else if (arr1[1] > lowCard1 && arr1[1] < highCard1)
            {
               midCard1 = arr1[1];
            }
            
            else
            {
               midCard1 = arr1[4];
            }
         }
         
         else
         {
            pair1 = arr1[3];
            highCard1 = Math.max(Math.max(arr1[0], arr1[1]), arr1[2]);
            lowCard1  = Math.min(Math.min(arr1[0], arr1[1]), arr1[2]);
            
            if (arr1[0] > lowCard1 && arr1[0] < highCard1)
            {
               midCard1 = arr1[0];
            }
            
            else if (arr1[1] > lowCard1 && arr1[1] < highCard1)
            {
               midCard1 = arr1[1];
            }
            
            else
            {
               midCard1 = arr1[2];
            }
         }
         
         if (arr2[0] == arr2[1])
         {
            pair2 = arr2[0];
            highCard2 = Math.max(Math.max(arr2[2], arr2[3]), arr2[4]);
            lowCard2  = Math.min(Math.min(arr2[2], arr2[3]), arr2[4]);
            
            if (arr2[2] > lowCard2 && arr2[2] < highCard2)
            {
               midCard2 = arr2[2];
            }
            
            else if (arr2[3] > lowCard2 && arr2[3] < highCard2)
            {
               midCard2 = arr2[3];
            }
            
            else
            {
               midCard2 = arr2[4];
            }
         }
         
         else if (arr2[1] == arr2[2])
         {
            pair2 = arr2[1];
            highCard2 = Math.max(Math.max(arr2[0], arr2[3]), arr2[4]);
            lowCard2  = Math.min(Math.min(arr2[0], arr2[3]), arr2[4]);
            
            if (arr2[0] > lowCard2 && arr2[0] < highCard2)
            {
               midCard2 = arr2[0];
            }
            
            else if (arr2[3] > lowCard2 && arr2[3] < highCard2)
            {
               midCard2 = arr2[3];
            }
            
            else
            {
               midCard2 = arr2[4];
            }
         }
         
         else if (arr2[2] == arr2[3])
         {
            pair2 = arr2[2];
            highCard2 = Math.max(Math.max(arr2[0], arr2[1]), arr2[4]);
            lowCard2  = Math.min(Math.min(arr2[0], arr2[1]), arr2[4]);
            
            if (arr2[0] > lowCard2 && arr2[0] < highCard2)
            {
               midCard2 = arr2[0];
            }
            
            else if (arr2[1] > lowCard2 && arr2[1] < highCard2)
            {
               midCard2 = arr2[1];
            }
            
            else
            {
               midCard2 = arr2[4];
            }
         }
         
         else
         {
            pair2 = arr2[3];
            highCard2 = Math.max(Math.max(arr2[0], arr2[1]), arr2[2]);
            lowCard2  = Math.min(Math.min(arr2[0], arr2[1]), arr2[2]);
            
            if (arr2[0] > lowCard2 && arr2[0] < highCard2)
            {
               midCard2 = arr2[0];
            }
            
            else if (arr2[1] > lowCard2 && arr2[1] < highCard2)
            {
               midCard2 = arr2[1];
            }
            
            else
            {
               midCard2 = arr2[2];
            }
         }

         if (pair1 > pair2)
         {
            return PLAYER1_WON;
         }
         
         else if (pair1 < pair2)
         {
            return PLAYER2_WON;
         }
         
         else
         {
            if (highCard1 > highCard2)
            {
               return PLAYER1_WON;
            }
            
            else if (highCard1 < highCard2)
            {
               return PLAYER2_WON;
            }
            
            else
            {
               if (midCard1 > midCard2)
               {
                  return PLAYER1_WON;
               }
               
               else if (midCard1 < midCard2)
               {
                  return PLAYER2_WON;
               }
               
               else
               {
                  if (lowCard1 > lowCard2)
                  {
                     return PLAYER1_WON;
                  }
                  
                  else if (lowCard1 < lowCard2)
                  {
                     return PLAYER2_WON;
                  }
                  
                  else
                  {
                     return TIE;
                  }
               }
            }
         }        
      }
   }
}