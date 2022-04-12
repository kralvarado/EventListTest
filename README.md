# EventListTest
A test to get json and display in MVVM

Initial code and project submitted and tested. Runs successfully.

Ran out of time for writing unit tests.
For testing the retrieval of the data, I would move the http get request into a separate method that returns the string of the json.  The GetEventListThread  would call this method for the string data to build the list of events.  Having the method would allow the unit testing to call and verify data was received.

For testing the parsing of data, I would move the Gson parsing into a method that takes the json string and returns an ArrayList of the EventItems.  The unit test would then verify if the list is either null or has more than zero items for success.
