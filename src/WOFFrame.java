import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/* Ben Vicinelli
 * CSC-112 Fall 2021
 * Programming Assignment 5
 * November 30, 2021
 * This program recreates the Wheel of Fortune game, using GUI components to create an interactive experience
 * where the user can click buttons and enter input on a screen to see results of the game.
 */
/* INSTRUCTIONS:
    - When a player's turn begins, they have the option to click one of three buttons. The "SPIN WHEEL" button spins a
      wheel with outcomes of "lose a turn", "bankrupt", or $100, $200, $300, $400, $500, $600. If the player successfully
      guesses a consonant in the word, they recieve the number of consonants in the word times the amount of money spun.
      The "BUY A VOWEL" button allows the player to spend $250 to buy a vowel. The "SOLVE" button allows the player to
      attempt to solve the puzzle. Each player's money for the current game and money for the total match are kept on
      the screen. The player only recieves money from a game if they win that game. The puzzle is updated towards the top
      of the screen and the game number is updated towards the bottom. Simplified instructions are also on the screen.
 */
/* GUI OBJECTS USED:
    - JTABLE: I used a JTable as one of my GUI objects that is used to display simplified instructions of what each button does
      onto the screen.
    - JOPTIONPANE: I used multiple JOptionPane's throughput my code for different purposes. Some simply displayed a message,
      such as notifying a user when their guess was incorrect. Other's promted for input, such as asking the user for a
      consonant; I then took that input and was able to check if it was correct or not; based on whether it was correct or
      not, I used another JOptionPane to display another message.
 */
/* SPECIAL FEATURE:
    - My special feature for this program is that the user can enter their own name. As opposed to just "player 1" and "player 2",
      the user is asked to input their name with a JOptionPane at the beginning of the game, allowing a user to add a personal
      touch to the game.
 */


public class WOFFrame extends JFrame implements ActionListener{

    //here are all my GUI objects
    private JLabel wofLabel;
    private JLabel gameMoney1;
    private JTextField puzzleText;
    private JTextField gameMoneyText1;
    private JLabel gameMoney2;
    private JTextField gameMoneyText2;
    private JLabel matchMoney1;
    private JTextField matchMoneyText1;
    private JLabel matchMoney2;
    private JTextField matchMoneyText2;
    private JLabel gameNum;
    private JTextField gameNumText;
    private JButton spinButton;
    private JButton buyVowelButton;
    private JButton solveButton;
    private JLabel playerLabel1;
    private JLabel playerLabel2;
    private JLabel whoseTurn;
    JFrame newWindow = new JFrame();
    JTable money;

    //here are all my fields
    private String[] hiddenPhrases = {"gone with the wind", "edward scissorhands", "the wizard of oz", "avatar"};
    private int[] spin = {0, 1, 100, 200, 300, 400, 500, 600};
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Player currentPlayer = new Player();
    int spinResultInt;
    int numPuzzle = 0;
    String hiddenPhrase = hiddenPhrases[numPuzzle];
    private StringBuffer shownPhrase;

    //this method sets player names and randomizes who will go first, as well as initializes the shown phrase. More importantly,
    //this method holds all of my GUI components and creates the JFrame, JLabels, JTextFields, JButtons and the JTable
    public WOFFrame() {
        //sets layout and gridbagconstraints
        setLayout(new GridBagLayout());
        GridBagConstraints positionConst = new GridBagConstraints();

        String player1name = JOptionPane.showInputDialog(newWindow, "Player 1, what is your name?");
        String player2name = JOptionPane.showInputDialog(newWindow, "Player 2, what is your name?");
        System.out.println(player1name);
        System.out.println(player2name);

        player1.setName(player1name);
        player2.setName(player2name);

        //randomizes which player goes first
        Random randGen = new Random( );
        int whoGoesFirst = randGen.nextInt(2);
        if (whoGoesFirst == 0) {
            currentPlayer = player1;
        }
        else {
            currentPlayer = player2;
        }

        //initializes the shown phrase to match the hiddenphrase, but with underscores instead of letters
        shownPhrase = new StringBuffer("");
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            shownPhrase.append('—');
            if (hiddenPhrase.charAt(i) == ' ') {
                shownPhrase.setCharAt(i, ' ');
            }
        }

        setTitle("Wheel of Fortune");   //sets title

        //JLabel for which player's turn it is
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 2;
        positionConst.gridy = 1;
        whoseTurn = new JLabel("It is " + currentPlayer.getName() + "'s turn.");
        add(whoseTurn, positionConst);

        //JLabel that restates the title
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 2;
        positionConst.gridy = 0;
        wofLabel = new JLabel("WHEEL OF FORTUNE");
        wofLabel.setFont(new Font("Helvetica", Font.BOLD, 30));     //sets font
        add(wofLabel, positionConst);

        //JTextField that shows the empty phrase (which is updated throughout)
        positionConst.gridx = 2;
        positionConst.gridy = 3;
        puzzleText = new JTextField(shownPhrase.toString(), 20);
        puzzleText.setFont(new Font("Helvetica", Font.BOLD, 16));       //sets font
        puzzleText.setEditable(false);
        add(puzzleText, positionConst);
        puzzleText.addActionListener(this);

        //"spin wheel" JButton
        positionConst.gridx = 2;
        positionConst.gridy = 4;
        spinButton = new JButton("SPIN WHEEL");
        add(spinButton, positionConst);
        spinButton.addActionListener(this);

        //"buy a vowel" JButton
        positionConst.gridx = 2;
        positionConst.gridy = 5;
        buyVowelButton = new JButton("BUY A VOWEL");
        add(buyVowelButton, positionConst);
        buyVowelButton.addActionListener(this);

        //"solve" JButton
        positionConst.gridx = 2;
        positionConst.gridy = 6;
        solveButton = new JButton("SOLVE");
        add(solveButton, positionConst);
        solveButton.addActionListener(this);

        //JLabel for player 1's name
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 1;
        positionConst.gridy = 5;
        playerLabel1 = new JLabel(player1.getName() + ":");
        playerLabel1.setFont(new Font("Helvetica", Font.BOLD, 22));     //sets font
        add(playerLabel1, positionConst);

        //JLabel for player 2's name
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 3;
        positionConst.gridy = 5;
        playerLabel2 = new JLabel(player2.getName() + ":");
        playerLabel2.setFont(new Font("Helvetica", Font.BOLD, 22));     //sets font
        add(playerLabel2, positionConst);

        //JLabel for player 1's current game money
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 1;
        positionConst.gridy = 6;
        gameMoney1 = new JLabel("Game money:");
        gameMoney1.setFont(new Font("Helvetica", Font.BOLD, 16));       //sets font
        add(gameMoney1, positionConst);

        //JLabel for player 2's current game money
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 3;
        positionConst.gridy = 6;
        gameMoney2 = new JLabel("Game money:");
        gameMoney2.setFont(new Font("Helvetica", Font.BOLD, 16));       //sets font
        add(gameMoney2, positionConst);

        //JTextField for player 1's current game money
        positionConst.gridx = 1;
        positionConst.gridy = 7;
        gameMoneyText1 = new JTextField("$0", 4);
        gameMoneyText1.setEditable(false);
        add(gameMoneyText1, positionConst);
        gameMoneyText1.addActionListener(this);

        //JTextField for player 2's current game money
        positionConst.gridx = 3;
        positionConst.gridy = 7;
        gameMoneyText2 = new JTextField("$0", 4);
        gameMoneyText2.setEditable(false);
        add(gameMoneyText2, positionConst);
        gameMoneyText2.addActionListener(this);

        //JLabel for player 1's match money
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 1;
        positionConst.gridy = 8;
        matchMoney1 = new JLabel("Match money:");
        matchMoney1.setFont(new Font("Helvetica", Font.BOLD, 16));      //sets font
        add(matchMoney1, positionConst);

        //JLabel for player 2's match money
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 3;
        positionConst.gridy = 8;
        matchMoney2 = new JLabel("Match money:");
        matchMoney2.setFont(new Font("Helvetica", Font.BOLD, 16));      //sets font
        add(matchMoney2, positionConst);

        //JTextField for player 1's match money
        positionConst.gridx = 1;
        positionConst.gridy = 9;
        matchMoneyText1 = new JTextField("$0", 4);
        matchMoneyText1.setEditable(false);
        add(matchMoneyText1, positionConst);
        matchMoneyText1.addActionListener(this);

        //JTextField for player 2's match money
        positionConst.gridx = 3;
        positionConst.gridy = 9;
        matchMoneyText2 = new JTextField("$0", 4);
        matchMoneyText2.setEditable(false);
        add(matchMoneyText2, positionConst);
        matchMoneyText2.addActionListener(this);

        //Jlabel for "game"
        positionConst.insets = new Insets(10, 10, 0, 10);
        positionConst.gridx = 2;
        positionConst.gridy = 7;
        gameNum = new JLabel("Game:");
        gameNum.setFont(new Font("Helvetica", Font.BOLD, 16));      //sets font
        add(gameNum, positionConst);

        //JLabel for which game the user is on
        positionConst.gridx = 2;
        positionConst.gridy = 8;
        gameNumText = new JTextField(String.valueOf(numPuzzle + 1), 1);
        gameNumText.setEditable(false);
        add(gameNumText, positionConst);
        gameNumText.addActionListener(this);

        //JTable that lists abreviated instructions for the game
        positionConst.gridx = 2;
        positionConst.gridy = 10;
        money = new JTable(6, 2);
        money.getColumnModel().getColumn(1).setPreferredWidth(400);
        money.setValueAt("BUTTONS:", 0, 0);
        money.setValueAt("FUNCTION:", 0, 1);
        money.setValueAt("Spin wheel:", 1, 0);
        money.setValueAt("Spins a wheel. Outcomes are \"lose a turn\", \"bankrupt\", or", 1, 1);
        money.setValueAt("$100, $200, $300, $400, $500, $600. For every consonant that", 2, 1);
        money.setValueAt("appears in the word, the amount of money spun is won.", 3, 1);
        money.setValueAt("Buy a vowel:", 4, 0);
        money.setValueAt("Allows player to buy a vowel for $250.", 4, 1);
        money.setValueAt("Solve:", 5, 0);
        money.setValueAt("Lets the user solve the puzzle. If correct, the game is over", 5, 1);
        money.setBorder(BorderFactory.createLineBorder(Color.black));
        money.setGridColor(Color.gray);
        money.setShowGrid(true);
        add(money, positionConst);
    }


    //actionPerformed method that uses actionListeners to call different methods
    public void actionPerformed(ActionEvent e) {

        //if the user clicks the spin button
        if (e.getSource() == spinButton) {
            spinResultInt = doSpin();       //calling doSpin method
            if (spinResultInt == 0) {
                JOptionPane.showMessageDialog(newWindow, "Oh no! You spun \"bankrupt\".");      //JOptionPane for bankrupt
                currentPlayer = bankrupt();         //calling bankrupt method
            } else if (spinResultInt == 1) {
                JOptionPane.showMessageDialog(newWindow, "Oh no! You spun \"lost a turn\".");   //JOptionPane for lose a turn
                currentPlayer = endOfTurn();        //calling endOfTurn method
            } else {
                String consonantAttempt = JOptionPane.showInputDialog(newWindow, "Congratulations! You spun $" + spinResultInt + ". Guess a consonant here:");
                consonantGuess(consonantAttempt);
            }
        }
        //if the user clicks the buy a vowel button
        else if (e.getSource() == buyVowelButton) {
            //takes the input from a JOptionPane and turns it into a string
            String vowelAttempt = JOptionPane.showInputDialog(newWindow, "Guess a vowel here");
            vowelGuess(vowelAttempt);       //calling vowelGuess method
        }
        //if the user clicks the solve button
        else if (e.getSource() == solveButton) {
            //takes the input from a JOptionPane and turns it into a string
            String userAttempt = JOptionPane.showInputDialog("Solve the puzzle here:");
            solveGuess(userAttempt);        //calling solveGuess method
        }
    }


    //method that checks to see if the user's guess of the name of the puzzle is correct or incorrect
    public void solveGuess (String userGuess2) {
        if (userGuess2.equals(hiddenPhrase)) {      //if the guess is correct
            numPuzzle++;
            if (currentPlayer == player1) {
                player1.setWinningsTemp(currentPlayer.getWinningsTemp());
                player1.setWinningsTotal(player1.getWinningsTemp() + player1.getWinningsTotal());
                JOptionPane.showMessageDialog(newWindow, "Correct!\n" +
                        player1.getName() + " won the game and won $" + player1.getWinningsTemp() + "!\n" +
                        "The correct answer was: " + hiddenPhrase + "\nStarting new game.");
            }
            else {
                player2.setWinningsTemp(currentPlayer.getWinningsTemp());
                player2.setWinningsTotal(player2.getWinningsTemp() + player2.getWinningsTotal());
                JOptionPane.showMessageDialog(newWindow, "Correct!\n" +
                        player2.getName() + " won the game and won $" + player2.getWinningsTemp() + "!\n" +
                        "The correct answer was: " + hiddenPhrase + "\nStarting new game.");
            }
            if (numPuzzle < 4) {        //if there are more puzzles to be used
                //resets winnings for the current game
                player1.setWinningsTemp(0);
                player2.setWinningsTemp(0);
                currentPlayer.setWinningsTemp(0);
                //resets the texts on the JFrame
                gameMoneyText1.setText("$" + player1.getWinningsTemp());
                gameMoneyText2.setText("$" + player2.getWinningsTemp());
                matchMoneyText1.setText("$" + player1.getWinningsTotal());
                matchMoneyText2.setText("$" + player2.getWinningsTotal());

                hiddenPhrase = hiddenPhrases[numPuzzle];    //advances to next puzzle
                gameNumText.setText(String.valueOf(numPuzzle + 1));     //states on the JFrame which puzzle the players are on
                shownPhrase.delete(0, shownPhrase.length());        //resets the shownPhrase
                //updates shownPhrase
                for (int i = 0; i < hiddenPhrase.length(); i++) {
                    shownPhrase.append('—');
                    if (hiddenPhrase.charAt(i) == ' ') {
                        shownPhrase.setCharAt(i, ' ');
                    }
                }
                puzzleText.setText(shownPhrase.toString());
            }
            else {      //if all the puzzles have been used
                JOptionPane.showMessageDialog(newWindow, "Those are all the puzzles. Thanks for playing!\n"
                        + "Total winnings for " + player1.getName() + ": $" + player1.getWinningsTotal() + "\n"
                        + "Total winnings for " + player2.getName() + ": $" + player2.getWinningsTotal());
                System.exit(0);
            }
        }
        else {      //if the guess is incorrect
            JOptionPane.showMessageDialog(newWindow, "Not correct. Your turn is over.");
            currentPlayer = endOfTurn();
        }
    }


    //spin method which used a random object to spin a wheel and return the outcome
    public int doSpin () {
        Random randGen = new Random();
        int wheelSpin = randGen.nextInt(8);
        return spin[wheelSpin];
    }


    //method that checks whether a char is a consonant or not
    private boolean isConsonant(char c) {
        return (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u');
    }


    //method that checks if the input is a consonant, if it has already been guessed, and if it is not in the word
    public void consonantGuess (String userGuess3) {
        char consonant = userGuess3.charAt(0);
        boolean alreadyGuessed = false;
        boolean notInWord = false;
        int counter = 0;
        int moneyMade = 0;
        int numConsonants = 0;
        //checks to see if the consonant has already been guessed
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (shownPhrase.charAt(i) == consonant) {
                alreadyGuessed = true;
                break;
            }
        }
        //this for loop, along with the following if statement, checks to see if the consonant guessed is not in the word
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) != consonant) {
                counter++;
            }
        }
        if (counter == hiddenPhrase.length()) {
            notInWord = true;
        }

        //Sometimes, many of the following if-statements can be true. So, the for loop is implemented so that the loop
        //will break as long as one of them is true. This way there won't be multiple restrictions printed.
        for (int i = 0; i < 1; i++) {
            //calls the isConsonant method to check and see if the input is a consonant
            if (!isConsonant(consonant)) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that is not a consonant. Your turn is over.");
                currentPlayer = endOfTurn();
                break;
            }
            //this if statement returns true if the consonant has already been guessed, which was checked in an above loop
            else if (alreadyGuessed) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that consonant has already been guessed. Your turn is over.");
                currentPlayer = endOfTurn();
                break;
            }
            //this if statement returns true if the consonant is not in the word, which was checked in an above loop and if statement
            else if (notInWord) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that consonant does not appear in the phrase. Your turn is over.");
                currentPlayer = endOfTurn();
                break;
            }
            //if the guess is a consonant in the word and has not been guessed yet, this loop appends shownPhrase to include the consonant
            else {
                for (int j = 0; j < hiddenPhrase.length(); j++) {
                    if (hiddenPhrase.charAt(j) == consonant) {
                        shownPhrase.setCharAt(j, consonant);
                        numConsonants++;
                    }
                }
                moneyMade = numConsonants * spinResultInt;   //updates moneyMade
                currentPlayer.setWinningsTemp(currentPlayer.getWinningsTemp() + moneyMade);   //updates the tempMoney to include the money made in that turn FIXME
                JOptionPane.showMessageDialog(newWindow, "That consonant is correct! You made $" + moneyMade);
                if (currentPlayer == player1) {
                    gameMoneyText1.setText("$" + (currentPlayer.getWinningsTemp()));        //updates the money shown on the JFrame
                }
                else {
                    gameMoneyText2.setText("$" + (currentPlayer.getWinningsTemp()));        //updates the money shown on the JFrame
                }
                puzzleText.setText(shownPhrase.toString());     //updates the shown phrase on the JFrame
                break;
            }
        }
    }


    //method that checks if the input is a vowel, if it has already been guessed, and if it is not in the word
    public void vowelGuess(String userGuess4) {
        char vowel = userGuess4.charAt(0);
        boolean alreadyGuessed = false;
        boolean notInWord = false;
        int counter = 0;

        //checks to see if the vowel has already been guessed
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (shownPhrase.charAt(i) == vowel) {
                alreadyGuessed = true;
                break;
            }
        }
        //this for loop, along with the following if statement, checks to see if the vowel guessed is not in the word
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) != vowel) {
                counter++;
            }
        }
        if (counter == hiddenPhrase.length()) {
            notInWord = true;
        }

        //Sometimes, many of the following if-statements can be true. So, the for loop is implemented so that the loop
        //will break as long as one of them is true. This way there won't be multiple restrictions printed.
        for (int i = 0; i < 1; i++) {
            //checks to see if the player has enough money to buy a vowel
            if (currentPlayer.getWinningsTemp() < 250) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, you do not have enough money to buy a vowel. Your turn is over.\n");
                currentPlayer = endOfTurn();
                break;
            }

            //calls the isConsonant method to check and see if the input is a vowel
            if (isConsonant(vowel)) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that is not a vowel. Your turn is over.\n");
                currentPlayer = endOfTurn();
                break;
            }
            //this if statement returns true if the vowel has already been guessed, which was checked in an above loop
            else if (alreadyGuessed) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that vowel has already been guessed. Your turn is over.\n");
                currentPlayer = endOfTurn();
                break;
            }
            //this if statement returns true if the vowel is not in the word, which was checked in an above loop and if statement
            else if (notInWord) {
                JOptionPane.showMessageDialog(newWindow, "Sorry, that vowel does not appear in the phrase. Your turn is over.\n");
                currentPlayer = endOfTurn();
                break;
            }
            //if the guess is a vowel and has not been guessed yet, this loop appends shownPhrase to include the vowel
            else {
                for (int j = 0; j < hiddenPhrase.length(); j++) {
                    if (hiddenPhrase.charAt(j) == vowel) {
                        shownPhrase.setCharAt(j, vowel);
                    }
                }
                currentPlayer.payForVowel();   //calls the payForVowel method which decreases the current players temporary money by 250
                JOptionPane.showMessageDialog(newWindow, "That vowel is correct!");
                if (currentPlayer == player1) {
                    gameMoneyText1.setText("$" + currentPlayer.getWinningsTemp());      //updates the money shown on the JFrame
                }
                else {
                    gameMoneyText2.setText(("$" + currentPlayer.getWinningsTemp()));    //updates the money shown on the JFrame
                }
                puzzleText.setText(shownPhrase.toString());     //updates the shown phrase on the JFrame
                break;
            }
        }
    }


    //method that is called at the end of a player's turn. It swaps which player's turn it is and updates the player's winnings on the JFrame
    public Player endOfTurn() {
        if (currentPlayer == player1) {
            gameMoneyText1.setText("$" + player1.getWinningsTemp());
            whoseTurn.setText("It is " + player2.getName() + "'s turn.");
            return player2;
        }
        else {
            gameMoneyText2.setText("$" + player2.getWinningsTemp());
            whoseTurn.setText("It is " + player1.getName() + "'s turn.");
            return player1;
        }
    }


    //method that is called if a player goes bankrupt. It swaps which player's turn it is and returns the current player's game winnings to 0
    public Player bankrupt() {
        if (currentPlayer == player1) {
            player1.setWinningsTemp(0);
            gameMoneyText1.setText("$0");
            whoseTurn.setText("It is " + player2.getName() + "'s turn.");
            return player2;
        }
        else {
            player2.setWinningsTemp(0);
            gameMoneyText2.setText("$0");
            whoseTurn.setText("It is " + player1.getName() + "'s turn.");
            return player1;
        }
    }
}