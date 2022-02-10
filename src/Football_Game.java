import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * defines a football game
 */
public class Football_Game {
    public Team team1,team2;
    private Referee main_referee, sub_referee;
    private Ball ball;
    private int team1_score,team2_score;
    private int team1_shoots,team2_shoots;
    private int timer;
    private int passes,possessions,interceptions,outs, attempts,successful,unsuccessful,injuries;
    private Score_Board score_board=new Score_Board();

    /**
     * sets football game attributes
     * @param team1 team1 object
     * @param team2 team2 object
     * @param main_referee main referee object
     * @param sub_referee sub referee object
     * @param ball ball object
     */
    Football_Game(Team team1, Team team2, Referee main_referee, Referee sub_referee, Ball ball){
        this.team1=team1;
        this.team2=team2;
        this.main_referee=main_referee;
        this.sub_referee=sub_referee;
        this.ball=ball;
        this.team1_score=0;
        this.team2_score=0;
        this.team1_shoots=0;
        this.team2_shoots=0;
        this.timer =0;
        this.passes=0;
        this.possessions=0;
        this.interceptions =0;
        this.outs=0;
        this.attempts =0;
        this.successful=0;
        this.unsuccessful=0;
        this.injuries=0;
    }

    /**
     * to check whether scores are equal
     * @return true if scores are equal
     */
    public boolean equal_score(){
        return team1_score==team2_score;
    }

    /**
     * to check toss status
     * @return true or false randomly
     */
    public boolean toss(){
        double toss=Math.random();
        if(toss<0.5)
            return true;
        return false;
    }

    /**
     * to display team details
     */
    public void display_teams(){
        System.out.format("\n%30s\t%-30s\n", team1.team_name, team2.team_name);
        System.out.println();
        System.out.format("%30s\t%-30s\n", "(gk) "+team1.goal_keeper.name, team2.goal_keeper.name+" (gk)");
        for(int i=0;i<10;i++){
            System.out.format("%30s\t%-30s\n", team1.field_players.get(i).name, team2.field_players.get(i).name);
        }
        for(int i=0;i<7;i++){
            System.out.format("%30s\t%-30s\n", team1.bench_players.get(i).name, team2.bench_players.get(i).name);
        }
    }

    /**
     * print log data with time
     * @param s string to print with time
     */
    public void log(String s){
        System.out.println("["+this.timer /60+":"+this.timer %60+"] "+s);
    }

    /**
     * randomly processing game functions
     * @param player current player who has the ball
     * @return a new player that has the ball
     */
    private Field_Player play(Field_Player player){
        double prob=Math.random();
        if(prob>0.5){
            Team new_team=(player.team.equals(team1.team_name)?team1:team2);
            Field_Player new_player;
            do{
                new_player = new_team.field_players.get((int) Math.floor(Math.random()*(10)));
            }while(new_player.equals(player));
            ball.current_player= new_player;
            log(player.name+"["+player.jersey_number+"] of "+player.team+" passes the ball to "+new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team);
            this.passes++;
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            return new_player;
        }
        else if(prob>0.4){
            ball.current_player= player;
            log(player.name+"["+player.jersey_number+"]"+" of "+player.team+" possesses the ball");
            this.possessions++;
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            return player;
        }
        else if(prob>0.1){
            Team new_team=(player.team.equals(team1.team_name)?team2:team1);
            Field_Player new_player = new_team.field_players.get((int) Math.floor(Math.random()*(10)));
            ball.current_player= new_player;
            log(new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" intercepts the ball from "+player.name+"["+player.jersey_number+"] of "+player.team);
            this.interceptions++;
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            return new_player;
        }
        else if(prob>0.025){
            Team new_team=(player.team.equals(team1.team_name)?team2:team1);
            Field_Player new_player = new_team.field_players.get((int) Math.floor(Math.random()*(10)));
            ball.current_player= new_player;
            log("Sub referee "+sub_referee.name+" signals "+player.name+"["+player.jersey_number+"]"+" of "+player.team+" passed the ball outside the play and "+new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" takes the throw in");
            this.outs++;
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            return new_player;
        }
        else if(prob>0.001){
            Team new_team=(player.team.equals(team1.team_name)?team2:team1);
            Field_Player new_player = new_team.field_players.get((int) Math.floor(Math.random()*(10)));
            this.attempts++;
            double prob2=Math.random();
            if(prob2>0.7){
                log(player.name+"["+player.jersey_number+"]"+" of "+player.team+" attempts to take a goal but it misses the goal and "+new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" takes the corner");
                this.unsuccessful++;
                this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            }
            else if(prob2>0.4){
                log(player.name+"["+player.jersey_number+"]"+" of "+player.team+" attempts to take a goal and "+new_player.name+"["+new_player.jersey_number+"]"+" of "+new_team.team_name+" blocks and takes the ball");
                this.unsuccessful++;
                this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            }
            else if(prob2>0.1){
                log(player.name+"["+player.jersey_number+"]"+" of "+player.team+" attempts to take a goal and the goal keeper of "+new_team.team_name+" "+new_team.goal_keeper.name+"["+new_team.goal_keeper.jersey_number+"]"+" saves it and passes ball to "+new_player.name+"["+new_player.jersey_number+"] of "+new_player.team);
                this.unsuccessful++;
                this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            }
            else{
                if(player.team.equals(team1.team_name)){
                    team1_score++;
                }
                else {
                    team2_score++;
                }
                player.goals++;
                log(player.name+"["+player.jersey_number+"]"+" of "+player.team+" attempts to take a goal and its a goal! "+player.name+"["+player.jersey_number+"]"+" of "+player.team+" takes a goal");
                this.successful++;
                score_board.set_last_goal_player(player);
                log(team1.team_name+" score: "+team1_score+"\t"+team2.team_name+" score: "+team2_score);
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            return new_player;
        }
        else{
            Team injury_team;
            if(Math.random()>0.5){
                injury_team=team1;
            }
            else{
                injury_team=team2;
            }
            Field_Player injured_player=injury_team.field_players.get((int) Math.floor(Math.random()*(10)));
            log("There's an injury to "+injured_player.name+"["+injured_player.jersey_number+"]"+" of "+injured_player.team+" and he is taken out to the team doctor "+injury_team.doctor.name);
            this.injuries++;
            injury_team.doctor.injured_players.add(injured_player);
            injury_team.field_players.remove(injured_player);
            log(injury_team.bench_players.get(0).name+"["+injury_team.bench_players.get(0).jersey_number+"]"+" is substituting for the injured "+injured_player.name+"["+injured_player.jersey_number+"] of "+injured_player.team);
            injury_team.field_players.add(injury_team.bench_players.get(0));
            injury_team.bench_players.remove(injury_team.bench_players.get(0));
            this.timer+=(int) Math.floor(Math.random()*(10-5+1)+5);
        }
        return player;
    }

    /**
     * first half procedure
     * @param team1_toss whether team1 has won the toss
     */
    public void first_half(boolean team1_toss){
        Team team=(team1_toss?team1:team2);
        log("Main referee "+main_referee.name+" signals the kick off of the first half");
        Field_Player new_player = team.field_players.get(0);
        this.timer +=(int) Math.floor(Math.random()*(5-1+1)+1);
        log(new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" starts the play of the first half");
        this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
        while (timer <2700){
            new_player=play(new_player);
        }
        log("Main referee "+main_referee.name+" signals half time");
        this.timer =2700;
        score_board.display_score_board(timer,team1,team2,team1_score,team2_score,team1_shoots,team2_shoots);
    }

    /**
     * first break time procedure
     */
    public void break_time1(){
        log("It's the first 15 minute interval");
        this.timer +=60;
        log("Coach "+team1.coach.name+" of "+team1.team_name+" is speaking to the players");
        this.timer +=10;
        log("Coach "+team2.coach.name+" of "+team2.team_name+" is also speaking to the players");
        this.timer =3600;
        log("The break time is over\n");
    }

    /**
     * second half procedure
     * @param team1_toss whether team1 has won the toss
     */
    public void second_half(boolean team1_toss){
        Team team=(team1_toss?team2:team1);
        log("Main referee "+main_referee.name+" signals the kick off of the second half");
        Field_Player new_player = team.field_players.get(0);
        log(new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" starts play of the second half");
        while (timer <6300){
            new_player=play(new_player);
        }
        log("Main referee "+main_referee.name+" signals the full time");
        this.timer =6300;
        score_board.display_score_board(timer,team1,team2,team1_score,team2_score,team1_shoots,team2_shoots);
    }

    /**
     * second break time procedure
     */
    public void break_time2(){
        log("It's the second 15 minute break time");
        this.timer +=60;
        log("Coach "+team1.coach.name+" of "+team1.team_name+" is speaking to the players");
        this.timer +=10;
        log("Coach "+team2.coach.name+" of "+team2.team_name+" is also speaking to the players");
        this.timer =7200;
        log("The break time is over\n");
    }

    /**
     * first extra time procedure
     * @param team1_toss whether team1 has won the toss
     */
    public void extra_time1(boolean team1_toss){
        Team team=(team1_toss?team1:team2);
        log("Main referee "+main_referee.name+" signals the kick off of the first extra time");
        Field_Player new_player = team.field_players.get(0);
        log(new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" starts play of the first extra time");
        while (timer <8100){
            new_player=play(new_player);
        }
        log("Main referee "+main_referee.name+" signals the end of the first extra time");
        this.timer =8100;
        score_board.display_score_board(timer,team1,team2,team1_score,team2_score,team1_shoots,team2_shoots);
    }

    /**
     * third break time procedure
     */
    public void break_time3(){
        log("It's the third 15 minute break time");
        this.timer +=60;
        log("Coach "+team1.coach.name+" of "+team1.team_name+" is speaking to the players");
        this.timer +=10;
        log("Coach "+team2.coach.name+" of "+team2.team_name+" is also speaking to the players");
        this.timer =9000;
        log("The break time is over\n");
    }

    /**
     * second extra time procedure
     * @param team1_toss whether team1 has won the toss
     */
    public void extra_time2(boolean team1_toss){
        Team team=(team1_toss?team2:team1);
        log("Main referee "+main_referee.name+" signals the kick off of the second extra time");
        Field_Player new_player = team.field_players.get(0);
        log(new_player.name+"["+new_player.jersey_number+"]"+" of "+new_player.team+" starts play of the second extra time");
        while (timer <9900){
            new_player=play(new_player);
        }
        log("Main referee "+main_referee.name+" signals the end of the second extra time");
        this.timer =9900;
        score_board.display_score_board(timer,team1,team2,team1_score,team2_score,team1_shoots,team2_shoots);
    }

    /**
     * fourth break time procedure
     */
    public void break_time4(){
        log("It's the fourth 15 minute break time");
        this.timer +=60;
        log("Coach "+team1.coach.name+" of "+team1.team_name+" is speaking to the players");
        this.timer +=10;
        log("Coach "+team2.coach.name+" of "+team2.team_name+" is also speaking to the players");
        this.timer =10800;
        log("The break time is over\n");
    }

    /**
     * penalty shoots procedure
     */
    public void penalty_shoots(){
        log("Main referee "+main_referee.name+" signals the kick off of penalty shoots");
        for(int i=0;i<5;i++){
            Field_Player player1=team1.field_players.get(i);
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            double prob1=Math.random();
            if(prob1>0.7){
                log(player1.name+"["+player1.jersey_number+"] of "+player1.team+" attempts the penalty shoot and the goal keeper of "+team2.team_name+" "+team2.goal_keeper.name+"["+team2.goal_keeper.jersey_number+"]"+" saves it");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            else if(prob1>0.5){
                log(player1.name+"["+player1.jersey_number+"] of "+player1.team+" attempts the penalty shoot and the shoot misses the goal");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            else{
                team1_shoots++;
                log(player1.name+"["+player1.jersey_number+"] of "+player1.team+" attempts the penalty shoot and it is successful and takes a penalty shoot for his team");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }

            Field_Player player2=team2.field_players.get(i);
            this.timer +=(int) Math.floor(Math.random()*(10-5+1)+5);
            double prob2=Math.random();
            if(prob2>0.7){
                log(player2.name+"["+player2.jersey_number+"] of "+player2.team+" attempts the penalty shoot and the goal keeper of "+team1.team_name+" "+team1.goal_keeper.name+"["+team1.goal_keeper.jersey_number+"]"+" saves it");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            else if(prob2>0.5){
                log(player2.name+"["+player2.jersey_number+"] of "+player2.team+" attempts the penalty shoot and the shoot misses the goal");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            else{
                team2_shoots++;
                log(player2.name+"["+player2.jersey_number+"] of "+player2.team+" attempts the penalty shoot and it is successful and takes a penalty shoot for his team");
                this.timer +=(int) Math.floor(Math.random()*(20-10+1)+10);
            }
            log(team1.team_name+" successful shoots: "+team1_shoots+"/"+(i+1)+"\t"+team2.team_name+" successful shoots: "+team2_shoots+"/"+(i+1));
        }
        score_board.display_score_board(timer,team1,team2,team1_score,team2_score,team1_shoots,team2_shoots);
    }

    /**
     * final score and statistic displaying
     */
    public void score(){
        System.out.println("______________________________________________________");
        System.out.println("End of the match ("+timer /60+" minutes)");
        System.out.format("%30s\t%-30s\n", team1.team_name, team2.team_name);
        System.out.print("Goals:");
        System.out.format("%24d\t%-30d\n", team1_score,team2_score);

        ArrayList<Field_Player> team1_goals=new ArrayList<>();
        ArrayList<Field_Player> team2_goals=new ArrayList<>();

        for(int i=0;i<team1.doctor.injured_players.size();i++){
            if(team1.doctor.injured_players.get(i).goals>0){
                team1_goals.add(team1.doctor.injured_players.get(i));
            }
        }
        for(int i=0;i<team2.doctor.injured_players.size();i++){
            if(team2.doctor.injured_players.get(i).goals>0){
                team2_goals.add(team2.doctor.injured_players.get(i));
            }
        }
        for(int i=0;i<10;i++){
            if(team1.field_players.get(i).goals>0){
                team1_goals.add(team1.field_players.get(i));
            }
            if(team2.field_players.get(i).goals>0){
                team2_goals.add(team2.field_players.get(i));
            }
        }
        int size=(Math.min(team1_goals.size(), team2_goals.size()));
        for(int i=0;i<size;i++){
            System.out.format("%30s\t%-30s\n", team1_goals.get(i).name, team2_goals.get(i).name);
        }
        for(int i=size;i<Math.max(team1_goals.size(), team2_goals.size());i++){
            if(team1_goals.size()>team2_goals.size()){
                System.out.format("%30s\t%-30s\n", team1_goals.get(i).name, "");
            }
            else{
                System.out.format("%30s\t%-30s\n", "", team2_goals.get(i).name);
            }
        }

        if(timer>9900){
            System.out.print("Penalty Shoots:");
            System.out.format("%15d\t%-30d\n", team1_shoots,team2_shoots);
        }
        System.out.println("______________________________________________________");
        System.out.println("Game statistics");
        System.out.println("\tPasses: "+this.passes);
        System.out.println("\tPossessions: "+this.possessions);
        System.out.println("\tInterceptions: "+this.interceptions);
        System.out.println("\tPlay outs: "+this.outs);
        System.out.println("\tAttempts: "+this.attempts);
        System.out.println("\tSuccessful: "+this.successful);
        System.out.println("\tUnsuccessful: "+this.unsuccessful);
        System.out.println("\tInjuries: "+this.injuries);
        System.out.println("______________________________________________________");
        if(team1_score>team2_score){
            System.out.println(team1.team_name+" win by a lead of "+(team1_score-team2_score)+" goal(s)");
        }
        else if(team2_score>team1_score){
            System.out.println(team2.team_name+" win by a lead of "+(team2_score-team1_score)+" goal(s)");
        }
        else{
            if(team1_shoots>team2_shoots){
                System.out.println(team1.team_name+" win by a lead of "+(team1_shoots-team2_shoots)+" penalty shoot(s)");
            }
            else if(team2_shoots>team1_shoots){
                System.out.println(team2.team_name+" win by a lead of "+(team2_shoots-team1_shoots)+" penalty shoot(s)");
            }
            else{
                System.out.println("Match ties");
            }
        }
        System.out.println("______________________________________________________");
    }

    /**
     * to get the number of previous goals
     * @param name name of the player
     * @return number of already taken goals
     */
    private int goal_count(String name){
        String line3= "";

        try(BufferedReader file=new BufferedReader(new FileReader("Player_Records/"+name+".txt"))){
            String line1= file.readLine();
            String line2= file.readLine();
            line3=file.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return Integer.parseInt(line3);
    }

    /**
     * writing player data to files
     */
    public void write_to_files(){
        for(int i=0;i<team1.doctor.injured_players.size();i++){
            int goals=goal_count(team1.doctor.injured_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team1.doctor.injured_players.get(i).name+".txt")){
                file.write(team1.doctor.injured_players.get(i).name+"\n");
                file.write(team1.doctor.injured_players.get(i).team+"\n");
                file.write(String.valueOf(goals+team1.doctor.injured_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<team2.doctor.injured_players.size();i++){
            int goals=goal_count(team2.doctor.injured_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team2.doctor.injured_players.get(i).name+".txt")){
                file.write(team2.doctor.injured_players.get(i).name+"\n");
                file.write(team2.doctor.injured_players.get(i).team+"\n");
                file.write(String.valueOf(team2.doctor.injured_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<10;i++){
            int goals1=goal_count(team1.field_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team1.field_players.get(i).name+".txt")){
                file.write(team1.field_players.get(i).name+"\n");
                file.write(team1.field_players.get(i).team+"\n");
                file.write(String.valueOf(goals1+team1.field_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
            int goals2=goal_count(team2.field_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team2.field_players.get(i).name+".txt")){
                file.write(team2.field_players.get(i).name+"\n");
                file.write(team2.field_players.get(i).team+"\n");
                file.write(String.valueOf(goals2+team2.field_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<team1.bench_players.size();i++){
            int goals=goal_count(team1.bench_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team1.bench_players.get(i).name+".txt")){
                file.write(team1.bench_players.get(i).name+"\n");
                file.write(team1.bench_players.get(i).team+"\n");
                file.write(String.valueOf(goals+team1.bench_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<team2.bench_players.size();i++){
            int goals=goal_count(team2.bench_players.get(i).name);
            try(FileWriter file=new FileWriter("Player_Records/"+team2.bench_players.get(i).name+".txt")){
                file.write(team2.bench_players.get(i).name+"\n");
                file.write(team2.bench_players.get(i).team+"\n");
                file.write(String.valueOf(goals+team2.bench_players.get(i).goals));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
