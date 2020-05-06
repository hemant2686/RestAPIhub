Feature: Validate the GoogleAPI's using Maven Cucumber framework

@AddPlaceAPI @Regression
Scenario Outline: Google API's using RestAssured services

Given When Google API Payload is provided with "<name>" "<language>" "<address>"
When Google API's "AddPlaceAPI" with "POST" Method is executed
Then Verify that the execution is success
And Status is OK
And Verify that the "GetPlaceAPI" service is execute GET method with the "placeId" which matches "<name>" successfully

Examples:

|name  				|language|address      	 |
|Chetan home address|Hindi   |D1101 Lakevista|
|Hemant home address|Marathi |A-608 Lakevista|
|Chirag home address|Spanish |D1101 Lakevista|
|Govind home address|Tamil   |A-608 Lakevista|


@DeletePlaceAPI @Regression
Scenario: Verify if Delete functionality is working

Given DeletePlace Payload is provided
When Google API's "DeletePlaceAPI" with "DELETE" Method is executed
Then Verify that the execution is success
And Status is OK
