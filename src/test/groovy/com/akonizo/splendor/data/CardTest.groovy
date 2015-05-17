package com.akonizo.splendor.data

import spock.lang.Specification

class CardTest extends Specification {

    def "Create card without value"() {
        when:
            Card card = new Card( '1', 'Black', 'WBGR' )
        then:
            Deck.GREEN == card.deck
            Gem.BLACK == card.provides
            0 == card.value
            1 == card.counts[Gem.WHITE]
            1 == card.counts[Gem.BLUE]
            1 == card.counts[Gem.GREEN]
            1 == card.counts[Gem.RED]
            0 == card.counts[Gem.BLACK]
    }

    def "Create card with value"() {
        when:
            Card card = new Card( '3','Black','WWWBBBGGGGGRRR','3')
        then:
            Deck.BLUE == card.deck
            Gem.BLACK == card.provides
            3 == card.value
            3 == card.counts[Gem.WHITE]
            3 == card.counts[Gem.BLUE]
            5 == card.counts[Gem.GREEN]
            3 == card.counts[Gem.RED]
            0 == card.counts[Gem.BLACK]
    }

}
