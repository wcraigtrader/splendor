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
            1 == card.white
            1 == card.blue
            1 == card.green
            1 == card.red
            0 == card.black
    }

    def "Create card with value"() {
        when:
            Card card = new Card( '3','Black','WWWBBBGGGGGRRR','3')
        then:
            Deck.BLUE == card.deck
            Gem.BLACK == card.provides
            3 == card.value
            3 == card.white
            3 == card.blue
            5 == card.green
            3 == card.red
            0 == card.black
    }

}
