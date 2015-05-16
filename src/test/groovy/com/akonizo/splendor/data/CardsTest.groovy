package com.akonizo.splendor.data

import spock.lang.Specification

class CardsTest extends Specification {
    
    def "Check Decks enumeration"() {
        expect:
            [ Deck.GREEN, Deck.GOLD, Deck.BLUE ] == Deck.values()
    }
    
    def "Check Colors enumeration"() {
        expect:
            Color.WHITE < Color.BLUE
            Color.BLUE < Color.GREEN
            Color.GREEN < Color.RED
            Color.RED < Color.BLACK
            
            Color.WHITE == 'WHITE' as Color
            Color.BLUE == 'BLUE' as Color
            Color.GREEN == 'GREEN' as Color
            Color.RED == 'RED' as Color
            Color.BLACK == 'BLACK' as Color
            
            Color.WHITE == Color.get( 'W' )
            Color.BLUE == Color.get( 'B' )
            Color.BLUE == Color.get( 'b' )
            Color.BLUE == Color.get( (char) 'B' )
            Color.BLUE == Color.get( (char) 'b' )
            Color.GREEN == Color.get( 'G' )
            Color.RED == Color.get( 'R' )
            Color.BLACK == Color.get( 'K' )
    }

    def "Create card without value"() {
        when:
            Card card = new Card( '1', 'Black', 'WBGR' )
        then:
            Deck.GREEN == card.deck
            Color.BLACK == card.provides
            0 == card.value
    }

    def "Create card with value"() {
        when:
            Card card = new Card( '3','Black','WWWBBBGGGGGRRR','3')
        then:
            Deck.BLUE == card.deck
            Color.BLACK == card.provides
            3 == card.value
    }

    def "Create all cards from data file"() {
        when:
            Cards c = new Cards()
        then:
            90 == c.cards.size()
    }
}