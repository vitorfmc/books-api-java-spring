# Google Books Integration API (Java+Quarkus+GraalVM)

This a Quarkus study project that integrates with Google Books API.

API Documentation: http://.../swagger-ui/#/


## 1. Technologies

* Quakus Framework (https://quarkus.io/);
* GraalVM;
* Java 11;

## 2. Executing and Deploying

#### 2.1 Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

#### 2.2 Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-google-books-integration-api-1.0.0-SNAPSHOT-runner.jar` file in the `/build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-google-books-integration-api-1.0.0-SNAPSHOT-runner.jar`.

#### 2.3 Send to AWS

To deploy the application throw cloudformation, use the script: 
```shell script
./infra/deploy-samcli.sh dev
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-google-books-integration-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## 3. Reference
* Reference for studies: https://quarkus.io/guides/
