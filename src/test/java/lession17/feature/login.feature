Feature: Login Rise's website

  Scenario Outline: Kiểm tra tính năng đăng nhập
    Given Truy cập website
    When Login with "<username>" and "<password>"
    Then Login successfully. The dashboard is shown
    Examples:
    |username       |password|
    |admin@demo.com |riseDemo|
    |client@demo.com|riseDemo|