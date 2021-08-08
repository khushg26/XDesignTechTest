# XDesignTechTest

I have implemented this excercise using Spring Boot.

Description of various files in the project -

MunroCSVReader -      loading csv during application startups

MunroDataController - "/getHill" endpoint is mapped in this controller.

                      Query Parameter for filtering data loaded by MunroCSVReader and default values 
                       1. hillCategory=""  - String(valid values - "MUN", "TOP")
                       2. sortBy = "name"  - String(valid values - "name" and "height")
                       3. ascendingOrder = true
                       4. count            - number of records to return
                       5. minHeight
                       6. maxHeight                     

MunroServiceImpl -    It implements MunroService class. Actual business logic lies in this classs

HillCategory -        Enum to hold all the hill categories

Year -                Enum to hold all the Years.

InvalidHillCategoryException - This exception is thrown when invalid hill category is passed while calling the API.

InvalidMinMaxHeight -          This exception is thrown when invalid values of min and max height are passed while calling the API.

InvalidSortByField -           This exception is thrown when invalid value is passed for sortBy query param while calling the API.

MunroLibraryApplicationTests - This class hold the JUnits, covering both positive and negative scenarios.

Sample Http request - http://localhost:8080/getHill?hillCategory=TOP&sortBy=height&ascendingOrder=false&count=10&minHeight=1174&maxHeight=1221
