public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
    private String[] suits;
    private String[] ranks;
	
	public Card(int s, int r){
		suit = s;
        rank = r;
        suits = new String[4];
        createSuits();
        ranks = new String[13];
        createRanks();
	}
	
    // compares ranks of different cards
	public int compareTo(Card other){
        if (this.rank < other.rank){
            return -1;
        }
        else if (this.rank > other.rank){
            return 1;
        }
        else{
            return 0;
        }
	}
    
    // returns a Card in string form
	public String toString(){
		return ranks[getRank()-1] + suits[getSuit()-1] + " ";
	}

    public int getSuit(){
        return suit;
    }
	
    public int getRank(){
        return rank;
    }
    
    // instantiates an array of suits
    private void createSuits(){
        suits[0] = "spades";
        suits[1] = "hearts";
        suits[2] = "diamonds";
        suits[3] = "clubs";
    }
    
    // instantiates an array of ranks
    private void createRanks(){
        ranks[0] = "ace of ";
        ranks[1] = "2 of ";
        ranks[2] = "3 of ";
        ranks[3] = "4 of ";
        ranks[4] = "5 of ";
        ranks[5] = "6 of ";
        ranks[6] = "7 of ";
        ranks[7] = "8 of ";
        ranks[8] = "9 of ";
        ranks[9] = "10 of ";
        ranks[10] = "jack of ";
        ranks[11] = "queen of ";
        ranks[12] = "king of ";
    }
}
