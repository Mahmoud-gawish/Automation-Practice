# Automation-Practice
This is where I practice Test Automation!

### The main Frameworks included in the project:
* Selenium Webdriver
* Rest-Assured
* TestNG
* Allure Report
* Extent Reports
* Apachi POI


### Design implementation:
* Page Object Model (POM) design pattern
* Data Driven framework
* Fluent design approach (method chaining)
* Have a supporting Utilities package in *src/main/java* file path, named ***"Utils"*** that includes many wrapper methods in static classes which services as a core engine for the project 
* Implementing the ***Test Automation Pyramid*** by have 2 different test automation levels which are SERVICE and GUI layers

### How to run the project main test cases:
* A properties file ***"automationPractice.properties"*** can be found it *src/main/resources* file path including all the configurations needed in the execution
* Can find the test cases in the *src/test/java* folder mainly in the *phptravels.tests* and *restfulbooker.tests* packages
* Can find the test suite for all the main practice test cases in the *src/test/resources/TestSuits* folder in the *automationPractice.xml* file
* To start the execution, please make sure that the "execution.type" property is "Local" if you are running locally then right click on the test suite xml file and click Run As >> TestNG Suit 
* After executing, you can easily generate the ***Allure Report*** by opening a command-line terminal on the project root path and type `mvn allure:serve` (needs to be able to execute mvn commands); Or you can find the Extent Report ***ExtentReports.html*** in the project root path for the latest execution


###### Finally, you can also find a [playlist on Youtube](https://youtube.com/playlist?list=PLmayvCz0Xqr6TT-XJHlPtjDSdJ8WArBHi) (Arabic content) that summarizes and executing the project for some cases
