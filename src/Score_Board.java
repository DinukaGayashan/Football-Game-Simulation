/**
 * defines a score board
 */
public class Score_Board {
    private Field_Player last_goal_player;

    /**
     * sets last goal taken player
     * @param player player who takes the last goal
     */
    public void set_last_goal_player(Field_Player player){
        this.last_goal_player=player;
    }

    /**
     * displays score board according to the time
     * @param timer timer value of the game
     * @param team1 team1 object
     * @param team2 team2 object
     * @param team1_score score of the team1
     * @param team2_score score of the team2
     * @param team1_shoots number of shoots by team1
     * @param team2_shoots number of shoots by team2
     */
    public void display_score_board(int timer,Team team1,Team team2,int team1_score,int team2_score,int team1_shoots,int team2_shoots){
        System.out.println("______________________________________________________");
        if(timer<=2700){
            System.out.print("Half Time");
        }
        else if(timer<=6300){
            System.out.print("Full Time");
        }
        else if(timer<=8100){
            System.out.print("First Extra Time");
        }
        else if(timer<=9900){
            System.out.print("Second Extra Time");
        }
        else{
            System.out.print("Penalty Shoot Time");
        }
        System.out.println(" ("+timer /60+" minutes)");
        System.out.format("%30s\t%-30s\n", team1.team_name, team2.team_name);
        System.out.print("Goals:");
        System.out.format("%24d\t%-30d\n", team1_score,team2_score);
        System.out.print("Last Goal:");
        if(last_goal_player==null){
            System.out.format("%20s\t%-30s\n", "", "");
        }
        else if(last_goal_player.team.equals(team1.team_name)){
            System.out.format("%20s\t%-30s\n", last_goal_player.name, "");
        }
        else if(last_goal_player.team.equals(team2.team_name)){
            System.out.format("%20s\t%-30s\n", "", last_goal_player.name);
        }
        if(timer>9900){
            System.out.print("Penalty Shoots:");
            System.out.format("%15d\t%-30d\n", team1_shoots,team2_shoots);
        }
        System.out.println("______________________________________________________\n");
    }
}
