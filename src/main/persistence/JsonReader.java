package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Attribute;
import model.Card;
import model.Inventory;
import org.json.*;

// Citation: Modelled after JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory inv = new Inventory();
        addCards(inv, jsonObject);
        return inv;
    }

    // MODIFIES: inv
    // EFFECTS: parses cards from JSON object and adds them to inventory
    private void addCards(Inventory inv, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(inv, nextCard);
        }
    }

    // MODIFIES: inv
    // EFFECTS: parses card from JSON object and adds it to inventory
    private void addCard(Inventory inv, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String attributeOneName = jsonObject.getString("attributeOneName");
        String attributeOneValue = jsonObject.getString("attributeOneValue");
        String attributeTwoName = jsonObject.getString("attributeTwoName");
        String attributeTwoValue = jsonObject.getString("attributeTwoValue");
        String attributeThreeName = jsonObject.getString("attributeThreeName");
        String attributeThreeValue = jsonObject.getString("attributeThreeValue");
        String description = jsonObject.getString("description");
        Attribute attributeOne = new Attribute(attributeOneName,attributeOneValue);
        Attribute attributeTwo = new Attribute(attributeTwoName,attributeTwoValue);
        Attribute attributeThree = new Attribute(attributeThreeName,attributeThreeValue);
        Card card = new Card(name, attributeOne, attributeTwo, attributeThree, description);
        inv.addCard(card);
    }
}
