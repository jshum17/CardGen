package ui;

import model.Attribute;
import model.Card;
import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// CashGen Application
public class CardGenApp {
    private static final String JSON_STORE = "./data/inventory.json";
    private Scanner input;
    private Inventory myInventory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runCardGen() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCard();
        } else if (command.equals("r")) {
            doRemoveCard();
        } else if (command.equals("e")) {
            doViewInventory();
        } else if (command.equals("s")) {
            saveCards();
        } else if (command.equals("l")) {
            loadCards();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes card inventory
    private void init() {
        myInventory = new Inventory();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a card");
        System.out.println("\tr -> Remove a card");
        System.out.println("\te -> View inventory");
        System.out.println("\ts-> Save file");
        System.out.println("\tl-> Load file");
        System.out.println("\tq -> Quit");
    }

    // EFFECTS: performs the tasks for adding a card to inventory
    private void doAddCard() {
        Card c = new Card(getCardInfo("Enter card name:"),
                getCardAttribute("Enter first attribute", "Enter first attribute value"),
                getCardAttribute("Enter second attribute", "Enter second attribute value"),
                getCardAttribute("Enter third attribute", "Enter third attribute value"),
                getCardInfo("Enter card description"));

        myInventory.addCard(c);
    }

    // EFFECTS: performs the tasks for removing a card from inventory
    private void doRemoveCard() {
        System.out.println("Enter the name of the card you want to remove:");
        String userInput;
        userInput = input.nextLine();

        Card c = myInventory.searchCard(userInput);
        if (myInventory.removeCard(c)) {
            myInventory.removeCard(c);
            System.out.println(userInput + " has been removed");
        } else {
            System.out.println("There is no card with the name: " + userInput);
        }
    }

    // EFFECTS: performs the tasks for showing all cards in inventory
    private void doViewInventory() {
        if (myInventory.getAllCards().isEmpty()) {
            System.out.println("Your inventory is empty.");
        }
        for (Card c: myInventory.getAllCards()) {
            System.out.println(c.getName());
        }
    }

    // EFFECTS: saves the cards to file
    private void saveCards() {
        try {
            jsonWriter.open();
            jsonWriter.write(myInventory);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads cards from file
    private void loadCards() {
        try {
            myInventory = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns the info inputted by the user
    private String getCardInfo(String field) {
        System.out.println(field);
        String userInput;
        userInput = input.nextLine();
        return userInput;
    }


    // EFFECTS: returns the attribute according to info inputted by the user
    private Attribute getCardAttribute(String name, String value) {
        return new Attribute(getCardInfo(name), getCardInfo(value));
    }
}
