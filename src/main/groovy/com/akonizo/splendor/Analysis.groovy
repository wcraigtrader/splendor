package com.akonizo.splendor

import com.akonizo.splendor.data.Cards
import com.akonizo.splendor.data.Deck
import com.akonizo.splendor.data.Gem

class Analysis {

    static main(args) {
        Cards cards = Cards.getSplendor()

        Gem.values().each { gem ->
            def counts = Deck.values().collect { deck ->
                cards.findAll { deck == it.deck && gem != it.provides }.collect { it.counts[ gem ] }.sum()
            }
            printf( "%5s %s\n", gem, counts )
        }

        def sorted = cards.cards.groupBy( { card -> card.deck }, { card -> card.provides } )
        println sorted
    }

}
