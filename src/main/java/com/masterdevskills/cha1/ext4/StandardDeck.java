package com.masterdevskills.cha1.ext4;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//spade
public class StandardDeck implements Deck {
    private final List<Card> entireDeck;

    public StandardDeck() {
        entireDeck = new ArrayList<>();
    }

    public void sort() {
        Collections.sort(entireDeck);
    }

    @Override
    public void sort(final Comparator<Card> c) {

        Collections.sort(entireDeck, c);
    }

    @Override
    public String deckToString() {
        Function<Card, String> function = (Card card) -> card.toString();

        return entireDeck.stream()
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

        shuffleNTimes(20);
        var cards  = new ArrayList<Card>();
        cards.addAll(entireDeck);

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
        return entireDeck;
    }

    @Override
    public Deck deckFactory() {
        Arrays.stream(Card.Suit.values())
                .forEach(suit -> {
                    Arrays.stream(Card.Rank.values()).forEach(rank -> {
                        var card = new PlayingCard(suit, rank);
                        entireDeck.add(card);
                    });
                });

        return this;
    }

    @Override
    public int size() {
        return entireDeck.size();
    }

    @Override
    public void addCard(final Card card) {

        entireDeck.add(card);
    }

    @Override
    public void addCards(final List<Card> cards) {

        entireDeck.addAll(cards);
    }

    @Override
    public void addDeck(final Deck deck) {

        entireDeck.addAll(deck.getCards());
    }

    public void shuffle() {
        Collections.shuffle(entireDeck);
    }
}
