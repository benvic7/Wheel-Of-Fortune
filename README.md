# Wheel of Fortune

## Description
The "Wheel of Fortune Game" is a Java application that simulates the popular TV game show "Wheel of Fortune." Players can take turns spinning a wheel, guessing consonants, or buying vowels to reveal letters in a hidden phrase. The game continues until a player correctly guesses the entire phrase. I created this game for my CSC 112: Fundamentals of Computer Science class

Author: **Ben Vicinelli**

Date: **November 2021**

Language: **Java**

Dependencies: **Java Spring**

## Game Components
- **Graphical User Interface (GUI)**: The game features a user-friendly GUI built using Java's Swing library. It includes labels, text fields, buttons, and a JTable to display game information and instructions.
- **Data Structures and Algorithms**: the game utilizes arrays, randomization, string manipulation, conditional statements, and loops to manage game state, logic, and random outcomes.
- **Object-Oriented Programming (OOP)**: the game follows object-oriented principles, with a Player class representing players and a WOFFrame class managing the main game frame, user input, and game flow.

## How to Use/Play
1. Clone this repository: `git clone https://github.com/benvic7/Wheel-of-Fortune.git`.
2. Navigate to the project directory and run the `Main` class.
3. Enter the names of both players at the beginning of the game.
4. The game will randomly choose a player to go first.
5. The hidden phrase will be displayed with underscores representing unrevealed letters.
6. Players take turns to spin the wheel by clicking the "SPIN WHEEL" button. The outcome will be displayed, indicating either winning money, losing a turn, or going bankrupt.
7. If the spin result is money, players can guess consonants by entering a letter in the "Guess a consonant here" field and clicking the "BUY A VOWEL" button to buy vowels.
8. If the guess is correct, the player earns money for each occurrence of the letter in the hidden phrase.
9. Players can also use the "SOLVE" button to guess the entire hidden phrase. If the guess is correct, the game ends, and the player wins the game money.
10. The game continues with the next player's turn until all hidden phrases are solved.
11. The match ends when all puzzles have been solved. The player with the highest total winnings wins the match.


Enjoy the game and good luck!
