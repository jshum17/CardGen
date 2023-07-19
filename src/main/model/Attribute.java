package model;

public class Attribute {
    private String name;
    private String value;

    // EFFECTS: constructs a new attribute with a name and value
    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // getters

    // EFFECTS: returns the name of the attribute
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the value of the attribute
    public String getValue() {
        return this.value;
    }
}
