Bruce Yi Chen Zhao
100298997
Nov.29

Files included in the submission:

FiveCardDrawServer.java - the server
FiveCardDrawClient.java - the client
FiveCardDrawConstants.java - protocol
PokerHandRanking.java - program that determines the winning hand. 
Card images for all 52 cards.
README.txt - includes a short instruction at the end.

Comments:
-PokerHandRanking.java works only on this project, wrote
entirely by myself without outside influences. I did
read a few program on the internet that do the same
thing but they contain too much unfamiliar knowledge
so I decided to write one myself using the knowledge
I already learned.

-PokerHandRanking.java may be hard to read especially the
tieBreaker() method because of the sheer amount of if/else if statements.
I was able to keep track and not get lost partly due to me being an experienced
poker player so I was comfortable working with poker hands. For eveyone else
it can be very difficult to keep track of everything. Though I am fairly confident
that the program works well.

-I modeled my server and client program on the six examples from
the project description pdf. Especially the tic tac toe program. 
I found it very useful to get me started.

-I completed scraped the money/betting system I envisioned last week.
I ultimately decided that it would be way too difficult to implement
and it is virtually impossible for me to do under the time constraint.

Instruction:

The game starts automatically once two players are connected. The goal of the game is to make 
the best poker hand possible by discarding useless cards and redraw new cards. The GUI will tell 
you if it's your turn. When it is your turn select cards by clicking on them then click redraw to
discard selected cards to draw new cards. Once both players finish redrawing, the game will automatically
choose a winner based on the strength of both player's hand. Once the hand is over click start to
ready and once both player are ready the server will start another game.