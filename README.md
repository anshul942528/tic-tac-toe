# tic-tac-toe
tic tac toe game in object oriented way


# My approach to design this game

First of all when I thought of this game, the picture on mind was 
1. A board
2. 2 Players
3. 2 Symbols (1 for each player)
4. Square
5. UserInteraction
4. Move
5. Game State 
(example : If I freeze the gme play, what all things will be there in front of me related to game)
{
  player1,
  player2,
  currentPlayer,
  turn,
  board,
  symbol1,
  symbol2,
  gamestatus(xwin,owin,tie,playing),
  move
}


# From the above discussion we found out all the information these will be converted to entity and actions
* Entity
  1. Game
  2. Player
  3. Board
  4. Square
  5. Move
  6. UserInteraction(Singleton class, we can not have multiple service taking user inputs)
* Action
  1. Load game(also print board)
  2. Start game
  1. Take input
  2. Place in the cell (print board)
  3. Check the game status
  4. Switch the currecnt player
  5. Continue until game tie/one of the player wins
  
After that implementation became very simple please have a look at the code.
