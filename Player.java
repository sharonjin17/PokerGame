import java.util.ArrayList;

public class Player {
    
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){	
	    bankroll = 5;
        bet = 1;
	}

	public void addCard(Card c){
	    hand.add(c);
	}

	public void removeCard(Card c){
	    hand.remove(c);
    }
		
    public void bets(double amt){
        bet = amt;
    }

    public void winnings(double odds){
        bankroll += bet*odds;
    }

    public double getBankroll(){
        return bankroll;
    }

    public void payToken(double tokens){
        bankroll-=tokens;
    }
}


