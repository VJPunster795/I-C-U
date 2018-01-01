/*
* Name: Vijayagopal Krishnan (V.J)
* NetID: vxk131830
* Date: 04/18/2016
* CS 2336 S16
* Tester file for the reversi game
*
 */

public class TestGame
{
    public static void main (String [] args)
    {
        int row;
        String option;
        java.util.Scanner keyboard = new java.util.Scanner(System.in);
        do
        {
            System.out.print("Please enter one value for both the row and column. Must be even and greater than equal to 4: ");
            row = keyboard.nextInt();
            if ((row%2) != 0 || (row < 4))
                System.out.println("Error, value must be even and greater than equal to 4");
        }
        while ((row%2) != 0 || (row < 4));
        int col = row;
        Board game = new Board (row,col);
        Player player1 = new Player ("Player 1", 0);
        Player player2 = new Player ("Player 2", 0);
        do
        {
           System.out.println("a. Player vs Computer \n b. Computer vs Computer \n c. Player vs Player \n d.Quit ");
            System.out.print("Please choose an option: ");
            option = keyboard.next();
            switch (option)
            {
                case "a":
                    game.playPlayerVersusComputer(player1);
                    break;
                case "b":
                    game.playComputerVersusComputer();
                    break;
                case "c":
                    game.playPlayerVersusPlayer(player1,player2);
                    break;
                case "d":
                    break;
            }
        }
        while (!option.equals("d"));
    }
}
