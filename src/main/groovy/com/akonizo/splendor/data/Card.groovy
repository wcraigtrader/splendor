package com.akonizo.splendor.data

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage=false)
class Card {

    Deck deck
    Color provides
    String requires
    int value
    Map<Color,Integer> counts = new HashMap<Color,Integer>()

    Card( String d, String p, String r, String v='') {
        this( Deck.values()[Integer.valueOf( d )-1], p.toUpperCase() as Color, r, v ? Integer.valueOf( v ) : 0 )
    }

    Card( Deck d, Color p, String r, int v) {
        deck = d
        provides = p
        requires = r
        value = v
        
        Color.values().each { color -> counts[ color ] = 0 }
        requires.each { c -> counts[ Color.get( c ) ] += 1 }

        assert 0 <= value && value <= 5
    }
}
