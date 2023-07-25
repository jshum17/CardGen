package model;

import org.json.JSONObject;
import persistence.Writable;

public class Card implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("attributeOneName", attributeOne.getName());
        json.put("attributeOneValue", attributeOne.getValue());
        json.put("attributeTwoName", attributeTwo.getName());
        json.put("attributeTwoValue", attributeTwo.getValue());
        json.put("attributeThreeName", attributeThree.getName());
        json.put("attributeThreeValue", attributeThree.getValue());
        json.put("description", description);
        return json;
    }
}
