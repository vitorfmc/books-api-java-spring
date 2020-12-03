# Java - Google Books Integration API

**LAST UPDATE:** 10/2019

Follow me: https://www.linkedin.com/in/vitor-cordeiro-921a5697/

---

### 1. Introduction

Study: This is a Java REST application that integrates with Google Books to generate a book library.

---

### 2. Installation

1. Install [SDKMAN](https://sdkman.io/)

```
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
```
2. Install Java and Gradle:

```
$ sdk use java 8
$ sdk use gradle 3.5
```

3. [OPTIONAL] Install Intellij and Lombok plugin

    3.1 Instalar de [IDE](https://www.jetbrains.com/idea/);
    
    3.2 With the Intellij open, navegate to: File > Settings... > Plugins.
    
    3.3 Install Lombok plugin and restart the IDE.
    
    3.4 Enable the anotation processor by checking "Enable Annotation Processing" at: File > Settings... > Build, Execution, Deployment >
   Compiler > Annotation Processors
   
---

### 3. Executing

```
$ gradle buildRun
```

---
    
### 4. Documentation

1. Main goal:

   https://github.com/vitorfmc/google-books-integration-api 

2. For API documentation: 

    **${APPLICATION_DOMAIN}**/swagger-ui.html

3. Tecnologies:

    - Java 1.8;
    - SpringBoot 2.2.0;
    - Gradle 5.6.2;
    - MongoDB;

4. More information about the API:

    - The API architeture is base on MVC design pattern;
    - All the requests are mapped on BookController class;
    - All the errors are handled at CustomGlobalExceptionHandler class;
    - The CronConfig class is responsable to schedule calls to Google API;  

