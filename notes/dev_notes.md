# 3X+1 Developer Notes



## Building the Project

### main build command

	mvn clean install
	
	mvn clean source:jar javadoc:jar install -DskipTests

### To build the uber jar, run:

	mvn clean install assembly:single

### To display available updates to Maven dependencies:
 (see also: https://www.baeldung.com/maven-dependency-latest-version)
 
	mvn versions:display-dependency-updates
	
## To build licensing information:

	mvn site

The results are written to [ ./java-project/target/site/dependencies.html ].

## To build Javadoc
	mvn javadoc:javadoc


