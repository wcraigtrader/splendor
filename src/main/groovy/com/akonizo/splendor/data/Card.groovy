package com.akonizo.splendor.data

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable

@EqualsAndHashCode
@Sortable(includes=['deck','value','provides','requires'])
class Card {

    Deck deck
    Integer value
    Gem provides
    String requires
    Integer ring

    Map<Gem,Integer> counts = new HashMap<Gem,Integer>()

    /** Construct from strings, as found in a spreadsheet */
    Card( String d, String p, String r, String v='', String i='') {
        this(
        Deck.values()[Integer.valueOf( d )-1],
        p.toUpperCase() as Gem,
        r,
        v ? Integer.valueOf( v ) : 0,
        i ? Integer.valueOf( i ) : 0 )
    }

    /** Construct from objects */
    Card( Deck d, Gem p, String r, int v, int i) {
        deck = d
        provides = p
        requires = r
        value = v
        ring = i

        Gem.values().each { gem -> counts[ gem ] = 0 }
        requires.each { ch -> counts[ Gem.get( ch ) ] += 1 }

        assert 0 <= value && value <= 5
    }

    /** Card gem cost */
    int getCost() {
        return requires.length()
    }
    
    /** White gem cost */
    int getWhite() {
        return counts[Gem.WHITE]
    }

    /** Blue gem cost */
    int getBlue() {
        return counts[Gem.BLUE]
    }

    /** Green gem cost */
    int getGreen() {
        return counts[Gem.GREEN]
    }

    /** Red gem cost */
    int getRed() {
        return counts[Gem.RED]
    }

    /** Black gem cost */
    int getBlack() {
        return counts[Gem.BLACK]
    }

    String getShortRequires() {
        StringBuilder sb = new StringBuilder()
        Gem.values().each { gem ->
            if (counts[gem] > 0) {
                sb.append(  gem.abbrev )
                if ( counts[gem] > 1) {
                    sb.append(  counts[gem] )
                }
            }
        }
        return sb.toString()
    }

    String toString() {
        if (value > 0) {
            return "${deck}( ${shortRequires} >> ${provides}:$value )"
        } else {
            return "${deck}( ${shortRequires} >> ${provides} )"
        }
    }
}
