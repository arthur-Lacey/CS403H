Milestone 8 Idea:

The addition to our game would have been to add code to allow players to trade cards at the beginning of the round.

We would have accomplished this by altering the Hand class to include a trade method, and introducing a new global
variable to keep track of which round it was.

On the first round, the players would give cards to the player on the left,
ie) 1 would give cards to 2, 2 to 3, 3 to 0, 0 to 1.

On the second round, the players would give cards to the player on the right
ie) 1 would give cards to 0, 0 to 3, 3 to 2, 2 to 1.

On the third round, the players would give cards to the player across from them.
ie) 1 and 3 would trade, 0 and 4 would trade.

The 4th round, every person would keep their hand intact, no passing would occur.\

The cards would be related to strategy, so we would have to associate trade strategies to 
integers as part of case statements.

We would accomplish it by instantiating decks for each player, removing items from their hand using the play method,
and pushing them to the deck.

We would then use the draw method for the hand to ensure each hand took the appropriate sub-deck.  

We would ensure control of the process through case statements, and there would be almost infinate modability because
the strategy implementation is very easily added on to.