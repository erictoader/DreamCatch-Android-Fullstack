# DreamCatch 🌙
DreamCatch is an application designed to monitor topics you dream of and evaluate the quality of your sleep over time. Users can input a description and tags to add entries, and further evaluate the quality of their sleep using metrics.

<img width="433" alt="Screenshot 2023-10-19 at 16 53 56" src="https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/edb4eaa6-fbf8-4a6b-b267-f57cd54cfd37">
<img width="433" alt="Screenshot 2023-10-19 at 16 54 32" src="https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/3be8ec50-8384-4be5-9b36-cdbf96908134">

### Table of Contents

- [Requirements Analysis](#requirements-analysis)
  * [Assignment Specification](#assignment-specification)
  * [Functional Requirements](#functional-requirements)
  * [Non-functional Requirements](#non-functional-requirements)
- [Use-Case Model](#use-case-model)
- [System Architectural Design](#system-architectural-design)
  * [Architectural Pattern Description](#architectural-pattern-description)
  * [Diagrams](#diagrams)
- [UML Sequence Diagrams](#uml-sequence-diagrams)
- [Class Design](#class-design)
  * [Design Patterns Description](#design-patterns-description)
  * [UML Class Diagram](#uml-class-diagram)
- [Data Model](#data-model)
- [System Testing](#system-testing)
- [Bibliography](#bibliography)

## Requirements Analysis

### Assignment Specification
Objective of DreamCatch is to allow users to input and track their dreams and sleep quality.

### Functional Requirements
- Add new entries with description, tags, and sleep metrics.
- View and filter entries by category and tags.
- View daily sleep metric charts based on category and date.
- Chart color indication based on trends.

### Non-functional Requirements
- Valid input validations and formats.
- ORM for database interactions.
- Use of DI container and CQRS architecture.
- Implementation of various design patterns.
- Database storage.

<br>
<img width="433" alt="Screenshot 2023-10-19 at 16 54 52" src="https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/a1e2de0f-8456-4645-a151-5e90f729223f">
<img width="433" alt="Screenshot 2023-10-19 at 16 54 12" src="https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/e82ef943-c663-47e8-a4b2-8cc264520942">

## Use-Case Model

Describes the "Fetch all entries" use-case.
![image](https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/66b2483e-cece-42e2-bdbd-8893a09f89d8)

## System Architectural Design

### Architectural Pattern Description

Using Data-Domain-Presentation on the frontend and Command Query Responsibility Segregation (CQRS) architectural pattern on the backend.

### Diagrams
![image](https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/b54b779f-3cf1-45ac-861c-df5b958d0f6e)
![image](https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/ea3e5e06-487e-4eba-9c33-d6b74961f8d7)

## UML Sequence Diagrams
![image](https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/12a65647-cb1d-47f7-908c-4621f3154abf)

## Class Design

### Design Patterns Description

Detailed breakdown of the MVVM, Decorator, Strategy patterns and Use Cases.

### UML Class Diagram
![image](https://github.com/erictoader/DreamCatch-Android-Fullstack/assets/99261319/1bc64ae8-6882-4a48-b43e-bf87485ca695)

## Data Model

SQLite and DataStore are utilized for structured data and tag storage.

## System Testing

Details on various testing methods and strategies implemented to assure application quality.

## Bibliography

List of references and useful links.
1.	https://www.baeldung.com/cs/layered-architecture
2.	https://www.digitalocean.com/community/tutorials/android-mvvm-design-pattern
3.	https://developer.android.com/topic/architecture/intro
4.	https://medium.com/huawei-developers/why-should-we-use-use-case-classes-in-our-android-projects-142de0f952fd
5.	https://www.sqlite.org/index.html
6.	https://medium.com/androiddevelopers/all-about-preferences-datastore-cc7995679334
7.	https://developer.android.com/training/testing/fundamentals
8.	https://developer.android.com/training/testing/instrumented-tests
9.	https://mockk.io/ANDROID.html
10.	https://www.baeldung.com/cqrs-event-sourcing-java
11.	https://refactoring.guru/design-patterns/mediator
12.	https://refactoring.guru/design-patterns/decorator
