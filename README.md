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
