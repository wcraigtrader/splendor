package com.akonizo.splendor.data

import spock.lang.Specification

class CardTest extends Specification {

    def "Create card without value"() {
        when:
        Card card = new Card( '1', 'Black', 'WBGR' )

        then:
        card.deck == Deck.GREEN
        card.provides == Gem.BLACK

        card.value == 0
        card.white == 1
        card.blue  == 1
        card.green == 1
        card.red   == 1
        card.black == 0
    }

    def "Create card with value"() {
        when:
        Card card = new Card( '3','Black','WWWBBBGGGGGRRR','3')

        then:
        card.deck == Deck.BLUE
        card.provides == Gem.BLACK

        card.value == 3
        card.white == 3
        card.blue  == 3
        card.green == 5
        card.red   == 3
        card.black == 0
    }
}
