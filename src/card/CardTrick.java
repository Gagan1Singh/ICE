/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 * A class that fills a magic hand of 7 cards with random Card Objects
 * and then asks the user to pick a card and searches the array of cards
 * for the match to the user's card. To be used as starting code in ICE 1
 * @author srinivsi
 * Modified By:- Gaganjotpal Singh
 * Student Number:- 991716953
 * Date:- 30-01-2024
 */

package card;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CardTrick {
    
    private static final Map<String, Integer> SUIT_INDEX_MAP = new HashMap<>();
    
    static {
        SUIT_INDEX_MAP.put("hearts", 0);
        SUIT_INDEX_MAP.put("diamonds", 1);
        SUIT_INDEX_MAP.put("spades", 2);
        SUIT_INDEX_MAP.put("clubs", 3);
    }
    
    public static void main(String[] args) {
        Card[] magicHand = new Card[7];
        
        // Add a hardcoded lucky card (2 of clubs)
        Card luckyCard = new Card();
        luckyCard.setValue(2);
        luckyCard.setSuit(Card.SUITS[3]);
        magicHand[0] = luckyCard; // Add the lucky card to the magicHand array

        for (int i = 1; i < magicHand.length; i++) {
            Card card = new Card();
            card.setValue(generateRandomValue());
            card.setSuit(Card.SUITS[generateRandomNumber(0, 3)]);
            magicHand[i] = card;
        }

        // Ask the user to pick a card
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose a card (enter card value Ace = 1, 2-10, J -11, Q =12, K = 13): ");
        int CardValue = sc.nextInt();

        System.out.print("Choose a suit (enter suit name - Hearts, Diamonds, Spades, Clubs): ");
        String CardSuit = sc.next().toLowerCase();

        Integer CardSuitIndex = SUIT_INDEX_MAP.get(CardSuit);
        
        // Check if the user input is a valid suit
        if (CardSuitIndex == null) {
            System.out.println("Invalid suit name.");
            return;
        }

        // Search for the user's card and report the result
        boolean found = false;
        for (Card card : magicHand) {
            if (card.getValue() == CardValue && card.getSuit().equals(Card.SUITS[CardSuitIndex])) {
                found = true;
                break;
            }
        }

        // Report the result
        if (found) {
            System.out.println("Congratulations! Your card is available in the magic hand.");
        } else {
            System.out.println("Sorry, your card is not available in the magic hand.");
        }

        // Close the scanner to prevent resource leak
        sc.close();
    }

    private static int generateRandomValue() {
        return new Random().nextInt(13) + 1;
    }

    private static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
