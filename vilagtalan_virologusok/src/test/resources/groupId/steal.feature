Feature: A virologist can steal items from a paralyzed virologist
  Scenario: The other virologist is paralyzed so he can steal
    Given Two virologists are on the same field
    And One virologist is on turn
    And The other virologist is paralyzed
    When He tries to steal from the other virologist
    Then He can steal

  Scenario: The other virologist isn't paralyzed so he can't steal
    Given Two virologists are on the same field
    And One virologist is on turn
    And The other virologist isn't paralyzed
    When He tries to steal from the other virologist
    Then He can't steal