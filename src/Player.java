/**
 * defines a player which is a person
 */
public class Player extends Person {
    public String team;
    public int jersey_number;

    /**
     * sets player attributes
     * @param name name of the person
     * @param team team of the player
     * @param jersey_number jersey number of the player
     */
    Player(String name, String team, int jersey_number){
        super(name);
        this.team=team;
        this.jersey_number=jersey_number;
    }
}
