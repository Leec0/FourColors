package be.fourcolors.console.game.players;

import be.fourcolors.console.game.cards.Card;
import be.fourcolors.console.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final List<Card> cards;

    public HumanPlayer() {
        cards = new ArrayList<>();
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int playTurn() {
        return playMenu();
    }

    @Override
    public Card playCard(int index) {
        Card returnCard = cards.get(index);
        cards.remove(index);
        return returnCard;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void uno() {

    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card).append("\n");
        }
        return stringBuilder.toString();
    }

    private int playMenu() {
        int selectedOption;
        Scanner sc = new Scanner(System.in);
        StringBuilder menuText = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            menuText.append(String.format("%d: %s%n", i + 1, cards.get(i)));
        }
        menuText.append(String.format("%d: %s%n", cards.size() + 1, "Neem kaart"));
        menuText.append("Select: ");
        System.out.print(menuText);
        do {
            selectedOption = sc.nextInt();
            if (selectedOption < 1 || selectedOption > cards.size() + 1) {
                System.out.println("Foutieve input");
                System.out.print(menuText);
            }
        } while (selectedOption < 1 || selectedOption > cards.size() + 1);
        return selectedOption;
    }

    @Override
    public int selectWildColor() {
        String menuText = """
                1: RED
                2: BLUE
                3: YELLOW
                4: GREEN
                Select:""";
        System.out.print(menuText);
        int selection;
        do {
            Scanner sc = new Scanner(System.in);
            selection = sc.nextInt();
            if (selection <= 0 || selection > 4) {
                System.out.println("Foutieve invoer");
                System.out.print(menuText);
            }
        } while (selection <= 0 || selection > 4);
        return selection;
    }
}
