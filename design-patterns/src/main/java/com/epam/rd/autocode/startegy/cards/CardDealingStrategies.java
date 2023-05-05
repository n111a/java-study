package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> map = new HashMap<>();
            ArrayList<Card> community = new ArrayList<>();
            ArrayList<Card> [] playersCards = new ArrayList[players];

            for(int i=0; i<players; i++)
                playersCards[i] = new ArrayList<>();
            for(int j=0; j<2;j++){
                for(int i=0; i<players; i++)
                    playersCards[i].add(deck.dealCard());
            }
            for(int i=0; i<5; i++)
                community.add(deck.dealCard());
            map.put("Community", community);
            for(int i=0; i<players; i++)
                map.put("Player " +(i+1), playersCards[i]);

            map.put("Remaining", deck.restCards());
            return map;
        };

    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> map = new HashMap<>();

            ArrayList<Card> [] playersCards = new ArrayList[players];

            for(int i=0; i<players; i++)
                playersCards[i] = new ArrayList<>();
            for(int j=0; j<5;j++){
                for(int i=0; i<players; i++)
                    playersCards[i].add(deck.dealCard());
            }

            for(int i=0; i<players; i++)
                map.put("Player " +(i+1), playersCards[i]);

            map.put("Remaining", deck.restCards());
            return map;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> map = new HashMap<>();

            ArrayList<Card> [] playersCards = new ArrayList[players];

            for(int i=0; i<players; i++)
                playersCards[i] = new ArrayList<>();
            for(int j=0; j<13;j++){
                for(int i=0; i<players; i++)
                    playersCards[i].add(deck.dealCard());
            }

            for(int i=0; i<players; i++)
                map.put("Player " +(i+1), playersCards[i]);


            return map;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> map = new HashMap<>();
            ArrayList<Card> trumpCard = new ArrayList<>();
            ArrayList<Card> [] playersCards = new ArrayList[players];

            for(int i=0; i<players; i++)
                playersCards[i] = new ArrayList<>();
            for(int j=0; j<6;j++){
                for(int i=0; i<players; i++)
                    playersCards[i].add(deck.dealCard());
            }

            trumpCard.add(deck.dealCard());
            map.put("Trump card", trumpCard);
            for(int i=0; i<players; i++)
                map.put("Player " +(i+1), playersCards[i]);

            map.put("Remaining", deck.restCards());
            return map;
        };
    }

}
