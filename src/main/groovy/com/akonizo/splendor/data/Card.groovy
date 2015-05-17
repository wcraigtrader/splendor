package com.akonizo.splendor.data

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage=false)
class Card {

    Deck deck
    Gem provides
    String requires
    int value
    
    Map<Gem,Integer> counts = new HashMap<Gem,Integer>()

    /** Construct from strings, as found in a spreadsheet */
    Card( String d, String p, String r, String v='') {
        this( Deck.values()[Integer.valueOf( d )-1], p.toUpperCase() as Gem, r, v ? Integer.valueOf( v ) : 0 )
    }

    /** Construct from objects */
    Card( Deck d, Gem p, String r, int v) {
        deck = d
        provides = p
        requires = r
        value = v
        
        Gem.values().each { gem -> counts[ gem ] = 0 }
        requires.each { ch -> counts[ Gem.get( ch ) ] += 1 }

        assert 0 <= value && value <= 5
    }
}
