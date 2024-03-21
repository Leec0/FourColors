package be.fourcolors.mvp.model.checks;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;

public class CardChecker {
    public boolean canBePlayed(Card card, Card playedCard, CardColor wildCardColor) {
        boolean result;
        result = card.getColor() == CardColor.WILD;
        if (!result) {
            result = card.getColor() == playedCard.getColor();
            if (!result) {
                if (card.getType() == null) {
                    result = card.getNumber() == playedCard.getNumber();
                } else {
                    result = card.getType() == playedCard.getType();
                }
                if (playedCard.getColor() == CardColor.WILD && !result) {
                    result = card.getColor() == wildCardColor;
                }
            }
        }
        return result;
    }
}
