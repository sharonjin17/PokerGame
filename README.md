# PokerGame
Allows user to play a game of machine poker, written in java

===========================================================================
Poker Programming Project
===========================================================================

For my Card class, I decided to add a createSuits() method and a createRanks()
method, both of which make String representations of the suits and ranks. Then,
in my toString() method, I put these two arrays together in a way that the card
would be printed as "rank of suit" when the game is being played. I thought 
that this was more convenient for the player and more clear than "s13" or "d1".
I also decided that in my compareTo() method, it was more convenient to have
the cards only compare the ranks and not the suits. So, if the ranks are equal,
compareTo() would return 0, which would be helpful for determining three of a
kind, four of a kind, and similar arrangements. For flush, I just called the 
getSuit() method.

For my Deck class, I created a createCards() method that would create an
unshuffled deck of cards. I called this in the constructor. Also, in my shuffle()
method, I decided to iterate through the cards array and swap each card with
another random card, 10 times. This way, each card should be thoroughly swapped.
Finally, in the deal() method, I decided to add the topCard int by one, rather
than removing each card. Thus, it would "deal" out a different card every time
deal() is called, even though the cards array itself stays the same. I thought
this was much more convenient than having to remove the physical card itself.

For my Player class, the methods were relatively straightforward. I decided to
set the bankroll to 5, thus starting the player with 5 tokens, and the bet to 1.
But, if the player chose to bet more than 1 token, he could change his bet. The 
getter/setter methods weren't too complicated and could be implemented with one
line each. 

For my Game class, I decided to implement nine additional methods: onePair(), 
twoPairs(), threeOfAKind(), straight(), flush(), fullHouse(), fourOfAKind(), royal(), 
and sort(). My sort() method was essential, because it sorted all of the cards in 
hand by their ranks. So, if I had a ten of spades, an ace of hearts, and a king of 
clubs, it would sort to an ace of hearts, a ten of spades, and a king of clubs. I 
called this in the play() method before I called the checkHand() method in play(). 
For onePair(), I checked to see if two consecutive cards had the same rank. Since I
called sort() before checkHand(), if two cards were equal, they would be next to 
each other. I used the same logic for twoPairs(), threeOfAKind(), and fourOfAKind().
For straight(), I used getRank() (and sort()), to see if the cards could be sorted
in ascending order with each one a rank above the previous. I didn't include the 
possibility of a royal straight here; I implemented that in royal(). For flush(),
I used getSuit(), and in fullHouse, I checked to see if the first two and last three
cards were the same or if the first three or last two cards were the same. If so, it
returned true. In royal(), I merely checked for the one possibility of a royal
straight, which wasn't difficult after my sort() method. I just checked to see if
there were any duplicated cards (with my onePair() method), if the first card was an
ace, and the second card was a 10. For card arrangements such as straight flush, I
just checked to see if the hand returned true for straight() and flush().

In checkHand(), I ordered the boolean methods in terms of lowest payout to highest
payout, and wrote them all as if methods. Thus, for example, if I had a 2 of clubs,
2 of spades, 2 of hearts, 3 of diamonds, and 3 of clubs, it would first evaluate as
one pair, then two pairs, then three of a kind, and finally full house. It would 
return the best possible configuration of my hand, and that's what it should do.

For the constructors, the first constructor that took an explicit parameter was more
difficult to program. I decided to do parseInt, in order to translate the input
array to my chosen String form of a card (ex. from "s12" to "queen of spades"). The
second constructor was simpler in that I only had to instantiate the values.

The play() method was the most complicated to implement. I had to make provisions to
ensure that the player had enough tokens to play (which I programmed at the start), 
that the player could only bet a certain amount of tokens, that the player could
only remove a certain number of cards, and that the player could remove valid cards.
I mostly did these with while loops, in which the condition must be fulfilled in
order for the player to continue. I also decided that if the player wanted to remove
all five cards, there should be a condition in which the player shouldn't have to be
asked which cards he would like to remove, because it got annoying as I was retesting
it.
