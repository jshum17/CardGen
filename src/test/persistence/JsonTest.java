package persistence;

import model.Attribute;
import model.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: Modelled after JsonSerializationDemo
public class JsonTest {
    protected void checkCard(String name, Attribute attributeOne, Attribute attributeTwo, Attribute attributeThree,
                             String description, Card card) {
        assertEquals(name, card.getName());
        assertEquals(attributeOne, card.getAttributeOne());
        assertEquals(attributeTwo, card.getAttributeTwo());
        assertEquals(attributeThree, card.getAttributeThree());
        assertEquals(description, card.getDescription());
    }

    protected void checkAttribute(String name, String value, Attribute attribute) {
        assertEquals(name, attribute.getName());
        assertEquals(value, attribute.getValue());
    }

}

