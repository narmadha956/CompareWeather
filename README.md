# CompareWeather
This is a simple UI-API comparator project. 

The weather details from UI and API can be compared by running one single command.

____________________________________________________________________________________________________________________________________________________

**Configuration Details**

One single config file to manage all the features to be compared and tested.

Add the details of cities and metrics(ex. temperature, humidity) to be tested in comparatorConfig.json

_____________________________________________________________________________________________________________________________________________________

**Installation Guide**

git clone repository

mvn install test -Dsurefire.suiteXmlFiles=testng.xml

______________________________________________________________________________________________________________________________________________________

**Reporting Guide**
Provide the details of report portal properties in src/test/resources reportportal.properties files

Detailed report of UI screenshots and API reponse attachemnts can be found against each test in case of failures. 


