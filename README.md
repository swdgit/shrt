# shrt
Spring-Boot with MongoDB URL Shortener service.

This is a self contained Spring Boot jar that will run off the default embedded Tomcat.

# Dependencies
* Java 8 JDK
* MongoDB 

# API
`mvn javadoc:javadoc` will generate the apidocs for this app.

## Controllers
 * Security
  * Used to generate a user account, update password, company name and activate/deactivate a given account.
 * ShrtURL 
  * Used to redirect based on a given unique Id, create, update activate/deactivate a given url. 

# Test
Built in test uses the embedded mongodb from flapdoodle. 

Local simulated prod testing db. ./mongod --dbpath ~/tmp/mongodb/data/db

debug mode via spring boot
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8787"