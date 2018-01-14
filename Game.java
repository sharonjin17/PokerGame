import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private Player p;
	private Deck cards;
	private ArrayList<Card> hand;
    private Scanner scan;
	
	public Game(String[] testHand){
        p = new Player();
        cards = new Deck();
        scan = new Scanner(System.in);
        hand = new ArrayList<Card>();
        
        for (String card : testHand){
            int rank = Integer.parseInt(card.substring(1));
            if (card.substring(0,1).equals("s")){
                hand.add(new Card(1, rank));
            }
            else if (card.substring(0,1).equals("h")){
                hand.add(new Card(2, rank));
            }
            else if (card.substring(0,1).equals("d")){
                hand.add(new Card(3, rank));
            }
            else{
                hand.add(new Card(4, rank));
            }
        }
	}
	
	public Game(){
		p = new Player();
        cards = new Deck();
		hand = new ArrayList<Card>();
        scan = new Scanner(System.in);
	}
	
    // plays game
	public void play(){
        boolean hasTokens = true;
        cards = new Deck();
        cards.shuffle();
        
        // makes sure that the player has enough tokens to play
        if (p.getBankroll() <= 0){
            System.out.println("Sorry, you're out of tokens!");
            hasTokens = false;
        }
        
        if (hasTokens){
            // creates the hand if there is no input hand
            if (hand.size()==0){
                for (int i = 0; i < 5; i++){
                    hand.add(cards.deal());
                }
            }
        
            // displays the hand to the player
            for (Card card : hand){
                System.out.println(card);
            }

            // asks how many tokens the player would like to bet
            System.out.println("You have " + (int)p.getBankroll() + " tokens."
                    + " How many tokens would you like to bet? Enter 1-5.");
            int tokens = scan.nextInt();
            
            // provision if the player doesn't enter a valid number of tokens
            while (tokens > 5 || tokens < 1 || tokens > p.getBankroll()){
                System.out.println("Please enter a valid number of tokens.");
                tokens = scan.nextInt();
            }

            p.payToken(tokens);
            p.bets(tokens);

            // asks how many cards the player would like to remove
            System.out.println("How many cards do you want to remove?");
            int numberRemoved = scan.nextInt();
                
            while (numberRemoved > 5 || numberRemoved < 0){
                System.out.println("You must remove 0-5 cards." + 
                              " How many cards do you want to remove?");
                numberRemoved = scan.nextInt();
            }
            
            // special case if player wants to remove all the cards
            if (numberRemoved == 5){
                for (int k = 0; k < 5; k++){
                    hand.set(k, cards.deal());
                }
                numberRemoved = 0;
            }
                
            // asks for the places of the player's cards and removes them
            for (int j = 0; j < numberRemoved; j++){
                System.out.println("Type the place of the card you want"
                        + " removed (1-5). Please type only one number:");
                int index = scan.nextInt();
                
                // provision if the player doesn't type a valid place
                while (index < 1 || index > 5){
                    System.out.println("Please type a valid place.");
                    index = scan.nextInt();
                }
                
                hand.set(index-1,cards.deal());
            }

            // returns new hand of cards
            System.out.println("Your new hand is: ");
            for (Card card : hand){
                System.out.println(card);
            }
            
            // checks the hand, returns the token total and hand configuration
            sort(hand);
            System.out.println("You have " + checkHand(hand) + 
               " and you have " + (int)p.getBankroll() + " token(s).");

            System.out.println("Thanks for playing!");

            // asks if player would like to play again
            System.out.println("Would you like to play again? yes/no");
            String playAgain = scan.next();
            
            if (playAgain.equals("yes")){
                hand.clear();
                play();
            }
        }
	}
	
    // checks hand and returns its configuration
    // adds total winnings to player's tokens
	public String checkHand(ArrayList<Card> hand){
        String result = "no pairs";
        double payout = 0;
        
        if (onePair(hand)){
            result = "one pair";
            payout = 1;
        }
        if (twoPairs(hand)){
            result = "two pairs";
            payout = 2;
        }
        if (threeOfAKind(hand)){
            result = "a three of a kind";
            payout = 3;
        }
        if (straight(hand)){
            result = "a straight";
            payout = 4;
        }
        if (flush(hand)){
            result = "a flush";
            payout = 5;
        }
        if (fullHouse(hand)){
            result = "a full house";
            payout = 6;
        }
		if (fourOfAKind(hand)){
            result = "a four of a kind";
            payout = 25;
        }
        if (straight(hand) && flush(hand)){
            result = "a straight flush";
            payout = 50;
        }
        if (royal(hand) && flush(hand)){
            result = "a royal flush";
            payout = 250;
        }
        
        p.winnings(payout);
        return result;
	}
	
    // returns true if hand has one pair
    public boolean onePair(ArrayList<Card> inputHand){
        sort(inputHand);
        boolean result = false;
        for (int i = 0; i < inputHand.size()-1; i++){
            if (inputHand.get(i).compareTo(inputHand.get(i+1)) == 0){
                result = true;
            }
        }
        return result;
    }
    
    // returns true if hand has two pairs
    public boolean twoPairs(ArrayList<Card> inputHand){
        int total = 0;
        
        for (int i = 0; i < inputHand.size()-1; i++){
            if (inputHand.get(i).compareTo(inputHand.get(i+1))==0){
                total++;
            }
        }
        if (total == 2){
            return true;
        }
        return false;
    }
    
    // returns true if hand has three of a kind
    public boolean threeOfAKind(ArrayList<Card> inputHand){
        for (int i = 0; i < inputHand.size()-2; i++){
            if (inputHand.get(i).compareTo(inputHand.get(i+1)) == 0
               && inputHand.get(i+1).compareTo(inputHand.get(i+2))==0){
                return true;
            }
        }
        return false;
    }
    
    // returns true if hand is a straight
    public boolean straight(ArrayList<Card> inputHand){
        boolean result = true;
        
        for (int j = 1; j < inputHand.size(); j++){
            if (inputHand.get(j).getRank() - 
                inputHand.get(j-1).getRank() != 1){
                result = false;
            }
        }
        
        return result;
    }
    
    
    // returns true if hand is a flush
    public boolean flush(ArrayList<Card> inputHand){
        boolean result = true;
        for (int i = 0; i < inputHand.size()-1; i++){
            if (inputHand.get(i).getSuit() != inputHand.get(i+1).getSuit()){
                result = false;
            }
        }
        return result;
    }
    
    // returns true if hand is a full house
    public boolean fullHouse(ArrayList<Card> inputHand){
        int i = 1;
        boolean same = true;
        boolean result = true;
        
        while (i < inputHand.size() && same){
            if (inputHand.get(i).compareTo(inputHand.get(i-1))!=0){
                same = false;
            }
            i++;
        }
        
        if (i == 3 || i == 4){
            for (int j = inputHand.size()-1; j >= i; j--){
                if (inputHand.get(j).compareTo(inputHand.get(j-1))!=0){
                    result = false;
                }
            }
            return result;
        }
        return false;
    }
    
    // returns true if hand is a four of a kind
    public boolean fourOfAKind(ArrayList<Card> inputHand){
        for (int i = 0; i < inputHand.size()-3; i++){
            if (inputHand.get(i).compareTo(inputHand.get(i+1))==0
               && inputHand.get(i+1).compareTo(inputHand.get(i+2))==0
               && inputHand.get(i+2).compareTo(inputHand.get(i+3))==0){
                return true;
            }
        }
        return false;
    }
    
    // returns true if hand has all "royal" cards (ace, 10, jack,
    // queen, king), not necessarily a flush
    public boolean royal(ArrayList<Card> inputHand){
        boolean duplicates = onePair(inputHand);
        
        if (!duplicates && inputHand.get(0).getRank() == 1
           && inputHand.get(1).getRank() == 10){
            return true;
        }
        return false;
    }
    
    // sorts cards in hand by their rank in ascending order
    public void sort(ArrayList<Card> inputHand){
        ArrayList<Card> result = new ArrayList<Card>();
        for (int i = 1; i <= 13; i++){
            for (int j = 0; j < inputHand.size(); j++){
                if (inputHand.get(j).getRank() == i){
                    result.add(inputHand.get(j));
                }
            }
        }
        hand = result;
    }
}
