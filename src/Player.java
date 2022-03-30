public class Player {
    private String name;
    private int winningsTemp;
    private int winningsTotal;

    //constructor for player object
    public Player() {
        winningsTemp = 0;
        winningsTotal = 0;
    }

    //sets the player's name
    public void setName(String n) {
        name = n;
    }

    //gets the player's name
    public String getName() {
        return name;
    }

    //sets the player's winnings for the current game
    public void setWinningsTemp(int wt) {
        winningsTemp = wt;
    }

    //sets the player's winnings for the total match
    public void setWinningsTotal(int wt) {
        winningsTotal = wt;
    }

    //gets the player's winnings for the current game
    public int getWinningsTemp() {
        return winningsTemp;
    }

    //gets the player's winnings for the total match
    public int getWinningsTotal() {
        return winningsTotal;
    }

    //subtracts $250 from the current player if they buy a vowel
    public void payForVowel() {
        winningsTemp -= 250;
    }
}