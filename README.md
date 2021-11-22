# Community Game Reviews (CGR)
Community Game Reviews is a full stack web application where users can perform CRUD functionality with respect to games and reviews. In other words, CGR is a community managed review aggregator, with the aim of letting only the community decide what score a game should receive. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

* MySQL Server 5.7 or later (local or GCP instance)
* Java (local)
* Optional but recommended - MySQL Workbench
* Optional but recommended - Eclipse Integrated Development Environment (IDE) 
* Optional but recommended - visual studio code (IDE) with live server extension
* Optional but recommended - google chrome browser 

### Installing

**MySQL Server**
Follow the link and instructions to install on your Windows machine https://dev.mysql.com/downloads/installer/
(recommended) - select MySQL Workbench within the package installer. 
(optional) Guide to install on Mac machines https://flaviocopes.com/mysql-how-to-install/

**Java**
Download and install the corresponding java package which is compatible with your preferred operating system https://www.java.com/en/download/manual.jsp

**Eclipse (optional)**
Recommended IDE for Java developers https://www.eclipse.org/downloads/packages/release/neon/2/eclipse-ide-java-developers

**Visual Studio code (optional)**
Recommended IDE for HTML, CSS and JavaScript developers https://code.visualstudio.com/download

**Google Chrome**
Recommended browser for use, development and testing https://www.google.co.uk/chrome/?brand=CHBD&brand=NMEO&gclid=Cj0KCQjwrJOMBhCZARIsAGEd4VGrSNL3Uj9eLtKq9JJUgklE5-ceqnOiup9GvoExasCqs_Tvy0HjErUaAvdqEALw_wcB&gclsrc=aw.ds

**Running CGR**
1. Git clone this repository into your local machine within your preferred location.
2. Configure the application.properties file (found within src/main/resources), setting spring.profiles.active to prod or test as appropriate.  
3. If using spring.profiles.active=prod, make sure to configure application-prod.properties with the relevant url, username and password which matches your mySQL credentials.
4. Right-click on the root folder, select "Run As" and then "Spring Boot App" to launch the back-end application or server. 
5. Within your IDE, preferably visual studio code, navigate to src/main/resources/static and with the index.html file selected, copy and paste the location within your browser, alternatively and if available, click on "Go Live" within visual studio code whilst on the index.html file.    
6. Within the browser, click on Games in the navbar and navigate to "Add a game" to get started.    

## Running the tests

To test the application, right click on the root folder within Eclipse, select "Run As" and then "JUnit Test"

This will run the Selenium tests (see below for details) and the integration test files (see below for details).

To run the unit tests, right click the relevant unit test files found within src/test/java, click "Run As" then "run configurations" and configure the test file to run as Junit4. 

### Unit Tests 

The unit test files test whether each 'unit' i.e. method of the application is functioning as expected.

Unit tests have been written to cover the following file types:
1. Controller
2. Service 
3. Repo

### Integration Tests 
Integration tests have been written using the SpringBootTest framework, the purpose of which is to test the functioning of the software when modules are combined.

Integration tests have been written to test how the controller interacts with the corresponding service and repo files.

### Front-end Tests 
Tests have been written using the selenium webdriver to test a range of front end (navigation-focused) user stories

## Deployment

The IMS application is deployed in a fat JAR file i.e. a JAR file that contains the Java program, front-end files (e.g. HTML, CSS and JavaScript) and all dependencies. 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Daniel Li** - *completed CGR* - [danielli](https://github.com/DanLi14)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to Reece Elder and Anoush Lowton. 
