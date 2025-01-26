# Cron Expression Parser

## Project Overview
The Cron Expression Parser is a Java-based application that parses a standard cron expression into its 
individual components (minute, hour, day of month, month, day of week, and command) and expands each field to show its
execution times. It adheres to the SOLID principles, making it extensible and maintainable. 
This project is implemented using Maven for dependency management and build automation.

## Features
- Supports standard cron expressions with fields for minute, hour, day of month, month, and day of week.
- Expands wildcard (`*`), ranges (e.g., `1-5`), lists (e.g., `1,15`), and step values (e.g., `*/15`).
- Supports unique and sorted outputs using `TreeSet`.
- Validates values to ensure they are within acceptable ranges.
- Allows future extensions, such as non-standard cron formats (e.g., `@hourly`, `@yearly`) and day abbreviations (e.g., `Mon`, `Tue`).

## System Requirements
- **Java Development Kit (JDK)**: Version 8 or higher.
- **Maven**: Version 3.6 or higher.

## How to Build and Run the Project

### 1. Using Maven Commands

#### **Build the Project**
Run the following command to build the project and generate the JAR file:
```bash
mvn clean package
```

The generated JAR file will be located in the `target` directory:
```
target/CronExpressionParser-1.0-SNAPSHOT.jar
```

#### **Run Tests**
Execute the tests using:
```bash
mvn test
```
This command will run all the unit tests and display the results in the console.

#### **Run the Application**
To run the application with Maven, use the following command:
```bash
mvn exec:java -Dexec.mainClass="com.example.parser.CronExpressionParser" -Dexec.args="\"*/15 0 1,15 * 1-5 /usr/bin/find\""
```
Ensure you escape the cron expression argument correctly.

---

### 2. Using the JAR File

#### **Run the JAR**
After building the project, you can run the generated JAR file directly with:
```bash
java -jar target/CronExpressionParser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

Replace the cron expression with your desired input.

#### **Example Output**
For the input:
```bash
*/15 0 1,15 * 1-5 /usr/bin/find
```
The output will be:
```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

---

## Project Structure
The project follows the standard Maven structure. The application is designed to be extensible.
Below is an overview of how the code is structured and how new features can be added:

com.example.parser: Contains the main classes, including:

   CronExpressionParser: Entry point for the application.

   CronExpression: Handles parsing and validation of cron expressions.

   CronExpressionPreprocessor: Converts non-standard formats (e.g., @hourly) to standard cron expressions.

com.example.fields: Field-specific parsers implementing FieldParser, such as:

   MinuteField, HourField, DayOfMonthField, MonthField, DayOfWeekField.

   Handles specific parsing logic and expansions (e.g., abbreviations like Mon).

com.example.utils: Utility classes for shared logic:

   CronUtils: Expands cron fields, validates ranges, and deduplicates output.

   CronFieldParser: Delegates parsing to field-specific parsers.

com.example.test: Unit tests to validate functionality.


## Additional Notes
- **Error Handling**: The program throws detailed `IllegalArgumentException` messages for invalid inputs.
- **Testing**: Comprehensive unit tests cover both valid and invalid cron expressions, ensuring robustness.

---
