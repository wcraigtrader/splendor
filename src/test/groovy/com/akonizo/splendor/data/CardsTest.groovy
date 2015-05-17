package com.akonizo.splendor.data

import spock.lang.Specification

class CardsTest extends Specification {

    def "Create some cards from String"() {
        when:
            Cards c = new Cards()
            c.load( """deck,produces,requires,value
3,Black,RRRRRRRKKK,5
3,Blue,WWWWWWWBBB,5
""" )
        then:
            2 == c.cards.size()
            new Card( Deck.BLUE, Color.BLACK, 'RRRRRRRKKK', 5 ) in c.cards
            new Card( '3', 'Blue', 'WWWWWWWBBB', '5' ) in c.cards
    }

    def "Create all cards from data file"() {
        when:
            Cards c = new Cards()
        then:
            90 == c.cards.size()
    }
}