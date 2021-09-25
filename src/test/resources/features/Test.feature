@Report
Feature:Verify Microsite content

  Scenario Outline: I can divide <firstOperand> with <secondOperand> and get correct result
    Given User access the website
    When I divide <firstOperand> with <secondOperand>
    Then I get the result from <firstOperand> <operator> <secondOperand>

    Examples:
      | firstOperand  | secondOperand | operator |
      | 9             | 3             |:         |
      | 78            | 51            |:         |
      | 999999999     | 258           |:         |
      | 989871235     | 999999999     |:         |

  Scenario Outline: I can switch operation from <firstOperator> to <secondOperator>
    Given User access the website
    When I switch <firstOperator> to <secondOperator> for <firstOperand> with <secondOperand>
    Then I get the result from <firstOperand> <secondOperator> <secondOperand>

    Examples:
      | firstOperand  | secondOperand | firstOperator | secondOperator |
      | 279           | 62            | -             | /            |
      | 78            | 51            | /             | -              |

  Scenario Outline: I can substract <firstOperand> with <secondOperand>
    Given User access the website
    When I subtract <firstOperand> with <secondOperand>
    Then I get the result from <firstOperand> <operator> <secondOperand>

    Examples:
      | firstOperand  | secondOperand | operator |
      | 10            | 5             | -        |
      | 86            | 959           | -        |
      | 892           | 999999999     | -        |
      | 100200300     | 283           | -        |


