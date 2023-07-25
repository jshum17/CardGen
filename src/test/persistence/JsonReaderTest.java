package persistence;


import model.Attribute;
import model.Card;
import model.Inventory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: Modelled after JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory inv = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            Inventory inv = reader.read();
            assertEquals(0, inv.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInventory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInventory.json");
        try {
            Inventory inv = reader.read();
            List<Card> cards = inv.getAllCards();
            assertEquals(2, cards.size());
            checkAttribute("Health","100",cards.get(0).getAttributeOne());
            checkAttribute("Speed","99",cards.get(0).getAttributeTwo());
            checkAttribute("Attack","4",cards.get(0).getAttributeThree());
            checkAttribute("Health","100",cards.get(1).getAttributeOne());
            checkAttribute("Speed","98",cards.get(1).getAttributeTwo());
            checkAttribute("Attack","3",cards.get(1).getAttributeThree());
            checkCard("David",cards.get(0).getAttributeOne(),cards.get(0).getAttributeTwo(),
                    cards.get(0).getAttributeThree(),"David is handsome", cards.get(0));
            checkCard("Justin",cards.get(1).getAttributeOne(),cards.get(1).getAttributeTwo(),
                    cards.get(1).getAttributeThree(),"Justin is dumb", cards.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}