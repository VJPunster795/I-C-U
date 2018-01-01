# Reversi-Othello
College project that implements the game of Reversi/Othello 

This is a college semester project that implements the game of Reversi on a n * n board, where n is an integer. Reversi is a strategy board game played by two players and both
players take turns to make their move which consists of placing one piece down in a legally acceptable position. The goal is to turn any of
opposing player's pieces to their side when applicable and the winner is the player who has more discs of their color than the opponent at the end
of the game. The end of the game is reached when neither of the players have a legal move or there are no spaces left on the board. 

In the implementation, there are only two colors the player can choose and they are black and white, where one player chooses black/white 
and the opponent chooses the opposite color. At their turn, the player will choose the location they want to place their color disc on one of
the empty squares of the board, adjacent to an opponent's disc. No two matching colors are connected vertically or horizontally so a miniature-chequered pattern is made.

During the beginning of the game, two characters of the letters "B" for black and "W" for white are placed in the middle of the board. Black always 
begins first, and the two players subsequently take turns moving. 

A legal move is one that consists of a colored piece - black or white - being placed on the board that creates a straight line whether horizontal
or vertical made up of the same color piece at either end and only the opposite color pieces in between. When a player achieves this, they must complete
the move by turning any of the opposite color pieces in between the player's color so that the line becomes the player's color entirely. 

This project is written entirely in Java and consists of three java files "Board.java", "Player.java", and "TestGame.java". All three java files contain comments that are descriptions
of the functions and variables implemented in them. A UML of the project design is provided under "Reversi_UML.jpg". When executing the program through "TestGame.java", four options will be shown 
with the fourth option to exit the program. The user should select the option out of the ones provided and entering an illegal character will cause the menu to loop again and ask the user
to select an option.


