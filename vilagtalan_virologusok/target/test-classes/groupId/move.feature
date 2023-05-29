Feature: The virologist steps from one field to another
  Scenario: The virologist is not infected by random move agent so he moves correctly
    Given The virologist is not infected by randomMove agent
    When He tries to move to a field
    Then He gets to that field

  Scenario: The virologist is infected by randomMove agent so he moves incorrectly
    Given The virologist is infected by randomMove agent
    When He tries to move to a field
    Then He gets to another field
