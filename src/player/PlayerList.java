package player;

public class PlayerList {
	private int score;
	private int chance;
	private int choice;
    // Constructor
    public void Player() {
        this.score = 0; // Initialize score to 0
        this.chance = 1;
    }

    // Getters and setters


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }
    
    public int getChance() {
        return chance;
    }
    
    public void setChance(int a) {
        this.chance = a;
    }
    
    public int getChoice() {
        return choice;
    }
    public void setChoice(int choice) {
    	this.choice = choice;
    }
}
