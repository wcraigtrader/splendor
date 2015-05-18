package com.akonizo.splendor.data

import spock.lang.Specification

class CardsTest extends Specification {

    Cards c

    def "Test initial conditions"() {
        when:
            c = new Cards()

        then:
            0 == c.cards.size()
    }

    def "Create cards from an empty string"() {
        when:
            c = new Cards()
            c.load("")

        then:
            0 == c.cards.size()
    }

    def "Create some cards from a string"() {
        when:
            c = new Cards()
            c.load( """deck,produces,requires,value
3,Black,RRRRRRRKKK,5
3,Blue,WWWWWWWBBB,5
""" )
        then:
            2 == c.cards.size()
            new Card( Deck.BLUE, Gem.BLACK, 'RRRRRRRKKK', 5 ) in c.cards
            new Card( '3', 'Blue', 'WWWWWWWBBB', '5' ) in c.cards
    }

    def "Test the Splendor decks"() {
        when:
            c = Cards.getSplendor()

        then:
            90 == c.cards.size()
            90 == c.findAll( { true } ).size()

            20 == c.findAll { it.deck == Deck.BLUE }.size()
            30 == c.findAll { it.deck == Deck.GOLD }.size()
            40 == c.findAll { it.deck == Deck.GREEN }.size()

            18 == c.findAll { it.provides == Gem.WHITE }.size()
            18 == c.findAll { it.provides == Gem.BLUE }.size()
            18 == c.findAll { it.provides == Gem.GREEN }.size()
            18 == c.findAll { it.provides == Gem.RED }.size()
            18 == c.findAll { it.provides == Gem.BLACK }.size()

             5 == c.findAll { it.value == 5 }.size()
            10 == c.findAll { it.value == 4 }.size()
            10 == c.findAll { it.value == 3 }.size()
            15 == c.findAll { it.value == 2 }.size()
            15 == c.findAll { it.value == 1 }.size()
            35 == c.findAll { it.value == 0 }.size()
    }
}