package com.masterdevskills.cha1.ext4;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public interface Deck {
    List<Card> getCards();

    Deck deckFactory();

    int size();

    void addCard(Card card);

    void addCards(List<Card> cards);

    void addDeck(Deck deck);

    void shuffle();

    void sort();

    void sort(Comparator<Card> c);

    String deckToString();

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;

    default void shuffleNTimes(int n) {
        IntStream.range(0, n).forEach(i -> {
            shuffle();
        });
    }

    default List<Card> getSpecificRankedCardsForAllSuit(List<Card.Rank> ranks) {
        var cards  = new ArrayList<Card>();
        cards.addAll(getCards());

        Predicate<Card> predicate = (Card card) -> ranks.contains(card.getRank());
        cards.removeIf(predicate);

        return cards;
    }

    default List<Card> softShuffle(int n) {
        List<Card> cards  = new ArrayList<>();
        cards.addAll(getCards());

        int count = 0;
        while(count < n) {
            cards = reArrangeListByIndex(cards, new Random().nextInt(cards.size()));
        }

        return cards;
    }

    private List<Card> reArrangeListByIndex(List<Card> cards, int index) {
        List<Card> result = new ArrayList<Card>();
        for(int i=index; i<cards.size(); i++) {
            result.add(cards.get(i));
        }

        for(int i=0; i<index; i++) {
            result.add(cards.get(i));
        }

        return result;
    }
}
