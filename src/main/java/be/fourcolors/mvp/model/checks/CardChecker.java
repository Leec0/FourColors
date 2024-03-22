package be.fourcolors.mvp.model.checks;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.cards.CardType;

public class CardChecker {
    private boolean requiredToPlayDraw;

    public CardChecker() {
        requiredToPlayDraw = false;
    }
    public boolean canBePlayed(Card card, Card playedCard, CardColor wildCardColor) {
        if (requiredToPlayDraw) {
            return card.getType() == CardType.DRAW;
        }
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

    public boolean isRequiredToPlayDraw() {
        return requiredToPlayDraw;
    }

    public void setRequiredToPlayDraw(boolean requiredToPlayDraw) {
        this.requiredToPlayDraw = requiredToPlayDraw;
    }
}
