package com.akonizo.splendor.data

import spock.lang.Specification

import static com.akonizo.splendor.data.Deck.GREEN
import static com.akonizo.splendor.data.Deck.GOLD
import static com.akonizo.splendor.data.Deck.BLUE

class DeckTest extends Specification {

    def "Check Decks enumeration"() {
        expect:
            Deck.values() == [GREEN, GOLD, BLUE ]
    }

}
