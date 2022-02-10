import java.util.ArrayList;

/**
 * defines a football team
 */
public class Team {
    public String team_name;
    public Coach coach;
    public Doctor doctor;
    public ArrayList <Field_Player> field_players=new ArrayList<>();
    public ArrayList <Field_Player> bench_players=new ArrayList<>();
    public Goal_Keeper goal_keeper;

    /**
     * sets team attribures
     * @param name name of the team
     * @param coach coach of the team
     * @param doctor doctor of the team
     * @param field_players array of field players
     * @param goal_keeper goal keeper
     */
    Team(String name, Coach coach, Doctor doctor, Field_Player[] field_players, Goal_Keeper goal_keeper){
        this.team_name=name;
        this.coach=coach;
        this.doctor=doctor;
        for(int i=0;i<17;i++){
            if(i<10){
                this.field_players.add(field_players[i]);
            }
            else{
                this.bench_players.add(field_players[i]);
            }
        }
        this.goal_keeper=goal_keeper;
    }
}