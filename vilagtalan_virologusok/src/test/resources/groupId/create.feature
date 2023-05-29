Feature: The virologist creates an agent with the materials he has

  Scenario: He doesn't know the genetic code for the agent, so he can't create it
    Given The virologist doesn't know the genetic code for the agent
    When He tries to create the agent
    Then The agent is not created

  Scenario: He doesn't have enough material, so he can't create it
    Given The virologist doesn't have enough material
    When He tries to create the agent
    Then The agent is not created

  Scenario: He knows the code and have the needed materials, so he creates it
    Given The virologist knows the genetic code for the agent
    And The virologist has enough material
    When He tries to create the agent
    Then The agent is created