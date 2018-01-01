/*
* Name: Vijayagopal Krishnan (V.J)
* NetID: vxk131830
* Date: 04/18/2016
* CS 2336 S16
* - Analysis -
* The goal of this project is to create a game of reversi/othello using the instances of the Player class, a 2D Array to create a game board
* and various private methods that perform various tasks like verifying a legal move, searching for adjacent spaces,verifying whether there are any legal
* moves left in the board, to check whether the board is full, printing the board, performing a legal move and checking which player/opponent won the match.
* What each method does is mentioned and explained in the comments bellow.
*
 */


public class Board
{
    // 2D array instance attribute
 private char gameBoard[][];
    // character constants for black and white
    public final char WHITE = 'W';
    public final char BLACK = 'B';

    public Board(int row, int col)
    {
        //creates gameBoard with row and col as it's vertical and horizontal size
      gameBoard = new char [row][col];
        //designs the board with '_' except for the midRow, midCol, midRow+1 and midCol+1
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
            {
                // assigns 'W' to midRow midCol and midRow+1 and midCol+1
                if ( ( (i == ((gameBoard.length/2)-1)) && (j == ((gameBoard[0].length/2)-1)) )
                        ||( (i == (gameBoard.length/2)) && (j == (gameBoard[0].length/2)) ) )
                    gameBoard[i][j] = WHITE;
                //assigns 'B' to midRow midCol+1 and midRow+1 midCol
                else if ( ( (i == ((gameBoard.length/2)-1)) && (j == gameBoard[0].length/2) )
                        || ( (i == ((gameBoard.length/2)) ) && (j == ((gameBoard[0].length/2)-1)) ))
                    gameBoard[i][j] = BLACK;
                else
                //assigns '_' to everywhere else
                gameBoard[i][j] = '_';
            }
    }


    private void printBoard()
    {
        // for each loop for navigating through each array
        for (char[] aGameBoard : gameBoard)
        {
            //for each loop for navigating through each character
            for (char anAGameBoard : aGameBoard)
                System.out.print(anAGameBoard + "  ");
            //makes it so that the next row of characters are printed in the next line
            System.out.print('\n');
        }
    }

    // Executes a Player vs Computer game. Uses the Player object player1 as an argument/parameter
    public void playPlayerVersusComputer(Player player1)
    {
        // print the board for reference
        printBoard();
        //creates a Player object called computer
        Player computer = new Player("Computer", 0);
        //makes it so that the choice of who plays first (gets the color Black) is randomized
        if((Math.random() * 10) > 5)
         {
            System.out.println("Player's color is Black, Computer's color is White");
             // sets the color for the Player
            player1.setColor_Status(BLACK);
            computer.setColor_Status(WHITE);
         }
        else
         {
            System.out.println("Computer's color is Black, Player's color is White");
            computer.setColor_Status(BLACK);
            player1.setColor_Status(WHITE);
         }
        // does a do while loop so that the loop runs at least one iteration before stopping the game
        do
         {
          if(player1.getColor_Status() == BLACK)
          {
              //checks if there are any legal moves for the Player
              if (isThereLegalMove(player1))
              {
                  //executes the player's turn
                  playerMove(player1);
              }
              else
                  System.out.println("There are no legal moves for " + player1.getName());
              //displays the board along with the scores for the Player and Computer
              showBoardAndScores(player1,computer);
              //checks if there are any legal moves for the Computer
              if (isThereLegalMove(computer))
               {
                   //executes the computer's turn
                  computerMove(computer);
               }
              else
                  System.out.println("There are no legal moves for " + computer.getName());
              showBoardAndScores(player1,computer);
          }
          else
          {
              if (isThereLegalMove(computer))
              {
                  computerMove(computer);
              }
              else
                  System.out.println("There are no legal moves for " + computer.getName());
              showBoardAndScores(player1,computer);
              if(isThereLegalMove(player1))
              {
                  playerMove(player1);
              }
              else
                  System.out.println("There are no legal moves for " + player1.getName());
              showBoardAndScores(player1,computer);
          }
         }
        //condition for the loop; stops the game if neither side cannot preform a legal move and if the board is full
        while (isThereLegalMove(computer) || isThereLegalMove(player1) && !isFull());
        //Checks if the player's score is greater than the computer's score or vice versa and then decides who is the winner
        if (player1.getScore() > computer.getScore())
            System.out.println("Game over, Player wins");
        else if (computer.getScore() > player1.getScore())
            System.out.println("Game over, Computer wins");
        else
        //if the scores are tied
            System.out.println("Game over, it's a tie");
    }


// Executes a Computer vs Computer game
    public void playComputerVersusComputer()
    {
        // constructs the two Player objects for Computer1 and Computer2 respectively
        Player computer1 = new Player ("Computer1", 0);
        Player computer2 = new Player ("Computer2", 0);
        //prints the game board for reference
        printBoard();
        //determines who plays first (who gets the color Black)
        if((Math.random() * 10) > 5)
         {
            System.out.println("Computer1's color is Black, Computer2's color is White");
            computer1.setColor_Status(BLACK);
            computer2.setColor_Status(WHITE);
         }
        else
         {
            System.out.println("Computer1's color is Black, Computer2's color is White");
            computer1.setColor_Status(BLACK);
            computer2.setColor_Status(WHITE);
         }
        //  does a do while loop so that the loop runs at least one iteration before stopping the game
        do
         {
            if(computer1.getColor_Status() == BLACK)
            {
                //checks if computer1 has a legal move or not
                if (isThereLegalMove(computer1))
                {
                    //executes the turn of the computer
                    computerMove(computer1);
                }
                else
                    System.out.println("There are no legal moves for " + computer1.getName());
                showBoardAndScores(computer1,computer2);
                if (isThereLegalMove(computer2))
                {
                    //executes the turn of the computer
                    computerMove(computer2);
                }
                else
                    System.out.println("There are no legal moves for " + computer2.getName());
                //displays the scores of computer1 and computer2
                showBoardAndScores(computer1, computer2);
            }
            else
             {
                 if(isThereLegalMove(computer2))
                {
                    computerMove(computer2);
                }
                else
                    System.out.println("There are no legal moves for " + computer2.getName());
                showBoardAndScores(computer1,computer2);
                if (isThereLegalMove(computer1))
                {
                    computerMove(computer1);
                }
                else
                    System.out.println("There are no legal moves for " + computer1.getName());
                showBoardAndScores(computer1, computer2);
            }
         }
        //checks whether neither computer1 nor computer2 have legal moves or if the board is full
        while (isThereLegalMove(computer1) || isThereLegalMove(computer2) && !isFull());

        //determines the winner based on the scores of both computer1 and computer2
        if (computer1.getScore() > computer2.getScore())
            System.out.println("Game over, Computer2 wins");
        else if (computer1.getScore() < computer2.getScore())
            System.out.println("Game over, Computer2 wins");
        else
            System.out.println("Game over, it's a tie");
    }

    //executes a game of Player vs Player (double player). Uses Player objects p1 and p2 as arguments/parameters

    public void playPlayerVersusPlayer(Player p1, Player p2)
     {
         // prints board for reference
        printBoard();
         //determines who plays first
        if((Math.random() * 10) > 5)
        {
            System.out.println("Player 1's color is Black, Player 2's color is White");
            p1.setColor_Status(BLACK);
            p2.setColor_Status(WHITE);
        }
        else
        {
            System.out.println("Player 2's color is Black, Player 1's color is White");
            p2.setColor_Status(BLACK);
            p1.setColor_Status(WHITE);
        }
         // does a do while loop so that the loop runs at least one iteration before stopping the game
        do
        {
            if(p1.getColor_Status() == BLACK)
            {
                //checks if there are legal moves for Player 1
                if (isThereLegalMove(p1))
                {
                    //executes the turn of Player 1
                    playerMove(p1);
                }
                else
                    System.out.println("There are no legal moves for " + p1.getName());
                //displays the scores for Player 1 and 2 along with the board
                showBoardAndScores(p1,p2);
                //checks if there are legal moves for Player 2
                if (isThereLegalMove(p2))
                {
                    //executes the turn of Player 2
                    playerMove(p2);
                }
                else
                    System.out.println("There are no legal moves for " + p2.getName());
                showBoardAndScores(p1,p2);
             }
            else
            {
                if (isThereLegalMove(p2))
                {
                    playerMove(p2);
                }
                else
                    System.out.println("There are no legal moves for " + p2.getName());
                showBoardAndScores(p1,p2);
                if(isThereLegalMove(p1))
                {
                    playerMove(p1);
                }
                else
                    System.out.println("There are no legal moves for " + p1.getName());
                showBoardAndScores(p1,p2);
            }
        }
        //checks whether neither player1 nor player2 have legal moves or if the board is full
        while (isThereLegalMove(p1) || isThereLegalMove(p2) && !isFull());
         //determines who is the winner based on the scores of Player 1 and 2
        if (p1.getScore() > p2.getScore())
            System.out.println("Game over, Player 1 wins");
        else if (p2.getScore() > p1.getScore())
            System.out.println("Game over, Player 2 wins");
        else
            System.out.println("Game over, it's a tie");
    }

    //searches for the left of the array to see if a legal move can be made. Takes two integers playerRow and playerCol and a Player object p as its
    //parameters/arguments. A legal move is a move where the color of the Player's two pieces are at either end and in between them contain pieces
    // of the opposite color (the opponent's color). This method searches for an index to the left, such that in between the result left index and the column
    //given are pieces of the opponent color in the board. [W B B B _] for example where '_' is the location the piece of playerRow and playerCol is going
    // to be placed.

    private int searchLeft(int playerRow, int playerCol, Player p)
     {
         //searches from the left index onwards not including the current location
            for (int i = playerCol-1; i > 1; i-- )
            {
                if (gameBoard[playerRow][i] == '_')
                    //if any of the left indexes contain '_' then it returns -1
                    return -1;
                else if (gameBoard[playerRow][i-1] == p.getColor_Status())
                    // returns the index  where a legal move can be made
                    return i;
            }
         //returns -1 if it's unable to find such an index
        return -1;
     }
    //searches for the right of the array to see if a legal move can be made. Takes two integers playerRow and playerCol and a Player object p as its
    //parameters/arguments. A legal move is a move where the color of the Player's two pieces are at either end and in between them contain pieces
    // of the opposite color (the opponent's color). This method searches for an index to the right, such that in between the result right index and the column
    //given are pieces of the opponent color in the board. [_ B B B W] for example, where '_' is the location the piece of playerRow and playerCol is going
    // to be placed.

    private int searchRight(int playerRow, int playerCol, Player p)
     {
         //searches from the right index onwards not including the current location
            for (int i = playerCol+1; i < gameBoard[playerRow].length-1; i++)
            {
                if (gameBoard[playerRow][i] == '_')
                    //returns -1 if any of the right indexes contains '_'
                    return -1;
                else if (gameBoard[playerRow][i+1] == p.getColor_Status())
                    //returns the index  where a legal move can be made
                    return i;

        }
         //returns -1 if it's unable to find such an index
       return -1;
     }
    //searches upwards the the array to see if a legal move can be made. Takes two integers playerRow and playerCol and a Player object p as its
    //parameters/arguments. A legal move is a move where the color of the Player's two pieces are at either end and in between them contain pieces
    // of the opposite color (the opponent's color). This method searches for an index upwards, such that in between the result upwards index and the column
    //given are pieces of the opponent color in the board. [W B B B _] for example, where '_' is the location the piece of playerRow and playerCol is going
    // to be placed.
    private int searchUpwards(int playerRow, int playerCol, Player p)
     {
         //searches from the upwards index onwards not including the current location
            for (int i = playerRow-1; i > 0; i--)
            {
                if (gameBoard[i][playerCol] == '_')
                    //returns -1,if it contains '_' in any of the upper indexes
                    return -1;
                else if (gameBoard[i][playerCol] == p.getColor_Status())
                    //returns the index where the legal move can be made
                    return i;
               }
        return -1;
     }
    //searches the array downwards to see if a legal move can be made. Takes two integers playerRow and playerCol and a Player object p as its
    //parameters/arguments. A legal move is a move where the color of the Player's two pieces are at either end and in between them contain pieces
    // of the opposite color (the opponent's color). This method searches for an index downwards, such that in between the result downwards index and the column
    //given are pieces of the opponent color in the board. [W B B B _] for example where '_' is the location the piece of playerRow and playerCol is going
    // to be placed.
    private int searchDownwards(int playerRow, int playerCol,Player p)
     {
         //searches from the downwards index onwards not including the current location
            for (int i = playerRow+1; i < gameBoard.length; i++)
            {
                //returns -1 if any index downwards contains '_'
                if (gameBoard[i][playerCol] == '_')
                    return -1;
                //returns the index where the legal move can be made
                if (gameBoard[i][playerCol] == p.getColor_Status())
                    return i;
            }
        return -1;
     }

    //replaces whatever character value that is in the array column from playCol to the index in the argument/parameter
    // with the color of the Player's pieces
    private void replaceToTheLeft(int playerRow, int playerCol,Player p,int index)
     {
         //goes from right to left
        for (int i = playerCol; i >= index ; i--)
        {
            //replaces the value of this location with the color of the Player's pieces
            gameBoard[playerRow][i] = p.getColor_Status();
        }
     }
    //replaces whatever character value that is in the array column from playCol to the index in the argument/parameter
    // with the color of the Player's pieces
    private void replaceToTheRight(int playerRow, int playerCol,Player p,int index)
     {
         //goes from left to right
        for (int i = playerCol; i <= index; i++)
        {
            //replaces the value of this location with the color of the Player's pieces
            gameBoard[playerRow][i] = p.getColor_Status();
        }
     }
    //replaces whatever character value that is in the array column from playRow to the index in the argument/parameter
    // with the color of the Player's pieces
    private void replaceUpwards(int playerRow, int playerCol,Player p,int index)
     {
         //goes from down to up
        for (int i = playerRow; i >= index; i-- )
        {
            //replaces the value of this location with the color of the Player's pieces
            gameBoard[i][playerCol] = p.getColor_Status();
        }
     }

    //replaces whatever character value that is in the array column from playRow to the index in the argument/parameter
    // with the color of the Player's pieces
    private void replaceDownwards(int playerRow, int playerCol,Player p,int index)
    {
        //goes from up to down
        for (int i = playerRow; i <= index; i++)
        {
            //replaces the value of this location with the color of the Player's pieces
            gameBoard[i][playerCol] = p.getColor_Status();
        }
    }

    //checks if the left adjacent spot of the current location has the same color as the player's piece or not
    private boolean isAdjacentLeft(int row, int col, Player p)
    {
        if (col != 0)
            if (gameBoard[row][col - 1] == p.getColor_Status())
                //returns true if it has the same color
            return true;
        return false;
    }
    //checks if the right adjacent spot of the current location has the same color as the player's piece or not
    private boolean isAdjacentRight(int row, int col, Player p)
    {
     if (col != gameBoard[row].length - 1)
         if (gameBoard[row][col + 1] == p.getColor_Status())
             //returns true if it has the same color
             return true;
     return false;
    }
    //checks if the upper adjacent spot of the current location has the same color as the player's piece or not
    private boolean isAdjacentUp (int row, int col, Player p)
    {
        if (row != 0)
            if (gameBoard[row - 1][col] == p.getColor_Status())
                //returns true if it has the same color
            return true;
        return false;
    }
    //checks if the bottom adjacent spot of the current location has the same color as the player's piece or not
    private boolean isAdjacentDown (int row, int col, Player p)
    {
        if (row != gameBoard.length - 1)
            if (gameBoard[row + 1][col] == p.getColor_Status())
                //returns true if it has the same color
                return true;
        return false;
    }
//checks if the game board is full/does not contain any free spaces
    private boolean isFull()
    {
        boolean result = false;
        for (char[] aGameBoard : gameBoard) {
            for (char anAGameBoard : aGameBoard)
                if (anAGameBoard != '_')
                    result = true;
                else
                {
                    return false;
                }
        }
       return result;
    }
//checks if the move entered by the player or radomized by the computer is legal or not. It checks that by seeing whether the current spot does not
// contain a '_' or is black and there's at least one direction where the adjacent side does not have the same color and there's an index such that a legal
//move can be made . Takes integers row, col and a Player object p as arguments/parameters
    private boolean isLegal(int row, int col, Player p) {
        return (gameBoard[row][col] == '_') && ((!isAdjacentUp(row, col, p) && (searchUpwards(row, col, p) != -1)) || ((!isAdjacentDown(row, col, p)) && ((searchDownwards(row, col, p)) != -1))
                || ((!isAdjacentRight(row, col, p)) && (searchRight(row, col, p) != -1)) || ((!isAdjacentLeft(row, col, p)) && (searchLeft(row, col, p) != -1)));
    }

    //executes the move made by the player/computer. Takes two integers row and column and a Player object p as its arguments/parameters
    private void makeMove(int playerRow, int playerCol, Player p)
    {
        // records the indexes where a legal move is possible to make
            int upIndex = searchUpwards(playerRow, playerCol, p);
            int downIndex = searchDownwards(playerRow, playerCol, p);
            int rightIndex = searchRight(playerRow, playerCol, p);
            int leftIndex = searchLeft(playerRow, playerCol, p);
        //checks if any of the indexes does not contain -1 and executes their respective method
            if (upIndex!=-1)
                //performs a legal move upwards
                replaceUpwards(playerRow,playerCol,p,upIndex);
            if (downIndex !=-1)
                //performs a legal move downwards
                replaceDownwards(playerRow,playerCol,p,downIndex);
            if (leftIndex !=-1)
                //performs a legal move leftwards
                replaceToTheLeft(playerRow,playerCol,p,leftIndex);
            if (rightIndex !=-1)
                //performs a legal move rightwards
                replaceToTheRight(playerRow,playerCol,p,rightIndex);
        //prints a message to ensure the player/computer that their move is sucessful
            System.out.println("Success " + p.getColor_Status() + " move at (" + playerRow + "," + playerCol + ")");
    }

    private void playerMove(Player p1)
    {
        int playerRow;
        int playerCol;
        //Scanner to be used to record the player's desired location to move their piece
        java.util.Scanner keyboard = new java.util.Scanner(System.in);
        //prompts the player to enter the row and column they wish to place their piece
        System.out.print("Enter the row and column you wish to place your piece: ");
        do
        {
            playerRow = keyboard.nextInt();
            playerCol = keyboard.nextInt();
            //if the move goes out of bounds, prints an error and prompts them to try again
            if ((playerRow < 0 || playerRow >= gameBoard.length) || (playerCol < 0 || playerCol >= gameBoard.length))
                System.out.print("Error, the number you entered goes out of bounds. " +
                        "Please enter the row and column you wish to place your piece: ");
            //if the move is not legal, prompts the player to try again
            else if (!isLegal(playerRow, playerCol, p1))
                System.out.print("Error, not a legal move. Please enter the row and column you wish to place your piece: ");
        }
        //loops until the player enters a legal move
        while (!isLegal(playerRow,playerCol,p1));
        //executes the move of the player
        makeMove(playerRow,playerCol,p1);
    }

    private void computerMove(Player comp)
    {
        //boolean variable to check whether a location where a legal move can be made is found or not
        boolean foundLocation = false;
        //checks throughout the game board to find a location such that a legal move can be made
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
                if (isLegal(i, j, comp)) {
                    //executes the move of the computer when location is found
                    makeMove(i, j, comp);
                    foundLocation = true;
                    //breaks out of the inner loop
                    break;
                }
            //uses the vairable to break out of the outer loop and hence the method
            if (foundLocation)
                break;
        }
    }

    //updates the current scores for two players. Takes Player objects player1 and player2 as arugments/parameters
    private void updateScores(Player p1, Player comp)
    {
        int pScore = 0;
        int cScore = 0;
        for (char[] aGameBoard : gameBoard)
        {
            for (char anAGameBoard : aGameBoard)
            {
                //counts how many of the player's pieces are on the board
                if (anAGameBoard == p1.getColor_Status())
                    pScore++;
                else if (anAGameBoard == comp.getColor_Status())
                    cScore++;
            }
        }
        //stores the values of the scores into their scores attribute variable
        p1.setScore(pScore);
        comp.setScore(cScore);
    }

    //updates the scores and displays the game board along with the scores of Player objects p1 and p2 used as arguments/parameters
    private void showBoardAndScores (Player p1, Player p2)
    {
        //updates the scores for p1 and p2
        updateScores(p1, p2);
        //prints the board
        printBoard();
        //prints the numerical ver of the scores for p1 and p2
        System.out.println( p1.getName()  + " : " + p1.getScore() + " " + p2.getName() + ": " + p2.getScore());
    }

//checks if there are any legal moves in the board for the Player object p used as the argument/parameter
    private boolean isThereLegalMove(Player p)
    {
        for (int i = 0; i < gameBoard.length; i++)
            for (int j = 0; j < gameBoard[i].length; j++)
                if(isLegal(i,j,p))
                    //returns true if a legal move could be found
                    return true;
        return false;
    }
}
