# recipe-service

### Installations
The following guides illustrate how to use some features concretely:
* [Installing Java](https://www.oracle.com/java/technologies/downloads/)
* [Installing Maven](https://maven.apache.org/install.html)
* [Installing H2](https://www.h2database.com/html/download.html)
* [Installing GitHub](https://docs.github.com/en/desktop/installing-and-configuring-github-desktop/installing-and-authenticating-to-github-desktop/installing-github-desktop)

### Developers Guide:
* Requirements:
    - Java
    - SpringBoot
    - Maven
    - H2
    - Github (personal account)
* Install application:
    - Cloning : `git clone https://github.com/sshukla154/recipe.git`. NOTE: As of now, I have kept it public so could be cloned but will make it private after results.
    - Open project in terminal or CMD in source code folder and run `mvn eclipse:clean` - This deletes the .project, .classpath, .wtpmodules files and .settings folder used by Eclipse.
    - Build/install project `mvn clean install`
    - Update DB Credentials in application.properties file
    - Update Server port in application.properties file (default is 8080)
    - To run only tests, execute `mvn test`
    - To run the application, execute `java -jar recipe-service-0.0.1-SNAPSHOT.jar`
* [Swagger URL](http://localhost:8080/swagger-ui/index.html)

### Consider but could not implement seeing the time constraint:
* 1. Any basic authentication
