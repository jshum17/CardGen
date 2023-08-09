package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an inventory where all the cards will be stored
public class Inventory implements Writable {

    private ArrayList<Card> myCards;

    // EFFECTS: constructs an inventory with an empty ArrayList of cards
    public Inventory() {
        myCards = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given card to the list of cards
    public void addCard(Card c) {
        myCards.add(c);
        EventLog.getInstance().logEvent(new Event("A Card has been added"));
    }

    // REQUIRES no duplicate cards in list of cards
    // MODIFIES: this
    // EFFECTS: if the given card is found in the list of cards, removes the given card from the list of cards
    //          and returns true. Otherwise, return false
    public boolean removeCard(Card c) {
        if (myCards.contains(c)) {
            myCards.remove(c);
            EventLog.getInstance().logEvent(new Event("A Card has been removed"));
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES no duplicate card names in list of cards
    // EFFECTS: returns the item that matches the given name. If no item exists, return null
    public Card searchCard(String name) {
        for (Card c : myCards) {
            if (c.getName().equals(name)) {
                EventLog.getInstance().logEvent(new Event("A Card has been searched and returned"));
                return c;
            }
        }
        return null;
    }

    // getters

    //EFFECTS: returns a list of all the cards
    public List<Card> getAllCards() {
        return myCards;
    }

    //EFFECTS: returns the size of the list
    public int getSize() {
        return myCards.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Cards", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in this Inventory as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : myCards) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
