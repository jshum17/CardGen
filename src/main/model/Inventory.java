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

    public boolean removeCard(Card c) {
        if (myCards.contains(c)) {
            myCards.remove(c);
            return true;
        } else {
            return false;
        }
    }

    public Card searchCard(String name) {
        for (Card c : myCards) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public List<Card> getAllCards() {
        return myCards;
    }

    public int getSize() {
        return myCards.size();
    }
}
