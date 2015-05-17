package com.akonizo.splendor.data

import spock.lang.Specification

class CardTest extends Specification {

    def "Create card without value"() {
        when:
            Card card = new Card( '1', 'Black', 'WBGR' )
        then:
            Deck.GREEN == card.deck
            Color.BLACK == card.provides
            0 == card.value
            1 == card.counts[Color.WHITE]
            1 == card.counts[Color.BLUE]
            1 == card.counts[Color.GREEN]
            1 == card.counts[Color.RED]
            0 == card.counts[Color.BLACK]
    }

    def "Create card with value"() {
        when:
            Card card = new Card( '3','Black','WWWBBBGGGGGRRR','3')
        then:
            Deck.BLUE == card.deck
            Color.BLACK == card.provides
            3 == card.value
            3 == card.counts[Color.WHITE]
            3 == card.counts[Color.BLUE]
            5 == card.counts[Color.GREEN]
            3 == card.counts[Color.RED]
            0 == card.counts[Color.BLACK]
    }

}
