package persistence;

import model.Attribute;
import model.Card;
import model.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: Modelled after JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInventory() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            inv = reader.read();
            assertEquals(0, inv.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInventory() {
        try {
            Inventory inv = new Inventory();
            Attribute attr1 = new Attribute("Health", "100");
            Attribute attr2 = new Attribute("Speed", "99");
            Attribute attr3 = new Attribute("Attack", "4");
            Attribute attr4 = new Attribute("Health", "100");
            Attribute attr5 = new Attribute("Speed", "98");
            Attribute attr6 = new Attribute("Attack", "3");
            inv.addCard(new Card("David", attr1, attr2, attr3, "David is handsome"));
            inv.addCard(new Card("Justin", attr4, attr5, attr6, "Justin is dumb"));
            JsonWriter writer = new JsonWriter("./data/testWriterInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterInventory.json");
            inv = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
