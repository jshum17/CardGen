package ui;

import exception.CardRestrictionException;
import model.Attribute;
import model.Card;
import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// EFFECTS: GUI for CardGen application
public class CardGenAppGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final Color BGCOLOR = (new Color(246, 215, 85));

    // inventory
    private Inventory inv = new Inventory();

    // home screen
    private final JFrame frame = new JFrame("CardGen");
    private final JPanel homePanel = new JPanel();
    private final JButton addButton = new JButton("Add Card");
    private final JButton inventoryButton = new JButton("View Inventory");
    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");

    // add screen
    private final JPanel addPanel = new JPanel();
    private final JButton addImgButton = new JButton("+");
    private final JButton addCardButton = new JButton("Add");
    private final JButton clearAllButton = new JButton("Clear All");
    private final JButton backFromAddPanelButton = new JButton("Back");

    private final JLabel addCardNameLabel = new JLabel("Card Name:");
    private final JTextField addCardNameTextField = new JTextField();
    private final JLabel addCardAttributeOneLabel = new JLabel("Attribute 1:");
    private final JTextField addCardAttributeOneTextField = new JTextField();
    private final JLabel addCardAttributeOneValueLabel = new JLabel("Value:");
    private final JTextField addCardAttributeOneValueTextField = new JTextField();
    private final JLabel addCardAttributeTwoLabel = new JLabel("Attribute 2:");
    private final JTextField addCardAttributeTwoTextField = new JTextField();
    private final JLabel addCardAttributeTwoValueLabel = new JLabel("Value:");
    private final JTextField addCardAttributeTwoValueTextField = new JTextField();
    private final JLabel addCardAttributeThreeLabel = new JLabel("Attribute 3:");
    private final JTextField addCardAttributeThreeTextField = new JTextField();
    private final JLabel addCardAttributeThreeValueLabel = new JLabel("Value:");
    private final JTextField addCardAttributeThreeValueTextField = new JTextField();
    private final JLabel addCardDescription = new JLabel("Description:");
    private final JTextArea addCardDescriptionTextArea = new JTextArea();
    private final JScrollPane addCardDescriptionTextAreaScroll = new JScrollPane(addCardDescriptionTextArea);

    // inventory screen
    private final JPanel inventoryPanel = new JPanel();
    private final JButton backFromInvPanelButton = new JButton("Back");
    private final JButton removeCardButton = new JButton("Remove");
    private final JButton viewCardButton = new JButton("View");

    private DefaultListModel cardListModel = new DefaultListModel();
    private JList displayCardList = new JList(cardListModel);
    private final JScrollPane displayCardListScroll = new JScrollPane(displayCardList);

    // card screen
    private final JFrame cardFrame = new JFrame();
    private final JPanel cardPanel = new JPanel();

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CardGenAppGUI();
            }
        });
    }

    // EFFECTS: Sets the layout of the main frame and all the panels
    public CardGenAppGUI() {
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setHomeScreen();
        setAddScreen();
        setInventoryPanel();
        actionPerformed();

        frame.add(homePanel);

        // display the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the layout of the home screen
    public void setHomeScreen() {
        // home Panel
        homePanel.setLayout(null);
        homePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        homePanel.setBackground(BGCOLOR);

        // home Panel Logo
        // Citation: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon#:~:text=Resizing%20the%
        //           20icon%20is%20not,method%20to%20get%20the%20image.
        // Modelled after StackOverflow solution for resizing ImageIcon
        ImageIcon imageIcon = new ImageIcon("./data/CardGenLogo.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH); // scale it
        imageIcon = new ImageIcon(newimg);  // transform it back

        JLabel logoLabel = new JLabel(imageIcon);
        homePanel.add(logoLabel);
        logoLabel.setBounds(0, 100, 400, 100);

        // home Panel buttons
        homePanel.add(addButton);
        addButton.setBounds((WIDTH / 2) - 100, 280, 200, 40);
        addButton.setFocusPainted(false);

        homePanel.add(inventoryButton);
        inventoryButton.setBounds((WIDTH / 2) - 100, 340, 200, 40);
        inventoryButton.setFocusPainted(false);

        homePanel.add(saveButton);
        saveButton.setBounds((WIDTH / 2) - 100, 400, 200, 40);
        saveButton.setFocusPainted(false);

        homePanel.add(loadButton);
        loadButton.setBounds((WIDTH / 2) - 100, 460, 200, 40);
        loadButton.setFocusPainted(false);
    }

    // MODIFIES: this
    // EFFECTS: Sets the layout of the add screen
    public void setAddScreen() {
        // add Panel
        addPanel.setLayout(null);
        addPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addPanel.setBackground(BGCOLOR);

        // add Panel buttons
        addPanel.add(addImgButton);
        addImgButton.setBounds((WIDTH / 2) - 40, 50, 80, 80);
        addImgButton.setFocusPainted(false);

        addPanel.add(addCardButton);
        addCardButton.setBounds(30, 500, 100, 40);
        addCardButton.setFocusPainted(false);

        addPanel.add(clearAllButton);
        clearAllButton.setBounds((WIDTH / 2) - 50, 500, 100, 40);
        clearAllButton.setFocusPainted(false);

        addPanel.add(backFromAddPanelButton);
        backFromAddPanelButton.setBounds(270, 500, 100, 40);
        backFromAddPanelButton.setFocusPainted(false);

        // add Panel fields
        setAddScreenNameAndDesc();
        setAddScreenAttribute();
        setAddScreenAttributeValue();

    }

    // MODIFIES: this
    // EFFECTS: Sets the name and description text fields of the add screen
    public void setAddScreenNameAndDesc() {
        addPanel.add(addCardNameLabel);
        addCardNameLabel.setBounds(30, 160, 100, 20);

        addPanel.add(addCardNameTextField);
        addCardNameTextField.setBounds(100, 160, 260, 20);

        addPanel.add(addCardDescription);
        addCardDescription.setBounds(30, 320, 100, 20);

        addPanel.add(addCardDescriptionTextAreaScroll);
        addCardDescriptionTextArea.setLineWrap(true);
        addCardDescriptionTextArea.setWrapStyleWord(true);
        addCardDescriptionTextAreaScroll.setBounds(30, 360, 340, 100);
    }

    // MODIFIES: this
    // EFFECTS: Sets the attribute text fields of the add screen
    public void setAddScreenAttribute() {
        addPanel.add(addCardAttributeOneLabel);
        addCardAttributeOneLabel.setBounds(30, 200, 70, 20);

        addPanel.add(addCardAttributeOneTextField);
        addCardAttributeOneTextField.setBounds(100, 200, 100, 20);

        addPanel.add(addCardAttributeTwoLabel);
        addCardAttributeTwoLabel.setBounds(30, 240, 70, 20);

        addPanel.add(addCardAttributeTwoTextField);
        addCardAttributeTwoTextField.setBounds(100, 240, 100, 20);

        addPanel.add(addCardAttributeThreeLabel);
        addCardAttributeThreeLabel.setBounds(30, 280, 70, 20);

        addPanel.add(addCardAttributeThreeTextField);
        addCardAttributeThreeTextField.setBounds(100, 280, 100, 20);
    }

    // MODIFIES: this
    // EFFECTS: Sets the attribute value text fields of the add screen
    public void setAddScreenAttributeValue() {
        addPanel.add(addCardAttributeOneValueLabel);
        addCardAttributeOneValueLabel.setBounds(220, 200, 40, 20);

        addPanel.add(addCardAttributeOneValueTextField);
        addCardAttributeOneValueTextField.setBounds(260, 200, 100, 20);

        addPanel.add(addCardAttributeTwoValueLabel);
        addCardAttributeTwoValueLabel.setBounds(220, 240, 40, 20);

        addPanel.add(addCardAttributeTwoValueTextField);
        addCardAttributeTwoValueTextField.setBounds(260, 240, 100, 20);

        addPanel.add(addCardAttributeThreeValueLabel);
        addCardAttributeThreeValueLabel.setBounds(220, 280, 40, 20);

        addPanel.add(addCardAttributeThreeValueTextField);
        addCardAttributeThreeValueTextField.setBounds(260, 280, 100, 20);
    }

    // MODIFIES: this
    // EFFECTS: Sets the layout of the inventory screen
    public void setInventoryPanel() {
        // inventory Panel
        inventoryPanel.setLayout(null);
        inventoryPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        inventoryPanel.setBackground(BGCOLOR);

        // inventory Panel buttons
        inventoryPanel.add(viewCardButton);
        viewCardButton.setBounds(30, 500, 100, 40);
        viewCardButton.setFocusPainted(false);

        inventoryPanel.add(removeCardButton);
        removeCardButton.setBounds((WIDTH / 2) - 50, 500, 100, 40);
        removeCardButton.setFocusPainted(false);

        inventoryPanel.add(backFromInvPanelButton);
        backFromInvPanelButton.setBounds(270, 500, 100, 40);
        backFromInvPanelButton.setFocusPainted(false);

        // inventory Panel List
        inventoryPanel.add(displayCardListScroll);
        displayCardListScroll.setBounds(30, 60, 340, 400);
        displayCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        displayCardList.setSelectedIndex(0);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame of the card screen
    public void setCardFrame(Card card) {
        cardFrame.setResizable(false);
        cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setCardPanel(card);
        setContentPane(cardPanel);

        cardFrame.add(cardPanel);

        // Citation: https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close
        // Modelled after StackOverflow solution for action listener for closing window
        cardFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cardPanel.removeAll();
                e.getWindow().dispose();
            }
        });

        cardFrame.pack();
        cardFrame.setLocationRelativeTo(null);
        cardFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the panel of the card screen
    public void setCardPanel(Card card) {
        try {
            // fields
            BufferedImage cardTemplate = ImageIO.read(new File("./data/CardFrame.png"));
            BufferedImage cardTemplateOverlay = ImageIO.read(new File("./data/CardFrameOverlay.png"));
            BufferedImage imageIcon = ImageIO.read(new File("./data/CardSample.jpg"));
            JLabel cardLabel = new JLabel(new ImageIcon(cardTemplate));
            JLabel cardLabelOverlay = new JLabel(new ImageIcon(cardTemplateOverlay));
            JLabel cardImg = new JLabel(new ImageIcon(imageIcon));
            JTextArea description = new JTextArea();

            // sets the image of the card template
            cardLabel.add(cardLabelOverlay);
            cardLabelOverlay.setBounds(0, 0, 400, 559);
            cardLabel.add(cardImg);
            cardImg.setBounds(24, 37, 352, 267);
            cardLabel.add(description);
            description.setBounds(208, 368, 160, 115);

            // fills the card texts
            Graphics g = cardTemplate.getGraphics();
            fillCardGraphic(g,card, description);

            cardPanel.add(cardLabel);
            cardLabel.setBounds(0, 100, 400, 100);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // helpers

    // MODIFIES: this, inv
    // EFFECTS: construct a new card from the given card info and add it to inventory
    public Card constructCard() {
        String name = addCardNameTextField.getText();
        Attribute attributeOne = new Attribute(addCardAttributeOneTextField.getText(),
                addCardAttributeOneValueTextField.getText());
        Attribute attributeTwo = new Attribute(addCardAttributeTwoTextField.getText(),
                addCardAttributeTwoValueTextField.getText());
        Attribute attributeThree = new Attribute(addCardAttributeThreeTextField.getText(),
                addCardAttributeThreeValueTextField.getText());
        String description = addCardDescriptionTextArea.getText();

        Card card = new Card(name, attributeOne, attributeTwo, attributeThree, description);
        inv.addCard(card);
        return card;
    }

    // MODIFIES: this
    // EFFECTS: fills the card template with the given card info
    public void fillCardGraphic(Graphics g, Card card, JTextArea description) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20));
        g.drawString(card.getName(), 60, 338);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
        g.drawString(card.getAttributeOne().getName(), 52, 384);
        g.drawString(card.getAttributeTwo().getName(), 52, 412);
        g.drawString(card.getAttributeThree().getName(), 52, 442);
        g.drawString(card.getAttributeOne().getValue(), 160, 384);
        g.drawString(card.getAttributeTwo().getValue(), 160, 412);
        g.drawString(card.getAttributeThree().getValue(), 160, 442);
        g.dispose();

        description. setFont(new Font("Arial", Font. PLAIN, 12));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setText(card.getDescription());
        description.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: clear all the text fields
    public void addPanelClearAll() {
        addCardNameTextField.setText("");
        addCardAttributeOneTextField.setText("");
        addCardAttributeOneValueTextField.setText("");
        addCardAttributeTwoTextField.setText("");
        addCardAttributeTwoValueTextField.setText("");
        addCardAttributeThreeTextField.setText("");
        addCardAttributeThreeValueTextField.setText("");
        addCardDescriptionTextArea.setText("");
    }

    // MODIFIES: this
    // EFFECTS: shows a pop-up message window with the info given
    public static void popUpMessage(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage,
                "CardGen", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: responsible for performing all the button actions
    public void actionPerformed() {
        addButtonAction();
        inventoryButtonAction();
        saveButtonAction();
        loadButtonAction();
        addCardButtonAction();
        addCardImgButtonAction();
        clearAllButtonAction();
        backFromAddPanelButtonAction();
        removeCardButtonAction();
        viewCardButtonAction();
        backFromInvPanelButtonAction();
    }

    // EFFECTS: shows the add screen and closes the home screen
    public void addButtonAction() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.add(addPanel);
                homePanel.setVisible(false);
                addPanel.setVisible(true);
            }
        });
    }

    // EFFECTS: shows the inventory screen and closes the home screen
    public void inventoryButtonAction() {
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.add(inventoryPanel);
                homePanel.setVisible(false);
                inventoryPanel.setVisible(true);
            }
        });
    }

    // EFFECTS: save the current file
    public void saveButtonAction() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter = new JsonWriter(JSON_STORE);
                    jsonWriter.open();
                    jsonWriter.write(inv);
                    jsonWriter.close();
                    popUpMessage("Inventory saved!");

                } catch (FileNotFoundException exception) {
                    popUpMessage("Unable to save file!");
                }
            }
        });
    }

    // EFFECTS: load saved file onto application
    private void loadButtonAction() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonReader = new JsonReader(JSON_STORE);
                    inv = jsonReader.read();
                    for (Card c : inv.getAllCards()) {
                        cardListModel.addElement(c.getName());
                    }
                    popUpMessage("Inventory loaded!");
                } catch (IOException exception) {
                    System.exit(1);
                }
            }
        });
    }

    // MODIFIES: this, inv
    // EFFECTS: add card to the inventory display if restrictions are met
    public void addCardButtonAction() {
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addCardRestrictions();
                    addCardAttributeRestrictions();
                    Card currentCard = constructCard();
                    cardListModel.addElement(currentCard.getName());
                    addPanelClearAll();
                    popUpMessage("Card added!");
                } catch (CardRestrictionException ex) {
                    //
                }
            }
        });
    }

    // EFFECTS: add the card image
    public void addCardImgButtonAction() {
        addImgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpMessage("Add Card Image: Feature coming soon!");
            }
        });
    }

    // EFFECTS: clear all the text fields
    public void clearAllButtonAction() {
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelClearAll();
            }
        });
    }

    // EFFECTS: shows the home screen and closes the add screen
    public void backFromAddPanelButtonAction() {
        backFromAddPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(true);
                addPanel.setVisible(false);
            }
        });
    }

    // MODIFIES: this, inv
    // EFFECTS: remove selected card from the inventory and the display
    public void removeCardButtonAction() {
        removeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = cardListModel.getSize();
                    int selected = displayCardList.getSelectedIndex();
                    if (size == 0) {
                        popUpMessage("No Card is selected.");
                    } else {
                        Object name = cardListModel.get(selected);
                        Card selectedCard = inv.searchCard(name.toString());
                        cardListModel.remove(selected);
                        inv.removeCard(selectedCard);
                        popUpMessage("Card removed!");
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    popUpMessage("No Card is selected.");
                }
            }
        });
    }

    // EFFECTS: display the card screen of the selected card
    public void viewCardButtonAction() {
        viewCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = cardListModel.getSize();
                    int selected = displayCardList.getSelectedIndex();
                    if (size == 0) {
                        popUpMessage("No Card is selected.");
                    } else {
                        Object name = cardListModel.get(selected);
                        Card selectedCard = inv.searchCard(name.toString());
                        setCardFrame(selectedCard);
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    popUpMessage("No Card is selected.");
                }
            }
        });
    }

    // EFFECTS: shows the home screen and closes the inventory screen
    public void backFromInvPanelButtonAction() {
        backFromInvPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(true);
                inventoryPanel.setVisible(false);
            }
        });
    }

    // EFFECTS: display a pop-up warning for a list of text field restrictions for adding a card
    //          also throws a new CardRestrictionException for each restriction
    public void addCardRestrictions() throws CardRestrictionException {
        if (addCardNameTextField.getText().equals("")) {
            popUpMessage("Please enter card name.");
            throw new CardRestrictionException();

        } else if (addCardNameTextField.getText().length() > 27) {
            popUpMessage("Card name is too long!");
            throw new CardRestrictionException();

        } else if (addCardDescriptionTextArea.getText().length() > 195) {
            popUpMessage("Card Description is too long!");
            throw new CardRestrictionException();
        }
    }

    // EFFECTS: display a pop-up warning for a list of attribute text field restrictions for adding a card
    //          also throws a new CardRestrictionException for each restriction
    public void addCardAttributeRestrictions() throws CardRestrictionException {
        if (addCardAttributeOneTextField.getText().length() > 13) {
            popUpMessage("Attribute One's name is too long!");
            throw new CardRestrictionException();

        } else if (addCardAttributeOneValueTextField.getText().length() > 5) {
            popUpMessage("Attribute One's value is too big!");
            throw new CardRestrictionException();

        } else if (addCardAttributeTwoTextField.getText().length() > 13) {
            popUpMessage("Attribute Two's name is too long!");
            throw new CardRestrictionException();

        } else if (addCardAttributeTwoValueTextField.getText().length() > 5) {
            popUpMessage("Attribute Two's value is too big!");
            throw new CardRestrictionException();

        } else if (addCardAttributeThreeTextField.getText().length() > 13) {
            popUpMessage("Attribute Three's name is too long!");
            throw new CardRestrictionException();

        } else if (addCardAttributeThreeValueTextField.getText().length() > 5) {
            popUpMessage("Attribute Three's value is too big!");
            throw new CardRestrictionException();
        }
    }
}
