package com.masterdevskills.cha1.ext4;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 08 August 2020
 */

//29
public class ItalianDeck implements Deck {
    private final List<Card> pointCards;
    private final List<Card> colorCards;
    private final List<Card> scoreCards;

    public ItalianDeck() {
        pointCards = new ArrayList<>();
        colorCards = new ArrayList<>();
        scoreCards = new ArrayList<>();
    }

    public void sort() {
        Collections.sort(pointCards);
    }

    @Override
    public void sort(final Comparator<Card> c) {

        Collections.sort(pointCards, c);
    }

    @Override
    public String deckToString() {
        Function<Card, String> function = (Card card) -> card.toString();

        return pointCards.stream()
                .map(function)
                .collect(Collectors.joining(","));
    }

    @Override
    public Map<Integer, Deck> deal(final int players, final int numberOfCards) throws IllegalArgumentException {
        if(players * numberOfCards > size()) {
            throw new IllegalArgumentException("Deck dont have sufficient cards");
        }

        Map<Integer, Deck> map = new HashMap<>();
        for(int playerNo = 0; playerNo < players; playerNo++) {
            map.put(playerNo, new StandardDeck());
        }

        softShuffle(10);
        var cards  = new ArrayList<Card>();
        cards.addAll(pointCards);

        for(int cardNo = 0; cardNo < numberOfCards; cardNo++) {
            for(int playerNo = 0; playerNo < players; playerNo++) {
                var index  =  new Random().nextInt(cards.size());
                map.get(playerNo).addCard(cards.get(index));
                cards.remove(index);
            }
        }

        return map;
    }

    @Override
    public List<Card> getCards() {
        return pointCards;
    }

    @Override
    public Deck deckFactory() {
        var allCards  =  new ArrayList<Card>();
        Arrays.stream(Card.Suit.values())
                .forEach(suit -> {
                    Arrays.stream(Card.Rank.values()).forEach(rank -> {
                        var card = new PlayingCard(suit, rank);
                        allCards.add(card);
                    });
                });

        pointCards.addAll(getSpecificRankedCardsForAllSuit(List.of(Card.Rank.ACE, Card.Rank.KING, Card.Rank.QUEEN,
                Card.Rank.JACK, Card.Rank.TEN, Card.Rank.NINE, Card.Rank.EIGHT, Card.Rank.SEVEN)));

        colorCards.addAll(getSpecificRankedCardsForAllSuit(List.of(Card.Rank.DEUCE, Card.Rank.THREE, Card.Rank.FOUR,
                Card.Rank.FIVE)));

        pointCards.addAll(getSpecificRankedCardsForAllSuit(List.of(Card.Rank.SIX)));

        return this;
    }

    @Override
    public int size() {
        return pointCards.size();
    }

    @Override
    public void addCard(final Card card) {

        pointCards.add(card);
    }

    @Override
    public void addCards(final List<Card> cards) {

        pointCards.addAll(cards);
    }

    @Override
    public void addDeck(final Deck deck) {

        pointCards.addAll(deck.getCards());
    }

    public void shuffle() {
        Collections.shuffle(pointCards);
    }

    public List<Card> getPointCards() {
        return pointCards;
    }

    public List<Card> getColorCards() {
        return colorCards;
    }

    public List<Card> getScoreCards() {
        return scoreCards;
    }
}
