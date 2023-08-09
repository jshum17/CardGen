# CardGen

## A collectible card generator 

> **What will the application do?**
> - Create customizable collectible cards with description and attributes
> - View all cards created
> - Search for a card

> **Who will use it?**
> - For any enthusiastic collectible card collectors
> - For those who want to turn their favourite character into a collectible card

> **Why is this project of interest to you?**
> - As a card game lover, having a customizable card generator is a fun way for me to unleash my creativity and immortalize my favourite characters from TV and video games into a collectible card

### User Stories

- [x] As a user, I want to be able to create and add cards to my inventory.
- [x] As a user, I want to be able to remove cards from my inventory.
- [ ] As a user, I want to be able to edit and modify the attributes of my cards.
- [x] As a user, I want to be able to add a description to my cards.
- [x] As a user, I want to be able to view each card in my inventory.
- [x] As a user, I want to be able to save all current app data.
- [x] As a user, I want to be able to load a saved app state.

### Instructions for Grader

- You can generate the first required action (adding) related to adding Xs to a Y by clicking the "Add Card" button, then filling in the Name, Attribute and Description text fields and clicking the "Add" button.
- You can generate the second required action (removing) related to adding Xs to a Y by clicking the "View Inventory" button then selecting the card to remove from the list and click the "Remove" button.
- You can locate my visual component from the application's background as well as clicking the "View" button in the inventory after creating a card.
- You can save the state of my application by clicking the "Save" button
- You can reload the state of my application by clicking the "Load" button

### Phase 4: Task 2
```
Wed Aug 09 00:00:04 PDT 2023
A Card has been added
Wed Aug 09 00:00:07 PDT 2023
A Card has been searched and returned
Wed Aug 09 00:00:10 PDT 2023
A Card has been searched and returned
Wed Aug 09 00:00:10 PDT 2023
A Card has been removed
```

### Phase 4: Task 3

- A refactoring design that comes to my mind after reflecting on my UML diagram is to refactor each component of my GUI class into their own classes. By splitting each component apart, instead of having a cluster of different methods in one big GUI class, each component will be able to serve their own responsibility in their own respective class. Each component can update and access each other through a bidirectional relationship to the main GUI class. This will greatly increase the cohesion of the program and improve the readability of the main GUI class.