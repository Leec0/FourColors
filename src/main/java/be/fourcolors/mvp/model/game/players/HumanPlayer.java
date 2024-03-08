package be.fourcolors.mvp.model.game.players;

import be.fourcolors.mvp.model.game.Player;
import be.fourcolors.mvp.model.game.cards.Card;

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
    public void playCard(Card card) {
        cards.remove(card);
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        cards.sort(Card::compareTo);
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
