# CS_Assignment

**Pre-requisites:**
- Java 8 installed
- Maven installed

**Run the program:**

1. Download the repository .zip
2. Unzip it
3. Open command line in the home directory (example: C:\users\roton\Download\CS_Assignment-master\)
4. Execute this command:   
  4.a Replace "path/to/log/file/" with the real path of the log file you want to test              
  4.b Or with "src/main/resources/server.log" in case you don't have a log file

``` mvn exec:java -Dexec.mainClass=com.rotonmeta.cs.App -Dexec.cleanupDaemonThreads=false -Dexec.args="path/to/log/file" ```



**Run the tests:**
1. Execute this command: ``` mvn test ```


**Some remarks:**
- I have used log4j2 to log informations about the database connection and the generated events on the console.
- Unit test coverage is 76% (main is not tested).
- This program can handle big files: it uses streams to process the data.
- I have thinked of 2 multi-threaded solution:
  1. Replacing streams with parallelStreams, this would be a simple solution.
  2. Creating another class that implements the runnable interface, and put in the run() method the function that extracts the Event instance from the log file.
