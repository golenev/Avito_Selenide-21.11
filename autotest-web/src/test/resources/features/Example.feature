Feature: search the most expensive printers
  Background:
    Given main page is open
    Scenario:
      And equipment is selected at dropdown list of category
      And value "Принтер" entered in search field
      Then click at dropdown list of regions
      Then value "Владивосток" is entered in the region field
      And show ads button is pressed
      Then page search results is open
      And checkbox onlyWithPhoto is activated
      And value ExpensiveAtFirst is chosen from Sort dropdown list
      And value of name and price is printed in console for the first three products

