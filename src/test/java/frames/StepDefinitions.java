package frames;

import io.cucumber.java.en.*;

import java.util.ArrayList;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private TestGame game;
    private TestVirologist virologist;
    private Agens missing;
    private Labor laboratory;
    @Given("The virologist have learned all the genomes except for one")
    public void the_virologist_have_learned_all_the_genomes_except_for_one() {
        game = new TestGame(null);
        ArrayList<Agens> agentList = new ArrayList<>();
        missing =  new Vitustanc().duplicate();
        Agens agent1 = new Vitustanc().duplicate();
        Agens agent2 = new Felejt().duplicate();
        agentList.add(agent1);
        agentList.add(agent2);
        game.setLearnable(agentList);
        virologist = new TestVirologist();
        virologist.addAgentCode(agent2.duplicate());
        virologist.setJatek(game);
    }
    @Given("He is on a laboratory where he can learn that genome")
    public void he_is_on_a_laboratory_where_he_can_learn_that_genome() {
        laboratory = new Labor(missing, false);
        laboratory.ralep(virologist);
        virologist.setField(laboratory);
    }
    @When("He learns that genome")
    public void he_learns_that_genome() {
        virologist.tapogasdLe();
    }
    @Then("He wins the game")
    public void he_wins_the_game() {
        assertTrue(game.nyerte_e(virologist.getIsmertKodok().size()));
    }
    @Given("The virologist needs to learn multiple genomes")
    public void the_virologist_needs_to_learn_multiple_genomes() {
        game = new TestGame(null);
        ArrayList<Agens> agentList = new ArrayList<>();
        missing =  new Vitustanc().duplicate();
        Agens agent1 = new Vitustanc().duplicate();
        Agens agent2 = new Felejt().duplicate();
        agentList.add(agent1);
        agentList.add(agent2);
        game.setLearnable(agentList);
        virologist = new TestVirologist();
        virologist.setJatek(game);
    }
    @Given("He is on a laboratory")
    public void he_is_on_a_laboratory() {
        // Write code here that turns the phrase above into concrete actions
        laboratory = new Labor(new Vitustanc().duplicate(), false);
        laboratory.ralep(virologist);
        virologist.setField(laboratory);
    }
    @Then("The game continues")
    public void the_game_continues() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(game.nyerte_e(virologist.getIsmertKodok().size()));
    }
    TestVirologist otherVirologist;
    Agens infecting;
    @Given("Two virologist are on the same field")
    public void two_virologist_are_on_the_same_field() {
        game = new TestGame(null);
        infecting = new Vitustanc().duplicate();
        virologist = new TestVirologist();
        virologist.setJatek(game);
        virologist.addAgentCode(infecting.duplicate());
        otherVirologist = new TestVirologist();
        otherVirologist.setJatek(game);
    }
    @Given("The virologist doesn't have enough material")
    public void the_virologist_doesn_t_have_enough_material() {
        virologist.getAnyag().csokkent(100);
    }
    @When("He tries to infect the other virologist")
    public void he_tries_to_infect_the_other_virologist() {
        virologist.kendMeg(otherVirologist, infecting);
    }
    @Then("The other virologist doesn't get infected")
    public void the_other_virologist_doesn_t_get_infected() {
        assertFalse(game.containsAgn(otherVirologist.getAgensek(), infecting));
    }
    @Given("The virologist doesn't know the genetic code for the agent")
    public void the_virologist_doesn_t_know_the_genetic_code_for_the_agent() {
        // It's not added to the known genetic codes
    }
    @Given("One virologist can infect the other")
    public void one_virologist_can_infect_the_other() {
        virologist.addAgentCode(infecting);
        virologist.getAnyag().novel(100);
    }
    @Given("The other virologist doesn't have any protective equipment")
    public void the_other_virologist_doesn_t_have_any_protective_equipment() {
        // by default
    }
    @Then("The other virologist gets infected")
    public void the_other_virologist_gets_infected() {
        assertTrue(game.containsAgn(otherVirologist.getAgensek(), infecting));
    }
    @Given("The other virologist has a cloak")
    public void the_other_virologist_has_a_cloak() {
        TestCloak cloak = new TestCloak();
        cloak.begin(otherVirologist);
        otherVirologist.addVedofelsz(cloak);
    }
    @Given("The other virologist has gloves")
    public void the_other_virologist_has_gloves() {
        otherVirologist.addVedofelsz(new Kesztyu());
    }
    @Then("The infector gets infected")
    public void the_infector_gets_infected() {
        assertTrue(game.containsAgn(virologist.getAgensek(), infecting));
    }
    Mezo actual;
    Mezo neighbour1;
    Mezo neighbour2;
    @Given("The virologist is not infected by randomMove agent")
    public void the_virologist_is_not_infected_by_random_move_agent() {
        game = new TestGame(null);
        actual = new Ovohely(new Kesztyu());
        neighbour1 = new Labor(new Felejt(), false);
        neighbour2 = new Raktar();
        ArrayList<Mezo> neighbours =new ArrayList<>();
        neighbours.add(neighbour1);
        neighbours.add(neighbour2);
        actual.set_szomszedok(neighbours);
        virologist = new TestVirologist();
        virologist.setJatek(game);
        virologist.setField(actual);
        virologist.setNeighbours(neighbours);
        //by default not infected
    }
    @When("He tries to move to a field")
    public void he_tries_to_move_to_a_field() {
        // Write code here that turns the phrase above into concrete actions
        virologist.lep(0);
    }
    @Then("He gets to that field")
    public void he_gets_to_that_field() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(virologist.getMostMezo().equals(neighbour1));
    }
    @Given("The virologist is infected by randomMove agent")
    public void the_virologist_is_infected_by_random_move_agent() {
        game = new TestGame(null);
        actual = new Ovohely(new Kesztyu());
        neighbour1 = new Labor(new Felejt(), false);
        neighbour2 = new Raktar();
        ArrayList<Mezo> neighbours =new ArrayList<>();
        neighbours.add(neighbour1);
        neighbours.add(neighbour2);
        actual.set_szomszedok(neighbours);
        virologist = new TestVirologist();
        virologist.setJatek(game);
        virologist.setField(actual);
        virologist.setNeighbours(neighbours);
        virologist.megken(virologist, new TestRandom());
    }
    @Then("He gets to another field")
    public void he_gets_to_another_field() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(virologist.getMostMezo().equals(neighbour1));
    }
    @Given("The other virologist is paralyzed")
    public void the_other_virologist_is_paralyzed() {
        otherVirologist.megken(otherVirologist, new Benit());
    }
    @Given("the other virologist has an equipment.")
    public void the_other_virologist_has_an_equipment() {
        otherVirologist.addVedofelsz(new Zsak());
    }

    @When("He tries to steal from the other virologist")
    public void he_tries_to_steal_from_the_other_virologist() {
        virologist.tamaddMeg(otherVirologist);
    }
    @Then("He can steal")
    public void he_can_steal() {
        assertEquals(0, otherVirologist.getVedofelszList().size());
        assertEquals(1, virologist.getVedofelszList().size());
    }
    @Given("The other virologist isn't paralyzed")
    public void the_other_virologist_isn_t_paralyzed() {
        // by default
    }
    @Then("He can't steal")
    public void he_can_t_steal() {
        assertEquals(1, otherVirologist.getVedofelszList().size());
        assertEquals(0, virologist.getVedofelszList().size());
    }
}
