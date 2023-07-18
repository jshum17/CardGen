package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeTest {
    private Attribute testAttribute;

    @BeforeEach
    public void setUp(){
        testAttribute = new Attribute("Health", "100");
    }

    @Test
    public void testConstructor(){
        assertEquals("Health",testAttribute.getName());
        assertEquals("100",testAttribute.getValue());
    }
}
