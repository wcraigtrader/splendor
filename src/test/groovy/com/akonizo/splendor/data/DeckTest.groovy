package com.akonizo.splendor.data

import spock.lang.Specification

class DeckTest extends Specification {

    def "Check Decks enumeration"() {
        expect:
            [Deck.GREEN, Deck.GOLD, Deck.BLUE ]== Deck.values()
    }

}
