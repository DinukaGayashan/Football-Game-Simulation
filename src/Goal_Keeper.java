/**
 * defines a goalkeeper which is a player
 */
public class Goal_Keeper extends Player {
    private int goals_saved;
    private int goals_missed;

    /**
     * @param name name of the player
     * @param jersey_number jersey number of the player
     * @param team team of the player
     */
    Goal_Keeper(String name, int jersey_number, String team){
        super(name,team,jersey_number);
        this.goals_missed=0;
        this.goals_saved=0;
    }
}
