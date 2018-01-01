/*
* Name: Vijayagopal Krishnan (V.J)
* NetID: vxk131830
* Date: 04/18/2016
* CS 2336 S16
*
*
* - Analysis -
* Creates a Player class to be used in the Board class for the project. It contains the attributes name for the Player's name, score for the Player's score
* during the game and color_Status to record the color piece the Player has.
*
 */
public class Player
{
    //data attributes for the Player class
  private String name;
    private int score;
    private char color_Status;

//constructor for the player class. Uses the string variable name and int variable score as parameters/arguments
    public Player(String name, int score)
    {
        //updates the respective attributes
        this.name = name;
        this.score = score;
    }
//Accessors for the data attributes
    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public char getColor_Status()
    {
        return color_Status;
    }

// mutators for the data attributes
    public void setName(String name)
    {
        this.name = name;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setColor_Status(char color_Status)
    {
        this.color_Status = color_Status;
    }
}
