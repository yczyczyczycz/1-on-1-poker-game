/**Program Name:        FiveCardDrawServer.java
 **Author:              Bruce Yi Chen Zhao
 **ID:                  100298997
 **Date Submitted:      Nov.29, 2017
 **Course:              CPSC 1181
 **Compiler:            JDK 1.8
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

//The server for running the game, shares constants from FiveCardDrawConstants with FiveCardDrawClient.
public class FiveCardDrawServer extends JFrame implements FiveCardDrawConstants
{
   public static int PLAYER1 = 1;
   public static int PLAYER2 = 2;

/**The main method that creates the FiveCardDrawServer object.
 */    
   public static void main(String[] args)
   {
      FiveCardDrawServer frame = new FiveCardDrawServer();
   }

/**Constructor.
 **Constructs a FiveCardDrawServer object. This will create a JFrame and a new server socket
 **that is ready to receive signal from clients. 
 */    
   public FiveCardDrawServer()
   {
      JTextArea jta = new JTextArea();
      
      JScrollPane scrollPane = new JScrollPane(jta);
      
      add(scrollPane, BorderLayout.CENTER);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 300);
      setTitle("FiveCardDrawServer");
      setVisible(true);
      
      try(ServerSocket serverSocket = new ServerSocket(PORT))
      {
         jta.append(new Date() + "; Server started at socket 8000\n");        
         
         while (true)
         {           
            Socket player1 = serverSocket.accept();
            
            jta.append("Player 1's IP address" + player1.getInetAddress().getHostAddress() + '\n');
            
            new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
            
            Socket player2 = serverSocket.accept();
            
            jta.append("Player 2's IP address" + player2.getInetAddress().getHostAddress() + '\n');
            
            new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
            
            jta.append(new Date() + ": Starting a thread for this session.\n");
            
            HandleSession session = new HandleSession(player1, player2);
            
            new Thread(session).start();
         }
      }
      catch(IOException ex)
      {
         System.err.println(ex);
      }   
   }

/**Inner class that handles a single session. Contains the run() method that runs the thread.
 */    
   class HandleSession implements Runnable
   {
      private Socket player1;
      private Socket player2;
      
      private DataInputStream  fromPlayer1;
      private DataOutputStream toPlayer1;
      private DataInputStream  fromPlayer2;
      private DataOutputStream toPlayer2;
      
   /**Constructor.
    **Contruct a HandleSession object that assigns two sockets to to player.
    */    
      public HandleSession(Socket player1, Socket player2)
      {
         this.player1 = player1;
         this.player2 = player2;
      }
      
   /**Runs the thread. Continusly loop and wait for client instruction.
    */  
      public void run()
      {
         //boolean to govern game states.
         boolean gameOver     = false;
         boolean player1show  = false;
         boolean player2show  = false;
         boolean player1ready = false;
         boolean player2ready = false;
         
         try
         {
            try
            {
               //The I/O system for the client/server interaction.
               fromPlayer1 = new DataInputStream(player1.getInputStream());
               toPlayer1   = new DataOutputStream(player1.getOutputStream());
               fromPlayer2 = new DataInputStream(player2.getInputStream());
               toPlayer2   = new DataOutputStream(player2.getOutputStream());
               
               //Loops continously until one player quits.
               while (!gameOver)
               {
                  //This section waits for instruction from player1.
                  int cmd1 = fromPlayer1.readInt();
                  
                  //One player redraw at a time.
                  if (cmd1 == REDRAW)
                  {                    
                     toPlayer1.writeInt(SWITCH_TURN);
                     toPlayer2.writeInt(SWITCH_TURN);
                     toPlayer2.writeInt(NOTIFY_TURN);
                     player1show = true;
                  }
                  
                  else if (cmd1 == NEW_GAME)
                  {
                     player1ready  = true;
                     toPlayer1.writeInt(WAITING);
                  }
                  
                  else if (cmd1 == FOLD)
                  {
                     toPlayer1.writeInt(PLAYER2_WON);
                     toPlayer2.writeInt(PLAYER2_WON);
                  }
                  
                  else if (cmd1 == QUIT)
                  {
                     toPlayer2.writeInt(PLAYER2_WON);
                     gameOver = true;
                  }
                  
                  //This section waits for instruction from player 2.
                  int cmd2 = fromPlayer2.readInt();
                                   
                  if (cmd2 == REDRAW)
                  {
                     toPlayer1.writeInt(SWITCH_TURN);
                     toPlayer2.writeInt(SWITCH_TURN);
                     toPlayer1.writeInt(NOTIFY_TURN);
                     player2show = true;
                  }
                  
                  else if (cmd2 == NEW_GAME)
                  {
                     player2ready  = true;
                     toPlayer2.writeInt(WAITING);
                  } 
                  
                  else if (cmd2 == FOLD)
                  {
                     toPlayer1.writeInt(PLAYER1_WON);
                     toPlayer2.writeInt(PLAYER1_WON);
                  }
                  
                  else if (cmd2 == QUIT)
                  {
                     toPlayer1.writeInt(PLAYER1_WON);
                     gameOver = true;
                  } 
                  
                  //Showdown phase using PokerHandRanking.java.
                  if (player1show && player2show)
                  {
                     int[] hand1 = new int[5];
                     int[] hand2 = new int[5];
                     
                     toPlayer1.writeInt(HAND);
                     hand1[0] = fromPlayer1.readInt();
                     hand1[1] = fromPlayer1.readInt();
                     hand1[2] = fromPlayer1.readInt();
                     hand1[3] = fromPlayer1.readInt();
                     hand1[4] = fromPlayer1.readInt();
                     
                     toPlayer2.writeInt(HAND);
                     hand2[0] = fromPlayer2.readInt();
                     hand2[1] = fromPlayer2.readInt();
                     hand2[2] = fromPlayer2.readInt();
                     hand2[3] = fromPlayer2.readInt();
                     hand2[4] = fromPlayer2.readInt();
                     
                     int result = PokerHandRanking.compareTwoHands(hand1, hand2);
                     
                     if (result == 1)
                     {
                        toPlayer1.writeInt(PLAYER1_WON);
                        toPlayer2.writeInt(PLAYER1_WON);
                        player1show = false;
                        player1show = false;
                     }
                     
                     else if (result == 2)
                     {
                        toPlayer1.writeInt(PLAYER2_WON);
                        toPlayer2.writeInt(PLAYER2_WON);
                        player1show = false;
                        player1show = false;
                     }
                     
                     else
                     {
                        toPlayer1.writeInt(TIE);
                        toPlayer2.writeInt(TIE);
                        player1show = false;
                        player1show = false;
                     }
                  }
                  
                  //When both player press ready.
                  if (player1ready && player2ready)
                  {
                     toPlayer1.writeInt(READY);
                     toPlayer2.writeInt(READY);
                     player1ready = false;
                     player2ready = false;
                  } 
                         
               }
            }
            finally
            {
               player1.close();
               player2.close();
            }
         }
         catch(IOException ex)
         {
            System.err.println(ex);
         }      
         
      }      
   }
}