import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private Random rand;
	private Card[] cards;
	private int top;

	// add more instance variables if needed
	
	public Deck(){
		cards = new Card[52];
        createCards();
        top = 0;
        rand = new Random();
	}
	
    // shuffles the deck
	public void shuffle(){
        int num;
        int j = 10;
        Card temp;
        while (j > 0){
            for (int i = 0; i < 52; i++){
                num = rand.nextInt(52);
                temp = cards[num];
                cards[num] = cards[i];
                cards[i] = temp;
            }
            j--;
        }
	}
	
    // deals the top card in the deck
	public Card deal(){
		Card topCard = cards[top];
        top++;
        return topCard;
	}
	
    // instantiates the deck, unshuffled
	private void createCards(){
        int temp = 0;
        
        for (int i = 1; i < 5; i++){
            for (int j = 1; j < 14; j++){
                cards[temp] = new Card(i,j);
                temp++;
            }        
        }
    } 
}
