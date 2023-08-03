### Overview
A command line application which parses a cron expression and expands each field displaying the timeframe the passed command will run.

#### Pre-requisites
- This application is written using Java 17.
- Maven version (3.9.x) should be installed.

#### Build and launch.
- Clone the repository from the [repository](git@github.com:pv-912/CornParser.git).
- Open the terminal and navigate to the correspondign directory. 
- Execute the below command to build the project using maven.

 ````
$ mvn clean install
````
- After the project is successfully build, a directory named target would create inside the project directory.
````
$ cd target
````

#### Running the script
- Navigate to project-root/target and execute the below command
```bash
$ java -jar cornParser-3.0.1.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
  minute        0 15 30 45
  hour          0
  day of month  1 15
  month         1 2 3 4 5 6 7 8 9 10 11 12
  day of week   1 2 3 4 5
  command       /usr/bin/find
```

#### Limitations and Improvements


- Currently, this exercise considers days in a month to be 31. This can be further improved. 

- Code structure could be definitely be improved, and further comments could be added to improve readability.

- There are tests, and Exception handling, however more tests can be added

- This application fails to take month/week names and only based on numeric values.