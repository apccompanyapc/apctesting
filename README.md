# GitHub API Automation Framework

## Overview
This project is an API automation framework using Java 17, Cucumber, RestAssured, and JUnit. It includes basic CRUD operations for testing the GitHub API.

## Project Requirements:
● Clone the repository https://github.com/apccompanyapc/apctesting.git
● Get the token from https://pwpush.com/p/lcdrsyp3oyxu1dcv/r link and paste it to the "token=" in the src/test/resources/config.properties file.
Example: token=K62L07y2AlfK62L07y2AlfK62L07y2Alf

## Project Structure
- `src/test/resources/features`: Contains feature files.
- `src/test/java/steps`: Contains step definitions.
- `src/test/java/runners`: Contains test runner.

## Usage
    NOTE: Please follow the Project Requirements steps first!

● To run all the tests via TestRunner class, go to "src/test/java/runners/TestRunner.java" and click on the "Run" button.

● To run all the tests via Terminal, type "mvn clean verify" and hit enter.

● To run all the tests via Maven tab, click on Maven tab then double-click on "verify".

● To run a single test, go to "src/test/resources/features" file select the regarding feature file and click on the "Run" button.

## Docker
●  To run the project via Docker, run the docker file under "apc/Dockerfile".


