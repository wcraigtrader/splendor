package com.akonizo.splendor.data

import spock.lang.Specification

class CardsTest extends Specification {

    Cards c

    def setup() {
        c = new Cards()
    }

    def "Test initial conditions"() {
        expect:
            0 == c.cards.size()
    }

    def "Create cards from an empty string"() {
        when:
            c.load("")
        then:
            0 == c.cards.size()
    }

    def "Create some cards from a string"() {
        when:
            c.load( """deck,produces,requires,value
3,Black,RRRRRRRKKK,5
3,Blue,WWWWWWWBBB,5
""" )
        then:
            2 == c.cards.size()
            new Card( Deck.BLUE, Gem.BLACK, 'RRRRRRRKKK', 5 ) in c.cards
            new Card( '3', 'Blue', 'WWWWWWWBBB', '5' ) in c.cards
    }

    def "Create all cards for Splendor"() {
        when:
            c = Cards.getSplendor()
        then:
            90 == c.cards.size()
    }
}