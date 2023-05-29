Feature: The virologist learns a genome

  Scenario: It was the last one
    Given The virologist have learned all the genomes except for one
    And He is on a laboratory where he can learn that genome
    When He learns that genome
    Then He wins the game

  Scenario: He needs to learn more
    Given The virologist needs to learn multiple genomes
    And He is on a laboratory
    When He learns that genome
    Then The game continues
