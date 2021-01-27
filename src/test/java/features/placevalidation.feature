Feature: Validating Place API
@AddPlace
Scenario Outline: verify if Place is sucessfully added using AddPlaceAPI
        
        Given Add Place Payload "<name>" "<website>" "<language>"
        When user call "AddPlace" with "POST" http request
        Then The API cal got sucess with status code 
        And "status" in response body is "OK"
        And "scope" in response body is "APP"
        And verify place_id created  maps  to "<name>" using "GetPlace"
        
        Examples:
    
      |name   |website    |language|
      |NewLand|www.fgt.com|English |
 #     |Great Amber|www.amber.com|Spanish| 
        
        @DeletePlace
        Scenario: Verify DeletePlace API is working
        And verify place deleated for "place_id" using "DeletePlace"
            
    
      
      
       