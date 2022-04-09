Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013-01-01 and 2015-01-01
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'
    Given a book with the title 'Cool Book', written by 'GRRM', published in 2014-05-06
      And another book with the title 'Even Cooler Book', written by 'Fernando Pessoa', published in 1978-05-16
      And another book with the title 'Even Coolest Book', written by 'Fernando Pessoa', published in 1978-05-16
    When the customer searches for books by 'Fernando Pessoa'
    Then 2 books should have been found
      And Book 1 should have the title 'Even Cooler Book'
      And Book 2 should have the title 'Even Coolest Book'

  Scenario: Search books by title
    Given I have the following books in store
      | title                         | author          | date        |
      | Why am I hungry?              | Nicolas Cage    | 1998-05-22  |
      | Why am I hungry? The sequel   | Nicolas Cage    | 2000-04-14  |
      | Why am I hungry? The prequel  | Nicolas Cage    | 1999-06-19  |
      | Some other book               | Tim Tomson      | 2014-08-23  |
      | How to cook a dino            | Fred Flintstone | 2012-01-01  | 
    When the customer searches for books titled 'Why am I hungry?'
    Then 3 books should have been found
      And Book 1 should have the title 'Why am I hungry?'
      And Book 2 should have the title 'Why am I hungry? The prequel'
      And Book 3 should have the title 'Why am I hungry? The sequel'