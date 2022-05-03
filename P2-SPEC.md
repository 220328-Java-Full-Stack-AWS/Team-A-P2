# Project 2 - E-Commerce SPA

## Executive Summary
The E-Commerce Single-Page Application will allow consumers to browse, search, and buy products. Users will be able to browse our catelog of products, or search for a specific item, and add these to a cart. Users can register accounts which includes a profile. There are a number of stretch goals to choose from. Please update this text to summarize additional features. This text is fairly generic, your project will be unique, this text should be re-written to reflect your final deliverable.


# Tech Stack
 - Languages
   - Java
   - JavaScript
   - TypeScript
 - Data Persistence
   - PostgreSQL
   - Hibernate
 - AWS
   - RDS
   - S3
   - CodeBuild & CodePipeline
   - ElasticBeanstalk
 - Spring Framework
   - Spring Boot
   - Spring MVC
 - Angular

## Forbidden Abstractions:
 - Spring Data JPA - Spring Data JPA uses Hibernate as an ORM provider. Before we get to that level of abstraction, we will use hibernate directly ourselves.


# Functional Requirements
### Required:
 - Domain objects persisted in relational database via ORM
 - All CRUD functionality accessible via RESTful API
 - Single page web UI to consume RESTful API
 - Workflows to complete all user stories
 - Validate all user input
 - Unit test coverage for service-layer classes

### Stretch Goals:
 - Application is merged, tested, and deployed with a fully functional CI/CD Pipeline

The persistence layer shall use Hibernate ORM to translate between the database and the application server. The API layer shall abstract away the low-level servlets with Spring Web MVC. The client shall use Angular to produce an SPA which is styled to be functional and readable. The server should follow a proper layered architecture, and have adequate unit testing of the service layer. The client and server should communicate in a RESTful manner, and the server should be stateless. 


## User Stories
### Requirements:
#### Guest
 - As a guest, I can register for an account.
 - As a guest, I can log in to my account.

#### User:
 - As a user, I have a profile which I can view.
 - As a user, I can browse products and add them to my cart.
 - As a user, I can remove items from my cart.
 - As a user, I can checkout to purchase the items in my cart.

### Stretch Goals:
 - As a User, I should be able to select an amount of an item to add to my cart as I am adding an item
 - As a User, I should be able to search the product list to better find the item(s) I am interested in
 - As a User, I should be able to see and purchase items that are on sale for a lower price.
 - As a User, I should be able to see a list of featured products on the main page of the application
 - As a User, my session should be maintained until I log out.
 - As a User, I should be able to reset my password.
 - As a User, I should be able to change the color scheme from the normal mode to a dark mode option.
 - As a User, I should receive notifications when a transaction has occurred or a transfer has been completed.

# Submission
### Due Date: Thursday 5/26/2022 at 9:00 AM CST
Your project needs to be pushed into the main branch of your team's P2 repository no later than the due data and time above. Commits after this deadline will not be considered. On the due date there will be a presentation. You will be expected to briefly cover your project, and should be prepared to discuss it with QC.

# A Note About P2

I said for P2 I would be less lenient about the final deliverable, and one of the teams wondered if I could go into detail. The P1s were very good and for the most part did adhere to these important principles. These are the main things that I would like everyone to be internalizing.
Firstly, how our application is layered. Most if not all P1s I looked through had a properly layered backend with API | Service | Persistence layers. We want these layers to be well defined. Services should have little to no state, all communication with the client should exist only in the API layer, and all database interaction should be entirely in the persistence layer. These layers should only be dependent on those immediately next door. So, for instance, you should be able to completely remove your API code without breaking anything in the persistence layer. (obviously the service layer would break all over the place).
Also we can take this structure and make it even more granular. Classes should be grouped into the layers, but how to we know how many and what classes we should be writing? Consider the SOLID principles:

 - Single Responsibility Principle - "A class should have one and only one reason to change, meaning that a class should have only one job."
 - Open-Closed Principle
 - Liskov Substitution Principle
 - Interface Segregation Principle
 - Dependency Inversion Principle - "Entities must depend on abstractions, not on concretions. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions."  

Use these principles to guide your design of classes. Most important are the first and last: Single Responsibility and Dependency Inversion.  
  
Single responsibility is fairly obvious, ask yourself "Is this class doing too much?"  
  
Dependency Inversion is a little more tricky. This basically means that one class should not depend on another, instead they should both depend on some abstraction. Consider a Car class which is dependent on a Wheel class.
```
public Class Car {
    private Wheel wheel;
    ...
}
```
If you wanted to change the car to use a tank treads instead, you would have to refactor the car class. Instead, have car depend on an abstraction that represents wheels or treads.
```
public Class Car {
   private Mover mover;
   ...
}

public Interface Mover {
   ...
}

public Class Wheel implements Mover {
   ...
}

public Class Tread implements Mover {
   ...
}
```

Finally the other thing I am looking for is for us to follow the RESTful constraint about transmitting resource representations as HTTP body payloads. Any time we are transmitting something that should be persisted to or retrieved from the database it should probably be sent as an HTTP request body in JSON format, which represents a model to be translated to/from the database.

### So to recap:
 - Single responsibility principle.
 - Dependency inversion principle
 - RESTful communication