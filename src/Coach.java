/**
 * defines a coach which is a person
 */
public class Coach extends Person {
    private String team;

    /**
     * sets coach attributes
     * @param name name of the coach
     * @param team team of the coach
     */
    Coach(String name, String team){
        super(name);
        this.team=team;
    }
}
