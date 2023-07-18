package model;

import java.util.ArrayList;
import java.util.List;

// Represents an inventory where all the cards will be stored
public class Inventory {

    private ArrayList<Card> myCards;

    public Inventory() {
        myCards = new ArrayList<>();
    }

    public void addCard(Card c) {
        myCards.add(c);
    }

    public void removeCard(Card c) {
        myCards.remove(c);
    }

    public List<Card> getAllCards() {
        return myCards;
    }
}
