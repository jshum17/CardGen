package ui;

import model.Inventory;

import java.util.Scanner;

// CashGen Application
public class CardGenApp {
    private Scanner input;
    private Inventory myInventory;

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runCardGen() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
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
            doSaveFile();
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
        System.out.println("\tq -> Quit");
    }

    // MODIFIES:
    // EFFECTS:
    private void doAddCard() {
        System.out.println("Enter Card Name:");
    }

    private void doRemoveCard() {

    }

    private void doViewInventory() {

    }

    private void doSaveFile() {

    }
}
