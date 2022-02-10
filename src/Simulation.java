/**
 * defines the simulation of the game
 */
public class Simulation {

    /**
     * provides data for the simulation and drives the simulation of the game
     * @param args arguments
     */
    public static void main(String[] args){

        String team1_name="Barcelona";
        Coach coach1=new Coach("Xavi Hernández",team1_name);
        Doctor doctor1=new Doctor("Ramon Cugat Bertomeu",team1_name);
        Field_Player[] field_players1= {
                new Field_Player("Martin Braithwaite",9,team1_name),
                new Field_Player("Konrad de la Fuente",29,team1_name),
                new Field_Player("Ousmane Dembélé",11,team1_name),
                new Field_Player("Álex Collado",30,team1_name),
                new Field_Player("Busquets",5,team1_name),
                new Field_Player("Coutinho",14,team1_name),
                new Field_Player("Frenkie de Jong",21,team1_name),
                new Field_Player("Ronald Araújo",4,team1_name),
                new Field_Player("Sergiño Dest",2,team1_name),
                new Field_Player("Jordi Alba",18,team1_name),
                new Field_Player("Ansu Fati",22,team1_name),
                new Field_Player("Antoine Griezmann",7,team1_name),
                new Field_Player("Ilaix Moriba",27,team1_name),
                new Field_Player("Matheus Fernandes",19,team1_name),
                new Field_Player("Pedri",16,team1_name),
                new Field_Player("Junior Firpo",24,team1_name),
                new Field_Player("Clément Lenglet",15,team1_name)};
        Goal_Keeper goal_keeper1=new Goal_Keeper("Arnau Tenas",36,team1_name);
        Team team1=new Team(team1_name,coach1,doctor1,field_players1,goal_keeper1);

        String team2_name="Manchester United";
        Coach coach2=new Coach("Ole Gunnar Solskjær",team2_name);
        Doctor doctor2=new Doctor("Steve McNally",team2_name);
        Field_Player[] field_players2= {
                new Field_Player("Edinson Cavani",7,team2_name),
                new Field_Player("Amad Diallo",19,team2_name),
                new Field_Player("Anthony Elanga",56,team2_name),
                new Field_Player("Arnau Puigmal",47,team2_name),
                new Field_Player("Bruno Fernandes",18,team2_name),
                new Field_Player("Fred",17,team2_name),
                new Field_Player("Ethan Galbraith",54,team2_name),
                new Field_Player("Alex Telles",27,team2_name),
                new Field_Player("Eric Bailly",3,team2_name),
                new Field_Player("Will Fish",48,team2_name),
                new Field_Player("Manson Greenwood",11,team2_name),
                new Field_Player("Anthony Martial",9,team2_name),
                new Field_Player("Daniel James",21,team2_name),
                new Field_Player("Jesse Lingard",14,team2_name),
                new Field_Player("Mata",8,team2_name),
                new Field_Player("Phil Jones",4,team2_name),
                new Field_Player("Victor Lindelöf",2,team2_name)};
        Goal_Keeper goal_keeper2=new Goal_Keeper("Nathan Bishop",30,team2_name);
        Team team2=new Team(team2_name,coach2,doctor2,field_players2,goal_keeper2);

        Referee main_referee=new Referee("Mike Dean");
        Referee sub_referee=new Referee("Michael Oliver");

        Ball ball=new Ball();

        Football_Game game=new Football_Game(team1,team2,main_referee,sub_referee,ball);


        System.out.println("The game between "+game.team1.team_name+" & "+game.team2.team_name+" is about to start\n");

        boolean team1_toss=game.toss();
        if(team1_toss){
            System.out.println(game.team1.team_name+" has won the toss");
        }
        else{
            System.out.println(game.team2.team_name+" has won the toss");
        }

        game.display_teams();

        System.out.println("\nIt's time to start the encounter\n");
        game.first_half(team1_toss);
        game.break_time1();
        game.second_half(team1_toss);
        if(game.equal_score()){
            game.break_time2();
            game.extra_time1(team1_toss);
            if(game.equal_score()){
                game.break_time3();
                game.extra_time2(team1_toss);
                if(game.equal_score()){
                    game.break_time4();
                    game.penalty_shoots();
                }
            }
        }
        game.score();
        game.write_to_files();
    }
}
