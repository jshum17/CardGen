package model;

public class Card {
    private String name;
    private Attribute attributeOne;
    private Attribute attributeTwo;
    private Attribute attributeThree;
    private String description;

    public Card(String cardName, Attribute attributeOne, Attribute attributeTwo, Attribute attributeThree,
                String description) {
        this.name = cardName;
        this.attributeOne = attributeOne;
        this.attributeTwo = attributeTwo;
        this.attributeThree = attributeThree;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public Attribute getAttributeOne() {
        return this.attributeOne;
    }

    public Attribute getAttributeTwo() {
        return this.attributeTwo;
    }

    public Attribute getAttributeThree() {
        return this.attributeThree;
    }

    public String getDescription() {
        return this.description;
    }

}
