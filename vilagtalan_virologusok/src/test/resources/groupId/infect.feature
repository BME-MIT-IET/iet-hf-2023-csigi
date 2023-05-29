Feature: The virologist infects another virologist

  Scenario: He doesn't have enough material, so he can't create it
    Given Two virologist are on the same field
    And The virologist doesn't have enough material
    When He tries to infect the other virologist
    Then The other virologist doesn't get infected

  Scenario: He doesn't know the genetic code for the agent, so he can't create it
    Given Two virologist are on the same field
    And The virologist doesn't know the genetic code for the agent
    When He tries to infect the other virologist
    Then The other virologist doesn't get infected

  Scenario: The other virologist wears no protective equipment, so he gets infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    And The other virologist doesn't have any protective equipment
    When He tries to infect the other virologist
    Then The other virologist gets infected

  Scenario: The other virologist wears a cloak, so he doesn't get infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    And The other virologist has a cloak
    When He tries to infect the other virologist
    Then The other virologist doesn't get infected

  Scenario: The other virologist wears a cloak, so he doesn't get infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    And The other virologist has gloves
    When He tries to infect the other virologist
    Then The infector gets infected
