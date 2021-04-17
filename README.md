[![Build Status](https://travis-ci.com/mischkes/logmein-challenge.svg?branch=master)](https://travis-ci.com/mischkes/logmein-challenge)
[![codecov](https://codecov.io/gh/mischkes/logmein-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/mischkes/logmein-challenge)
[![Known Vulnerabilities](https://snyk.io/test/github/mischkes/logmein-challenge/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/mischkes/logmein-challenge?targetFile=pom.xml)
# Logmein Challenge

A simple REST API that completes the LogMeIn programming challenge.

Locally, access the API through Swagger at
http://localhost:8081/shopping-list/swagger-ui/


# Challenge Description
Implement a simple shopping list service that allows a user to manage a list of items they
want to buy during their next time doing their groceries.

### Task Description
* Implement the shopping list as a REST service based on Java Spring Boot
* The service should allow to insert an item, change an item, delete an item, and get a
list of all items on the list
* Inserting an item should generate a unique ID for later reference
* The service does not offer a UI
* The shopping list items do not need to survive a service restart
* For simplicity: Do not bother about multi-user or authentication scenarios. For this
exercise the API does not need to be secured.
* Limit the scope of the functionality to the specification
* The project should build a WAR file, deployable on a web container.
* Feel free to use online references and resources. Add citations when lifting blocks of
code.

# Used References
 
Project initialization spring through boot cli: https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#cli-jar

Deployment: https://www.baeldung.com/tomcat-deploy-war

CodeCov: https://github.com/codecov/example-java
