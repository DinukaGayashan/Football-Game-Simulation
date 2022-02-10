/**
 * defines a field player which is a player
 */
public class Field_Player extends Player {
    public int goals;

    /**
     * sets field player attributes
     * @param name name of the player
     * @param jersey_number jersey number of the player
     * @param team team of the player
     */
    Field_Player(String name, int jersey_number, String team){
        super(name,team,jersey_number);
        this.goals=0;
    }
}
