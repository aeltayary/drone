# drone-srv-app

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) drone-srv-app.

## General commnets

For building and running the application you need:

- The application is a set of API rest services developed using JDK 11 and spring boot 2.6.2 and built using Apache Maven 3.6.3
- The application connects to MySQL database
- DB_HOST, DB_PORT, DB_USER_NAME, DB_PASSWORD environment variables must be set to make the application able to locate the configuration
- The application default port is 8080 and this can be configured when run
- REST API definition is provided with the application using http://[host]:[port]/swagger-ui/index.html

## build&run steps 


- Execute the db/drone.sql , this will created "dronedb" database and user "musala"
- Based on your DB config from the above step, define the below environment variables on the machine that will run the application <br>
  DB_HOST should refer to the database host. For example, 192.168.1.12 <br>
  DB_PORT should refer to the dadabase port. For example, 3066<br>
  DB_USER_NAME should be "musala" <br>
  DB_PASSWORD  should be "musala" <br>
  If these environment varaible are not set, application will use localhost database configuration
- go to the drone-srv folder and execute "mvn install" to build the application jar file.On success, this will be "drone-srv\target\drone-srv-0.0.1-SNAPSHOT.jar"
- From command line , run "java -jar drone-srv-0.0.1-SNAPSHOT.jar" to run the application on port 8080  or set your port using  "java -jar drone-srv-0.0.1-SNAPSHOT.jar -Dserver.port=[your-port]"
- Open your browser and hit http://[host]:[port]/swagger-ui/index.html to see the api definition
```


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
