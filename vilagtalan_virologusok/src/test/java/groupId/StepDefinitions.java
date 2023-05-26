package groupId;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Game {
    private List<Agent> learnables;
    public Game(){
        learnables = new ArrayList<>();
    }

    public void setLearnables(List<Agent> learnables) {
        this.learnables = learnables;
    }
    public boolean won(int learned){
        return learned == learnables.size();
    }
}
class Virologist {
    private List<Agent> leaned;
    private Field field;

    public Virologist(){
        leaned = new ArrayList<>();
    }
    public List<Agent> getLeaned() {
        return leaned;
    }

    public void setField(Field field) {
        this.field = field;
    }
    public boolean learn(){
        if (field.learn() == null)
            return false;
        leaned.add(field.learn());
        return true;
    }
    public void addAgent(Agent agent){
        leaned.add(agent);
    }
}
abstract class Agent {

}
class Agent1 extends Agent{

}
class Agent2 extends Agent{

}

abstract class Field{
    private List<Virologist> virologistList;
    public Field(){
        virologistList = new ArrayList();
    }
    public void stepsOn(Virologist v){
        this.virologistList.add(v);
        v.setField(this);
    }
    public Agent learn(){
        return null;
    }
}
class Laboratory extends Field{
    private Agent agent;
    public Laboratory(Agent agent){
        this.agent = agent;
    }

    @java.lang.Override
    public Agent learn() {
        return agent;
    }
}
public class StepDefinitions {
    private Game game;
    private Virologist virologist;
    private Agent missing;
    private Laboratory laboratory;
    @Given("The virologist have learned all the genomes except for one")
    public void the_virologist_have_learned_all_the_genomes_except_for_one() {
        // Write code here that turns the phrase above into concrete actions
        game = new Game();
        missing = new Agent1();
        List<Agent> agentList = new ArrayList<>();
        agentList.add(new Agent1());
        agentList.add(new Agent2());
        game.setLearnables(agentList);
        virologist = new Virologist();
        virologist.addAgent(new Agent2());
    }
    @Given("He is on a laboratory where he can learn that genome")
    public void he_is_on_a_laboratory_where_he_can_learn_that_genome() {
        // Write code here that turns the phrase above into concrete actions
        laboratory = new Laboratory(missing);
        laboratory.stepsOn(virologist);
    }
    @When("He learns that genome")
    public void he_learns_that_genome() {
        // Write code here that turns the phrase above into concrete actions
        virologist.learn();
    }
    @Then("He wins the game")
    public void he_wins_the_game() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(game.won(virologist.getLeaned().size()));
    }
    @Given("The virologist needs to learn multiple genomes")
    public void the_virologist_needs_to_learn_multiple_genomes() {
        // Write code here that turns the phrase above into concrete actions
        game = new Game();
        missing = new Agent1();
        List<Agent> agentList = new ArrayList<>();
        agentList.add(new Agent1());
        agentList.add(new Agent2());
        game.setLearnables(agentList);
        virologist = new Virologist();
    }
    @Given("He is on a laboratory")
    public void he_is_on_a_laboratory() {
        // Write code here that turns the phrase above into concrete actions
        laboratory = new Laboratory(new Agent1());
        laboratory.stepsOn(virologist);
    }
    @Then("The game continues")
    public void the_game_continues() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(game.won(virologist.getLeaned().size()));
    }


}
