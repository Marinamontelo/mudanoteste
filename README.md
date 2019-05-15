# mudanoteste

Requirements: 
Java8
https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
Maven

https://maven.apache.org/install.html

To run Tests:
# mvn clean test

To run main class:

# mvn exec:java -Dexec.mainClass="com.teste.extract.ExtractFile" -Dexec.classpathScope=runtime  


The file should be in src/main/resources/file.txt



