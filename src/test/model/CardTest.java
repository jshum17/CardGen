package model;

import exception.CardRestrictionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private Card testCard;
    private Card testCard2;
    private Attribute a1;
    private Attribute a2;
    private Attribute a3;
    private String desc;

    @BeforeEach
    public void setUp(){
        a1 = new Attribute("Health", "100");
        a2 = new Attribute("Speed", "98");
        a3 = new Attribute("Attack", "99");
        desc = "David is a fierce fighter.";
        testCard = new Card("David", a1, a2, a3, desc);
        testCard2 = new Card("", a1, a2, a3, desc);
    }

    @Test
    public void testConstructor(){
        assertEquals("David",testCard.getName());
        assertEquals(a1,testCard.getAttributeOne());
        assertEquals(a2,testCard.getAttributeTwo());
        assertEquals(a3,testCard.getAttributeThree());
        assertEquals(desc, testCard.getDescription());
    }

    @Test
    public void testCardRestrictions() {
        try {
            testCard2.addCardRestrictions();
        } catch (CardRestrictionException e) {
            // expected
        }
    }

}
