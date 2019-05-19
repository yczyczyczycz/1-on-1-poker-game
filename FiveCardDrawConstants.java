/**Program Name:        FiveCardDrawConstants.java
 **Author:              Bruce Yi Chen Zhao
 **ID:                  100298997
 **Date Submitted:      Nov.29, 2017
 **Course:              CPSC 1181
 **Compiler:            JDK 1.8
 */

//Protocol for the server / client five card draw program.
public interface FiveCardDrawConstants
{
   //The number assigned to player 1 is 1.
   public static final int PLAYER1     = 1;
   
   //The number assigned to player 2 is 2.
   public static final int PLAYER2     = 2;
   
   //What the server send to the player when a winer is decided(or tie).
   public static final int PLAYER1_WON = 1;
   public static final int PLAYER2_WON = 2;
   public static final int TIE         = 3;
   
   //Request from the player to the server to fold. Only works during the player's turn.
   public static final int FOLD        = 1;
   
   //Request from the player to the server to redraw. The method won't execute if it isn't the player's turn.
   //The server keeps track whose turn is it.
   public static final int REDRAW      = 2;
   
   //Request from the player to the server to quit the game. The current server thread of the current game will end after this.
   public static final int QUIT        = 3;
   
   //Response from the server to the player to switch turn.
   public static final int SWITCH_TURN = 4;
   
   //Response from the server to the player to notify the player's turn.
   public static final int NOTIFY_TURN = 5;
   
   //Request from the player to the server to start a new game.
   public static final int NEW_GAME    = 6;
   
   //Response from the server to the player that the game is ready to start.
   public static final int READY       = 7;
   
   //Response from the server to the player that the game is waiting on the other player.
   public static final int WAITING     = 8;
   
   //Request from the server to the player to retrive the information of the player's hand for showdown.
   public static final int HAND        = 9;
   
   //Port number.
   public static final int PORT        = 8000;
}