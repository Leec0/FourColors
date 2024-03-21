package be.fourcolors.mvp.model.game.players.interfaces;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;

public interface BotBase extends Player {
    boolean playOrDraw(Card playedCard, CardColor wildCardColor); //return true als er een card word gespeeld, false als er een kaart genomen moet worden
    CardColor selectWildColor();
    Card play(Card playedCard, CardColor wildCardColor);
    boolean hasPlayableCard(Card playedCard, CardColor wildCardColor);
    boolean callOutPlayers();
}
