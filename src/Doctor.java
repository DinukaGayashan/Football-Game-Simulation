import java.util.ArrayList;

/**
 * defines a doctor which is a person
 */
public class Doctor extends Person {
    private String team;
    public ArrayList <Field_Player> injured_players=new ArrayList<>();

    /**
     * sets doctor attributes
     * @param name name of the doctor
     * @param team team of the doctor
     */
    Doctor(String name, String team){
        super(name);
        this.team=team;
    }
}
