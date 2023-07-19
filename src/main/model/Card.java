package model;

public class Card {
    private String name;
    private Attribute attributeOne;
    private Attribute attributeTwo;
    private Attribute attributeThree;
    private String description;

    // EFFECTS: constructs a new card with a name, three attributes, and a description
    public Card(String cardName, Attribute attributeOne, Attribute attributeTwo, Attribute attributeThree,
                String description) {
        this.name = cardName;
        this.attributeOne = attributeOne;
        this.attributeTwo = attributeTwo;
        this.attributeThree = attributeThree;
        this.description = description;
    }

    // getters

    // EFFECTS: returns the name of the card
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the first attribute of the card
    public Attribute getAttributeOne() {
        return this.attributeOne;
    }

    // EFFECTS: returns the second attribute of the card
    public Attribute getAttributeTwo() {
        return this.attributeTwo;
    }

    // EFFECTS: returns the third attribute of the card
    public Attribute getAttributeThree() {
        return this.attributeThree;
    }

    // EFFECTS: returns the description of the card
    public String getDescription() {
        return this.description;
    }

}
