# Google Books Integration API (Java+SpringBoot+MongoDB)

**LAST UPDATE:** 10/2019

Follow me: https://www.linkedin.com/in/vitor-cordeiro-921a5697/

---

### 1. Introduction

This project main objective is provide a [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) API service that integrates with [Google Books API](https://developers.google.com/books/docs/v1/using) to generate search data for study purposes.

---

### 2. How it works


The application works as a rest-api, which stores data from a book in a DynamoDB.
The integration with Google takes place at the moment of persistence, in which the application searches for additional information on Google to popularize the Book entity.

![Integration Diagram](https://raw.githubusercontent.com/vitorfmc/google-books-integration-api/master/help/integrations_chart.png)

Additional technical information:

* The API architeture is base on MVC design pattern;
* All the requests are mapped on BookController class;
* All the errors are handled at CustomGlobalExceptionHandler class;
* The CronConfig class is responsable to schedule calls to Google API;

---

## 3. Technologies

* SpringBoot;
* Swagger;
* Java 8;
* MongoDB;

---

### 4. Installation

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

### 5. Executing

```
$ gradle buildRun
```

---
    
## 6. Documentation so far

**6.1 Google books:** 

https://developers.google.com/books/docs/v1/using (Last Visit: 02/12/2020)

**6.2 Postman requests (Import and use in Postman):** 

[postman_collection.json](https://raw.githubusercontent.com/vitorfmc/google-books-integration-api/master/help/postman_collection.json)

**6.3 API Documentation:**

**NOTE:** If you are running the API, you can access the documentation using the path: **${APPLICATION_DOMAIN}**/swagger-ui.html

```
[GET] /v1/book?q=
```

```
[POST] /v1/book

Body: { "title":"String", "libraryCode":"String", "catalogingDate":"String"}

Restrictions:
- Title must be unique;
- Code must be unique and can only contains letters and numbers;
- Cataloging date must be a string with the following format: dd/mm/yyyy
```

```
[DELETE] /v1/book/{id}
```

```
[PUT] /v1/book/{id}

Body: { "title":"String", "libraryCode":"String", "catalogingDate":"String"}

Restrictions:
- Title must be unique;
- Code must be unique and can only contains letters and numbers;
- Cataloging date must be a string with the following format: dd/mm/yyyy
```

---
    
## 7. TO DO

* Create TESTs;
