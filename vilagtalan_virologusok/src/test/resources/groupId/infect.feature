Feature: The virologist infects another virologist

  Scenario: The other virologist wears no protective equipment, so he gets infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    When The other virologist doesn't have any protective equipment
    Then He gets infected

  Scenario: The other virologist wears a cloak, so he doesn't get infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    When The other virologist has a cloak
    Then He doesn't get infected

  Scenario: The other virologist wears a cloak, so he doesn't get infected
    Given Two virologist are on the same field
    And One virologist can infect the other
    When The other virologist has gloves
    Then The infector gets infected
