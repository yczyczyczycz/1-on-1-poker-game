/**Program Name:        FiveCardDrawClient.java
 **Author:              Bruce Yi Chen Zhao
 **ID:                  100298997
 **Date Submitted:      Nov.29, 2017
 **Course:              CPSC 1181
 **Compiler:            JDK 1.8
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.*;
import javax.swing.*;


//The client side of the game.
public class FiveCardDrawClient extends JFrame implements Runnable, FiveCardDrawConstants
{
   //Global variables
   private static final int FRAME_WIDTH  = 660;
   private static final int FRAME_HEIGHT = 500;
   private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
   private static int[] currentHand;
   private static boolean card1 = false;
   private static boolean card2 = false;
   private static boolean card3 = false;
   private static boolean card4 = false;
   private static boolean card5 = false;
      
   //first row
   private static JButton firstCard  = new JButton();
   private static JButton secondCard = new JButton();
   private static JButton thirdCard  = new JButton();
   private static JButton fourthCard = new JButton();
   private static JButton fifthCard  = new JButton();
   private static JButton startReset = new JButton("start");
   
   //second row   
   private JButton    fold    = new JButton("fold");
   private JButton    redraw  = new JButton("redraw");
   private JButton    quit    = new JButton("quit");
   
   //bottom display
   private JLabel displayInfo  = new JLabel();
   
   private static FiveCardDrawClient gameWindow;
   
   private DataOutputStream toServer;
   private DataInputStream fromServer;
   
   private static boolean ready  = false;
   private static boolean myTurn = false;
   
/**The main method that creates the JFrame and adds the FiveCardDrawGUI.
 */     
   public static void main(String[] args)
   {
      gameWindow = new FiveCardDrawClient();
      gameWindow.setTitle("Five Card Draw Poker");
      gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameWindow.setVisible(true);  
   }
   
/**Constructor.
 **Initalizes a newly created FiveCardDrawGUI object. It loads all 52 cards into an array list and creates the components.
 **Techniques for loading images learned from https://stackoverflow.com/questions/10263861/importing-an-image-file-adding-it-to-an-arraylist-and-then-displaying-the-imag 
 */       
   public FiveCardDrawClient()
   {
      BufferedImage temp = null;
           
      try
      {
         temp = ImageIO.read(new File("ace_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("2_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("3_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("4_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("5_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("6_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("7_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("8_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("9_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("10_of_spades.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("jack_of_spades2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("queen_of_spades2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("king_of_spades2.png"));
         images.add(resizeImage(temp));
         
         temp = ImageIO.read(new File("ace_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("2_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("3_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("4_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("5_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("6_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("7_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("8_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("9_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("10_of_hearts.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("jack_of_hearts2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("queen_of_hearts2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("king_of_hearts2.png"));
         images.add(resizeImage(temp));
         
         temp = ImageIO.read(new File("ace_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("2_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("3_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("4_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("5_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("6_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("7_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("8_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("9_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("10_of_clubs.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("jack_of_clubs2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("queen_of_clubs2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("king_of_clubs2.png"));
         images.add(resizeImage(temp));
         
         temp = ImageIO.read(new File("ace_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("2_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("3_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("4_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("5_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("6_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("7_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("8_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("9_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("10_of_diamonds.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("jack_of_diamonds2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("queen_of_diamonds2.png"));
         images.add(resizeImage(temp));
         temp = ImageIO.read(new File("king_of_diamonds2.png"));
         images.add(resizeImage(temp));        
      } 
      catch (IOException e)
      {
         System.out.println("Can't find image.");
      }
         
      createComponents();
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      
      
      //Creates the client side socket.
      try
      {
         Socket socket = new Socket("localhost", PORT);
         
         fromServer = new DataInputStream(socket.getInputStream());
         toServer = new DataOutputStream(socket.getOutputStream());
      }
      catch(IOException ex)
      {
         System.err.println(ex);
      }
      
      
      //Starts the thread.
      Thread thread = new Thread(this);
      thread.start();
   }    

/**Helps the constructor to set up all the panels and buttons include all the action listeners.
 */           
   private void createComponents()
   {
      JPanel motherPanel = new JPanel();
      motherPanel.setLayout(new BorderLayout());
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(2, 5));                  
      
      //first row
      firstCard.addActionListener(e -> selectCard("c1"));
      buttonPanel.add(firstCard);
      
      secondCard.addActionListener(e -> selectCard("c2"));
      buttonPanel.add(secondCard);
      
      thirdCard.addActionListener(e -> selectCard("c3"));
      buttonPanel.add(thirdCard);
      
      fourthCard.addActionListener(e -> selectCard("c4"));
      buttonPanel.add(fourthCard);
      
      fifthCard.addActionListener(e -> selectCard("c5"));
      buttonPanel.add(fifthCard);  
      
      //second row
      //start button
      startReset.addActionListener(e -> {try {startGame();} catch(IOException ex){}});
      buttonPanel.add(startReset);
 
      //fold button
      fold.addActionListener(e -> {try {fold();} catch(IOException ex){}});
      buttonPanel.add(fold);
       
      //redraw button     
      redraw.addActionListener(e -> {try { redrawCards();} catch(IOException ex){}});
      buttonPanel.add(redraw); 
      
      //quit button     
      quit.addActionListener(e -> {try {quit(); dispose();} catch (IOException ex){}});
      buttonPanel.add(quit);
      
      //mother panel add everything together.
      motherPanel.add(buttonPanel, BorderLayout.CENTER);
      displayInfo.setPreferredSize(new Dimension(800,60));
      motherPanel.add(displayInfo, BorderLayout.SOUTH);
      add(motherPanel);
   }

/**Resizes the card images to the desireable dimension.
 **Techniques learned from //https://stackoverflow.com/questions/16497853/scale-a-bufferedimage-the-fastest-and-easiest-way
 */    
   public static BufferedImage resizeImage(BufferedImage ori)
   {
      BufferedImage originalImage = ori;
      BufferedImage newImage = new BufferedImage(100, 150, BufferedImage.TYPE_INT_RGB);

      Graphics g = newImage.createGraphics();
      g.drawImage(originalImage, 0, 0, 100, 150, null);
      g.dispose();
      
      return newImage;
   }

/**Stores 5 unique random numbers between 0 and 51 inclusive. into a global array currentHands. Also return the same array.
 **No duplicate numbers will be stored.
 **@return an int array that contains 5 unique random number between 0 and 51 inclusive.
 */  
   private static int[] resetCards()
   {
      int i = 0;
	   int j = 0;
	   int[] hands = new int[5];	
	   int temp;
	   boolean noDuplicate = true;
	
	   //Resets the selections.
	   card1 = false;
	   card2 = false;
	   card3 = false;
	   card4 = false;
	   card5 = false;
	
	   //Assign 10 unique numbers between 0 and 51 into an array.
	   for (i = 0; i < hands.length; i++)
	   {		
		   do
		   {
			   noDuplicate = true;	
			   temp = (int)Math.floor((Math.random() * 52));
            
			   for (j = 0; j < hands.length; j++)
			   {
				   if (temp == hands[j])
				   {
					   noDuplicate = false;
				   }	
			   }	
		   } while (!noDuplicate);
			
         hands[i] = temp;	
      }
      
      //Sets the global variable currentHand and returns the array.
      currentHand = hands;
	   return hands;    	
	}	

/**Sends the NEW_GAME signal to the server. The game will start when both players clicked start.
 */   
   private void startGame() throws IOException
   {  
      toServer.writeInt(NEW_GAME);         
   }
   
/**Assigns card pictures to the 5 card button on the frame. Cards are based on the parameter array
 **that contains 5 unique random numbers between 0 and 51 inclusive.
 **Techniques learned from https://stackoverflow.com/questions/10263861/importing-an-image-file-adding-it-to-an-arraylist-and-then-displaying-the-imag.
 **@param arr an array containing 5 unique random numbers between 0 and 51 inclusive.
 */   
   private static void redeal(int[] arr)
   {
      ImageIcon icon;
      
      icon = new ImageIcon(images.get(arr[0]));
      firstCard.setIcon(icon);
      icon = new ImageIcon(images.get(arr[1]));
      secondCard.setIcon(icon);
      icon = new ImageIcon(images.get(arr[2]));
      thirdCard.setIcon(icon);
      icon = new ImageIcon(images.get(arr[3]));
      fourthCard.setIcon(icon);
      icon = new ImageIcon(images.get(arr[4]));
      fifthCard.setIcon(icon);     
   }

/**Highlights a card for redraw. A global boolean variable associated with that button will switch from truth to false and vice versa.
 **@param str a string indicating which button is pressed.
 */   
   private void selectCard(String str)
   {
      ImageIcon icon;
      
      //Each button has the same block of code. Highligted cards will dissappear from the frame.
      switch(str)
      {
         case "c1":  if (card1 == false)
					      {
						      displayInfo.setText("Card 1 is selected.");
                        firstCard.setIcon(null);
						      card1 = true;
					      }
					
					      else if (card1 == true)
					      {
                        icon = new ImageIcon(images.get(currentHand[0]));
						      displayInfo.setText("Card 1 is unselected.");
                        firstCard.setIcon(icon);
						      card1 = false;
					      }
					      break;
					
		   case "c2":	if (card2 == false)
					      {
						      displayInfo.setText("Card 2 is selected.");
                        secondCard.setIcon(null);
						      card2 = true;
					      }
					
					      else if (card2 == true)
					      {
                        icon = new ImageIcon(images.get(currentHand[1]));
						      displayInfo.setText("Card 2 is unselected.");
                        secondCard.setIcon(icon);
						      card2 = false;
					      }
					      break;
					
		   case "c3":	if (card3 == false)
					      {
						      displayInfo.setText("Card 3 is selected.");
                        thirdCard.setIcon(null);
						      card3 = true;
					      }
					
					      else if (card3 == true)
					      {
                        icon = new ImageIcon(images.get(currentHand[2]));
						      displayInfo.setText("Card 3 is unselected.");
                        thirdCard.setIcon(icon);
						      card3 = false;
					      }
					      break;
					
		   case "c4":	if (card4 == false)
					      {
						      displayInfo.setText("Card 4 is selected.");
                        fourthCard.setIcon(null);
						      card4 = true;
					      }
					
					      else if (card4 == true)
					      {
                        icon = new ImageIcon(images.get(currentHand[3]));
						      displayInfo.setText("Card 4 is unselected.");
                        fourthCard.setIcon(icon);
						      card4 = false;
					      }
					      break;
					
		   case "c5":	if (card5 == false)
					      {
						      displayInfo.setText("Card 5 is selected.");
                        fifthCard.setIcon(null);
						      card5 = true;
					      }
					
					      else if (card5 == true)
					      {
                        icon = new ImageIcon(images.get(currentHand[4]));
						      displayInfo.setText("Card 5 is unselected.");
                        fifthCard.setIcon(icon);
						      card5 = false;
					      }
					      break;
      }
   }

/**Redraws cards depend on which card button is highlighted by selectCard(String str).
 **The selected cards will be discarded and stored into an int array called discard so that they won't be drawn again.
 **New random cards are assigned to the card buttons.
 **Will not run if it's not the player's turn, governed by the boolean value myTurn. The server will send the signal SWITCH_TURN
 **when it is the player's turn.
 */   
   private void redrawCards() throws IOException
   {
      boolean noDuplicate = true;
	   int i = 0;
	   int temp;
	   int[] discard = new int[5];
	        
      if (myTurn == true)
      {
         toServer.writeInt(REDRAW);
         
         //Each card button has the same block of code.
   	   if (card1 == true)
   	   {	
   		   do
   		   {
   			   noDuplicate = true;	
   			   temp = (int)Math.floor(Math.random() * 52);
   			   for (i = 0; i < currentHand.length; i++)
   			   {
   				   if (temp == currentHand[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }
   			
   			   for (i = 0; i < discard.length; i++)
   			   {
   				   if (temp == discard[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }	
   		   } while (!noDuplicate);
   		
   		   discard[0] = currentHand[0];
   		   currentHand[0] = temp;	
   	   }	
   
   	   if (card2 == true)
   	   {	
   		   do
   		   {
   			   noDuplicate = true;	
   			   temp = (int)Math.floor(Math.random() * 52);
   			   for (i = 0; i < currentHand.length; i++)
   			   {
   				   if (temp == currentHand[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }
   				
   			   for (i = 0; i < discard.length; i++)
   			   {
   				   if (temp == discard[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }	
   		   } while (!noDuplicate);
   		
   		   discard[1] = currentHand[1];
   		   currentHand[1] = temp;	
   	   }
   
   	   if (card3 == true)
   	   {	
   		   do
   		   {
   			   noDuplicate = true;	
   			   temp = (int)Math.floor(Math.random() * 52);
   			   for (i = 0; i < currentHand.length; i++)
   			   {
   				   if (temp == currentHand[i])
   				   {
   					   noDuplicate = false;
   				   }
   			   }
   			
   			   for (i = 0; i < discard.length; i++)
   			   {
   				   if (temp == discard[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }		
   		   } while (!noDuplicate);
   		
   		   discard[2] = currentHand[2];	
   		   currentHand[2] = temp;	
   	   }
   
   	   if (card4 == true)
   	   {	
   		   do
   		   {
   			   noDuplicate = true;	
   			   temp = (int)Math.floor(Math.random() * 52);
   			   for (i = 0; i < currentHand.length; i++)
   			   {
   				   if (temp == currentHand[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }
   
   			   for (i = 0; i < discard.length; i++)
   			   {
   				   if (temp == discard[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }			
   		   } while (!noDuplicate);
   		
   		   discard[3] = currentHand[3];
   		   currentHand[3] = temp;	
   	   }
   
   	   if (card5 == true)
   	   {	
   		   do
   		   {
   			   noDuplicate = true;	
   			   temp = (int)Math.floor(Math.random() * 52);
   			   for (i = 0; i < currentHand.length; i++)
   			   {
   				   if (temp == currentHand[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }
   
   			   for (i = 0; i < discard.length; i++)
   			   {
   				   if (temp == discard[i])
   				   {
   					   noDuplicate = false;
   				   }	
   			   }	
   		   } while (!noDuplicate);
   		
   		   discard[4] = currentHand[4];	
   		   currentHand[4] = temp;	
   	   }
   	
   	   redeal(currentHand);
         
         //selection reset
         card1 = false;
   	   card2 = false;
   	   card3 = false;
   	   card4 = false;
   	   card5 = false;
      }
      
      else
      {
         displayInfo.setText("Not your turn.");

      }

   }

/**Sends the FOLD signal to the server. The other player will win.
 */ 
   private void fold() throws IOException
   {
      toServer.writeInt(FOLD);
   }

/**Sends the QUIT signal to the server. The client window will be closed and server thread will end.
 */    
   private void quit() throws IOException
   {
      toServer.writeInt(QUIT);
   }

/**Runs the thread. Will continously wait for server instructions.
 **When first created will assign the player to player 1 if this player is the first to join the server.
 **Otherwise assigns the player to player 2.
 */ 
   public void run()
   {     
      try
      {
         int player = fromServer.readInt();
         
         if (player == PLAYER1)
         {
            
            displayInfo.setText("You are player 1. You go first.");
            gameWindow.redeal(resetCards());
            myTurn = true;
            
         }
         
         else if (player == PLAYER2)
         {
            
            displayInfo.setText("You are player 2. You go second.");
            gameWindow.redeal(resetCards());
            myTurn = false;
         }
         
         //Continously wait for server instrcutions.
         while (true)
         {
            int cmd = fromServer.readInt();
            
            switch (cmd)
            {
               case SWITCH_TURN: gameWindow.switchTurn();
                                 break;

               case NOTIFY_TURN: displayInfo.setText("It is your turn.");
                                 break;
                                                                  
               case PLAYER1_WON: if (player == PLAYER1)
                                 {
                                    displayInfo.setText("You won. Press start to start again.");
                                 }
                                 
                                 else
                                 {
                                    displayInfo.setText("You lost. Press start to start again.");
                                 }
                                 
                                 break;
                                 
               case PLAYER2_WON: if (player == PLAYER2)
                                 {
                                    displayInfo.setText("You won. Press start to start again.");
                                 }
                                 
                                 else
                                 {
                                    displayInfo.setText("You lost. Press start to start again.");
                                 }
                                 
                                 break; 
                                               
               case TIE:         displayInfo.setText("Tie Game. Press start to start again.");
                                 break;
                                 
               case READY:       displayInfo.setText("New game starts. You are player " + player);
                                 redeal(resetCards());                                   
                                 break;
                                 
               case WAITING:     displayInfo.setText("You are ready, waiting for other player to ready.");
                                 break;
               
               //Provide the info of the player's hand to the server.                  
               case HAND:        toServer.writeInt(currentHand[0]);
                                 toServer.writeInt(currentHand[1]);
                                 toServer.writeInt(currentHand[2]);
                                 toServer.writeInt(currentHand[3]);
                                 toServer.writeInt(currentHand[4]);
                                 break;
                                 
               default:          break;
            }              
         }
         
      }
      catch (Exception ex)
      {
      }
   }

/**After the client receives the SWITCH_TURN signal from the server. this method switches myTurn so redraw() can be called.
 */    
   private static void switchTurn()
   {
      if (myTurn == true)
      {
         myTurn = false;

      }
      
      else if (myTurn == false)
      {
         myTurn = true;

      }
   }
}
