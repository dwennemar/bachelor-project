# Bachelor-Project

<p align="center">
    <a href="https://travis-ci.org/dwennemar/bachelor-project">
        <img src="https://travis-ci.org/dwennemar/bachelor-project.svg?branch=master" alt="Travis Badge"/>
    </a>
    <a href="https://github.com/dwennemar/bachelor-project/blob/master/LICENSE.md">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="license"/>
    </a>
    <a href="https://openjdk.java.net/projects/jdk/11/">
        <img src="https://img.shields.io/badge/Java-11-orange.svg" alt="language version">
    </a>
</p>

Implementation of the project for my Bachelor-Thesis.  
It's about GDPR-compliant database backup handling.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

All necessary dependencies handled by [Maven](https://maven.apache.org) and defined in the several `pom.xml`-files.

### Installing

The dependencies should be installed by your IDE.

If not, go to (sub)project root and run:

```
mvn install
```

## Running the tests

Defined JUnit-Tests can be executed by running

````
mvn test
````

## Deployment

The microservices get deployed as docker-container.

*More information will be added, when the dockerfiles are created*

## Built With

* [Spring](https://spring.io) - Java-Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Lombok](https://projectlombok.org/) - Generate boilerplate code

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/dwennemar/bachelor-project/tags). 

## Authors

* **Daniel Wennemar** - *Initial work* - [GitHub](https://github.com/dwennemar/)

See also the list of [contributors](https://github.com/dwennemar/bachelor-project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
