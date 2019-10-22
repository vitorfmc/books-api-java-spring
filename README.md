# Google Books Integration APIs

**LAST UPDATE:** 10/2019

Follow me: https://www.linkedin.com/in/vitor-cordeiro-921a5697/

---

### 1. Introduction

This repository brings together a number of identical APIs in different languages, where each one of them integrates with [Google Books](https://developers.google.com/books/docs/v1/using) to generate its own [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) service.
The ultimate goal is to generate an example of communication and integration between two rest APIs using different languages.

---

### 2. Tecnologies so far

- Java; (IN PROGRESS)
- Kotlin; (TO DO)
- Go; (TO DO)
- NodeJs; (TO DO)

---

### 3. Concept

![Integration Diagram](https://raw.githubusercontent.com/vitorfmc/google-books-integration-api/master/integrations_chart.png)

**Consumer interation steps:**

The Consumer call the CRUD APIs and the application will retrive Book information from Google when creating and updating requests occurs;

**CRON interation steps:**

The application will execute a CRON to automatically update Google information based on a schedulle;

---

### 4. Documentation

**Google books:** https://developers.google.com/books/docs/v1/using (Last Visit: 21/10/2019)

**Postman requests (Import and use in Postman):** [post_man.json](https://raw.githubusercontent.com/vitorfmc/google-books-integration-api/master/postman_collection.json)

**Requests:**

[GET] /v1/book?q=

[POST] /v1/book

Body: { "title":"String", "libraryCode":"String", "catalogingDate":"String"}

Restrictions:
- Title must be unique;
- Code must be unique and can only contains letters and numbers;
- Cataloging date must be a string with the following format: dd/mm/yyyy

[DELETE] /v1/book/{id}

[PUT] /v1/book/{id}

Body: { "title":"String", "libraryCode":"String", "catalogingDate":"String"}

Restrictions:
- Title must be unique;
- Code must be unique and can only contains letters and numbers;
- Cataloging date must be a string with the following format: dd/mm/yyyy
