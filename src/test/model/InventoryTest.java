package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory testInventory;
    private Card c1;
    private Card c2;
    private Attribute a1;
    private Attribute a2;
    private Attribute a3;
    private Attribute a4;
    private Attribute a5;
    private Attribute a6;

    @BeforeEach
    public void setUp() {
        testInventory = new Inventory();
        a1 = new Attribute("Health", "88");
        a2 = new Attribute("Speed", "69");
        a3 = new Attribute("Attack", "54");
        a4 = new Attribute("Wisdom", "92");
        a5 = new Attribute("Defense", "41");
        a6 = new Attribute("Mana", "73");
        c1 = new Card("David", a1, a2, a3, "David is handsome");
        c2 = new Card("Gregor", a4, a5, a6, "Gregor is the man");
    }

    @Test
    public void testAddCard() {
        assertEquals(0,testInventory.getSize());
        testInventory.addCard(c1);
        assertEquals(1,testInventory.getSize());
        testInventory.addCard(c2);
        assertEquals(2,testInventory.getSize());
    }

    @Test
    public void testRemoveCard() {
        testInventory.addCard(c1);
        testInventory.addCard(c2);
        assertTrue(testInventory.removeCard(c1));
        assertEquals(1,testInventory.getSize());

        assertFalse(testInventory.removeCard(c1));
        assertTrue(testInventory.removeCard(c2));
        assertEquals(0,testInventory.getSize());
    }

    @Test
    public void testRemoveCardEmpty() {
        assertEquals(0,testInventory.getSize());
        assertFalse(testInventory.removeCard(c2));
    }

    @Test
    public void testSearchCard() {
        testInventory.addCard(c1);
        assertEquals(c1,testInventory.searchCard("David"));
    }

    @Test
    public void testSearchCardFailed() {
        assertNull(testInventory.searchCard("Justin"));
    }

}